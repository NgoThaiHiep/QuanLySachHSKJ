package DAO;


import entity.QuyDinh;

public interface QuyDinh_DAO {
	public QuyDinh layDuLieuQuyDinh();
	public void capNhatQuyDinh(QuyDinh qd);
	public boolean InsertQuyDinh(int soLuongToiThieu, int soLuongToiDa, float VAT);
	
}
