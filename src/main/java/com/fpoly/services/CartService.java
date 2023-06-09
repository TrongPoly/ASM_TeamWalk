package com.fpoly.services;

import com.fpoly.entity.GioHangChiTiet;
import com.fpoly.entity.GioHangChiTietId;

public interface CartService {
	void saveCartDetail();

	GioHangChiTiet update(GioHangChiTietId id, int soLuong);

}
