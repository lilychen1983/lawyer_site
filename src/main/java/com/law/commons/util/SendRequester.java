package com.law.commons.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.helper.StringUtil;

import com.law.commons.po.AccessToken;
import com.law.commons.redis.cache.ICacheClient;
import com.law.commons.redis.cache.service.RedisClient;



public class SendRequester {
	private static final Logger logger = Logger.getLogger(SendRequester.class);
	public static final String AUTH_URL = PpsConfig.getString("authUrl");
	public static final String clientId = PpsConfig.getString("clientId");
	public static final String clientSecret = PpsConfig.getString("clientSecret");
	public static final String unauthorizedExceptionCode = PpsConfig.getString("unauthorizedExceptionCode");
	
	

	public static CloseableHttpClient createSSLClientDefault() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}//18911556340黄燕
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (Exception e) {
			logger.error("获取连接失败：", e);
		}
		return HttpClients.createDefault();
	}

	public synchronized static void getAuth(String oldToken) {
		ICacheClient cacheClient = null;
		try {
			cacheClient = new RedisClient();
			String currentToken = cacheClient.get(AccessToken.KEY);
			if(!oldToken.equals(currentToken)){
				return;
			}
			CloseableHttpClient client = SendRequester.createSSLClientDefault();
			HttpPost post = new HttpPost(AUTH_URL);

			String requestBody = "{\"grant_type\":\"client_credentials\",\"client_id\":\"" + clientId + "\",\"client_secret\":\"" + clientSecret + "\"}";
			HttpEntity entity = new ByteArrayEntity(requestBody.getBytes("UTF-8"));
			post.setEntity(entity);
			int i = 0;
			while (i++ < 3) {
				CloseableHttpResponse response = client.execute(post);
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {

					HttpEntity rentity = response.getEntity();
					String sEntity = EntityUtils.toString(rentity);
					// parse json here.
					JSONObject jsonObject = JSONObject.fromObject(sEntity);
					if (jsonObject.has("error")) {
						String error = jsonObject.getString("error").trim();
						String errorDesc = jsonObject.getString("error_description");

						Exception myException = new Exception(errorDesc);
						throw new Exception(error, myException);
					}else if (jsonObject.has("access_token")) {
						String accessToken = jsonObject.getString("access_token") ;
                        if(!StringUtil.isBlank(accessToken)){
                        	cacheClient.set(AccessToken.KEY, accessToken);
                        	break;
                        }
					}
				}  
				
			}
		} catch (Exception e) {
			logger.error("auth huanxin happend error :", e);
		}finally{
			cacheClient.release();
		}

	}

	// 在自己的程序中调用：

	// public static void request(String proposalId) {
	// //请求的body信息
	// String requestBody = "{proposalId:" + proposalId + "}";
	// HttpClient httpClient = new DefaultHttpClient();
	// HttpPost httpPost = new HttpPost("");
	// //添加header
	// httpPost.addHeader("X-Easymi-AppCode", "AppCode");
	// httpPost.addHeader("X-Easymi-UserName", "UserName");
	// //添加body
	// ByteArrayEntity entity = null;
	// try {
	// entity = new ByteArrayEntity(requestBody.getBytes("UTF-8"));
	// entity.setContentType("application/json");
	// } catch (UnsupportedEncodingException e) {
	// logger.error("向服务器承保接口发起http请求,封装请求body时出现异常", e);
	// throw new RuntimeException("向服务器承保接口发起http请求,封装请求body时出现异常", e);
	// }
	// httpPost.setEntity(entity);
	// //执行post请求
	// HttpResponse response = null;
	// try {
	// response = httpClient.execute(httpPost);
	// } catch (ClientProtocolException e) {
	// logger.error("提交给服务器的请求，不符合HTTP协议", e);
	// throw new RuntimeException("提交给服务器的请求，不符合HTTP协议", e);
	// } catch (IOException e) {
	// logger.error("向服务器承保接口发起http请求,执行post请求异常", e);
	// throw new RuntimeException("向服务器承保接口发起http请求,执行post请求异常", e);
	// }
	// logger.info("状态码：" + response.getStatusLine());
	// }
}
