
package Them;

import DAO.NhaCungCap_DAO;
import DAO.NhaXuatBan_DAO;
import DAO.TheLoai_DAO;
import Entity.NhaCungCap;
import Entity.NhaXuatBan;
import Entity.TheLoai;
import static Pannel.pnlTraCuuNhanVien.readExcel_City;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author FPTSHOP
 */
public class frmNhaXuatBan extends javax.swing.JFrame {

    private TheLoai_DAO theLoai_DAO;
    private String tp = "Tỉnh/Thành phố";
    private String q = "Quận/huyện";
    private String cboDiaChi = "";
    private ArrayList<String> cities ;
    private ArrayList<String> districts ;
    private ArrayList<String> wardsDistricts ;
    private NhaXuatBan_DAO nhaXuatBan_DAO;
    /**
     * Creates new form JframThemTheLoai
     */
    public frmNhaXuatBan() {
        initComponents();
        theLoai_DAO = new  TheLoai_DAO();
        nhaXuatBan_DAO = new NhaXuatBan_DAO();
        try {
                lblMaNhaCungCapp1.setText( nhaXuatBan_DAO.generateNhaXuatBan());
            } catch (SQLException ex) {
                Logger.getLogger(frmNhaXuatBan.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        try {
            cities = readExcel_City();
        } catch (IOException ex) {
            Logger.getLogger(frmNhaXuatBan.class.getName()).log(Level.SEVERE, null, ex);
        }
          for (String city : cities) {
            cboTinhThanhPho.addItem(city);
        }
          
        DuLieuEmail();
        duLieuSDT();
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMaNhaCungCap = new javax.swing.JLabel();
        lblMaNhaCungCapp1 = new javax.swing.JLabel();
        lblTenNhaXuatBan = new javax.swing.JLabel();
        txtTenNhaXuatBan = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        lblSoDienThoai = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        cboTinhThanhPho = new javax.swing.JComboBox<>();
        cboQuanHuyen = new javax.swing.JComboBox<>();
        cboPhuongXa = new javax.swing.JComboBox<>();
        lblTieuDe = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblMaNhaCungCap.setText("Mã nhà xuất bản");

        lblTenNhaXuatBan.setText("Tên nhà cung cấp");

        btnLamMoi.setText("Làm mới ");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm nhà cung cấp");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        lblSoDienThoai.setText("Số điện thoại");

        lblDiaChi.setText("Địa chỉ");

        lblEmail.setText("Email");

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

        lblTieuDe.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe.setText("Thêm nhà xuất bản");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMaNhaCungCap)
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaNhaCungCapp1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(343, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(143, 143, 143)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cboTinhThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cboQuanHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboPhuongXa, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(btnLamMoi)
                .addGap(53, 53, 53)
                .addComponent(btnThem)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaNhaCungCapp1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaNhaCungCap))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenNhaXuatBan)
                    .addComponent(txtTenNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTinhThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboQuanHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPhuongXa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoi)
                    .addComponent(btnThem))
                .addContainerGap(108, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
       lamMoiDuLieu();
       
        
       
    }//GEN-LAST:event_btnLamMoiActionPerformed
    private void lamMoiDuLieu(){
        txtTenNhaXuatBan.setText("");
        txtEmail.setText("");
        txtSoDienThoai.setText("");
        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtSoDienThoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
          cboTinhThanhPho.setSelectedItem("Tỉnh/Thành phố");
    }
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        
        String maTheLoai = lblMaNhaCungCapp1.getText();
        String tenTheLoai = txtTenNhaXuatBan.getText();
        String soDienThoai = txtSoDienThoai.getText();
        String email = txtSoDienThoai.getText();
        String diaChi = "";
        if(!cboTinhThanhPho.getSelectedItem().equals("Tỉnh/Thành phố")){
            diaChi+=cboTinhThanhPho.getSelectedItem();
                if(!cboQuanHuyen.getSelectedItem().equals("Quận/huyện") ){
                    diaChi+="-"+cboQuanHuyen.getSelectedItem();
                    if(!cboPhuongXa.getSelectedItem().equals("Phường/xã") ){
                        diaChi+="-"+cboPhuongXa.getSelectedItem();
                    }
                }
        }
       // TheLoai theLoai = new TheLoai(maTheLoai, tenTheLoai);
        NhaXuatBan nhaXuatBan = new NhaXuatBan(maTheLoai, tenTheLoai, diaChi, soDienThoai, email);
       
        
        nhaXuatBan_DAO = new NhaXuatBan_DAO();
        
        if(nhaXuatBan_DAO.InsertNhaXuatBan(nhaXuatBan)){
            lamMoiDuLieu();
            try {
                lblMaNhaCungCapp1.setText( nhaXuatBan_DAO.generateNhaXuatBan());
            } catch (SQLException ex) {
                Logger.getLogger(frmNhaXuatBan.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            JOptionPane.showMessageDialog(this, "Thêm thành công thể loại");
        }
        
    }//GEN-LAST:event_btnThemActionPerformed

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
public static String readExcel_City_Id(String ip) throws IOException {
                    //Đọc dữ liệu từ file Diachi.xlsx
                    String cities = "";
                    FileInputStream file = new FileInputStream("src\\Li\\Tinh_2023.xlsx");
                    //Nạp file input stream đưa về dạng excel
                    XSSFWorkbook wb = new XSSFWorkbook(file);
                    //Đọc file từ Sheet 1 (bắt đầu từ số 0)
                    XSSFSheet sheet = wb.getSheetAt(0);
                    //Lấy các giá trị trong các cột
                    FormulaEvaluator formula = wb.getCreationHelper().createFormulaEvaluator();
                    int v = 0;
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
		FileInputStream file = new FileInputStream("src\\Li\\Book1.xlsx");
		//Nạp file input stream đưa về dạng excel
		XSSFWorkbook wb = new XSSFWorkbook(file);
		//Đọc file từ Sheet 1 (bắt đầu từ số 0)
		XSSFSheet sheet = wb.getSheetAt(0);
		//Lấy các giá trị trong các cột
		FormulaEvaluator formula = wb.getCreationHelper().createFormulaEvaluator();
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
		FileInputStream file = new FileInputStream("src\\Li\\Book1.xlsx");
		//Nạp file input stream đưa về dạng excel
		XSSFWorkbook wb = new XSSFWorkbook(file); 
		//Đọc file từ Sheet 1 (bắt đầu từ số 0)
		XSSFSheet sheet = wb.getSheetAt(0);
		//Lấy các giá trị trong các cột
		FormulaEvaluator formula = wb.getCreationHelper().createFormulaEvaluator();
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
    private void cboQuanHuyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboQuanHuyenActionPerformed
        // TODO add your handling code here:
        String t = cboQuanHuyen.getSelectedItem()+"";

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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmNhaXuatBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmNhaXuatBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmNhaXuatBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmNhaXuatBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmNhaXuatBan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cboPhuongXa;
    private javax.swing.JComboBox<String> cboQuanHuyen;
    private javax.swing.JComboBox<String> cboTinhThanhPho;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblMaNhaCungCap;
    private javax.swing.JLabel lblMaNhaCungCapp1;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblTenNhaXuatBan;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenNhaXuatBan;
    // End of variables declaration//GEN-END:variables
}
