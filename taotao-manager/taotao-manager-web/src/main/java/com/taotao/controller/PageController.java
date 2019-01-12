package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	/*
	 * 展示首页
	 */
	@RequestMapping(value="/")
	public String showindex() {
		return "index";
	}
	
	/*
	 * 展示后台页面
	 */
	@RequestMapping(value="/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
}
