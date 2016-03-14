package com.law.commons.util;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/**
 * 
 * @author l
 * @email weilong@chuchuchina.com
 * @date 2014-12-15
 */
public class ResponseJsonUtils {
	
	public static void responseJson(String json, HttpServletResponse response){
		
	    try{
	    	response.setContentType("application/json;charset=UTF-8");
	    	response.setHeader("Pragma", "No-cache");   
	    	response.setHeader("Cache-Control", "no-cache");  
	    	response.setDateHeader("Expires", 0);  
	    	response.flushBuffer();
	    	response.setCharacterEncoding("UTF-8");
	    	IOUtils.write(json, response.getOutputStream(), "UTF-8");
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

}
