package com.fpoly.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "gio_hang")
public class GioHang {
    @Id
    @Column(name = "ma_gio_hang", nullable = false)
    private UUID id;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @Column(name = "ngay_cap_nhat")
    private LocalDate ngayCapNhat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nguoi_so_huu")
    private KhachHang nguoiSoHuu;

}