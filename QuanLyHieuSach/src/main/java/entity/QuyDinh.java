
package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
@NamedQueries({
	@NamedQuery(name = "QuyDinh.findAll", query = "SELECT * FROM QuyDinh qd")
})

@Entity
@Table(name = "QuyDinh")

public class QuyDinh {
	@Id
	@Column(name = "SoLuongToiThieu")
    private int soLuongToiThieu;
	@Column(name = "SoLuongToiDa")
    private int soLuongToiDa;
    private float VAT;

    public QuyDinh(int soLuongToiThieu, int soLuongToiDa, float VAT) {
        this.soLuongToiThieu = soLuongToiThieu;
        this.soLuongToiDa = soLuongToiDa;
        this.VAT = VAT;
    }

    public QuyDinh() {
    }

    public int getSoLuongToiThieu() {
        return soLuongToiThieu;
    }

    public void setSoLuongToiThieu(int soLuongToiThieu) {
        this.soLuongToiThieu = soLuongToiThieu;
    }

    public int getSoLuongToiDa() {
        return soLuongToiDa;
    }

    public void setSoLuongToiDa(int soLuongToiDa) {
        this.soLuongToiDa = soLuongToiDa;
    }

    public float getVAT() {
        return VAT;
    }

    public void setVAT(float VAT) {
        this.VAT = VAT;
    }

	@Override
	public String toString() {
		return "QuyDinh [soLuongToiThieu=" + soLuongToiThieu + ", soLuongToiDa=" + soLuongToiDa + ", VAT=" + VAT + "]";
	}
    
   
}
