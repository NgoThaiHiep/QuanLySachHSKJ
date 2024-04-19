package DAO;

import java.util.ArrayList;
import entity.ThuongHieu;

public interface ThuongHieu_DAO {
    public ArrayList<ThuongHieu> layDanhSachThuongHieu();
    public String generateThuongHieu();
    public boolean kiemTraMaThuongHieu(String code);
    public boolean InsertThuongHieu(ThuongHieu thuongHieu);
}
