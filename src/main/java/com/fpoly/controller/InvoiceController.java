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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.fpoly.dao.GioHangChiTietDAO;
import com.fpoly.dao.GioHangDAO;
import com.fpoly.dao.HoaDonChiTietDAO;
import com.fpoly.dao.HoaDonDAO;
import com.fpoly.dao.KhachHangDAO;
import com.fpoly.dao.SanPhamDAO;
import com.fpoly.dao.TaiKhoanDAO;
import com.fpoly.dao.TrangThaiHoaDonDAO;
import com.fpoly.entity.GioHang;
import com.fpoly.entity.GioHangChiTiet;
import com.fpoly.entity.GioHangChiTietId;
import com.fpoly.entity.HoaDon;
import com.fpoly.entity.HoaDonChiTiet;
import com.fpoly.entity.HoaDonChiTietId;
import com.fpoly.entity.KhachHang;
import com.fpoly.entity.SanPham;
import com.fpoly.entity.TaiKhoan;
import com.fpoly.entity.TrangThaiHoaDon;
import com.fpoly.services.CartService;
import com.fpoly.services.CookieImpl;
import com.fpoly.services.CookieService;
import com.fpoly.services.MailerServiceImp;
import com.fpoly.services.SessionService;
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
	@Autowired
	TrangThaiHoaDonDAO trangThaiHoaDonDAO;
	@Autowired
	SessionService sessionService;

	@Autowired
	HttpServletRequest request;

	@GetMapping("/invoice/view")
	public String invoiceView(Model model) {
		userServiceImpl.checkLogged(model);

		// lấy giá trị cookie value = email đã đăng nhập
//		String email = cookieImpl.getValue("cuser");
		TaiKhoan taiKhoan = sessionService.get("user");
		// tìm khách với email
		KhachHang khachHang = khachHangDAO.getByEmail(taiKhoan);

		var hd = hoaDonDAO.findByNguoiMua(khachHang);
		model.addAttribute("hd", hd);
		return "views/user/invoice";
	}

	@RequestMapping("/invoice/details")
	public String invoiceDetails(@RequestParam("id") long id, Model model) {
		userServiceImpl.checkLogged(model);

		HoaDon hoaDon = hoaDonDAO.getById(id);
		long total = hoaDonChiTietDAO.total(hoaDon);
		var hdct = hoaDonChiTietDAO.findByMaHoaDon(hoaDon);
		model.addAttribute("hdct", hdct);
		model.addAttribute("hoaDon", hoaDon);
		model.addAttribute("total", total);
		return "views/user/invoiceDetails";
	}

	@Transactional
	@RequestMapping("/invoice/add")
	public String invoiceAdd(Model model) throws Exception {

		userServiceImpl.checkLogged(model);

		TaiKhoan taiKhoan = sessionService.get("user");

		KhachHang khachHang = khachHangDAO.getByEmail(taiKhoan);
		if (khachHang.getDiaChi().isBlank() || khachHang.getTenKhachHang().isBlank()
				|| khachHang.getSoDienThoai().isBlank()) {

			model.addAttribute("msgProfile", "*Vui lòng thêm thông tin cá nhân trước khi đặt hàng");
			model.addAttribute("kh", khachHang);
			Boolean view = true;

			model.addAttribute("view", view);
			return "views/user/UserInformation";

		}
		HoaDon hd = new HoaDon();

		LocalDate ngayLap = LocalDate.now();
		hd.setNgayLap(ngayLap);

		hd.setNguoiMua(khachHang);

		TrangThaiHoaDon trangThaiHoaDon = trangThaiHoaDonDAO.getById(1);
		hd.setTrangThai(trangThaiHoaDon);
		var hoaDon = hoaDonDAO.save(hd);

		GioHang gioHang = gioHangDAO.getByNguoiSoHuu(khachHang);
		var gioHangChiTiet = gioHangChiTietDAO.findByMaGioHang(gioHang);

		// Kiem tra slSP trong hoaDon và sl tồn
		Boolean checkSLSP = true;
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
			if (hoaDonChiTiet.getSoLuong() > sanPham.getSoLuongTon()) {
				checkSLSP = false;
				break;
			}
			sanPham.setSoLuongTon(sanPham.getSoLuongTon() - hoaDonChiTiet.getSoLuong());
			if (sanPham.getSoLuongTon() == 0) {
				sanPham.setTrangThai(false);
			}
			hoaDonChiTietDAO.save(hoaDonChiTiet);
			sanPhamDAO.save(sanPham);

		}
		if (checkSLSP == false) {
			var hdct = hoaDonChiTietDAO.findByMaHoaDon(hd);
			for (int i = 0; i < hdct.size(); i++) {
				SanPham sanPham = sanPhamDAO.getById(gioHangChiTiet.get(i).getMaSanPham().getId());

				sanPham.setSoLuongTon(sanPham.getSoLuongTon() + hdct.get(i).getSoLuong());
				sanPham.setTrangThai(true);
				sanPhamDAO.save(sanPham);
				hoaDonChiTietDAO.delete(hdct.get(i));
			}

			hoaDonDAO.delete(hd);
			model.addAttribute("messageSLSP", "*Một số sản phẩm có thể không còn đủ số lượng");
			model.addAttribute("messageSLSP1", "Chúng tôi đã đặt lại số lượng tối đa cho bạn");

			return "forward:/viewCart";
		}
		gioHangChiTietDAO.deleteAllByMaGioHang(gioHang);
		mailerServiceImp.datHang(khachHang.getTenKhachHang(), hoaDon.getId());
		return "redirect:/invoice/view";
	}

	// Người dùng xác nhận đã nhận hàng
	@RequestMapping("/requestConfirm")
	public String SendEmail(@RequestParam("maHoaDon") String maHoaDon, @RequestParam("email") String email,
			HttpServletRequest request, Model model) throws Exception {
		mailerServiceImp.send(email, maHoaDon);
		String referer = request.getHeader("referer");
		System.out.println(referer);
		model.addAttribute("msgEmail", "Gửi yêu cầu xác nhận thành công");
		// Chuyển hướng trang đến URL hiện tại để giữ nguyên các tham số
		return "redicrect:" + referer;
	}
}
