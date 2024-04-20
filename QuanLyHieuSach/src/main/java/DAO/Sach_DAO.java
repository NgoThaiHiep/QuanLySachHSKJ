package DAO;


import java.util.List;

import entity.Sach;

public interface Sach_DAO {
	public List<Sach> layDanhSachSanPhamSach();
	public Sach layThongTinSach(String tenSach);
	public boolean insertSach(Sach s);
	public boolean updateSach(Sach s);
	public boolean deleteSach(String id);
	public String generateMaSach();
	public boolean kiemTraMaSach(String code) ;
	public boolean updateSachNgonNguMoTa(String maSach, String ngonNgu, String moTa);
	public List<Sach> layDanhSachTheoMaSach(String maSach);
}
