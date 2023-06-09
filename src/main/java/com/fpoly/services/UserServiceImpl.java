package com.fpoly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fpoly.dao.TaiKhoanDAO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	TaiKhoanDAO tkDAO;
	@Autowired
	CookieImpl cookieImpl;

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
		if (cookieImpl.getValue("cuser") != null) {
			boolean isUser = true;
			model.addAttribute("emailAccount", cookieImpl.getValue("cuser"));
			model.addAttribute("isUser", isUser);
		}
	}

}
