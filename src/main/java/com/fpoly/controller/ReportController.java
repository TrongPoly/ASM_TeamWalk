package com.fpoly.controller;

import java.math.BigDecimal;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.dao.HoaDonChiTietDAO;
import com.fpoly.dao.HoaDonDAO;

import lombok.experimental.var;

@Controller
public class ReportController {
	@Autowired
	HoaDonChiTietDAO hoaDonChiTietDAO;
	@Autowired
	HoaDonDAO hoaDonDAO;

	@RequestMapping("/admin/doanhThu")
	public String doanhThu(Model model) {
		var rp = hoaDonChiTietDAO.reportDoanhThu(7, 2023);
		var year = hoaDonDAO.getYearHoaDon();
		model.addAttribute("year", year);
		model.addAttribute("rp", rp);

		return "views/Admin/Report/doanhThu";
	}

	@RequestMapping("/admin/resetDoanhThu")
	public String resetDoanhThu(Model model, @RequestParam("month") Integer month, @RequestParam("year") Integer year) {
		System.out.println(month);
		var rp = hoaDonChiTietDAO.reportDoanhThu(month, year);
		model.addAttribute("rp", rp);
		var yearAttribute = hoaDonDAO.getYearHoaDon();
		model.addAttribute("year", yearAttribute);
		int total = 0;
		for (int i = 0; i < rp.size(); i++) {
			total += Integer.parseInt(rp.get(i).getTongTien().setScale(0).toString());
		}
		model.addAttribute("total", "Tổng: " + total);
		return "views/Admin/Report/doanhThu";

	}

}
