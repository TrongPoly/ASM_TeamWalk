package com.fpoly.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator;
import com.fpoly.dao.SanPhamDAO;
import com.fpoly.entity.SanPham;
import com.fpoly.entity.TaiKhoan;
import com.fpoly.services.CookieImpl;
import com.fpoly.services.SessionService;
import com.fpoly.services.UserService;
import com.fpoly.services.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.experimental.var;

@Controller
public class IndexController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	SessionService sessionService;
	@Autowired
	CookieImpl cookieImpl;
	@Autowired
	SanPhamDAO spDAO;
	@Autowired
	UserServiceImpl userServiceImpl;

	@GetMapping("/index")
	public String getindex(Model model, @RequestParam(name = "page", defaultValue = "0") Integer pageNo) {
		userServiceImpl.checkLogged(model);
		Pageable pageable = PageRequest.of(pageNo, 8);
		var sp = spDAO.findAll(pageable);
		model.addAttribute("sp", sp);
		return "views/user/index";
	}

	@GetMapping("/sanpham/chitiet")
	public String sanPhamChiTiet(Model model, @RequestParam("id") long id) {
		userServiceImpl.checkLogged(model);

		SanPham sanpham = spDAO.getById(id);
		model.addAttribute("sp", sanpham);
		return "views/user/chiTietSP";
	}


}
