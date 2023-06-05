package com.fpoly.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "hang_khach_hang")
public class HangKhachHang {
    @Id
    @Column(name = "ma_hang", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Nationalized
    @Column(name = "ten_hang", length = 50)
    private String tenHang;

    @Nationalized
    @Lob
    @Column(name = "mo_ta")
    private String moTa;

    @NotNull
    @Column(name = "diem_toi_thieu", nullable = false)
    private Integer diemToiThieu;

    @Column(name = "trang_thai")
    private Boolean trangThai;

}