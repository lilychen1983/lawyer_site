package com.law.news.introduce.dao;

import java.sql.SQLException;
import java.util.List;

import com.law.news.introduce.bean.NewsDetail;
import com.law.news.introduce.bean.NewsIntroduceForm;
import com.law.news.introduce.bean.NewsIntroducePo;


public interface INewsIntroDao {
	public boolean save(NewsIntroducePo registPo) throws Exception ;
	public boolean update(NewsIntroducePo registPo) throws Exception;
	public List<NewsIntroduceForm> get() throws SQLException;
	public NewsDetail getNewsDetailById(String id) throws SQLException;
}
