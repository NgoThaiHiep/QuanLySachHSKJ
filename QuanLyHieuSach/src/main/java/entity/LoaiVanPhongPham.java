
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
@Table(name = "LoaiVanPhongPham")
public class LoaiVanPhongPham {
	@Id
	@Column(name="MaLoaiVanPhongPham")
    private String maLoaiVanPhongPham;
	    @Column(name = "TenLoaiVanPhongPham", columnDefinition = "nvarchar(255)")
    private String tenLoaiVanPhongPham;

    public LoaiVanPhongPham() {
    }

    public LoaiVanPhongPham(String maLoaiVanPhongPham) {
        this.maLoaiVanPhongPham = maLoaiVanPhongPham;
    }
    
    

    

    public LoaiVanPhongPham(String maLoaiVanPhongPham, String tenLoaiVanPhongPham) {
        this.maLoaiVanPhongPham = maLoaiVanPhongPham;
        this.tenLoaiVanPhongPham = tenLoaiVanPhongPham;
    }

    public String getMaLoaiVanPhongPham() {
        return maLoaiVanPhongPham;
    }

    public void setMaLoaiVanPhongPham(String maLoaiVanPhongPham) {
        this.maLoaiVanPhongPham = maLoaiVanPhongPham;
    }

    public String getTenLoaiVanPhongPham() {
        return tenLoaiVanPhongPham;
    }

    public void setTenLoaiVanPhongPham(String tenLoaiVanPhongPham) {
        this.tenLoaiVanPhongPham = tenLoaiVanPhongPham;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.maLoaiVanPhongPham);
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
        final LoaiVanPhongPham other = (LoaiVanPhongPham) obj;
        return Objects.equals(this.maLoaiVanPhongPham, other.maLoaiVanPhongPham);
    }

    @Override
    public String toString() {
        return "LoaiVanPhongPham{" + "maLoaiVanPhongPham=" + maLoaiVanPhongPham + ", tenLoaiVanPhongPham=" + tenLoaiVanPhongPham + '}';
    }
    
    
}
