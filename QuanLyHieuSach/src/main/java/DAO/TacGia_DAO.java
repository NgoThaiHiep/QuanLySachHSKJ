package DAO;

import java.util.ArrayList;

import entity.TacGia;

public interface TacGia_DAO {
	public boolean kiemTraMaSach(String code);
	public ArrayList<TacGia> layDanhSachTacGia();
	public boolean InsertTacGia(TacGia tacGia);
	public String generateTacGia();
}
