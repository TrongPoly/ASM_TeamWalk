package com.fpoly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.dao.GioHangChiTietDAO;

@Service
public class CartImpl implements CartService {
	@Autowired
	GioHangChiTietDAO gioHangChiTietDAO;
	
	@Override
	public void saveCartDetail() {
		// TODO Auto-generated method stub
		
	}

}
