package com.law.news.introduce.bean;

import java.util.List;

import com.law.commons.ResultBody;

public class NewsIntroduceList extends ResultBody{

	private List<NewsIntroduceForm> newsIntroList;

	public List<NewsIntroduceForm> getNewsIntroList() {
		return newsIntroList;
	}

	public void setNewsIntroList(List<NewsIntroduceForm> newsIntroList) {
		this.newsIntroList = newsIntroList;
	}
	
	
}
