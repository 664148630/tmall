package com.entor.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
public class ExceptionController {

	@RequestMapping("/test")
	public String test() {
		throw new RuntimeException();
//		return "index";
	}
	@ExceptionHandler
	public String exception(HttpServletRequest request,Exception e) {
		request.setAttribute("e", e);
		return "exception";
	}
	@RequestMapping("/test2")
	public String test2() {
		String s = null;
		System.out.println(s.length());
		return "index";
	}
}
