package com.fpoly.controller;

import java.math.BigDecimal;
import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fpoly.dao.GioHangChiTietDAO;
import com.fpoly.dao.GioHangDAO;
import com.fpoly.dao.HoaDonChiTietDAO;
import com.fpoly.dao.HoaDonDAO;
import com.fpoly.dao.KhachHangDAO;
import com.fpoly.dao.SanPhamDAO;
import com.fpoly.dao.TaiKhoanDAO;
import com.fpoly.entity.GioHang;
import com.fpoly.entity.GioHangChiTiet;
import com.fpoly.entity.GioHangChiTietId;
import com.fpoly.entity.HoaDon;
import com.fpoly.entity.HoaDonChiTiet;
import com.fpoly.entity.HoaDonChiTietId;
import com.fpoly.entity.KhachHang;
import com.fpoly.entity.SanPham;
import com.fpoly.entity.TaiKhoan;
import com.fpoly.services.CartService;
import com.fpoly.services.CookieImpl;
import com.fpoly.services.CookieService;
import com.fpoly.services.MailerServiceImp;
import com.fpoly.services.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.experimental.var;

@Controller
public class InvoiceController {

	@Autowired
	CookieImpl cookieImpl;
	@Autowired
	KhachHangDAO khachHangDAO;
	@Autowired
	TaiKhoanDAO taiKhoanDAO;
	@Autowired
	SanPhamDAO sanPhamDAO;
	@Autowired
	GioHangDAO gioHangDAO;
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	GioHangChiTietDAO gioHangChiTietDAO;
	@Autowired
	CartService cart;
	@Autowired
	CartService cartService;
	@Autowired
	HoaDonDAO hoaDonDAO;
	@Autowired
	HoaDonChiTietDAO hoaDonChiTietDAO;
	@Autowired
	MailerServiceImp mailerServiceImp;

	@GetMapping("/invoice/view")
	public String invoiceView(Model model) {
		userServiceImpl.checkLogged(model);

		// lấy giá trị cookie value = email đã đăng nhập
		String email = cookieImpl.getValue("cuser");
		TaiKhoan taiKhoan = taiKhoanDAO.getById(email);
		// tìm khách với email
		KhachHang khachHang = khachHangDAO.getByEmail(taiKhoan);

		var hd = hoaDonDAO.findByNguoiMua(khachHang);
		model.addAttribute("hd", hd);
		return "views/user/invoice";
	}

	@RequestMapping("/invoice/details")
	public String invoiceDetails(@RequestParam("trangThai") Boolean trangThai, @RequestParam("id") long id,
			Model model) {
		userServiceImpl.checkLogged(model);
		HoaDon hoaDon = hoaDonDAO.getById(id);
		long total = hoaDonChiTietDAO.total(hoaDon);
		var hdct = hoaDonChiTietDAO.findByMaHoaDon(hoaDon);
		model.addAttribute("hdct", hdct);
		model.addAttribute("hoaDon", hoaDon);
		model.addAttribute("total", total);
		model.addAttribute("trangThai", trangThai);
		return "views/user/invoiceDetails";
	}

	@Transactional
	@RequestMapping("/invoice/add")
	public String invoiceAdd(Model model) {

		// lấy giá trị cookie value = email đã đăng nhập
		String email = cookieImpl.getValue("cuser");
		TaiKhoan taiKhoan = taiKhoanDAO.getById(email);
		// tìm khách với email
		KhachHang khachHang = khachHangDAO.getByEmail(taiKhoan);
		HoaDon hd = new HoaDon();
		LocalDate ngayLap = LocalDate.now();
		hd.setNgayLap(ngayLap);
		hd.setNguoiMua(khachHang);
		hd.setTrangThai(false);
		var hoaDon = hoaDonDAO.save(hd);

		GioHang gioHang = gioHangDAO.getByNguoiSoHuu(khachHang);
		var gioHangChiTiet = gioHangChiTietDAO.findByMaGioHang(gioHang);

		for (int i = 0; i < gioHangChiTiet.size(); i++) {
			HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
			HoaDonChiTietId hoaDonChiTietId = new HoaDonChiTietId();
			SanPham sanPham = sanPhamDAO.getById(gioHangChiTiet.get(i).getMaSanPham().getId());

			hoaDonChiTietId.setMaSanPham(gioHangChiTiet.get(i).getMaSanPham().getId());
			hoaDonChiTietId.setMaHoaDon(hoaDon.getId());

			hoaDonChiTiet.setId(hoaDonChiTietId);
			hoaDonChiTiet.setMaHoaDon(hoaDon);
			hoaDonChiTiet.setMaSanPham(sanPham);
			hoaDonChiTiet.setSoLuong(gioHangChiTiet.get(i).getSoLuong());
			hoaDonChiTiet.setDonGia(BigDecimal.valueOf(gioHangChiTiet.get(i).getMaSanPham().getDonGia()));

			var hdct = hoaDonChiTietDAO.save(hoaDonChiTiet);
		}
		gioHangChiTietDAO.deleteAllByMaGioHang(gioHang);
		return "redirect:/invoice/view";
	}

	@RequestMapping("/requestConfirm")
	public String SendEmail(@RequestParam("maHoaDon") String maHoaDon, @RequestParam("email") String email,
			HttpServletRequest request) throws Exception {
		mailerServiceImp.send(email, maHoaDon);
		String referer = request.getHeader("referer");

		// Chuyển hướng trang đến URL hiện tại để giữ nguyên các tham số
		return "redirect:" + referer;
	}
}
