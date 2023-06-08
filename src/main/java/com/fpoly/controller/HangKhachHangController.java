package com.fpoly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.dao.HangKhachHangDAO;
import com.fpoly.entity.HangKhachHang;


@Controller
public class HangKhachHangController {
	
	@Autowired
	HangKhachHangDAO hkhdao;
	 
	@RequestMapping("/admin/customerRanked")
	public String CustomerRank(Model model) {
		HangKhachHang hkh = new HangKhachHang();
		return "views/Admin/customerRanked";
	}
	
	@RequestMapping("/admin/customerRanktabled")
	public String customerRankTable(@ModelAttribute("hangkhachhang") HangKhachHang hkh, Model model) {
		
		List<HangKhachHang> hkhs = hkhdao.findAll();
		model.addAttribute("hkhs", hkhs);
		return "views/Admin/customerRankTabled";
	}
	
	

}
