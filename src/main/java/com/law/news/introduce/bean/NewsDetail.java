package com.law.news.introduce.bean;

import com.law.commons.ResultBody;

public class NewsDetail extends ResultBody{
	private String id;
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
