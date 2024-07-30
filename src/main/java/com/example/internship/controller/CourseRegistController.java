package com.example.internship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.internship.model.CourseRegist;
import com.example.internship.model.TimeTable;
import com.example.internship.repository.CourseRegistRepository;
import com.example.internship.service.TimeTableService;

@Controller
public class CourseRegistController {
	@Autowired
	private TimeTableService timeTableService;
	@Autowired
    private CourseRegistRepository courseRegistRepository;

	// 履修登録画面へ
	@GetMapping(value = "menu", params = "courseRegist")
	public String courseRegist(@RequestParam("hiddenStudentID") String studentID, Model model) {
		model.addAttribute("userId", studentID);
		// int userID = Integer.parseInt(studentID);
		
		// // DBからデータ取得
		// List<TimeTable> notClass = timeTableService.getNotClass(userID);

		// model.addAttribute("notClass", notClass);
		return "redirect:/dispCourse";
	}

	@GetMapping("/dispCourse")
	public String dispA(Model model) {
		int userID = 101;
		model.addAttribute("userId", userID);


		// DBからデータ取得
		List<TimeTable> notClass = timeTableService.getNotClass(userID);

		model.addAttribute("notClass", notClass);
		return "courseRegist";
	}
	



	// 未登録の授業を追加する
	@PostMapping(value  = "CourseRegist", params = "regist")
	public String addClass(@RequestParam("hiddenStudentID") String studentID, @RequestParam("addClassID") String classId, Model model) {
		int userID = Integer.parseInt(studentID);
		int classID = Integer.parseInt(classId);

		
		// timeTableService.deleteClass(userID, classID);

		CourseRegist addClass = new CourseRegist();
		addClass.setSTUDENT_ID(userID);
		addClass.setCLASS_ID(classID);
		courseRegistRepository.save(addClass);

		model.addAttribute("notClass", timeTableService.getNotClass(userID));
		return "courseRegist";
		// return "redirect:/dispCourse";
	}
	


	// メニュー画面へ戻る
	@RequestMapping(value  = "CourseRegist", params = "back", method = RequestMethod.POST)
	public String back() {
		return "menu";
	}
}
