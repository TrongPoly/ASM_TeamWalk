package com.fpoly.controller;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.dao.HoaDonChiTietDAO;
import com.fpoly.dao.HoaDonDAO;
import com.fpoly.dao.LoaiSanPhamDAO;
import com.fpoly.entity.ReportSanPham;

import lombok.experimental.var;

@Controller
public class ReportController {
	@Autowired
	HoaDonChiTietDAO hoaDonChiTietDAO;
	@Autowired
	HoaDonDAO hoaDonDAO;
	@Autowired
	LoaiSanPhamDAO loaiSanPhamDAO;

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
		model.addAttribute("msg", "Thống kê doanh thu tháng " + month + "/" + year);

		return "views/Admin/Report/doanhThu";

	}

	@RequestMapping("/admin/sanPham")
	public String sanPham(Model model) {
		var rp = hoaDonChiTietDAO.reportSanPham(1, 2023);
		var year = hoaDonDAO.getYearHoaDon();
		var loai = loaiSanPhamDAO.findAll();
		model.addAttribute("year", year);
		model.addAttribute("rp", rp);
		model.addAttribute("loai", loai);
		return "views/Admin/Report/SanPham";
	}

	@RequestMapping("/admin/resetSanPham")
	public String resetSanPham(Model model, @RequestParam("month") Integer month, @RequestParam("year") Integer year,
			@RequestParam("loai") Integer loaiHang) {
		List<ReportSanPham> rp;
		if (loaiHang != 0) {
			rp = hoaDonChiTietDAO.reportSanPhamTheoLoai(month, year, loaiHang);
		} else {
			rp = hoaDonChiTietDAO.reportSanPham(month, year);
		}
		model.addAttribute("msg", "Thống kê sản phẩm tháng " + month + "/" + year);
		model.addAttribute("rp", rp);
		var yearAttribute = hoaDonDAO.getYearHoaDon();
		model.addAttribute("year", yearAttribute);
		var loai = loaiSanPhamDAO.findAll();
		model.addAttribute("loai", loai);
		return "views/Admin/Report/SanPham";

	}

}
