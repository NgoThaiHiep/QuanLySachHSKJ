
package entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan {
	
	@Id
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TaiKhoan")
	
    private NhanVien tenTK;
    private String matKhau;
  
    @Column(name= "trangThai", columnDefinition = "nvarchar(255)")
    private String trangThai;

    public TaiKhoan(NhanVien tenTK, String matKhau, String trangThai) {
        this.tenTK = tenTK;
        this.matKhau = matKhau;
      
        this.trangThai = trangThai;
    }

    

    public TaiKhoan(NhanVien tenTK, String matKhau) {
        this.tenTK = tenTK;
        this.matKhau = matKhau;
    }
    
    public TaiKhoan() {
    }

    public TaiKhoan(NhanVien tenTK) {
        this.tenTK = tenTK;
    }

    public void setTenTK(NhanVien tenTK) {
        this.tenTK = tenTK;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }


    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public NhanVien getTenTK() {
        return tenTK;
    }

    public String getMatKhau() {
        return matKhau;
    }



    public String getTrangThai() {
        return trangThai;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.tenTK);
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
        final TaiKhoan other = (TaiKhoan) obj;
        return Objects.equals(this.tenTK, other.tenTK);
    }

    @Override
    public String toString() {
        return "TaiKhoan{" + "tenTK=" + tenTK + ", matKhau=" + matKhau +  ", trangThai=" + trangThai + '}';
    }
    
    
    
           
   
    
}
