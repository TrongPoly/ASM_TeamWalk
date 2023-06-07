package com.fpoly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fpoly.dao.KhachHangDAO;
import com.fpoly.entity.KhachHang;

import jakarta.validation.Valid;




@Controller
public class KhachHangController {

	@Autowired
	KhachHangDAO khdao;
	
	@RequestMapping("/customerTable")
	public String KhachHang(@ModelAttribute("khachhang") KhachHang kh, Model model) {	
		List<KhachHang> khs = khdao.findAll();
		model.addAttribute("khs",khs);// buộc lên bảng
		return "views/Admin/customerTabled";	
	}
	
	@RequestMapping("/customered")
	public String Customer(@ModelAttribute("khachhang") KhachHang kh ,Model model) {
//		KhachHang kh = new KhachHang();
//		model.addAttribute("khachhang",kh);
		return "views/Admin/customered";
	}
	
	@RequestMapping("/customer/save")
	public String Save(@Valid @ModelAttribute("khachhang") KhachHang kh, BindingResult khresult,Model model) {
		
		if(khresult.hasErrors()) {
			return "views/Admin/customered";
		}
		khdao.save(kh);
		
		model.addAttribute("kh",kh);
		return "views/Admin/customered";
	}
	
	
	@RequestMapping("/customer/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id) {
		KhachHang kh = khdao.findById(id).get();
		List<KhachHang> khs = khdao.findAll();
		model.addAttribute("khs",khs);
		return "redirect:/views/Admin/customered";
	}
	
//	@RequestMapping("/customer/delete/{id}")
//	public String delete(@PathVariable("id") Long id) {
//	
//	}

}
