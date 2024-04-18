package DAO;

import java.util.ArrayList;

import entity.SanPham;

public interface SanPham_DAO {
	public SanPham layThongTinSanPham(String maSanPham);
	public ArrayList<SanPham> layDanhSachSanPham();
	public boolean updateTinhTrang(String maSanPham, String tinhTrang);
	public boolean updateSoLuong(String maSanPham, int soLuong);
	public ArrayList<SanPham> layDanhSachTheoMaSach(String maSach);
	
	public ArrayList<SanPham> layDanhSachTheoTenSach(String tenSach);
	public SanPham layThongTinSanPhamTheoTen(String tenSanPham);
	
	
	
}


