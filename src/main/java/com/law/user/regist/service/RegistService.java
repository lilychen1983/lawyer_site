package com.law.user.regist.service;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.law.commons.AbsService;
import com.law.commons.BaseForm;
import com.law.commons.Result;
import com.law.commons.po.AccessToken;
import com.law.commons.redis.cache.ICacheClient;
import com.law.commons.redis.cache.service.RedisClient;
import com.law.commons.util.DateUtil;
import com.law.commons.util.PpsConfig;
import com.law.commons.util.SendRequester;
import com.law.user.regist.bean.AccountPo;
import com.law.user.regist.bean.RegistForm;
import com.law.user.regist.dao.IRegistDao;

@Service("registService")
public class RegistService extends AbsService implements IRegistService {
	private static final Logger logger = Logger.getLogger(RegistService.class);
	private static final String REGIST_URL = PpsConfig.getString("registUrl");
	@Resource(name = "registDao")
	private IRegistDao registDao;
	

	@Override
	public Result regist(RegistForm form) {
		String account = form.getAccount();
		Result result = this.valid(form);
		if (!Result.SUCCESS_CODE.equals(result.getStatus())) {
			return result;
		}

		ICacheClient cacheClient = null;
		try {
			cacheClient = new RedisClient();
			String key = AccountPo.KEY+account;
			if(cacheClient.exists(key)){
				result.setStatus(Result.OTHER_ERROR);
				result.setDesc("该用户账号已经被注册过,请使用其他账号注册");
				return result;
			}
//			String verifyKey = VerifyPo.KEY + account;
//			String verifyCode = cacheClient.get(verifyKey);
//			if (StringUtils.isBlank(verifyCode) || !verifyCode.equals(form.getVerify())) {
//				result.setStatus(Result.VERIFY_ERROR);
//				result.setDesc("验证码错误，请重新获取验证码");
//				return result;
//			}
			boolean b = this.registIM(form);
			if (!b) {
				result.setStatus(Result.REGIST_IM_ERROR);
				result.setDesc("向IM注册用户出现错误");
				return result;
			}
			AccountPo po = this.formToPo(form);
			b = this.saveRegistPo(po,cacheClient);
			if (!b) {
				result.setStatus(Result.OTHER_ERROR);
				result.setDesc("保存用户注册信息出现错误");
				return result;
			}
			
		 

		} catch (Exception e) {
			logger.error("send sms error:", e);
		}finally{
			cacheClient.release();
		}

		return result;
	}

	private boolean saveRegistPo(AccountPo po, ICacheClient cacheClient) {
		boolean b = false;
		try {
			b = this.registDao.save(po);
			String key = AccountPo.KEY + po.getAccount();
			String id = UUID.randomUUID().toString();
			String account = po.getAccount();
			String password = po.getPassword();
			String verify = po.getVerify();
			String version = po.getVersion() == null ? "" : po.getVersion();

			String currentTime = DateUtil.getDefaultDate(new Date());
			cacheClient.hset(key, AccountPo.ID, id);
			cacheClient.hset(key, AccountPo.ACCOUNT, account);
			cacheClient.hset(key, AccountPo.PASSWORD, password);
			cacheClient.hset(key, AccountPo.VERIFY, verify);
			cacheClient.hset(key, AccountPo.VERSION, version);
			cacheClient.hset(key, AccountPo.CREATE_TIME, currentTime);
			cacheClient.hset(key, AccountPo.UPDATE_TIME, currentTime);
			
			
		
		} catch (Exception e) {
			logger.error("save registPo happened error:", e);
			e.printStackTrace();
		}
		return b;
	}

	private boolean registIM(RegistForm form) {

		boolean result = true;
		ICacheClient cacheClient = null;
		try {
			cacheClient = new RedisClient();
			CloseableHttpClient client = SendRequester.createSSLClientDefault();
			HttpPost get = new HttpPost(REGIST_URL);
			String requestBody = "{\"username\":\"" + form.getAccount() + "\",\"password\":\"" + form.getPassword() + "\"}";
			HttpEntity entity = new ByteArrayEntity(requestBody.getBytes("UTF-8"));
			get.setEntity(entity);
			String token = cacheClient.get(AccessToken.KEY);
			Header header = new BasicHeader("Authorization", "Bearer " + token);
			get.addHeader(header);
			CloseableHttpResponse response = client.execute(get);
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity rentity = response.getEntity();
				String sEntity = EntityUtils.toString(rentity);
				JSONObject jsonObject = JSONObject.fromObject(sEntity);
				if (jsonObject.has("error")) {
					String error = jsonObject.getString("error");
					String errorDesc = jsonObject.getString("error_description");
					if (error != null && error.equals(SendRequester.unauthorizedExceptionCode)) {
						SendRequester.getAuth(token);
					}
					Exception myException = new Exception(errorDesc);
					logger.error(jsonObject.toString());
					throw new Exception(error, myException);
				}
			} else if(status == 400){

				HttpEntity rentity = response.getEntity();
				String sEntity = EntityUtils.toString(rentity);
				JSONObject jsonObject = JSONObject.fromObject(sEntity);
				logger.error("regist same user:"+form.getAccount()+":"+jsonObject.toString());
				result = true;
			
			}else {
				HttpEntity rentity = response.getEntity();
				String sEntity = EntityUtils.toString(rentity);
				JSONObject jsonObject = JSONObject.fromObject(sEntity);
				logger.error(jsonObject.toString());
				result = false;
			}
		} catch (Exception e) {
			logger.error("regist huanxin user happend error", e);
			result = false;
		} finally {
			cacheClient.release();
		}
		return result;

	}

	private AccountPo formToPo(RegistForm form) {
		String id = UUID.randomUUID().toString();
		String account = form.getAccount();
		String password = form.getPassword();
		String verify = form.getVerify();
		String version = form.getVersion();

		AccountPo po = new AccountPo();
		po.setAccount(account);
		po.setPassword(password);
		po.setVerify(verify);
		po.setVersion(version);

		
		po.setId(id);
		return po;
	}

	@Override
	protected void subValid(BaseForm form, Result result) {

		RegistForm registForm = (RegistForm) form;

		if (StringUtils.isBlank(registForm.getAccount())) {
			result.setStatus(Result.NECESSARY_FIELD_IS_NULL);
			result.setDesc("用户名/手机号字段为空");
			return;
		}

		if (StringUtils.isBlank(registForm.getPassword())) {
			result.setStatus(Result.NECESSARY_FIELD_IS_NULL);
			result.setDesc("密码字段为空");
			return;
		}
		if (StringUtils.isBlank(registForm.getVerify())) {
			result.setStatus(Result.NECESSARY_FIELD_IS_NULL);
			result.setDesc("验证码字段为空");
			return;
		}

	}

	@Override
	protected void init() {
		this.type = "m1_reg";
	}

}
