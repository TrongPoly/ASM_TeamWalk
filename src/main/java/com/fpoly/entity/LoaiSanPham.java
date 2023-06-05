package com.fpoly.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "loai_san_pham")
public class LoaiSanPham {
    @Id
    @Column(name = "ma_loai", nullable = false)
    private Long id;

    @Nationalized
    @Lob
    @Column(name = "ten_loai")
    private String tenLoai;

}