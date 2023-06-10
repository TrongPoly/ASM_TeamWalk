package com.fpoly.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.dao.LoaiSanPhamDAO;
import com.fpoly.entity.LoaiSanPham;

@Service
public class OptionServiceLoaiSanPham {
	@Autowired
	public LoaiSanPhamDAO loaisanphamdao;
	
	public Map<Long, String> getAllOptions(){
		List<LoaiSanPham> loaisanphams = loaisanphamdao.findAll();
		Map<Long, String> sanphamMap = new HashMap<>();
		for(LoaiSanPham loaisanpham : loaisanphams) {
			sanphamMap.put(loaisanpham.getId(), loaisanpham.getTenLoai());
		}
		
		return sanphamMap;
	}
	public LoaiSanPham getOptionByKey(String key) {
		List<LoaiSanPham> loaisanphams = loaisanphamdao.findbykeywords(key);
		return loaisanphams.isEmpty() ? null : loaisanphams.get(0);
	}

}
