
package Them;

import DAO.HangCho_DAO;
import DAO.KhachHang_DAO;
import Entity.HangCho;
import Entity.KhachHang;
import Entity.NhanVien;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FPTSHOP
 */
public class frmHangCho1 extends javax.swing.JFrame {

    private KhachHang_DAO khachHang_DAO;
    private HangCho_DAO hangCho_DAO;

    /**
     * Creates new form frmHangCho
     */
    public frmHangCho1() {
        initComponents();
        capNhatDanhSachHangCho();
        capNhatDanhSachTimKiemTheoSoDienThoai(txtTimKiemSoDienThoai);
    }
    private  int count = 0;
    public void capNhatDanhSachHangCho(){
    String colTieuDe1[] = new String[]{"STT", "Mã Khách Hàng", "Tên khách hàng", "Số điện thoại"};
    DefaultTableModel model = new DefaultTableModel(colTieuDe1, 0);
    hangCho_DAO = new HangCho_DAO();
    khachHang_DAO = new KhachHang_DAO();
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
            KhachHang dsKhachHang = khachHang_DAO.layThongTinKhachHang_TheoMa(khachHang);
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
    hangCho_DAO = new HangCho_DAO();
    khachHang_DAO = new KhachHang_DAO();
    ArrayList<HangCho> dsHangCho = hangCho_DAO.layDanhSachHangCho();
    Object[] row;
    int count = 0;
    Set<String> maKhachHangSet = new HashSet<>(); // Sử dụng Set để lưu trữ giá trị mã khách hàng đã xuất hiện
    for (HangCho hangCho : dsHangCho) {
    row = new Object[12];
    String khachHang = hangCho.getKhachHang().getMaKhachHang();
        if (!maKhachHangSet.contains(khachHang)) { // Kiểm tra xem mã khách hàng đã xuất hiện chưa
        try {
            ArrayList<KhachHang> dsKhachHangSoDienThoai = khachHang_DAO.layDanhSachTheoMaSach_TheoSoDienThoai(sdt);
            for (KhachHang khachHang1 : dsKhachHangSoDienThoai) {
                if(khachHang1.getMaKhachHang().equals(hangCho.getKhachHang().getMaKhachHang())){
                    maKhachHangSet.add(khachHang); // Thêm mã khách hàng vào Set
                    count++;
                    row[0] = count;
                    row[1] = khachHang;
                    row[2] = khachHang1.getTenKhachHang();
                    row[3] = khachHang1.getSoDienThoai();
                    model.addRow(row);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmHangCho1.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    tblHangCho.setModel(model);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblHangCho = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHangCho = new javax.swing.JTable();
        btnXoaTatCa = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        pnlTimKiemKhachHang = new javax.swing.JPanel();
        lblTimKiemSoDienThoai = new javax.swing.JLabel();
        txtTimKiemSoDienThoai = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblHangCho.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblHangCho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHangCho.setText("Hàng chờ");

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

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

        btnXoaTatCa.setText("Xóa tất cả");

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
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
                        .addComponent(btnXoaTatCa)
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
                    .addComponent(btnXoaTatCa)
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
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int rowToRemove = tblHangCho.getSelectedRow();
        if (rowToRemove != -1) {
            DefaultTableModel model = (DefaultTableModel) tblHangCho.getModel();
            model.removeRow(rowToRemove);

            // Cập nhật lại STT sau khi xóa hàng
            for (int i = rowToRemove; i < model.getRowCount(); i++) {
                model.setValueAt(i + 1, i, 0); // Cột STT ở đây là cột đầu tiên (0)
            }
            count--;
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnThanhToanActionPerformed
    
    public void capNhatDanhSachTimKiemTheoSoDienThoai(JTextField txt){
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaTatCa;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHangCho;
    private javax.swing.JLabel lblTimKiemSoDienThoai;
    private javax.swing.JPanel pnlTimKiemKhachHang;
    private javax.swing.JTable tblHangCho;
    private javax.swing.JTextField txtTimKiemSoDienThoai;
    // End of variables declaration//GEN-END:variables
}
