package DAO;

import java.util.ArrayList;

import entity.TheLoai;

public interface TheLoai_DAO {
	public String generateTheLoai();

	public boolean kiemTraTheLoai(String code);

	public boolean InsertTheLoai(TheLoai theLoai);

	public boolean UpdateTheLoai(TheLoai theLoai);

	public boolean DeleteTheLoai(TheLoai theLoai);

	public ArrayList<TheLoai> layDanhSachTheLoai();
}
