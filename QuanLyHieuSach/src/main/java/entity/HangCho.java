
package entity;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Thái Hiệp
 */
@Entity
@Table(name = "HangCho")
public class HangCho {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "MaKH")
    private KhachHang khachHang;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "MaSP")
    private SanPham sanPham;
    private int soLuong;
    private LocalDate ngayMua;

    public HangCho() {
    }

    public HangCho(KhachHang khachHang, SanPham sanPham, int soLuong, LocalDate ngayMua) {
        this.khachHang = khachHang;
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.ngayMua = ngayMua;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public LocalDate getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(LocalDate ngayMua) {
        this.ngayMua = ngayMua;
    }

    
    @Override
	public int hashCode() {
		return Objects.hash(khachHang, ngayMua, sanPham, soLuong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HangCho other = (HangCho) obj;
		return Objects.equals(khachHang, other.khachHang) && Objects.equals(ngayMua, other.ngayMua)
				&& Objects.equals(sanPham, other.sanPham) && soLuong == other.soLuong;
	}

	@Override
    public String toString() {
        return "HangCho{" + "khachHang=" + khachHang + ", sanPham=" + sanPham + ", soLuong=" + soLuong + ", ngayMua=" + ngayMua + '}';
    }
    
}
