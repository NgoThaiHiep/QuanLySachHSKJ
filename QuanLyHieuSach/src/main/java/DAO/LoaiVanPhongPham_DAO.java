package DAO;

import java.util.ArrayList;

import entity.LoaiVanPhongPham;

public interface LoaiVanPhongPham_DAO {
	public String generatMaLoaiVanPhongPham() ;
	public boolean kiemTraMaLoaiVanPhongPham(String code) ;
	public boolean InsertLoaiVanPhongPham(LoaiVanPhongPham loaiVanPhongPham);
	public ArrayList<LoaiVanPhongPham> layDanhLoaiVanPhongPham();
}
