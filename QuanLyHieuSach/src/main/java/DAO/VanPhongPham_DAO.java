package DAO;



import java.util.ArrayList;
import java.util.List;

import entity.Sach;
import entity.VanPhongPham;

public interface VanPhongPham_DAO {
	
	public Sach layThongTinSach(String tenSach);

	public boolean insertVanPhongPham(VanPhongPham vpp);

	public boolean updateVanPhongPham(VanPhongPham vpp);

	public boolean deleteVanPhongPham(String id);
	
	public String generateMaVanPhongPham();
	
	public boolean kiemTraMaVanPhongPham(String code) ;
	public List<VanPhongPham> layDanhSanPhamVanPhongPham_TheoMa(String maVanPhongPham);
	public List<VanPhongPham> layDanhSanPhamVanPhongPham();
}
