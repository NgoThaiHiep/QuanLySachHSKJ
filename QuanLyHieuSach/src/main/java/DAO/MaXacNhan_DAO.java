package DAO;

import entity.MaXacNhan;

public interface MaXacNhan_DAO {
	public String generateVerifyCode();
	public boolean checkDuplicateCode(String code);

	public boolean themMaXacNhan(MaXacNhan mxn);
	public boolean xoaMaXacNhan(String taiKhoan);
	public MaXacNhan layMaXacNhan(String taiKhoan);
}
