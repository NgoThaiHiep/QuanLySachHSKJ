
package InHoaDon;

public class FieldHoaDon {
    String tenSP;
    int soLuong;
    double giaBan;
    double tongTienSP;
    
    public FieldHoaDon() {
    }

    public FieldHoaDon(String tenSP, int soLuong, double giaBan, double tongTienSP) {
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.tongTienSP = tongTienSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public double getTongTienSP() {
        return tongTienSP;
    }

    public void setTongTienSP(double tongTienSP) {
        this.tongTienSP = tongTienSP;
    }

   
    
}
