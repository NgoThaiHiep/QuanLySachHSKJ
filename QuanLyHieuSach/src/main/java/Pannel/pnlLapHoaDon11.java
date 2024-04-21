package Pannel;

import DAO.ChiTietHoaDon_DAO;
import DAO.HangCho_DAO;
import DAO.HoaDon_DAO;
import DAO.KhachHang_DAO;
import DAO.KhuyenMaiThanhToan_DAO;
import DAO.QuyDinh_DAO;
import DAO.Sach_DAO;
import DAO.SanPham_DAO;
import DAO.VanPhongPham_DAO;
import DAO_IMP.ChiTietHoaDonDAO_IMP;
import DAO_IMP.HangChoDAO_IMP;
import DAO_IMP.HoaDonDAO_IMP;
import DAO_IMP.KhachHangDAO_IMP;
import DAO_IMP.KhuyenMaiThanhToanDAO_IMP;
import DAO_IMP.QuyDinhDAO_IMP;
import DAO_IMP.SachDAO_IMP;
import DAO_IMP.SanPhamDAO_IMP;
import entity.ChiTietHoaDon;
import entity.HangCho;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMaiThanhToan;
import entity.NhanVien;
import entity.QuyDinh;
import entity.Sach;
import entity.SanPham;
import entity.TaiKhoan;
import entity.VanPhongPham;
import InHoaDon.FieldHoaDon;
import InHoaDon.ParameterHoaDon;
import InHoaDon.XuLyHoaDon;
import Pannel.pnlLapHoaDon.frmHangCho;
import Scenner.QRCode_Dialog;
import ServiceUser.ButtonCustom;
import ServiceUser.ScrollBarCustom;
import ServiceUser.TableActionCellEditor;
import ServiceUser.TableActionCellRender;
import ServiceUser.TableActionEvent;
import java.awt.Font;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.format.DateTimeFormatter;
import UI.TrangChu;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import net.sf.jasperreports.engine.JRException;



/**
 *
 * @author FPTSHOP
 */
public class pnlLapHoaDon11 extends javax.swing.JPanel {

    /**
     * Creates new form TaoHoaDon
     */
	private static final Gson Gson = new Gson();
    private TaiKhoan tk;
    private NhanVien nv;
    private HoaDon_DAO hoaDon_DAO;
    private TrangChu trangChu;
    private LookAndFeel originalLookAndFeel;
    private Sach_DAO sach_DAO;
    private VanPhongPham_DAO vanPhongPham_DAO;
    private KhachHang_DAO khachHang_DAO;
    private ChiTietHoaDon_DAO chiTietHoaDon_DAO;
    private HangCho_DAO hangCho_DAO;
    private KhuyenMaiThanhToan_DAO khuyenMaiThanhToan;
    private KhuyenMaiThanhToan_DAO khuyenMaiThanhToan_DAO;
    private Timer timer;
    private QuyDinh_DAO quyDinh_DAO;
    
    //-------------------------//
    public pnlLapHoaDon11(TaiKhoan tk,NhanVien nv, LookAndFeel originalLookAndFeel) {
        this.nv = nv;
        this.tk = tk;
        this.originalLookAndFeel = originalLookAndFeel;
        initComponents();
        addTableStyle(scrGioHang);
      
        
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onThem(int row) {
                editThemSanPhamOnTable(row, 1);
            }

            @Override
            public void onDelete(int row) {
                if(tblGioHang.isEditing()){
                    tblGioHang.getCellEditor().stopCellEditing();
                }
            btnXoaSanPham();
            }
            @Override
            public void onTru(int row) {
                editTruSanPhamOnTable(row, 1);
            }
        };
        tblGioHang.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
        tblGioHang.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(event));
	hoaDon_DAO = new HoaDonDAO_IMP();
        
	String formattedDate = null;
        formattedDate = hoaDon_DAO.generateHoaDon(nv);
        lblMaHoaDonFont.setText(formattedDate);
        lblMaHoaDonFont.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 15)); 
        
        Date currentDate = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	String formattedDate2 = formatter.format(currentDate);
        
        lblNgayLapHoaDonFont.setText(formattedDate2);
        lblNgayLapHoaDonFont.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 15)); 
        
        lblTenNhanVienFont.setText(nv.getHoTenNhanVien());
        lblTenNhanVienFont.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 15)); // Chú ý: sử dụng Font.BOLD

       // capNhatDanhSanPham();
       
        
      //  kiemTraDuLieuFloat(txtTienKhachDua);
        kiemTraSoTienTraLai();
        capNhatDanhSach_TheoMa_TimKiem(txtTimKiemMaSanPham);
        
        quyDinh_DAO = new QuyDinhDAO_IMP();
        QuyDinh qd = new QuyDinh();
        List<QuyDinh> quyDinh = quyDinh_DAO.layDuLieuQuyDinh();

        lblTongTienThanhToan.setText("Tổng tiền thanh toán (đã gồm VAT "+ qd.getVAT() + " %) :");
        kiemTraDuLieuFloat( txtTienKhachDua);
        kiemTraDuLieuFloat(txtSoLuong);
        theoDoiThayDoiTien();
        sach_DAO = new SachDAO_IMP();
        phimTatTienKhachDua(txtTienKhachDua,lblTongTienThanhToanKyTu);
        anHienSuDungDiem(false);
        
        duLieuSuDungDiem(txtSuDungDiem,txtDiemTichLuy,lblTongTien1);
        duLieuSDT();
        phimTatDiemSuDung(txtSuDungDiem,txtDiemTichLuy,lblTongTien1);
        phimTatChoButton(btnThanhToan, "ctrl B",() -> {
            thanhToan();
        });
        phimTatChoButton(btnThemSanPham, "F1",() -> {
            btnThemSuaSanPham();
        });
        phimTatChoButton(btnXoaSanPham, "F2",() -> {
            btnXoaSanPham();
        });
        phimTatChoButton(btnThemVaoHangCho, "F3",() -> {
            btnThemVaoHangCho();
        });
        phimTatChoButton(btnThemSanPham, "F4",() -> {
            lamMoiDuLieuThanhToan();
        });
        
        //scan
        scanImei = new ButtonCustom("Quét imei", "success", 13);
        
    }
    
    private void phimTatTienKhachDua(JTextField txt,JLabel lbl){
       txt.getInputMap().put(KeyStroke.getKeyStroke("ctrl P"),"total payment");
       txt.getActionMap().put("total payment", new AbstractAction() {
           @Override
           public void actionPerformed(ActionEvent e) {
                String priceWithoutCurrency = lbl.getText().replaceAll("[^\\d.]+", "");
                txt.setText(priceWithoutCurrency);
           }
       });
    }
    
    private void phimTatDiemSuDung(JTextField txt,JTextField txt1,JLabel lbl){
         
       txt.getInputMap().put(KeyStroke.getKeyStroke("ctrl D"),"point of use");
       txt.getActionMap().put("point of use", new AbstractAction() {
           @Override
           public void actionPerformed(ActionEvent e) {
                       
                        int x = Integer.parseInt( txt1.getText());
                        String strDiemTichLuy = lbl.getText().replaceAll("[^\\d.]+", "");
                        int diemSuDung = Integer.parseInt(strDiemTichLuy)/ 2000;
                        
                       
                        if(x > diemSuDung){
                            txt.setText(String.valueOf(diemSuDung));  
                                khauTru();
                                            
                    
                        }else {
                                txt.setText(String.valueOf(x));  
                                khauTru();
                        }                
            }
           
        });
    }
    
    private static void phimTatChoButton(AbstractButton button, String shortCutKey,Runnable action){
        int buttonMask = JComponent.WHEN_IN_FOCUSED_WINDOW;
        
        KeyStroke keyStroke = KeyStroke.getKeyStroke(shortCutKey);
        button.getInputMap(buttonMask).put(keyStroke, shortCutKey);
        button.getActionMap().put(shortCutKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        });
        
        
    }
    
    private void theoDoiThayDoiTien(){
       PropertyChangeListener listener = (PropertyChangeEvent evt) -> {
           if ("text".equals(evt.getPropertyName())) {
               
               
               khuyenMaiThanhToan();
               
               
           }
       };
        // Lắng nghe sự kiện thay đổi
        lblTongTien1.addPropertyChangeListener(listener);
    
    
}
    
    private void inHoaDon() throws JRException {
        XuLyHoaDon.getInstance().compileReport();
        if (tblGioHang.getRowCount() == 0) {
        } else {
            try {
                    List<FieldHoaDon> fields = new ArrayList<>();
                    for (int i = 0; i < tblGioHang.getRowCount(); i++) {
                    // Kiểm tra và chuyển đổi kiểu dữ liệu một cách an toàn
                    String tenSP = String.valueOf(tblGioHang.getValueAt(i, 2));
                        Object soLuongObj = tblGioHang.getValueAt(i, 3);
                        System.out.println(soLuongObj+" ");
                    int soLuong = Integer.parseInt(soLuongObj+"");

                    // Kiểm tra và chuyển đổi kiểu dữ liệu cho giaBan
                    Object giaBanObj = tblGioHang.getValueAt(i, 4);
                    
                        System.out.println(giaBanObj+" ");
                    double giaBan = Double.parseDouble(giaBanObj+"");

                    // Kiểm tra và chuyển đổi kiểu dữ liệu cho soLuong
                   
                    // Kiểm tra và chuyển đổi kiểu dữ liệu cho tongTienSP
                    Object tongTienSPObj = tblGioHang.getValueAt(i, 5);
                    System.out.println(tongTienSPObj+" ");
                    double tongTienSP = Double.parseDouble(tongTienSPObj+"");

                     fields.add(new FieldHoaDon(tenSP, soLuong, giaBan, tongTienSP));
                }
                String tenNhanVien = lblTenNhanVienFont.getText();
                String maHoaDon = lblMaHoaDonFont.getText();
                String tienThanhToan = lblTongTienThanhToanKyTu.getText();
                LocalDate ngay = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String ngayTao = ngay.format(formatter);
                String sdt = txtSoDienThoai.getText();
                if(sdt.length()!=0){
                    sdt = txtSoDienThoai.getText();
                }else{
                    sdt = "Khách Lẻ";
                }
                String tienBD = lblTongTien1.getText();
                String khachDua = txtTienKhachDua.getText();
                if(khachDua.length()!=0){
                    khachDua = txtTienKhachDua.getText() +" "+"VND";
                }else{
                    khachDua = tienBD;
                }
                String khuyenMai = lblKm.getText();
                if (khuyenMai.equals("")){
                    khuyenMai = "0 VND";
                }else{
                    khuyenMai = lblKm.getText();
                }
                String traKhach ;
                if(khachDua.equals("")){
                    traKhach = "0 VND";
                }else{
                    traKhach = lblTienTraLaiKyTu.getText();
                }
                ParameterHoaDon dataprint = new ParameterHoaDon(tenNhanVien, maHoaDon, tienThanhToan, ngayTao, sdt, tienBD, khuyenMai, khachDua, traKhach, fields);
                XuLyHoaDon.getInstance().printReportPayment(dataprint);
            } catch (NumberFormatException | JRException e) {
                System.out.println(e);
            }
        }
    }
    
    public void editThemSanPhamOnTable(int row, int soLuong){
                     DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
                    // Cập nhật số lượng và thành tiền
                    int currentSoLuong = Integer.parseInt(model.getValueAt(row, 3).toString());
                    double currentThanhTien = Double.parseDouble(model.getValueAt(row, 5).toString());
                    int newSoLuong = currentSoLuong + soLuong;
                   
                    int soLuongTon =  soLuongToiDaDeThem(model.getValueAt(row, 1).toString());       
                    
                    
                    if(newSoLuong > soLuongTon ){
                        if(soLuongTon == currentSoLuong)
                            
                             JOptionPane.showMessageDialog(this, "Sản phẩm đã thêm số lượng tối đa");
                        else 
                            JOptionPane.showMessageDialog(this, "Số lượng thêm lớn hơn số lượng tồn");                            
                    }else{
                    double newThanhTien = currentThanhTien + Double.parseDouble( model.getValueAt(row, 4).toString());
                    model.setValueAt(newSoLuong, row, 3);
                    model.setValueAt(newThanhTien, row, 5);
                    tblGioHang.setModel(model);
                    
                    DecimalFormat decimalFormat = new DecimalFormat("#,###");
                    String formattedTongTien = decimalFormat.format(tongTien());
                   if(tongTien() == 0 ){
                        lblTongTien1.setText("0"+formattedTongTien+" VND");
                    }else
                        lblTongTien1.setText(formattedTongTien+" VND");
                   }
    }
    
    public void editTruSanPhamOnTable(int row, int soLuong){
          DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
                    // Cập nhật số lượng và thành tiền
                    int currentSoLuong = Integer.parseInt(model.getValueAt(row, 3).toString());
                    double currentThanhTien = Double.parseDouble(model.getValueAt(row, 5).toString());
                    
                    int newSoLuong = currentSoLuong - soLuong;
                    if(newSoLuong == 0){
                         if(tblGioHang.isEditing()){
                            tblGioHang.getCellEditor().stopCellEditing();
                        }
                    btnXoaSanPham();
                    }else{ 
                    double newThanhTien = currentThanhTien - Double.parseDouble( model.getValueAt(row, 4).toString());
                    model.setValueAt(newSoLuong, row, 3);
                    model.setValueAt(newThanhTien, row, 5);
                    
                    
                     }
                    tblGioHang.setModel(model);
                    
                    DecimalFormat decimalFormat = new DecimalFormat("#,###");
                    String formattedTongTien = decimalFormat.format(tongTien());
                    if(tongTien() == 0.0 ){
                        lblTongTien1.setText(formattedTongTien+".0 VND");
                    }else
                        lblTongTien1.setText(formattedTongTien+" VND");
    }
    
    private void kiemTraDuLieuFloat(JTextField textField){
        DecimalFormat df = new DecimalFormat("#,###");     
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String text = textField.getText().replaceAll(",", "");
                int dotIndex = text.indexOf('.');
                if ((c < '0' || c > '9') && c != '.') { // Chỉ cho phép nhập số và dấu chấm
                    e.consume();
                } else if (c == '0' && text.isEmpty()) { // Số đầu tiên không được là 0
                    e.consume();
                } else if (c == '.' && (text.isEmpty() || dotIndex != -1)) { // Dấu chấm không được là ký tự đầu tiên và chỉ được nhập một lần
                    e.consume();
                } else if (dotIndex != -1 && text.substring(dotIndex).length() > 3 && textField.getCaretPosition() > dotIndex) { // Sau dấu chấm chỉ cho phép nhập tối đa 3 số
                    e.consume();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                String text = textField.getText().replaceAll(",", "");
                if (!text.isEmpty() && !text.contains(".")) {
                    try {
                        long number = Long.parseLong(text);
                        textField.setText(df.format(number));
                    } catch (NumberFormatException ex) {
                        // Không xử lý ngoại lệ này vì nó sẽ không xảy ra do kiểm tra nhập liệu ở trên
                    }
                }
            }
        });
    }

    private void kiemTraSoTienTraLai(){
       
        DecimalFormat df = new DecimalFormat("#,###");

            txtTienKhachDua.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String lblTongTien = lblTongTienThanhToanKyTu.getText().replaceAll("[^\\d.]+", "");
                double tongTien = Double.parseDouble(lblTongTien);
                
                String txtTien = txtTienKhachDua.getText().replaceAll("[^\\d.]+", "");
                double tienKhachDua = Double.parseDouble(txtTien);
                double tien = tienKhachDua - tongTien ;
                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                String formattedTongTien = decimalFormat.format(tien);
                
                if( tien >= 0 ){
                    txtTienKhachDua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
                    lblTienTraLaiKyTu.setText(formattedTongTien+" VND");
                    if(tien==0){
                        lblTienTraLaiKyTu.setText("0 VND");
                    }
                }else{
                    LineBorder redLineBorder = new LineBorder(Color.RED);
                    txtTienKhachDua.setBorder(redLineBorder);
                }
                    
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                    
                String lenght =txtTienKhachDua.getText(); 
                if(lenght.length()==0){
                    lblTienTraLaiKyTu.setText("0.0 VND");
                }               
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
               
                    
            }
       
        });
    }
    
    private void khuyenMaiThanhToan(){
            // Tách chuỗi thành hai phần sử dụng dấu chấm làm điểm chia
            String tongTien = tongTien()+"";
            String priceWithoutCurrency = tongTien.replaceAll("[^\\d.]+", "");
            String[] mangChuoi = priceWithoutCurrency.split("\\.");
            // Lấy phần trước và sau dấu chấm
            String phanTruocDauCham = mangChuoi[0];
            
            int tien = Integer.parseInt(phanTruocDauCham );
            cboKhuyenMai.removeAllItems();
            cboKhuyenMai.addItem("Không áp mã");
            khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToanDAO_IMP();
            ArrayList<KhuyenMaiThanhToan> dsKhuyenMai_GiaTien = khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_GiaTien();
            for (KhuyenMaiThanhToan khuyenMaiThanhToan1 : dsKhuyenMai_GiaTien) {
                if(tien >= khuyenMaiThanhToan1.getGiaTriToiThieuDonHang() && khuyenMaiThanhToan1.getTinhTrang().equals("Đang khuyến mãi") && khuyenMaiThanhToan1.getSoLuong() > 0){    
                    cboKhuyenMai.addItem(khuyenMaiThanhToan1.getMaKhuyenMai());
                }
            }
            ArrayList<KhuyenMaiThanhToan> dsKhuyenMai_TyLe = khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_TyLe();
              for (KhuyenMaiThanhToan khuyenMaiThanhToan1 : dsKhuyenMai_TyLe) {
                if(tien >= khuyenMaiThanhToan1.getGiaTriToiThieuDonHang() && khuyenMaiThanhToan1.getTinhTrang().equals("Đang khuyến mãi")&& khuyenMaiThanhToan1.getSoLuong() > 0){
                    cboKhuyenMai.addItem(khuyenMaiThanhToan1.getMaKhuyenMai());
                }
            }
           
            cboKhuyenMai.setSelectedItem("Không áp mã");
}
    
    private void anHienSuDungDiem(boolean  b){
        txtSuDungDiem.setVisible(b);
        cbkSuDungDiem.setVisible(b);
        if(b==false){
            cbkSuDungDiem.setSelected(false);
            txtSuDungDiem.setEnabled(false);
            txtSuDungDiem.setText("");                 
        }else{
            if(cbkSuDungDiem.isSelected()){  
                  txtSuDungDiem.setEnabled(true);
            }else{
                txtSuDungDiem.setEnabled(false);
                txtSuDungDiem.setText("");
            }
        }
    }   
    
    private boolean shouldReactToChanges = true;
	private SanPham_DAO sanPham_DAO;
	private SanPham sanPham;

    private void updateValue(JTextField txt, JTextField txt1, JLabel lbl) {
    SwingUtilities.invokeLater(() -> {
        try {
            // Disable the document listener temporarily
            shouldReactToChanges = false;

            if (!txt.getText().equals("")) {
                int enteredValue = Integer.parseInt(txt.getText());
                int x = Integer.parseInt(txt1.getText());
                String strDiemTichLuy = lbl.getText().replaceAll("[^\\d.]+", "");
                int diemSuDung = Integer.parseInt(strDiemTichLuy) / 2000;

                int v = findMinNumber(enteredValue, x, diemSuDung);

                v = Math.max(v, 10);

               
                // Update the text without triggering the listener
                txt.setText(String.valueOf(v));
                khauTru();
            }else{
                khauTru();
            }
        } catch (NumberFormatException ex) {
            // Handle the case where the text is not a valid integer
            // (you may want to display an error message or take other action)
        } finally {
            // Enable the document listener back
            shouldReactToChanges = true;
        }
    });
}

    private static int findMinNumber(int a, int b, int c) {
        int min = a;

        if (b < min) {
            min = b;
        }

        if (c < min) {
            min = c;
        }

        return min;
    } 
    
    private void duLieuSuDungDiem(JTextField txt,JTextField txt1,JLabel lbl){
        txt.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                 if (shouldReactToChanges) 
                    updateValue(txt,txt1,lbl);
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (shouldReactToChanges)
                    updateValue(txt,txt1,lbl);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (shouldReactToChanges)
                    updateValue(txt,txt1,lbl);
            }
        });
    }
    
    private int  soLuongToiDaDeThem(String maSanPham){
    	   int soLuongTon = 0 ;
           sanPham_DAO = new SanPhamDAO_IMP();
           String json = Gson.toJson(sanPham_DAO.layDanhSachSanPham());
           JsonArray jsonArray = Gson.fromJson(json, JsonArray.class);
           for (JsonElement jsonElement : jsonArray) {
              soLuongTon = jsonElement.getAsJsonObject().get("soLuongTon").getAsInt();
           }
           return soLuongTon;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtGia = new javax.swing.JTextField();
        lblKm = new javax.swing.JLabel();
        pnlHoaDon = new javax.swing.JPanel();
        pnlTacVu2 = new javax.swing.JPanel();
        btnLamMoi2 = new javax.swing.JButton();
        btnThemSanPham = new javax.swing.JButton();
        btnXoaSanPham = new javax.swing.JButton();
        btnThemVaoHangCho = new javax.swing.JButton();
        btnDanhSachHangCho = new javax.swing.JButton();
        scanImei = new ServiceUser.ButtonCustom();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaImei = new javax.swing.JTextArea();
        pnlGioHang = new javax.swing.JPanel();
        scrGioHang = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        pnlThongTinHoaDon = new javax.swing.JPanel();
        lblNgayLapHoaDon = new javax.swing.JLabel();
        lblMaHoaDon = new javax.swing.JLabel();
        lblTenNhanVien = new javax.swing.JLabel();
        lblNgayLapHoaDonFont = new javax.swing.JLabel();
        lblMaHoaDonFont = new javax.swing.JLabel();
        lblTenNhanVienFont = new javax.swing.JLabel();
        pnlThongTinSanPham = new javax.swing.JPanel();
        lblMaSanPham = new javax.swing.JLabel();
        lblTenSanPham = new javax.swing.JLabel();
        lblGiaBan = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        txtTenSanPham = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        pnlTimKiemSanPham = new javax.swing.JPanel();
        lblTimKiemMaSanPham = new javax.swing.JLabel();
        txtTimKiemMaSanPham = new javax.swing.JTextField();
        lblThongTinKhachHang = new javax.swing.JPanel();
        lblMaKhachHang = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        lblTenKhachHang = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        txtDiemTichLuy = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtTenKhachHang = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblTienKhachDua = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        lblTienTraLaiKyTu = new javax.swing.JLabel();
        lblTienTraLai = new javax.swing.JLabel();
        cbkSuDungDiem = new javax.swing.JCheckBox();
        txtSuDungDiem = new javax.swing.JTextField();
        lblTongTienThanhToan = new javax.swing.JLabel();
        lblTongTienThanhToanKyTu = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboHinhThucThanhToan = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cboKhuyenMai = new javax.swing.JComboBox<>();
        lblTongTien1 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblSoTienGiam = new javax.swing.JLabel();
        lblChiecKhauKyTu = new javax.swing.JLabel();
        lblTieuDeLapHoaDon = new javax.swing.JLabel();

        txtGia.setText("jTextField1");

        lblKm.setText("jLabel2");

        setPreferredSize(new java.awt.Dimension(1300, 700));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        pnlHoaDon.setPreferredSize(new java.awt.Dimension(1325, 787));
        pnlHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTacVu2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tác vụ"));
        pnlTacVu2.setPreferredSize(new java.awt.Dimension(528, 400));
        pnlTacVu2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLamMoi2.setText("Làm mới (F4)");
        btnLamMoi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi2ActionPerformed(evt);
            }
        });
        pnlTacVu2.add(btnLamMoi2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 160, -1));

        btnThemSanPham.setText("Thêm sản phẩm (F1)");
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });
        pnlTacVu2.add(btnThemSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        btnXoaSanPham.setText("Xóa sản phẩm  (F2)");
        btnXoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSanPhamActionPerformed(evt);
            }
        });
        pnlTacVu2.add(btnXoaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 140, -1));

        btnThemVaoHangCho.setText("Thêm vào hàng chờ (F3)");
        btnThemVaoHangCho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVaoHangChoActionPerformed(evt);
            }
        });
        pnlTacVu2.add(btnThemVaoHangCho, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        btnDanhSachHangCho.setText("Danh sách hàng chờ");
        btnDanhSachHangCho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhSachHangChoActionPerformed(evt);
            }
        });
        pnlTacVu2.add(btnDanhSachHangCho, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 140, -1));

        scanImei.setText("a");
        scanImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scanImeiActionPerformed(evt);
            }
        });
        pnlTacVu2.add(scanImei, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 120, -1));

        textAreaImei.setColumns(20);
        textAreaImei.setRows(5);
        jScrollPane1.setViewportView(textAreaImei);

        pnlTacVu2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 170, 30));

        pnlHoaDon.add(pnlTacVu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 170, 550, 110));

        pnlGioHang.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        scrGioHang.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGioHang.setRowHeight(40);
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        scrGioHang.setViewportView(tblGioHang);

        javax.swing.GroupLayout pnlGioHangLayout = new javax.swing.GroupLayout(pnlGioHang);
        pnlGioHang.setLayout(pnlGioHangLayout);
        pnlGioHangLayout.setHorizontalGroup(
            pnlGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrGioHang)
                .addContainerGap())
        );
        pnlGioHangLayout.setVerticalGroup(
            pnlGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioHangLayout.createSequentialGroup()
                .addComponent(scrGioHang, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlHoaDon.add(pnlGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 700, 330));

        pnlThongTinHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin hóa đơn"));
        pnlThongTinHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNgayLapHoaDon.setText("Ngày lập hóa đơn :");
        pnlThongTinHoaDon.add(lblNgayLapHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 110, 22));

        lblMaHoaDon.setText("Mã hóa đơn :");
        pnlThongTinHoaDon.add(lblMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 80, 22));

        lblTenNhanVien.setText("Tên nhân viên :");
        pnlThongTinHoaDon.add(lblTenNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 80, 20));
        pnlThongTinHoaDon.add(lblNgayLapHoaDonFont, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 100, 22));
        pnlThongTinHoaDon.add(lblMaHoaDonFont, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 150, 22));
        pnlThongTinHoaDon.add(lblTenNhanVienFont, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 150, 22));

        pnlHoaDon.add(pnlThongTinHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 700, 70));

        pnlThongTinSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));
        pnlThongTinSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMaSanPham.setText("Mã sản phẩm");
        pnlThongTinSanPham.add(lblMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 27, -1, -1));

        lblTenSanPham.setText("Tên sản phẩm");
        pnlThongTinSanPham.add(lblTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(378, 27, -1, -1));

        lblGiaBan.setText("Đơn giá");
        pnlThongTinSanPham.add(lblGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        lblSoLuong.setText("Số lượng");
        pnlThongTinSanPham.add(lblSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 74, -1));

        txtMaSanPham.setEnabled(false);
        txtMaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSanPhamActionPerformed(evt);
            }
        });
        pnlThongTinSanPham.add(txtMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 208, -1));

        txtTenSanPham.setEditable(false);
        txtTenSanPham.setEnabled(false);
        txtTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSanPhamActionPerformed(evt);
            }
        });
        pnlThongTinSanPham.add(txtTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(481, 24, 208, -1));

        txtGiaBan.setEditable(false);
        txtGiaBan.setEnabled(false);
        txtGiaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaBanActionPerformed(evt);
            }
        });
        pnlThongTinSanPham.add(txtGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 208, -1));

        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });
        pnlThongTinSanPham.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 208, -1));

        pnlHoaDon.add(pnlThongTinSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 700, 110));

        btnThanhToan.setText("Thanh toán (ctrl  + B)");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        pnlHoaDon.add(btnThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 640, -1, -1));

        pnlTimKiemSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm sản phẩm"));
        pnlTimKiemSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTimKiemMaSanPham.setText("Mã sản phẩm");
        pnlTimKiemSanPham.add(lblTimKiemMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 86, -1));

        txtTimKiemMaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemMaSanPhamActionPerformed(evt);
            }
        });
        pnlTimKiemSanPham.add(txtTimKiemMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 200, -1));

        pnlHoaDon.add(pnlTimKiemSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 700, 70));

        lblThongTinKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng"));
        lblThongTinKhachHang.setPreferredSize(new java.awt.Dimension(528, 400));
        lblThongTinKhachHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMaKhachHang.setText("Điểm tích lũy");
        lblThongTinKhachHang.add(lblMaKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, -1, -1));

        lblSoDienThoai.setText("Số điện thoại");
        lblThongTinKhachHang.add(lblSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        lblTenKhachHang.setText("Tên khách hàng");
        lblThongTinKhachHang.add(lblTenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        lblDiaChi.setText("Địa chỉ");
        lblThongTinKhachHang.add(lblDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        txtDiemTichLuy.setEditable(false);
        txtDiemTichLuy.setEnabled(false);
        txtDiemTichLuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiemTichLuyActionPerformed(evt);
            }
        });
        lblThongTinKhachHang.add(txtDiemTichLuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 150, 30));

        txtDiaChi.setEditable(false);
        txtDiaChi.setEnabled(false);
        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });
        lblThongTinKhachHang.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 102, 430, 30));

        txtSoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoDienThoaiActionPerformed(evt);
            }
        });
        lblThongTinKhachHang.add(txtSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 22, 160, 30));

        txtTenKhachHang.setEditable(false);
        txtTenKhachHang.setEnabled(false);
        txtTenKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKhachHangActionPerformed(evt);
            }
        });
        lblThongTinKhachHang.add(txtTenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 62, 430, 30));

        pnlHoaDon.add(lblThongTinKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 570, 150));

        lblTienKhachDua.setText("Tiền khách đưa (ctrl + P)   :");

        txtTienKhachDua.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTienKhachDuaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTienKhachDuaFocusLost(evt);
            }
        });

        lblTienTraLaiKyTu.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTienTraLaiKyTu.setText("0.0 VND");

        lblTienTraLai.setText("Tiền trả lại                         :");

        cbkSuDungDiem.setText("Sử dụng điểm (ctrl + D)");
        cbkSuDungDiem.setToolTipText("");
        cbkSuDungDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbkSuDungDiemActionPerformed(evt);
            }
        });

        txtSuDungDiem.setEnabled(false);
        txtSuDungDiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSuDungDiemFocusLost(evt);
            }
        });

        lblTongTienThanhToan.setText("Tổng tiền thanh toán  (đã gồm VAT  )   :");

        lblTongTienThanhToanKyTu.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTongTienThanhToanKyTu.setText("0.0 VND");

        jLabel2.setText("Hình thức thanh toán");

        cboHinhThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Ngân hàng", "Momo", "Zalo pay" }));
        cboHinhThucThanhToan.setToolTipText("");

        jLabel1.setText("Khuyến mãi                        :");

        cboKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKhuyenMaiActionPerformed(evt);
            }
        });

        lblTongTien1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTongTien1.setText("0.0 VND");

        lblTongTien.setText("Tổng tiền sản phẩm          :");

        lblSoTienGiam.setText("Chiết khấu (discount)      : ");

        lblChiecKhauKyTu.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblChiecKhauKyTu.setText("0.0 VND");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblTienTraLai, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTienTraLaiKyTu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTongTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTongTienThanhToanKyTu, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboHinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTongTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbkSuDungDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSuDungDiem))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSoTienGiam))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboKhuyenMai, 0, 220, Short.MAX_VALUE)
                            .addComponent(lblChiecKhauKyTu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTongTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoTienGiam)
                    .addComponent(lblChiecKhauKyTu, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSuDungDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbkSuDungDiem))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTongTienThanhToanKyTu, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboHinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblTienTraLai, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTienTraLaiKyTu, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pnlHoaDon.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 290, 540, 340));

        lblTieuDeLapHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblTieuDeLapHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDeLapHoaDon.setText("Lập hóa đơn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTieuDeLapHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTieuDeLapHoaDon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi2ActionPerformed
        // TODO add your handling code here:
        lamMoiDuLieuThanhToan();
        
        
    }//GEN-LAST:event_btnLamMoi2ActionPerformed

    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed
        // TODO add your handling code here:
    btnThemSuaSanPham();
       

    }//GEN-LAST:event_btnThemSanPhamActionPerformed

    private void btnThemSuaSanPham(){
         if(btnThemSanPham.getText().equals("Thêm sản phẩm (F1)")){

            themSoLuongSanPham();

        }
        else{
            capNhatSoLuongSanPham();
        }
    }
   
    private void btnXoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSanPhamActionPerformed
        // TODO add your handling code here:
        // Lấy chỉ số cột đang được chọn
        //int columnToRemove = tblGioHang.getSelectedColumn();
        //
        //// Kiểm tra xem có cột nào được chọn không
        //if (columnToRemove != -1) {
            //    // Lặp qua từng hàng và xóa dữ liệu trong cột đó
            //    DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
            //    for (int i = 0; i < model.getRowCount(); i++) {
                //        model.setValueAt(null, i, columnToRemove);
                //    }
            //
            //    // Xóa cột khỏi mô hình của bảng
            //    TableColumnModel tcm = tblGioHang.getColumnModel();
            //    TableColumn col = tcm.getColumn(columnToRemove);
            //
            //    tcm.removeColumn(row);

            btnXoaSanPham();
    }//GEN-LAST:event_btnXoaSanPhamActionPerformed

    private void btnThemVaoHangChoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVaoHangChoActionPerformed
        btnThemVaoHangCho();
    }//GEN-LAST:event_btnThemVaoHangChoActionPerformed
    
    private void btnThemVaoHangCho(){
                // TODO add your handling code here:
        //  addToPendingPayment();

        // new frmHangCho().setVisible(true);
    	KhachHang kh = new KhachHang();
        khachHang_DAO = new KhachHangDAO_IMP();
        if(txtSoDienThoai.getText().equals("")) {
		   kh.setMaKhachHang(khachHang_DAO.generateVerifyCode_KhachHangLe());
		}else{
		  kh =   khachHang_DAO.layThongTinKhachHang(txtSoDienThoai.getText());
		}
        LocalDate localDate = LocalDate.now();
        System.out.println(kh);
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();

        SanPham sanPham = null ;
        int soLuong = 0;
        double donGia = 0;
        // Duyệt qua từng hàng và cột để lấy dữ liệu\

        String maKhachHang = "";
        if(kh != null){
            maKhachHang = kh.getMaKhachHang().substring(0,3);
        }

        if(kh != null && !maKhachHang.equals("KHL")){
            if(rowCount!=0){
            	 hangCho_DAO = new HangChoDAO_IMP();
            	 hangCho_DAO.DeleteDanhSachHangCho(kh.getMaKhachHang());
                 for (int row = 0; row < rowCount; row++) {
                     for (int col = 0; col < columnCount; col++) {
                         if(col == 1){
                             Object cellValue = model.getValueAt(row, col);
                              // System.out.print("sách " + cellValue + "\t");
                             sanPham_DAO = new SanPhamDAO_IMP();
                             sanPham = sanPham_DAO.layThongTinSanPham(cellValue.toString());
                                   
                         }
                        //số lượng
                        if(col == 3){
                            Object cellValue = model.getValueAt(row, col).toString();
                            // System.out.print("sách " + cellValue + "\t");
                            soLuong = Integer.parseInt((String) cellValue);
                        }
                        //đơn giá
                        if(col == 4){
                            Object cellValue = model.getValueAt(row, col).toString();
                            //              System.out.print("sách " + cellValue + "\t");
                            donGia = Double.parseDouble((String) cellValue);
                        }
                        //thành tiền
                        if(col == 5){

                            // HoaDon hoaDon = new HoaDon(lblMaHoaDonFont.getText(), localDate, nv, kh,soTienKhachDua,giaBan,tongTienSanPham ,quyDinh.getVAT(),tienTraLai);

                        	 hoaDon_DAO = new HoaDonDAO_IMP();
                            // hoaDon_DAO.InsertHoaDon(hoaDon, nv, kh);
                            HangCho hangCho = new HangCho(kh, sanPham, soLuong, localDate);
                            System.out.println(hangCho ); // Xuống dòng sau khi duyệt qua một hàng
                            System.out.println("\n");

                            hangCho_DAO = new HangChoDAO_IMP();
                            chiTietHoaDon_DAO = new ChiTietHoaDonDAO_IMP();
                            // chiTietHoaDon_DAO.InsertCTHoaDon(hoaDon, soLuong, donGia, sanPham);
                            if(hangCho_DAO.InsertHangCho(hangCho)){
                            }
                        }
                    }
                }
                JOptionPane.showMessageDialog(this, "Đã thêm vào hàng chờ");
                model.setRowCount(0);
                lamMoiDuLieu_KhachHang();
                lamMoiDuLieu_SanPham();
                lamMoiDuLieuThanhToan();

                AbstractDocument document = (AbstractDocument) txtSoDienThoai.getDocument();

                DocumentFilter oldFilter;
                oldFilter = document.getDocumentFilter();
                document.setDocumentFilter(null);

                // Đặt lại giá trị
                txtSoDienThoai.setText("");

                // Đặt lại DocumentFilter
                document.setDocumentFilter(oldFilter);
                txtSoDienThoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
                cboKhuyenMai.setSelectedIndex(-1);
                lblTongTien1.setText("0.0 VND");
                lblTongTienThanhToanKyTu.setText("0.0 VND");
               
            }else{
                JOptionPane.showMessageDialog(this, "Không có sản phẩm để thêm vào hàng chờ");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Nhập số điện thoại để thêm vào hàng chờ");
        }

    }
    
    private void btnDanhSachHangChoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhSachHangChoActionPerformed
        // TODO add your handling code here:

        frmHangCho themHC = new frmHangCho();

        // Thiết lập loại đóng cửa sổ
        themHC.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Thêm sự kiện WindowListener để ẩn frame khi đóng
        themHC.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Ẩn frame thay vì đóng nó
                ((JFrame) windowEvent.getSource()).setVisible(false);
            }
        });

        // Hiển thị frame ThemTH
        themHC.setVisible(true);
    }//GEN-LAST:event_btnDanhSachHangChoActionPerformed

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        // TODO add your handling code here:

          int row = tblGioHang.getSelectedRow();

            // Kiểm tra xem có hàng nào được chọn không
            if (row != -1) {
                btnThemSanPham.setText("Sửa số lượng (F1)");
                txtMaSanPham.setText(tblGioHang.getValueAt(row, 1).toString());
                txtTenSanPham.setText(tblGioHang.getValueAt(row, 2).toString());
                txtGiaBan.setText(tblGioHang.getValueAt(row, 4).toString());
                txtSoLuong.setText(tblGioHang.getValueAt(row, 3).toString());
            } else {
                // Xử lý khi không có hàng nào được chọn
                // Có thể hiển thị thông báo hoặc thực hiện hành động khác tùy thuộc vào yêu cầu của bạn.
            }
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void txtMaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSanPhamActionPerformed

    private void txtTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSanPhamActionPerformed

    private void txtGiaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaBanActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

      thanhToan();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void thanhToan(){                                            
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();

        
        
        int soLuong = 0;
        double donGia = 0;
        

        Object selectedItem = cboKhuyenMai.getSelectedItem();
        if (selectedItem != null && !selectedItem.equals("Không áp mã")) {
            khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToanDAO_IMP();
            ArrayList<KhuyenMaiThanhToan> dsKhuyenMai_GiaTien = khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_GiaTien();
            for (KhuyenMaiThanhToan khuyenMaiThanhToan1 : dsKhuyenMai_GiaTien) {
                if(cboKhuyenMai.getSelectedItem().equals(khuyenMaiThanhToan1.getMaKhuyenMai())){
                    khuyenMaiThanhToan_DAO.updateSoLuongKhuyenMai(khuyenMaiThanhToan1.getMaKhuyenMai(), 1);
                }
            }

            ArrayList<KhuyenMaiThanhToan> dsKhuyenMai_TyLe = khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_TyLe();
            for (KhuyenMaiThanhToan khuyenMaiThanhToan1 : dsKhuyenMai_TyLe) {
                if(cboKhuyenMai.getSelectedItem().equals(khuyenMaiThanhToan1.getMaKhuyenMai())){
                    khuyenMaiThanhToan_DAO.updateSoLuongKhuyenMai(khuyenMaiThanhToan1.getMaKhuyenMai(), 1);
                }
            }
        }
        KhachHang kh = new KhachHang();
        khachHang_DAO = new KhachHangDAO_IMP();
        if(txtSoDienThoai.getText().equals("") ||  khachHang_DAO.layThongTinKhachHang(txtSoDienThoai.getText()) == null) {
		    kh.setMaKhachHang(khachHang_DAO.generateVerifyCode_KhachHangLe());
		    kh.setTenKhachHang("No name");
		}else{
		    kh = khachHang_DAO.layThongTinKhachHang(txtSoDienThoai.getText());
		}
        LocalDate localDate = LocalDate.now();

        int columnCount = model.getColumnCount();

        int rowCount = model.getRowCount();
        if(rowCount!=0){
            if(txtSoDienThoai.getText().equals("")) {
                int diemTichLuy = kh.getDiemTL();
                // Loại bỏ chữ cái không cần thiết từ chuỗi số
                String priceWithoutCurrency = lblTongTien1.getText().replaceAll("[^\\d.]+", "");
                double parsedNumber = Double.parseDouble(priceWithoutCurrency);
                diemTichLuy += (int) parsedNumber / 100000 ;
                kh.setDiemTL(diemTichLuy);
                khachHang_DAO.InsertKhachHang(kh);
            }
            String priceWithoutCurrency = lblTongTienThanhToanKyTu.getText().replaceAll("[^\\d.]+", "");
            double giaBan = Double.parseDouble(priceWithoutCurrency);

           
            

            
            if(cboHinhThucThanhToan.getSelectedItem().equals("Tiền mặt")){
                 
                if(validDataHoaDonTienMat()){
                    double soTienKhachDua = 0;
                    if(!txtTienKhachDua.getText().equals("")){
                        String txtSoTienKhachDua = txtTienKhachDua.getText().replaceAll("[^\\d.]+", "");
                        soTienKhachDua = Double.parseDouble(txtSoTienKhachDua);
                    }else{
                        JOptionPane.showMessageDialog(this, "Chưa điền tiền khách đưa !");
                    }
                    String txtTongTienSanPham = lblTongTien1.getText().replaceAll("[^\\d.]+", "");
                    double tongTienSanPham = Double.parseDouble(txtTongTienSanPham);

                    String txtTienTraLaiKyTu = lblTienTraLaiKyTu.getText().replaceAll("[^\\d.]+", "");
                    double tienTraLai = Double.parseDouble(txtTienTraLaiKyTu);

                    quyDinh_DAO = new QuyDinhDAO_IMP();
                    QuyDinh qd = new QuyDinh();
                    List<QuyDinh> quyDinh = quyDinh_DAO.layDuLieuQuyDinh();

                    HoaDon hoaDonTienMat = new HoaDon(lblMaHoaDonFont.getText(), localDate, nv, kh,soTienKhachDua,giaBan,tongTienSanPham ,qd.getVAT(),tienTraLai);
                    hoaDon_DAO = new HoaDonDAO_IMP();
                    hoaDon_DAO.InsertHoaDon(hoaDonTienMat);
                    
                    
                                for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < columnCount; col++) {
                	 if(col == 1){
                         Object cellValue = model.getValueAt(row, col);
                          // System.out.print("sách " + cellValue + "\t");
                                sanPham = new SanPham() {
                             	   @Override
                             	   public String getMaSanPham() {
                             		   // TODO Auto-generated method stub
                             		   return cellValue.toString();
                             	   }
                                };
                     }
                    //số lượng
                    if(col == 3){
                    	Object cellValue = model.getValueAt(row, col).toString();
                        // System.out.print("sách " + cellValue + "\t");
                           soLuong = Integer.parseInt((String) cellValue);
                           
                           sanPham_DAO = new SanPhamDAO_IMP();
                           sanPham_DAO.updateSoLuong(sanPham.getMaSanPham(), soLuong);

                    }
                    //đơn giá
                    if(col == 4){
                        Object cellValue = model.getValueAt(row, col).toString();
                        //              System.out.print("sách " + cellValue + "\t");
                        donGia = Double.parseDouble((String) cellValue);
                    }
                    //thành tiền
                    if(col == 5){
                    	 Object cellValue = model.getValueAt(row, col);
                         //System.out.print("sách " + cellValue + "\t");
                         ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(hoaDonTienMat, sanPham, soLuong,donGia);
                         chiTietHoaDon_DAO = new ChiTietHoaDonDAO_IMP();
                         chiTietHoaDon_DAO.InsertCTHoaDon(hoaDonTienMat, soLuong, donGia, sanPham);
                    }
                }
            }
            String formattedDate = null;

            formattedDate = hoaDon_DAO.generateHoaDon(nv);
            lblMaHoaDonFont.setText(formattedDate);
            lblMaHoaDonFont.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 15));

            
            try {
            
            String strDiemTichLuy = lblTongTienThanhToanKyTu.getText().replaceAll("[^\\d.]+", "");
            int diemTichLuy= (int)(Double.parseDouble(strDiemTichLuy) / 100000) + kh.getDiemTL(); 
            
            
            kh.setDiemTL(diemTichLuy);
            khachHang_DAO.updateDiemTichLuy(kh);
            inHoaDon();
            lamMoiDuLieu_SanPham();
            lamMoiDuLieu_KhachHang();
            clearTable(model);
            tblGioHang.setModel(model);
            khuyenMaiThanhToan();
            lamMoiDuLieuThanhToan();

            } catch (JRException ex) {
                Logger.getLogger(pnlLapHoaDon11.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
            }
                

      
        }else{
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm để thanh toán");
        }
        
    }
    
    private boolean validDataHoaDonTienMat(){
        String lblTongTienMat = lblTongTienThanhToanKyTu.getText().replaceAll("[^\\d.]+", "");
        double tongTien = Double.parseDouble(lblTongTienMat);
        
        String txtTien = txtTienKhachDua.getText().replaceAll("[^\\d.]+", "");
        
        if(!(txtTien.length() > 0 && !(txtTien.equals("0.0"))) ){
            JOptionPane.showMessageDialog(this , "Vui lòng nhập số tiền khách đưa !");
            LineBorder redLineBorder = new LineBorder(Color.RED);
            txtTienKhachDua.setBorder(redLineBorder);
            return false;
        }
      
         if(!(Double.parseDouble(txtTien) >= tongTien) ){
            JOptionPane.showMessageDialog(this , "Vui lòng nhập số tiền khách đưa !");
            LineBorder redLineBorder = new LineBorder(Color.RED);
            txtTienKhachDua.setBorder(redLineBorder);
            return false;
            
        }
        txtTienKhachDua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        return true;
    }
    private void cboKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKhuyenMaiActionPerformed
        quyDinh_DAO = new QuyDinhDAO_IMP();
        QuyDinh qd = new QuyDinh();
        List<QuyDinh> quyDinh = quyDinh_DAO.layDuLieuQuyDinh();

        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        Object selectedItem = cboKhuyenMai.getSelectedItem();
        if (selectedItem != null && !selectedItem.equals("Không áp mã")) {
            khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToanDAO_IMP();
            ArrayList<KhuyenMaiThanhToan> dsKhuyenMai_GiaTien = khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_GiaTien();
            for (KhuyenMaiThanhToan khuyenMaiThanhToan1 : dsKhuyenMai_GiaTien) {
                if(cboKhuyenMai.getSelectedItem().equals(khuyenMaiThanhToan1.getMaKhuyenMai())){
                    String priceWithoutCurrency = lblTongTien1.getText().replaceAll("[^\\d.]+", "");
                    double giaBan = Double.parseDouble(priceWithoutCurrency);
                    double giaBanKhuyenMai = (double) khuyenMaiThanhToan1.getSoTienGiam();
                    double tongHoaDon = ((giaBan - giaBanKhuyenMai)* qd.getVAT()/100) + (giaBan - giaBanKhuyenMai);
                    lblChiecKhauKyTu.setText(khuyenMaiThanhToan1.getSoTienGiam()+"");
                    
                    
                    if(tongHoaDon <=0){
                        String formattedTongTien = decimalFormat.format( 0);
                        lblTongTienThanhToanKyTu.setText(formattedTongTien+".0 VND");
                    }else{
                        khachHang_DAO = new KhachHangDAO_IMP();
                        KhachHang kh = khachHang_DAO.layThongTinKhachHang(txtSoDienThoai.getText());    
                        int diemSuDung = (int)tongHoaDon / 2000;

                        if(diemSuDung >= 10){
                        if( !txtDiemTichLuy.getText().equals("" ) ){
                            if( Integer.parseInt(txtDiemTichLuy.getText())  >= 10){
                                anHienSuDungDiem(true);
                                if(cbkSuDungDiem.isSelected()) {
                                    if(kh.getDiemTL() >= 10)   {
                                        if(!txtSuDungDiem.getText().equals("") ){    
                                            int khauTru = Integer.parseInt(txtSuDungDiem.getText())*1000;  
                                                   if(tongHoaDon >=  khauTru * 2)
                                                    tongHoaDon -= khauTru;
                                            }
                                    }
                                }
                            }
                       }
                   }else{
                            System.out.println("ẩn");
                            anHienSuDungDiem(false); 
                    }        
                        
                        String formattedTongTien = decimalFormat.format( tongHoaDon );
                        lblTongTienThanhToanKyTu.setText(formattedTongTien+" VND");
                    }
                }
            }
            ArrayList<KhuyenMaiThanhToan> dsKhuyenMai_TyLe = khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_TyLe();
            for (KhuyenMaiThanhToan khuyenMaiThanhToan1 : dsKhuyenMai_TyLe) {
                if(cboKhuyenMai.getSelectedItem().equals(khuyenMaiThanhToan1.getMaKhuyenMai())){
                    if(khuyenMaiThanhToan1.getGiamToiDa()==0.0){
                        String priceWithoutCurrency = lblTongTien1.getText().replaceAll("[^\\d.]+", "");
                        double giaBan = Double.parseDouble(priceWithoutCurrency);

                        double giaKhuyenMai = giaBan * khuyenMaiThanhToan1.getPhanTramGiam();
                        double tongHoaDon = ((giaBan - giaKhuyenMai) * qd.getVAT()/100)  + (giaBan - giaKhuyenMai);
                        String formattedTongTien = decimalFormat.format( tongHoaDon );
                        lblTongTienThanhToanKyTu.setText(formattedTongTien+" VND");
                    }else{
                        String priceWithoutCurrency = lblTongTien1.getText().replaceAll("[^\\d.]+", "");
                        double giaBan = Double.parseDouble(priceWithoutCurrency);
                        double giamGiaKhuyenMai = giaBan * khuyenMaiThanhToan1.getPhanTramGiam();
                        if(giamGiaKhuyenMai > khuyenMaiThanhToan1.getGiamToiDa()){
                            double tongHoaDon = ((giaBan  - khuyenMaiThanhToan1.getGiamToiDa())* qd.getVAT()/100) +giaBan  - khuyenMaiThanhToan1.getGiamToiDa() ;
                            String formattedTongTien = decimalFormat.format( tongHoaDon );
                            lblTongTienThanhToanKyTu.setText(formattedTongTien+" VND");
                        }else{
                            double tongHoaDon = ((giaBan  - giamGiaKhuyenMai)* qd.getVAT()/100) +  (giaBan  - giamGiaKhuyenMai);
                            String formattedTongTien = decimalFormat.format( tongHoaDon );
                            lblTongTienThanhToanKyTu.setText(formattedTongTien+" VND");
                        }

                    }
                }

            }
        }else{
            String priceWithoutCurrency = lblTongTien1.getText().replaceAll("[^\\d.]+", "");
            double giaBan = Double.parseDouble(priceWithoutCurrency);
            double tongtien = (giaBan*qd.getVAT()/100) + giaBan ;
            
              khachHang_DAO = new KhachHangDAO_IMP();
                        KhachHang kh = khachHang_DAO.layThongTinKhachHang(txtSoDienThoai.getText());  
                            
                        int diemSuDung = (int)tongtien / 2000;
                        System.out.println(diemSuDung);
                   if(diemSuDung >= 10){
                        if( !txtDiemTichLuy.getText().equals("" ) ){
                            if( Integer.parseInt(txtDiemTichLuy.getText())  >= 10){
                                anHienSuDungDiem(true);
                               
                                
                                if(cbkSuDungDiem.isSelected()) {
                                    if(kh.getDiemTL() >= 10)   {
                                        if(!txtSuDungDiem.getText().equals("") ){    
                                            int khauTru = Integer.parseInt(txtSuDungDiem.getText())*1000;  
                                                   if(tongtien >=  khauTru * 2)
                                                    tongtien -= khauTru;
                                            }
                                    }
                                }
                            }
                       }
                   }else{
                            System.out.println("ẩn");
                            anHienSuDungDiem(false);
                           
                    }        
            String formattedTongTien = decimalFormat.format( tongtien );
            lblTongTienThanhToanKyTu.setText(formattedTongTien+" VND");

        }

    }//GEN-LAST:event_cboKhuyenMaiActionPerformed
    
    public void khauTru(){
        quyDinh_DAO = new QuyDinhDAO_IMP();
        QuyDinh qd = new QuyDinh();
        List<QuyDinh> quyDinh = quyDinh_DAO.layDuLieuQuyDinh();

        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        Object selectedItem = cboKhuyenMai.getSelectedItem();
        if (selectedItem != null && !selectedItem.equals("Không áp mã")) {
            khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToanDAO_IMP();
            ArrayList<KhuyenMaiThanhToan> dsKhuyenMai_GiaTien = khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_GiaTien();
            for (KhuyenMaiThanhToan khuyenMaiThanhToan1 : dsKhuyenMai_GiaTien) {
                if(cboKhuyenMai.getSelectedItem().equals(khuyenMaiThanhToan1.getMaKhuyenMai())){

                    String priceWithoutCurrency = lblTongTien1.getText().replaceAll("[^\\d.]+", "");
                    double giaBan = Double.parseDouble(priceWithoutCurrency);
                    double giaBanKhuyenMai = (double) khuyenMaiThanhToan1.getSoTienGiam();
                    double tongHoaDon = ((giaBan - giaBanKhuyenMai)* qd.getVAT()/100) + (giaBan - giaBanKhuyenMai);
                    
                    if(cbkSuDungDiem.isSelected()){
                        if(!txtSuDungDiem.getText().equals("0")){
                            int khauTru = Integer.parseInt(txtSuDungDiem.getText())*1000;  
                                   if(tongHoaDon >=  khauTru * 2)
                                        tongHoaDon -= khauTru;
                            }
                    }
                     String formattedTongTien = decimalFormat.format( 0);
                    if(tongHoaDon <=0){  
                        lblTongTienThanhToanKyTu.setText(formattedTongTien+".0 VND");
                    }else{
                        lblTongTienThanhToanKyTu.setText(formattedTongTien+" VND");
                    }
                }
            }
            ArrayList<KhuyenMaiThanhToan> dsKhuyenMai_TyLe = khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_TyLe();
            for (KhuyenMaiThanhToan khuyenMaiThanhToan1 : dsKhuyenMai_TyLe) {
                if(cboKhuyenMai.getSelectedItem().equals(khuyenMaiThanhToan1.getMaKhuyenMai())){
                    if(khuyenMaiThanhToan1.getGiamToiDa()==0.0){
                        String priceWithoutCurrency = lblTongTien1.getText().replaceAll("[^\\d.]+", "");
                        double giaBan = Double.parseDouble(priceWithoutCurrency);
                        double giaKhuyenMai = giaBan * khuyenMaiThanhToan1.getPhanTramGiam();
                        double tongHoaDon = ((giaBan - giaKhuyenMai) * qd.getVAT()/100)  + (giaBan - giaKhuyenMai);
                        String formattedTongTien = decimalFormat.format( tongHoaDon );
                                   
                        if(  tongHoaDon <=0){
                            lblTongTienThanhToanKyTu.setText(formattedTongTien+".0 VND");
                        }else{     
                            lblTongTienThanhToanKyTu.setText(formattedTongTien+" VND");
                        }
                    }else{
                        String priceWithoutCurrency = lblTongTien1.getText().replaceAll("[^\\d.]+", "");
                        double giaBan = Double.parseDouble(priceWithoutCurrency);
                        double giamGiaKhuyenMai = giaBan * khuyenMaiThanhToan1.getPhanTramGiam();
                        if(giamGiaKhuyenMai > khuyenMaiThanhToan1.getGiamToiDa()){
                            double tongHoaDon = ((giaBan  - khuyenMaiThanhToan1.getGiamToiDa())* qd.getVAT()/100) +giaBan  - khuyenMaiThanhToan1.getGiamToiDa() ;
                            String formattedTongTien = decimalFormat.format( tongHoaDon );
                            if(  tongHoaDon <=0){
                                lblTongTienThanhToanKyTu.setText(formattedTongTien+".0 VND");
                            }else{     
                                lblTongTienThanhToanKyTu.setText(formattedTongTien+" VND");
                            }
                        }else{
                            double tongHoaDon = ((giaBan  - giamGiaKhuyenMai)* qd.getVAT()/100) +  (giaBan  - giamGiaKhuyenMai);
                            String formattedTongTien = decimalFormat.format( tongHoaDon );      
                            if(  tongHoaDon <=0){
                                lblTongTienThanhToanKyTu.setText(formattedTongTien+".0 VND");
                            }else{     
                                lblTongTienThanhToanKyTu.setText(formattedTongTien+" VND");
                            }
                        }

                    }
                }

            }
        }else{
            String priceWithoutCurrency = lblTongTien1.getText().replaceAll("[^\\d.]+", "");
            double giaBan = Double.parseDouble(priceWithoutCurrency);
            double tongtien = (giaBan*qd.getVAT()/100) + giaBan ;
                    if(cbkSuDungDiem.isSelected()){
                            if(!txtSuDungDiem.getText().equals("")) {
                                int khauTru = Integer.parseInt(txtSuDungDiem.getText())*1000;
                                   if(tongtien >=  khauTru * 2)
                                        tongtien -= khauTru;
                            }
                    }
            String formattedTongTien = decimalFormat.format( tongtien );
            if( tongtien <=0){
                lblTongTienThanhToanKyTu.setText(formattedTongTien+".0 VND");
            }else{     
                lblTongTienThanhToanKyTu.setText(formattedTongTien+" VND");
            }

        }
    }
    
    private void txtTimKiemMaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemMaSanPhamActionPerformed
        // TODO add your handling code here:
    
    }//GEN-LAST:event_txtTimKiemMaSanPhamActionPerformed

    private void txtDiemTichLuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiemTichLuyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiemTichLuyActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void txtSoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDienThoaiActionPerformed

    private void txtTenKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKhachHangActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        System.out.println("Pannel.pnlLapHoaDon11.formMouseClicked()");
    }//GEN-LAST:event_formMouseClicked

    private void cbkSuDungDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbkSuDungDiemActionPerformed
        // TODO add your handling code here:
        if(cbkSuDungDiem.isSelected()){
            txtSuDungDiem.setEnabled(true);
            
        }else{
            txtSuDungDiem.setText("");
            txtSuDungDiem.setEnabled(false);
            khauTru();
        }
    }//GEN-LAST:event_cbkSuDungDiemActionPerformed

    private void txtSuDungDiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSuDungDiemFocusLost
        // TODO add your handling code here:
        if(txtSuDungDiem.getText().length() <=1 && cbkSuDungDiem.isSelected()){
                        int x = Integer.parseInt( txtDiemTichLuy.getText());
                        String strDiemTichLuy = lblTongTien1.getText().replaceAll("[^\\d.]+", "");
                        int diemSuDung = Integer.parseInt(strDiemTichLuy)/ 2000;
                        
                       
                        if(x > diemSuDung){
                            txtSuDungDiem.setText(String.valueOf(diemSuDung));  
                                khauTru();
                                            
                    
                        }else {
                                txtSuDungDiem.setText(String.valueOf(x));  
                                khauTru();
                        }  
        }
    }//GEN-LAST:event_txtSuDungDiemFocusLost

    private void txtTienKhachDuaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTienKhachDuaFocusGained
        // TODO add your handling code here:
        kiemTraSoTienTraLai();
    }//GEN-LAST:event_txtTienKhachDuaFocusGained

    private void txtTienKhachDuaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTienKhachDuaFocusLost
        // TODO add your handling code here:
        kiemTraSoTienTraLai();
    }//GEN-LAST:event_txtTienKhachDuaFocusLost

    private void scanImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scanImeiActionPerformed
        // TODO add your handling code here:
                  JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
               QRCode_Dialog qr = new QRCode_Dialog(owner, "Scan", true, textAreaImei);
         
    }//GEN-LAST:event_scanImeiActionPerformed
    
    public void lamMoiDuLieu_KhachHang(){
       
        txtTenKhachHang.setText("");
        txtDiemTichLuy.setText("");
        txtDiaChi.setText("");
    }
    
    public void lamMoiDuLieu_SanPham(){
        txtMaSanPham.setText("");
        txtTenSanPham.setText("");
        txtGiaBan.setText("");
        txtSoLuong.setText("");
    }
    
    private int count = 0;    
        
    public void themSoLuongSanPham(){
        String maSanPham = txtMaSanPham.getText();
        String tenSanPham = txtTenSanPham.getText();
        String giaBan = txtGiaBan.getText();
        String soLuong = txtSoLuong.getText().replaceAll("[^\\d.]+", "");
        
        
        if(!txtSoLuong.getText().equals("") ){
        
        double thanhTien = Integer.parseInt(soLuong) * Double.parseDouble(giaBan);
        
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        boolean found = false;
        int rowCount = model.getRowCount();
        // Kiểm tra nếu mã sản phẩm đã tồn tại trong bảng
        for (int i = 0; i < rowCount; i++) {
                if (model.getValueAt(i, 1).equals(maSanPham)) {
                    // Cập nhật số lượng và thành tiền
                    int currentSoLuong = Integer.parseInt(model.getValueAt(i, 3).toString());
                    double currentThanhTien = Double.parseDouble(model.getValueAt(i, 5).toString());
                    int newSoLuong = currentSoLuong + Integer.parseInt(soLuong);
                    int soLuongTon = soLuongToiDaDeThem(maSanPham);
                    if(newSoLuong > soLuongTon){
                        int soLuongMess = soLuongTon -  currentSoLuong;
                        if(soLuongMess > 0){
                        JOptionPane.showMessageDialog(this, "Số lượng sản phẩm có thể thêm là : " + (soLuongMess));
                           
                        }else{
                        JOptionPane.showMessageDialog(this, "Sản phẩm đã thêm số lượng tối đa");
                        }
                        found = true;
                        break;
                    }else{           
                    double newThanhTien = currentThanhTien + thanhTien;
                    model.setValueAt(newSoLuong, i, 3);
                    model.setValueAt(newThanhTien, i, 5);
                    
                    found = true;
                    break;
                    }
                
                }
            }

    // Nếu không tìm thấy sản phẩm, thêm sản phẩm mới vào bảng
            if (!found) {
                 int soLuongTon = soLuongToiDaDeThem(maSanPham);
                    if(Integer.parseInt(soLuong) > soLuongTon){
                        
                        JOptionPane.showMessageDialog(this, "Số lượng sản phẩm có thể thêm là : " + (soLuongTon));
                    }else{               
                        count++;
                        model.addRow(new Object[]{count, maSanPham, tenSanPham, soLuong, giaBan, thanhTien});                                }
            }else{
                
            }
            tblGioHang.setModel(model);
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedTongTien = decimalFormat.format(tongTien());
           if(tongTien() == 0.0 ){
                lblTongTien1.setText("0"+formattedTongTien+" VND");
            }else
                lblTongTien1.setText(formattedTongTien+" VND");
        }else{
            JOptionPane.showMessageDialog(this, "Nhập số lượng để thêm vào giỏ hàng");
        }
        
    }
    
    public void capNhatSoLuongSanPham(){
        String maSanPham = txtMaSanPham.getText();
        String tenSanPham = txtTenSanPham.getText();
        String giaBan = txtGiaBan.getText();
        String soLuong = txtSoLuong.getText().replaceAll("[^\\d.]+", "");
        
        if(!soLuong.equals("")){
            double thanhTien = Integer.parseInt(soLuong) * Double.parseDouble(giaBan);
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        int rowCount = model.getRowCount();
        int row = tblGioHang.getSelectedRow();
        int soLuongThem = Integer.parseInt(soLuong);
        // Kiểm tra nếu mã sản phẩm đã tồn tại trong bảng
        if(Integer.parseInt(soLuong)!=0){
        boolean found = false;
        for (int i = 0; i < rowCount; i++) {
                if (model.getValueAt(i, 1).equals(maSanPham)) {
                    found = true;
                    break;
                }
        }
        if(!found){
              int soLuongTon = soLuongToiDaDeThem(maSanPham);
                    if(Integer.parseInt(soLuong) > soLuongTon){
                        JOptionPane.showMessageDialog(this, "Số lượng sản phẩm có thể thêm là : " + (soLuongTon));
                    }else{               
                        count++;
                        model.addRow(new Object[]{count, maSanPham, tenSanPham, soLuong, giaBan, thanhTien});         
                    }
            }else{
                 if(soLuongThem !=0){ 
                    // Cập nhật số lượng và thành tiền
                    int soLuongTon = soLuongToiDaDeThem(maSanPham);
                    if(Integer.parseInt(soLuong) > soLuongTon){   
                        JOptionPane.showMessageDialog(this, "Số lượng sản phẩm cập nhật lớn hơn số lượng tồn : " + (soLuongTon));  
                    }else{           
                    double newThanhTien =  thanhTien;
                    model.setValueAt(soLuongThem,row , 3);
                    model.setValueAt(newThanhTien, row , 5);
                    }
                }
            }
        }
            if(soLuongThem == 0)    
                btnXoaSanPham();
            tblGioHang.setModel(model);
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedTongTien = decimalFormat.format(tongTien());
           if(tongTien() == 0.0 ){
                lblTongTien1.setText(formattedTongTien+".0 VND");
            }else
                lblTongTien1.setText(formattedTongTien+" VND");
        }
       
         
    }
    
    public double tongTien(){
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        int rowCount = model.getRowCount();
        double tongTien = 0.0;

        for (int i = 0; i < rowCount; i++) {
            double thanhTien = Double.parseDouble(model.getValueAt(i, 5).toString());
            tongTien += thanhTien;
        }


// In ra tổng tiền hoặc thực hiện các thao tác khác với giá trị này
    return tongTien;
    }    
    
    public void capNhatDanhSanPham_TheMa(String maSanPham){
    	
    	 sanPham_DAO = new SanPhamDAO_IMP();
         String json = Gson.toJson(sanPham_DAO.layDanhSachSanPham());
         JsonArray jsonArray = Gson.fromJson(json, JsonArray.class);
        
         for (JsonElement jsonElement : jsonArray) {
        	 String tinhTrang = jsonElement.getAsJsonObject().get("tinhTrang").getAsString();
        	 int soLuongTon = jsonElement.getAsJsonObject().get("soLuongTon").getAsInt();
             
                    // Thực hiện các thao tác với đối tượng Sach
                    if(tinhTrang.equals("Ngừng kinh doanh") || soLuongTon==0){
                        
                    }else{
                     txtMaSanPham.setText( jsonElement.getAsJsonObject().get("maSanPham").getAsString());
                    txtTenSanPham.setText(jsonElement.getAsJsonObject().get("tenSanPham").getAsString());
                    txtGiaBan.setText(jsonElement.getAsJsonObject().get("donGia").getAsDouble()+"");
                    txtSoLuong.setText("1");
                    themSoLuongSanPham();
                      btnThemSanPham.setText("Thêm sản phẩm (F1)");
                    }
            }      
         
    }
    
    /**
     *
     * @param txt
     */
    private void capNhatDanhSach_TheoMa_TimKiem(JTextField txt){
        txt.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                capNhatDanhSanPham_TheMa(txt.getText());
               
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                capNhatDanhSanPham_TheMa(txt.getText());
               
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
               capNhatDanhSanPham_TheMa(txt.getText());
            }
        });
    }
            
    public void btnXoaSanPham(){
        // Lấy chỉ số hàng đang được chọn
        int rowToRemove = tblGioHang.getSelectedRow();
        if (rowToRemove != -1) {
            DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
            model.removeRow(rowToRemove);

            // Cập nhật lại STT sau khi xóa hàng
            for (int i = rowToRemove; i < model.getRowCount(); i++) {
                model.setValueAt(i + 1, i, 0); // Cột STT ở đây là cột đầu tiên (0)
            }
            count--;
        }else{
           JOptionPane.showMessageDialog(this, "Chọn sản phẩm để xóa !");
        }
        if(tblGioHang.getRowCount()==0){
            btnThemSanPham.setText("Thêm sản phẩm (F1)");
        }
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedTongTien = decimalFormat.format(tongTien());
         if(tongTien() == 0.0 ){
                lblTongTien1.setText("0.0 VND");
            }else
                lblTongTien1.setText(formattedTongTien+" VND");
    }
//    
    
//    private void addToPendingPayment() {
//    // Lấy chỉ số hàng đang được chọn trong bảng sản phẩm
//    int[] selectedRows = tblGioHang.getSelectedRows();
//    
//    if (selectedRows.length > 0) { // Kiểm tra xem có hàng nào được chọn không
//        DefaultTableModel modelSanPham = (DefaultTableModel) tblGioHang.getModel();
//        DefaultTableModel modelHangChoThanhToan = (DefaultTableModel)tblGioHang1.getModel();
//        
//        // Duyệt qua tất cả các hàng đã chọn trong bảng sản phẩm
//        for (int selectedRow : selectedRows) {
//            // Lấy dữ liệu từ hàng đã chọn
//            Object[] rowData = new Object[modelSanPham.getColumnCount()];
//            for (int i = 0; i < modelSanPham.getColumnCount(); i++) {
//                rowData[i] = modelSanPham.getValueAt(selectedRow, i);
//            }
//            
//            // Thêm dữ liệu từ hàng đã chọn vào bảng hàng chờ thanh toán
//            modelHangChoThanhToan.addRow(rowData);
//        }
//    }
//}

    public class frmHangCho extends javax.swing.JFrame {

    private KhachHang_DAO khachHang_DAO;
    private HangCho_DAO hangCho_DAO;

    /**
     * Creates new form frmHangCho
     */
    public frmHangCho() {
        initComponents();
        
        capNhatDanhSachHangCho();
        capNhatDanhSachTimKiemTheoSoDienThoai(txtTimKiemSoDienThoai);
    }
    private  int count = 0;
    private  void capNhatDanhSachHangCho(){
    	  String colTieuDe1[] = new String[]{"STT", "Mã Khách Hàng", "Tên khách hàng", "Số điện thoại"};
          DefaultTableModel model = new DefaultTableModel(colTieuDe1, 0);
          hangCho_DAO = new HangChoDAO_IMP();
          khachHang_DAO = new KhachHangDAO_IMP();
          hangCho_DAO.DeleteHangChoQuaNgay();
          ArrayList<HangCho> dsHangCho = hangCho_DAO.layDanhSachHangCho();
          Object[] row;

          Set<String> maKhachHangSet = new HashSet<>(); // Sử dụng Set để lưu trữ giá trị mã khách hàng đã xuất hiện
          for (HangCho hangCho : dsHangCho) {
              
                  row = new Object[12];
                  String khachHang = hangCho.getKhachHang().getMaKhachHang();
                  if (!maKhachHangSet.contains(khachHang)) { // Kiểm tra xem mã khách hàng đã xuất hiện chưa
                      maKhachHangSet.add(khachHang); // Thêm mã khách hàng vào Set
                      count++;
                      row[0] = count;
                      row[1] = khachHang;
                      KhachHang dsKhachHang = khachHang_DAO.layThongTinKhachHang_TheoMaKH(khachHang);
                      row[2] = dsKhachHang.getTenKhachHang();
                      row[3] = dsKhachHang.getSoDienThoai();
                      model.addRow(row);
                  }
              }
          
      tblHangCho.setModel(model);
    }
    
    public void capNhatDanhSachKhachHangTheoMa_HangCho(String sdt){
        String colTieuDe1[] = new String[]{"STT", "Mã Khách Hàng", "Tên khách hàng", "Số điện thoại"};
        DefaultTableModel model = new DefaultTableModel(colTieuDe1, 0);
        hangCho_DAO = new HangChoDAO_IMP();
        khachHang_DAO = new KhachHangDAO_IMP();
        ArrayList<HangCho> dsHangCho = hangCho_DAO.layDanhSachHangCho();
        Object[] row;
        int dem = 0;
        Set<String> maKhachHangSet = new HashSet<>(); // Sử dụng Set để lưu trữ giá trị mã khách hàng đã xuất hiện
        for (HangCho hangCho : dsHangCho) {
        row = new Object[12];
        String khachHang = hangCho.getKhachHang().getMaKhachHang();
            if (!maKhachHangSet.contains(khachHang)) { // Kiểm tra xem mã khách hàng đã xuất hiện chưa
            ArrayList<KhachHang > dsKhachHangSoDienThoai = khachHang_DAO.danhSachKhachHangTheoSDT(sdt);
			for (KhachHang khachHang1 : dsKhachHangSoDienThoai) {
			    if(khachHang1.getMaKhachHang().equals(hangCho.getKhachHang().getMaKhachHang())){
			        maKhachHangSet.add(khachHang); // Thêm mã khách hàng vào Set
			        dem++;
			        row[0] = dem;
			        row[1] = khachHang;
			        row[2] = khachHang1.getTenKhachHang();
			        row[3] = khachHang1.getSoDienThoai();
			        model.addRow(row);
			    }
			}
            }
        }
        tblHangCho.setModel(model);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblHangCho = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHangCho = new javax.swing.JTable();
        
        btnXoa = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        pnlTimKiemKhachHang = new javax.swing.JPanel();
        lblTimKiemSoDienThoai = new javax.swing.JLabel();
        txtTimKiemSoDienThoai = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblHangCho.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblHangCho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHangCho.setText("Hàng chờ");

        tblHangCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Khách Hàng", "Tên khách hàng", "Số điện thoại"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblHangCho);

       

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanHangDoiActionPerformed(evt);
            }
        });
        pnlTimKiemKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N
        pnlTimKiemKhachHang.setToolTipText("");

        lblTimKiemSoDienThoai.setText("Số điện thoại");

        javax.swing.GroupLayout pnlTimKiemKhachHangLayout = new javax.swing.GroupLayout(pnlTimKiemKhachHang);
        pnlTimKiemKhachHang.setLayout(pnlTimKiemKhachHangLayout);
        pnlTimKiemKhachHangLayout.setHorizontalGroup(
            pnlTimKiemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemKhachHangLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblTimKiemSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiemSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTimKiemKhachHangLayout.setVerticalGroup(
            pnlTimKiemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTimKiemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTimKiemSoDienThoai)
                    .addComponent(txtTimKiemSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHangCho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTimKiemKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        
                        .addGap(26, 26, 26)
                        .addComponent(btnXoa)
                        .addGap(27, 27, 27)
                        .addComponent(btnThanhToan)
                        .addGap(38, 38, 38)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblHangCho, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(pnlTimKiemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    
                    .addComponent(btnXoa)
                    .addComponent(btnThanhToan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        int rowToRemove = tblHangCho.getSelectedRow();
        if (rowToRemove != -1) {
            DefaultTableModel model = (DefaultTableModel) tblHangCho.getModel();
            hangCho_DAO = new HangChoDAO_IMP();
            hangCho_DAO.DeleteDanhSachHangCho(model.getValueAt(rowToRemove, 1)+"");
        
            model.removeRow(rowToRemove);
            
            // Cập nhật lại STT sau khi xóa hàng
            for (int i = rowToRemove; i < model.getRowCount(); i++) {
                model.setValueAt(i + 1, i, 0); // Cột STT ở đây là cột đầu tiên (0)
            }
            count--;

            
        }
        
        
        
    }                                      
    
    private  void capNhatDanhSachTimKiemTheoSoDienThoai(JTextField txt){
        txt.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            capNhatDanhSachKhachHangTheoMa_HangCho(txt.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                capNhatDanhSachKhachHangTheoMa_HangCho(txt.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }
    private void btnThanhToanHangDoiActionPerformed(java.awt.event.ActionEvent evt) { 
    	 count = 1;
         hangCho_DAO = new HangChoDAO_IMP();
         
         int row = tblHangCho.getSelectedRow();
         String maKhachHang = tblHangCho.getValueAt(row, 1).toString();
         ArrayList<HangCho> dsHangCho = hangCho_DAO.layDanhSachHangChoTheoMaKhachHang(maKhachHang);
         DefaultTableModel model =  (DefaultTableModel) tblGioHang.getModel();
         model.setRowCount(0);
         Object[] rowTable;
         txtSoDienThoai.setText("");
         txtSoDienThoai.setText(tblHangCho.getValueAt(row, 3).toString());
         
        
         sanPham_DAO = new SanPhamDAO_IMP();
         String json = Gson.toJson(sanPham_DAO.layDanhSachSanPham());
         JsonArray jsonArray = Gson.fromJson(json, JsonArray.class);
         for (HangCho hangCho : dsHangCho) {
              rowTable = new Object[6];
              //rowTable[3] = '
              for (JsonElement jsonElement1 : jsonArray) {
                         if(hangCho.getSanPham().getMaSanPham().equals(jsonElement1.getAsJsonObject().get("maSanPham").getAsString())){
                         String tenSanPham = jsonElement1.getAsJsonObject().get("tenSanPham").getAsString();
                      	double donGia = jsonElement1.getAsJsonObject().get("donGia").getAsDouble();            	                
                         rowTable[0] =  count++;
                         rowTable[1] =  hangCho.getSanPham().getMaSanPham();
                         rowTable[2] = tenSanPham;;
                         rowTable[3] = hangCho.getSoLuong();
                         rowTable[4] = donGia;
                         double thanhTien = hangCho.getSoLuong() *  donGia;
                         rowTable[5] = thanhTien;
                         model.addRow(rowTable);
                      } 
              }
         }
         tblGioHang.setModel(model);
  
         DecimalFormat decimalFormat = new DecimalFormat("#,###");
         String formattedTongTien = decimalFormat.format(tongTien());
         if(tongTien() == 0.0 ){
             lblTongTien1.setText("0"+formattedTongTien+" VND");
         }else
             lblTongTien1.setText(formattedTongTien+" VND");
         
        
    }
    // Variables declaration - do not modify                     
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoa;
    
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHangCho;
    private javax.swing.JLabel lblTimKiemSoDienThoai;
    private javax.swing.JPanel pnlTimKiemKhachHang;
    private javax.swing.JTable tblHangCho;
    private javax.swing.JTextField txtTimKiemSoDienThoai;
    // End of variables declaration                   
}
   
    private void lamMoiDuLieuThanhToan(){
        lamMoiDuLieu_KhachHang();
        lamMoiDuLieu_SanPham();
        anHienSuDungDiem(false);
        txtTimKiemMaSanPham.setText("");
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0);
        tblGioHang.setModel(model);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedTongTien = decimalFormat.format(tongTien());
        lblTongTien1.setText(formattedTongTien+".0 VND");
        cboKhuyenMai.setSelectedIndex((-1));
        btnThemSanPham.setText("Thêm sản phẩm (F1)");
        txtTienKhachDua.setText("");
        lblTienTraLaiKyTu.setText("0.0 VND");
        lblTongTienThanhToanKyTu.setText("0.0 VND");
        txtSoDienThoai.setText("");
        lblChiecKhauKyTu.setText("0.0 VND");
    }    
    
    //Để xóa hết hàng trong bảng, bạn có thể sử dụng phương thức setRowCount(0) của DefaultTableModel. Điều này sẽ xóa tất cả các hàng khỏi bảng.
    private void clearTable(DefaultTableModel model) {
        model.setRowCount(0);
        count = 0; // Đặt lại giá trị count về 0 nếu cần thiết
        //Sau đó, để sử dụng hàm này, bạn chỉ cần gọi nó với mô hình của bảng:
//clearTable((DefaultTableModel) tblGioHang.getModel());
//Hàm clearTable sẽ đặt số hàng về 0 và cũng có thể đặt lại giá trị count nếu bạn sử dụng count để theo dõi số lượng hàng.
}
   
    private void duLieuSDT(){
    
             ((AbstractDocument) txtSoDienThoai.getDocument()).setDocumentFilter(new DocumentFilter() {
                   @Override
                   public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr)
                           throws BadLocationException {
                        String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                        text += string;
                        if (text.length() <= 10 && text.matches("\\d*")) {
                            super.insertString(fb, offset, string, attr);
                        } else if (text.matches("\\d*")) {
                            super.replace(fb, 9, 1, string, attr);
                        }
                   }

                   @Override
                   public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                           throws BadLocationException {
                       String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                       if (newText.length() <= 10 && newText.matches("\\d*")) {
                           super.replace(fb, offset, length, text, attrs);
                       } else if (newText.matches("\\d*")) {
                           super.replace(fb, 9, 1, text, attrs);
                       }
                   }
               });

               txtSoDienThoai.addFocusListener(new FocusAdapter() {
                   @Override
                     public void focusLost(FocusEvent e) {
                       String text = txtSoDienThoai.getText();
                       if (text.length() > 10) {
                           txtSoDienThoai.setText(text.substring(text.length() - 10));
                       }
                   }
               });
               txtSoDienThoai.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                 private KhachHang_DAO khachHang_DAO;
                 @Override
                 public void insertUpdate(DocumentEvent e) {
                    anHienSuDungDiem(false);
                     
                    if ( txtSoDienThoai.getText().length() < 10) {
                    // Đặt màu đỏ đậm cho đường viền
                    LineBorder redLineBorder = new LineBorder(Color.RED, 2);
                    txtSoDienThoai.setBorder(redLineBorder);
                    lamMoiDuLieu_KhachHang();
                    
                    } else {
                        
                     if(!txtSoDienThoai.getText().substring(0, 1).equals("0")){
                            
                            lamMoiDuLieu_KhachHang();
                            LineBorder redLineBorder = new LineBorder(Color.RED, 2);
                            txtSoDienThoai.setBorder(redLineBorder);
                            }else{
                         
                            khachHang_DAO = new KhachHangDAO_IMP();
                            KhachHang kh = khachHang_DAO.layThongTinKhachHang(txtSoDienThoai.getText());  
                           
                            if(kh != null){
                                txtSoDienThoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
                                txtTenKhachHang.setText(kh.getTenKhachHang());
                                txtDiaChi.setText(kh.getDiaChi());
                                txtDiemTichLuy.setText(kh.getDiemTL()+""); 
                                String strDiemTichLuy = lblTongTien1.getText().replaceAll("[^\\d.]+", "");
                                if(!strDiemTichLuy.equals("0.0")){
                                    int diemSuDung = Integer.parseInt(strDiemTichLuy) / 2000;
                                    if(kh.getDiemTL() >= 10 && diemSuDung >= 10 ){
                                        anHienSuDungDiem(true); 
                                    }
                                }
                            }    
                        }
                    }
                 }

                 @Override
                 public void removeUpdate(DocumentEvent e) {
                    anHienSuDungDiem(false);
                    
                    LineBorder redLineBorder = new LineBorder(Color.RED, 2);
                    txtSoDienThoai.setBorder(redLineBorder);
                    lamMoiDuLieu_KhachHang();
                    if(txtSoDienThoai.getText().length() == 0){
                          txtSoDienThoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
                    }
                 }
                 
                 @Override
                 public void changedUpdate(DocumentEvent e) {
                     lamMoiDuLieu_KhachHang();
                     anHienSuDungDiem(false);
                    
                 }
               });
                
    }
    
    private void addTableStyle(JScrollPane scroll) {
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null); 
        scroll.setBorder(null);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel panel = new JPanel();
        panel.setBackground(new Color(60, 60, 60));
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
        scroll.setBackground(Color.red);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDanhSachHangCho;
    private javax.swing.JButton btnLamMoi2;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemSanPham;
    private javax.swing.JButton btnThemVaoHangCho;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.JCheckBox cbkSuDungDiem;
    private javax.swing.JComboBox<String> cboHinhThucThanhToan;
    private javax.swing.JComboBox<String> cboKhuyenMai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblChiecKhauKyTu;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblGiaBan;
    private javax.swing.JLabel lblKm;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblMaHoaDonFont;
    private javax.swing.JLabel lblMaKhachHang;
    private javax.swing.JLabel lblMaSanPham;
    private javax.swing.JLabel lblNgayLapHoaDon;
    private javax.swing.JLabel lblNgayLapHoaDonFont;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblSoTienGiam;
    private javax.swing.JLabel lblTenKhachHang;
    private javax.swing.JLabel lblTenNhanVien;
    private javax.swing.JLabel lblTenNhanVienFont;
    private javax.swing.JLabel lblTenSanPham;
    private javax.swing.JPanel lblThongTinKhachHang;
    private javax.swing.JLabel lblTienKhachDua;
    private javax.swing.JLabel lblTienTraLai;
    private javax.swing.JLabel lblTienTraLaiKyTu;
    private javax.swing.JLabel lblTieuDeLapHoaDon;
    private javax.swing.JLabel lblTimKiemMaSanPham;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTongTien1;
    private javax.swing.JLabel lblTongTienThanhToan;
    private javax.swing.JLabel lblTongTienThanhToanKyTu;
    private javax.swing.JPanel pnlGioHang;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlTacVu2;
    private javax.swing.JPanel pnlThongTinHoaDon;
    private javax.swing.JPanel pnlThongTinSanPham;
    private javax.swing.JPanel pnlTimKiemSanPham;
    private ServiceUser.ButtonCustom scanImei;
    private javax.swing.JScrollPane scrGioHang;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTextArea textAreaImei;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDiemTichLuy;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtSuDungDiem;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTimKiemMaSanPham;
    // End of variables declaration//GEN-END:variables
}
