
package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author ThaiHiep
 */
@Entity
@Table(name = "VanPhongPham")
public class VanPhongPham extends SanPham{
	
	@ManyToOne
	@JoinColumn(name = "MaXuatXu")
    private XuatXu xuatXu;
	
	@ManyToOne
	@JoinColumn(name = "MaThuongHieu")
    private ThuongHieu thuongHieu;
	
	@ManyToOne
	@JoinColumn(name = "MaLoaiVanPhongPham")
    private LoaiVanPhongPham loaiVanPhongPham;
	@Column(name = "chatLieu", columnDefinition = "nvarchar(255)")
    private String chatLieu;
    private int namSanXuat;

    public VanPhongPham(XuatXu xuatXu, ThuongHieu thuongHieu, LoaiVanPhongPham loaiVanPhongPham, String chatLieu, int namSanXuat, String maSanPham, String tenSanPham, LoaiSanPham loaiSanPham, NhaCungCap nhaCungCap, int soLuongTon, double donGia, String moTa, String tinhTrang, String hinhAnh) {
        super(maSanPham, tenSanPham, loaiSanPham, nhaCungCap, soLuongTon, donGia, moTa, tinhTrang, hinhAnh);
        this.xuatXu = xuatXu;
        this.thuongHieu = thuongHieu;
        this.loaiVanPhongPham = loaiVanPhongPham;
        this.chatLieu = chatLieu;
        this.namSanXuat = namSanXuat;
    }

    public VanPhongPham(XuatXu xuatXu, ThuongHieu thuongHieu, LoaiVanPhongPham loaiVanPhongPham, String chatLieu, int namSanXuat, String maSanPham, String tenSanPham, LoaiSanPham loaiSanPham, NhaCungCap nhaCungCap, int soLuongTon, double donGia, String tinhTrang, String hinhAnh) {
        super(maSanPham, tenSanPham, loaiSanPham, nhaCungCap, soLuongTon, donGia, tinhTrang, hinhAnh);
        this.xuatXu = xuatXu;
        this.thuongHieu = thuongHieu;
        this.loaiVanPhongPham = loaiVanPhongPham;
        this.chatLieu = chatLieu;
        this.namSanXuat = namSanXuat;
    }


    
    public VanPhongPham(String maSanPham) {
        super(maSanPham);
    }

    public VanPhongPham() {
    }

    public XuatXu getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(XuatXu xuatXu) {
        this.xuatXu = xuatXu;
    }

    public ThuongHieu getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(ThuongHieu thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public LoaiVanPhongPham getLoaiVanPhongPham() {
        return loaiVanPhongPham;
    }

    public void setLoaiVanPhongPham(LoaiVanPhongPham loaiVanPhongPham) {
        this.loaiVanPhongPham = loaiVanPhongPham;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public int getNamSanXuat() {
        return namSanXuat;
    }

    public void setNamSanXuat(int namSanXuat) {
        this.namSanXuat = namSanXuat;
    }

    @Override
    public String toString() {
        return "VanPhongPham{" + "xuatXu=" + xuatXu + ", thuongHieu=" + thuongHieu + ", loaiVanPhongPham=" + loaiVanPhongPham + ", chatLieu=" + chatLieu + ", namSanXuat=" + namSanXuat + '}';
    }
    
    
}
