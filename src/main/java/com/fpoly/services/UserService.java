package com.fpoly.services;

import org.springframework.ui.Model;

public interface UserService {
	boolean checkLogin(String email, String password);

	void checkLogged(Model model);
}
