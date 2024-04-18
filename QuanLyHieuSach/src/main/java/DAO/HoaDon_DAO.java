package DAO;

import java.time.LocalDate;
import java.util.ArrayList;

import entity.HoaDon;
import entity.NhanVien;

public interface HoaDon_DAO {
	public String generateHoaDon(NhanVien nv);
	public boolean kiemTraMaHoaDon(String code);
	public boolean InsertHoaDon(HoaDon hd);
	public HoaDon layHoaDon(String ma, LocalDate ngay);
	public ArrayList<HoaDon> layDanhSachHoaDon();
	public ArrayList<HoaDon> layDanhSachHoaDonTheoNgay(LocalDate ngayChon);
	public ArrayList<HoaDon> layDanhSachHoaDonTheoThang(LocalDate ngayChon);
	public ArrayList<HoaDon> layDanhSachHoaDonTheoNam(LocalDate ngayChon);
	public ArrayList<HoaDon> layDanhSachHoaDonTheoNhanVien(NhanVien nv);
	public ArrayList<HoaDon> layDanhSachHoaDonTheoKhachHang(String maKH);
	public boolean updateHoaDon(HoaDon hd);
	public boolean deleteHoaDon(HoaDon hd);
	public ArrayList<HoaDon> layDanhSachHoaDonTheoNgayVaNhanVien(NhanVien nv,LocalDate ngayChon);

	
	
}
