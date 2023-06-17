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
import com.fpoly.services.MailerServiceImp;

import jakarta.validation.Valid;
import lombok.experimental.var;

@Controller
public class RegisterController {
	@Autowired
	TaiKhoanDAO taiKhoanDAO;
	@Autowired
	KhachHangDAO khachHangDAO;
	@Autowired
	MailerServiceImp mailerServiceImp;

	@RequestMapping("/register")
	public String register(@ModelAttribute("taikhoan") TaiKhoan taiKhoan) {
		return "/views/user/DangKy";
	}

	@PostMapping("/register")
	public String doRegister(@Valid @ModelAttribute("taikhoan") TaiKhoan taiKhoan, BindingResult result, Model model,
			@RequestParam("comfirm") String cf) throws Exception {
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
		taiKhoan.setTrangThai(true);
		taiKhoanDAO.save(taiKhoan);
		KhachHang kh = new KhachHang();
		kh.setDiemTichLuy(0);
		kh.setEmail(taiKhoan);
		khachHangDAO.save(kh);
		model.addAttribute("messageRegister", "Đăng ký thành công");
		mailerServiceImp.SendSuccessRegister(taiKhoan.getEmail());
		return "views/user/login";
	}
}
