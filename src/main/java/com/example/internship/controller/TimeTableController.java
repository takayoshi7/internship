package com.example.internship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.internship.model.TimeTable;
import com.example.internship.service.CourseRegistService;
import com.example.internship.service.TimeTableService;

@Controller
public class TimeTableController {
	@Autowired
	private TimeTableService timeTableService;
	@Autowired
	private CourseRegistService courseRegistService;

	// 時間割表から1レコード削除して再表示
//	@GetMapping(value  = "timeTable", params = "delete")
//	public String delTimeTable(@RequestParam("classId") String classId, @RequestParam("hiddenStudentID") String studentID, Model model) {
////		int userID = Integer.parseInt(studentID);
////		int classID = Integer.parseInt(classId);
//		
//		// 選択した授業IDを履修登録から削除
//		//courseRegistService.deleteClass(userID, classID);
//		
//    	// DBからデータ取得
//        //List<TimeTable> timeTable = timeTableService.getTimeTable(userID);
////
////        model.addAttribute("timeTable", timeTable);
//		//redirectAttributes.addAttribute("timeTable", timeTable);
////        model.addAttribute("userId", userID);
//		return "timeTable";
//	}
	
	
	@GetMapping(value  = "timeTable", params = "delete")
	public String delTimeTable(@RequestParam("classId") String classId, @RequestParam("hiddenStudentID") String studentID, Model model) {
		int userID = Integer.parseInt(studentID);
		int classID = Integer.parseInt(classId);
		
		// 選択した授業IDを履修登録から削除
		timeTableService.deleteClass(userID, classID);
		//courseRegistService.deleteClass(userID, classID);
		
    	// DBからデータ取得
		//List<TimeTable> timeTable = timeTableService.getTimeTable(userID);
//
//        model.addAttribute("timeTable", timeTable);
		//redirectAttributes.addAttribute("timeTable", timeTable);
//        model.addAttribute("userId", userID);
		return "redirect:/dispTimeTable";
		// return "timeTable";
	}

	// @GetMapping(value = "menu", params = "timeTable")
	// public String displayList(@RequestParam("hiddenUserName") String userName, @RequestParam("hiddenStudentID") String studentID, Model model) {
	// //public String displayList(Model model) {

	// 	int userID = Integer.parseInt(studentID);
	// 	model.addAttribute("userName", userName);
	// 	model.addAttribute("userId", userID);


	// 	List<TimeTable> timeTable = timeTableService.getTimeTable(userID);
	// 	//List<TimeTable> timeTable = timeTableService.getTimeTable(101);

	// 	model.addAttribute("timeTable", timeTable);
	// 	return "timeTable";
	// }
	

	@GetMapping(value = "menu", params = "timeTable")
	public String displayList(@RequestParam("hiddenUserName") String userName, @RequestParam("hiddenStudentID") String studentID, Model model, RedirectAttributes redirectAttributes) {
		int userID = Integer.parseInt(studentID);
		redirectAttributes.addFlashAttribute("userName", userName);
		redirectAttributes.addFlashAttribute("userId", userID);


		// List<TimeTable> timeTable = timeTableService.getTimeTable(userID);
		//List<TimeTable> timeTable = timeTableService.getTimeTable(101);

		// redirectAttributes.addFlashAttribute("timeTable", timeTable);
		return "redirect:/dispTimeTable";
	}

	
	@GetMapping(value = "dispTimeTable")
	public String displayList(Model model) {


		List<TimeTable> timeTable = timeTableService.getTimeTable(101);

		model.addAttribute("timeTable", timeTable);
		return "timeTable";
	}


	
	// メニュー画面へ戻る
	@GetMapping(value  = "timeTable", params = "back")
    public String back() {
        return "menu";
	}
}
