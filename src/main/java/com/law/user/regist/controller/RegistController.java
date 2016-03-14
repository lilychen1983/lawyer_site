package com.law.user.regist.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.law.commons.Result;
import com.law.commons.util.ResponseJsonUtils;
import com.law.user.regist.bean.RegistForm;
import com.law.user.regist.service.IRegistService;

@Controller
@RequestMapping("/user")
public class RegistController {

	@Resource(name = "registService")
	private IRegistService registService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void valacode( HttpServletResponse response) {

//		Result result = registService.regist(form);
		ResponseJsonUtils.responseJson("name", response);
	}
}
