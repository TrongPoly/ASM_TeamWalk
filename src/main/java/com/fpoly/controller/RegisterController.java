package com.fpoly.controller;

import java.security.PublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.dao.KhachHangDAO;
import com.fpoly.dao.TaiKhoanDAO;
import com.fpoly.entity.KhachHang;
import com.fpoly.entity.TaiKhoan;

import jakarta.validation.Valid;
import lombok.experimental.var;

@Controller
public class RegisterController {
	@Autowired
	TaiKhoanDAO taiKhoanDAO;
	@Autowired
	KhachHangDAO khachHangDAO;

	@RequestMapping("/register")
	public String register(@ModelAttribute("taikhoan") TaiKhoan taiKhoan) {
		return "/views/user/DangKy";
	}

	@PostMapping("/register")
	public String doRegister(@Valid @ModelAttribute("taikhoan") TaiKhoan taiKhoan, BindingResult result, Model model,
			@RequestParam("comfirm") String cf) {
		if (result.hasErrors()) {
			return "views/user/DangKy";
		}
		if (!taiKhoan.getMatKhau().equals(cf)) {
			model.addAttribute("errConfirm", "*Xác nhận mật khẩu không khớp");
			return "views/user/DangKy";
		}
		var tk = taiKhoanDAO.findById(taiKhoan.getEmail());
		if (tk.isPresent()) {
			model.addAttribute("errConfirm", "*Địa chỉ Email đã tồn tại");
			return "views/user/DangKy";
		}
		taiKhoan.setPhanQuyen(false);
		taiKhoanDAO.save(taiKhoan);
		System.out.println(taiKhoan.getEmail());
		KhachHang kh = new KhachHang();
		kh.setEmail(taiKhoan);
		System.out.println("Lần 2" + kh.getEmail());
		khachHangDAO.save(kh);
		return "redirect:/login";
	}
}
