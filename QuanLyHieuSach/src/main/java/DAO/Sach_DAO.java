package DAO;


import entity.Sach;

public interface Sach_DAO {


	public Sach layThongTinSach(String tenSach);

	public boolean insertSach(Sach s);

	public boolean updateSach(Sach s);

	public boolean deleteSach(String id);
	
	public String generateMaSach();
	
	public boolean kiemTraMaSach(String code) ;
	
	
	
	public boolean updateSachNgonNguMoTa(String maSach, String ngonNgu, String moTa);
	
	
	

}
