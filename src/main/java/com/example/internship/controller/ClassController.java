package com.example.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.internship.entity.LoginForm;
import com.example.internship.service.ClassService;

@Controller
public class ClassController {
    @Autowired
	private LoginForm loginForm;
	@Autowired
	private ClassService classService;

	// 授業から1レコード削除
	@PostMapping(value = "teacherClass", params = "teacherDelete")
	public String delTeacherClass(@RequestParam("classId") String classId, Model model) {
		int userID = loginForm.getUserId();
		int classID = Integer.parseInt(classId);

		// 選択した授業IDを授業から削除
		classService.deleteTeacherClass(userID, classID);

		return "redirect:/class";
	}

	// 新しく授業を登録する
	@PostMapping(value = "teacherClass", params = "teacherRegist")
	public String addTeacherClass(@RequestParam("className") String className, @RequestParam("classTime") String classTime, Model model) {
		int userID = loginForm.getUserId();

		try {
			// 入力された内容で授業を登録
			classService.insertTeacherClass(userID, className, classTime);
		} catch (DataIntegrityViolationException e) {
			System.out.println("例外エラー");
		}

		return "redirect:/class";
	}

	// 登録済みの授業を更新する
	@PostMapping(value = "teacherClass", params = "teacherUpdate")
	public String upTeacherClass(@RequestParam("classId") String classId, @RequestParam("className") String className, @RequestParam("classTime") String classTime, Model model) {
		int userID = loginForm.getUserId();
		int classID = Integer.parseInt(classId);

		// 選択した授業を更新する
		classService.updateTeacherClass(classID, userID, className, classTime);

		return "redirect:/class";
	}
}
