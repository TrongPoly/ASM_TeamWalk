package com.fpoly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.dao.HangKhachHangDAO;
import com.fpoly.entity.HangKhachHang;




@Controller
public class HangKhachHangController {
	
	@Autowired
	HangKhachHangDAO hkhdao;
	 
	@RequestMapping("/admin/customerRanked")
	public String CustomerRank(Model model) {
		HangKhachHang hkh = new HangKhachHang();
		model.addAttribute("hangkhachhang",hkh);
		return "views/Admin/customerRanked";
	}
	
	@RequestMapping("/admin/customerRanktabled")
	public String customerRankTable(@ModelAttribute("hangkhachhang") HangKhachHang hkh, Model model,
			@RequestParam("p") Optional<Integer> p) {
		var numberOfRecords = hkhdao.count();
		var numberOfPages = (int) Math.ceil(numberOfRecords / 5.0);
		model.addAttribute("numberOfPages", numberOfPages);
		Pageable sort = PageRequest.of(p.orElse(0), 5, Sort.by("diemToiThieu").ascending());
		model.addAttribute("currIndex", p.orElse(0));
		var hkhs = hkhdao.findAll(sort);
		model.addAttribute("hkhs", hkhs);
		return "views/Admin/customerRankTabled";
	}
	
	@RequestMapping("/admin/customerRank/edit/{id}")
	public String edit(Model model,@PathVariable("id") Integer id) {
		HangKhachHang hkh = hkhdao.findById(id).get();
		model.addAttribute("hangkhachhang",hkh);
		List<HangKhachHang> hkhs = hkhdao.findAll();
		model.addAttribute("hkhs",hkhs);
		return "views/Admin/customerRanked";
	}
	@GetMapping("/admin/customerRank/page")
	public String paginate(@ModelAttribute("hangkhachhang") HangKhachHang hkh, Model model,@RequestParam("p") Optional<Integer> p) {
		return this.customerRankTable(hkh, model,p);
	}
	
	@RequestMapping("/admin/customerRankTabled/save")
	public String save(@ModelAttribute("hangkhachhang") HangKhachHang hkh, Model model) {
		
		hkhdao.save(hkh);
		return "redirect:/admin/customerRanktabled";
	}
	
	@RequestMapping(value = "/admin/customerRank/delete/{id}")
	public String deleteId(@PathVariable("id") Integer id ) {
		hkhdao.deleteById(id);
		return "redirect:/admin/customerRanktabled";
	}
	
	

}
