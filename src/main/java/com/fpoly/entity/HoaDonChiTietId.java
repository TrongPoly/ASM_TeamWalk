package com.fpoly.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class HoaDonChiTietId implements Serializable {
    private static final long serialVersionUID = 3077465354933783014L;
    @NotNull
    @Column(name = "ma_hoa_don", nullable = false)
    private Long maHoaDon;

    @NotNull
    @Column(name = "ma_san_pham", nullable = false)
    private Long maSanPham;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HoaDonChiTietId entity = (HoaDonChiTietId) o;
        return Objects.equals(this.maSanPham, entity.maSanPham) &&
                Objects.equals(this.maHoaDon, entity.maHoaDon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maSanPham, maHoaDon);
    }

}