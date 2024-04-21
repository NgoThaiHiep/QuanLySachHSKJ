
package Pannel;


import DAO.CaLamViec_DAO;
import DAO.ChucVu_DAO;
import DAO.NhanVien_DAO;
import DAO.TaiKhoan_DAO;
import DAO_IMP.CaLamViecDAO_IMP;
import DAO_IMP.ChucVuDAO_IMP;
import DAO_IMP.NhanVienDAO_IMP;
import DAO_IMP.TaiKhoanDAO_IMP;
import entity.CaLamViec;
import entity.ChucVu;
import entity.NhanVien;
import entity.TaiKhoan;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ServiceUser.SelectedDate;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.JDialog;
/**
 *
 * @author FPTSHOP
 */
public class pnlThemNhanVien extends javax.swing.JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Creates new form ThemNhanVien
     */
    
    private String tp = "Tỉnh/Thành phố";
    private String q = "Quận/huyện";
    private String cboDiaChi = "";
    private ArrayList<String> cities ;
    private ArrayList<String> districts ;
    private ArrayList<String> wardsDistricts ;
    private TaiKhoan tk;
    private NhanVien_DAO nhanVien_DAO;
    private File selectedFile;
    private JFrame imageFrame;
    private NhanVien nv;
    private TaiKhoan_DAO taiKhoan_DAO;
    private ChucVu_DAO chucVuDAO;
    private CaLamViec_DAO caLam_DAO;
    public pnlThemNhanVien(TaiKhoan tk) throws IOException, SQLException {
        this.tk = tk;
        initComponents();
        init();
        lblCaLam.setVisible(false);
        cboCaLam.setVisible(false);
          
    }
        
    public void init() throws IOException, SQLException{
                Timer timeXuatHienDiaChi = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    
                try {
                    cities = readExcel_City();
                        for (String city : cities) {
                            cboTinhThanhPho.addItem(city);
                        }
                } catch (IOException ex) {
                    Logger.getLogger(pnlTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
        });
        
        timeXuatHienDiaChi.setRepeats(false); // Chỉ chạy một lần
        timeXuatHienDiaChi.start();
          txtMaNhanVien.setEnabled(false);
          nhanVien_DAO = new NhanVienDAO_IMP();
          txtMaNhanVien.setText(nhanVien_DAO.generateVerifyCode());
          System.out.println("mã nhân viên"+nhanVien_DAO.generateVerifyCode());
          
            txtTenNhanVien.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = txtTenNhanVien.getText();
                String formattedText = vietHoaChuCaiDauTienTrongJtextField(text);
                txtTenNhanVien.setText(formattedText);
            }
        });
        
                // Tạo một DocumentFilter để kiểm tra và lọc ký tự
        AbstractDocument document = (AbstractDocument) txtTenNhanVien.getDocument();
        document.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                // Chỉ cho phép chữ cái, mã UTF-8, và có đúng một dấu cách giữa các từ
                if (string == null)
                    return;

                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + string + currentText.substring(offset);
                if (isValidText(newText)) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                // Chỉ cho phép chữ cái, mã UTF-8, và có đúng một dấu cách giữa các từ
                if (text == null)
                    return;

                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);
                if (isValidText(newText)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            private boolean isValidText(String text) {
                if (text.isEmpty() || text.startsWith(" ")) {
                    return false; // Ký tự đầu tiên không được là dấu cách
                }

                String[] words = text.split(" ");
                if (words.length < 1) {
                    return false; // Phải có ít nhất một từ
                }

                for (String word : words) {
                    if (!Pattern.matches("^[\\p{L} ]*$", word)) {
                        return false; // Chỉ mã UTF-8 và dấu cách được chấp nhận
                    }
                }

                return true;
            }
        });
        
        duLieuCCCD();
        DuLieuEmail();
        duLieuSDT();
        
        
        // Đặt ảnh vào JLabel và thiết lập kích thước bằng với JLabel
        try {
            selectedFile =new File("src\\main\\java\\IMG\\anhMacDinhNhanVien _daThayDoi.png");
            BufferedImage image = ImageIO.read(selectedFile); // Thay đổi đường dẫn đến ảnh
            ImageIcon icon = new ImageIcon(image);
            lblAnhNhanVien.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        date.toDay();
        SelectedDate day = date.getSelectedDate();
        
       
        
        date.setSelectedDate(new SelectedDate(day.getDay(),day.getMonth(),day.getYear() - 18) );
           
        
       chucVuDAO = new ChucVuDAO_IMP();
       ArrayList<ChucVu> dsChucVu = chucVuDAO.getDSChucVu();
        for (ChucVu chucVu : dsChucVu) {
            cboChucVu.addItem(chucVu.getTenChucVu());
        }
       cboChucVu.setSelectedItem("Quản lý");
        
        caLam_DAO = new CaLamViecDAO_IMP();    
        ArrayList<CaLamViec> dsCaLamViec = caLam_DAO.layDanhSachCaLamViec();
        for (CaLamViec caLam : dsCaLamViec) {
            cboCaLam.addItem(caLam.getTenCa());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        date = new ServiceUser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        cboTinhThanhPho = new javax.swing.JComboBox<>();
        cboQuanHuyen = new javax.swing.JComboBox<>();
        cboPhuongXa = new javax.swing.JComboBox<>();
        lblMaNhanVien = new javax.swing.JLabel();
        lblTenNhanVien = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        lblCCCD = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        btnChonNgaySinh = new javax.swing.JButton();
        cboChucVu = new javax.swing.JComboBox<>();
        cboGioiTinh = new javax.swing.JComboBox<>();
        txtNgaySinh = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtTenNhanVien = new javax.swing.JTextField();
        txtMaNhanVien = new javax.swing.JTextField();
        lblCaLam = new javax.swing.JLabel();
        cboCaLam = new javax.swing.JComboBox<>();
        btnLamMoi = new javax.swing.JButton();
        lblAnhNhanVien = new javax.swing.JLabel();
        btnChonAnh = new javax.swing.JButton();
        lblThemNhanVien = new javax.swing.JLabel();
        btnThemNhanVien = new javax.swing.JButton();

        date.setTextRefernce(txtNgaySinh);

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin nhân viên"));

        cboTinhThanhPho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTinhThanhPhoActionPerformed(evt);
            }
        });

        cboQuanHuyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboQuanHuyenActionPerformed(evt);
            }
        });

        cboPhuongXa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPhuongXaActionPerformed(evt);
            }
        });

        lblMaNhanVien.setText("Mã nhân viên");

        lblTenNhanVien.setText("Tên nhân viên");

        lblGioiTinh.setText("Giới tính");

        lblSoDienThoai.setText("Số điện thoại");

        lblCCCD.setText("CCCD");

        jLabel6.setText("Địa chỉ");

        lblNgaySinh.setText("Ngày sinh");

        lblChucVu.setText("Chức vụ");

        lblEmail.setText("Email");

        txtCCCD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        btnChonNgaySinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/iconAnhLich.png"))); // NOI18N
        btnChonNgaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonNgaySinhActionPerformed(evt);
            }
        });

        cboChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChucVuActionPerformed(evt);
            }
        });

        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cboGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGioiTinhActionPerformed(evt);
            }
        });

        txtNgaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgaySinhActionPerformed(evt);
            }
        });

        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtSoDienThoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtTenNhanVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtMaNhanVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lblCaLam.setText("Ca làm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblMaNhanVien)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCCCD)
                            .addComponent(lblEmail)
                            .addComponent(lblSoDienThoai))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(cboTinhThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(cboQuanHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                .addComponent(txtSoDienThoai, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCCCD, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenNhanVien)
                            .addComponent(lblGioiTinh)
                            .addComponent(lblNgaySinh)
                            .addComponent(lblChucVu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cboChucVu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnChonNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addComponent(lblCaLam)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(txtTenNhanVien))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(cboPhuongXa, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(209, 209, 209))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaNhanVien)
                            .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenNhanVien)
                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCCCD)
                            .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGioiTinh)
                            .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSoDienThoai)
                        .addComponent(lblNgaySinh))
                    .addComponent(btnChonNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblChucVu)
                    .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCaLam)
                    .addComponent(cboCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboTinhThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboQuanHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPhuongXa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 89, 910, 430));

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        add(btnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 540, 150, -1));

        lblAnhNhanVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblAnhNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhNhanVienMouseClicked(evt);
            }
        });
        add(lblAnhNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 99, 184, 216));

        btnChonAnh.setText("Chọn ảnh");
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });
        add(btnChonAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 321, -1, -1));

        lblThemNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblThemNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThemNhanVien.setText("Thêm nhân viên");
        add(lblThemNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1251, -1));

        btnThemNhanVien.setText("Thêm nhân viên");
        btnThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVienActionPerformed(evt);
            }
        });
        add(btnThemNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 540, 158, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void cboTinhThanhPhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTinhThanhPhoActionPerformed
        // TODO add your handling code here:
         // TODO add your handling code here:
        if(!tp.equals(cboTinhThanhPho.getSelectedItem()+"")||cboTinhThanhPho.getSelectedItem().equals("Tỉnh/Thành phố")){
            q = cboTinhThanhPho.getSelectedItem()+"";
            //System.out.println(provinceComboBox.getSelectedItem());
            if(cboTinhThanhPho.getSelectedItem().equals("Tỉnh/Thành phố")){

                cboQuanHuyen.removeAllItems();
                cboQuanHuyen.addItem("Quận/huyện");
                cboPhuongXa.removeAllItems();
                cboPhuongXa.addItem("Phường/xã");
                cboQuanHuyen.setEnabled(false);
                cboPhuongXa.setEnabled(false);

                System.out.println("1223 "+"Tỉnh/Thành phố");
            }else{

                try {
                    districts = readExcel_districts(cboTinhThanhPho,cboQuanHuyen,cboPhuongXa,tp);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                cboQuanHuyen.removeAllItems();
                cboQuanHuyen.addItem("Quận/huyện");
                for (String district : districts) {
                    cboQuanHuyen.addItem(district);
                }
                cboPhuongXa.setEnabled(true);
                try {
                    wardsDistricts = readExcel_wardsDistrict(cboQuanHuyen,cboPhuongXa);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                cboPhuongXa.removeAllItems();
                cboPhuongXa.addItem("Phường/xã");
                for (String wad : wardsDistricts) {
                    cboPhuongXa.addItem(wad);
                }
            }
        }
        tp = cboTinhThanhPho.getSelectedItem()+"";
    }//GEN-LAST:event_cboTinhThanhPhoActionPerformed

    private void cboQuanHuyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboQuanHuyenActionPerformed
        // TODO add your handling code here:
        

        cboQuanHuyen.addItem(q);
        // System.out.println(districtComboBox.getSelectedItem()+"");
        if( q == "Tỉnh/Thành phố"){

            //wardComboBox.removeAllItems();
            //wardComboBox.addItem("Phường/xã");
            // wardComboBox.setEnabled(true);
            // updateWardComboBox();
            // q = districtComboBox.getSelectedItem()+"";
            cboPhuongXa.setEnabled(false);
            cboPhuongXa.removeAllItems();
            cboPhuongXa.addItem("Phường/xã");

        }else{
            cboPhuongXa.setEnabled(true);
            try {
                wardsDistricts = readExcel_wardsDistrict(cboQuanHuyen,cboPhuongXa);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(!cboDiaChi.equals(cboQuanHuyen.getSelectedItem())){
                cboPhuongXa.removeAllItems();
                cboPhuongXa.addItem("Phường/xã");
                for (String wad : wardsDistricts) {
                    cboPhuongXa.addItem(wad);
                }
            }
        }
        cboDiaChi = cboQuanHuyen.getSelectedItem()+"";
        cboQuanHuyen.removeItem(q);
        
    }//GEN-LAST:event_cboQuanHuyenActionPerformed

    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienActionPerformed
        // TODO add your handling code here:
        if(cboTinhThanhPho.getSelectedItem().equals("Tỉnh/Thành phố")){
           
                if(cboPhuongXa.getSelectedItem().equals("Phường/xã") ){
                    
                }
              if(cboPhuongXa.getSelectedItem().equals("Phường/xã") ){

            }
        }
        
        // NhanVien(String maNV, String hoTenNhanVien, LocalDate ngaySinh, String soDienThoai, boolean gioiTinh, String email, ChucVu chucVu, TaiKhoan taiKhoan, CaLamViec caLam, String trangThai, String hinhAnh);
         
        String maNhanVien = txtMaNhanVien.getText();
        String tenNhanVieninput = txtTenNhanVien.getText().trim();
        String tenNhanVien = tenNhanVieninput.replaceAll("\\s+", " ");
        String cccd = txtCCCD.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        String ngay = date.getSelectedDate().getYear() +"-"+date.getSelectedDate().getMonth()+"-"+ date.getSelectedDate().getDay();
        LocalDate ngaySinh = LocalDate.parse(ngay, formatter);
        String gioiTinh = cboGioiTinh.getSelectedItem()+"";
        String email = txtEmail.getText();
        String soDienThoai = txtSoDienThoai.getText();
        String diaChi = cboTinhThanhPho.getSelectedItem() +"-"+cboQuanHuyen.getSelectedItem()+"-"+cboPhuongXa.getSelectedItem();
        String chucVu = "QL";
        if(!cboChucVu.getSelectedItem().equals("Quản Lý")){
            chucVu = "NV";
        }
        ChucVu cv = new ChucVu(chucVu);
        CaLamViec clv = new CaLamViec("");
        String trangThai = "Đang làm";
        String hinhAnh = selectedFile.getAbsolutePath();
        
        nhanVien_DAO = new NhanVienDAO_IMP();
        taiKhoan_DAO = new TaiKhoanDAO_IMP();
        
        nv = new NhanVien(maNhanVien, tenNhanVien, cccd,ngaySinh, gioiTinh, email,soDienThoai, cv, clv, trangThai, hinhAnh, diaChi);
        TaiKhoan taiKhoan = new TaiKhoan(nv,"123456","Đã đăng xuất");
        try {
            
                if(nhanVien_DAO.InsertNhanVien(nv)){
                    if(taiKhoan_DAO.inserTaiKhoan(taiKhoan)){
                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
                }
                    
            }
               
            
        } catch (Exception ex) {
            Logger.getLogger(pnlThemNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
       // System.out.println(tenNhanVien);
    }//GEN-LAST:event_btnThemNhanVienActionPerformed

    private void cboGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGioiTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboGioiTinhActionPerformed

    private void txtNgaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgaySinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgaySinhActionPerformed

    private void btnChonNgaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonNgaySinhActionPerformed
        // TODO add your handling code here:
        date.showPopup();
    }//GEN-LAST:event_btnChonNgaySinhActionPerformed

    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser(selectedFile);
                fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "jpeg", "png", "gif"));

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // Lấy tệp được chọn
                    selectedFile = fileChooser.getSelectedFile();
                  //  String filePath = selectedFile.getAbsolutePath().replace("\\", "/");
                  //  filePath = "/"+filePath;
                   // System.out.println("Đường dẫn tệp: " + filePath);
                   // lblAnhNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource(filePath)));
                    BufferedImage b;
                    try {
                        b = ImageIO.read(selectedFile);
                        // Thiết lập kích thước ảnh bằng với kích thước của JLabel
                    int labelWidth = lblAnhNhanVien.getWidth();
                    int labelHeight = lblAnhNhanVien.getHeight();
                    
                    Image scaledImage = b.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

                    // Tạo ImageIcon với ảnh đã điều chỉnh kích thước
                    ImageIcon imageIcon = new ImageIcon(scaledImage);

                    // Thiết lập ImageIcon cho JLabel
                    lblAnhNhanVien.setIcon(imageIcon);
                     //  lblAnhNhanVien.setIcon(new javax.swing.ImageIcon(b));

                    } catch (Exception e) {
                    }
                            
                }
    }//GEN-LAST:event_btnChonAnhActionPerformed

    private void lblAnhNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhNhanVienMouseClicked
        
 JDialog modalDialog = new JDialog(SwingUtilities.windowForComponent(this), "Fullscreen Modal", ModalityType.APPLICATION_MODAL);
        modalDialog.setUndecorated(true);

        // Đặt kích thước cửa sổ modal dựa trên kích thước màn hình
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        modalDialog.setSize(screenSize.width, screenSize.height);

        // Đặt độ trong suốt cho modal
        float modalOpacity = 0.7f;
        AlphaComposite modalAlpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, modalOpacity);
        modalDialog.setOpacity(modalAlpha.getAlpha());

        // Đóng cửa sổ modal khi bất kỳ sự kiện click chuột nào xảy ra
        modalDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                modalDialog.dispose();
               closeImageFrame();
            }
        });

        // Hiển thị cửa sổ modal
        modalDialog.setLocation(0, 0);
        jframAnh(true);
        modalDialog.setVisible(true);
    }//GEN-LAST:event_lblAnhNhanVienMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        txtCCCD.setText("");
        txtTenNhanVien.setText("");
        txtEmail.setText("");
        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtCCCD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        
        txtSoDienThoai.setText("");
        txtSoDienThoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        
        
        
        
        
        date.toDay();
        SelectedDate day = date.getSelectedDate();
        date.setSelectedDate(new SelectedDate(day.getDay(),day.getMonth(),day.getYear() - 18) );
        
        cboTinhThanhPho.setSelectedItem("Tỉnh/Thành phố");
        cboGioiTinh.setSelectedItem("Nam");
        cboChucVu.setSelectedItem("Quản lý");
                    
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void cboChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChucVuActionPerformed
        // TODO add your handling code here:
         System.out.println(cboChucVu.getSelectedItem());
         if(cboChucVu.getSelectedItem().equals("Quản lý")){
            lblCaLam.setVisible(false);
            cboCaLam.setVisible(false);
        }else if(cboChucVu.getSelectedItem().equals("Nhân viên")){
            lblCaLam.setVisible(true);
            cboCaLam.setVisible(true);   
        }
    }//GEN-LAST:event_cboChucVuActionPerformed

    private void cboPhuongXaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPhuongXaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPhuongXaActionPerformed
     


    public void jframAnh(boolean a){
                    // TODO add your handling code here:
           // Tạo một JFrame mới để hiển thị ảnh phóng to
            imageFrame = new JFrame("Large Image");
            imageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            imageFrame.setSize(500, 400);
            imageFrame.setAlwaysOnTop(a);
            imageFrame.setUndecorated(true);
            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new BorderLayout());
            GroupLayout layout = new GroupLayout(imagePanel);
            imagePanel.setLayout(layout);

            JLabel jLabel1 = new JLabel();
            layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            );

        // Đọc ảnh từ tệp (chắc chắn rằng bạn đã đặt selectedFile)
        BufferedImage b;
        try {
            b = ImageIO.read(selectedFile);
            // Scale ảnh để khớp kích thước của JLabel
            Image scaledImage = b.getScaledInstance(imageFrame.getWidth(), imageFrame.getHeight(), Image.SCALE_SMOOTH);

            // Tạo ImageIcon từ ảnh đã điều chỉnh kích thước
            ImageIcon imageIcon = new ImageIcon(scaledImage);

            // Đặt ImageIcon cho JLabel
            jLabel1.setIcon(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageFrame.add(imagePanel);

         // Đóng cửa sổ ảnh khi bất kỳ sự kiện nào xảy ra
        imageFrame.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                closeImageFrame();
            }
        });

        imageFrame.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                 closeImageFrame();
            }
        });

        // Hiển thị JFrame
        imageFrame.setLocationRelativeTo(null);
        imageFrame.setVisible(a);
  
    }
   private void closeImageFrame() {
        if (imageFrame != null) {
            imageFrame.dispose();
            imageFrame = null;
        }
    }
    public static ArrayList<String> readExcel_City() throws IOException {
		//Đọc dữ liệu từ file Diachi.xlsx
                ArrayList<String> cities = new ArrayList<>();
		FileInputStream file = new FileInputStream("src\\main\\java\\Li\\Tinh_2023.xlsx");
		//Nạp file input stream đưa về dạng excel
		XSSFWorkbook wb = new XSSFWorkbook(file);
		//Đọc file từ Sheet 1 (bắt đầu từ số 0)
		XSSFSheet sheet = wb.getSheetAt(0);
		//Lấy các giá trị trong các cột
		
		int v = 0;
		for(Row row:sheet) {
			
				if(row.getCell(1) !=null ) {
					cities.add(row.getCell(1)+"");
					v++;
				}
			
		}
		System.out.println(v);
		wb.close();
		file.close();
		return cities;
}   

    public static String readExcel_City_Id(String ip) throws IOException {
                    //Đọc dữ liệu từ file Diachi.xlsx
                    String cities = "";
                    FileInputStream file = new FileInputStream("src\\main\\java\\Li\\Tinh_2023.xlsx");
                    //Nạp file input stream đưa về dạng excel
                    XSSFWorkbook wb = new XSSFWorkbook(file);
                    //Đọc file từ Sheet 1 (bắt đầu từ số 0)
                    XSSFSheet sheet = wb.getSheetAt(0);
                    //Lấy các giá trị trong các cột
                  
                    
                    for(Row row:sheet) {
                            
                        if(row.getCell(1) !=null ) {
                           if(row.getCell(1) != null && ip.equals(row.getCell(0)+"") ) {
	
				cities = row.getCell(1)+"";
                                break;
				}
                            }
                        }                     
                    wb.close();
                    file.close();
        return cities;
    }   
    public static ArrayList<String> readExcel_districts(JComboBox<String> cbo,JComboBox<String> cbo1,JComboBox<String> cbo2,String tb) throws IOException {
	//Đọc dữ liệu từ file Diachi.xlsx
            ArrayList<String> districts = new ArrayList<>();
		FileInputStream file = new FileInputStream("src\\main\\java\\Li\\Book1.xlsx");
		//Nạp file input stream đưa về dạng excel
		XSSFWorkbook wb = new XSSFWorkbook(file);
		//Đọc file từ Sheet 1 (bắt đầu từ số 0)
		XSSFSheet sheet = wb.getSheetAt(0);
		//Lấy các giá trị trong các cột
//		FormulaEvaluator formula = wb.getCreationHelper().createFormulaEvaluator();
		//Duyệt các row
                
		int v = 0;
                if(cbo.getSelectedItem().equals("Tỉnh/Thành phố")){
                    cbo1.setEnabled(false);
                    cbo2.setEnabled(false);
                }else{
                     cbo1.setEnabled(true);
                    cbo1.setEditable(false);
                    cbo2.setEnabled(true);
                }
		for(Row row:sheet) {
			if(row.getCell(0) != null && cbo.getSelectedItem().equals(row.getCell(0)+"") ) {
				if(!districts.contains(row.getCell(2)+"" )) {
					districts.add(row.getCell(2)+"");
					v++;
				}
			}
		}
                
		System.out.println(v);
		wb.close();
		file.close();
		return districts;
	}    
     public static ArrayList<String> readExcel_wardsDistrict(JComboBox<String> cbo,JComboBox<String> cbo1) throws IOException {
		//Đọc dữ liệu từ file Diachi.xlsx
    	 ArrayList<String> wardsDistrict = new ArrayList<>();
		FileInputStream file = new FileInputStream("src\\main\\java\\Li\\Book1.xlsx");
		//Nạp file input stream đưa về dạng excel
		XSSFWorkbook wb = new XSSFWorkbook(file); 
		//Đọc file từ Sheet 1 (bắt đầu từ số 0)
		XSSFSheet sheet = wb.getSheetAt(0);
		//Lấy các giá trị trong các cột
		
		//Duyệt các row
		int v = 0;
               
                if(cbo.getSelectedItem().equals("Quận/huyện")){
                    cbo1.setEnabled(false);
                }else{
                cbo.setEnabled(true);   
                }
		for(Row row:sheet) {
			if(row.getCell(2) != null && cbo.getSelectedItem().equals(row.getCell(2)+"") ) {
				if(!wardsDistrict.contains(row.getCell(4)+"" )) {
					wardsDistrict.add(row.getCell(4)+"");
					
				}
			}
		}
		System.out.println(v);
		wb.close();
		file.close();
		return wardsDistrict;
	}
    private static String vietHoaChuCaiDauTienTrongJtextField(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder formattedText = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : input.toCharArray()) {
            if (Character.isWhitespace(c)) {
                capitalizeNext = true;
                formattedText.append(c);
            } else {
                if (capitalizeNext) {
                    formattedText.append(Character.toUpperCase(c));
                } else {
                    formattedText.append(Character.toLowerCase(c));
                }
                capitalizeNext = false;
            }
        }

        return formattedText.toString();
    }
    private static boolean validateData(String input) {
         String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
         java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
         java.util.regex.Matcher matcher = pattern.matcher(input);
        if (matcher.matches() && input.endsWith("@gmail.com")&& input.length()>0)  {
            
         return true;  
        } else {     
            return false;
        }
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
                 @Override
                 public void insertUpdate(DocumentEvent e) {
                      if ( txtSoDienThoai.getText().length() < 10) {
                    // Đặt màu đỏ đậm cho đường viền
                    LineBorder redLineBorder = new LineBorder(Color.RED, 2);
                     txtSoDienThoai.setBorder(redLineBorder);
                    } else {
                     if(!txtSoDienThoai.getText().substring(0, 1).equals("0")){
                             LineBorder redLineBorder = new LineBorder(Color.RED, 2);
                             txtSoDienThoai.setBorder(redLineBorder);
                            }else{
                         txtSoDienThoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
                     }
                    }
                 }

                 @Override
                 public void removeUpdate(DocumentEvent e) {
                      LineBorder redLineBorder = new LineBorder(Color.RED, 2);
                     txtSoDienThoai.setBorder(redLineBorder);
                 }
                 
                 @Override
                 public void changedUpdate(DocumentEvent e) {
                 }
               });
                
    }
    private void duLieuCCCD(){
        
             ((AbstractDocument) txtCCCD.getDocument()).setDocumentFilter(new DocumentFilter() {
                   @Override
                   public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr)
                           throws BadLocationException {
                       String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                       text += string;
                       if (text.length() <= 12 && text.matches("\\d*")) {
                           super.insertString(fb, offset, string, attr);
                       } else if (text.matches("\\d*")) {
                           super.replace(fb, 11, 1, string, attr);
                       }
                   }

                   @Override
                   public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                           throws BadLocationException {
                       String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                       if (newText.length() <= 12 && newText.matches("\\d*")) {
                           super.replace(fb, offset, length, text, attrs);
                       } else if (newText.matches("\\d*")) {
                           super.replace(fb, 11, 1, text, attrs);
                       }
                   }
               });

               txtCCCD.addFocusListener(new FocusAdapter() {
                   @Override
                   public void focusLost(FocusEvent e) {
                       String text = txtCCCD.getText();
                       if (text.length() > 12) {
                           txtCCCD.setText(text.substring(text.length() - 10));
                       }
                   }
               });
               
            txtCCCD.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
              
        if ( txtCCCD.getText().length() < 12) {
                // Đặt màu đỏ đậm cho đường viền
                LineBorder redLineBorder = new LineBorder(Color.RED, 2);
                     txtCCCD.setBorder(redLineBorder);
                 
        
            
     
            } else {
             if(!txtCCCD.getText().substring(0, 1).equals("0")){
                     LineBorder redLineBorder = new LineBorder(Color.RED, 2);
                     txtCCCD.setBorder(redLineBorder);
                    }else{
                 txtCCCD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
             }
                 
                   
            }
                if( txtCCCD.getText().length() == 3 ){
                    String part2 = txtCCCD.getText().substring(1, 3);  // Phần 2: "52"
                    try {
                        if(!readExcel_City_Id(part2).equals("")){
                            System.out.println(readExcel_City_Id(part2));
                            cboTinhThanhPho.setSelectedItem(readExcel_City_Id(part2));
                        }else{
                            cboTinhThanhPho.setSelectedItem("Tỉnh/Thành phố");
                        }
                        
                    } catch (IOException ex) {
                        Logger.getLogger(pnlThemNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(txtCCCD.getText().length() == 4){
                     String part3 = txtCCCD.getText().substring(3, 4);
                     if(part3.equals("2")||part3.equals("0")){
                         cboGioiTinh.setSelectedItem("Nam");
                     }else if(part3.equals("1")||part3.equals("3")){
                            cboGioiTinh.setSelectedItem("Nữ");
                     }
                }
                if(txtCCCD.getText().length()==6){
                    Calendar calendar1 = Calendar.getInstance();
                    int year1 = calendar1.get(Calendar.YEAR) % 100;
                    String part4 = txtCCCD.getText().substring(4, 6);
                    if( year1 - Integer.parseInt(part4) >= 18 ){
                        
                        String s = "20"+ part4;
                        date.setSelectedDate(new SelectedDate(1,1,Integer.parseInt(s)) );
                        
                     }else if(Integer.parseInt(part4) > year1){
                          String s = "19"+ part4;
                          date.setSelectedDate(new SelectedDate(1,1,Integer.parseInt(s)) );
                     }
                }
                
            }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
               if( !txtCCCD.getText().equals("") ){
                    LineBorder redLineBorder = new LineBorder(Color.RED, 2);
                     txtCCCD.setBorder(redLineBorder);
                    
                }
            }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                if( !txtCCCD.getText().equals("") ){
                    
                    System.out.println(".changedUpdate()");
                }
            }
        });
    }
    
    private void DuLieuEmail(){
        txtEmail.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if( validateData(txtEmail.getText())){
                      // Tạo Border tùy chỉnh (ví dụ: viền đậm màu đỏ)
                txtEmail.setBorder(null);
                }else{
                Border customBorder = BorderFactory.createLineBorder(Color.RED, 2);
                txtEmail.setBorder(customBorder); 
                }
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonAnh;
    private javax.swing.JButton btnChonNgaySinh;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThemNhanVien;
    private javax.swing.JComboBox<String> cboCaLam;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private javax.swing.JComboBox<String> cboPhuongXa;
    private javax.swing.JComboBox<String> cboQuanHuyen;
    private javax.swing.JComboBox<String> cboTinhThanhPho;
    private ServiceUser.DateChooser date;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAnhNhanVien;
    private javax.swing.JLabel lblCCCD;
    private javax.swing.JLabel lblCaLam;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblMaNhanVien;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblTenNhanVien;
    private javax.swing.JLabel lblThemNhanVien;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenNhanVien;
    // End of variables declaration//GEN-END:variables
}
