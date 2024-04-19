
package ServiceUser;


import DAO.LoaiVanPhongPham_DAO;
import java.util.ArrayList;
import java.util.List;
import DAO.NhaCungCap_DAO;
import DAO.Sach_DAO;
import DAO.TacGia_DAO;
import DAO.TheLoai_DAO;
import DAO.ThuongHieu_DAO;
import DAO.VanPhongPham_DAO;
import DAO.XuatXu_DAO;
import Entity.LoaiSanPham;
import Entity.LoaiVanPhongPham;
import Entity.NhaCungCap;
import Entity.ThuongHieu;
import Entity.VanPhongPham;
import Entity.XuatXu;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
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
public class CellVanPhongPham extends javax.swing.JPanel {
    	private VanPhongPham vanPhongPham;
	private NhaCungCap_DAO nhaCungCap_DAO;
	private TheLoai_DAO theLoai_DAO;
	private List<Object> selectedItemsTheLoai;
	private List<Object>   selectedItemsTacGia;
        private Sach_DAO sach_DAO;
	private TacGia_DAO tacGia_DAO;
        private XuatXu_DAO xuatXu_DAO;
        private int soLuongToiThieu = 10;
         private int width = 148;
        private int height = 198;
    /**
     * Creates new form CellSach
     */

    private File selectedFile;
    private boolean show = false;
    private ThuongHieu_DAO thuongHieu_DAO;
    private LoaiVanPhongPham_DAO loaiVanPhongPham_DAO;
    private VanPhongPham_DAO vanPhongPham_DAO;
    public CellVanPhongPham(VanPhongPham vanPhongPham) throws IOException {
        this.vanPhongPham = vanPhongPham;
         try {
            // Set the Look and Feel for the entire application
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        initComponents();
        lblLoaiSanPham.setText(vanPhongPham.getLoaiSanPham().getMaLoaiSanPham());
        txtTinhTrang.setVisible(false);
        txtMaSanPham.setVisible(false);
        
        lblMaSanPham1.setText(vanPhongPham.getMaSanPham());
        
        lblTenVanPhongPham1.setText(vanPhongPham.getTenSanPham());
        
        xuatXu_DAO = new XuatXu_DAO();
        ArrayList<XuatXu> dsxx = xuatXu_DAO.layDanhSachXuatXu();
        for (XuatXu xuatXu : dsxx) {
            cboXuatXu.addItem(xuatXu.getTenQuocGia());
                if(xuatXu.getMaXuatXu().equals(vanPhongPham.getXuatXu().getMaXuatXu()))
                    lblXuatXu1.setText(xuatXu.getTenQuocGia());
        }
        
        cboXuatXu.setSelectedIndex(-1);
        
        thuongHieu_DAO = new ThuongHieu_DAO();
        ArrayList<ThuongHieu> dsth = thuongHieu_DAO.layDanhSachThuongHieu();
        for (ThuongHieu thuongHieu : dsth) {
            cboThuongHieu.addItem(thuongHieu.getTenThuongHieu());
                if(thuongHieu.getMaThuongHieu().equals(vanPhongPham.getThuongHieu().getMaThuongHieu()))
                   lblThuongHieu1.setText(thuongHieu.getTenThuongHieu());
        }
        loaiVanPhongPham_DAO = new LoaiVanPhongPham_DAO();
          ArrayList<LoaiVanPhongPham> dslvpp = loaiVanPhongPham_DAO.layDanhLoaiVanPhongPham();
          for (LoaiVanPhongPham loaiVanPhongPham : dslvpp) {
            cboLoaiVanPhongPham.addItem(loaiVanPhongPham.getTenLoaiVanPhongPham());
            if(loaiVanPhongPham.getMaLoaiVanPhongPham().equals(vanPhongPham.getLoaiVanPhongPham().getMaLoaiVanPhongPham())){
                
                lblLoaiVanPhongpham1.setText(loaiVanPhongPham.getTenLoaiVanPhongPham());
            }
                    
        }
       // lblTheLoai1.setText(sach.getTheLoai().getMaTheLoai());
        
      
        lblGiaBan1.setText(vanPhongPham.getDonGia()+"");
        lblSoLuongTon1.setText(vanPhongPham.getSoLuongTon()+"");
        
        lblChatLieu1.setText(vanPhongPham.getChatLieu());
        
        lblTinhTrang1.setText(vanPhongPham.getTinhTrang());
        
        lblNamSanXuat1.setText(vanPhongPham.getNamSanXuat()+"");
        
//        nhaXuatBan_DAO = new NhaXuatBan_DAO();
//        ArrayList<NhaXuatBan> dsnxb = nhaXuatBan_DAO.layDanhSachNhaXuatBan();
//        for (NhaXuatBan nhaXuatBan : dsnxb) {
//      	  cboChatLieu.addItem(nhaXuatBan.getTenNhaXuatBanl());
//        }
//        for (NhaXuatBan nhaXuatBan : dsnxb) {
//      	  if( nhaXuatBan.getMaNhaXuatBan().equals(lblChatLieu1.getText())) {
//      		  lblChatLieu1.setText(nhaXuatBan.getTenNhaXuatBanl());
//      	  }
//      	 
//        }
        
        nhaCungCap_DAO = new NhaCungCap_DAO();
       ArrayList<NhaCungCap> dsncc = nhaCungCap_DAO.layDanhSachNhaCungCap();
        for (NhaCungCap nhaCungCap : dsncc) {
            if(nhaCungCap.getSanPhamCungCap().equals("Văn phòng phẩm")){
                
                cboNhaCungCap.addItem(nhaCungCap.getTenNCC());
                if(vanPhongPham.getNhaCungCap().getMaNCC().equals(nhaCungCap.getMaNCC()))
                    lblNhaCungCap1.setText(nhaCungCap.getTenNCC());
            }
        }
	visibelTextField(false);
        visibelLable(true);
        
        lblAnh.setText("1");
        System.out.println(vanPhongPham.getHinhAnh());
        try {
            selectedFile =new File(vanPhongPham.getHinhAnh());
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
       // kiemTraSo(txtThuongHieu);
        kiemTraSo(txtSoLuongTon);
        kiemTraSo(txtNamSanXuat);
       
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
        private void duLieuTenSach(){
            txtTenVanPhongPham.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    String text = txtTenVanPhongPham.getText();
                    String formattedText = vietHoaChuCaiDauTienTrongJtextField(text);
                    txtTenVanPhongPham.setText(formattedText);
                }
            });

                    // Tạo một DocumentFilter để kiểm tra và lọc ký tự
            AbstractDocument document = (AbstractDocument) txtTenVanPhongPham.getDocument();
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
           lblTenVanPhongPham1.setVisible(a);
           lblLoaiVanPhongpham1.setVisible(a);
         lblXuatXu1.setVisible(a);
         lblThuongHieu1.setVisible(a);
         lblGiaBan1.setVisible(a);
         lblSoLuongTon1.setVisible(a);
         lblChatLieu1.setVisible(a);
       //  lblTinhTrang1.setVisible(a);
         lblNhaCungCap1.setVisible(a);
         lblNamSanXuat1.setVisible(a);
    }
 public void visibelTextField(boolean a) {
    	// txtMaSanPham.setVisible(a);
         txtTenVanPhongPham.setVisible(a);
         txtGiaBan.setVisible(a);
         txtSoLuongTon.setVisible(a);
         txtNamSanXuat.setVisible(a);
         cboLoaiVanPhongPham.setVisible(a);
         cboXuatXu.setVisible(a);
          cboThuongHieu.setVisible(a);
         cboChatLieu.setVisible(a);
       //  txtTinhTrang.setVisible(a);
         cboNhaCungCap.setVisible(a);
         
    }

    public void dataTextField() {
    	
      //txtMaSanPham.setText(sach.getMaSanPham());
      txtTenVanPhongPham.setText(vanPhongPham.getTenSanPham());
     // cboTacGia.setText(sach.getTacGia());
   
      // txtTheLoai.setText(sach.getTheLoai().getMaTheLoai());
       
       
      //txtThuongHieu.setText(sach.getSoTrang()+"");
      
      
      txtGiaBan.setText(vanPhongPham.getDonGia()+"");
      txtSoLuongTon.setText(vanPhongPham.getSoLuongTon()+"");
      
      //nhaXuatBan_DAO = new NhaXuatBan_DAO();
      
      
      cboChatLieu.setSelectedItem(lblChatLieu1.getText());
 
     // cboNhaXuatBan.setText(sach.getNhaXuatBan().getMaNhaXuatBan());
      
        lblTinhTrang1.setText(vanPhongPham.getTinhTrang());
      
     // cboNhaCungCap.setText(sach.getNhaCungCap().getMaNCC());
      txtNamSanXuat.setText(vanPhongPham.getNamSanXuat()+"");
      cboXuatXu.setSelectedItem(lblXuatXu1.getText());
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
        lblXuatXu = new javax.swing.JLabel();
        lblTenSach = new javax.swing.JLabel();
        lblLoaiVanPhongPham = new javax.swing.JLabel();
        lblChatLieu = new javax.swing.JLabel();
        txtSoLuongTon = new javax.swing.JTextField();
        txtTinhTrang = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        lblGiaBan = new javax.swing.JLabel();
        lblSoLuongTon = new javax.swing.JLabel();
        lblTinhTrang = new javax.swing.JLabel();
        btnSua = new javax.swing.JButton();
        btnNgungKinhDoanh = new javax.swing.JButton();
        txtTenVanPhongPham = new javax.swing.JTextField();
        lblNamSanXuat = new javax.swing.JLabel();
        txtNamSanXuat = new javax.swing.JTextField();
        txtMaSanPham = new javax.swing.JTextField();
        lblMaSanPham = new javax.swing.JLabel();
        lblThuongHieu = new javax.swing.JLabel();
        lblXuatXu1 = new javax.swing.JLabel();
        lblLoaiVanPhongpham1 = new javax.swing.JLabel();
        lblMaSanPham1 = new javax.swing.JLabel();
        lblChatLieu1 = new javax.swing.JLabel();
        lblGiaBan1 = new javax.swing.JLabel();
        lblSoLuongTon1 = new javax.swing.JLabel();
        lblThuongHieu1 = new javax.swing.JLabel();
        lblTinhTrang1 = new javax.swing.JLabel();
        lblNhaCungCap1 = new javax.swing.JLabel();
        lblNamSanXuat1 = new javax.swing.JLabel();
        lblNhaCungCap = new javax.swing.JLabel();
        cboChatLieu = new javax.swing.JComboBox<>();
        cboNhaCungCap = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        lblAnh = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        btnBanLai = new javax.swing.JButton();
        lblTenVanPhongPham1 = new javax.swing.JLabel();
        cboXuatXu = new javax.swing.JComboBox<>();
        cboThuongHieu = new javax.swing.JComboBox<>();
        cboLoaiVanPhongPham = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        setMinimumSize(new java.awt.Dimension(1150, 230));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(700, 230));
        setRequestFocusEnabled(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblXuatXu.setText("Xuất xứ");
        add(lblXuatXu, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, -1, -1));

        lblTenSach.setText("Tên văn phòng phẩm");
        add(lblTenSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));

        lblLoaiVanPhongPham.setText("Loại văn phòng phẩm");
        add(lblLoaiVanPhongPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 120, -1));

        lblChatLieu.setText("Chất liệu");
        add(lblChatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 180, 70, -1));
        add(txtSoLuongTon, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 130, 210, -1));
        add(txtTinhTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 30, 180, -1));

        txtGiaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaBanActionPerformed(evt);
            }
        });
        add(txtGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, 210, -1));

        lblGiaBan.setText("Giá bán");
        add(lblGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, 70, -1));

        lblSoLuongTon.setText("Số lượng tồn");
        add(lblSoLuongTon, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 130, 80, -1));

        lblTinhTrang.setText("Tình trạng");
        add(lblTinhTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 30, 71, -1));

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 170, 62, -1));

        btnNgungKinhDoanh.setText("Ngừng kinh doanh");
        btnNgungKinhDoanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgungKinhDoanhActionPerformed(evt);
            }
        });
        add(btnNgungKinhDoanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 170, 130, -1));
        add(txtTenVanPhongPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 260, -1));

        lblNamSanXuat.setText("Năm sản xuất");
        add(lblNamSanXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 130, 84, -1));
        add(txtNamSanXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 130, 180, -1));
        add(txtMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 147, -1));

        lblMaSanPham.setText("Mã văn phòng phẩm");
        add(lblMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        lblThuongHieu.setText("Thương hiệu");
        add(lblThuongHieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, 80, -1));
        add(lblXuatXu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 260, 30));
        add(lblLoaiVanPhongpham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 260, 30));
        add(lblMaSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 140, 20));
        add(lblChatLieu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 170, 210, 30));
        add(lblGiaBan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, 210, 20));
        add(lblSoLuongTon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 130, 210, 20));
        add(lblThuongHieu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 210, 30));
        add(lblTinhTrang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 30, 180, 20));
        add(lblNhaCungCap1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 70, 180, 30));
        add(lblNamSanXuat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 130, 180, 20));

        lblNhaCungCap.setText("Nhà cung cấp");
        add(lblNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 80, 84, -1));

        cboChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhựa", "Gỗ", "Cao su" }));
        cboChatLieu.setSelectedIndex(-1);
        add(cboChatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 170, 210, 30));

        add(cboNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 70, 180, 30));

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
        add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 170, 62, -1));

        btnBanLai.setText("Bán lại");
        btnBanLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanLaiActionPerformed(evt);
            }
        });
        add(btnBanLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 170, 130, -1));
        add(lblTenVanPhongPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 260, 20));

        add(cboXuatXu, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 172, 260, 30));

        add(cboThuongHieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 22, 200, 30));

        add(cboLoaiVanPhongPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 122, 260, 30));
    }// </editor-fold>//GEN-END:initComponents
    public class FrmSuaAnhVPP extends javax.swing.JFrame {

    public FrmSuaAnhVPP() {
        initComponents();
        init();
    }
    public void init(){
      lblTenSachKyTu.setText(vanPhongPham.getTenSanPham());
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
        taxMoTa.setText(vanPhongPham.getMoTa());
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
        btnCapNhat = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Mô tả");

        btnChonAnh.setText("Chọn Ảnh");
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });

        lblTenSach.setText("Tên văn phòng phẩm");

        taxMoTa.setColumns(20);
        taxMoTa.setRows(5);
        taxMoTa.setPreferredSize(new java.awt.Dimension(200, 84));
        jScrollPane1.setViewportView(taxMoTa);

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Thông tin văn phòng phẩm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTenSach)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(btnChonAnh)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenSachKyTu, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(btnCapNhat)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChonAnh)
                        .addGap(65, 65, 65)
                        .addComponent(btnCapNhat))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTenSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTenSachKyTu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
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
        vanPhongPham_DAO = new VanPhongPham_DAO();
        String hinhAnh = selectedFile.getAbsolutePath();
        vanPhongPham.setHinhAnh(hinhAnh);
        vanPhongPham.setMoTa(taxMoTa.getText());
        if( vanPhongPham_DAO.updateVanPhongPhamMoTaHinhAnh(vanPhongPham)){
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTenSach;
    private javax.swing.JLabel lblTenSachKyTu;
    private javax.swing.JTextArea taxMoTa;
    // End of variables declaration                   
}
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
      
        vanPhongPham_DAO = new VanPhongPham_DAO();
        int hoiNhac = JOptionPane.showConfirmDialog(this, "Sản phẩm "+lblMaSanPham1.getText()+" có muốn ngừng kinh doanh không?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if(hoiNhac == JOptionPane.YES_OPTION){
  
        lblTinhTrang1.setText("Ngừng kinh doanh");
        ngungKinhDoanh();
        if(vanPhongPham_DAO.updateTinhTrangVanPhongPham(vanPhongPham.getMaSanPham(), lblTinhTrang1.getText())){
            
        }
        }
      
//        sach_DAO = new Sach_DAO();
//        if(sach_DAO.updateTinhTrang(vanPhongPham.getMaSanPham(), lblTinhTrang1.getText())){  
//        }
    
  
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
        String tenSach = txtTenVanPhongPham.getText();
       
//        List<Object> ItemTacGia = cboTacGia.getSelectedItems();
//       String tacGia = "";
//        int count = 0;
//        for (Object item : ItemTacGia) {
//                 count++;
//         }
//        int countCong = 0;
//        for (Object item : ItemTacGia) {
//          //  System.out.println(item.toString());
//            if(count-1 == countCong)
//            	tacGia+=item.toString();
//            else
//            	tacGia+=item.toString()+",";
//            countCong++;
//        }
//        TacGia tacGias= new TacGia(tacGia);
//        String theLoai= "";
//        
//         List<Object> ItemTheLoai = cboTheLoai.getSelectedItems();
//        int countTheLoai = 0;
//        for (Object item : ItemTheLoai) {
//                 countTheLoai++;
//         }
//        int countCongTheLoai = 0;
//        for (Object item : ItemTheLoai) {
//          //  System.out.println(item.toString());
//            if(countTheLoai-1 == countCongTheLoai)
//            	theLoai+=item.toString();
//            else
//            	theLoai+=item.toString()+",";
//            countCongTheLoai++;
//        }
        
        //TheLoai tl = new TheLoai(theLoai);
         loaiVanPhongPham_DAO = new LoaiVanPhongPham_DAO();
         String loaiVanPhongPham_CBO ="";
          ArrayList<LoaiVanPhongPham> dslvpp = loaiVanPhongPham_DAO.layDanhLoaiVanPhongPham();
          for (LoaiVanPhongPham loaiVanPhongPham : dslvpp) {
            if(loaiVanPhongPham.getTenLoaiVanPhongPham().equals(cboLoaiVanPhongPham.getSelectedItem())){
                loaiVanPhongPham_CBO  = loaiVanPhongPham.getMaLoaiVanPhongPham();
            }
                    
        }
        
        LoaiVanPhongPham loaiVanPhongPham = new LoaiVanPhongPham(loaiVanPhongPham_CBO);
        
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
        
        String txtgiaBan = txtGiaBan.getText();
              
        // Xóa dấu phẩy trong chuỗi
	     String cleanedInput = txtgiaBan.replaceAll(",", "");
	     // Chuyển đổi thành số
	     double giaBan = Double.parseDouble(cleanedInput);
             
        String soLuongTon = txtSoLuongTon.getText();
        String chatLieu = cboChatLieu.getSelectedItem().toString();
  
        
        String tinhTrang = lblTinhTrang1.getText();
        tinhTrang = "Hết hàng";
        if(Integer.parseInt(soLuongTon) > soLuongToiThieu){
            tinhTrang = "Còn hàng";
        }
        
        nhaCungCap_DAO = new NhaCungCap_DAO();
        ArrayList<NhaCungCap> dsncc = nhaCungCap_DAO.layDanhSachNhaCungCap();
        String nhaCungCap_Sua = "";
        for (NhaCungCap nhaCungCap : dsncc) {
            if(cboNhaCungCap.getSelectedItem().equals(nhaCungCap.getTenNCC())){
                nhaCungCap_Sua = nhaCungCap.getMaNCC()+"";
            }
        }
        
        NhaCungCap ncc = new NhaCungCap(nhaCungCap_Sua);
        
        LoaiSanPham loaiSanPham = new LoaiSanPham(vanPhongPham.getLoaiSanPham().getMaLoaiSanPham());
        
        
        
        String namXuatBan = txtNamSanXuat.getText();
        
        String hinhAnh = selectedFile.getAbsolutePath();
        
      vanPhongPham = new VanPhongPham(xuatXu,thuongHieu, loaiVanPhongPham, chatLieu, Integer.parseInt(namXuatBan), maSach, tenSach, loaiSanPham, ncc, Integer.parseInt(soLuongTon), giaBan, maSach, tinhTrang, hinhAnh);
//        sach_DAO = new Sach_DAO();
//       // System.out.println();
//        sach_DAO.updateSach(sach);
        System.out.println(vanPhongPham);
        
        vanPhongPham_DAO = new VanPhongPham_DAO();
        
        if(vanPhongPham_DAO.updateVanPhongPham(vanPhongPham )){
        
        lblTenVanPhongPham1.setText(tenSach);
        lblLoaiVanPhongpham1.setText(cboLoaiVanPhongPham.getSelectedItem().toString());
        lblXuatXu1.setText(cboXuatXu.getSelectedItem()+"");
        lblThuongHieu1.setText(cboThuongHieu.getSelectedItem().toString());
        lblGiaBan1.setText(txtgiaBan);
        lblSoLuongTon1.setText(soLuongTon);
        lblChatLieu1.setText(cboChatLieu.getSelectedItem().toString());
        lblNhaCungCap1.setText(cboNhaCungCap.getSelectedItem().toString());
        lblNamSanXuat1.setText(namXuatBan);
        lblTinhTrang1.setText(tinhTrang);
        JOptionPane.showMessageDialog(this, "Cập nhật văn phòng phầm");

        }
        
      
        
    }//GEN-LAST:event_btnLuuActionPerformed

    private void txtGiaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaBanActionPerformed

    private void lblAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMouseClicked
        // TODO add your handling code here:
        new FrmSuaAnhVPP().setVisible(true);
    }//GEN-LAST:event_lblAnhMouseClicked
 private void clickChonAnh(){
         JFileChooser fileChooser = new JFileChooser();
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
//        System.out.println(sach.getMaSanPham());
//        if(sach_DAO.updateTinhTrang(sach.getMaSanPham(), lblTinhTrang1.getText())){
           JOptionPane.showMessageDialog(this, "Bán lại sản phẩm");
//        }
    }//GEN-LAST:event_btnBanLaiActionPerformed
   
    




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanLai;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnNgungKinhDoanh;
    private javax.swing.JButton btnSua;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboLoaiVanPhongPham;
    private javax.swing.JComboBox<String> cboNhaCungCap;
    private javax.swing.JComboBox<String> cboThuongHieu;
    private javax.swing.JComboBox<String> cboXuatXu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblChatLieu;
    private javax.swing.JLabel lblChatLieu1;
    private javax.swing.JLabel lblGiaBan;
    private javax.swing.JLabel lblGiaBan1;
    private javax.swing.JLabel lblLoaiSanPham;
    private javax.swing.JLabel lblLoaiVanPhongPham;
    private javax.swing.JLabel lblLoaiVanPhongpham1;
    private javax.swing.JLabel lblMaSanPham;
    private javax.swing.JLabel lblMaSanPham1;
    private javax.swing.JLabel lblNamSanXuat;
    private javax.swing.JLabel lblNamSanXuat1;
    private javax.swing.JLabel lblNhaCungCap;
    private javax.swing.JLabel lblNhaCungCap1;
    private javax.swing.JLabel lblSoLuongTon;
    private javax.swing.JLabel lblSoLuongTon1;
    private javax.swing.JLabel lblTenSach;
    private javax.swing.JLabel lblTenVanPhongPham1;
    private javax.swing.JLabel lblThuongHieu;
    private javax.swing.JLabel lblThuongHieu1;
    private javax.swing.JLabel lblTinhTrang;
    private javax.swing.JLabel lblTinhTrang1;
    private javax.swing.JLabel lblXuatXu;
    private javax.swing.JLabel lblXuatXu1;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtNamSanXuat;
    private javax.swing.JTextField txtSoLuongTon;
    private javax.swing.JTextField txtTenVanPhongPham;
    private javax.swing.JTextField txtTinhTrang;
    // End of variables declaration//GEN-END:variables
}
