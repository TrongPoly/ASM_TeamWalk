package com.fpoly.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.dao.SanPhamDAO;

import com.fpoly.entity.SanPham;
import com.fpoly.services.OptionServiceLoaiSanPham;

@Controller
public class SanPhamController {
	
	@Autowired
	SanPhamDAO spdao;
	@Autowired
	public OptionServiceLoaiSanPham optionService;
	
	
	@RequestMapping("/admin/product")
	public String ProductForm(Model model) {
		
		Map<Long, String> options = optionService.getAllOptions();
		SanPham sp = new SanPham();
		model.addAttribute("sanpham",sp);
		var sps = spdao.findAll();
		model.addAttribute("sps",sps);
		model.addAttribute("options", options);
		
		return "views/Admin/productadd";
	}
	
	@RequestMapping("/admin/productTable")
	public String ProductTable(Model model,@RequestParam("p") Optional<Integer> p) {
		var numberOfRecords = spdao.count();
		var numberOfPages = (int) Math.ceil(numberOfRecords / 5.0);
		model.addAttribute("numberOfPages", numberOfPages);
		Pageable sort = PageRequest.of(p.orElse(0), 5, Sort.by("maLoai").ascending());
		model.addAttribute("currIndex", p.orElse(0));
		
		
		var sps = spdao.findAll(sort);
		model.addAttribute("sps",sps);
		return "views/Admin/productTabled";
	}

	@GetMapping("/admin/product/page")
	public String paginate(@ModelAttribute("sanpham") SanPham sp, Model model,@RequestParam("p") Optional<Integer> p) {
		return this.ProductTable(model,p);
	}
	
	@RequestMapping("/admin/product/save")
	public String save(@ModelAttribute("sanpham") SanPham sp,Model model) {
		sp.getTrangThai();
		if (Boolean.TRUE) {
			model.addAttribute("trangthai","Còn Hàng");
		}else
			model.addAttribute("trangthai","Hết Hàng");
	
		spdao.save(sp);
		return "redirect:/admin/productTable";
	}
	@RequestMapping(value = "admin/product/delete/{id}")
	public String deleteId(@PathVariable("id") Long id ) {
		spdao.deleteById(id);
		return "redirect:/admin/productTable";
	}
	
	@RequestMapping("/admin/product/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		SanPham sp = spdao.findById(id).get();
		model.addAttribute("sanpham",sp);
		Map<Long, String> options = optionService.getAllOptions();
		model.addAttribute("options", options);
		List<SanPham> sps = spdao.findAll();
		model.addAttribute("sps",sps);
		return "views/Admin/productadd";
	}
	@ModelAttribute("trangthais")
	public Map<Boolean, String> getTrangThai(){
		Map<Boolean, String> map = new HashMap<>();
		map.put(true, "còn hàng");
		map.put(false, "hết hàng");
		return map;
		
	}
}
