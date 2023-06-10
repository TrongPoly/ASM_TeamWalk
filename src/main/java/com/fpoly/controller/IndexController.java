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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.experimental.var;

@Controller
public class IndexController {
	@Autowired
	HttpServletRequest request;

	@Autowired
	CookieImpl cookieImpl;
	@Autowired
	SanPhamDAO spDAO;

	@GetMapping("/index")
	public String getindex(Model model, @RequestParam(name = "page", defaultValue = "0") Integer pageNo) {
		if (cookieImpl.getValue("cuser") != null) {
			boolean isUser = true;
			model.addAttribute("emailAccount", cookieImpl.getValue("cuser"));
			model.addAttribute("isUser", isUser);
		}
		System.out.println(cookieImpl.getValue("cuser"));
		Pageable pageable = PageRequest.of(pageNo, 8);
		var sp = spDAO.findAll(pageable);
		model.addAttribute("sp", sp);
		return "views/user/index";
	}

	@GetMapping("/sanpham/chitiet")
	public String sanPhamChiTiet(Model model, @RequestParam("id") long id) {
		if (cookieImpl.getValue("cuser") != null) {
			boolean isUser = true;
			model.addAttribute("emailAccount", cookieImpl.getValue("cuser"));
			model.addAttribute("isUser", isUser);
		}
		SanPham sanpham = spDAO.getById(id);
		model.addAttribute("sp", sanpham);
		return "views/user/chiTietSP";
	}

//	@RequestMapping("/index")
//	public String getIndex() {
//		return "views/user/index";
//	}
//
//	@RequestMapping("/product/laptop")
//	public String latop() {
//		return "views/user/MayTinh";
//	}
//
//	@RequestMapping("/product/details")
//	public String details() {
//		return "views/user/ChiTietSP";
//	}
//
	@RequestMapping("/admin")
	public String Admin() {
		return "views/Admin/adminn";
	}

//	@RequestMapping("/customered")
//	public String Customer() {
//		return "views/Admin/customered";
//	}
//
//	@RequestMapping("/customerRanked")
//	public String CustomerRank() {
//		return "views/Admin/customerRanked";
//	}
//
//	@RequestMapping("/customerTabled")
//	public String CustomerTable() {
//		return "views/Admin/customerTabled";
//	}
//
//	@RequestMapping("/productadd")
//	public String productadd() {
//		return "views/Admin/productadd";
//	}
//
//	@RequestMapping("/productTabled")
//	public String ProductTable() {
//		return "views/Admin/productTabled";
//	}
//
//	@RequestMapping("/customerRankTabled")
//	public String customerProductTable() {
//		return "views/Admin/customerRankTabled";
//	}
//
//	@GetMapping("/register")
//	public String FormRegres(@ModelAttribute("taikhoan") TaiKhoan taiKhoan) {
//		return "views/user/DangKy";
//	}
//
//	@PostMapping("/register")
//	public String Regres(@Valid @ModelAttribute("taikhoan") TaiKhoan taiKhoan, BindingResult bindingResult) {
//		if (bindingResult.hasErrors()) {
//			return "views/user/DangKy";
//		}
//		if (!request.getParameter("password").equals(request.getParameter("confirm"))) {
//			request.setAttribute("errConfirm", "*Xác nhận mật khẩu không khớp");
//			request.setAttribute("password", taiKhoan.getMatKhau());
//			return "views/user/DangKy";
//		}
//		return "views/user/index";
//	}
//
//	@GetMapping("/login")
//	public String formLogin(@ModelAttribute("taikhoan") TaiKhoan taiKhoan) {
//		return "views/user/login";
//	}
//
//	@PostMapping("/login")
//	public String login(@Valid @ModelAttribute("taikhoan") TaiKhoan taiKhoan, BindingResult bindingResult) {
//		if (bindingResult.hasErrors()) {
//			return "views/user/login";
//		}
//		return "views/user/index";
//	}
//
//	@RequestMapping("/card/add")
//	public String addCart() {
//		return "views/user/cart";
//	}
}
