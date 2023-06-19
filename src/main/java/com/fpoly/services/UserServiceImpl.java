package com.fpoly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fpoly.dao.LoaiSanPhamDAO;
import com.fpoly.dao.TaiKhoanDAO;
import com.fpoly.entity.TaiKhoan;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	TaiKhoanDAO tkDAO;
	@Autowired
	CookieImpl cookieImpl;
	@Autowired
	SessionService sessionService;
	@Autowired
	LoaiSanPhamDAO loaiSanPhamDAO;

	@Override
	public boolean checkLogin(String email, String password) {
		var tk = tkDAO.findById(email);
		if (tk.isPresent() && tk.get().getMatKhau().equals(password)) {
			return true;
		}
		return false;
	}

	@Override
	public void checkLogged(Model model) {
		TaiKhoan tKhoan = sessionService.get("user");
		var loaiSP = loaiSanPhamDAO.findAll();
		model.addAttribute("loaiSP", loaiSP);
		if (tKhoan != null) {
			boolean isUser = true;
			model.addAttribute("emailAccount", tKhoan.getEmail());
			model.addAttribute("isUser", isUser);
		}
	}

}
