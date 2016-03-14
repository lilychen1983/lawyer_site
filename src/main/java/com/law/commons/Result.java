package com.law.commons;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

public class Result {
	public static final String RESPONSE = "response";
	public static final String SUCCESS_CODE = "0";
	public static final String SUCCESS_MSG = "success";

	public static final String BLANK_CODE = "100001";//
	public static final String TYPE_UNEXPECTED_CODE = "100002";//type字段值与接口定义不匹配
	public static final String TYPE_UNEXPECTED_MSG = "type应为接口指定值";

	public static final String SMS_SEND_ERROR = "100003";//申请验证码错误
	public static final String NECESSARY_FIELD_IS_NULL = "100004";//必要字段为空
	
	public static final String VERIFY_ERROR = "100005";//验证码错误
	public static final String REGIST_IM_ERROR = "100006";//注册接口向IM注册出现错误
	public static final String NECESSARY_FIELD_TYPE_ERROR = "100007";//字段类型错误
	
	public static final String USER_NOT_EXIST_ERROR="100008";//用户不存在
	public static final String USER_PASSWORD_ERROR="100009";//密码错误
	
	public static final String SAVE_PO_ERROR = "100010";//保存到db，数据库操作错误
	
	public static final String DUPLICATE_RECORD_ERROR = "100011";//数据库存在相同记录
	
	
	public static final String OTHER_ERROR = "999999";//注册接口向IM注册出现错误
	
	

	private String id;// id String M 6位随机id
	private String type;// type String M 固定值m1_get_verify,
	private String action;// action String M 固定值 response
	private String status;// status Int M 状态，0表示成功 1表示失败
	private String desc;// desc String O 描述值，当失败的时候服务器需要提供描述信息

	private ResultBody resultBody;

	public String toString() {
		StringBuffer sb = new StringBuffer("{");
		sb.append("\"id\":").append("\"").append(this.id).append("\",");
		sb.append("\"type\":").append("\"").append(this.type).append("\",");
		sb.append("\"action\":").append("\"").append(this.action).append("\",");
		sb.append("\"status\":").append(this.status).append(",");
		sb.append("\"desc\":").append("\"").append(this.desc).append("\"");

		JSONObject bodyObject = JSONObject.fromObject(resultBody);
		String bodyJson = this.getBodyJson(bodyObject);
		if (StringUtils.isNotBlank(bodyJson)) {
			sb.append(",").append(bodyJson);
		}
		sb.append("}");
		return sb.toString();
	}

	private String getBodyJson(JSONObject bodyObject) {
		String json = bodyObject.toString();
		Pattern pattern = Pattern.compile("\\{(.*)\\}");

		Matcher mather = pattern.matcher(json);
		if (mather.find()) {
			String returnStr = mather.group(1);
			return returnStr;
		}
		return null;
	}

	public ResultBody getResultBody() {
		return resultBody;
	}

	public void setResultBody(ResultBody resultBody) {
		this.resultBody = resultBody;
	}

	public Result(String type,String id) {

		this.id = id;
		this.type = type;
		this.action = RESPONSE;
		this.status = SUCCESS_CODE;
		this.desc = SUCCESS_MSG;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	// public static void main(String[] args){
	// ResultHeader resultHeader = new ResultHeader("type");
	// JSONObject headerObject = JSONObject.fromObject(resultHeader);
	// String header = headerObject.toString();
	// System.out.println(header);
	// }

}
