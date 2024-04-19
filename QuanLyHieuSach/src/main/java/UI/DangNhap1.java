
package UI;

import ConnectDB.ConnectDB;
import DAO.NhanVien_DAO;
import DAO.TaiKhoan_DAO;
import Entity.NhanVien;
import Entity.TaiKhoan;
import java.awt.Component;
import java.awt.Cursor;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

/**
 *
 * @author FPTSHOP
 */
public class DangNhap1 extends javax.swing.JFrame {

    private TaiKhoan_DAO taiKhoan_DAO;
    private NhanVien_DAO nhanVien_DAO;
    private NhanVien nv;
    private TaiKhoan tk;
    private final LookAndFeel originalLookAndFeel;

    
    /**
     * Creates new form Login
     */
    public DangNhap1() {
        
        initComponents();
        originalLookAndFeel = UIManager.getLookAndFeel();
        init();
        btnDangNhap.setEnabled(false);
    }
    public void init(){
        try {
            ConnectDB.getInstance().connect();
	}catch (SQLException e) {
	// TODO: handle exception
            e.printStackTrace();
	}
       
        
        phimTatChoNutDangNhap();
        DulieuTenDangNhap();
        DuLieuMatKhau();
        
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblQuenMatKhau = new javax.swing.JLabel();
        btnDangNhap = new javax.swing.JButton();
        txtMatKhau = new ServiceUser.JTextFielMatKhau();
        txtTenDangNhap = new ServiceUser.TextFieldTenDangNhap();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        lblQuenMatKhau.setText("<html><u>Quên mật khẩu?</u></html>");
        lblQuenMatKhau.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblQuenMatKhauMouseMoved(evt);
            }
        });
        lblQuenMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblQuenMatKhauMousePressed(evt);
            }
        });

        btnDangNhap.setText("Đăng nhập");
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });

        txtMatKhau.setLabelText("MẬT KHẨU");

        txtTenDangNhap.setLabelText("TÊN ĐĂNG NHẬP");
        txtTenDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDangNhapActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Đăng nhập");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblQuenMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(btnDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDangNhap)
                .addGap(18, 18, 18)
                .addComponent(lblQuenMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        // TODO add your handling code here:
        if (evt.getActionCommand().equals("ActionPerformed")) {

        }
        DangNhap();
    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void lblQuenMatKhauMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuenMatKhauMousePressed
        // TODO add your handling code here:
        QuenMatKhauEmail Qmke = new QuenMatKhauEmail();
        this.setVisible(false);
        Qmke.setVisible(true);
    }//GEN-LAST:event_lblQuenMatKhauMousePressed

    private void lblQuenMatKhauMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuenMatKhauMouseMoved
        // TODO add your handling code here:
        lblQuenMatKhau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblQuenMatKhauMouseMoved

    private void txtTenDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDangNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDangNhapActionPerformed
    private void DangNhap(){
        
        String tenDangNhap = txtTenDangNhap.getText();
        String matKhau = txtMatKhau.getText();
        if(tenDangNhap.equals("")){
            
        }
        
        tk = new TaiKhoan(tenDangNhap, matKhau);
        nv  = new NhanVien();
    	taiKhoan_DAO = new TaiKhoan_DAO();
        nhanVien_DAO = new NhanVien_DAO();
        
        if(taiKhoan_DAO.login(tk)){
            System.out.println(tk.getTenTK());
            System.out.println(tk.getMatKhau());
            nv = nhanVien_DAO.layThongTinNhanVien(tk);
            try {
                taiKhoan_DAO.updataTinhTrangDangNhap(tk.getTenTK(), "Đang đăng nhập");
            } catch (SQLException ex) {
                Logger.getLogger(DangNhap1.class.getName()).log(Level.SEVERE, null, ex);
            }
           if(nv.getChucVu().getMaChucVu().equals("QL")||nv.getChucVu().getMaChucVu().equals("NV")){
                System.out.println(nv);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String ngaySinhFormatted =nv.getNgaySinh().format(formatter);
                System.out.println("Ngày Sinh : "+ngaySinhFormatted);
                (new TrangChu(tk,nv,originalLookAndFeel)).setVisible(true);
                this.dispose();
           }
        }else if(taiKhoan_DAO.login_DaDangNhap(tk)){
             JOptionPane.showMessageDialog(null ,"Tài khoản đang được sử dụng");
        }
        else{
            JOptionPane.showMessageDialog(null ,"Tên Đăng nhập hoặc mật khẩu sai");
           
        }
    }
    
    private void phimTatChoNutDangNhap(){
        // Tạo phím tắt "Enter" cho JButton
        javax.swing.KeyStroke enterKeyStroke =  javax.swing.KeyStroke.getKeyStroke( java.awt.event.KeyEvent.VK_ENTER, 0);
        
        // Tạo hành động khi phím tắt được nhấn
            javax.swing.Action enterAction = new  javax.swing.AbstractAction("Đăng nhập") {
            
            @Override
            public void actionPerformed( java.awt.event.ActionEvent e) {
                DangNhap();
            }
        };
        
        // Ánh xạ phím tắt "Enter" với hành động
        InputMap inputMap = btnDangNhap.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = btnDangNhap.getActionMap();
        
        inputMap.put(enterKeyStroke, "enterClick");
        actionMap.put("enterClick", enterAction);  
    }
    
    private void DulieuTenDangNhap(){
        txtTenDangNhap.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                if( !txtTenDangNhap.getText().equals("") && !txtMatKhau.getText().equals("")){
                    
                         btnDangNhap.setEnabled(true);
                    
                    
                }else{
                     btnDangNhap.setEnabled(false);
                }
            }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                if( !txtTenDangNhap.getText().equals("") && !txtMatKhau.getText().equals("")){
                   btnDangNhap.setEnabled(true);
                    
                }else{
                     btnDangNhap.setEnabled(false);
                }
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                if( !txtTenDangNhap.getText().equals("")){
                   btnDangNhap.setEnabled(true);
                    
                }else{
                     btnDangNhap.setEnabled(false);
                }
            }
        });
    }
    
    private void DuLieuMatKhau(){
        txtMatKhau.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                if(!txtTenDangNhap.getText().equals("") && !txtMatKhau.getText().equals("") ){
                   btnDangNhap.setEnabled(true);
                    
                }else{
                     btnDangNhap.setEnabled(false);
                }
            }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                if( !txtTenDangNhap.getText().equals("") && !txtMatKhau.getText().equals("")){
                   btnDangNhap.setEnabled(true);
                    
                }else{
                     btnDangNhap.setEnabled(false);
                }
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                if( !txtMatKhau.getText().equals("")){
                   btnDangNhap.setEnabled(true);
                    
                }else{
                     btnDangNhap.setEnabled(false);
                }
            }
        });
    }
    
    
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
            java.util.logging.Logger.getLogger(DangNhap1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhap1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhap1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhap1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhap1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblQuenMatKhau;
    private ServiceUser.JTextFielMatKhau txtMatKhau;
    private ServiceUser.TextFieldTenDangNhap txtTenDangNhap;
    // End of variables declaration//GEN-END:variables
}
