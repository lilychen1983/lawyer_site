package com.law.news.introduce.service;

import com.law.commons.Result;
import com.law.commons.interf.IValidService;
import com.law.news.introduce.bean.NewsIntroduceForm;

public interface INewsIntroduceService extends IValidService {
	public Result getNewsDetailByid(String id);
	Result getAllNewsIntroduce(int valid);
	
	
}
