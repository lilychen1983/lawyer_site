package com.law.news.introduce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.law.commons.util.DateUtil;
import com.law.news.introduce.bean.NewsIntroduceForm;
import com.law.news.introduce.bean.NewsIntroducePo;

@Repository("newsIntroDao")
public class NewsIntroDao implements INewsIntroDao {
	@Resource(name = "dataSource")
	protected DataSource dataSource;

	@Override
	public boolean save(NewsIntroducePo nesIntroPo) throws Exception {

		String sql = "INSERT INTO account( account,password,loginSession,create_time,update_time) VALUES( ?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			String currentTime = DateUtil.getDefaultDate(new Date());

//			ps.setString(1, account);
//			ps.setString(2, password);
//			ps.setString(3, loginSession);
			ps.setString(4, currentTime);
			ps.setString(5, currentTime);
			int i = ps.executeUpdate();
			if (i != 1) {
				throw new Exception();
			}
//			saveUserInfo(conn, account,version);

			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			throw e;
		} finally {
		 
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return true;

	}

	private void saveUserInfo(Connection conn, String account,String version) throws Exception {
		PreparedStatement ps = null;
		String sql = "insert into account_info(account,version,create_time,update_time) values(?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			String createTime = DateUtil.getDefaultDate(new Date());
			String updateTime = createTime;
			ps.setString(1, account);
			ps.setString(2, version);
			ps.setString(3, createTime);
			ps.setString(4, updateTime);
			int i = ps.executeUpdate();
			if (i != 1) {
				throw new Exception("insert into account_info happend error:");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
		 
			if (ps != null) {
				ps.close();
			}

		}
	}
	
	
	@Override
	public boolean update(NewsIntroducePo registPo) throws Exception {
		String account = registPo.getTitle();
		String sql = "UPDATE  account 	SET	password = ? , 	loginSession = ? , update_time = ?		WHERE account = '"+account+"'";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			
			String password = registPo.getTitle();
			String loginSession = registPo.getImage();
			String currentTime = DateUtil.getDefaultDate(new Date());

			ps.setString(1, password);
			ps.setString(2, loginSession);
			ps.setString(3, currentTime);
			int i = ps.executeUpdate();
			if (i != 1) {
				throw new Exception();
			}

			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			throw e;
		} finally {
		 
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return true;

	}

	@Override
	public List<NewsIntroduceForm> get() throws SQLException {
		String sql = "select id,title,image,introduce,valid,create_time,update_time from news_intro";
		Connection conn = null;
		PreparedStatement ps = null;

		List<NewsIntroduceForm> newsIntroList = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			
			ResultSet rs = null;
			
			rs = ps.executeQuery(sql);
			newsIntroList = new ArrayList<NewsIntroduceForm>();
			
			while (rs.next()) {
				NewsIntroduceForm newsIntro  = new NewsIntroduceForm();
				newsIntro.setId(rs.getString(1));
				newsIntro.setTitle(rs.getString(2));
				newsIntro.setImage(rs.getString(3));
				newsIntro.setIntroduce(rs.getString(4));
				newsIntro.setValid(rs.getBoolean(5));
				newsIntro.setCreateTime(rs.getString(6));
				newsIntro.setUpdateTime(rs.getString(7));
				
				newsIntroList.add(newsIntro);
			}
			
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			throw e;
		} finally {
		 
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return newsIntroList;
	}

}
