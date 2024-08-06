package com.example.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.internship.entity.LoginForm;
import com.example.internship.service.CourseRegistService;

@Controller
public class CourseRegistController {
    @Autowired
	private LoginForm loginForm;
	@Autowired
	private CourseRegistService courseRegistService;

	// 未登録の授業を追加する
	@PostMapping("courseRegist-insert")
	public String addClass(@RequestParam("classId") String classId, Model model) {
		int userID = loginForm.getUserId();
		int classID = Integer.parseInt(classId);

		try {
			// 選択した授業IDを履修登録に登録
			courseRegistService.insertClass(userID, classID);
		} catch (Exception e) {
			System.err.println("データベース操作中にエラーが発生しました: " + e.getMessage());
		}

        return "redirect:/courseRegist";
	}
}
