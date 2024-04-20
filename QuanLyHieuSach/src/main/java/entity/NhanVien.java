
package entity;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author ThaiHiep
 */
@Entity
@Table(name = "NhanVien")
@Inheritance(strategy = InheritanceType.JOINED)
public class NhanVien {
	@Id
	@Column(name = "MaNV")
    private String maNV;
	@Column(name = "hoTenNhanVien", columnDefinition = "nvarchar(255)")
    private String hoTenNhanVien;
    private String CCCD;
    private  LocalDate  ngaySinh;
    
    @Column(name = "gioiTinh", columnDefinition = "nvarchar(255)")
    private String gioiTinh;
    private String email;
    private String soDienThoai;
    @Column(name = "diaChi", columnDefinition = "nvarchar(255)")
    private String diaChi;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaChucVu")
    private  ChucVu chucVu;
 
   
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaCa")
    private CaLamViec caLam;
    
    @Column(name = "trangThai", columnDefinition = "nvarchar(255)")
    private String trangThai;
    private String hinhAnh;
   
    public NhanVien(String maNV, String hoTenNhanVien, String CCCD, LocalDate ngaySinh, String gioiTinh, 
    		String email, String soDienThoai, ChucVu chucVu, CaLamViec caLam, String trangThai, 
    		String hinhAnh,String diaChi) {
        this.maNV = maNV;
        this.hoTenNhanVien = hoTenNhanVien;
        this.CCCD = CCCD;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.chucVu = chucVu;
      
        this.caLam = caLam;
        this.trangThai = trangThai;
        this.hinhAnh = hinhAnh;
        this.diaChi = diaChi;
    }

    public NhanVien(String maNV, String hoTenNhanVien, ChucVu chucVu) {
        this.maNV = maNV;
        this.hoTenNhanVien = hoTenNhanVien;
        this.chucVu = chucVu;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getCCCD() {
        return CCCD;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

   
    public NhanVien() {
    }

    public NhanVien(String maNV) {
        this.maNV = maNV;
    }

    
    public String getMaNV() {
        return maNV;
    }



    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getEmail() {
        return email;
    }

   
    public String getHoTenNhanVien() {
        return hoTenNhanVien;
    }

    public void setHoTenNhanVien(String hoTenNhanVien) {
        this.hoTenNhanVien = hoTenNhanVien;
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

  
   

    public CaLamViec getCaLam() {
        return caLam;
    }

    public void setCaLam(CaLamViec caLam) {
        this.caLam = caLam;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
    

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }


    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.maNV);
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
        final NhanVien other = (NhanVien) obj;
        return Objects.equals(this.maNV, other.maNV);
    }

    @Override
    public String toString() {
        return "NhanVien{" + "maNV=" + maNV + ", hoTenNhanVien=" + hoTenNhanVien + ", ngaySinh=" + ngaySinh + ", soDienThoai=" + soDienThoai + ", gioiTinh=" + gioiTinh + ", email=" + email + ", chucVu=" + chucVu  + ", caLam=" + caLam + ", trangThai=" + trangThai + ", hinhAnh=" + hinhAnh + '}';
    }
    
    
    
}
