// package com.example.internship.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.example.internship.model.TimeTable;
// import com.example.internship.service.TimeTableService;

// @Controller
// public class MenuController {
// 	@Autowired
// 	private TimeTableService timeTableService;

// 	// // 履修登録画面へ
// 	// @GetMapping(value  = "menu", params = "courseRegist")
// 	// public String courseRegist(@RequestParam("hiddenStudentID") String studentID, Model model) {
// 	// 	int userID = Integer.parseInt(studentID);
		
// 	// 	// DBからデータ取得
// 	// 	List<TimeTable> notClass = timeTableService.getNotClass(userID);

// 	// 	model.addAttribute("notClass", notClass);
// 	// 	return "courseRegist";
// 	// }

// 	// 時間割表画面へ
// 	// @RequestMapping(value  = "menu", params = "timeTable", method = RequestMethod.POST)
// 	// public String timeTable(@RequestParam("hiddenUserName") String userName, @RequestParam("hiddenStudentID") String studentID, Model model) {
// 	// 	int userID = Integer.parseInt(studentID);
// 	// 	model.addAttribute("userName", userName);
// 	// 	model.addAttribute("userId", userID);
		
// 	// 	// DBからデータ取得
// 	// 	List<TimeTable> timeTable = timeTableService.getTimeTable(userID);

// 	// 	model.addAttribute("timeTable", timeTable);
// 	// 	return "timeTable";
// 	// }

// 	// @GetMapping(value  = "menu", params = "timeTable")
// 	// public String timeTable(@RequestParam("hiddenUserName") String userName, @RequestParam("hiddenStudentID") String studentID, Model model) {
// 	// 	int userID = Integer.parseInt(studentID);
// 	// 	model.addAttribute("userName", userName);
// 	// 	model.addAttribute("userId", userID);
		
// 	// 	// DBからデータ取得
// 	// 	//List<TimeTable> timeTable = timeTableService.getTimeTable(userID);

// 	// 	//model.addAttribute("timeTable", timeTable);
// 	// 	return "dispTimeTable";
// 	// }


// 	// 終了　ログイン画面へ
// 	@GetMapping("logout")
// 	public String logout() {
// 		return "login";
// 	}
// }
