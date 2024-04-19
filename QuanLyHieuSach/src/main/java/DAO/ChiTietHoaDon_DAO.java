package DAO;

import java.util.ArrayList;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.SanPham;

public interface ChiTietHoaDon_DAO {
	public boolean InsertCTHoaDon(HoaDon hd,int soLuong, double giaBan,SanPham sanPham);
	public boolean UpdateCTHoaDon(HoaDon hd,int soLuong, double giaBan,SanPham sanPham);
	public boolean DeleteCTHoaDon(HoaDon hd,SanPham sanPham);
	public boolean DeleteCTHoaDon(HoaDon hd);
	public ArrayList<ChiTietHoaDon> layDanhSachCTHoaDon();
	public ArrayList<ChiTietHoaDon> layDanhSachCTHoaDonTheoMa(String maHoaDon);
}
