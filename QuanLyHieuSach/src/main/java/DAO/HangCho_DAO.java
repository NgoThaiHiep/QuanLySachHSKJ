package DAO;

import java.util.ArrayList;

import entity.HangCho;

public interface HangCho_DAO {
	public ArrayList<HangCho> layDanhSachHangCho();
	public ArrayList<HangCho> layDanhSachHangChoTheoMaKhachHang(String maKhachHang);
	public boolean InsertHangCho(HangCho hangCho) ;
	public void DeleteDanhSachHangCho(String maKhachHang) ;
	public void DeleteHangChoQuaNgay();
}
