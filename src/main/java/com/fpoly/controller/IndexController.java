package com.fpoly.controller;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator;
import com.fpoly.model.KhachHang;
import com.fpoly.model.TaiKhoan;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class IndexController {
	@Autowired
	HttpServletRequest request;

	@RequestMapping("/index")
	public String getIndex() {
		return "views/user/index";
	}

	@RequestMapping("/product/laptop")
	public String latop() {
		return "views/user/MayTinh";
	}

	@RequestMapping("/product/details")
	public String details() {
		return "views/user/ChiTietSP";
	}

	@GetMapping("/register")
	public String FormRegres(@ModelAttribute("taikhoan") TaiKhoan taiKhoan) {
		return "views/user/DangKy";
	}

	@PostMapping("/register")
	public String Regres(@Valid @ModelAttribute("taikhoan") TaiKhoan taiKhoan, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "views/user/DangKy";
		}
		if (!request.getParameter("password").equals(request.getParameter("confirm"))) {
			request.setAttribute("errConfirm", "*Xác nhận mật khẩu không khớp");
			request.setAttribute("password", taiKhoan.getPassword());
			return "views/user/DangKy";
		}
		return "views/user/index";
	}

	@GetMapping("/login")
	public String formLogin(@ModelAttribute("taikhoan") TaiKhoan taiKhoan) {
		return "views/user/login";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("taikhoan") TaiKhoan taiKhoan, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "views/user/login";
		}
		return "views/user/index";
	}

	@RequestMapping("/card/add")
	public String addCart() {
		return "views/user/cart";
	}
}
