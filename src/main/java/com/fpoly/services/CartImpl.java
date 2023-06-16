package com.fpoly.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fpoly.dao.GioHangChiTietDAO;
import com.fpoly.dao.GioHangDAO;
import com.fpoly.dao.KhachHangDAO;
import com.fpoly.entity.GioHang;
import com.fpoly.entity.GioHangChiTiet;
import com.fpoly.entity.GioHangChiTietId;
import com.fpoly.entity.KhachHang;
import com.fpoly.entity.TaiKhoan;

@Service
public class CartImpl implements CartService {
	@Autowired
	GioHangChiTietDAO gioHangChiTietDAO;
	@Autowired
	SessionService sessionService;
	@Autowired
	KhachHangDAO khachHangDAO;
	@Autowired
	GioHangDAO gioHangDAO;

	@Override
	public void saveCartDetail() {
		// TODO Auto-generated method stub

	}

	@Override
	public GioHangChiTiet update(GioHangChiTietId id, int soLuong) {
		gioHangChiTietDAO.getById(id).setSoLuong(soLuong);
		return gioHangChiTietDAO.getById(id);
	}

	@Override
	public Long TinhTotal(GioHang gioHang) {
		var total = gioHangChiTietDAO.total(gioHang);
		return total;
	}

}
