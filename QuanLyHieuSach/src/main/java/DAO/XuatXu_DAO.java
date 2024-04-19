package DAO;

import java.util.ArrayList;

import entity.XuatXu;

public interface XuatXu_DAO {
	 public ArrayList<XuatXu> layDanhSachXuatXu();
	    public String generateXuatXu();
	    public boolean kiemTraMaXuatXu(String code);
	    public boolean InsertXuatXu(XuatXu xuatXu);
}
