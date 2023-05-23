package com.fpoly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/index")
	public String getIndex() {
		return "views/user/index";
	}

	@GetMapping("/login")
	public String formLogin() {
		return "views/user/login";
	}

	@PostMapping("/login")
	public String login() {
		// Xử lý
		return "views/user/index";
	}
}
