/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Them;


import DAO.LoaiVanPhongPham_DAO;
import DAO_IMP.LoaiVanPhongPhamDAO_IMP;
import entity.LoaiVanPhongPham;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author FPTSHOP
 */
public class frmThemLoaiVanPhongPham extends javax.swing.JFrame {

    private LoaiVanPhongPham_DAO loaiVanPhongPham_DAO;

    /**
     * Creates new form frmThemLoaiVanPhongPham
     */
    public frmThemLoaiVanPhongPham() {
        
        initComponents();
        loaiVanPhongPham_DAO = new LoaiVanPhongPhamDAO_IMP();
       
                lblMaLoaiVanPhongPham1.setText( loaiVanPhongPham_DAO.generatMaLoaiVanPhongPham());
          
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
        lblMaLoaiVanPhongPham = new javax.swing.JLabel();
        lblMaLoaiVanPhongPham1 = new javax.swing.JLabel();
        lblLoaiVanPhongPham = new javax.swing.JLabel();
        txtTenLoaiVanPhongPham = new javax.swing.JTextField();
        lblMauSac = new javax.swing.JLabel();
        lblKichThuoc = new javax.swing.JLabel();
        txtMauSac = new javax.swing.JTextField();
        txtKichThuoc = new javax.swing.JTextField();
        lblChucNang = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaChucNang = new javax.swing.JTextArea();
        btnLamMoi = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        lblTieuDe1 = new javax.swing.JLabel();
        lblTieuDe = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblMaLoaiVanPhongPham.setText("Mã loại văn phòng phẩm");

        lblLoaiVanPhongPham.setText("Tên loại văn phòng phẩm");

        lblMauSac.setText("Màu sắc");

        lblKichThuoc.setText("Kích thước");

        lblChucNang.setText("Chức năng");

        jtaChucNang.setColumns(20);
        jtaChucNang.setRows(5);
        jScrollPane1.setViewportView(jtaChucNang);

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm loại văn phòng phẩm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        lblTieuDe1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTieuDe1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe1.setText("Thêm nhà xuất bản");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblMauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMaLoaiVanPhongPham)
                                .addComponent(lblLoaiVanPhongPham))
                            .addComponent(lblKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaLoaiVanPhongPham1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenLoaiVanPhongPham, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnLamMoi)
                        .addGap(62, 62, 62)
                        .addComponent(btnThem)))
                .addContainerGap(155, Short.MAX_VALUE))
            .addComponent(lblTieuDe1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblTieuDe1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaLoaiVanPhongPham1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaLoaiVanPhongPham))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLoaiVanPhongPham)
                    .addComponent(txtTenLoaiVanPhongPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMauSac)
                    .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKichThuoc)
                    .addComponent(txtKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblChucNang)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnLamMoi))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        lblTieuDe.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe.setText("Thêm nhà xuất bản");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(228, 228, 228)
                    .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(228, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        lamMoiDuLieu();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        
        String maLoaiVanPhongPham = lblMaLoaiVanPhongPham1.getText();
        String tenLoaiVanPhongPham = txtTenLoaiVanPhongPham.getText();
        String mauSac = txtMauSac.getText();
        String chucNang= jtaChucNang.getText();
        String kichThuoc = txtKichThuoc.getText();
        
        LoaiVanPhongPham loaiVanPhongPham = new LoaiVanPhongPham(maLoaiVanPhongPham, tenLoaiVanPhongPham);
        
        loaiVanPhongPham_DAO = new LoaiVanPhongPhamDAO_IMP();
        
        
         if(loaiVanPhongPham_DAO.InsertLoaiVanPhongPham(loaiVanPhongPham)){
             lamMoiDuLieu();
          
                lblMaLoaiVanPhongPham1.setText( loaiVanPhongPham_DAO.generatMaLoaiVanPhongPham());
           
           
            JOptionPane.showMessageDialog(this, "Thêm thành công loại sản phẩm");
        }
        
        
        
    }//GEN-LAST:event_btnThemActionPerformed
    private void lamMoiDuLieu(){
        txtKichThuoc.setText("");
        txtMauSac.setText("");
        txtTenLoaiVanPhongPham.setText("");
        jtaChucNang.setText("");
    }
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jtaChucNang;
    private javax.swing.JLabel lblChucNang;
    private javax.swing.JLabel lblKichThuoc;
    private javax.swing.JLabel lblLoaiVanPhongPham;
    private javax.swing.JLabel lblMaLoaiVanPhongPham;
    private javax.swing.JLabel lblMaLoaiVanPhongPham1;
    private javax.swing.JLabel lblMauSac;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTieuDe1;
    private javax.swing.JTextField txtKichThuoc;
    private javax.swing.JTextField txtMauSac;
    private javax.swing.JTextField txtTenLoaiVanPhongPham;
    // End of variables declaration//GEN-END:variables
}
