package DAO;

import java.util.ArrayList;
import entity.ChucVu;

public interface ChucVu_DAO {
	public boolean themChucVu(ChucVu chucVu);
	public boolean suaChucVu(ChucVu chucVu);
	public boolean xoaChucVu(String maChucVu);
    public ArrayList<ChucVu> getDSChucVu();
   
}
