package com.fpoly.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fpoly.dao.KhachHangDAO;
import com.fpoly.dao.TaiKhoanDAO;
import com.fpoly.entity.KhachHang;
import com.fpoly.entity.TaiKhoan;
import com.fpoly.services.SessionService;

import jakarta.validation.Valid;

@Controller
public class KhachHangController {

	@Autowired
	KhachHangDAO khdao;

	@Autowired
	TaiKhoanDAO tkdao;

	@Autowired
	SessionService sessionService;

	@RequestMapping("/admin/customerTabled")
	public String customerTable(Model model, @RequestParam("p") Optional<Integer> p) {
		var numberOfRecords = khdao.count();
		var numberOfPages = (int) Math.ceil(numberOfRecords / 5.0);
		model.addAttribute("numberOfPages", numberOfPages);
		Pageable sort = PageRequest.of(p.orElse(0), 5, Sort.by("id").ascending());
		model.addAttribute("currIndex", p.orElse(0));
		var khs = khdao.findAll(sort);
		model.addAttribute("khs", khs);// buộc lên bảng
		return "views/Admin/customerTabled";
	}

	@RequestMapping("/admin/customered")
	public String Customer(Model model, Optional<Integer> p) {
		KhachHang kh = new KhachHang();
		model.addAttribute("khachhang", kh);
		List<KhachHang> khs = khdao.findAll();
		model.addAttribute("khs", khs);
		return "views/Admin/customered";
	}

	@RequestMapping("/admin/chanuser")
	public String blockUser(Model model, @RequestParam("email") String email,
			@RequestParam("trangThai") Boolean trangThai) {
		TaiKhoan currentUser = sessionService.get("user");
		 if (currentUser != null && currentUser.getEmail().equals(email)) {
			model.addAttribute("error", "Bạn không thể chặn chính mình.");
		} else {
			// Tiến hành chặn người dùng khác
			TaiKhoan tk = tkdao.getById(email);
			tk.setTrangThai(trangThai);
			tkdao.save(tk);
			model.addAttribute("msg", "Đã chặn người dùng thành công.");
		}
		return "forward:/admin/customerTabled";
	}

	@RequestMapping(value = "/admin/customer/save", method = RequestMethod.POST)
	public String Save(@Valid @ModelAttribute("khachhang") KhachHang kh, BindingResult khresult, Model model) {

		if (khresult.hasErrors()) {
			return "views/Admin/customered";
		}
		khdao.save(kh);

		model.addAttribute("kh", kh);
		return "redirect:/admin/customerTabled";
	}

	@RequestMapping("/admin/customer/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id) {
		KhachHang kh = khdao.findById(id).get();
		model.addAttribute("khachhang", kh);
		List<KhachHang> khs = khdao.findAll();
		model.addAttribute("khs", khs);
		return "views/Admin/customered";
	}

	@GetMapping("/admin/customer/page")
	public String paginate(@ModelAttribute("khachhang") KhachHang kh, Model model,
			@RequestParam("p") Optional<Integer> p) {
		return this.customerTable(model, p);
	}

	@RequestMapping("/admin/customer/update")
	public String update(@ModelAttribute("khachhang") KhachHang kh, Model model) {
		khdao.save(kh);
		return "views/Admin/customered";
	}

	@RequestMapping(value = "/admin/customer/delete/{id}")
	public String deleteId(@PathVariable("id") Long id) {
		khdao.deleteById(id);
		return "redirect:/customerTabled";
	}

	@RequestMapping("/admin/customer/search")
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
		var numberOfRecords = khdao.count();
		var numberOfPages = (int) Math.ceil(numberOfRecords / 5.0);
		model.addAttribute("numberOfPages", numberOfPages);
		Pageable sort = PageRequest.of(pageNo, 5, Sort.by("id").ascending());
		model.addAttribute("currIndex", pageNo);

		var khs = khdao.findByTenKhachHang("%" + name.orElse("") + "%", sort);
		model.addAttribute("khs", khs);

		return "views/Admin/customerTabled";
	}

}
