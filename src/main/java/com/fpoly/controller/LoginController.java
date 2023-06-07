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

import com.fpoly.dao.TaiKhoanDAO;
import com.fpoly.entity.TaiKhoan;
import com.fpoly.services.CookieImpl;
import com.fpoly.services.CookieService;
import com.fpoly.services.UserService;

import jakarta.validation.Valid;

@Controller
public class LoginController {
	@Autowired
	UserService userService;
	@Autowired
	CookieImpl cookieImpl;

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
			cookieImpl.add("cuser", taiKhoan.getEmail(), 10);
			return "redirect:/index";
		}
		return "redirect:/login";
	}

	@RequestMapping("/logout")
	public String logout() {
		cookieImpl.remove("cuser");
		return "redirect:/index";
	}
}
