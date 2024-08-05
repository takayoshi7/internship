package com.example.internship.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String checkLogin(@RequestParam("selectJob") String selectJob, @RequestParam("userId") String loginId, @RequestParam("password") String password, Model model, RedirectAttributes redirectAttributes) {
		// 入力チェックの検証結果を格納する変数
		boolean result = true;
		// 入力チェック
		if (true) {
			if (loginId.isBlank()) {
				// ユーザーIDが空文字、もしくは空白であればエラーメッセージセット
				redirectAttributes.addFlashAttribute("idError", "ユーザーIDが入力されていません。");
				result =false;
			} else {
				//一文字ずつ先頭から確認する。for文は文字数分繰り返す
				for(int i = 0; i < loginId.length(); i++) {

					//i文字めの文字についてCharacter.isDigitメソッドで判定する
					if(Character.isDigit(loginId.charAt(i))) {
						//数字の場合は次の文字の判定へ
						continue;
					}else {
						//数字でない場合はエラーメッセージセットをセットし、検証結果をfalseに上書きする
						redirectAttributes.addFlashAttribute("idError", "ユーザーIDは数字のみ入力してください。");
						result =false;
						break;
					}
				}
			}

			if (password.isBlank()) {
				// パスワードが空文字、もしくは空白であればエラーメッセージセット
				redirectAttributes.addFlashAttribute("passError", "パスワードが入力されていません。");
				result =false;
			}

			if (!result) {
				return "redirect:/login";
			}
		}

		// ユーザーIDは全角数字を半角数字に変換
		StringBuilder sb = new StringBuilder(loginId);
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (c >= 0xFF10 && c <= 0xFF19) { // 全角数字の範囲
				sb.setCharAt(i, (char) (c - 0xFEE0)); // 半角数字に変換
			}
		}

		// ユーザーIDを数値に変換
		int userId = Integer.parseInt(sb.toString());

		// セッションに値を保存
		loginForm.setSelectJob(selectJob);
		loginForm.setUserId(userId);
		loginForm.setPassword(password);

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

		// DBとの照合チェックの検証結果を格納する変数
		boolean dbResult = true;

		// 職業による分岐処理
		if (selectJob.equals("Teacher")) {
			// 教員
			// DBからデータ取得
			List<Teacher> loginUser = teacherService.loginUser(userId);

			// ユーザーIDが存在するか
			if (loginUser.isEmpty()) {
				redirectAttributes.addFlashAttribute("idError", "ユーザーIDが存在しません。");
				dbResult =false;
			} else {
				// DBのカラム情報を取得
				String userName = loginUser.get(0).getTEACHER_NAME();
				String userPass = loginUser.get(0).getTEACHER_PASS();

				// パスワードが合致するか
				if (hashPass.equals(userPass)) {
					// セッションにログインユーザー名を保存
					loginForm.setName(userName);
				} else {
					// パスワードが違った場合はエラーメッセージをセットし、ログイン画面に戻る
					redirectAttributes.addFlashAttribute("passError", "パスワードが違います。");
					dbResult =false;
				}
			}
		} else {
			// 学生
			// DBからデータ取得
			List<Student> loginUser = studentService.loginUser(userId);

			// ユーザーIDが存在するか
			if (loginUser.isEmpty()) {
				// ユーザーIDが存在しなければエラーメッセージをセットし、ログイン画面に戻る
				redirectAttributes.addFlashAttribute("idError", "ユーザーIDが存在しません。");
				dbResult =false;
			} else {
				// DBのカラム情報を取得
				String userName = loginUser.get(0).getSTUDENT_NAME();
				String userPass = loginUser.get(0).getSTUDENT_PASS();

				// パスワードが合致するか
				if (hashPass.equals(userPass)) {
					// セッションにログインユーザー名を保存
					loginForm.setName(userName);
				} else {
					// パスワードが違った場合はエラーメッセージをセットし、ログイン画面に戻る
					redirectAttributes.addFlashAttribute("passError", "パスワードが違います。");
					dbResult =false;
				}
			}
		}

		if (dbResult) {
			// エラーが無ければメニュー画面へ
			return "redirect:/menu";
		} else {
			// エラーがあればログイン画面へリダイレクト
			return "redirect:/login";
		}
	}
}
