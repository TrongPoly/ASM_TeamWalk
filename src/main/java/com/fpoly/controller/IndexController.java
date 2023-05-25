package com.fpoly.controller;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.model.TaiKhoan;

import jakarta.validation.Valid;

@Controller
public class IndexController {
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

	@RequestMapping("/regresion")
	public String Regres() {
		return "views/user/DangKy";
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
}
