package DAO;

import java.util.ArrayList;

import entity.NhanVien;

public interface NhanVien_DAO {
	
	
	public boolean capNhatNhanVien(NhanVien nv);
	public NhanVien layThongTinNhanVien(NhanVien  nv);
	public ArrayList<NhanVien> layDanhSachNhanVien();
	public ArrayList<NhanVien> timKiemDanhSachTaiKhoanTheoTenDangNhap(String TenDangNhap);
	
	public String generateVerifyCode();
	public boolean checkDuplicateCode(String code);
	
	public boolean InsertNhanVien(NhanVien nv);
	public boolean capNhatTrangThaiNhanVien(String maNV,String trangThai);
	public NhanVien timKiemNhanVienTheoMaNV(String maNV);
	public NhanVien timKiemNhanVienTheoEmail(String email);
	public boolean checkDuplicateEmail(String Email,String trangThai);
	public ArrayList<NhanVien> dsNhanVienTheoTrangThai(String trangThai);
}
