package DAO;

import java.util.ArrayList;

import entity.NhaCungCap;

public interface NhaCungCap_DAO {
	public String generateNhaCungCap();
	public boolean kiemTraMaNhaCungCap(String code);
	public boolean InsertNhaCungCap(NhaCungCap nhaCungCap);
	public ArrayList<NhaCungCap> layDanhSachNhaCungCap();
	
}
