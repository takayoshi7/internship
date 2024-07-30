package com.example.internship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.internship.model.LoginForm;
import com.example.internship.model.TimeTable;
import com.example.internship.service.TimeTableService;

@Controller
public class MainController {
	@Autowired
	private TimeTableService timeTableService;
	@Autowired
	private LoginForm loginForm;

	// ログイン画面表示
	@GetMapping("/login")
	public String dispLogin(Model model) {
		// 入力情報セット
		model.addAttribute("userId", loginForm.getUserId());
		model.addAttribute("password", loginForm.getPassword());

		return "demo/login";
	}

	// メニュー画面表示
	@GetMapping("/menu")
	public String dispMenu(Model model) {
		// ユーザー情報セット
		model.addAttribute("userId", loginForm.getUserId());
		model.addAttribute("selectJob", loginForm.getSelectJob());
		model.addAttribute("userName", loginForm.getName());

		return "demo/menu";
	}





	// 履修登録画面
	@GetMapping("/courseRegist")
	public String dispCourseRegist(Model model) {
		// ユーザー名取得
		//model.addAttribute("userName", loginForm.getName());

		// DBから時間割表データ取得
		//List<TimeTable> timeTable = timeTableService.getTimeTable(loginForm.getUserId());

		// 時間割表データセット
		//model.addAttribute("timeTable", timeTable);

		return "demo/courseRegist";
	}

	// 時間割表画面
	@GetMapping("/timeTable")
	public String dispTimeTable(Model model) {
		// ユーザー名取得
		model.addAttribute("userName", loginForm.getName());

		// DBから時間割表データ取得
		List<TimeTable> timeTable = timeTableService.getTimeTable(loginForm.getUserId());

		// 時間割表データセット
		model.addAttribute("timeTable", timeTable);

		return "demo/timeTable";
	}





	// メニュー画面に戻る
	@GetMapping("back")
	public String backMenu() {
		return "redirect:/menu";
	}

	// アプリ終了画面
	@GetMapping("end")
	public String dispEnd() {
		return "demo/end";
	}

	// 	// 終了　ログイン画面へ
	@GetMapping("logout")
	public String logout() {
		// セッション削除


		return "demo/login";
	}

}