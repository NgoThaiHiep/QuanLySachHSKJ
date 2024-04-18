
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
@Table(name = "ChucVu")

public class ChucVu {
	@Id
	@Column(name="MaChucVu")
    private String maChucVu;
	@Column(name = "TenChucVu", columnDefinition = "nvarchar(255)")
    private String tenChucVu;

    public ChucVu(String maChucVu, String tenChucVu) {
        this.maChucVu = maChucVu;
        this.tenChucVu = tenChucVu;
    }

    public ChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public ChucVu() {
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.maChucVu);
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
        final ChucVu other = (ChucVu) obj;
        return Objects.equals(this.maChucVu, other.maChucVu);
    }

    @Override
    public String toString() {
        return "ChucVu{" + "maChucVu=" + maChucVu + ", tenChucVu=" + tenChucVu + '}';
    }
    
    
}
