
package InHoaDon;


import java.util.List;

public class ParameterHoaDon {
    private String tenNhanVien;
    private String maHoaDon;
    private String tongTien;
    private String ngay;
    private String sdt;
    private String tienBD;
    private String khuyenMai;
    private String khachDua;
    private String traKhach;
    List<FieldHoaDon> listFields;
    
    public ParameterHoaDon() {

    }

    public ParameterHoaDon(String tenNhanVien, String maHoaDon, String tongTien, String ngay, String sdt, String tienBD, String khuyenMai, String khachDua, String traKhach, List<FieldHoaDon> listFields) {
        this.tenNhanVien = tenNhanVien;
        this.maHoaDon = maHoaDon;
        this.tongTien = tongTien;
        this.ngay = ngay;
        this.sdt = sdt;
        this.tienBD = tienBD;
        this.khuyenMai = khuyenMai;
        this.khachDua = khachDua;
        this.traKhach = traKhach;
        this.listFields = listFields;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTienBD() {
        return tienBD;
    }

    public void setTienBD(String tienBD) {
        this.tienBD = tienBD;
    }

    public String getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(String khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public String getKhachDua() {
        return khachDua;
    }

    public void setKhachDua(String khachDua) {
        this.khachDua = khachDua;
    }

    public String getTraKhach() {
        return traKhach;
    }

    public void setTraKhach(String traKhach) {
        this.traKhach = traKhach;
    }

    public List<FieldHoaDon> getListFields() {
        return listFields;
    }

    public void setListFields(List<FieldHoaDon> listFields) {
        this.listFields = listFields;
    }

    
    
}
