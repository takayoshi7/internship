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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.internship.model.LoginForm;
import com.example.internship.model.Student;
import com.example.internship.model.Syain;
import com.example.internship.model.Teacher;
import com.example.internship.service.StudentService;
import com.example.internship.service.TeacherService;

@Controller // このクラスがコントローラークラスだと指定
public class LoginController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	LoginForm loginFormData;


	@GetMapping("/login")
	public String login() {
		return "login"; // Thymeleafを使用する場合、htmlファイルはtemplates直下に置く
	}

	// フォームのth:actionでmenuかつsubmitのnameがloginでpost送信したときの処理
	@RequestMapping(value = "menu", params = "login", method = RequestMethod.POST)
	// login画面からフォーム送信されたとき、loginFormで値を取得
	public String login(@Valid LoginForm loginForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		// 入力チェック
		if (bindingResult.hasErrors()) {
			if (bindingResult.hasFieldErrors("userId")) {
				// ユーザーIDにエラーがあればエラーメッセージセット
				redirectAttributes.addFlashAttribute("idError", bindingResult.getFieldError("userId").getDefaultMessage());
			}

			if (bindingResult.hasFieldErrors("password")) {
				// パスワードにエラーがあれば
				redirectAttributes.addFlashAttribute("passError", bindingResult.getFieldError("password").getDefaultMessage());
			}
			return "redirect:/login";
		}

		// 入力されたパスワードのハッシュ化
		String hashPass = "";
		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			sha256.update(loginForm.getPassword().getBytes());
			byte[] hashBytes = sha256.digest();
			hashPass = Base64.getEncoder().encodeToString(hashBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// 職業による分岐処理
		if (loginForm.getSelectJob().equals("Teacher")) {
			// DBからデータ取得
			List<Teacher> loginUser = teacherService.loginUser(loginForm.getUserId());

			if (loginUser.isEmpty()) {
				redirectAttributes.addFlashAttribute("idError", "ユーザーIDが存在しません。");
				return "redirect:/login";
			} else {
				// DBのカラム情報を取得
				int userId = loginUser.get(0).getTEACHER_ID(); 
				String userName = loginUser.get(0).getTEACHER_NAME();
				String userPass = loginUser.get(0).getTEACHER_PASS();

				// パスワードが合致するか
				if (hashPass.equals(userPass)) {
					// Modelオブジェクトのmodelに値を追加することで、menu画面で値を参照させられる
					model.addAttribute("userId", userId);
					model.addAttribute("userName", userName);
					model.addAttribute("selectJob", loginForm.getSelectJob());

					return "menu"; // menu.htmlを表示
				} else {
					// パスワードが違った場合
					redirectAttributes.addFlashAttribute("passError", "パスワードが違います。");
					return "redirect:/login";
				}
			}
		} else {
			// DBからデータ取得
			List<Student> loginUser = studentService.loginUser(loginForm.getUserId());

			if (loginUser.isEmpty()) {
				redirectAttributes.addFlashAttribute("idError", "ユーザーIDが存在しません。");
				return "redirect:/login";
			} else {
				// DBのカラム情報を取得
				int userId = loginUser.get(0).getSTUDENT_ID(); 
				String userName = loginUser.get(0).getSTUDENT_NAME();
				String userPass = loginUser.get(0).getSTUDENT_PASS();

				// パスワードが合致するか
				if (hashPass.equals(userPass)) {
					// Modelオブジェクトのmodelに値を追加することで、menu画面で値を参照させられる
					model.addAttribute("userId", userId);
					model.addAttribute("userName", userName);
					model.addAttribute("selectJob", loginForm.getSelectJob());


					loginFormData.setUserId(userId);
					model.addAttribute(loginFormData);
					System.out.println(model);


					return "menu"; // menu.htmlを表示
				} else {
					// パスワードが違った場合
					redirectAttributes.addFlashAttribute("passError", "パスワードが違います。");
					return "redirect:/login";
				}
			}
		}
	}

	// フォームのth:actionでmenuかつsubmitのnameがendでpost送信したときの処理
	@RequestMapping(value  = "menu", params = "end", method = RequestMethod.POST)
	public String end() {
		return "finished";
	}
}
