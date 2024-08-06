package com.example.internship.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.internship.entity.LoginForm;
import com.example.internship.service.CourseRegistService;

@Controller
public class TimeTableController {
	@Autowired
	private LoginForm loginForm;
	@Autowired
	private CourseRegistService courseRegistService;

	// 時間割表から1レコード削除
	@PostMapping("timeTable-delete")
	public String delTimeTable(@RequestParam("classId") String classId, Model model, RedirectAttributes redirectAttributes) {
		int userID = loginForm.getUserId();
		int classID = Integer.parseInt(classId);

		try {
			// 選択した授業IDを履修登録から削除
			courseRegistService.deleteClass(userID, classID);
		} catch (Exception e) {
			System.err.println("データベース操作中にエラーが発生しました: " + e.getMessage());
		}

		return "redirect:/timeTable";
	}
}
