
package Pannel;
import DAO.GiamGiaSanPham_DAO;
import DAO.KhuyenMaiThanhToan_DAO;
import DAO.LoaiSanPham_DAO;
import DAO.Sach_DAO;
import DAO.VanPhongPham_DAO;
import Entity.GiamGiaSanPham;
import Entity.KhuyenMaiThanhToan;
import Entity.LoaiSanPham;
import Entity.Sach;
import Entity.SanPham;
import Entity.VanPhongPham;
import ServiceUser.SelectedDate;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author FPTSHOP
 */
public class pnlKhuyenMai extends javax.swing.JPanel {

    private Sach_DAO sach_DAO;
    private VanPhongPham_DAO vanPhongPham_DAO;
    private KhuyenMaiThanhToan_DAO khuyenMaiThanhToan_DAO;
    private GiamGiaSanPham_DAO giamGiaSanPham_DAO;
    private LoaiSanPham_DAO loaiSanPham_DAO;

    /**
     * Creates new form pnlKhuyenMai
     */
    public pnlKhuyenMai() {
        initComponents();
        capNhatTinhTrang();
        giaTienRad();
        kiemTraDuLieuFloat(txtGiaTien1);
        kiemTraDuLieuFloat(txtGiaTien2);
        kiemTraDuLieuFloat(txtGiaTien3);
        
        kiemTraDuLieuFloat(txtTyLe2);
        kiemTraDuLieuFloat(txtTyLe3);
        kiemTraDuLieuFloat(txtTyLe4);
        
        kiemTraDuLieuFloat(txtSanPham3);
        
        maHoaDon_GiaTri(lblMaKhuyenMaiKyTu);
        duLieu();
        tinhTrang();
        Ngay();
        
    }
    public void capNhatTinhTrang(){
        khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToan_DAO();
        ArrayList<KhuyenMaiThanhToan> dsKhuyenMaiGiaTien= khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_GiaTien();
        for (KhuyenMaiThanhToan khuyenMaiThanhToan : dsKhuyenMaiGiaTien) {
            String tinhTrang = handleDateChange_CapNhatTinhTrang(khuyenMaiThanhToan.getNgayBatDau(),khuyenMaiThanhToan.getNgayKetThuc());
            System.out.println(tinhTrang);
            khuyenMaiThanhToan_DAO.update_tinhTrang(khuyenMaiThanhToan.getMaKhuyenMai(),tinhTrang);
        }
        ArrayList<KhuyenMaiThanhToan> dsKhuyenMaiTyLe= khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_TyLe();
        for (KhuyenMaiThanhToan khuyenMaiThanhToan : dsKhuyenMaiTyLe) {
            String tinhTrang = handleDateChange_CapNhatTinhTrang(khuyenMaiThanhToan.getNgayBatDau(),khuyenMaiThanhToan.getNgayKetThuc());
            System.out.println(tinhTrang);
            khuyenMaiThanhToan_DAO.update_tinhTrang(khuyenMaiThanhToan.getMaKhuyenMai(),tinhTrang);
        }
        giamGiaSanPham_DAO = new GiamGiaSanPham_DAO();
        ArrayList<GiamGiaSanPham> dsGiamGia =  giamGiaSanPham_DAO.layDanhSachGiamGiaSanPham();
        for (GiamGiaSanPham giamGiaSanPham : dsGiamGia) {
         String tinhTrang = handleDateChange_CapNhatTinhTrang(giamGiaSanPham.getNgayBatDau(),giamGiaSanPham.getNgayKetThuc());
            System.out.println(tinhTrang);
            giamGiaSanPham_DAO.update_tinhTrang(giamGiaSanPham.getMaGiamGiaSanPham(),tinhTrang);
        }
       
        
    }
    int demNgayBatDau =0, demNgayKetThuc =0;
    
    public boolean isValidDateRange(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        return !ngayBatDau.isAfter(ngayKetThuc); // Trả về true nếu ngày bắt đầu không sau ngày kết thúc
    }
    public void Ngay(){
        System.out.println(demNgayKetThuc);
        Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            langNgheThayDoi(txtNgayBatDau, demNgayBatDau);
            System.out.println(demNgayBatDau +"-"+ manHinh);
            if(demNgayBatDau!=0){
               LocalDate ngayBatDau = ngay(dateNgayBatDau);
               LocalDate ngayKetThuc = ngay(dateNgayKetThuc);
               if(!isValidDateRange(ngayBatDau, ngayKetThuc)){
                    int ngayTach = ngayKetThuc.getDayOfMonth();
                    int thang = ngayKetThuc .getMonthValue();
                    int nam = ngayKetThuc .getYear();
                    String ngay = ngayTach +"-"+thang+"-"+nam;
                    String[] parts = ngay.split("-"); // Tách chuỗi thành mảng các phần tử
                    if (parts.length == 3) { // Kiểm tra có đủ phần tử ngày, tháng, năm không
                        int day = Integer.parseInt(parts[0]);
                        int month = Integer.parseInt(parts[1]);
                        int year = Integer.parseInt(parts[2]);
                        dateNgayBatDau.setSelectedDate(new SelectedDate(day,month,year));
                        // Kiểm tra và xử lý ngày, tháng, năm nếu cần      
                    }
                }
            }
        }
    });
    if(demNgayKetThuc==0)    {
        timer.start(); 
    }
}
    public void langNgheThayDoi(JTextField txt, int dem){
        txt.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                 handleChange(dem);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                 handleChange(dem);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleChange(dem);
            }
            private void handleChange(int dem) {
              dem++;
                System.out.println(dem);
            }
        });
    }
    public void tinhTrang(){
        Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           String lbl = "Tình trạng  : ";
           lblTinhTrang.setText(lbl + handleDateChange(dateNgayBatDau, dateNgayKetThuc) );
        }
    });

    timer.start(); 
    }
    
    public void duLieu(){
        sach_DAO = new Sach_DAO();
        vanPhongPham_DAO = new VanPhongPham_DAO();
        
        ArrayList<Sach> dssach = sach_DAO.layDanhSanPhamSach();
        for (Sach sach : dssach) {
           txtSanPham1.addItemSuggestion(sach.getTenSanPham());
       }
        ArrayList<VanPhongPham> dsvpp = vanPhongPham_DAO.layDanhSanPhamVanPhongPham();
         // Tạo một ArrayList mới để chứa cả hai danh sách
         for (VanPhongPham vanPhongPham : dsvpp) {
            txtSanPham1.addItemSuggestion(vanPhongPham.getTenSanPham());
       }
      
   }
public void kiemTraDuLieuFloat(JTextField textField){
        DecimalFormat df = new DecimalFormat("#,###");
        textField.addKeyListener(new KeyAdapter() {
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
            public void keyReleased(KeyEvent e)
            {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btg1 = new javax.swing.ButtonGroup();
        dateNgayBatDau = new ServiceUser.DateChooser();
        btg2 = new javax.swing.ButtonGroup();
        dateNgayKetThuc = new ServiceUser.DateChooser();
        jLabel1 = new javax.swing.JLabel();
        lblmaKhuyenMai = new javax.swing.JLabel();
        lblTenKhuyenMai = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblTinhTrang = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNgayBatDau = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNgayKetThuc = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtTyLe3 = new javax.swing.JTextField();
        lblViTri2 = new javax.swing.JLabel();
        radTyLe = new javax.swing.JRadioButton();
        radGiaTien = new javax.swing.JRadioButton();
        lblViTri1 = new javax.swing.JLabel();
        radSanPham = new javax.swing.JRadioButton();
        lblViTri3 = new javax.swing.JLabel();
        txtGiaTien1 = new javax.swing.JTextField();
        txtGiaTien2 = new javax.swing.JTextField();
        txtTyLe1 = new javax.swing.JTextField();
        txtSanPham1 = new ServiceUser.TextFieldSuggestion();
        lblViTri4 = new javax.swing.JLabel();
        txtTyLe4 = new javax.swing.JTextField();
        txtGiaTien3 = new javax.swing.JTextField();
        radGiaTienSanPham = new javax.swing.JRadioButton();
        radTyLeSanPham = new javax.swing.JRadioButton();
        txtSanPham3 = new javax.swing.JTextField();
        txtSanPham4 = new javax.swing.JTextField();
        txtTyLe2 = new javax.swing.JTextField();
        cboSanPham = new javax.swing.JComboBox<>();
        lblMaKhuyenMaiKyTu = new javax.swing.JLabel();
        txtTenKhuyenMai = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tatChiTiet = new javax.swing.JTextArea();
        scrDanhSachSanPhamTimKiem = new javax.swing.JScrollPane();
        tblDanhSachKhuyenMai = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnNgayBatDau = new javax.swing.JButton();
        btnNgayKetThuc = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        lblSoLuong = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        dateNgayBatDau.setTextRefernce(txtNgayBatDau);
        dateNgayBatDau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dateNgayBatDauFocusGained(evt);
            }
        });
        dateNgayBatDau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateNgayBatDauMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dateNgayBatDauMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dateNgayBatDauMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dateNgayBatDauMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dateNgayBatDauMouseReleased(evt);
            }
        });

        dateNgayKetThuc.setTextRefernce(txtNgayKetThuc);
        dateNgayKetThuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateNgayKetThucMouseClicked(evt);
            }
        });

        setPreferredSize(new java.awt.Dimension(1300, 982));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Khuyến mãi");

        lblmaKhuyenMai.setText("Mã khuyến mãi");

        lblTenKhuyenMai.setText("Tên khuyến mãi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1132, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );

        lblTinhTrang.setText("Tình trạng  : ");

        jLabel11.setText("Ngày bắt đầu");

        jLabel12.setText("Ngày kết thúc");

        txtNgayBatDau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNgayBatDauFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNgayBatDauFocusLost(evt);
            }
        });
        txtNgayBatDau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNgayBatDauMouseClicked(evt);
            }
        });

        jLabel6.setText("Chi tiết");

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTyLe3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTyLe3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTyLe3FocusLost(evt);
            }
        });
        jPanel2.add(txtTyLe3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 160, -1));

        lblViTri2.setText("Giá trị tối thiểu đơn hàng");
        jPanel2.add(lblViTri2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, -1, -1));

        btg1.add(radTyLe);
        radTyLe.setText("Tỷ lệ");
        radTyLe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radTyLeActionPerformed(evt);
            }
        });
        jPanel2.add(radTyLe, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        btg1.add(radGiaTien);
        radGiaTien.setSelected(true);
        radGiaTien.setText("Giá tiền");
        radGiaTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radGiaTienActionPerformed(evt);
            }
        });
        jPanel2.add(radGiaTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        lblViTri1.setText("Số tiền giảm");
        jPanel2.add(lblViTri1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        btg1.add(radSanPham);
        radSanPham.setText("Sản phẩm");
        radSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSanPhamActionPerformed(evt);
            }
        });
        jPanel2.add(radSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        lblViTri3.setText("jLabel4");
        jPanel2.add(lblViTri3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 140, -1));

        txtGiaTien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaTien1ActionPerformed(evt);
            }
        });
        jPanel2.add(txtGiaTien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 160, -1));
        jPanel2.add(txtGiaTien2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 160, -1));

        txtTyLe1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTyLe1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTyLe1FocusLost(evt);
            }
        });
        jPanel2.add(txtTyLe1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 160, -1));

        txtSanPham1.setText("textFieldSuggestion2");
        jPanel2.add(txtSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 36, 350, 30));

        lblViTri4.setText("jLabel3");
        jPanel2.add(lblViTri4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 140, -1));

        txtTyLe4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTyLe4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTyLe4FocusLost(evt);
            }
        });
        jPanel2.add(txtTyLe4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 160, -1));
        jPanel2.add(txtGiaTien3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 160, -1));

        btg2.add(radGiaTienSanPham);
        radGiaTienSanPham.setSelected(true);
        radGiaTienSanPham.setText("Giá tiền");
        radGiaTienSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radGiaTienSanPhamActionPerformed(evt);
            }
        });
        jPanel2.add(radGiaTienSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, -1));

        btg2.add(radTyLeSanPham);
        radTyLeSanPham.setText("Tỷ lệ");
        radTyLeSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radTyLeSanPhamActionPerformed(evt);
            }
        });
        jPanel2.add(radTyLeSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, -1, -1));
        jPanel2.add(txtSanPham3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 160, -1));

        txtSanPham4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSanPham4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSanPham4FocusLost(evt);
            }
        });
        jPanel2.add(txtSanPham4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 160, -1));
        jPanel2.add(txtTyLe2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 160, -1));

        cboSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Toàn bộ sản phẩm", "Theo tên sản phẩm", "Toàn bộ sách", "Toàn bộ văn phòng phẩm" }));
        cboSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSanPhamActionPerformed(evt);
            }
        });
        jPanel2.add(cboSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 15, 200, -1));

        tatChiTiet.setColumns(20);
        tatChiTiet.setRows(5);
        jScrollPane1.setViewportView(tatChiTiet);

        scrDanhSachSanPhamTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scrDanhSachSanPhamTimKiemMouseClicked(evt);
            }
        });

        tblDanhSachKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Khuyến mãi", "Tên Khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc", "Tình trạng", "Số lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDanhSachKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachKhuyenMaiMouseClicked(evt);
            }
        });
        scrDanhSachSanPhamTimKiem.setViewportView(tblDanhSachKhuyenMai);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập nhật ");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnNgayBatDau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/iconAnhLich.png"))); // NOI18N
        btnNgayBatDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayBatDauActionPerformed(evt);
            }
        });

        btnNgayKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/iconAnhLich.png"))); // NOI18N
        btnNgayKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayKetThucActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang khuyến mãi", "Sắp khuyến mãi", "Đã kết thúc" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        lblSoLuong.setText("Số lượng    :");

        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblmaKhuyenMai)
                            .addComponent(lblTenKhuyenMai))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaKhuyenMaiKyTu, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(353, 353, 353)
                                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(525, 525, 525)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrDanhSachSanPhamTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 1381, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(lblTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnThem)
                                        .addGap(55, 55, 55)
                                        .addComponent(btnCapNhat)
                                        .addGap(56, 56, 56)
                                        .addComponent(btnLamMoi))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(50, 50, 50)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaKhuyenMai)
                    .addComponent(lblMaKhuyenMaiKyTu, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSoLuong)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnNgayBatDau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnNgayKetThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCapNhat)
                            .addComponent(btnLamMoi)
                            .addComponent(btnThem))
                        .addGap(18, 18, 18)
                        .addComponent(scrDanhSachSanPhamTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public void dinhDangSo(String chuoi,JTextField txt){
                 
                         
                String[] mangChuoi = chuoi.split("\\.");

                        // Lấy phần trước và sau dấu chấm
                String phanTruocDauCham = mangChuoi[0];
                        DecimalFormat dinhDang = new DecimalFormat("#,###");
                        String so = dinhDang.format(Integer.parseInt(phanTruocDauCham));
                        txt.setText(so);
    }
    private void tblDanhSachKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachKhuyenMaiMouseClicked
        // TODO add your handling code here:
         int row = tblDanhSachKhuyenMai.getSelectedRow();
        if(radGiaTien.isSelected()){
            khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToan_DAO();
            ArrayList<KhuyenMaiThanhToan> dsKhuyenMaiThanhToan = khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_GiaTien();
            String ma = tblDanhSachKhuyenMai.getValueAt(row, 0).toString();
            for (KhuyenMaiThanhToan khuyenMaiThanhToan : dsKhuyenMaiThanhToan) {
                if(khuyenMaiThanhToan.getMaKhuyenMai().equals(ma)){
                    lblMaKhuyenMaiKyTu.setText(ma);
                    txtTenKhuyenMai.setText(khuyenMaiThanhToan.getTenKhuyenMai());
                        
                        
                    dinhDangSo(khuyenMaiThanhToan.getSoTienGiam()+"", txtGiaTien1);
                    dinhDangSo(khuyenMaiThanhToan.getGiaTriToiThieuDonHang()+"", txtGiaTien2);
                    dinhDangSo(khuyenMaiThanhToan.getSoLuong()+"", txtGiaTien3);

                    
                   
                    int namBatDau= Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 2).toString().substring(0,4));
                    int thangBatDau = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 2).toString().substring(5,7));
                    int ngayBatDau = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 2).toString().substring(8,10));
                    dateNgayBatDau.setSelectedDate(new SelectedDate(ngayBatDau,thangBatDau,namBatDau));
                   
                    int namKetThuc = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 3).toString().substring(0,4));
                    int thangKetThuc = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 3).toString().substring(5,7));
                    int ngayKetThuc = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 3).toString().substring(8,10));
                    dateNgayKetThuc.setSelectedDate(new SelectedDate(ngayKetThuc,thangKetThuc,namKetThuc));
                    tatChiTiet.setText(khuyenMaiThanhToan.getChiTiet());
                    System.out.println(khuyenMaiThanhToan);
                }
            }
        }else if(radTyLe.isSelected()){
            khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToan_DAO();
            ArrayList<KhuyenMaiThanhToan> dsKhuyenMaiThanhToan = khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_TyLe();
            String ma = tblDanhSachKhuyenMai.getValueAt(row, 0).toString();
            for (KhuyenMaiThanhToan khuyenMaiThanhToan : dsKhuyenMaiThanhToan) {
                 if(khuyenMaiThanhToan.getMaKhuyenMai().equals(ma)){
                     lblMaKhuyenMaiKyTu.setText(ma);
                     
                     txtTenKhuyenMai.setText(khuyenMaiThanhToan.getTenKhuyenMai());
                     
                     txtTyLe1.setText(khuyenMaiThanhToan.getPhanTramGiam()+" %");
                     
                     
                    dinhDangSo(khuyenMaiThanhToan.getGiaTriToiThieuDonHang()+"", txtTyLe2);
                     if(khuyenMaiThanhToan.getGiamToiDa() == 0){
                        txtTyLe3.setText("Không giới hạn");
                     }else{
                        dinhDangSo(khuyenMaiThanhToan.getGiamToiDa()+"", txtTyLe3);
                     }
                    dinhDangSo(khuyenMaiThanhToan.getSoLuong()+"", txtTyLe4);
                    
                    int namBatDau= Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 2).toString().substring(0,4));
                    int thangBatDau = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 2).toString().substring(5,7));
                    int ngayBatDau = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 2).toString().substring(8,10));
                    dateNgayBatDau.setSelectedDate(new SelectedDate(ngayBatDau,thangBatDau,namBatDau));
                    int namKetThuc = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 3).toString().substring(0,4));
                    int thangKetThuc = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 3).toString().substring(5,7));
                    int ngayKetThuc = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 3).toString().substring(8,10));
                    dateNgayKetThuc.setSelectedDate(new SelectedDate(ngayKetThuc,thangKetThuc,namKetThuc));
                    tatChiTiet.setText(khuyenMaiThanhToan.getChiTiet());
                 }
             }
        }else if(radSanPham.isSelected()){
            if(cboSanPham.getSelectedIndex()==0){ 
                 mouseClick(row, 7, 8);
                
            }else if(cboSanPham.getSelectedIndex()==1){
                    String ma = tblDanhSachKhuyenMai.getValueAt(row, 0).toString();
                    lblMaKhuyenMaiKyTu.setText(ma);
                    txtTenKhuyenMai.setText(tblDanhSachKhuyenMai.getValueAt(row, 1).toString());
                    txtSanPham1.setText(tblDanhSachKhuyenMai.getValueAt(row, 2).toString());
                    int namBatDau= Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 3).toString().substring(0,4));
                    int thangBatDau = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 3).toString().substring(5,7));
                    int ngayBatDau = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 3).toString().substring(8,10));
                    dateNgayBatDau.setSelectedDate(new SelectedDate(ngayBatDau,thangBatDau,namBatDau));
                    int namKetThuc = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 4).toString().substring(0,4));
                    int thangKetThuc = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 4).toString().substring(5,7));
                    int ngayKetThuc = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 4).toString().substring(8,10));
                    dateNgayKetThuc.setSelectedDate(new SelectedDate(ngayKetThuc,thangKetThuc,namKetThuc));
                    if(radGiaTienSanPham.isSelected()){
                        giamGiaSanPham_DAO = new GiamGiaSanPham_DAO();
                        ArrayList<GiamGiaSanPham> dsGiamGia = giamGiaSanPham_DAO.layDanhSachGiamGiaSanPham_GiaTien(1);
                        for (GiamGiaSanPham giamGiaSanPham_GiaTien : dsGiamGia) {
                            if(ma.equals(giamGiaSanPham_GiaTien.getMaGiamGiaSanPham())){
                                dinhDangSo(giamGiaSanPham_GiaTien.getSoTienGiam()+"", txtSanPham3);
                                tatChiTiet.setText(giamGiaSanPham_GiaTien.getChiTiet());
                            }
                        }
                    }else if (radTyLeSanPham.isSelected()){
                        giamGiaSanPham_DAO = new GiamGiaSanPham_DAO();
                        ArrayList<GiamGiaSanPham> dsGiamGia = giamGiaSanPham_DAO.layDanhSachGiamGiaSanPham_TyLe(2);
                        for (GiamGiaSanPham giamGiaSanPham_TyLe : dsGiamGia) {
                        if(ma.equals(giamGiaSanPham_TyLe.getMaGiamGiaSanPham())){
                            txtSanPham4.setText(giamGiaSanPham_TyLe.getTyLeGiam()+" %");
                            tatChiTiet.setText(giamGiaSanPham_TyLe.getChiTiet());
                        }
                    }
                    }
            }else if(cboSanPham.getSelectedIndex()==2){
                       mouseClick(row, 3, 4);
            }else if(cboSanPham.getSelectedIndex()==3){
                 mouseClick(row, 5, 6);
            }
            
            
        }
    }//GEN-LAST:event_tblDanhSachKhuyenMaiMouseClicked
    public void mouseClick(int row,int loai, int loai1){
       String ma = tblDanhSachKhuyenMai.getValueAt(row, 0).toString();
                        lblMaKhuyenMaiKyTu.setText(ma);
                        txtTenKhuyenMai.setText( tblDanhSachKhuyenMai.getValueAt(row, 1).toString());
                        int namBatDau= Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 2).toString().substring(0,4));
                        int thangBatDau = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 2).toString().substring(5,7));
                        int ngayBatDau = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 2).toString().substring(8,10));
                        dateNgayBatDau.setSelectedDate(new SelectedDate(ngayBatDau,thangBatDau,namBatDau));
                        int namKetThuc = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 3).toString().substring(0,4));
                        int thangKetThuc = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 3).toString().substring(5,7));
                        int ngayKetThuc = Integer.parseInt( tblDanhSachKhuyenMai.getValueAt(row, 3).toString().substring(8,10));
                        dateNgayKetThuc.setSelectedDate (new SelectedDate(ngayKetThuc,thangKetThuc,namKetThuc));
                   
                    if(radGiaTienSanPham.isSelected()){
                        giamGiaSanPham_DAO = new GiamGiaSanPham_DAO();
                        ArrayList<GiamGiaSanPham> dsGiamGia = giamGiaSanPham_DAO.layDanhSachGiamGiaSanPham_GiaTien( loai);
                        for (GiamGiaSanPham giamGiaSanPham_GiaTien : dsGiamGia) {
                            if(ma.equals(giamGiaSanPham_GiaTien.getMaGiamGiaSanPham())){
                                dinhDangSo(giamGiaSanPham_GiaTien.getSoTienGiam()+"", txtSanPham3);
                                tatChiTiet.setText(giamGiaSanPham_GiaTien.getChiTiet());
                            }
                        }
                    }else if (radTyLeSanPham.isSelected()){
                        giamGiaSanPham_DAO = new GiamGiaSanPham_DAO();
                        ArrayList<GiamGiaSanPham> dsGiamGia = giamGiaSanPham_DAO.layDanhSachGiamGiaSanPham_TyLe( loai1);
                        for (GiamGiaSanPham giamGiaSanPham_TyLe : dsGiamGia) {
                        if(ma.equals(giamGiaSanPham_TyLe.getMaGiamGiaSanPham())){
                            txtSanPham4.setText(giamGiaSanPham_TyLe.getTyLeGiam()+" %");
                            tatChiTiet.setText(giamGiaSanPham_TyLe.getChiTiet());
                        }
                    }
         }
    }
    private void radTyLeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radTyLeActionPerformed
        // TODO add your handling code here:
        lblViTri1.setText("Phần trăm giảm");
        lblViTri2.setText("Giá trị tối thiểu đơn hàng");
        
        txtGiaTien1.setVisible(false);
        txtGiaTien2.setVisible(false);
        
        txtSanPham1.setVisible(false);
       
        txtSanPham3.setVisible(false);
        txtSanPham4.setVisible(false);
        radGiaTienSanPham.setVisible(false);
        cboSanPham.setVisible(false);
        radTyLeSanPham.setVisible(false);
       
        
        txtTyLe1.setVisible(true);
        txtTyLe2.setVisible(true);
        txtTyLe3.setVisible(true);
        txtTyLe4.setVisible(true);
        
        lblViTri1.setVisible(true);
        lblViTri2.setVisible(true);
        lblViTri3.setVisible(true);
        lblViTri4.setVisible(true);
        lblViTri3.setText("Giảm tối đa");
        lblViTri4.setText("Số lượng");
        
        danhSachKhuyenMai_TyLe();
        lamMoiDuLieu_TyLe();
    }//GEN-LAST:event_radTyLeActionPerformed
    public void lamMoiDuLieu_TyLe(){
        maHoaDon_TyLe(lblMaKhuyenMaiKyTu);
        txtTenKhuyenMai.setText("");
        txtTyLe1.setText("");
        txtTyLe2.setText("");
        txtTyLe3.setText("");
        txtTyLe4.setText("");
        txtTenKhuyenMai.setText("");
        tatChiTiet.setText("");
    }
    public void maHoaDon_TyLe(JLabel lbl){
        khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToan_DAO();
        try {
            if(radGiaTien.isSelected()){
                lbl.setText(khuyenMaiThanhToan_DAO.generateMaKhuyenMai_GiaTri());
            }else
                lbl.setText( khuyenMaiThanhToan_DAO.generateMaKhuyenMai_TyLe());
            
        } catch (SQLException ex) {
            Logger.getLogger(pnlKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void radGiaTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radGiaTienActionPerformed
        // TODO add your handling code here:
        giaTienRad();
        lamMoiDuLieu_GiaTri();
    }//GEN-LAST:event_radGiaTienActionPerformed
    public void lamMoiDuLieu_GiaTri(){
        txtTenKhuyenMai.setText("");
        maHoaDon_GiaTri(lblMaKhuyenMaiKyTu);
        txtGiaTien1.setText("");
        txtGiaTien2.setText("");                        
        txtGiaTien3.setText("");
        txtTenKhuyenMai.setText("");
        tatChiTiet.setText("");
    }
    public void maHoaDon_GiaTri(JLabel lbl){
        khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToan_DAO();
        try {
           lbl.setText( khuyenMaiThanhToan_DAO.generateMaKhuyenMai_GiaTri());
            
        } catch (SQLException ex) {
            Logger.getLogger(pnlKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void giaTienRad(){
        lblViTri1.setText("Số tiền giảm");
        lblViTri2.setText("Giá trị tối thiểu đơn hàng");
        
        lblViTri1.setVisible(true);
        lblViTri2.setVisible(true);
        lblViTri3.setVisible(true);
        lblViTri3.setText("Số lượng");
        
        lblViTri4.setVisible(false);
        
        txtSanPham1.setVisible(false);
      
        txtSanPham3.setVisible(false);
        txtSanPham4.setVisible(false);
        radGiaTienSanPham.setVisible(false);
        cboSanPham.setVisible(false);
        radTyLeSanPham.setVisible(false);
        
        txtTyLe1.setVisible(false);
        txtTyLe2.setVisible(false);
        txtTyLe3.setVisible(false);
        txtTyLe4.setVisible(false);
         
        txtGiaTien1.setVisible(true);
        txtGiaTien2.setVisible(true);
        txtGiaTien3.setVisible(true);
        
        
        danhSachKhuyenMai_GiaTien();
    }
    
    private void radSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSanPhamActionPerformed
        // TODO add your handling code here:
       
        lblViTri2.setText("Tên sản phẩm");
        
        lblViTri1.setVisible(false);
        lblViTri3.setVisible(false);
        lblViTri4.setVisible(false);
        
        txtSanPham1.setVisible(true);
       
        
        txtSanPham3.setVisible(true);
        txtSanPham4.setVisible(true);
        txtSanPham4.setEnabled(false);
        
        cboSanPham.setVisible(true);
        radGiaTienSanPham.setVisible(true);
        radTyLeSanPham.setVisible(true);
        
        txtTyLe1.setVisible(false);
        txtTyLe2.setVisible(false);
        txtTyLe3.setVisible(false);
        txtTyLe4.setVisible(false);
        
        txtGiaTien1.setVisible(false);
        txtGiaTien2.setVisible(false);
        txtGiaTien3.setVisible(false);
        danhSachGiamGia();
        txtTenKhuyenMai.setText("");
        lamMoi_sanPham();
        cboSanPham.setSelectedIndex(1);
        
        
    }//GEN-LAST:event_radSanPhamActionPerformed
    public void lamMoi_sanPham(){
        maHoaDon_SanPham(lblMaKhuyenMaiKyTu);
        txtSanPham1.setText("");
        txtSanPham3.setText("");
        txtSanPham4.setText("");
        
        txtTenKhuyenMai.setText("");
        tatChiTiet.setText("");
    }

    public void lamMoi(){
        if(radGiaTien.isSelected()){
            lamMoiDuLieu_GiaTri();
        }else if(radTyLe.isSelected()){
            lamMoiDuLieu_TyLe();
        }else if(radSanPham.isSelected()){
             lamMoi_sanPham();
        }
    }
    public void maHoaDon_SanPham(JLabel lbl){
        giamGiaSanPham_DAO = new GiamGiaSanPham_DAO();
        try {
            if(radGiaTienSanPham.isSelected()){
                lbl.setText( giamGiaSanPham_DAO.generateGiamGiaSanPham_GiaTien());
            }else{
                lbl.setText( giamGiaSanPham_DAO.generateGiamGiaSanPham_TyLe());
            }
        } catch (SQLException ex) {
            Logger.getLogger(pnlKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void txtGiaTien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaTien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaTien1ActionPerformed

    private void scrDanhSachSanPhamTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrDanhSachSanPhamTimKiemMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_scrDanhSachSanPhamTimKiemMouseClicked

    private void radGiaTienSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radGiaTienSanPhamActionPerformed
        // TODO add your handling code here:
        isSelected(txtSanPham3,txtSanPham4);
        lamMoi_sanPham();
        danhSachGiamGia();
    }//GEN-LAST:event_radGiaTienSanPhamActionPerformed
    public void isSelected(JTextField txt1, JTextField txt2){
        txt1.setVisible(true);
        txt1.setEditable(true);
        txt1.setEnabled(true);
        txt2.setEnabled(false);
        txt2.setText("");
    }
    private void radTyLeSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radTyLeSanPhamActionPerformed
        // TODO add your handling code here:
        isSelected(txtSanPham4,txtSanPham3);
        lamMoi_sanPham();
        danhSachGiamGia();
    }//GEN-LAST:event_radTyLeSanPhamActionPerformed
    
    private void btnNgayBatDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayBatDauActionPerformed
        // TODO add your handling code here:
        dateNgayBatDau.showPopup();
        demNgayBatDau++;
    }//GEN-LAST:event_btnNgayBatDauActionPerformed

    private void btnNgayKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayKetThucActionPerformed
        // TODO add your handling code here:
        dateNgayKetThuc.showPopup();
        demNgayKetThuc++;
    }//GEN-LAST:event_btnNgayKetThucActionPerformed
    int manHinh = 0;
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_formMouseClicked

    private void dateNgayKetThucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateNgayKetThucMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_dateNgayKetThucMouseClicked
    
    private void dateNgayBatDauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateNgayBatDauMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_dateNgayBatDauMouseClicked

    private void txtNgayBatDauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNgayBatDauMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtNgayBatDauMouseClicked

    private void txtNgayBatDauFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayBatDauFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtNgayBatDauFocusLost

    private void txtNgayBatDauFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayBatDauFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayBatDauFocusGained

    private void dateNgayBatDauMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateNgayBatDauMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_dateNgayBatDauMouseExited

    private void dateNgayBatDauMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateNgayBatDauMouseEntered
        // TODO add your handling code here:
        //System.out.println("Pannel.pnlKhuyenMai.dateNgayBatDauMouseEntered()");
    }//GEN-LAST:event_dateNgayBatDauMouseEntered

    private void dateNgayBatDauMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateNgayBatDauMousePressed
        // TODO add your handling code here:
        System.out.println("Pannel.pnlKhuyenMai.dateNgayBatDauMousePressed()");
    }//GEN-LAST:event_dateNgayBatDauMousePressed

    private void dateNgayBatDauFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateNgayBatDauFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_dateNgayBatDauFocusGained

    private void dateNgayBatDauMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateNgayBatDauMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_dateNgayBatDauMouseReleased

    private void cboSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSanPhamActionPerformed
        // TODO add your handling code here:
        switch (cboSanPham.getSelectedIndex()) {
            case 0 -> {
                lblViTri2.setVisible(false);
                txtSanPham1.setVisible(false);
                danhSachGiamGia();
            }
            case 1 -> {
                lblViTri2.setVisible(true);
                txtSanPham1.setVisible(true);
                 danhSachGiamGia();
            }
            case 2 -> {
                lblViTri2.setVisible(false);
                txtSanPham1.setVisible(false);
                danhSachGiamGia();
            }
            case 3 -> {
                lblViTri2.setVisible(false);
                txtSanPham1.setVisible(false);
                 danhSachGiamGia();
            }
            default -> {
            }
        }
    }//GEN-LAST:event_cboSanPhamActionPerformed

    private void txtTyLe3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTyLe3FocusGained
        // TODO add your handling code here:
        if(txtTyLe3.getText().equals("Không giới hạn")){
            txtTyLe3.setText("0.0");
        }
    }//GEN-LAST:event_txtTyLe3FocusGained

    private void txtTyLe3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTyLe3FocusLost
        // TODO add your handling code here:
        if(txtTyLe3.getText().equals("0.0")){
             txtTyLe3.setText("Không giới hạn");
        }
        
           
    }//GEN-LAST:event_txtTyLe3FocusLost
    private LocalDate ngay(ServiceUser.DateChooser dateTextField){
    SelectedDate selectedNgayKetThuc = dateTextField.getSelectedDate();
    String inputDate = selectedNgayKetThuc.getDay() + "-" + selectedNgayKetThuc.getMonth() + "-" + selectedNgayKetThuc.getYear();
    LocalDate ngay = parseDate(inputDate);
    return ngay;
    }
    private String handleDateChange_CapNhatTinhTrang(LocalDate  ngayBatDau ,LocalDate ngayKetThuc) {
    
    String tinhTrang ;
    
  
    // Lấy ngày hiện tại
    LocalDate local = LocalDate.now();
    if (ngayBatDau != null && ngayKetThuc != null) {
        if (local.isEqual(ngayBatDau) || (local.isAfter(ngayBatDau) && local.isBefore(ngayKetThuc))) {
            // Nếu ngày hiện tại nằm trong khoảng từ ngày bắt đầu đến ngày kết thúc
            tinhTrang = "Đang khuyến mãi";
        } else if (local.isBefore(ngayBatDau)) {
            // Ngày nhập vào lớn hơn ngày hiện tại, sự kiện sắp diễn ra
            tinhTrang = "Sắp khuyến mãi";
        } else if (local.isAfter(ngayKetThuc)) {
            // Sự kiện đã kết thúc
            tinhTrang = "Đã kết thúc";
        } else {
            tinhTrang = "Đang khuyến mãi";
        }
    } else {
        tinhTrang = "Ngày không hợp lệ";
    }

    return tinhTrang;
}
    private String handleDateChange(ServiceUser.DateChooser dateTextField, ServiceUser.DateChooser dateTextField1) {
    String tinhTrang = "";
    // Chuyển đổi chuỗi thành LocalDate
    LocalDate ngayBatDau = ngay(dateTextField);
    LocalDate ngayKetThuc =ngay(dateTextField1);
    // Lấy ngày hiện tại
    LocalDate local = LocalDate.now();
    if (ngayBatDau != null && ngayKetThuc != null) {
        if (local.isEqual(ngayBatDau) || (local.isAfter(ngayBatDau) && local.isBefore(ngayKetThuc))) {
            // Nếu ngày hiện tại nằm trong khoảng từ ngày bắt đầu đến ngày kết thúc
            tinhTrang = "Đang khuyến mãi";
        } else if (local.isBefore(ngayBatDau)) {
            // Ngày nhập vào lớn hơn ngày hiện tại, sự kiện sắp diễn ra
            tinhTrang = "Sắp khuyến mãi";
        } else if (local.isAfter(ngayKetThuc)) {
            // Sự kiện đã kết thúc
            tinhTrang = "Đã kết thúc";
        } else {
            tinhTrang = "Đang khuyến mãi";
        }
    } else {
        tinhTrang = "Ngày không hợp lệ";
    }

    return tinhTrang;
}
    
    private LocalDate parseDate(String inputDate) {
    String[] parts = inputDate.split("-"); // Tách chuỗi thành mảng các phần tử

    if (parts.length == 3) { // Kiểm tra có đủ phần tử ngày, tháng, năm không
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        // Kiểm tra và xử lý ngày, tháng, năm nếu cần
        // Ví dụ: nếu các thành phần không thỏa mãn điều kiện ngày, tháng, năm hợp lệ

        return LocalDate.of(year, month, day); // Tạo đối tượng LocalDate
    } else {
        // Xử lý khi chuỗi không đúng định dạng
        return null; // hoặc throw exception tùy thuộc vào cách xử lý của bạn
    }
}
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        
        if(radGiaTien.isSelected()){
                themGiaTienKhuyenMai();
                lamMoiDuLieu_GiaTri();
        }else if(radTyLe.isSelected()){
            themTyLeKhuyenMai();
            lamMoiDuLieu_TyLe();
        }else if(radSanPham.isSelected()){
            if(cboSanPham.getSelectedIndex() ==0){
               themToanBoGiamGia(7,8); 
               lamMoi_sanPham();
            }else if(cboSanPham.getSelectedIndex() ==1){
               themGiamGiaTungSanPham();
               lamMoi_sanPham();
            }else if(cboSanPham.getSelectedIndex() == 2){
               
               themToanBoGiamGia(3,4);
                 lamMoi_sanPham();
            }else if(cboSanPham.getSelectedIndex() ==3){
               
                themToanBoGiamGia(5,6);
                 lamMoi_sanPham();
            }
        }
        
    }//GEN-LAST:event_btnThemActionPerformed
    
    public void themTyLeKhuyenMai(){
            KhuyenMaiThanhToan khuyenMaiThanhToan_tyLe = khuyenMaiThanhToan_TyLe();
            khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToan_DAO();
            
            ArrayList<KhuyenMaiThanhToan> dsKhuyenMai = khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_TyLe();
            int dem = 0;
            for (KhuyenMaiThanhToan khuyenMaiThanhToan : dsKhuyenMai) {
                if(khuyenMaiThanhToan_tyLe.getMaKhuyenMai().equals(khuyenMaiThanhToan.getMaKhuyenMai())){
                    dem++;
                }
            }
            if(dem!=0){
                 JOptionPane.showMessageDialog(this, "không thể thêm");
            }else{
                if( khuyenMaiThanhToan_DAO.themGiamGiaSanPham_TyLe(khuyenMaiThanhToan_tyLe)){
                maHoaDon_TyLe(lblMaKhuyenMaiKyTu);
                danhSachKhuyenMai_TyLe();
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                }    
            }
            
           
            
    }
    public KhuyenMaiThanhToan khuyenMaiThanhToan_GiaTien(){
          String maKhuyenMai = lblMaKhuyenMaiKyTu.getText();
            String tenKhuyenMai = txtTenKhuyenMai.getText();
            LocalDate ngayBatDau = ngay(dateNgayBatDau);
            LocalDate ngayKetThuc = ngay(dateNgayKetThuc);
            String tinhTrang = handleDateChange(dateNgayBatDau, dateNgayKetThuc);
            
            
            
            
            String txtgiaTriToiThieuDonHang = txtGiaTien2.getText().replaceAll("[^\\d.]+", "");
            double giaTriToiThieuDonHang = Double.parseDouble(txtgiaTriToiThieuDonHang);
            
            String txtSoTienGiam = txtGiaTien1.getText().replaceAll("[^\\d.]+", "");
            double soTienGiam = Double.parseDouble(txtSoTienGiam);
     
            
            String txtSoLuong= txtGiaTien3.getText().replaceAll("[^\\d.]+", "");
            int soLuong = Integer.parseInt(txtSoLuong);
            
            
            String chiTiet =  tatChiTiet.getText();
             return new KhuyenMaiThanhToan(maKhuyenMai, tenKhuyenMai, (float) giaTriToiThieuDonHang, (float)soTienGiam, ngayBatDau, ngayKetThuc, tinhTrang, 2, chiTiet, soLuong);
    }
    public void themGiaTienKhuyenMai(){
            KhuyenMaiThanhToan khuyenMaiThanhToan_GiaTien = khuyenMaiThanhToan_GiaTien();
            khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToan_DAO();
            ArrayList<KhuyenMaiThanhToan> dsKhuyenMai = khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_GiaTien();
            int dem = 0;
            for (KhuyenMaiThanhToan khuyenMaiThanhToan : dsKhuyenMai) {
                if(khuyenMaiThanhToan_GiaTien.getMaKhuyenMai().equals(khuyenMaiThanhToan.getMaKhuyenMai())){
                    dem++;
                }
            }
            if(dem!=0){
                JOptionPane.showMessageDialog(this, "không thể thêm");
            }else{
                khuyenMaiThanhToan_DAO.themGiamGiaSanPham_GiaTien(khuyenMaiThanhToan_GiaTien);
            maHoaDon_TyLe(lblMaKhuyenMaiKyTu);
            danhSachKhuyenMai_GiaTien();
            }
           
    }
    public KhuyenMaiThanhToan khuyenMaiThanhToan_TyLe(){
            String maKhuyenMai = lblMaKhuyenMaiKyTu.getText();
            String tenKhuyenMai = txtTenKhuyenMai.getText();
            LocalDate ngayBatDau = ngay(dateNgayBatDau);
            LocalDate ngayKetThuc = ngay(dateNgayKetThuc);
            String tinhTrang = handleDateChange(dateNgayBatDau, dateNgayKetThuc);
            
            String txtphanTramGiam = txtTyLe1.getText().replaceAll("[^\\d.]+", "");
            double phanTramGiam = Double.parseDouble(txtphanTramGiam);
            
            String txtgiaTriToiThieuDonHang = txtTyLe2.getText().replaceAll("[^\\d.]+", "");
            double giaTriToiThieuDonHang = Double.parseDouble(txtgiaTriToiThieuDonHang);
            
            double giamToiDa = 0.0;
            if(!txtTyLe3.getText().equals("Không giới hạn")){
                String txtGiamToiDa = txtTyLe3.getText().replaceAll("[^\\d.]+", "");
                giamToiDa = Double.parseDouble(txtGiamToiDa);
            }else{
                  giamToiDa = 0.0;  
            }
            String txtSoLuong= txtTyLe4.getText().replaceAll("[^\\d.]+", "");
            int soLuong = Integer.parseInt(txtSoLuong);
            
            String chiTiet =  tatChiTiet.getText();
            return new KhuyenMaiThanhToan(maKhuyenMai, tenKhuyenMai, (float) phanTramGiam, (float) giaTriToiThieuDonHang,(float) giamToiDa, ngayBatDau, ngayKetThuc, tinhTrang, 1, chiTiet, soLuong);
    }
    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        
        if(radGiaTien.isSelected()){
                  khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToan_DAO();
                   KhuyenMaiThanhToan khuyenMaiThanhToan_GiaTien = khuyenMaiThanhToan_GiaTien();
                if(khuyenMaiThanhToan_DAO.capNhatTyLe_KhuyenMai(khuyenMaiThanhToan_GiaTien)){
                   JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                   danhSachKhuyenMai_GiaTien();
                }
        }else if(radTyLe.isSelected()){
            khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToan_DAO();
             KhuyenMaiThanhToan khuyenMaiThanhToan_tyLe = khuyenMaiThanhToan_TyLe();
             if(khuyenMaiThanhToan_DAO.capNhatTyLe_KhuyenMai(khuyenMaiThanhToan_tyLe)){
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                danhSachKhuyenMai_TyLe();
             }
        }else if(radSanPham.isSelected()){
            if(cboSanPham.getSelectedIndex() ==0){
               capNhatToanBoGiaGiam_SanPham(7,8);
            }else if(cboSanPham.getSelectedIndex() ==1){
               capNhatGiaTungSanPham();
            }else if(cboSanPham.getSelectedIndex() == 2){
                capNhatToanBoGiaGiam_SanPham(3,4);
            }else if(cboSanPham.getSelectedIndex() == 3){
               capNhatToanBoGiaGiam_SanPham(5,6);
            }
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void txtTyLe4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTyLe4FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtTyLe4FocusGained

    private void txtTyLe4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTyLe4FocusLost
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtTyLe4FocusLost

    private void txtTyLe1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTyLe1FocusGained
        // TODO add your handling code here:
        String priceWithoutCurrency = txtTyLe1.getText().replaceAll("[^\\d.]+", "");
        double parsedNumber = Double.parseDouble(priceWithoutCurrency);
        txtTyLe1.setText( parsedNumber+"");
    }//GEN-LAST:event_txtTyLe1FocusGained

    private void txtTyLe1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTyLe1FocusLost
        // TODO add your handling code here:
          txtTyLe1.setText(txtTyLe1.getText()+" %");
    }//GEN-LAST:event_txtTyLe1FocusLost

    private void txtSanPham4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSanPham4FocusGained
        // TODO add your handling code here:
        String priceWithoutCurrency = txtSanPham4.getText().replaceAll("[^\\d.]+", "");
        double parsedNumber = Double.parseDouble(priceWithoutCurrency);
        txtSanPham4.setText( parsedNumber+"");
    }//GEN-LAST:event_txtSanPham4FocusGained

    private void txtSanPham4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSanPham4FocusLost
        // TODO add your handling code here:
        txtSanPham4.setText(txtSanPham4.getText()+" %");
    }//GEN-LAST:event_txtSanPham4FocusLost

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        lamMoi();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox1.isSelected()){
             System.out.println("Pannel.pnlKhuyenMai.jComboBox1ActionPerformed()");
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed
    public void capNhatGiaTungSanPham(){
        if(radGiaTienSanPham.isSelected()){
                    GiamGiaSanPham giamGiaSanPham = giamGiaSanPham_GiaTien(1);
                        giamGiaSanPham_DAO = new GiamGiaSanPham_DAO();
                        ArrayList<GiamGiaSanPham> dsGiamGiaSanPham = giamGiaSanPham_DAO.layDanhSachGiamGiaSanPham();
                        int cotMoc = 0;
                        String ma = "";
                        for (GiamGiaSanPham dsGiamGia : dsGiamGiaSanPham) {
                            if(dsGiamGia.getSanPham().getMaSanPham().equals(giamGiaSanPham.getSanPham().getMaSanPham())&& dsGiamGia.getLoai() == 1){
                                ma = dsGiamGia.getMaGiamGiaSanPham();
                                cotMoc ++;
                            }
                        }
                        if(cotMoc!=0){
                              giamGiaSanPham.setMaGiamGiaSanPham(ma);
                            giamGiaSanPham_DAO.updateGiamGiaSanPham_GiaTien(giamGiaSanPham);
                             JOptionPane.showMessageDialog(this, "Đã cập nhật");
                        }
                }else if(radTyLeSanPham.isSelected()){
                        GiamGiaSanPham giamGiaSanPham = giamGiaSanPham_TyLe(2);
                        giamGiaSanPham_DAO = new GiamGiaSanPham_DAO();
                        ArrayList<GiamGiaSanPham> dsGiamGiaSanPham = giamGiaSanPham_DAO.layDanhSachGiamGiaSanPham();
                        int cotMoc = 0;
                        String ma = "";
                        for (GiamGiaSanPham dsGiamGia : dsGiamGiaSanPham) {
                            if(dsGiamGia.getSanPham().getMaSanPham().equals(giamGiaSanPham.getSanPham().getMaSanPham()) && dsGiamGia.getLoai() == 2){
                                cotMoc ++;
                                ma = dsGiamGia.getMaGiamGiaSanPham();
                            }
                        }
                        if(cotMoc!=0){
                            giamGiaSanPham.setMaGiamGiaSanPham(ma);
                            giamGiaSanPham_DAO.updateGiamGiaSanPham_TyLe(giamGiaSanPham);
                            JOptionPane.showMessageDialog(this, "Đã cập nhật");
                        }
                }
    }
    public void capNhatToanBoGiaGiam_SanPham(int loai1, int loai2){
        if(radGiaTienSanPham.isSelected()){
                    GiamGiaSanPham giamGiaSanPham = giamGiaSanPham_GiaTien_SanPham(loai1);
                        giamGiaSanPham_DAO = new GiamGiaSanPham_DAO();
                        if(giamGiaSanPham_DAO.updateGiamGiaSanPham_GiaTien(giamGiaSanPham)){
                           JOptionPane.showMessageDialog(this, "Đã cập nhật");
                        }
                }else if(radTyLeSanPham.isSelected()){
                        GiamGiaSanPham giamGiaSanPham = giamGiaSanPham_TyLe_SanPham(loai2);
                        giamGiaSanPham_DAO = new GiamGiaSanPham_DAO();
                        if(giamGiaSanPham_DAO.updateGiamGiaSanPham_TyLe(giamGiaSanPham)){
                           JOptionPane.showMessageDialog(this, "Đã cập nhật");
                        }
                }
    }
    public void themGiamGiaTungSanPham(){
         if(radGiaTienSanPham.isSelected()){
                    GiamGiaSanPham giamGiaSanPham = giamGiaSanPham_GiaTien(1);
                        giamGiaSanPham_DAO = new GiamGiaSanPham_DAO();
                        ArrayList<GiamGiaSanPham> dsGiamGiaSanPham = giamGiaSanPham_DAO.layDanhSachGiamGiaSanPham();
                        int cotMoc = 0;
                        String ma = "";
                        for (GiamGiaSanPham dsGiamGia : dsGiamGiaSanPham) {
                            if(dsGiamGia.getSanPham().getMaSanPham().equals(giamGiaSanPham.getSanPham().getMaSanPham())&& dsGiamGia.getLoai() == 1){
                                ma = dsGiamGia.getMaGiamGiaSanPham();
                                cotMoc ++;
                            }
                        }
                        if(cotMoc!=0){
                              giamGiaSanPham.setMaGiamGiaSanPham(ma);
                            giamGiaSanPham_DAO.updateGiamGiaSanPham_GiaTien(giamGiaSanPham);
                             JOptionPane.showMessageDialog(this, "Đã cập nhật");
                        }else{
                                if(giamGiaSanPham_DAO.themGiamGiaSanPham_GiaTien(giamGiaSanPham)){
                                    JOptionPane.showMessageDialog(this, "Đã thêm");
                                }  
                        } 
                }else if(radTyLeSanPham.isSelected()){
                        GiamGiaSanPham giamGiaSanPham = giamGiaSanPham_TyLe(2);
                        giamGiaSanPham_DAO = new GiamGiaSanPham_DAO();
                        ArrayList<GiamGiaSanPham> dsGiamGiaSanPham = giamGiaSanPham_DAO.layDanhSachGiamGiaSanPham();
                        int cotMoc = 0;
                        String ma = "";
                        for (GiamGiaSanPham dsGiamGia : dsGiamGiaSanPham) {
                            if(dsGiamGia.getSanPham().getMaSanPham().equals(giamGiaSanPham.getSanPham().getMaSanPham()) && dsGiamGia.getLoai() == 2){
                                cotMoc ++;
                                ma = dsGiamGia.getMaGiamGiaSanPham();
                            }
                        }
                        if(cotMoc!=0){
                            giamGiaSanPham.setMaGiamGiaSanPham(ma);
                            giamGiaSanPham_DAO.updateGiamGiaSanPham_TyLe(giamGiaSanPham);
                            JOptionPane.showMessageDialog(this, "Đã cập nhật");
                        }else{
                                if(giamGiaSanPham_DAO.themGiamGiaSanPham_TyLe(giamGiaSanPham)){
                                    JOptionPane.showMessageDialog(this, "Đã thêm");
                                }  
                        }  
                }
    }
    public void themToanBoGiamGia(int loai1,int loai2){
        if(radGiaTienSanPham.isSelected()){
                    GiamGiaSanPham giamGiaSanPham = giamGiaSanPham_GiaTien_SanPham(loai1);
                        giamGiaSanPham_DAO = new GiamGiaSanPham_DAO();
                        if(giamGiaSanPham_DAO.themGiamGiaSanPham_GiaTien(giamGiaSanPham)){
                           JOptionPane.showMessageDialog(this, "Đã thêm");
                        }
                }else if(radTyLeSanPham.isSelected()){
                        GiamGiaSanPham giamGiaSanPham = giamGiaSanPham_TyLe_SanPham(loai2);
                        giamGiaSanPham_DAO = new GiamGiaSanPham_DAO();
                        if(giamGiaSanPham_DAO.themGiamGiaSanPham_TyLe(giamGiaSanPham)){
                           JOptionPane.showMessageDialog(this, "Đã thêm");
                        }
                }
        danhSachGiamGia();
    }
    public GiamGiaSanPham giamGiaSanPham_GiaTien(int loai){
            String maKhuyenMai = lblMaKhuyenMaiKyTu.getText();
            String tenKhuyenMai = txtTenKhuyenMai.getText();
            String tenSanPham = txtSanPham1.getText();
            String maSanPham = "";
                sach_DAO = new Sach_DAO();
                vanPhongPham_DAO = new VanPhongPham_DAO();

                ArrayList<Sach> dssach = sach_DAO.layDanhSanPhamSach();
                for (Sach sach : dssach) {
                    if(tenSanPham.equals(sach.getTenSanPham()))
                        maSanPham = sach.getMaSanPham();
                }
                ArrayList<VanPhongPham> dsvpp = vanPhongPham_DAO.layDanhSanPhamVanPhongPham();
                 // Tạo một ArrayList mới để chứa cả hai danh sách
                for (VanPhongPham vanPhongPham : dsvpp) {
                     if(tenSanPham.equals(vanPhongPham.getTenSanPham()))
                        maSanPham = vanPhongPham.getMaSanPham();
                }
                
               
                
                String txtsoTienGiam= txtSanPham3.getText().replaceAll("[^\\d.]+", "");
                float soTienGiam = Float.parseFloat(txtsoTienGiam);
                
                LocalDate ngayBatDau = ngay(dateNgayBatDau);
                LocalDate ngayKetThuc = ngay(dateNgayKetThuc);
                String chiTiet = tatChiTiet.getText();
                String tinhTrang = handleDateChange(dateNgayBatDau, dateNgayKetThuc);
                SanPham sanPham = new SanPham(maSanPham); 
                return new GiamGiaSanPham(maKhuyenMai, tenKhuyenMai, sanPham, soTienGiam, ngayBatDau, ngayKetThuc, tinhTrang, chiTiet, loai);
    }
    public GiamGiaSanPham giamGiaSanPham_GiaTien_SanPham(int loai){
        String maKhuyenMai = lblMaKhuyenMaiKyTu.getText();
        String tenKhuyenMai = txtTenKhuyenMai.getText();
        String txtSoLuong= txtSanPham3.getText().replaceAll("[^\\d.]+", "");
        float soTienGiam= Float.parseFloat(txtSoLuong);
        LocalDate ngayBatDau = ngay(dateNgayBatDau);
        LocalDate ngayKetThuc = ngay(dateNgayKetThuc);
        String chiTiet = tatChiTiet.getText();
        String tinhTrang = handleDateChange(dateNgayBatDau, dateNgayKetThuc);
       
        
        return new GiamGiaSanPham(maKhuyenMai, tenKhuyenMai, new SanPham(""), soTienGiam, ngayBatDau, ngayKetThuc, tinhTrang, chiTiet, loai);
    }
    public GiamGiaSanPham giamGiaSanPham_TyLe(int loai){
            String maKhuyenMai = lblMaKhuyenMaiKyTu.getText();
            String tenKhuyenMai = txtTenKhuyenMai.getText();
            String tenSanPham = txtSanPham1.getText();
            String maSanPham = "";
                sach_DAO = new Sach_DAO();
                vanPhongPham_DAO = new VanPhongPham_DAO();

                ArrayList<Sach> dssach = sach_DAO.layDanhSanPhamSach();
                for (Sach sach : dssach) {
                    if(tenSanPham.equals(sach.getTenSanPham()))
                        maSanPham = sach.getMaSanPham();
                }
                ArrayList<VanPhongPham> dsvpp = vanPhongPham_DAO.layDanhSanPhamVanPhongPham();
                 // Tạo một ArrayList mới để chứa cả hai danh sách
                for (VanPhongPham vanPhongPham : dsvpp) {
                     if(tenSanPham.equals(vanPhongPham.getTenSanPham()))
                        maSanPham = vanPhongPham.getMaSanPham();
                }
                
                LocalDate ngayBatDau = ngay(dateNgayBatDau);
                LocalDate ngayKetThuc = ngay(dateNgayKetThuc);
                String txtTyLe = txtSanPham4.getText().replaceAll("[^\\d.]+", "");
                float tyLe = Float.parseFloat(txtTyLe);
                String chiTiet = tatChiTiet.getText();
                String tinhTrang = handleDateChange(dateNgayBatDau, dateNgayKetThuc);
                SanPham sanPham = new SanPham(maSanPham); 
                return new GiamGiaSanPham(maKhuyenMai, tenKhuyenMai, sanPham, ngayBatDau, ngayKetThuc, tinhTrang, chiTiet, loai, tyLe);
    }
    public GiamGiaSanPham giamGiaSanPham_TyLe_SanPham(int loai){
            String maKhuyenMai = lblMaKhuyenMaiKyTu.getText();
            String tenKhuyenMai = txtTenKhuyenMai.getText();
            
                String maSanPham = "";
                LocalDate ngayBatDau = ngay(dateNgayBatDau);
                LocalDate ngayKetThuc = ngay(dateNgayKetThuc);
                
                String txtSoLuong= txtSanPham4.getText().replaceAll("[^\\d.]+", "");
                float tyLe = Float.parseFloat(txtSoLuong);
                
               
                String chiTiet = tatChiTiet.getText();
                String tinhTrang = handleDateChange(dateNgayBatDau, dateNgayKetThuc);
                SanPham sanPham = new SanPham(maSanPham); 
                return new GiamGiaSanPham(maKhuyenMai, tenKhuyenMai, sanPham, ngayBatDau, ngayKetThuc, tinhTrang, chiTiet, loai, tyLe);
    }
    public void danhSachGiamGia(){
      
        
        
         if(radGiaTien.isSelected()){
                
        }else if(radTyLe.isSelected()){
            
        }else if(radSanPham.isSelected()){
            if(cboSanPham.getSelectedIndex() ==0){
                if(radGiaTienSanPham.isSelected()){
                    danhSachSanPham_SanPham_ToanBo(7);
                }else if(radTyLeSanPham.isSelected()){
                    danhSachSanPham_SanPham_ToanBo(8);
                }
            }else if(cboSanPham.getSelectedIndex() ==1){
                if(radGiaTienSanPham.isSelected()){
                   danhSachSanPham(1);
               }else if(radTyLeSanPham.isSelected()){
                   danhSachSanPham(2);
               }
            }else if(cboSanPham.getSelectedIndex() == 2){
                if(radGiaTienSanPham.isSelected()){
                    danhSachSanPham_SanPham_ToanBo(3);
                }else if(radTyLeSanPham.isSelected()){
                    danhSachSanPham_SanPham_ToanBo(4);
                }
            }else if(cboSanPham.getSelectedIndex() == 3){
                if(radGiaTienSanPham.isSelected()){
                    danhSachSanPham_SanPham_ToanBo(5);
                }else if(radTyLeSanPham.isSelected()){
                    danhSachSanPham_SanPham_ToanBo(6);
                }
            }
        }
    }
    
    public void danhSachSanPham(int loai){
        int demSoLuong = 0;
        String colTieuDe[] = new String [] {"Mã Khuyến mãi", "Tên Khuyến mãi","Tên sản phẩm", "Ngày bắt đầu", "Ngày kết thúc", "Tình trạng"};
        DefaultTableModel model =  new DefaultTableModel(colTieuDe,0);
        Object[] row = null;
        giamGiaSanPham_DAO = new GiamGiaSanPham_DAO();
        ArrayList<Sach> dssach = sach_DAO.layDanhSanPhamSach();
        ArrayList<VanPhongPham> dsvpp = vanPhongPham_DAO.layDanhSanPhamVanPhongPham();
         // Tạo một ArrayList mới để chứa cả hai danh sách
        ArrayList<Object> combinedList = new ArrayList<>();
        // Thêm danh sách sách vào danh sách kết hợp
        combinedList.addAll(dssach);
        // Thêm danh sách văn phòng phẩm vào danh sách kết hợp
        combinedList.addAll(dsvpp);
        ArrayList<GiamGiaSanPham> dsGiamGiaSanPham = giamGiaSanPham_DAO.layDanhSachGiamGiaSanPham();
                for (GiamGiaSanPham giamGiaSanPham : dsGiamGiaSanPham) {
                   if(giamGiaSanPham.getLoai()==loai){
                    demSoLuong ++;
                    row = new Object[12];
                      row[0] =  giamGiaSanPham.getMaGiamGiaSanPham();
                      row[1] =  giamGiaSanPham.getTenGiamGia();
                        for (Object item : combinedList) {
                            if (item instanceof Sach) {
                               Sach sach = (Sach) item;
                               if(sach.getMaSanPham().equals(giamGiaSanPham.getSanPham().getMaSanPham()))
                                   row[2] = sach.getTenSanPham();
                           }else if (item instanceof VanPhongPham) {
                               VanPhongPham vpp = (VanPhongPham) item;
                               if(vpp.getMaSanPham().equals(giamGiaSanPham.getSanPham().getMaSanPham()))
                                   row[2] = vpp.getTenSanPham();
                               // Thực hiện các thao tác với đối tượng VanPhongPham
                           }
                       }
                      row[3] =  giamGiaSanPham.getNgayBatDau();
                      row[4] =  giamGiaSanPham.getNgayKetThuc();
                      row[5] =  giamGiaSanPham.getTinhTrang();
                      model.addRow(row);
                    }
               }   
        lblSoLuong.setText("Số lượng  : "+demSoLuong);
        tblDanhSachKhuyenMai.setModel(model);    
    }
    public void danhSachSanPham_SanPham_ToanBo(int loai){
        int demSoLuong = 0;
        String colTieuDe[] = new String [] {"Mã Khuyến mãi", "Tên Khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc", "Tình trạng"};
        DefaultTableModel model =  new DefaultTableModel(colTieuDe,0);
        Object[] row = null;
        giamGiaSanPham_DAO = new GiamGiaSanPham_DAO();
        ArrayList<GiamGiaSanPham> dsGiamGiaSanPham = giamGiaSanPham_DAO.layDanhSachGiamGiaSanPham();
                for (GiamGiaSanPham giamGiaSanPham : dsGiamGiaSanPham) {
                   if(giamGiaSanPham.getLoai()==loai){
                    demSoLuong ++;
                    row = new Object[12];
                      row[0] =  giamGiaSanPham.getMaGiamGiaSanPham();
                      row[1] =  giamGiaSanPham.getTenGiamGia();  
                      row[2] =  giamGiaSanPham.getNgayBatDau();
                      row[3] =  giamGiaSanPham.getNgayKetThuc();
                      row[4] =  giamGiaSanPham.getTinhTrang();
                      model.addRow(row);
                    }
               }   
        lblSoLuong.setText("Số lượng  : "+demSoLuong);
        tblDanhSachKhuyenMai.setModel(model);    
    }
    public void danhSachKhuyenMai_TyLe(){
        int dem =0;
        String colTieuDe[] = new String [] {"Mã Khuyến mãi", "Tên Khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc", "Tình trạng", "Số lượng"};
        DefaultTableModel model =  new DefaultTableModel(colTieuDe,0);
        Object[] row;
        khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToan_DAO();
        ArrayList<KhuyenMaiThanhToan> dsKhuyenMaiThanhToan = khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_TyLe();
        for (KhuyenMaiThanhToan khuyenMaiThanhToan : dsKhuyenMaiThanhToan) {
              row = new Object[12];
              row[0] = khuyenMaiThanhToan.getMaKhuyenMai();
              row[1] = khuyenMaiThanhToan.getTenKhuyenMai();
              row[2] = khuyenMaiThanhToan.getNgayBatDau();
              row[3] = khuyenMaiThanhToan.getNgayKetThuc();
              row[4] = khuyenMaiThanhToan.getTinhTrang();
              row[5] = khuyenMaiThanhToan.getSoLuong();
              model.addRow(row);
              dem++;
        }
        lblSoLuong.setText("Số lượng  : "+dem);
        tblDanhSachKhuyenMai.setModel(model);
    }
    public void danhSachKhuyenMai_GiaTien(){
        int dem = 0;
        String colTieuDe[] = new String [] {"Mã Khuyến mãi", "Tên Khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc", "Tình trạng", "Số lượng"};
        DefaultTableModel model =  new DefaultTableModel(colTieuDe,0);
        Object[] row;
        khuyenMaiThanhToan_DAO = new KhuyenMaiThanhToan_DAO();
        ArrayList<KhuyenMaiThanhToan> dsKhuyenMaiThanhToan = khuyenMaiThanhToan_DAO.layDanhSachKhuyenMai_GiaTien();
        for (KhuyenMaiThanhToan khuyenMaiThanhToan : dsKhuyenMaiThanhToan) {
            
            row = new Object[12];
              row[0] = khuyenMaiThanhToan.getMaKhuyenMai();
              row[1] = khuyenMaiThanhToan.getTenKhuyenMai();
              row[2] = khuyenMaiThanhToan.getNgayBatDau();
              row[3] = khuyenMaiThanhToan.getNgayKetThuc();
              row[4] = khuyenMaiThanhToan.getTinhTrang();
              row[5] = khuyenMaiThanhToan.getSoLuong();
              model.addRow(row);
              dem++;
        }
        lblSoLuong.setText("Số lượng  : "+dem);
        tblDanhSachKhuyenMai.setModel(model);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btg1;
    private javax.swing.ButtonGroup btg2;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnNgayBatDau;
    private javax.swing.JButton btnNgayKetThuc;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cboSanPham;
    private ServiceUser.DateChooser dateNgayBatDau;
    private ServiceUser.DateChooser dateNgayKetThuc;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMaKhuyenMaiKyTu;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTenKhuyenMai;
    private javax.swing.JLabel lblTinhTrang;
    private javax.swing.JLabel lblViTri1;
    private javax.swing.JLabel lblViTri2;
    private javax.swing.JLabel lblViTri3;
    private javax.swing.JLabel lblViTri4;
    private javax.swing.JLabel lblmaKhuyenMai;
    private javax.swing.JRadioButton radGiaTien;
    private javax.swing.JRadioButton radGiaTienSanPham;
    private javax.swing.JRadioButton radSanPham;
    private javax.swing.JRadioButton radTyLe;
    private javax.swing.JRadioButton radTyLeSanPham;
    private javax.swing.JScrollPane scrDanhSachSanPhamTimKiem;
    private javax.swing.JTextArea tatChiTiet;
    private javax.swing.JTable tblDanhSachKhuyenMai;
    private javax.swing.JTextField txtGiaTien1;
    private javax.swing.JTextField txtGiaTien2;
    private javax.swing.JTextField txtGiaTien3;
    private javax.swing.JTextField txtNgayBatDau;
    private javax.swing.JTextField txtNgayKetThuc;
    private ServiceUser.TextFieldSuggestion txtSanPham1;
    private javax.swing.JTextField txtSanPham3;
    private javax.swing.JTextField txtSanPham4;
    private javax.swing.JTextField txtTenKhuyenMai;
    private javax.swing.JTextField txtTyLe1;
    private javax.swing.JTextField txtTyLe2;
    private javax.swing.JTextField txtTyLe3;
    private javax.swing.JTextField txtTyLe4;
    // End of variables declaration//GEN-END:variables
}
