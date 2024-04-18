package DAO;

import java.util.List;

import entity.ChucVu;

public interface ChucVu_DAO {
	public boolean themChucVu(ChucVu chucVu);
	public boolean suaChucVu(ChucVu chucVu);
	public boolean xoaChucVu(String maChucVu);
    public List<ChucVu> getDSChucVu();
   
}
