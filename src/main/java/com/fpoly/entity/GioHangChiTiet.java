package com.fpoly.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gio_hang_chi_tiet")
public class GioHangChiTiet {
    @EmbeddedId
    private GioHangChiTietId id;

    @MapsId("maGioHang")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_gio_hang", nullable = false)
    private GioHang maGioHang;

    @MapsId("maSanPham")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_san_pham", nullable = false)
    private SanPham maSanPham;

    @Column(name = "so_luong")
    private Integer soLuong;

}