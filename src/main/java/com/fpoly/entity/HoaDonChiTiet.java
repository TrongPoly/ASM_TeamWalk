package com.fpoly.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "hoa_don_chi_tiet")
public class HoaDonChiTiet {
    @EmbeddedId
    private HoaDonChiTietId id;

    @MapsId("maHoaDon")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_hoa_don", nullable = false)
    private HoaDon maHoaDon;

    @MapsId("maSanPham")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_san_pham", nullable = false)
    private SanPham maSanPham;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "don_gia", precision = 19, scale = 4)
    private BigDecimal donGia;

    @Nationalized
    @Lob
    @Column(name = "ghi_chu")
    private String ghiChu;

}