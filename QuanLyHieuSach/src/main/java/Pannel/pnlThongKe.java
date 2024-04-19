
package Pannel;

import Entity.NhanVien;
import Entity.TaiKhoan;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class pnlThongKe extends javax.swing.JPanel {

    private TaiKhoan tk;
    private NhanVien nv;
    
    public pnlThongKe(TaiKhoan tk) {
        this.tk = tk;
        this.nv = nv;
        initComponents();
        
        btnNgay.setBackground(Color.red);
        pnlSouth.add(new Pannel.pnlThongKeTheoNgay());
        pnlSouth.repaint();
        pnlSouth.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNgay = new javax.swing.JButton();
        btnThang = new javax.swing.JButton();
        pnlSouth = new javax.swing.JPanel();
        btnNam = new javax.swing.JButton();

        btnNgay.setText("Ngày");
        btnNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayActionPerformed(evt);
            }
        });

        btnThang.setText("Tháng");
        btnThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThangActionPerformed(evt);
            }
        });

        pnlSouth.setLayout(new java.awt.BorderLayout());

        btnNam.setText("Năm");
        btnNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSouth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnNgay)
                .addGap(18, 18, 18)
                .addComponent(btnThang)
                .addGap(18, 18, 18)
                .addComponent(btnNam)
                .addContainerGap(131, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNgay)
                    .addComponent(btnThang)
                    .addComponent(btnNam))
                .addGap(18, 18, 18)
                .addComponent(pnlSouth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayActionPerformed
        // TODO add your handling code here:
        pnlSouth.removeAll();
        btnNgay.setBackground(Color.red);
        btnThang.setBackground(Color.white);
        btnNam.setBackground(Color.white);
        pnlSouth.add(new Pannel.pnlThongKeTheoNgay());
        System.out.println(tk.getTenTK());
        pnlSouth.repaint();
        pnlSouth.revalidate();
    }//GEN-LAST:event_btnNgayActionPerformed

    private void btnThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThangActionPerformed
        // TODO add your handling code here:
        pnlSouth.removeAll();
        btnNgay.setBackground(Color.white);
        btnThang.setBackground(Color.red);
        btnNam.setBackground(Color.white);
        pnlSouth.add(new Pannel.pnlThongKeTheoThang());
        System.out.println(tk.getTenTK());
        pnlSouth.repaint();
        pnlSouth.revalidate();
    }//GEN-LAST:event_btnThangActionPerformed

    private void btnNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNamActionPerformed
        // TODO add your handling code here:
        pnlSouth.removeAll();
        btnNgay.setBackground(Color.white);
        btnNam.setBackground(Color.red);
        btnThang.setBackground(Color.white);
        pnlSouth.add(new Pannel.pnlThongKeTheoNam());
        System.out.println(tk.getTenTK());
        pnlSouth.repaint();
        pnlSouth.revalidate();
    }//GEN-LAST:event_btnNamActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNam;
    private javax.swing.JButton btnNgay;
    private javax.swing.JButton btnThang;
    private javax.swing.JPanel pnlSouth;
    // End of variables declaration//GEN-END:variables
}