package com.fpoly.services;

import org.springframework.ui.Model;

import com.fpoly.entity.GioHang;
import com.fpoly.entity.GioHangChiTiet;
import com.fpoly.entity.GioHangChiTietId;

public interface CartService {
	void saveCartDetail();

	GioHangChiTiet update(GioHangChiTietId id, int soLuong);

	Long TinhTotal(GioHang gioHang);
}
