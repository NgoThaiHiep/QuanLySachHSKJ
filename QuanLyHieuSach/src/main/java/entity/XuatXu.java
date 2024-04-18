
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
@Table(name = "XuatXu")
public class XuatXu {
	@Id
	@Column(name="MaXuatXu")
    private String maXuatXu;
	    @Column(name = "TenQuocGia", columnDefinition = "nvarchar(255)")
    private String tenQuocGia;

    public XuatXu(String maXuatXu) {
        this.maXuatXu = maXuatXu;
    }

    
    
    public XuatXu(String maXuatXu, String tenQuocGia) {
        this.maXuatXu = maXuatXu;
        this.tenQuocGia = tenQuocGia;
    }

    public XuatXu() {
    }

    public String getMaXuatXu() {
        return maXuatXu;
    }

    public void setMaXuatXu(String maXuatXu) {
        this.maXuatXu = maXuatXu;
    }

    public String getTenQuocGia() {
        return tenQuocGia;
    }

    public void setTenQuocGia(String tenQuocGia) {
        this.tenQuocGia = tenQuocGia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.maXuatXu);
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
        final XuatXu other = (XuatXu) obj;
        return Objects.equals(this.maXuatXu, other.maXuatXu);
    }

    @Override
    public String toString() {
        return "XuatXu{" + "maXuatXu=" + maXuatXu + ", tenQuocGia=" + tenQuocGia + '}';
    }
    
    
}
