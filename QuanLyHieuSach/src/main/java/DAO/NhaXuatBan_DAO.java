package DAO;

import java.util.ArrayList;

import entity.NhaXuatBan;

public interface NhaXuatBan_DAO {
	 public String generateNhaXuatBan();
	 public boolean kiemTraNhaXuatBan(String code);
	 public boolean InsertNhaXuatBan(NhaXuatBan nhaXuatBan);
	 public ArrayList<NhaXuatBan> layDanhSachNhaXuatBan();
}
