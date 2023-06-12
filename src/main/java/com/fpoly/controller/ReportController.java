package com.fpoly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.fpoly.dao.SanPhamDAO;

@Controller
public class ReportController {
	
	@Autowired
	SanPhamDAO spdao;
	
	@RequestMapping("/admin/report")
	public String getReport(Model model) {
		var rp = spdao.getThongKe();
		model.addAttribute("rps", rp);	
		return	"views/Admin/reportSP";
	}
	
	
	
	
}
