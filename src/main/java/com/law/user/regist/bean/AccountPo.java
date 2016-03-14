package com.law.user.regist.bean;

import com.law.commons.po.PO;

public class AccountPo extends PO {
	public static final String KEY = "ACCOUNT_";

	public static final String ID = "id";
	public static final String ACCOUNT = "account";
	public static final String PASSWORD = "password";
	public static final String VERIFY = "verify";
	public static final String VERSION = "version";
	public static final String UUID = "uuid";
	public static final String CREATE_TIME = "create_time";
	public static final String UPDATE_TIME = "update_time";
	// public static final String NICKNAME = "nickname";
	// public static final String LABEL = "label";
	// public static final String LOCATION = "location";
	// public static final String DESC = "desc";

	private String id;
	private String account;// account String M 用户名，手机号
	private String password;// password String M 密码，md5加密
	private String verify;// verify String M 验证码
	private String uuid;
	private String version;// version String O 版本号
	private String createTime;
	private String updateTime;

	// private String nickname;// 昵称
	// private String label;// 标签
	// private String location;// 所在地区
	// private String desc;// 个人签名

	// private String sex ;//sex Int 性别，0,男，1女
	// private String birthDay ;//birthDay Datetime 出生日期
	// private String description;//description Text 个人签名
	// private String image ;//image Text 头像url
	// private String fansNum ;//fansNum Long 粉丝总数
	// private String score ;//score Long 积分
	// private String focusNum ;//focusNum Long 关注人数
	// private String videoNums ;//videoNums Long 直播视频数目

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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
