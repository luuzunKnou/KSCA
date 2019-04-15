package com.luuzun.ksca.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
@RequestMapping("/introduce/*")
public class IntroduceController {
	private static final Logger logger = LoggerFactory.getLogger(IntroduceController.class);
	
	//홈페이지 소개 이동
	@RequestMapping(value="/introduce")
	public String introduceGET(HttpSession session){
		logger.info("Introduce Page..........");
		return "introduce/introduce";
	}
}
