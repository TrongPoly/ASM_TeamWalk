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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fpoly.dao.KhachHangDAO;
import com.fpoly.entity.KhachHang;

import jakarta.validation.Valid;




@Controller
public class KhachHangController {

	@Autowired
	KhachHangDAO khdao;
	
	@RequestMapping("/customerTabled")
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
	
	
	@RequestMapping( value = "/customer/save" , method = RequestMethod.POST)
	public String Save(@Valid @ModelAttribute("khachhang") KhachHang kh, BindingResult khresult,Model model) {
		
		if(khresult.hasErrors()) {
			return "views/Admin/customered";
		}
		khdao.save(kh);
		
		model.addAttribute("kh",kh);
		return "redirect:/customerTabled";
	}
	
	
	@RequestMapping(value =  "/customer/edit")
	public String edit(@PathVariable("id") Long id,Model model) {
		KhachHang kh = khdao.findById(id).get();
		model.addAttribute("kh",kh);
		List<KhachHang> khs = khdao.findAll();
		model.addAttribute("khs",khs);
		return "redirect:/customered";
	}
	
	@RequestMapping(value = "/customer/update")
	public String update(@ModelAttribute("khachhang") KhachHang kh, Model model) {
		khdao.save(kh);
		return "redirect:/customer/edit" + kh.getId();
	}
	
	@RequestMapping(value = "/customer/delete/{id}")
	public String deleteId(@PathVariable("id") Long id ) {
		khdao.deleteById(id);
		return "redirect:/customerTabled";
	}
}
