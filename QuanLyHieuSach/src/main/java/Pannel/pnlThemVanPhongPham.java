
package Pannel;

import DAO.LoaiSanPham_DAO;
import DAO.LoaiVanPhongPham_DAO;
import DAO.NhaCungCap_DAO;
import DAO.ThuongHieu_DAO;
import DAO.VanPhongPham_DAO;
import DAO.XuatXu_DAO;
import Entity.LoaiSanPham;
import Entity.LoaiVanPhongPham;
import Entity.NhaCungCap;
import Entity.ThuongHieu;
import Entity.VanPhongPham;
import Entity.XuatXu;
import Them.ThemXuatXu;
import Them.frmNhaCungCap;
import Them.frmThemLoaiVanPhongPham;
import Them.ThemTH;
import Them.ThemXuatXu;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
public class pnlThemVanPhongPham extends javax.swing.JPanel {

    private File selectedFile;
    private JFrame imageFrame;
    private VanPhongPham_DAO vanPhongPham_DAO;
    private NhaCungCap_DAO nhaCungCap_DAO;
    private LoaiSanPham_DAO loaiSanPham_DAO;
    private XuatXu_DAO xuatXu_DAO;
    private ThuongHieu_DAO thuongHieu_DAO;
    private LoaiVanPhongPham_DAO loaiVanPhongPham_DAO;
    public pnlThemVanPhongPham() throws SQLException {
        
     try {
            // Set the Look and Feel for the entire application
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        initComponents();
        init();
        vanPhongPham_DAO = new VanPhongPham_DAO();
        lblMaVPPKyTu.setText(vanPhongPham_DAO.generateMaVanPhongPham());
        
        // Create a custom DocumentFilter
    }
      public void init(){
                // Đặt ảnh vào JLabel và thiết lập kích thước bằng với JLabel
        try {
            selectedFile =new File("src\\IMG\\anhSachMacDinh_daThayDoi.png");
            BufferedImage image = ImageIO.read(selectedFile); // Thay đổi đường dẫn đến ảnh
            
            //thay đổi kích thước ảnh phù hợp vs lable 259x137
             Image scaledImage = image.getScaledInstance(259, 137, Image.SCALE_SMOOTH);

                    // Tạo ImageIcon với ảnh đã điều chỉnh kích thước
                    ImageIcon imageIcon = new ImageIcon(scaledImage);

                    // Thiết lập ImageIcon cho JLabel
                    lblAnhVanPhongPham.setIcon(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
          xuatXu_DAO = new XuatXu_DAO();
        ArrayList<XuatXu> dsxx = xuatXu_DAO.layDanhSachXuatXu();
        for (XuatXu xuatXu : dsxx) {
            cboXuatXu.addItem(xuatXu.getTenQuocGia());
        }
         cboXuatXu.setSelectedIndex(-1);
        
        thuongHieu_DAO = new ThuongHieu_DAO();
        ArrayList<ThuongHieu> dsth = thuongHieu_DAO.layDanhSachThuongHieu();
        for (ThuongHieu thuongHieu : dsth) {
            cboThuongHieu.addItem(thuongHieu.getTenThuongHieu());
        }
        cboThuongHieu.setSelectedIndex(-1);
        
        loaiVanPhongPham_DAO = new LoaiVanPhongPham_DAO();
          ArrayList<LoaiVanPhongPham> dslvpp = loaiVanPhongPham_DAO.layDanhLoaiVanPhongPham();
          for (LoaiVanPhongPham loaiVanPhongPham : dslvpp) {
            cboLoaiVanPhongPham.addItem(loaiVanPhongPham.getTenLoaiVanPhongPham());       
        }
          cboLoaiVanPhongPham.setSelectedIndex(-1);
          nhaCungCap_DAO = new NhaCungCap_DAO();
        ArrayList<NhaCungCap> dsncc = nhaCungCap_DAO.layDanhSachNhaCungCap();
        for (NhaCungCap nhaCungCap : dsncc) {
            if(nhaCungCap.getSanPhamCungCap().equals("Văn phòng phẩm")){
                cboNhaCungCap.addItem(nhaCungCap.getTenNCC());
            }
        }
        cboNhaCungCap.setSelectedIndex(-1);
        kiemTraDuLieuFloat(txtDonGia);
        kiemTraDuLieuFloat(txtSoLuongTon);

          
         
         
    }
      private void duLieuTenSach(){
            txtTenSach.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    String text = txtTenSach.getText();
                    String formattedText = vietHoaChuCaiDauTienTrongJtextField(text);
                    txtTenSach.setText(formattedText);
                }
            });

                    // Tạo một DocumentFilter để kiểm tra và lọc ký tự
            AbstractDocument document = (AbstractDocument) txtTenSach.getDocument();
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblAnhVanPhongPham = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblMaSach = new javax.swing.JLabel();
        lblTenSach = new javax.swing.JLabel();
        txtTenSach = new javax.swing.JTextField();
        lblDonGia = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        lblNhaCungCap = new javax.swing.JLabel();
        cboLoaiVanPhongPham = new javax.swing.JComboBox<>();
        cboNhaCungCap = new javax.swing.JComboBox<>();
        lblTheLoai = new javax.swing.JLabel();
        lblTacGia = new javax.swing.JLabel();
        lblNhaXuatBan = new javax.swing.JLabel();
        lblNamXuatBan = new javax.swing.JLabel();
        lblSoTrang = new javax.swing.JLabel();
        cboThuongHieu = new javax.swing.JComboBox<>();
        txtSoLuongTon = new javax.swing.JTextField();
        lblMaVPPKyTu = new javax.swing.JLabel();
        cboXuatXu = new javax.swing.JComboBox<>();
        txtNamSanXuat = new javax.swing.JTextField();
        lblSoTrang1 = new javax.swing.JLabel();
        cboChatlieu = new javax.swing.JComboBox<>();
        btnThuongHieu = new javax.swing.JButton();
        btnNhaCungCap = new javax.swing.JButton();
        btnLoaiDungCu = new javax.swing.JButton();
        btnXuatXu = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        jPanel1.setEnabled(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(1320, 787));

        lblAnhVanPhongPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnhVanPhongPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhVanPhongPhamMouseClicked(evt);
            }
        });

        jButton1.setText("Chọn ảnh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblMaSach.setBackground(new java.awt.Color(0, 0, 0));
        lblMaSach.setText("Mã SP");

        lblTenSach.setText("Tên sản phẩm");

        txtTenSach.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTenSachFocusGained(evt);
            }
        });
        txtTenSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSachActionPerformed(evt);
            }
        });

        lblDonGia.setText("Đơn giá");

        txtDonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDonGiaActionPerformed(evt);
            }
        });

        lblNhaCungCap.setText("Loại dụng cụ");

        lblTheLoai.setText("Nhà cung cấp");

        lblTacGia.setText("Xuất xứ");

        lblNhaXuatBan.setText("Thương hiệu");

        lblNamXuatBan.setText("Chất liệu");

        lblSoTrang.setText("Số lượng tồn");

        txtSoLuongTon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongTonActionPerformed(evt);
            }
        });

        lblMaVPPKyTu.setBackground(new java.awt.Color(204, 204, 204));
        lblMaVPPKyTu.setEnabled(false);

        txtNamSanXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamSanXuatActionPerformed(evt);
            }
        });

        lblSoTrang1.setText("Năm sản xuất");

        cboChatlieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhựa", "Gỗ", "Cao Su" }));
        cboChatlieu.setSelectedIndex(-1);

        btnThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThuongHieuActionPerformed(evt);
            }
        });

        btnNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhaCungCapActionPerformed(evt);
            }
        });

        btnLoaiDungCu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoaiDungCuActionPerformed(evt);
            }
        });

        btnXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatXuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenSach)
                            .addComponent(lblMaSach))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaVPPKyTu, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(lblNhaCungCap)
                                    .addGap(18, 18, 18))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(lblTheLoai)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblDonGia)
                                .addGap(44, 44, 44)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboLoaiVanPhongPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboNhaCungCap, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnNhaCungCap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnLoaiDungCu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtDonGia))))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNhaXuatBan)
                            .addComponent(lblNamXuatBan)
                            .addComponent(lblSoTrang)
                            .addComponent(lblSoTrang1))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoLuongTon)
                            .addComponent(txtNamSanXuat)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTacGia)
                                .addGap(67, 67, 67)
                                .addComponent(cboXuatXu, 0, 367, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboChatlieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboThuongHieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblMaVPPKyTu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMaSach))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTacGia))
                    .addComponent(btnXuatXu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTenSach)
                        .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNhaXuatBan))
                    .addComponent(btnThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDonGia)
                    .addComponent(lblNamXuatBan)
                    .addComponent(cboChatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnLoaiDungCu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblNhaCungCap)
                                .addComponent(cboLoaiVanPhongPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSoTrang)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNamSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTheLoai)
                            .addComponent(cboNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSoTrang1))))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jButton2.setText("Làm mới");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Thêm sản phẩm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Thêm bằng file Excel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(378, 378, 378)
                                .addComponent(lblAnhVanPhongPham, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(486, 486, 486)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(335, 335, 335)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblAnhVanPhongPham, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(35, 35, 35)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton2))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1122, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String maSach = lblMaVPPKyTu.getText();
        String tenSach = txtTenSach.getText();

        String soTrang = txtSoLuongTon.getText();
        String txtGiaBan= txtDonGia.getText();
        // Xóa dấu phẩy trong chuỗi
        String cleanedInput = txtGiaBan.replaceAll(",", "");
        // Chuyển đổi thành số
        double giaBan = Double.parseDouble(cleanedInput);

      
        
        String soLuongTon = txtSoLuongTon.getText().replaceAll(",", "");
        // Chuyển đổi thành số
     
        
        String nhaXuatBan = cboThuongHieu.getSelectedItem()+"";

        nhaCungCap_DAO = new NhaCungCap_DAO();
        ArrayList<NhaCungCap> dsncc = nhaCungCap_DAO.layDanhSachNhaCungCap();
        String nhaCungCap_Sua = "";
        for (NhaCungCap nhaCungCap : dsncc) {
            if(cboNhaCungCap.getSelectedItem().equals(nhaCungCap.getTenNCC())){
                nhaCungCap_Sua = nhaCungCap.getMaNCC()+"";
            }
        }

        NhaCungCap ncc = new NhaCungCap(nhaCungCap_Sua);

        String loaiSanPham_l = "";

        loaiSanPham_DAO = new LoaiSanPham_DAO();
        ArrayList<LoaiSanPham> dslsp = loaiSanPham_DAO.layDanhLoaiSanPham();
        for (LoaiSanPham loaiSanPham : dslsp) {
            if(loaiSanPham.getTenLoaiSanPham().equals("Sách")){
                loaiSanPham_l  = loaiSanPham.getMaLoaiSanPham();
            }
        }
        LoaiSanPham loaiSanPham = new LoaiSanPham( loaiSanPham_l);
        String namSanXuat= txtNamSanXuat.getText();

        String hinhAnh = selectedFile.getAbsolutePath();
        String tinhTrang = "";

        if(Integer.parseInt(txtSoLuongTon.getText()) < 10){
            tinhTrang = "Hết hàng";
        }else{
            tinhTrang = "Còn hàng";
        }
        String xuatXu_CBO = "";
        xuatXu_DAO = new XuatXu_DAO();
        ArrayList<XuatXu> dsxx = xuatXu_DAO.layDanhSachXuatXu();
        for (XuatXu xuatXu : dsxx) {
            if(xuatXu.getTenQuocGia().equals(cboXuatXu.getSelectedItem()))
            xuatXu_CBO = xuatXu.getMaXuatXu();
        }
        XuatXu xuatXu = new XuatXu(xuatXu_CBO);

        String thuongHieu_CBO= cboThuongHieu.getSelectedItem().toString();
        thuongHieu_DAO = new ThuongHieu_DAO();
        ArrayList<ThuongHieu> dsnxb = thuongHieu_DAO.layDanhSachThuongHieu();
        for (ThuongHieu thuongHieu : dsnxb) {
            if(thuongHieu.getTenThuongHieu().equals(thuongHieu_CBO)){
                thuongHieu_CBO = thuongHieu.getMaThuongHieu();
            }
        }
        ThuongHieu thuongHieu = new ThuongHieu(thuongHieu_CBO);

        loaiVanPhongPham_DAO = new LoaiVanPhongPham_DAO();
        String loaiVanPhongPham_CBO ="";
        ArrayList<LoaiVanPhongPham> dslvpp = loaiVanPhongPham_DAO.layDanhLoaiVanPhongPham();
        for (LoaiVanPhongPham loaiVanPhongPham : dslvpp) {
            if(loaiVanPhongPham.getTenLoaiVanPhongPham().equals(cboLoaiVanPhongPham.getSelectedItem())){
                loaiVanPhongPham_CBO  = loaiVanPhongPham.getMaLoaiVanPhongPham();
            }

        }

        LoaiVanPhongPham loaiVanPhongPham = new LoaiVanPhongPham(loaiVanPhongPham_CBO);

        String chatLieu = cboChatlieu.getSelectedItem().toString();
        VanPhongPham vanPhongPham = new VanPhongPham(xuatXu, thuongHieu, loaiVanPhongPham, chatLieu, Integer.parseInt(namSanXuat), maSach, tenSach, loaiSanPham, ncc, Integer.parseInt(soLuongTon), giaBan, soTrang, tinhTrang, hinhAnh);
        vanPhongPham_DAO = new VanPhongPham_DAO();
        if(vanPhongPham_DAO.inserVanPhongPham(vanPhongPham)){
            lamMoiDuLieu();
            try {

                lblMaVPPKyTu.setText( vanPhongPham_DAO.generateMaVanPhongPham());
            } catch (SQLException ex) {
                Logger.getLogger(pnlThemVanPhongPham.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "Thêm văn phòng phẩm thành công");

        }

        System.out.println(vanPhongPham.getNhaCungCap());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        lamMoiDuLieu();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatXuActionPerformed

            ThemXuatXu themXX;
        try {
            themXX = new ThemXuatXu();
            // Thiết lập loại đóng cửa sổ
        themXX.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Thêm sự kiện WindowListener để ẩn frame khi đóng
        themXX.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Ẩn frame thay vì đóng nó
                ((JFrame) windowEvent.getSource()).setVisible(false);
            }
        });

        // Hiển thị frame ThemTH
        themXX.setVisible(true);
        lamMoiDuLieu();
        } catch (SQLException ex) {
            Logger.getLogger(pnlThemVanPhongPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        
    }//GEN-LAST:event_btnXuatXuActionPerformed

    private void btnLoaiDungCuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoaiDungCuActionPerformed
        frmThemLoaiVanPhongPham themLoaiVPP = new frmThemLoaiVanPhongPham();

        // Thiết lập loại đóng cửa sổ
        themLoaiVPP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Thêm sự kiện WindowListener để ẩn frame khi đóng
        themLoaiVPP.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Ẩn frame thay vì đóng nó
                ((JFrame) windowEvent.getSource()).setVisible(false);
            }
        });

        // Hiển thị frame ThemTH
        themLoaiVPP.setVisible(true);
        lamMoiDuLieu();
    }//GEN-LAST:event_btnLoaiDungCuActionPerformed

    private void btnNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhaCungCapActionPerformed
        frmNhaCungCap themNCC = new frmNhaCungCap();

        // Thiết lập loại đóng cửa sổ
        themNCC.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Thêm sự kiện WindowListener để ẩn frame khi đóng
        themNCC.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Ẩn frame thay vì đóng nó
                ((JFrame) windowEvent.getSource()).setVisible(false);
            }
        });

        // Hiển thị frame ThemTH
        themNCC.setVisible(true);
        lamMoiDuLieu();
    }//GEN-LAST:event_btnNhaCungCapActionPerformed

    private void btnThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThuongHieuActionPerformed
        // TODO add your handling code here:
        ThemTH themTH = new ThemTH();

        // Thiết lập loại đóng cửa sổ
        themTH.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Thêm sự kiện WindowListener để ẩn frame khi đóng
        themTH.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Ẩn frame thay vì đóng nó
                ((JFrame) windowEvent.getSource()).setVisible(false);
            }
        });

        // Hiển thị frame ThemTH
        themTH.setVisible(true);
        lamMoiDuLieu();
    }//GEN-LAST:event_btnThuongHieuActionPerformed

    private void txtNamSanXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamSanXuatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamSanXuatActionPerformed

    private void txtSoLuongTonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongTonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongTonActionPerformed

    private void txtDonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDonGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDonGiaActionPerformed

    private void txtTenSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSachActionPerformed

    private void txtTenSachFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenSachFocusGained
        // TODO add your handling code here:
        txtTenSach.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtTenSachFocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
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
                    int labelWidth = lblAnhVanPhongPham.getWidth();
                    int labelHeight = lblAnhVanPhongPham.getHeight();

                    Image scaledImage = b.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

                    // Tạo ImageIcon với ảnh đã điều chỉnh kích thước
                    ImageIcon imageIcon = new ImageIcon(scaledImage);

                    // Thiết lập ImageIcon cho JLabel
                    lblAnhVanPhongPham.setIcon(imageIcon);
                    //  lblAnhNhanVien.setIcon(new javax.swing.ImageIcon(b));

                } catch (Exception e) {
                }

            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void lblAnhVanPhongPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhVanPhongPhamMouseClicked
        // TODO add your handling code here:

        JDialog modalDialog = new JDialog(SwingUtilities.windowForComponent(this), "Fullscreen Modal", Dialog.ModalityType.APPLICATION_MODAL);
        modalDialog.setUndecorated(true);

        // Đặt kích thước cửa sổ modal dựa trên kích thước màn hình
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        modalDialog.setSize(screenSize.width, screenSize.height);

        // Đặt độ trong suốt cho modal
        float modalOpacity = 0.7f;
        AlphaComposite modalAlpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, modalOpacity);
        modalDialog.setOpacity(modalOpacity);

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
    }//GEN-LAST:event_lblAnhVanPhongPhamMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Choose Excel File");
    fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));

    int userSelection = fileChooser.showOpenDialog(null);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        String excelFilePath = selectedFile.getAbsolutePath();

        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet

            Iterator<Row> iterator = sheet.iterator();

            // Skip the header row if present
            if (iterator.hasNext()) {
                iterator.next();
            }

            VanPhongPham_DAO vpp = new VanPhongPham_DAO();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();

                try {
                    String maVPP = vpp.generateMaVanPhongPham().toString();
                    System.out.println(maVPP);
                    String tenVPP = currentRow.getCell(0).getStringCellValue();
                    System.out.println(tenVPP);
                    double donGia = currentRow.getCell(1).getNumericCellValue();
                    System.out.println(donGia);
                    String nhaCCS = currentRow.getCell(2).getStringCellValue();
                    NhaCungCap nhaCC = new NhaCungCap(nhaCCS);
                    System.out.println(nhaCCS);
                    String xuatXuS = currentRow.getCell(3).getStringCellValue();
                    
                    XuatXu xuatXu = (xuatXuS!= null)? new XuatXu(xuatXuS):new XuatXu("Khác");
                    System.out.println(xuatXuS);
                    int namSX = (int) currentRow.getCell(4).getNumericCellValue();
                    String chatLieu = currentRow.getCell(5).getStringCellValue();
                    String tenLVPPS = currentRow.getCell(6).getStringCellValue();
                    LoaiVanPhongPham tenLVPP = new LoaiVanPhongPham(tenLVPPS);
                    String tenTHS = currentRow.getCell(7).getStringCellValue();
                    ThuongHieu tenTH = new ThuongHieu(tenTHS);
                    int soLuongTon = (int) currentRow.getCell(8).getNumericCellValue();
                    LoaiSanPham lsp = new LoaiSanPham("LSP000002");
                    VanPhongPham s = new VanPhongPham(xuatXu, tenTH, tenLVPP, chatLieu, namSX, maVPP, tenVPP, lsp, nhaCC, soLuongTon, donGia," "," ", " ");
                    // Now you have the data, you can add it to your system
                    vpp.inserVanPhongPham(s);
                } catch (SQLException ex) {
                    Logger.getLogger(pnlThemSach.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Error during data insertion: " + ex.getMessage());
                }
            }

            System.out.println("Data insertion completed successfully");

            // Trigger UI update if needed
            System.out.println("UI updated");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_jButton4ActionPerformed
        public void lamMoiDuLieu(){
        AbstractDocument document = (AbstractDocument) txtTenSach.getDocument();

        DocumentFilter oldFilter;
         oldFilter = document.getDocumentFilter();
        document.setDocumentFilter(null);

    // Đặt lại giá trị
        txtTenSach.setText("");
        
        // Đặt lại DocumentFilter
        document.setDocumentFilter(oldFilter);
        txtDonGia.setText("");
        cboLoaiVanPhongPham.setSelectedIndex(-1);
        cboNhaCungCap.setSelectedIndex(-1);
        cboXuatXu.setSelectedIndex(-1);
        cboThuongHieu.setSelectedIndex(-1);
        cboChatlieu.setSelectedIndex(-1);
        
       
        txtSoLuongTon.setText("");
        txtNamSanXuat.setText("");
        
        
      
      
    }    public void jframAnh(boolean a){
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
    public void kiemTraSo(JTextField s){
         PlainDocument document = (PlainDocument) s.getDocument();
        document.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                if (isValidInput(text)) {
                    super.insertString(fb, offset, text, attr);
                }
            }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (isValidInput(text)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
             private static boolean isValidInput(String text) {
                if (text.isEmpty()) {
                    return true;
                }
                char firstChar = text.charAt(0);
                return Character.isDigit(firstChar) && firstChar != '0' && text.matches("\\d*");
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoaiDungCu;
    private javax.swing.JButton btnNhaCungCap;
    private javax.swing.JButton btnThuongHieu;
    private javax.swing.JButton btnXuatXu;
    private javax.swing.JComboBox<String> cboChatlieu;
    private javax.swing.JComboBox<String> cboLoaiVanPhongPham;
    private javax.swing.JComboBox<String> cboNhaCungCap;
    private javax.swing.JComboBox<String> cboThuongHieu;
    private javax.swing.JComboBox<String> cboXuatXu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAnhVanPhongPham;
    private javax.swing.JLabel lblDonGia;
    private javax.swing.JLabel lblMaSach;
    private javax.swing.JLabel lblMaVPPKyTu;
    private javax.swing.JLabel lblNamXuatBan;
    private javax.swing.JLabel lblNhaCungCap;
    private javax.swing.JLabel lblNhaXuatBan;
    private javax.swing.JLabel lblSoTrang;
    private javax.swing.JLabel lblSoTrang1;
    private javax.swing.JLabel lblTacGia;
    private javax.swing.JLabel lblTenSach;
    private javax.swing.JLabel lblTheLoai;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtNamSanXuat;
    private javax.swing.JTextField txtSoLuongTon;
    private javax.swing.JTextField txtTenSach;
    // End of variables declaration//GEN-END:variables
}
