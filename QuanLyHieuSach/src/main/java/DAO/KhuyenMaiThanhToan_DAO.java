package DAO;

import java.util.ArrayList;

import entity.KhuyenMaiThanhToan;

public interface KhuyenMaiThanhToan_DAO {
	public ArrayList<KhuyenMaiThanhToan> layDanhSachKhuyenMai_TyLe();
	public String generateMaKhuyenMai_GiaTri();
	public String generateMaKhuyenMai_TyLe();
	public boolean kiemTraMaKhuyenMai_GiaTri(String code);
	public ArrayList<KhuyenMaiThanhToan> layDanhSachKhuyenMai_GiaTien();
	public boolean themGiamGiaSanPham_TyLe(KhuyenMaiThanhToan khuyenMaiThanhToan);
	public boolean capNhatTyLe_KhuyenMai(KhuyenMaiThanhToan khuyenMaiThanhToan);
	public boolean capNhatTyLe_GiaTien(KhuyenMaiThanhToan khuyenMaiThanhToan);
	public boolean themGiamGiaSanPham_GiaTien(KhuyenMaiThanhToan khuyenMaiThanhToan);
	public boolean update_tinhTrang(String ma,String tinhTrang);
	public boolean updateSoLuongKhuyenMai(String ma, int soLuong);
}
