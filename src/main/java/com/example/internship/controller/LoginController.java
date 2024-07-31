package com.example.internship.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.internship.entity.LoginForm;
import com.example.internship.entity.Student;
import com.example.internship.entity.Teacher;
import com.example.internship.service.StudentService;
import com.example.internship.service.TeacherService;

@Controller // このクラスがコントローラークラスだと指定
public class LoginController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private LoginForm loginForm;

	// ログイン認証
	@PostMapping("/menu")
	// login画面からフォーム送信されたとき、loginFormで値を取得
	public String checkLogin(@RequestParam("selectJob") String selectJob, @RequestParam("userId") int loginId, @RequestParam("password") String password, Model model, RedirectAttributes redirectAttributes) {
		// セッションに値を保存
		loginForm.setSelectJob(selectJob);
		loginForm.setUserId(loginId);
		loginForm.setPassword(password);
		
		
		// 入力チェック
		// if (bindingResult.hasErrors()) {
		// 	if (bindingResult.hasFieldErrors("userId")) {
		// 		// ユーザーIDにエラーがあればエラーメッセージセット
		// 		redirectAttributes.addFlashAttribute("idError", bindingResult.getFieldError("userId").getDefaultMessage());
		// 	}

		// 	if (bindingResult.hasFieldErrors("password")) {
		// 		// パスワードにエラーがあれば
		// 		redirectAttributes.addFlashAttribute("passError", bindingResult.getFieldError("password").getDefaultMessage());
		// 	}
		// 	return "redirect:/login";
		// }

		// 入力されたパスワードのハッシュ化
		String hashPass = "";
		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			sha256.update(password.getBytes());
			byte[] hashBytes = sha256.digest();
			hashPass = Base64.getEncoder().encodeToString(hashBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// 職業による分岐処理
		if (selectJob.equals("Teacher")) {
			// 教員
			// DBからデータ取得
			List<Teacher> loginUser = teacherService.loginUser(loginId);

			// ユーザーIDが存在するか
			if (loginUser.isEmpty()) {
				redirectAttributes.addFlashAttribute("idError", "ユーザーIDが存在しません。");

				return "redirect:/login";
			} else {
				// DBのカラム情報を取得
				String userName = loginUser.get(0).getTEACHER_NAME();
				String userPass = loginUser.get(0).getTEACHER_PASS();

				// パスワードが合致するか
				if (hashPass.equals(userPass)) {
					// セッションにログインユーザー名を保存
					loginForm.setName(userName);

					return "redirect:/menu";
				} else {
					// パスワードが違った場合はエラーメッセージをセットし、ログイン画面に戻る
					redirectAttributes.addFlashAttribute("passError", "パスワードが違います。");

					return "redirect:/login";
				}
			}
		} else {
			// 学生
			// DBからデータ取得
			List<Student> loginUser = studentService.loginUser(loginId);

			// ユーザーIDが存在するか
			if (loginUser.isEmpty()) {
				// ユーザーIDが存在しなければエラーメッセージをセットし、ログイン画面に戻る
				redirectAttributes.addFlashAttribute("idError", "ユーザーIDが存在しません。");
				
				return "redirect:/login";
			} else {
				// DBのカラム情報を取得
				String userName = loginUser.get(0).getSTUDENT_NAME();
				String userPass = loginUser.get(0).getSTUDENT_PASS();

				// パスワードが合致するか
				if (hashPass.equals(userPass)) {
					// セッションにログインユーザー名を保存
					loginForm.setName(userName);

					return "redirect:/menu";
				} else {
					// パスワードが違った場合はエラーメッセージをセットし、ログイン画面に戻る
					redirectAttributes.addFlashAttribute("passError", "パスワードが違います。");

					return "redirect:/login";
				}
			}
		}
	}
}
