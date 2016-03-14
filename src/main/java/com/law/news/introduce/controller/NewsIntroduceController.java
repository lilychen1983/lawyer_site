package com.law.news.introduce.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.law.commons.Result;
import com.law.commons.util.ResponseJsonUtils;
import com.law.news.introduce.service.INewsIntroduceService;

@Controller
@RequestMapping("/user")
public class NewsIntroduceController {

	@Resource(name = "newsIntroduceService")
	private INewsIntroduceService newIntroduceService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void valacode( HttpServletResponse response) {

		Result result = newIntroduceService.getAllNewsIntroduce();
		ResponseJsonUtils.responseJson(result.toString(), response);
	}
}
