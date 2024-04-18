
package entity;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
/**
 *
 * @author FPTSHOP
 */
@Entity
@Table(name = "ThoiGianHoatDong")
public class ThoiGianHoatDong {
	
	@Id
	@Column(name = "MaThoiGian")
    private String maThoiGian;
	
	@ManyToOne
	@JoinColumn(name = "MaNV")
	private NhanVien nhanVien;
    private LocalDate  ngayDangNhap;
    private  LocalTime thoiGianDangNhap;
     private LocalTime thoiGianDangXuat;
    private LocalTime thoiGianDaLam;
   
    public ThoiGianHoatDong(String maThoiGian, NhanVien nhanVien, LocalDate ngayDangNhap,  LocalTime thoiGianDangNhap, LocalTime thoiGianDaLam) {
        this.maThoiGian = maThoiGian;
        this.nhanVien = nhanVien;
        this.ngayDangNhap = ngayDangNhap;
        this.thoiGianDangNhap = thoiGianDangNhap;
        this.thoiGianDaLam = thoiGianDaLam;
    }

    public ThoiGianHoatDong(String maThoiGian, NhanVien nhanVien, LocalDate ngayDangNhap, LocalTime thoiGianDangNhap) {
        this.maThoiGian = maThoiGian;
        this.nhanVien = nhanVien;
        this.ngayDangNhap = ngayDangNhap;
        this.thoiGianDangNhap = thoiGianDangNhap;
    }

    public ThoiGianHoatDong(String maThoiGian, LocalTime thoiGianDangXuat, LocalTime thoiGianDaLam) {
        this.maThoiGian = maThoiGian;
        this.thoiGianDangXuat = thoiGianDangXuat;
        this.thoiGianDaLam = thoiGianDaLam;
    }

    public LocalTime getThoiGianDangXuat() {
        return thoiGianDangXuat;
    }

    public void setThoiGianDangXuat(LocalTime thoiGianDangXuat) {
        this.thoiGianDangXuat = thoiGianDangXuat;
    }

    public LocalTime getThoiGianDangNhap() {
        return thoiGianDangNhap;
    }

    public void setThoiGianDangNhap(LocalTime thoiGianDangNhap) {
        this.thoiGianDangNhap = thoiGianDangNhap;
    }

    public ThoiGianHoatDong() {
    }

    public String getMaThoiGian() {
        return maThoiGian;
    }

    public void setMaThoiGian(String maThoiGian) {
        this.maThoiGian = maThoiGian;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public LocalDate getNgayDangNhap() {
        return ngayDangNhap;
    }

    public void setNgayDangNhap(LocalDate ngayDangNhap) {
        this.ngayDangNhap = ngayDangNhap;
    }

  

    public LocalTime getThoiGianDaLam() {
        return thoiGianDaLam;
    }

    public void setThoiGianDaLam(LocalTime thoiGianDaLam) {
        this.thoiGianDaLam = thoiGianDaLam;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.maThoiGian);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ThoiGianHoatDong other = (ThoiGianHoatDong) obj;
        return Objects.equals(this.maThoiGian, other.maThoiGian);
    }

    @Override
    public String toString() {
        return "ThoiGianHoatDong{" + "maThoiGian=" + maThoiGian + ", nhanVien=" + nhanVien + ", ngayDangNhap=" + ngayDangNhap + ", thoiGianDangNhap=" + thoiGianDangNhap + ", thoiGianDaLam=" + thoiGianDaLam + '}';
    }
    
    
    
}
