package DAO;

import java.util.ArrayList;

import entity.NhanVien;
import entity.TaiKhoan;

public interface TaiKhoan_DAO {
	public ArrayList<TaiKhoan> layDanhSachTaiKhoan();
	public TaiKhoan layThongTinTaiKhoan(String tenTK);
	public boolean login(TaiKhoan tk);
	public boolean login_DaDangNhap(TaiKhoan tk);
	public boolean inserTaiKhoan(TaiKhoan tk) ;
//	public String generateVerifyCode();
//	public boolean checkDuplicateCode(String code);
//	public boolean checkDuplicateEmail(String Email);
//	public boolean CheckEmailLost(NhanVien nv,TaiKhoan tk);
//	public boolean updateMaXacNhanLost(TaiKhoan tk);
	public boolean upDateMatKhau(NhanVien nhanVien, String matKhauCu, String matKhauMoi);
	public boolean updataPasswordLost(String taiKhoan, String matKhauMoi, String trangThai);
	public boolean updataTinhTrangDangNhap(String taiKhoan, String trangThai);
	public boolean xoataikhoan(String id);
}
