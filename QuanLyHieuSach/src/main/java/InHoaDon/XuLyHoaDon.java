package InHoaDon;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class XuLyHoaDon {

    private static XuLyHoaDon instance;
    private JasperReport reportPay;
    

    public static XuLyHoaDon getInstance() {
        if (instance == null) {
            instance = new XuLyHoaDon();
        }
        return instance;
    }
    
    private XuLyHoaDon() {
    }
    
    public void compileReport() throws JRException {
        reportPay = JasperCompileManager.compileReport(getClass().getResourceAsStream("/InHoaDon/xmlHoaDon.jrxml"));  
    }
    
    public void printReportPayment(ParameterHoaDon data) throws JRException {
        Map<String, Object> para = new HashMap<String, Object>();
        para.put("maHoaDon", data.getMaHoaDon());
        para.put("tenNhanVien", data.getTenNhanVien());
        para.put("ngay", data.getNgay());
        para.put("tongTien", data.getTongTien());
        para.put("sdt", data.getSdt());
        para.put("khuyenMai", data.getKhuyenMai());
        para.put("tienBD", data.getTienBD());
        para.put("khachDua", data.getKhachDua());
        para.put("traKhach", data.getTraKhach());
        DefaultJasperReportsContext.getInstance();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getListFields());
        JasperPrint print = JasperFillManager.fillReport(reportPay, para, dataSource);
        view(print);
    }
    
    private void view(JasperPrint print) throws JRException {
        JasperViewer.viewReport(print, false);
    }
}
