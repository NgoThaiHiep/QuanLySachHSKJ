
package entity;

import java.util.Objects;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 *
 * @author ThaiHiep
 */
@Entity
@Table(name = "NhaCungCap")
public class NhaCungCap {
	@Id
	@Column(name = "MaNCC")
    private String maNCC;
	@Column(name = "TenNCC", columnDefinition = "nvarchar(255)")
    private String tenNCC;
	@Column(name = "SanPhamCungCap", columnDefinition = "nvarchar(255)")
    private String sanPhamCungCap;
    @Column(name = "DiaChiNCC", columnDefinition = "nvarchar(255)")
    private String diaChiNCC;
    private String soDienThoai;
    private String email;

   
    public NhaCungCap(String maNCC, String tenNCC, String sanPhamCungCap, String diaChiNCC, String soDienThoai,
			String email) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.sanPhamCungCap = sanPhamCungCap;
		this.diaChiNCC = diaChiNCC;
		this.soDienThoai = soDienThoai;
		this.email = email;
	}
    
	public String getSanPhamCungCap() {
		return sanPhamCungCap;
	}

	public void setSanPhamCungCap(String sanPhamCungCap) {
		this.sanPhamCungCap = sanPhamCungCap;
	}

	public NhaCungCap(String maNCC, String tenNCC) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
    }

    public NhaCungCap() {
    }

    public NhaCungCap(String maNCC) {
        this.maNCC = maNCC;
    }

    
    

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getDiaChiNCC() {
        return diaChiNCC;
    }

    public void setDiaChiNCC(String diaChiNCC) {
        this.diaChiNCC = diaChiNCC;
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
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.maNCC);
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
        final NhaCungCap other = (NhaCungCap) obj;
        return Objects.equals(this.maNCC, other.maNCC);
    }

    @Override
    public String toString() {
        return "NhaCungCap{" + "maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChiNCC=" + diaChiNCC + ", soDienThoai=" + soDienThoai + ", email=" + email + '}';
    }

    

    
    
}
