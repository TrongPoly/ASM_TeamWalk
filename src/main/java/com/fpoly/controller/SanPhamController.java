package com.fpoly.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fpoly.dao.SanPhamDAO;

import com.fpoly.entity.SanPham;
import com.fpoly.services.OptionServiceLoaiSanPham;
import com.fpoly.services.UploadFileService;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;
import lombok.val;

@Controller
public class SanPhamController {

	@Autowired
	UploadFileService uploadFileService;
	@Autowired
	SanPhamDAO spdao;
	@Autowired
	public OptionServiceLoaiSanPham optionService;

	@RequestMapping("/admin/product")
	public String ProductForm(Model model) {

		Map<Long, String> options = optionService.getAllOptions();
		SanPham sp = new SanPham();
		model.addAttribute("sanpham", sp);
		var sps = spdao.findAll();
		model.addAttribute("sps", sps);
		model.addAttribute("options", options);

		return "views/Admin/productadd";
	}

	@RequestMapping("/admin/productTable")
	public String ProductTable(Model model, @RequestParam("p") Optional<Integer> p) {
		var numberOfRecords = spdao.count();
		var numberOfPages = (int) Math.ceil(numberOfRecords / 5.0);
		model.addAttribute("numberOfPages", numberOfPages);
		Pageable sort = PageRequest.of(p.orElse(0), 5, Sort.by("maLoai").ascending());
		model.addAttribute("currIndex", p.orElse(0));

		var sps = spdao.findAll(sort);
		model.addAttribute("sps", sps);
		return "views/Admin/productTabled";
	}

	@GetMapping("/admin/product/page")
	public String paginate(@ModelAttribute("sanpham") SanPham sp, Model model, @RequestParam("p") Optional<Integer> p) {
		return this.ProductTable(model, p);
	}

	@RequestMapping("/admin/product/save")
	public String save(@Valid @ModelAttribute("sanpham") SanPham sp, BindingResult result,@RequestParam("file") MultipartFile file,  Model model)
			throws IOException {
		
		if(result.hasErrors()) {
			Map<Long, String> options = optionService.getAllOptions();
			model.addAttribute("options", options);
			return "views/Admin/productadd";
		}
		
		// Lưu tệp vào thư mục
		String filename = file.getOriginalFilename().toString();
		String path = "C:\\Users\\Admin\\eclipse-workspace\\ASM_TeamWalk\\src\\main\\resources\\static\\img\\product\\"
				+ filename;
		File savedFile = new File(path);
		file.transferTo(savedFile);

		// Thiết lập đường dẫn cho sản phẩm
		sp.setAnhSanPham(filename);

		// Lưu sản phẩm vào cơ sở dữ liệu
		spdao.save(sp);
		return "redirect:/admin/productTable";
	}

	@RequestMapping(value = "admin/product/delete/{id}")
	public String deleteId(@PathVariable("id") Long id) {
		spdao.deleteById(id);
		return "redirect:/admin/productTable";
	}

	@RequestMapping("/admin/product/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		SanPham sp = spdao.findById(id).get();
		model.addAttribute("sanpham", sp);
		Map<Long, String> options = optionService.getAllOptions();
		model.addAttribute("options", options);
		List<SanPham> sps = spdao.findAll();
		model.addAttribute("sps", sps);
		return "views/Admin/productadd";
	}

	@RequestMapping("/admin/product/search")
	public String searchName(Model model, @RequestParam("name") Optional<String> name,
			@RequestParam(name = "page", defaultValue = "0") Integer pageNo) {
		
//		Pageable pageable = PageRequest.of(pageNo, 4);
//		
//		int totalPages = pageable.getPageSize();
//		
//		
//		
//		model.addAttribute("totalPages", totalPages);
//		
//		model.addAttribute("currenIdenx", pageNo);
		var numberOfRecords = spdao.count();
		var numberOfPages = (int) Math.ceil(numberOfRecords / 5.0);
		model.addAttribute("numberOfPages", numberOfPages);
		Pageable sort = PageRequest.of(pageNo, 5, Sort.by("maLoai").ascending());
		model.addAttribute("currIndex", pageNo);
		
		var sps = spdao.findByTenSanPham("%" + name.orElse("")+ "%", sort);
		model.addAttribute("sps", sps);
		
		
		return "views/Admin/productTabled";
	}
	
	
	
	
	@ModelAttribute("trangthais")
	public Map<Boolean, String> getTrangThai() {
		Map<Boolean, String> map = new HashMap<>();
		map.put(true, "còn hàng");
		map.put(false, "hết hàng");
		return map;

	}
}
