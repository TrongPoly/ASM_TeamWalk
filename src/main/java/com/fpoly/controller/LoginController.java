package com.fpoly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fpoly.dao.TaiKhoanDAO;
import com.fpoly.entity.TaiKhoan;
import com.fpoly.services.CookieImpl;
import com.fpoly.services.CookieService;
import com.fpoly.services.MailerServiceImp;
import com.fpoly.services.SessionService;
import com.fpoly.services.UserService;

import jakarta.validation.Valid;

@Controller
public class LoginController {
	@Autowired
	UserService userService;
	@Autowired
	CookieImpl cookieImpl;
	@Autowired
	SessionService sessionService;
	@Autowired
	TaiKhoanDAO taiKhoanDAO;

	@Autowired
	MailerServiceImp mailerServiceImp;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String formLogin(@ModelAttribute("taikhoan") TaiKhoan taiKhoan, Model model) {
		return "views/user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("taikhoan") TaiKhoan taiKhoan, BindingResult bdResult, Model model) {
		if (bdResult.hasErrors()) {
			return "views/user/login";
		}
		if (userService.checkLogin(taiKhoan.getEmail(), taiKhoan.getMatKhau())) {
			TaiKhoan tk = taiKhoanDAO.getById(taiKhoan.getEmail());
			if (tk.getTrangThai() == false) {
				model.addAttribute("msg", "Tài khoản đã bị chặn");
				return "views/user/login";
			}
			sessionService.set("user", tk);
			cookieImpl.add("cuser", taiKhoan.getEmail(), 10);
			if (tk.getPhanQuyen()) {
				return "redirect:/admin";
			} else
				return "redirect:/index";

		}
		model.addAttribute("msg", "Sai email hoặc mật khẩu");
		return "views/user/login";
	}

	@RequestMapping("/logout")
	public String logout() {
		cookieImpl.remove("cuser");
		sessionService.remove("user");
		return "redirect:/index";
	}

	@RequestMapping("/account/changePassword")
	public String chgPassword(@ModelAttribute("taikhoan") TaiKhoan taiKhoan, Model model) {
		userService.checkLogged(model);
		taiKhoan = taiKhoanDAO.getById(sessionService.get("user").getEmail());
		model.addAttribute("taikhoan", taiKhoan);
		return "/views/user/ChangePassword";
	}

	@PostMapping("/account/DoChangePassword")
	public String doChgPassword(@ModelAttribute("taikhoan") TaiKhoan taiKhoan, @RequestParam("matKhauCu") String mkCu,
			@RequestParam("confirm") String cf, Model model) {
		userService.checkLogged(model);
		TaiKhoan tk = sessionService.get("user");

		if (mkCu.isBlank() || taiKhoan.getMatKhau().isBlank() || cf.isBlank()) {
			model.addAttribute("err", "Không được để trống thông tin");
			return "/views/user/ChangePassword";
		}
		if (!mkCu.equals(tk.getMatKhau())) {
			model.addAttribute("err", "Mật khẩu hiện tại không đúng");
			return "/views/user/ChangePassword";
		} else if (!taiKhoan.getMatKhau().equals(cf)) {
			model.addAttribute("err", "Xác nhận mật khẩu không đúng");
			return "/views/user/ChangePassword";
		}
		taiKhoan.setEmail(tk.getEmail());
		taiKhoan.setPhanQuyen(tk.getPhanQuyen());
		taiKhoanDAO.save(taiKhoan);
		model.addAttribute("taikhoan", taiKhoan);
		model.addAttribute("err", "Đổi mật khẩu thành công");

		return "/views/user/ChangePassword";
	}

	@RequestMapping("/forgotPassword")
	public String forgotPw(Model model) {
		model.addAttribute("sendCode", false);
		return "views/user/ForgotPassword";
	}

	@RequestMapping("/sendCodeForgotPw")
	public String sendCode(Model model, @RequestParam("email") String email) throws Exception {
		if (email.isBlank()) {
			model.addAttribute("msg", "Không được để trống Email");
			return "/views/user/ForgotPassword";
		}
		TaiKhoan taiKhoan = taiKhoanDAO.findByEmail(email);

		if (taiKhoan == null) {
			model.addAttribute("msg", "Địa chỉ Email không đúng");
			return "/views/user/ForgotPassword";
		} else {
			int max = 9999;
			int min = 1000;
			int range = max - min + 1;
			int rand = (int) (Math.random() * range) + min;
			cookieImpl.add("email", email, 30);
			cookieImpl.add("code", String.valueOf(rand), 5);
//			mailerServiceImp.SendCodeFogotPw(email, rand);
			System.out.println(rand);
			model.addAttribute("sendCode", true);
			model.addAttribute("msg", "Đã gửi mã OPT đến email của bạn");
		}
		return "/views/user/ForgotPassword";

	}

	@PostMapping("/confirmCode")
	public String confirmCode(@RequestParam("code") String otp, Model model) throws Exception {
		String email = cookieImpl.getValue("email");
		String code = cookieImpl.getValue("code");
		System.out.println(email);
		System.out.println(code);
		if (otp.isBlank()) {
			model.addAttribute("msg", "Vui lòng nhập mã");
			model.addAttribute("sendCode", true);
			return "/views/user/ForgotPassword";
		}
		if (otp.equals(code)) {
			return "/views/user/CreateNewPw";
		}
		model.addAttribute("msg", "Mã không đúng, vui lòng nhập lại");
		model.addAttribute("sendCode", true);
		return "/views/user/ForgotPassword";

	}

	@PostMapping("/CreateNewPw")
	public String CreateNewPw(@RequestParam("matKhau") String mk, @RequestParam("confirm") String cf, Model model) {
		String email = cookieImpl.getValue("email");
		if (mk.isBlank() || cf.isBlank()) {
			model.addAttribute("err", "Không được để trống");
			return "/views/user/CreateNewPw";

		}
		if (mk.equals(cf)) {
			TaiKhoan tk = taiKhoanDAO.findByEmail(email);
			tk.setMatKhau(mk);
			taiKhoanDAO.save(tk);
			model.addAttribute("success", true);
			model.addAttribute("msg", "Mật khẩu đã được thay đổi");
			return "forward:/login";
		}
		model.addAttribute("err", "Xác nhận mật khẩu không đúng");
		return "/views/user/CreateNewPw";

	}
}
