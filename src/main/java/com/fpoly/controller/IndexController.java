package com.fpoly.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator;
import com.fpoly.dao.HoaDonDAO;
import com.fpoly.dao.KhachHangDAO;
import com.fpoly.dao.SanPhamDAO;
import com.fpoly.entity.KhachHang;
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
	@Autowired
	HoaDonDAO hoaDonDAO;
	@Autowired
	KhachHangDAO khachHangDAO;
	@Autowired
	SanPhamDAO sanPhamDAO;

	@GetMapping("/index")
	public String getindex(Model model, @RequestParam(name = "page", defaultValue = "0") Integer pageNo) {
		userServiceImpl.checkLogged(model);
		Pageable pageable = PageRequest.of(pageNo, 8);
		var sp = spDAO.findAll(pageable);
		model.addAttribute("sp", sp);
		return "views/user/index";
	}

	@GetMapping("/category")
	public String getIndexLaptop(Model model, @RequestParam(name = "page", defaultValue = "0") Integer pageNo,
			@RequestParam("loai") Long loai) {
		userServiceImpl.checkLogged(model);

		Pageable pageable = PageRequest.of(pageNo, 8);
		var sp = spDAO.findByCategory(loai, pageable);
		var numberOfRecords = spDAO.findByCategory(loai).size();
		System.out.println(numberOfRecords);
		var numberOfPages = (int) Math.ceil(numberOfRecords / 8.0);
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("sp", sp);
		model.addAttribute("loai", loai);
		return "views/user/ProductType";
	}

	@GetMapping("/sanpham/chitiet")
	public String sanPhamChiTiet(Model model, @RequestParam("id") long id) {
		userServiceImpl.checkLogged(model);

		SanPham sanpham = spDAO.getById(id);
		model.addAttribute("sp", sanpham);
		return "views/user/chiTietSP";
	}

	@RequestMapping("/admin")
	public String Admin(Model model, @RequestParam(name = "page", defaultValue = "0") Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo, 5, Sort.by("id").descending());
		var hd = hoaDonDAO.findAll(pageable);
		model.addAttribute("hd", hd);
		return "views/Admin/adminn";
	}

	@RequestMapping("/Profile")
	public String viewInfor(Model model) {

		userServiceImpl.checkLogged(model);
		TaiKhoan taiKhoan = sessionService.get("user");
		KhachHang khachHang = khachHangDAO.getByEmail(taiKhoan);
		model.addAttribute("kh", khachHang);
		Boolean view = true;
		// Kiểm tra xem thuộc tính 'msgProfile' có tồn tại trong RedirectAttributes hay
		// không

		model.addAttribute("view", view);
		return "views/user/UserInformation";
	}

	@RequestMapping("/Profile/edit")
	public String profileUpdate(@ModelAttribute("khachHang") KhachHang khachHang, Model model) {
		userServiceImpl.checkLogged(model);

		TaiKhoan taiKhoan = sessionService.get("user");
		khachHang = khachHangDAO.getByEmail(taiKhoan);
		model.addAttribute("kh1", khachHang);
		Boolean view = false;
		model.addAttribute("view", view);
		return "views/user/UserInformation";
	}

	@PostMapping("/Profile/save")
	public String profileSave(@RequestParam("id") long id, @ModelAttribute("khachHang") KhachHang khachHang,
			Model model) {
		KhachHang kh = khachHangDAO.getById(id);
		khachHang.setEmail(kh.getEmail());
		khachHang.setDiemTichLuy(kh.getDiemTichLuy());
		khachHang.setHangKhachHang(kh.getHangKhachHang());
		khachHang.setId(id);
		khachHangDAO.save(khachHang);

		return "redirect:/Profile";

	}

	@GetMapping("/errorPage")
	public String errorPage() {
		return "/views/user/ErrorPage";
	}

}
