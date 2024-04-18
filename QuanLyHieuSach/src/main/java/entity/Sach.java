 
package entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author ThaiHiep
 */
@Entity
@Table(name = "Sach")
public class Sach extends SanPham{
	@ManyToMany
	@JoinTable(name = "Sach_TacGia",
		joinColumns  = @JoinColumn(name = "SachID"),
		inverseJoinColumns = @JoinColumn(name = "TacGiaID"))
    private Set<TacGia> tacGia ;
	
    private int namXuatBan ;
    private  int soTrang ;
    
    @ManyToMany
    @JoinTable(name = "Sach_TheLoai",
    		joinColumns  = @JoinColumn(name = "SachID"),
    		inverseJoinColumns = @JoinColumn(name = "TheLoaiID"))
    private Set<TheLoai> theLoai; 
    
    @ManyToOne
    @JoinColumn(name = "NhaXuatBan")
    private NhaXuatBan nhaXuatBan ;
    
    @Column(name = "NgonNgu", columnDefinition = "nvarchar(255)")
    private String ngonNgu;
   

    public Sach(String maSanPham, String tenSanPham, LoaiSanPham loaiSanPham, NhaCungCap nhaCungCap, int soLuongTon,
			double donGia, String moTa, String tinhTrang, String hinhAnh, int namXuatBan, int soTrang,
			NhaXuatBan nhaXuatBan, String ngonNgu) {
		super(maSanPham, tenSanPham, loaiSanPham, nhaCungCap, soLuongTon, donGia, moTa, tinhTrang, hinhAnh);
		this.namXuatBan = namXuatBan;
		this.soTrang = soTrang;
		this.nhaXuatBan = nhaXuatBan;
		this.ngonNgu = ngonNgu;
	}


	
    

    public Sach(String maSanPham) {
        super(maSanPham);
    }

    public String getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(String ngonNgu) {
        this.ngonNgu = ngonNgu;
    }

    
    public Sach() {
    }

  

    public Set<TacGia> getTacGia() {
		return tacGia;
	}

	public void setTacGia(Set<TacGia> tacGia) {
		this.tacGia = tacGia;
	}





	public void setTheLoai(Set<TheLoai> theLoai) {
		this.theLoai = theLoai;
	}





	public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }

  
    public NhaXuatBan getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(NhaXuatBan nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    @Override
    public String toString() {
        return "Sach{" + "tacGia=" + tacGia + ", namXuatBan=" + namXuatBan + ", soTrang=" + soTrang + ", theLoai=" + theLoai + ", nhaXuatBan=" + nhaXuatBan + ", ngonNgu=" + ngonNgu + '}';
    }


    
    
    
}
