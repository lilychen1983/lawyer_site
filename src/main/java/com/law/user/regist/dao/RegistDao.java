package com.law.user.regist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.law.commons.util.DateUtil;
import com.law.user.regist.bean.AccountPo;

@Repository("registDao")
public class RegistDao implements IRegistDao {
	@Resource(name = "dataSource")
	protected DataSource dataSource;

	@Override
	public boolean save(AccountPo registPo) throws Exception {

		String sql = "INSERT INTO account( account,password,loginSession,create_time,update_time) VALUES( ?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			String account = registPo.getAccount();
			String password = registPo.getPassword();
			String loginSession = registPo.getUuid();
			String version = registPo.getVersion();
			String currentTime = DateUtil.getDefaultDate(new Date());

			ps.setString(1, account);
			ps.setString(2, password);
			ps.setString(3, loginSession);
			ps.setString(4, currentTime);
			ps.setString(5, currentTime);
			int i = ps.executeUpdate();
			if (i != 1) {
				throw new Exception();
			}
			saveUserInfo(conn, account,version);

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
	public boolean update(AccountPo registPo) throws Exception {
		String account = registPo.getAccount();
		String sql = "UPDATE  account 	SET	password = ? , 	loginSession = ? , update_time = ?		WHERE account = '"+account+"'";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			
			String password = registPo.getPassword();
			String loginSession = registPo.getUuid();
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

}
