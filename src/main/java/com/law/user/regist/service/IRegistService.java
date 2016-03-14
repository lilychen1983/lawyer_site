package com.law.user.regist.service;

import com.law.commons.Result;
import com.law.commons.interf.IValidService;
import com.law.user.regist.bean.RegistForm;

public interface IRegistService extends IValidService {
	public Result  regist(RegistForm form) ;
	
	
	
}
