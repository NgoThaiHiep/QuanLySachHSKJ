
package Pannel;

import entity.TaiKhoan;
import java.sql.SQLException;
import java.util.ArrayList;
import DAO.KhachHang_DAO;
import DAO_IMP.KhachHangDAO_IMP;
import entity.KhachHang;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.Timer;
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
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author FPTSHOP
 */
public class pnlThemKhachHang extends javax.swing.JPanel {

    /**
     * Creates new form ThemKhachHang
     */
    private final TaiKhoan tk;
    private KhachHang_DAO khachHang_DAO;
    private String cboDiaChi = "";
      private String tp = "Tỉnh/Thành phố";
    private String q = "Quận/huyện";
    private ArrayList<String> cities ;
    private ArrayList<String> districts ;
    private ArrayList<String> wardsDistricts ;
    private final int diemTL = 0;
    public pnlThemKhachHang(TaiKhoan tk) throws IOException, SQLException {
        this.tk = tk;
        initComponents();
        init();
        
    }
    public final void init() throws IOException, SQLException{
              Timer timeXuatHienDiaChi = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    
                try {
                    cities = readExcel_City();
                        for (String city : cities) {
                            cboTinh.addItem(city);
                        }
                } catch (IOException ex) {
                    Logger.getLogger(pnlTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
        });
        
        timeXuatHienDiaChi.setRepeats(false); // Chỉ chạy một lần
        timeXuatHienDiaChi.start();
          txtMaKhachHang.setEnabled(false);
          khachHang_DAO = new KhachHangDAO_IMP();
          txtMaKhachHang.setText(khachHang_DAO.generateVerifyCodeKH());
          System.out.println("mã khách hàng"+khachHang_DAO.generateVerifyCodeKH());
          
            txtTenKhachHang.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = txtTenKhachHang.getText();
                String formattedText = vietHoaChuCaiDauTienTrongJtextField(text);
                txtTenKhachHang.setText(formattedText);
            }
        });
        
                // Tạo một DocumentFilter để kiểm tra và lọc ký tự
        AbstractDocument document = (AbstractDocument) txtTenKhachHang.getDocument();
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
        duLieuSDT();
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
		FormulaEvaluator formula = wb.getCreationHelper().createFormulaEvaluator();
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
	    		FileInputStream file = new FileInputStream("src\\main\\java\\Li\\Book1.xlsx");
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
			FileInputStream file = new FileInputStream("src\\main\\java\\Li\\Book1.xlsx");
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
    private void capNhatDanhSachKhachHang(String sdt){
        khachHang_DAO = new KhachHangDAO_IMP();
        KhachHang  khachHang = khachHang_DAO.layThongTinKhachHang(sdt);
      
        String colTieuDe1[] = new String[]{"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Địa chỉ"};
        DefaultTableModel model = new DefaultTableModel(colTieuDe1, 0);
           Object[] row;
      
              row = new Object[12];
            // GÁN GIÁ TRỊ
            row[0] =  khachHang.getMaKhachHang();
            row[1] =  khachHang.getTenKhachHang();
            row[2] =  khachHang.getSoDienThoai();
            row[3] =  khachHang.getDiaChi();
           model.addRow(row);
        
        jTable1.setModel(model);
    }   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaKhachHang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cboTinh = new javax.swing.JComboBox<>();
        cboQuan = new javax.swing.JComboBox<>();
        cboPhuong = new javax.swing.JComboBox<>();
        btnThemKhachHang = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thêm khách hàng");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng"));

        jLabel2.setText("Mã khách hàng");

        jLabel3.setText("Tên khách hàng");

        jLabel4.setText("Số điện thoại");

        jLabel6.setText("Địa chỉ");

        cboTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTinhActionPerformed(evt);
            }
        });

        cboQuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboQuanActionPerformed(evt);
            }
        });

        btnThemKhachHang.setText("Thêm khách hàng");
        btnThemKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKhachHangActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaKhachHang)
                                .addGap(2, 2, 2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtTenKhachHang))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoDienThoai)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(81, 81, 81)
                                        .addComponent(cboTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboQuan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboPhuong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(81, 81, 81))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(367, 367, 367)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(61, 61, 61)
                        .addComponent(btnThemKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(294, 294, 294)))
                .addGap(124, 124, 124))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboQuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPhuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoi)
                    .addComponent(btnThemKhachHang))
                .addGap(20, 20, 20))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Địa chỉ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        AbstractDocument document = (AbstractDocument) txtTenKhachHang.getDocument();
        document.setDocumentFilter(null);
        txtTenKhachHang.setText("");
    
    // Các dòng mã khác để làm mới các thành phần khác nếu cần thiết
    txtSoDienThoai.setText("");
    txtSoDienThoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
    cboTinh.setSelectedItem("Tỉnh/Thành phố");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKhachHangActionPerformed
        if(cboTinh.getSelectedItem().equals("Tỉnh/Thành phố") || cboQuan.getSelectedItem().equals("Quận/huyện") || cboPhuong.getSelectedItem().equals("Phường/xã") || txtMaKhachHang.getText().trim().isEmpty() || txtTenKhachHang.getText().trim().isEmpty() || txtSoDienThoai.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin trước khi thêm khách hàng.");
    } else {
        // Các biến không rỗng, thực hiện việc thêm khách hàng vào cơ sở dữ liệu
        String maKhachHang = txtMaKhachHang.getText();
        String tenKhachHangInput = txtTenKhachHang.getText().trim();
        String tenKhachHang = tenKhachHangInput.replaceAll("\\s+", " ");
        String soDienThoai = txtSoDienThoai.getText();
        String diaChi = cboTinh.getSelectedItem() + "-" + cboQuan.getSelectedItem() + "-" + cboPhuong.getSelectedItem();
        
        KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, soDienThoai, diaChi,diemTL);
        if(khachHang_DAO.InsertKhachHang(kh)) {
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
            capNhatDanhSachKhachHang(kh.getMaKhachHang());
        }else{
            JOptionPane.showMessageDialog(this, "Đã có dữ liệu của số điện thoại này");
        }
    }
    }//GEN-LAST:event_btnThemKhachHangActionPerformed

    private void cboTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTinhActionPerformed
        // TODO add your handling code here:
        if(!tp.equals(cboTinh.getSelectedItem()+"")||cboTinh.getSelectedItem().equals("Tỉnh/Thành phố")){
            q = cboTinh.getSelectedItem()+"";
            //System.out.println(provinceComboBox.getSelectedItem());
            if(cboTinh.getSelectedItem().equals("Tỉnh/Thành phố")){

                cboQuan.removeAllItems();
                cboQuan.addItem("Quận/huyện");
                cboPhuong.removeAllItems();
                cboPhuong.addItem("Phường/xã");
                cboQuan.setEnabled(false);
                cboPhuong.setEnabled(false);

                System.out.println("1223 "+"Tỉnh/Thành phố");
            }else{

                try {
                    districts = readExcel_districts(cboTinh,cboQuan,cboPhuong,tp);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                cboQuan.removeAllItems();
                cboQuan.addItem("Quận/huyện");
                for (String district : districts) {
                    cboQuan.addItem(district);
                }
                cboPhuong.setEnabled(true);
                try {
                    wardsDistricts = readExcel_wardsDistrict(cboQuan,cboPhuong);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                cboPhuong.removeAllItems();
                cboPhuong.addItem("Phường/xã");
                for (String wad : wardsDistricts) {
                    cboPhuong.addItem(wad);
                }
            }
        }
        tp = cboTinh.getSelectedItem()+"";
    }//GEN-LAST:event_cboTinhActionPerformed

    private void cboQuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboQuanActionPerformed
        // TODO add your handling code here:
       

        cboQuan.addItem(q);
        // System.out.println(districtComboBox.getSelectedItem()+"");
        if( q == "Tỉnh/Thành phố"){

            //wardComboBox.removeAllItems();
            //wardComboBox.addItem("Phường/xã");
            // wardComboBox.setEnabled(true);
            // updateWardComboBox();
            // q = districtComboBox.getSelectedItem()+"";
            cboPhuong.setEnabled(false);
            cboPhuong.removeAllItems();
            cboPhuong.addItem("Phường/xã");

        }else{
            cboPhuong.setEnabled(true);
            try {
                wardsDistricts = readExcel_wardsDistrict(cboQuan,cboPhuong);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(!cboDiaChi.equals(cboQuan.getSelectedItem())){
                cboPhuong.removeAllItems();
                cboPhuong.addItem("Phường/xã");
                for (String wad : wardsDistricts) {
                    cboPhuong.addItem(wad);
                }
            }
        }
        cboDiaChi = cboQuan.getSelectedItem()+"";
        cboQuan.removeItem(q);
    }//GEN-LAST:event_cboQuanActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThemKhachHang;
    private javax.swing.JComboBox<String> cboPhuong;
    private javax.swing.JComboBox<String> cboQuan;
    private javax.swing.JComboBox<String> cboTinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenKhachHang;
    // End of variables declaration//GEN-END:variables
}
