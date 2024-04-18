/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author FPTSHOP
 */
@Entity
@Table(name = "NhaXuatBan")
public class NhaXuatBan {
	@Id
	@Column(name="MaNhaXuatBan")
    private String maNhaXuatBan;
	@Column(name = "TenNhaXuatBan", columnDefinition = "nvarchar(255)")
    private String tenNhaXuatBan;
	@Column(name = "DiaChi", columnDefinition = "nvarchar(255)")
    private String diaChi;
    private String soDienThoai;
    private String email;

    public NhaXuatBan() {
    }

    public NhaXuatBan(String maNhaXuatBan) {
        this.maNhaXuatBan = maNhaXuatBan;
    }

    public NhaXuatBan(String maNhaXuatBan, String tenNhaXuatBanl, String diaChi, String soDienThoai, String email) {
        this.maNhaXuatBan = maNhaXuatBan;
        this.tenNhaXuatBan = tenNhaXuatBanl;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

    public String getMaNhaXuatBan() {
        return maNhaXuatBan;
    }

    public void setMaNhaXuatBan(String maNhaXuatBan) {
        this.maNhaXuatBan = maNhaXuatBan;
    }

    public String getTenNhaXuatBanl() {
        return tenNhaXuatBan;
    }

    public void setTenNhaXuatBanl(String tenNhaXuatBanl) {
        this.tenNhaXuatBan = tenNhaXuatBanl;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.maNhaXuatBan);
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
        final NhaXuatBan other = (NhaXuatBan) obj;
        return Objects.equals(this.maNhaXuatBan, other.maNhaXuatBan);
    }

    @Override
    public String toString() {
        return "NhaXuatBan{" + "maNhaXuatBan=" + maNhaXuatBan + ", tenNhaXuatBanl=" + tenNhaXuatBan + ", diaChi=" + diaChi + ", soDienThoai=" + soDienThoai + ", email=" + email + '}';
    }
    
    
}
