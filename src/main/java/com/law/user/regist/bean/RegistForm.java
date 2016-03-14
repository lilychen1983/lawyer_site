package com.law.user.regist.bean;

import com.law.commons.BaseForm;

public class RegistForm extends BaseForm {
	private String account;// account String M 用户名，手机号
	private String password;// password String M 密码，md5加密
	private String verify;// verify String M 验证码


	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

 

}
