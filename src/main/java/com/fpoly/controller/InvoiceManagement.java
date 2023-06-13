package com.fpoly.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.dao.HoaDonDAO;
import com.fpoly.dao.KhachHangDAO;
import com.fpoly.entity.HoaDon;
import com.fpoly.entity.KhachHang;
import com.fpoly.entity.TaiKhoan;
import com.fpoly.services.CookieService;
import com.fpoly.services.MailerServiceImp;
import com.fpoly.services.SessionService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class InvoiceManagement {
	@Autowired
	HoaDonDAO hoaDonDAO;
	@Autowired
	InvoiceController invoiceController;
	@Autowired
	SessionService sessionService;
	@Autowired
	KhachHangDAO khachHangDAO;
	@Autowired
	MailerServiceImp mailerServiceImp;
	@Autowired
	CookieService cookieService;

	@RequestMapping("/admin/paymentConfirm")
	public String paymentConfirm(@RequestParam("id") long id) {
		HoaDon hoaDon = hoaDonDAO.getById(id);
		hoaDon.setTrangThai(true);
		LocalDate ngayThanhToan = LocalDate.now();
		hoaDon.setNgayThanhToan(ngayThanhToan);

		hoaDonDAO.save(hoaDon);
		return "redirect:/admin";
	}

	@RequestMapping("/huyDon")
	public String viewHuyDon(Model model, @RequestParam("trangThai") Boolean trangThai, @RequestParam("id") long id) {
		model.addAttribute("ghiLyDo", true);
		invoiceController.invoiceDetails(trangThai, id, model);
		cookieService.add("maHoaDon", String.valueOf(id), 5);
		return "views/user/invoiceDetails";
	}

	@RequestMapping("/doHuyDon")
	public String doHuyDon(@RequestParam("body") String body, Model model, HttpServletRequest request)
			throws Exception {
		TaiKhoan tKhoan = sessionService.get("user");
		KhachHang khachHang = khachHangDAO.getByEmail(tKhoan);
		String maHoaDon = cookieService.getValue("maHoaDon");
		mailerServiceImp.huyDon(body, khachHang.getTenKhachHang(), maHoaDon);
		String referer = request.getHeader("referer");
		// Chuyển hướng trang đến URL hiện tại để giữ nguyên các tham số
		return "redirect:" + referer;
	}
}
