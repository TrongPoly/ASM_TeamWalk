package com.fpoly.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "khach_hang")
public class KhachHang {
    @Id
    @Column(name = "ma_khach_hang", nullable = false)
    private Long id;

    @Size(max = 50)
    @Nationalized
    @Column(name = "ten_khach_hang", length = 50)
    private String tenKhachHang;

    @Nationalized
    @Lob
    @Column(name = "dia_chi")
    private String diaChi;

    @Size(max = 15)
    @Column(name = "so_dien_thoai", length = 15)
    private String soDienThoai;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private TaiKhoan email;

    @Size(max = 15)
    @Column(name = "so_can_cuoc", length = 15)
    private String soCanCuoc;

    @Column(name = "diem_tich_luy")
    private Integer diemTichLuy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hang_khach_hang")
    private HangKhachHang hangKhachHang;

}