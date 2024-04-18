package DAO;

import entity.KhachHang;

public interface KhachHang_DAO {
	public KhachHang layThongTinKhachHang(String sdt);
	public KhachHang layThongTinKhachHang_TheoMaKH(String maKH);
	public String generateVerifyCodeKH();
	public String generateVerifyCode_KhachHangLe();
	public boolean checkDuplicateCode(String code);
	public boolean InsertKhachHang(KhachHang kh);
	public boolean updateDiemTichLuy(KhachHang kh);
}
