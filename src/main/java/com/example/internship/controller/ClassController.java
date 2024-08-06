package com.example.internship.controller;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.internship.entity.LoginForm;
import com.example.internship.service.ClassService;

@Controller
public class ClassController {
    @Autowired
	private LoginForm loginForm;
	@Autowired
	private ClassService classService;

	// 授業から1レコード削除
	@PostMapping("class-delete")
	public String delTeacherClass(@RequestParam("classId") String classId, Model model, RedirectAttributes redirectAttributes) {
		// 入力チェック変数
		boolean result = true;

		// 授業ID入力チェック
		if (classId.isEmpty()) {
			redirectAttributes.addFlashAttribute("classidError", "授業IDが選択されておりません。");
			result = false;
		}
		
		// エラーが無ければ削除処理
		if (result) {
			int userID = loginForm.getUserId();
			int classID = Integer.parseInt(classId);

			try {
				// 選択した授業IDを授業から削除
				classService.deleteTeacherClass(userID, classID);
			} catch (Exception e) {
				System.err.println("データベース操作中にエラーが発生しました: " + e.getMessage());
			}
		}
		return "redirect:/class";
	}

	// 新しく授業を登録する
	@PostMapping("class-insert")
	public String addTeacherClass(@RequestParam("className") String className, @RequestParam("fromClassTime") String fromClassTime, @RequestParam("toClassTime") String toClassTime, Model model, RedirectAttributes redirectAttributes) {
		// 入力チェック変数
		boolean result = true;

		// 授業名入力チェック
		if (className.isEmpty() || className.isBlank()) {
			redirectAttributes.addFlashAttribute("classNameError", "授業名の入力が正しくありません。");
			result = false;
		}

		// 時間のみの文字列を、比較できるLocalTime形式に変換
		LocalTime  date1 = LocalTime .parse(fromClassTime);
		LocalTime  date2 = LocalTime .parse(toClassTime);

		// 授業時間入力チェック
		if (date1.isAfter(date2)) {
			redirectAttributes.addFlashAttribute("classTimeError", "開始時間が終了時間より未来になっています。");
			result = false;
		}

		// エラーが無ければ登録処理
		if (result) {
			int userID = loginForm.getUserId();
			String classTime = fromClassTime.replace(":", "") + "_" + toClassTime.replace(":", "");

			try {
				// 入力された内容で授業を登録
				classService.insertTeacherClass(userID, className, classTime);
			} catch (Exception e) {
				System.err.println("データベース操作中にエラーが発生しました: " + e.getMessage());
			}
		}

		return "redirect:/class";
	}

	// 登録済みの授業を更新する
	@PostMapping("class-update")
	public String upTeacherClass(@RequestParam("classId") String classId, @RequestParam("className") String className, @RequestParam("fromClassTime") String fromClassTime, @RequestParam("toClassTime") String toClassTime, Model model, RedirectAttributes redirectAttributes) {
		// 入力チェック変数
		boolean result = true;

		// 授業ID入力チェック
		if (classId.isEmpty()) {
			redirectAttributes.addFlashAttribute("classidError", "授業IDが選択されておりません。");
			result = false;
		}

		// 授業名入力チェック
		if (className.isBlank()) {
			redirectAttributes.addFlashAttribute("classNameError", "授業名の入力が正しくありません。");
			result = false;
		}

		// 時間のみの文字列を、比較できるLocalTime形式に変換
		LocalTime  date1 = LocalTime .parse(fromClassTime);
		LocalTime  date2 = LocalTime .parse(toClassTime);

		// 授業時間入力チェック
		if (date1.isAfter(date2)) {
			redirectAttributes.addFlashAttribute("classTimeError", "開始時間が終了時間より未来になっています。");
			result = false;
		}

		// エラーが無ければ更新処理
		if (result) {
			int classID = Integer.parseInt(classId);
			String classTime = fromClassTime.replace(":", "") + "_" + toClassTime.replace(":", "");

			try {
				// 選択した授業を更新する
				classService.updateTeacherClass(classID, className, classTime);
			} catch (Exception e) {
				System.err.println("データベース操作中にエラーが発生しました: " + e.getMessage());
			}
		}

		return "redirect:/class";
	}
}
