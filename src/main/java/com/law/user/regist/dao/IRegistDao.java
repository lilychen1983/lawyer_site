package com.law.user.regist.dao;

import com.law.user.regist.bean.AccountPo;


public interface IRegistDao {
	public boolean save(AccountPo registPo) throws Exception ;
	public boolean update(AccountPo registPo) throws Exception;
}
