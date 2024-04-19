
package Pannel;

import Entity.TaiKhoan;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.imageio.ImageIO;
/**
 *
 * @author ThaiHiep
 */
public class pnlTrangChu extends javax.swing.JPanel {

    
    /**
     * Creates new form TrangChu
     */
    private TaiKhoan tk;
    private Timer timer;
    private long startTime;
    private long elapsedTime;
    private  JLabel timJLabel ;
    private File selectedFile;
    public pnlTrangChu(TaiKhoan tk, JLabel timJLabel) {
        this.tk= tk;
        this.timJLabel = timJLabel;
        initComponents();
        System.out.println("x"+timJLabel.getText());
        init();
        
        try {
            selectedFile =new File("src\\IMG\\anhTrangChu.png");
            BufferedImage image = ImageIO.read(selectedFile); // Thay đổi đường dẫn đến ảnh
            
            // thay đổi kích thức ảnh cùng kích thước với lable 184x216
             Image scaledImage = image.getScaledInstance(1300, 800,Image.SCALE_SMOOTH);

                    // Tạo ImageIcon với ảnh đã điều chỉnh kích thước
                    ImageIcon imageIcon = new ImageIcon(scaledImage);

                    // Thiết lập ImageIcon cho JLabel
                    jLabel5.setIcon(imageIcon);
           
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    public void init(){
        System.out.println("X2 : "+timJLabel.getText());
        String timeString = timJLabel.getText(); // Thời gian 01:00:00
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        int thoiGianLam = 0;
        try {
            Date parsedTime = format.parse(timeString); // Chuyển đổi chuỗi thời gian thành đối tượng Date
            int hours = parsedTime.getHours(); // Lấy giờ
            int minutes = parsedTime.getMinutes(); // Lấy phút
            int seconds = parsedTime.getSeconds(); // Lấy giây

            System.out.println("Giờ: " + hours);
            System.out.println("Phút: " + minutes);
            System.out.println("Giây: " + seconds);
            thoiGianLam = hours * 60 * 60 * 1000 + minutes * 60 * 1000 + seconds * 1000;
            System.out.println(thoiGianLam);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        startTime = System.currentTimeMillis() - thoiGianLam; // 3600000 milliseconds = 1 giờ

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long currentTime = System.currentTimeMillis();
                elapsedTime = currentTime - startTime;
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                format.setTimeZone(TimeZone.getTimeZone("UTC"));
                String timeText = format.format(new Date(elapsedTime));
                lblThoiGianLam.setText("Thời gian: " + timeText);
            }
        });

        timer.start();


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
        lblAnh = new javax.swing.JLabel();
        lblThoiGianLam = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        lblAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblThoiGianLam.setText("Thời gian đã làm : ");

        jLabel2.setText("Chức vụ                :");

        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1210, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblThoiGianLam;
    // End of variables declaration//GEN-END:variables
}