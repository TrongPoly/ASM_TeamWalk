package com.fpoly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.model.User;

import jakarta.validation.Valid;

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

	@PostMapping("/login/submit")
	public String submit(@Valid @ModelAttribute("User") User user,BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "views/user/login";
		}
		return "views/user/index";
	}
	
}

