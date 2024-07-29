package com.example.internship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CourseRegistController {

	// メニュー画面へ戻る
	@RequestMapping(value  = "CourseRegist", params = "back", method = RequestMethod.POST)
	public String back() {
		return "menu";
	}
}
