package com.fpoly.controller;

import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fpoly.dao.GioHangChiTietDAO;
import com.fpoly.dao.GioHangDAO;
import com.fpoly.dao.KhachHangDAO;
import com.fpoly.dao.SanPhamDAO;
import com.fpoly.dao.TaiKhoanDAO;
import com.fpoly.entity.GioHang;
import com.fpoly.entity.GioHangChiTiet;
import com.fpoly.entity.GioHangChiTietId;
import com.fpoly.entity.KhachHang;
import com.fpoly.entity.SanPham;
import com.fpoly.entity.TaiKhoan;
import com.fpoly.services.CartService;
import com.fpoly.services.CookieImpl;
import com.fpoly.services.SessionService;
import com.fpoly.services.UserService;
import com.fpoly.services.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.var;

@Controller
public class CartController {
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
	SessionService sessionService;

	@RequestMapping("/viewCart")
	public String viewCart(Model model) {
		userServiceImpl.checkLogged(model);
		// lấy taiKhoan đã lưu vào session
		TaiKhoan taiKhoan = sessionService.get("user");
		// Lấy mã khách thông qua email đăng nhập
		KhachHang IdKhach = khachHangDAO.getByEmail(taiKhoan);
		// Lấy đối tượng giỏ hàng theo id khách
		GioHang gioHang = gioHangDAO.getByNguoiSoHuu(IdKhach);
		// Lấy mã giỏ hàng từ đối tượng giỏ hàng tìm được
		var cartSP = gioHangChiTietDAO.findByMaGioHang(gioHang);
		for (int i = 0; i < cartSP.size(); i++) {
			if (cartSP.get(i).getMaSanPham().getSoLuongTon() == 0) {
				gioHangChiTietDAO.delete(cartSP.get(i));
			} else if (cartSP.get(i).getSoLuong() > cartSP.get(i).getMaSanPham().getSoLuongTon()) {
				cartSP.get(i).setSoLuong(cartSP.get(i).getMaSanPham().getSoLuongTon());
				gioHangChiTietDAO.save(cartSP.get(i));
			}
		}
		var cartSP2 = gioHangChiTietDAO.findByMaGioHang(gioHang);
		model.addAttribute("cartSP", cartSP2);
		// Tính total giỏ hàng
		model.addAttribute("total", cartService.TinhTotal(gioHang));
		return "/views/user/cart";

	}

	@RequestMapping("/sanpham/addtocart")
	public String add(Model model, @RequestParam("id") long id, HttpServletRequest request) {
		String email = cookieImpl.getValue("cuser");
		if (email == null) {
			return "redirect:/login";
		} else {
			SanPham sanPham = sanPhamDAO.getById(id);
			TaiKhoan taiKhoan = taiKhoanDAO.getById(email);
			KhachHang IdKhach = khachHangDAO.getByEmail(taiKhoan);
			GioHang checkGioHang = gioHangDAO.getByNguoiSoHuu(IdKhach);
			// kiểm tra khách hàng có giỏ hàng chưa nếu chưa thì tạo giỏ hàng mới
			if (checkGioHang == null) {
				// tạo giỏ hàng mới
				GioHang gioHang = new GioHang();
				LocalDate ngayTao = LocalDate.now();
				gioHang.setNgayTao(ngayTao);
				gioHang.setNguoiSoHuu(IdKhach);
				gioHangDAO.save(gioHang);

				GioHangChiTietId ghctId = new GioHangChiTietId();
				ghctId.setMaGioHang(gioHang.getId());
				ghctId.setMaSanPham(sanPham.getId());

				GioHangChiTiet ghct = new GioHangChiTiet();
				ghct.setId(ghctId);
				ghct.setMaGioHang(gioHang);
				ghct.setMaSanPham(sanPham);
				ghct.setSoLuong(1);
				gioHangChiTietDAO.save(ghct);
			}
			// Ngược lại thì thêm sản phẩm vào giỏ hàng hiện tại với ma người sở hữu
			else {
				GioHangChiTiet checkSanPham = gioHangChiTietDAO.getByMaSanPhamAndMaGioHang(sanPham, checkGioHang);
				// Kiểm tra sản phẩm có trong giỏ hàng chưa
				if (checkSanPham == null) {
					GioHangChiTietId ghctId = new GioHangChiTietId();
					ghctId.setMaGioHang(checkGioHang.getId());
					ghctId.setMaSanPham(sanPham.getId());

					GioHangChiTiet ghct = new GioHangChiTiet();
					ghct.setId(ghctId);
					ghct.setMaGioHang(checkGioHang);
					ghct.setMaSanPham(sanPham);
					ghct.setSoLuong(1);
					gioHangChiTietDAO.save(ghct);
				} else {
					int quant = checkSanPham.getSoLuong();
					checkSanPham.setSoLuong(quant += 1);
					gioHangChiTietDAO.save(checkSanPham);
				}

			}

		}
		String referer = request.getHeader("referer");

		// Chuyển hướng trang đến URL hiện tại để giữ nguyên các tham số
		return "redirect:" + referer;
	}

	@ResponseBody
	@PostMapping("/cart/item/update")
	public GioHangChiTiet updateSLCardItem(@RequestParam("tenSanPham") String tenSP,
			@RequestParam("soLuong") Integer soLuong, Model model) {
		TaiKhoan taiKhoan = sessionService.get("user");
		// Lấy mã khách thông qua email đăng nhập
		KhachHang IdKhach = khachHangDAO.getByEmail(taiKhoan);
		// Lấy đối tượng giỏ hàng theo id khách
		GioHang gioHang = gioHangDAO.getByNguoiSoHuu(IdKhach);
		// lấy đối tượng sản phẩm theo param tenSP
		SanPham sp = sanPhamDAO.getByTenSanPham(tenSP);
		// Lấy đối tượng ghct theo mã sp của sp tìm được ở trên và
		// mã giỏ hàng
		GioHangChiTiet gioHangChiTiet = gioHangChiTietDAO.getByMaSanPhamAndMaGioHang(sp, gioHang);
		// gọi cartService phương thức update số lượng trong service
		GioHangChiTiet updatedItem = cart.update(gioHangChiTiet.getId(), soLuong);
		if (updatedItem.getSoLuong() > sp.getSoLuongTon()) {
			updatedItem = cart.update(gioHangChiTiet.getId(), sp.getSoLuongTon());
		} else if (updatedItem.getSoLuong() <= 0) {
			updatedItem = cart.update(gioHangChiTiet.getId(), 1);

		}
		gioHangChiTietDAO.save(updatedItem);

		return updatedItem;
	}

	@RequestMapping("/cart/item/delete")
	public String deleteItem(@RequestParam("maGioHang") String magioHang, @RequestParam("maSanPham") long maSanPham) {
		// lấy giá trị cookie value = email đã đăng nhập
		String email = cookieImpl.getValue("cuser");
		// tìm tài khoản với email
		TaiKhoan taiKhoan = taiKhoanDAO.getById(email);
		// Lấy mã khách thông qua email đăng nhập
		KhachHang IdKhach = khachHangDAO.getByEmail(taiKhoan);
		// Lấy đối tượng giỏ hàng theo id khách
		GioHang gioHang = gioHangDAO.getByNguoiSoHuu(IdKhach);
		// lấy đối tượng sản phẩm theo param tenSP
		SanPham sp = sanPhamDAO.getById(maSanPham);
		GioHangChiTiet gioHangChiTiet = gioHangChiTietDAO.getByMaSanPhamAndMaGioHang(sp, gioHang);
		gioHangChiTietDAO.delete(gioHangChiTiet);
		return "redirect:/viewCart";
	}
}
