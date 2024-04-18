
package entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author FPTSHOP
 */

@Entity
@Table(name = "KhuyenMaiThanhToan")
public class KhuyenMaiThanhToan {
	@Id
	@Column(name = "MaKhuyenMai")
    private String maKhuyenMai;
	@Column(name = "TenKhuyenMai", columnDefinition = "nvarchar(255)")
    private String tenKhuyenMai;
    private float PhanTramGiam;
    private float giaTriToiThieuDonHang;
    private float GiamToiDa;
    private float soTienGiam;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    @Column(name = "TinhTrang", columnDefinition = "nvarchar(255)")
    private String tinhTrang;
    private int loai;
    @Column(name = "ChiTiet", columnDefinition = "nvarchar(255)")
    private String chiTiet;
    private int soLuong;
    public KhuyenMaiThanhToan() {
    }

    public KhuyenMaiThanhToan(String maKhuyenMai, String tenKhuyenMai, float PhanTramGiam, float giaTriToiThieuDonHang, float GiamToiDa, LocalDate ngayBatDau, LocalDate ngayKetThuc, String tinhTrang,int loai,String chiTiet,int soLuong) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.PhanTramGiam = PhanTramGiam;
        this.giaTriToiThieuDonHang = giaTriToiThieuDonHang;
        this.GiamToiDa = GiamToiDa;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tinhTrang = tinhTrang;
        this.loai = loai;
        this.chiTiet = chiTiet;
        this.soLuong = soLuong;
    }

    public KhuyenMaiThanhToan(String maKhuyenMai, String tenKhuyenMai, float giaTriToiThieuDonHang, float soTienGiam, LocalDate ngayBatDau, LocalDate ngayKetThuc, String tinhTrang, int loai,String chiTiet,int soLuong) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.giaTriToiThieuDonHang = giaTriToiThieuDonHang;
        this.soTienGiam = soTienGiam;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tinhTrang = tinhTrang;
        this.loai = loai;
        this.chiTiet = chiTiet;
        this.soLuong = soLuong;
    }

    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public float getPhanTramGiam() {
        return PhanTramGiam;
    }

    public void setPhanTramGiam(float PhanTramGiam) {
        this.PhanTramGiam = PhanTramGiam;
    }

    public float getGiaTriToiThieuDonHang() {
        return giaTriToiThieuDonHang;
    }

    public void setGiaTriToiThieuDonHang(float giaTriToiThieuDonHang) {
        this.giaTriToiThieuDonHang = giaTriToiThieuDonHang;
    }

    public float getGiamToiDa() {
        return GiamToiDa;
    }

    public void setGiamToiDa(float GiamToiDa) {
        this.GiamToiDa = GiamToiDa;
    }

    public float getSoTienGiam() {
        return soTienGiam;
    }

    public void setSoTienGiam(float soTienGiam) {
        this.soTienGiam = soTienGiam;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "KhuyenMaiThanhToan{" + "maKhuyenMai=" + maKhuyenMai + ", tenKhuyenMai=" + tenKhuyenMai + ", PhanTramGiam=" + PhanTramGiam + ", giaTriToiThieuDonHang=" + giaTriToiThieuDonHang + ", GiamToiDa=" + GiamToiDa + ", soTienGiam=" + soTienGiam + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", tinhTrang=" + tinhTrang + ", loai=" + loai + ", chiTiet=" + chiTiet + ", soLuong=" + soLuong + '}';
    }
    
    
    
}
