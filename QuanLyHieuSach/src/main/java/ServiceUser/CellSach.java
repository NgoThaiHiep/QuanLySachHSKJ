
package ServiceUser;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import DAO.NhaCungCap_DAO;
import DAO.NhaXuatBan_DAO;
import DAO.Sach_DAO;
import DAO.Sach_TheLoai_DAO;
import DAO.TacGia_DAO;
import DAO.TheLoai_DAO;
import Entity.LoaiSanPham;
import Entity.NhaCungCap;

import Entity.NhaXuatBan;
import Entity.Sach;
import Entity.TacGia;
import Entity.TheLoai;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;


/**
 *
 * @author FPTSHOP
 */
public class CellSach extends javax.swing.JPanel {
    	private Sach sach;
	private NhaXuatBan_DAO nhaXuatBan_DAO;
	private NhaCungCap_DAO nhaCungCap_DAO;
	private TheLoai_DAO theLoai_DAO;
	private List<Object> selectedItemsTheLoai;
	private List<Object>   selectedItemsTacGia;
        private Sach_DAO sach_DAO;
	private TacGia_DAO tacGia_DAO;
        private int soLuongToiThieu = 10;
        private int width = 148;
        private int height = 198;
    /**
     * Creates new form CellSach
     */

    private File selectedFile;
    private boolean show = false;
    public CellSach(Sach sach) throws IOException, SQLException {
        this.sach = sach;
         try {
            // Set the Look and Feel for the entire application
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        initComponents();
        lblLoaiSanPham.setText(sach.getLoaiSanPham().getMaLoaiSanPham());
        txtTinhTrang.setVisible(false);
        txtMaSanPham.setVisible(false);
        
        lblMaSanPham1.setText(sach.getMaSanPham());
        lblTenSach1.setText(sach.getTenSanPham());
        lblTacGia1.setText(sach.getTacGia().getMaTacGia());
        lblTheLoai1.setText(sach.getTheLoai().getTenTheLoai());
        lblSoTrang1.setText(sach.getSoTrang()+"");
        lblGiaBan1.setText(sach.getDonGia()+"");
        lblSoLuongTon1.setText(sach.getSoLuongTon()+"");
        lblNhaXuatBan1.setText(sach.getNhaXuatBan().getMaNhaXuatBan());
        lblTinhTrang1.setText(sach.getTinhTrang());
        lblNhaCungCap1.setText(sach.getNhaCungCap().getMaNCC());
        lblNamXuatBan1.setText(sach.getNamXuatBan()+"");
        
        nhaXuatBan_DAO = new NhaXuatBan_DAO();
        ArrayList<NhaXuatBan> dsnxb = nhaXuatBan_DAO.layDanhSachNhaXuatBan();
        for (NhaXuatBan nhaXuatBan : dsnxb) {
      	  cboNhaXuatBan.addItem(nhaXuatBan.getTenNhaXuatBanl());
        }
        for (NhaXuatBan nhaXuatBan : dsnxb) {
      	  if( nhaXuatBan.getMaNhaXuatBan().equals(lblNhaXuatBan1.getText())) {
      		  lblNhaXuatBan1.setText(nhaXuatBan.getTenNhaXuatBanl());
      	  }
      	 
        }
        
        nhaCungCap_DAO = new NhaCungCap_DAO();
       ArrayList<NhaCungCap> dsncc = nhaCungCap_DAO.layDanhSachNhaCungCap();
        for (NhaCungCap nhaCungCap : dsncc) {
            if(nhaCungCap.getSanPhamCungCap().equals("Sách")){
                 cboNhaCungCap.addItem(nhaCungCap.getTenNCC());
            }
           
        }
       
        for (NhaCungCap nhaCungCap : dsncc) {
            if(lblNhaCungCap1.getText().equals(nhaCungCap.getMaNCC()))
                lblNhaCungCap1.setText(nhaCungCap.getTenNCC());
          
        }
	visibelTextField(false);
        visibelLable(true);
        
        lblAnh.setText("1");
        System.out.println(sach.getHinhAnh());
        try {
            selectedFile =new File(sach.getHinhAnh());
            BufferedImage image = ImageIO.read(selectedFile); // Thay đổi đường dẫn đến ảnh
            
            // thay đổi kích thức ảnh cùng kích thước với lable 184x216
             Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                    // Tạo ImageIcon với ảnh đã điều chỉnh kích thước
                    ImageIcon imageIcon = new ImageIcon(scaledImage);

                    // Thiết lập ImageIcon cho JLabel
                    lblAnh.setIcon(imageIcon);
           
        } catch (IOException e) {
            selectedFile =new File("src\\IMG\\khongCoAnh.png");
            BufferedImage image = ImageIO.read(selectedFile); // Thay đổi đường dẫn đến ảnh
            
            // thay đổi kích thức ảnh cùng kích thước với lable 184x216
             Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                    // Tạo ImageIcon với ảnh đã điều chỉnh kích thước
                    ImageIcon imageIcon = new ImageIcon(scaledImage);

                    // Thiết lập ImageIcon cho JLabel
                    lblAnh.setIcon(imageIcon);      
        }
        
        btnLuu.setVisible(false);
        
        if(lblTinhTrang1.getText().equals("Ngừng kinh doanh")){
            ngungKinhDoanh();
        }
        
        kiemTraDuLieuFloat(txtGiaBan);
        duLieuTenSach();
        kiemTraSo(txtSoTrang);
        kiemTraSo(txtSoLuongTon);
        kiemTraSo(txtNamXuatBan);
        tacGia_DAO = new TacGia_DAO();
        tacGia_DAO.generateTacGia();
        System.out.println(tacGia_DAO.generateTacGia());
        
    }
   
    public void kiemTraSo(JTextField s){
    PlainDocument document = (PlainDocument) s.getDocument();
    document.setDocumentFilter(new DocumentFilter() {
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
            String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
            if (isValidInput(currentText, text)) {
                super.insertString(fb, offset, text, attr);
            }
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
            if (isValidInput(currentText, text)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    });
}

        private static boolean isValidInput(String currentText, String text) {
            if (text.isEmpty()) {
                return true;
            }

            for (char c : text.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }

            String newText = currentText + text;
            if (newText.charAt(0) == '0' && newText.length() > 1) {
                return false;
            }

            return true;
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
         private void DuLieuTinhTrang(){
            txtSoLuongTon.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if( Integer.parseInt(txtSoLuongTon.getText()) < soLuongToiThieu ){
                       
                        lblTinhTrang1.setText("Hết hàng");
                    }else{
                        lblTinhTrang1.setText("Còn hàng");
                    }
                }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if( Integer.parseInt(txtSoLuongTon.getText()) < soLuongToiThieu ){
                       
                        lblTinhTrang1.setText("Hết hàng");
                    }else{
                        lblTinhTrang1.setText("Còn hàng");
                    }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if( Integer.parseInt(txtSoLuongTon.getText()) < soLuongToiThieu ){
                       
                        lblTinhTrang1.setText("Hết hàng");
                    }else{
                        lblTinhTrang1.setText("Còn hàng");
                    }
            }
        });
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
    public void visibelLable(boolean a) {
    	// lblMaSanPham1.setVisible(a);
         lblTenSach1.setVisible(a);
         lblTacGia1.setVisible(a);
         lblTheLoai1.setVisible(a);
         lblSoTrang1.setVisible(a);
         lblGiaBan1.setVisible(a);
         lblSoLuongTon1.setVisible(a);
         lblNhaXuatBan1.setVisible(a);
       //  lblTinhTrang1.setVisible(a);
         lblNhaCungCap1.setVisible(a);
         lblNamXuatBan1.setVisible(a);
    }
 public void visibelTextField(boolean a) {
    	// txtMaSanPham.setVisible(a);
         txtTenSach.setVisible(a);
         cboTacGia.setVisible(a);
         cboTheLoai.setVisible(a);
         txtSoTrang.setVisible(a);
         txtGiaBan.setVisible(a);
         txtSoLuongTon.setVisible(a);
         cboNhaXuatBan.setVisible(a);
       //  txtTinhTrang.setVisible(a);
         cboNhaCungCap.setVisible(a);
         txtNamXuatBan.setVisible(a);
    }
  private void dataTheLoai(JComboBox combo) {
    	theLoai_DAO = new TheLoai_DAO();
    	ArrayList<TheLoai> dstl = theLoai_DAO.layDanhSachTheLoai();
    
    	String[] tenTheLoaiArray = new String[dstl.size()];
    	for (int i = 0; i < dstl.size(); i++) {
    	    tenTheLoaiArray[i] = dstl.get(i).getTenTheLoai();
    	}

    	// Đặt mô hình cho ComboBox sử dụng mảng tên thể loại
        
    	combo.setModel(new javax.swing.DefaultComboBoxModel<>(tenTheLoaiArray));
    }
    
    private void testData(JComboBox combo) {
    	tacGia_DAO = new TacGia_DAO();
    	ArrayList<TacGia> dstg = tacGia_DAO.layDanhSachTacGia();
    	String[] tacGiaArray = new String[dstg.size()];
    	for (int i = 0; i < dstg.size(); i++) {
    	   tacGiaArray[i] = dstg.get(i).getTenTacGia();
    	}

    	// Đặt mô hình cho ComboBox sử dụng mảng tên thể loại
    	combo.setModel(new javax.swing.DefaultComboBoxModel<>(tacGiaArray));
    }
    public void dataTacGia(){
        testData(cboTacGia);
        selectedItemsTacGia = new ArrayList<>();
        cboTacGia.removeSelectedItems();
        
        String inputTacGia = lblTacGia1.getText();
        String[] itemTacGia = inputTacGia.split(",");

       for (String items : itemTacGia) {
           selectedItemsTacGia.add(items);
       }
        cboTacGia.setSelectedItems( selectedItemsTacGia);
      
    }
    public void dataTheLoai(){
        
       
      dataTheLoai(cboTheLoai);
      selectedItemsTheLoai= new ArrayList<>();
      cboTheLoai.removeSelectedItems();
      
      String inputTheLoai =lblTheLoai1.getText();
      String[] itemTheLoai = inputTheLoai.split(",");
    
     for (String items : itemTheLoai) {
    	 selectedItemsTheLoai.add(items);
     }
      cboTheLoai.setSelectedItems(selectedItemsTheLoai);
    }
    public void dataTextField() {
    	
      txtMaSanPham.setText(sach.getMaSanPham());
      txtTenSach.setText(sach.getTenSanPham());
     // cboTacGia.setText(sach.getTacGia());
      dataTacGia();
      // txtTheLoai.setText(sach.getTheLoai().getMaTheLoai());
       dataTheLoai();
       
      txtSoTrang.setText(sach.getSoTrang()+"");
      txtGiaBan.setText(sach.getDonGia()+"");
      txtSoLuongTon.setText(sach.getSoLuongTon()+"");
      
      nhaXuatBan_DAO = new NhaXuatBan_DAO();
      
      cboNhaXuatBan.setSelectedItem(lblNhaXuatBan1.getText());
 
     // cboNhaXuatBan.setText(sach.getNhaXuatBan().getMaNhaXuatBan());
      
        lblTinhTrang1.setText(sach.getTinhTrang());
        
     // cboNhaCungCap.setText(sach.getNhaCungCap().getMaNCC());
        txtNamXuatBan.setText(sach.getNamXuatBan()+"");
      cboNhaCungCap.setSelectedItem(lblNhaCungCap1.getText());

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLoaiSanPham = new javax.swing.JLabel();
        lblTacGia = new javax.swing.JLabel();
        lblTenSach = new javax.swing.JLabel();
        lblTheLoai = new javax.swing.JLabel();
        lblNhaXuatBan = new javax.swing.JLabel();
        txtSoLuongTon = new javax.swing.JTextField();
        txtTinhTrang = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        lblGiaBan = new javax.swing.JLabel();
        lblSoLuongTon = new javax.swing.JLabel();
        lblTinhTrang = new javax.swing.JLabel();
        btnSua = new javax.swing.JButton();
        btnNgungKinhDoanh = new javax.swing.JButton();
        txtTenSach = new javax.swing.JTextField();
        lblNamXuatBan = new javax.swing.JLabel();
        txtNamXuatBan = new javax.swing.JTextField();
        txtMaSanPham = new javax.swing.JTextField();
        txtSoTrang = new javax.swing.JTextField();
        lblMaSanPham = new javax.swing.JLabel();
        lblSoTrang = new javax.swing.JLabel();
        lblTacGia1 = new javax.swing.JLabel();
        lblTenSach1 = new javax.swing.JLabel();
        lblTheLoai1 = new javax.swing.JLabel();
        lblMaSanPham1 = new javax.swing.JLabel();
        lblNhaXuatBan1 = new javax.swing.JLabel();
        lblGiaBan1 = new javax.swing.JLabel();
        lblSoLuongTon1 = new javax.swing.JLabel();
        lblSoTrang1 = new javax.swing.JLabel();
        lblTinhTrang1 = new javax.swing.JLabel();
        lblNhaCungCap1 = new javax.swing.JLabel();
        lblNamXuatBan1 = new javax.swing.JLabel();
        lblNhaCungCap = new javax.swing.JLabel();
        cboTacGia = new ServiceUser.ComboBoxMultiSelection();
        cboTheLoai = new ServiceUser.ComboBoxMultiSelection();
        cboNhaXuatBan = new javax.swing.JComboBox<>();
        cboNhaCungCap = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        lblAnh = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        btnBanLai = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        setMinimumSize(new java.awt.Dimension(1150, 230));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(700, 230));
        setRequestFocusEnabled(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTacGia.setText("Tác giả");
        add(lblTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, -1, -1));

        lblTenSach.setText("Tên sách");
        add(lblTenSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        lblTheLoai.setText("Thể loại");
        add(lblTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, -1, -1));

        lblNhaXuatBan.setText("Nhà xuất bản");
        add(lblNhaXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, 70, -1));
        add(txtSoLuongTon, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 210, -1));
        add(txtTinhTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, 180, -1));

        txtGiaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaBanActionPerformed(evt);
            }
        });
        add(txtGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 210, -1));

        lblGiaBan.setText("Giá bán");
        add(lblGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, 70, -1));

        lblSoLuongTon.setText("Số lượng tồn");
        add(lblSoLuongTon, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, 80, -1));

        lblTinhTrang.setText("Tình trạng");
        add(lblTinhTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 20, 71, -1));

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 170, 62, -1));

        btnNgungKinhDoanh.setText("Ngừng kinh doanh");
        btnNgungKinhDoanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgungKinhDoanhActionPerformed(evt);
            }
        });
        add(btnNgungKinhDoanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 170, 130, -1));
        add(txtTenSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 260, -1));

        lblNamXuatBan.setText("Năm xuất bản");
        add(lblNamXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 120, 84, -1));
        add(txtNamXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 120, 180, -1));
        add(txtMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 147, -1));
        add(txtSoTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 210, -1));

        lblMaSanPham.setText("Mã sách");
        add(lblMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        lblSoTrang.setText("Số  trang");
        add(lblSoTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 50, -1));
        add(lblTacGia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 260, 30));
        add(lblTenSach1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 260, 20));
        add(lblTheLoai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 260, 30));
        add(lblMaSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 140, 20));
        add(lblNhaXuatBan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 210, 30));
        add(lblGiaBan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 210, 20));
        add(lblSoLuongTon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 210, 20));
        add(lblSoTrang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 210, 20));
        add(lblTinhTrang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, 180, 20));
        add(lblNhaCungCap1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 60, 180, 30));
        add(lblNamXuatBan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 120, 180, 20));

        lblNhaCungCap.setText("Nhà cung cấp");
        add(lblNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 70, 84, -1));
        add(cboTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 260, 30));
        add(cboTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 260, 30));

        add(cboNhaXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 210, 30));

        add(cboNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 60, 180, 30));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        lblAnh.setBackground(new java.awt.Color(255, 255, 255));
        lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 160, 210));

        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 170, 62, -1));

        btnBanLai.setText("Bán lại");
        btnBanLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanLaiActionPerformed(evt);
            }
        });
        add(btnBanLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 170, 130, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
         visibelTextField(true);
         visibelLable(false);
         dataTextField() ;
         btnSua.setVisible(false);
         btnLuu.setVisible(true);
         show = true;
         
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnNgungKinhDoanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgungKinhDoanhActionPerformed
        // TODO add your handling code here:
     
        sach_DAO = new Sach_DAO();
        int hoiNhac = JOptionPane.showConfirmDialog(this, "Sản phẩm "+lblMaSanPham1.getText()+" có muốn ngừng kinh doanh không?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if(hoiNhac == JOptionPane.YES_OPTION){
                   
                    lblTinhTrang1.setText("Ngừng kinh doanh");
                    ngungKinhDoanh();
                  if(sach_DAO.updateTinhTrang(sach.getMaSanPham(), lblTinhTrang1.getText())){ 
            }
       
        }
        
    }//GEN-LAST:event_btnNgungKinhDoanhActionPerformed
    public void ngungKinhDoanh(){
        btnSua.setVisible(false);
        btnLuu.setVisible(false);
        btnNgungKinhDoanh.setVisible(false);
        btnBanLai.setVisible(true);
        setBackground(Color.GRAY);
    }
    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        visibelTextField(false);
        visibelLable(true);
        btnLuu.setVisible(false);
        btnSua.setVisible(true);
        show = false;
        String maSach = lblMaSanPham1.getText();
        String tenSach = txtTenSach.getText();
       
        List<Object> ItemTacGia = cboTacGia.getSelectedItems();
       String tacGia = "";
        int count = 0;
        for (Object item : ItemTacGia) {
                 count++;
         }
        int countCong = 0;
        for (Object item : ItemTacGia) {
          //  System.out.println(item.toString());
            if(count-1 == countCong)
            	tacGia+=item.toString();
            else
            	tacGia+=item.toString()+",";
            countCong++;
        }
        TacGia tacGias= new TacGia(tacGia);
        String theLoai= "";
        
         List<Object> ItemTheLoai = cboTheLoai.getSelectedItems();
        int countTheLoai = 0;
        for (Object item : ItemTheLoai) {
                 countTheLoai++;
         }
        int countCongTheLoai = 0;
        for (Object item : ItemTheLoai) {
          //  System.out.println(item.toString());
            if(countTheLoai-1 == countCongTheLoai)
            	theLoai+=item.toString();
            else
            	theLoai+=item.toString()+",";
            countCongTheLoai++;
        }
        
        TheLoai tl = new TheLoai(theLoai);
        String soTrang = txtSoTrang.getText();
        
        String txtgiaBan = txtGiaBan.getText();
              
        // Xóa dấu phẩy trong chuỗi
	     String cleanedInput = txtgiaBan.replaceAll(",", "");
	     // Chuyển đổi thành số
	     double giaBan = Double.parseDouble(cleanedInput);
             
        String soLuongTon = txtSoLuongTon.getText();
        String nhaXuatBan = cboNhaXuatBan.getSelectedItem()+"";
        String tinhTrang = lblTinhTrang1.getText();
        String nhaXuatBanDuocChon = nhaXuatBan;
        
        nhaXuatBan_DAO = new NhaXuatBan_DAO();
        
        ArrayList<NhaXuatBan> dsnxb = nhaXuatBan_DAO.layDanhSachNhaXuatBan();
        for (NhaXuatBan nhaXuatBan1 : dsnxb) {
            if( nhaXuatBan1.getTenNhaXuatBanl().equals(nhaXuatBan)) {
                 
      		 nhaXuatBan = nhaXuatBan1.getMaNhaXuatBan();
      	  }
        }
        System.out.println( nhaXuatBan);
         NhaXuatBan nxb = new NhaXuatBan(nhaXuatBan);
        
        String nhaCungCapDuocChon = "";
        nhaCungCap_DAO = new NhaCungCap_DAO();
       ArrayList<NhaCungCap> dsncc = nhaCungCap_DAO.layDanhSachNhaCungCap();
       String nhaCungCap_Sua = "";
        for (NhaCungCap nhaCungCap : dsncc) {
            if(cboNhaCungCap.getSelectedItem().equals(nhaCungCap.getTenNCC())){
                nhaCungCap_Sua = nhaCungCap.getMaNCC()+"";
                
            }
        }
        
        
        LoaiSanPham loaiSanPham = new LoaiSanPham(sach.getLoaiSanPham().getMaLoaiSanPham());
        NhaCungCap ncc = new NhaCungCap(nhaCungCap_Sua);
        String namXuatBan = txtNamXuatBan.getText();
        String hinhAnh = selectedFile.getAbsolutePath();
       tinhTrang = "Hết hàng";
        if(Integer.parseInt(soLuongTon) >= soLuongToiThieu){
            tinhTrang = "Còn hàng";
        }
        sach = new Sach(tacGias,  Integer.parseInt(namXuatBan), Integer.parseInt(soTrang), tl, nxb, maSach, tenSach, loaiSanPham, ncc, Integer.parseInt(soLuongTon), giaBan, tinhTrang, hinhAnh);
        sach_DAO = new Sach_DAO();
       // System.out.println();
        
        if(sach_DAO.updateSach(sach)){
           
                   
        lblTenSach1.setText(tenSach);
        lblTacGia1.setText(tacGia);
        lblTheLoai1.setText(theLoai);
        lblSoTrang1.setText(soTrang);
        lblGiaBan1.setText(txtgiaBan);
        lblSoLuongTon1.setText(soLuongTon);
        lblNhaXuatBan1.setText(nhaXuatBanDuocChon);
        lblTinhTrang1.setText(tinhTrang);
        
        lblNhaCungCap1.setText(cboNhaCungCap.getSelectedItem().toString());
        JOptionPane.showMessageDialog(this,"Cập nhật sách thành công");
        
        }
       
    }//GEN-LAST:event_btnLuuActionPerformed

    private void txtGiaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaBanActionPerformed

    private void lblAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMouseClicked
        // TODO add your handling code here:
     
            new FrmSuaAnhSua().setVisible(true);
       
    }//GEN-LAST:event_lblAnhMouseClicked
 
public class FrmSuaAnhSua extends javax.swing.JFrame {

    public FrmSuaAnhSua() {
        initComponents();
        try {
            init();
        } catch (IOException ex) {
            Logger.getLogger(CellSach.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void init() throws IOException{
        lblTenSachKyTu.setText(sach.getTenSanPham());
        try {
            BufferedImage image = ImageIO.read(selectedFile); // Thay đổi đường dẫn đến ảnh
            
            // thay đổi kích thức ảnh cùng kích thước với lable 184x216
             Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                    // Tạo ImageIcon với ảnh đã điều chỉnh kích thước
                    ImageIcon imageIcon = new ImageIcon(scaledImage);

                    // Thiết lập ImageIcon cho JLabel
                    jLabel1.setIcon(imageIcon);
           
        } catch (IOException e) {
        }
        addDataComboNgonNgu(cboNgonNgu);
        cboNgonNgu.setSelectedItem(sach.getNgonNgu());
        taxMoTa.setText(sach.getMoTa());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnChonAnh = new javax.swing.JButton();
        lblTenSach = new javax.swing.JLabel();
        lblTenSachKyTu = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taxMoTa = new javax.swing.JTextArea();
        cboNgonNgu = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btnCapNhat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Mô tả");

        btnChonAnh.setText("Chọn Ảnh");
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });

        lblTenSach.setText("Tên sách");

        taxMoTa.setColumns(20);
        taxMoTa.setRows(5);
        jScrollPane1.setViewportView(taxMoTa);

        jLabel5.setText("Ngôn ngữ");

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTenSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(btnChonAnh)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(cboNgonNgu, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenSachKyTu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(btnCapNhat)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChonAnh))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(lblTenSach))
                            .addComponent(lblTenSachKyTu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboNgonNgu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(btnCapNhat)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        
                      

     private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
         clickChonAnhFrmSua();
    }   
      private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        sach_DAO = new Sach_DAO();
        String hinhAnh = selectedFile.getAbsolutePath();
        sach.setHinhAnh(hinhAnh);
        sach.setNgonNgu(cboNgonNgu.getSelectedItem()+"");
        sach.setMoTa(taxMoTa.getText());
        if( sach_DAO.updateSachNgonNguMoTa(sach)){
            JOptionPane.showMessageDialog(this, "Cập nhật thành công");
           BufferedImage b;
                    try {
                        b = ImageIO.read(selectedFile);
                        // Thiết lập kích thước ảnh bằng với kích thước của JLabel
                  
                    
                    Image scaledImage = b.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                    // Tạo ImageIcon với ảnh đã điều chỉnh kích thước
                    ImageIcon imageIcon = new ImageIcon(scaledImage);

                    // Thiết lập ImageIcon cho JLabel
                    lblAnh.setIcon(imageIcon);
                     //  lblAnh.setIcon(new javax.swing.ImageIcon(b));

                    } catch (Exception e) {
                    }
        }
       
        
    }   
     private void addDataComboNgonNgu(JComboBox cbo){
         cbo.setModel(new DefaultComboBoxModel<>(
            new String[]{
            "Tiếng Việt",
            "Tiếng Anh",
            "Tiếng Trung",
            "Tiếng Đức",
            "Tiếng Tây Ban Nha",
            "Tiếng Nhật",
            "Tiếng Nga"
            }
         ));
     }
     private void clickChonAnhFrmSua(){
         JFileChooser fileChooser = new JFileChooser(selectedFile);
                fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "jpeg", "png", "gif"));

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // Lấy tệp được chọn
                    selectedFile = fileChooser.getSelectedFile();
                  //  String filePath = selectedFile.getAbsolutePath().replace("\\", "/");
                  //  filePath = "/"+filePath;
                   // System.out.println("Đường dẫn tệp: " + filePath);
                   // lblAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource(filePath)));
                    BufferedImage b;
                    try {
                        b = ImageIO.read(selectedFile);
                        // Thiết lập kích thước ảnh bằng với kích thước của JLabel
                    int labelWidth = jLabel1.getWidth();
                    int labelHeight = jLabel1.getHeight();
                    
                    Image scaledImage = b.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

                    // Tạo ImageIcon với ảnh đã điều chỉnh kích thước
                    ImageIcon imageIcon = new ImageIcon(scaledImage);

                    // Thiết lập ImageIcon cho JLabel
                    jLabel1.setIcon(imageIcon);
                     //  lblAnh.setIcon(new javax.swing.ImageIcon(b));

                    } catch (Exception e) {
                    }
                            
                }
    }
    // Variables declaration - do not modify                     
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnChonAnh;
    private javax.swing.JComboBox<String> cboNgonNgu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTenSach;
    private javax.swing.JLabel lblTenSachKyTu;
    private javax.swing.JTextArea taxMoTa;
    // End of variables declaration                   
}

    private void clickChonAnh(){
         JFileChooser fileChooser = new JFileChooser(selectedFile);
                fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "jpeg", "png", "gif"));

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // Lấy tệp được chọn
                    selectedFile = fileChooser.getSelectedFile();
                  //  String filePath = selectedFile.getAbsolutePath().replace("\\", "/");
                  //  filePath = "/"+filePath;
                   // System.out.println("Đường dẫn tệp: " + filePath);
                   // lblAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource(filePath)));
                    BufferedImage b;
                    try {
                        b = ImageIO.read(selectedFile);
                        // Thiết lập kích thước ảnh bằng với kích thước của JLabel
                    int labelWidth = lblAnh.getWidth();
                    int labelHeight = lblAnh.getHeight();
                    
                    Image scaledImage = b.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

                    // Tạo ImageIcon với ảnh đã điều chỉnh kích thước
                    ImageIcon imageIcon = new ImageIcon(scaledImage);

                    // Thiết lập ImageIcon cho JLabel
                    lblAnh.setIcon(imageIcon);
                     //  lblAnh.setIcon(new javax.swing.ImageIcon(b));

                    } catch (Exception e) {
                    }
                            
                }
    }
    private void btnBanLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanLaiActionPerformed
        // TODO add your handling code here:
        btnNgungKinhDoanh.setVisible(true);
        btnBanLai.setVisible(false);
        btnSua.setVisible(true);
        lblTinhTrang1.setText("Còn hàng");
        if( Integer.parseInt(lblSoLuongTon1.getText() )< soLuongToiThieu){
             lblTinhTrang1.setText("Hết hàng");
        }
        setBackground(new Color(255,255,255));
        sach_DAO = new Sach_DAO();
        System.out.println(sach.getMaSanPham());
        if(sach_DAO.updateTinhTrang(sach.getMaSanPham(), lblTinhTrang1.getText())){
            JOptionPane.showMessageDialog(this, "Bán lại sản phẩm");
        }
    }//GEN-LAST:event_btnBanLaiActionPerformed
   
    




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanLai;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnNgungKinhDoanh;
    private javax.swing.JButton btnSua;
    private javax.swing.JComboBox<String> cboNhaCungCap;
    private javax.swing.JComboBox<String> cboNhaXuatBan;
    private ServiceUser.ComboBoxMultiSelection cboTacGia;
    private ServiceUser.ComboBoxMultiSelection cboTheLoai;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblGiaBan;
    private javax.swing.JLabel lblGiaBan1;
    private javax.swing.JLabel lblLoaiSanPham;
    private javax.swing.JLabel lblMaSanPham;
    private javax.swing.JLabel lblMaSanPham1;
    private javax.swing.JLabel lblNamXuatBan;
    private javax.swing.JLabel lblNamXuatBan1;
    private javax.swing.JLabel lblNhaCungCap;
    private javax.swing.JLabel lblNhaCungCap1;
    private javax.swing.JLabel lblNhaXuatBan;
    private javax.swing.JLabel lblNhaXuatBan1;
    private javax.swing.JLabel lblSoLuongTon;
    private javax.swing.JLabel lblSoLuongTon1;
    private javax.swing.JLabel lblSoTrang;
    private javax.swing.JLabel lblSoTrang1;
    private javax.swing.JLabel lblTacGia;
    private javax.swing.JLabel lblTacGia1;
    private javax.swing.JLabel lblTenSach;
    private javax.swing.JLabel lblTenSach1;
    private javax.swing.JLabel lblTheLoai;
    private javax.swing.JLabel lblTheLoai1;
    private javax.swing.JLabel lblTinhTrang;
    private javax.swing.JLabel lblTinhTrang1;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtNamXuatBan;
    private javax.swing.JTextField txtSoLuongTon;
    private javax.swing.JTextField txtSoTrang;
    private javax.swing.JTextField txtTenSach;
    private javax.swing.JTextField txtTinhTrang;
    // End of variables declaration//GEN-END:variables
}
