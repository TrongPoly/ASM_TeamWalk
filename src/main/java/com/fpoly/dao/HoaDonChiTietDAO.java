package com.fpoly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.entity.HoaDonChiTiet;
import com.fpoly.entity.HoaDonChiTietId;
import com.fpoly.entity.ReportDoanhThu;
import com.fpoly.entity.ReportSanPham;

import java.util.List;

import com.fpoly.entity.HoaDon;

public interface HoaDonChiTietDAO extends JpaRepository<HoaDonChiTiet, HoaDonChiTietId> {
	List<HoaDonChiTiet> findByMaHoaDon(HoaDon maHoaDon);

	@Query("select sum(hdct.soLuong * hdct.donGia) from HoaDonChiTiet hdct where hdct.maHoaDon = ?1")
	Long total(HoaDon maHD);

	@Query("select new ReportDoanhThu(hdct.maSanPham.maLoai.tenLoai, sum(hdct.soLuong), sum(hdct.soLuong*hdct.donGia))"
			+ " from HoaDonChiTiet hdct where hdct.maHoaDon.trangThai.id=3 and Month(hdct.maHoaDon.ngayThanhToan)=?1"
			+ "  and Year(hdct.maHoaDon.ngayThanhToan)=?2 Group by hdct.maSanPham.maLoai.tenLoai")
	List<ReportDoanhThu> reportDoanhThu(Integer month, Integer year);

	@Query("select new ReportSanPham(hdct.maSanPham.tenSanPham, sum(hdct.soLuong), sum(hdct.maSanPham.soLuongTon))"
			+ " from HoaDonChiTiet hdct where hdct.maHoaDon.trangThai.id=3 and Month(hdct.maHoaDon.ngayThanhToan)=?1"
			+ "  and Year(hdct.maHoaDon.ngayThanhToan)=?2 Group by hdct.maSanPham.tenSanPham")
	List<ReportSanPham> reportSanPham(Integer month, Integer year);

	@Query("select new ReportSanPham(hdct.maSanPham.tenSanPham, sum(hdct.soLuong), sum(hdct.maSanPham.soLuongTon))"
			+ " from HoaDonChiTiet hdct where hdct.maHoaDon.trangThai.id=3 and Month(hdct.maHoaDon.ngayThanhToan)=?1"
			+ "  and Year(hdct.maHoaDon.ngayThanhToan)=?2 and hdct.maSanPham.maLoai.id = ?3 Group by hdct.maSanPham.tenSanPham")
	List<ReportSanPham> reportSanPhamTheoLoai(Integer month, Integer year, Integer loai);
}
