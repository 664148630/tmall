package com.entor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@RequestMapping("/hello")
	public String hello() {
		String s = null;
		System.out.println(s.length());
		return "index";
	}
}
