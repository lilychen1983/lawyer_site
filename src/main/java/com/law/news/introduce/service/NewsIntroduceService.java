package com.law.news.introduce.service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.law.commons.AbsService;
import com.law.commons.BaseForm;
import com.law.commons.Result;
import com.law.commons.util.PpsConfig;
import com.law.news.introduce.bean.NewsIntroduceForm;
import com.law.news.introduce.bean.NewsIntroduceList;
import com.law.news.introduce.dao.INewsIntroDao;

@Service("newsIntroduceService")
public class NewsIntroduceService extends AbsService implements INewsIntroduceService {
	private static final Logger logger = Logger.getLogger(NewsIntroduceService.class);
	private static final String REGIST_URL = PpsConfig.getString("registUrl");
	@Resource(name = "newsIntroDao")
	private INewsIntroDao newsIntroDao;


	@Override
	protected void init() {
		this.type = "m1_get_newsintro";
	}

	@Override
	public Result getAllNewsIntroduce() {
		Result result = new Result("500", "Internal ERROR");
		NewsIntroduceList newsIntroduceList = new NewsIntroduceList();
		try {
			List<NewsIntroduceForm>  newsIntroduce = this.newsIntroDao.get();
			newsIntroduceList.setNewsIntroList(newsIntroduce);
			result.setResultBody(newsIntroduceList);
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	protected void subValid(BaseForm obj, Result result) {
		// TODO Auto-generated method stub
		
	}

}
