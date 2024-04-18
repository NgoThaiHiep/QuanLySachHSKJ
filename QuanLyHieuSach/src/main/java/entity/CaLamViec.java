
package entity;

import java.time.LocalTime;
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
@Table(name = "CaLamViec")
public class CaLamViec {
	@Id
	@Column(name="MaCa")
    private String maCa ;
	@Column(name="TenCa", columnDefinition = "nvarchar(255)")
    private String tenCa;
    private LocalTime thoiGianBatDau;
    private LocalTime thoiGianKetThuc;

    public CaLamViec(String maCa, String tenCa, LocalTime thoiGianBatDau, LocalTime thoiGianKetThuc) {
        this.maCa = maCa;
        this.tenCa = tenCa;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public LocalTime getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(LocalTime thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public LocalTime getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(LocalTime thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    

    
    public CaLamViec(String maCa) {
        this.maCa = maCa;
    }

    public CaLamViec() {
    }

    public void setMaCa(String maCa) {
        this.maCa = maCa;
    }

    public void setTenCa(String tenCa) {
        this.tenCa = tenCa;
    }

   
    public String getMaCa() {
        return maCa;
    }

    public String getTenCa() {
        return tenCa;
    }

  

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.maCa);
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
        final CaLamViec other = (CaLamViec) obj;
        return Objects.equals(this.maCa, other.maCa);
    }

    @Override
    public String toString() {
        return "CaLamViec{" + "maCa=" + maCa + ", tenCa=" + tenCa + ", thoiGianBatDau=" + thoiGianBatDau + ", thoiGianKetThuc=" + thoiGianKetThuc + '}';
    }

    
    
}
