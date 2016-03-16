package com.law.news.introduce.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.law.commons.Result;
import com.law.commons.util.ResponseJsonUtils;
import com.law.news.introduce.service.INewsIntroduceService;

@Controller
@RequestMapping("/news")
public class NewsIntroduceController {

	@Resource(name = "newsIntroduceService")
	private INewsIntroduceService newIntroduceService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void getNewsIntroduceList(@RequestParam(value="valid")int valid, HttpServletResponse response) {

		Result result = newIntroduceService.getAllNewsIntroduce(valid);
		ResponseJsonUtils.responseJson(result.toString(), response);
	}
	
	@RequestMapping(value = "/detail",method = RequestMethod.GET)
	public void getNewsDetail(@RequestParam(value ="id") String id,HttpServletResponse response){
		Result result = newIntroduceService.getNewsDetailByid(id);
		ResponseJsonUtils.responseJson(result.toString(), response);
		
	}
}
