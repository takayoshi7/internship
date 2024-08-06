package com.example.internship.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.internship.entity.LoginForm;
import com.example.internship.entity.TimeTable;
import com.example.internship.service.TimeTableService;
import com.mysql.cj.util.StringUtils;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	@Autowired
	private LoginForm loginForm;
	@Autowired
	private TimeTableService timeTableService;

	// ログイン画面表示
	@GetMapping("/login")
	public String dispLogin(Model model) {
		// 入力情報セット
		if (StringUtils.isNullOrEmpty(loginForm.getSelectJob())) {
			model.addAttribute("selectJob", "Student");
		} else {
			model.addAttribute("selectJob", loginForm.getSelectJob());
		}
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

	// 授業画面
	@GetMapping("/class")
	public String dispClass(Model model) {
		try {
			// DBから登録済みの授業データ取得
			List<TimeTable> getClassData = timeTableService.getClass(loginForm.getUserId());
		
			// 授業データセット
			model.addAttribute("classData", getClassData);

			// 時間割表データが一つ以上あれば
			if (getClassData.size() > 0) {
				// コンボボックス用リスト作成
				List<String> list = new ArrayList<String>();
				getClassData.forEach(record -> {
					list.add(record.getCLASS_ID().toString());
				});
				// リストに空欄を追加
				list.add("");
				// 昇順ソート
				Collections.sort(list);

				// コンボボックス用リストセット
				model.addAttribute("comboBox", list);
			}
		} catch (Exception e) {
			System.err.println("データベース操作中にエラーが発生しました: " + e.getMessage());
		}

		return "demo/class";
	}

	// 履修登録画面
	@GetMapping("/courseRegist")
	public String dispCourseRegist(Model model) {
		try {
			// DBから未登録の授業データ取得
			List<TimeTable> notClassData = timeTableService.getNotClass(loginForm.getUserId());

			// 時間割表データセット
			model.addAttribute("notClassData", notClassData);

			// 時間割表データが一つ以上あれば
			if (notClassData.size() > 0) {
				// コンボボックス用リスト作成
				List<Integer> list = new ArrayList<Integer>();
				notClassData.forEach(record -> {
					list.add(record.getCLASS_ID());
				});
				// 昇順ソート
				Collections.sort(list);

				// コンボボックス用リストセット
				model.addAttribute("comboBox", list);
			}
		} catch (Exception e) {
			System.err.println("データベース操作中にエラーが発生しました: " + e.getMessage());
		}

		return "demo/courseRegist";
	}

	// 時間割表画面
	@GetMapping("/timeTable")
	public String dispTimeTable(Model model) {
		try {
			// ユーザー名取得
			model.addAttribute("userName", loginForm.getName());

			// DBから時間割表データ取得
			List<TimeTable> timeTable = timeTableService.getTimeTable(loginForm.getUserId());

			// 時間割表データセット
			model.addAttribute("timeTable", timeTable);

			// 時間割表データが一つ以上あれば
			if (timeTable.size() > 0) {
				// コンボボックス用リスト作成
				List<Integer> list = new ArrayList<Integer>();
				timeTable.forEach(record -> {
					list.add(record.getCLASS_ID());
				});
				// 昇順ソート
				Collections.sort(list);

				// コンボボックス用リストセット
				model.addAttribute("comboBox", list);
			}
		} catch (Exception e) {
			System.err.println("データベース操作中にエラーが発生しました: " + e.getMessage());
		}

		return "demo/timeTable";
	}

	// 確認ダイアログ画面
	@PostMapping("/dialog")
	public String dialog(@RequestParam("classId") String classId,
						@RequestParam(value="className", required = false) String className,
						@RequestParam(value="fromClassTime", required = false) String fromClassTime,
						@RequestParam(value="toClassTime", required = false) String toClassTime,
						@RequestParam("from") String from,
						@RequestParam("actionMsg") String actionMsg,
						Model model) {
		// Mapping先作成
		String action = "";
		switch(actionMsg) {
			case "登録":
				action = from + "-" + "insert";
				break;
			case "更新":
				action = from + "-" + "update";
				break;
			case "削除":
				action = from + "-" + "delete";
				break;
		}

		model.addAttribute("classId", classId);
		model.addAttribute("className", className);
		model.addAttribute("fromClassTime", fromClassTime);
		model.addAttribute("toClassTime", toClassTime);
		model.addAttribute("from", from);
		model.addAttribute("action", action);
		model.addAttribute("actionMsg", actionMsg);

		return "demo/dialog";
	}

	// メニュー画面に戻る
	@GetMapping("back")
	public String backMenu() {
		return "redirect:/menu";
	}

	// 終了　ログイン画面へ
	@GetMapping("logout")
	public String logout() {
		return "redirect:/login";
	}

	// アプリ終了画面
	@GetMapping("end")
	public String dispEnd(HttpSession session) {
		// セッション削除
		session.invalidate();

		return "demo/end";
	}
}