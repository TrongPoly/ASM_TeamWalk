package com.fpoly.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @Column(name = "ma_hoa_don", nullable = false)
    private Long id;

    @Column(name = "ngay_lap")
    private LocalDate ngayLap;

    @Nationalized
    @Lob
    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "ngay_thanh_toan")
    private LocalDate ngayThanhToan;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nguoi_mua")
    private KhachHang nguoiMua;

}