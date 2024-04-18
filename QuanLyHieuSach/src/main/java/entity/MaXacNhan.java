package entity;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "MaXacNhan")
public class MaXacNhan {
	
	@Id
	@OneToOne
	@JoinColumn(name = "TaiKhoan")
	private NhanVien taiKhoan;
	private String email;
	private String maXacNhan;
	private LocalDateTime thoiGianTao;

	
	
	public MaXacNhan() {
		// TODO Auto-generated constructor stub
	}


	public MaXacNhan(NhanVien taiKhoan, String email, String maXacNhan, LocalDateTime thoiGianTao) {
		super();
		this.taiKhoan = taiKhoan;
		this.email = email;
		this.maXacNhan = maXacNhan;
		this.thoiGianTao = thoiGianTao;
		
	}


	public NhanVien getTaiKhoan() {
		return taiKhoan;
	}


	public void setTaiKhoan(NhanVien taiKhoan) {
		this.taiKhoan = taiKhoan;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMaXacNhan() {
		return maXacNhan;
	}


	public void setMaXacNhan(String maXacNhan) {
		this.maXacNhan = maXacNhan;
	}


	public LocalDateTime getThoiGianTao() {
		return thoiGianTao;
	}


	public void setThoiGianTao(LocalDateTime thoiGianTao) {
		this.thoiGianTao = thoiGianTao;
	}




	@Override
	public int hashCode() {
		return Objects.hash(email, maXacNhan, taiKhoan, thoiGianTao);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaXacNhan other = (MaXacNhan) obj;
		return Objects.equals(email, other.email) && Objects.equals(maXacNhan, other.maXacNhan)
				&& Objects.equals(taiKhoan, other.taiKhoan) && Objects.equals(thoiGianTao, other.thoiGianTao);
	}


	@Override
	public String toString() {
		return "MaXacNhan [taiKhoan=" + taiKhoan + ", email=" + email + ", maXacNhan=" + maXacNhan + ", thoiGianTao="
				+ thoiGianTao  + " ]";
	}
	
}
