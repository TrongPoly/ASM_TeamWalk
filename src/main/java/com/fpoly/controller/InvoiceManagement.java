package com.fpoly.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.dao.HoaDonDAO;
import com.fpoly.entity.HoaDon;

@Controller
public class InvoiceManagement {
	@Autowired
	HoaDonDAO hoaDonDAO;

	@RequestMapping("/admin/paymentConfirm")
	public String paymentConfirm(@RequestParam("id") long id) {
		HoaDon hoaDon = hoaDonDAO.getById(id);
		hoaDon.setTrangThai(true);
		LocalDate ngayThanhToan = LocalDate.now();
		hoaDon.setNgayThanhToan(ngayThanhToan);
		hoaDonDAO.save(hoaDon);
		return "redirect:/admin";
	}
}
