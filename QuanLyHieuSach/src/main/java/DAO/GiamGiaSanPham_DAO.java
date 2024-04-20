package DAO;

import java.util.ArrayList;

import entity.GiamGiaSanPham;

public interface GiamGiaSanPham_DAO {
	public ArrayList<GiamGiaSanPham> layDanhSachGiamGiaSanPham ();
	public ArrayList<GiamGiaSanPham> layDanhSachGiamGiaSanPham_GiaTien (int loai);
	public ArrayList<GiamGiaSanPham> layDanhSachGiamGiaSanPham_TyLe (int loai);
	public String generateGiamGiaSanPham_TyLe();
	public String generateGiamGiaSanPham_GiaTien() ;
	 public boolean kiemTraGiamGiaSanPham(String code);
	 public boolean themGiamGiaSanPham_TyLe(GiamGiaSanPham giamGiaSanPham);
	 public boolean themGiamGiaSanPham_GiaTien(GiamGiaSanPham giamGiaSanPham);
	 public boolean updateGiamGiaSanPham_GiaTien(GiamGiaSanPham giamGiaSanPham);
	 public boolean updateGiamGiaSanPham_TyLe(GiamGiaSanPham giamGiaSanPham);
	 public boolean update_tinhTrang(String ma,String tinhTrang);
}
