package com.law.commons;

import org.apache.commons.lang.StringUtils;

import com.law.commons.interf.IValidService;

public abstract class AbsService implements IValidService {
	public final static String REQUEST_ACTION = "request";
	protected String type = "";
	
	
	public AbsService(){
		this.init();
	}
	public Result  valid(BaseForm form) {
		
		if(form==null){
			Result  result = new Result(this.type,"000000");
			result.setStatus(Result.NECESSARY_FIELD_IS_NULL);
			result.setDesc("必要字段为空");
			return result;
		}
		Result  result = new Result(this.type,form.getId());
		if(StringUtils.isBlank(form.getId())){
			result.setStatus(Result.BLANK_CODE);
			result.setDesc("id字段为空");
			return result;
		}
		
		if(!REQUEST_ACTION.equals(form.getAction())){
			result.setStatus(Result.BLANK_CODE);
			result.setDesc("请求action的值应为request");
			return result;
		}
		
		 
		
		if(!this.type.equals(form.getType())){
			result.setStatus(Result .TYPE_UNEXPECTED_CODE);
			result.setDesc(Result .TYPE_UNEXPECTED_MSG);
			return result;
		}
		
		this.subValid(form,result);
		return result;
	}
	protected abstract void  subValid(BaseForm obj,Result result) ;
	protected abstract void init();
	
	
}
