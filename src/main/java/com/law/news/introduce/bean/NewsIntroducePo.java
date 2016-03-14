package com.law.news.introduce.bean;

import com.law.commons.po.PO;

public class NewsIntroducePo extends PO {
	public static final String KEY = "ACCOUNT_";

	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String IMAGE = "image";
	public static final String INTRODUCE = "introduce";
	public static final String VALID = "valid";
	public static final String CREATE_TIME = "create_time";
	public static final String UPDATE_TIME = "update_time";
	
	private String id;
	private String title;
	private String image;
	private boolean valid;
	private String create_time;
	private String update_time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	
	

}
