package com.fpoly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.dao.HoaDonChiTietDAO;

import lombok.experimental.var;

@Controller
public class ReportController {
	@Autowired
	HoaDonChiTietDAO hoaDonChiTietDAO;

	@RequestMapping("/admin/doanhThu")
	public String doanhThu(Model model) {
		var rp = hoaDonChiTietDAO.reportDoanhThu(7, 2023);
		model.addAttribute("rp", rp);
		return "views/Admin/Report/doanhThu";
	}

	@RequestMapping("/admin/resetDoanhThu")
	public String resetDoanhThu(Model model, @RequestParam("month") Integer month, @RequestParam("year") Integer year) {
		System.out.println(month);
		var rp = hoaDonChiTietDAO.reportDoanhThu(month, year);
		model.addAttribute("rp", rp);
		return "views/Admin/Report/doanhThu";
	}

}
