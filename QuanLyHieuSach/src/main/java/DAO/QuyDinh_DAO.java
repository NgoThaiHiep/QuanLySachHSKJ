package DAO;

import java.util.List;

import entity.QuyDinh;

public interface QuyDinh_DAO {
	public List<QuyDinh> layDuLieuQuyDinh();
	public void capNhatQuyDinh(QuyDinh qd);
	public boolean InsertQuyDinh(int soLuongToiThieu, int soLuongToiDa, float VAT);
	
}
