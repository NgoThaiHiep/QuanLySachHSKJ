
package UI;


import DAO.NhanVien_DAO;
import DAO.TaiKhoan_DAO;
import DAO.ThoiGianHoatDong_DAO;
import DAO_IMP.TaiKhoanDAO_IMP;
import DAO_IMP.ThoiGianHoatDongDAO_IMP;
import entity.NhanVien;
import entity.TaiKhoan;
import entity.ThoiGianHoatDong;
import Menu.MenuItem;
import Pannel.pnlTraCuuNhanVien;
import Pannel.pnlDanhSachHoaDon;
import Pannel.pnlKhuyenMai;
import Pannel.pnlLapHoaDon11;
import Pannel.pnlQuyDinh;
import Pannel.pnlTaiKhoan;
import Pannel.pnlThemKhachHang;
import Pannel.pnlThemNhanVien;
import Pannel.pnlThemSanPham;
import Pannel.pnlThongKe;
import Pannel.pnlTraCuuKhachHang;
import Pannel.pnlTraCuuSanPham;
import Pannel.pnlTrangChu;
import Pannel.pnlTroGiup;
import ServiceUser.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LookAndFeel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author FPTSHOP
 */
public class TrangChu extends javax.swing.JFrame {

    /**
     * Creates new form TrangChu
     */
    private TaiKhoan tk ;
    private NhanVien nv;
    private NhanVien_DAO nhanVien_DAO;
    private ThoiGianHoatDong_DAO thoiGianHoatDong_DAO;
    private long startTime;
    private ThoiGianHoatDong tghd;
    private JLabel lblThoiGianDaLam;
    private LocalTime thoiGianDangNhap;
    private TaiKhoan_DAO taiKhoan_DAO;
    private MenuItem menuTrangChu,menuSanPham,menuThemSanPham,menuTraCuuSanPham,menuKhachHang ,menuTraCuuKhachHang, menuThemKhachHang
                    ,menuHoaDon,menuLapHoaDon, menuDanhSachHoaDon
                    ,menuNhanVien ,menuTraCuuNhanVien, menuThemNhanVien
                   ,menuDoanhThu,menuThongKe, menuBaoCao 
                   ,menuTaiKhoan, menuQuyDinh,menuTroGiup,menuKhuyenMai;
    private LookAndFeel originalLookAndFeel;
//    private final pnlTrangChu pnlTrangChu;
//    private final pnlTraCuuSanPham pnlTraCuuSanPham;
//    private final pnlThemSanPham pnlThemSanPham;
//    private final pnlCapNhatSanPham pnlCapNhatSanPham;
//    private final pnlLapHoaDon pnlLapHoaDon;
//    private final pnlDanhSachHoaDon pnlDanhSachHoaDon;
//    private pnlTraCuuKhachHang pnlTraCuuKhachHang = null;
//    private final pnlThemKhachHang pnlThemKhachHang;
//    private final pnlTraCuuNhanVien pnlTraCuuNhanVien;
//    private pnlThemNhanVien pnlThemNhanVien = null;
//    private pnlCapNhatNhanVien pnlCapNhatNhanVien = null;
//    private final pnlThongKe pnlThongKe;
//    private final pnlTaiKhoan pnlTaiKhoan;
//    private final CardLayout cardLayout;
//    private final JPanel mainPanel;



    public TrangChu(TaiKhoan tk,NhanVien nv,LookAndFeel originalLookAndFeel) {     
        super("Trang chủ");
        this.tk = tk;
        this.nv = nv;
        this.originalLookAndFeel = originalLookAndFeel;
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        lblThoiGianDaLam = new JLabel();
        setTitle("");
        try {
            UIManager.setLookAndFeel(originalLookAndFeel);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        thoiGianLamViec();
        thoiGianHeader();
        gioDaLam();
        chonMenu();
        
//        pnlTrangChu = new pnlTrangChu(tk, lblThoiGianDaLam);
//        pnlTraCuuSanPham = new pnlTraCuuSanPham(tk);
//        pnlThemSanPham = new pnlThemSanPham(tk);
//        pnlCapNhatSanPham = new pnlCapNhatSanPham(tk);
//        pnlLapHoaDon = new pnlLapHoaDon(tk, nv,originalLookAndFeel);
//        pnlDanhSachHoaDon = new pnlDanhSachHoaDon(tk, nv);
//        try {
//            pnlTraCuuKhachHang = new pnlTraCuuKhachHang(tk);
//        } catch (IOException ex) {
//            Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        pnlThemKhachHang = new pnlThemKhachHang(tk);
//        pnlTraCuuNhanVien = new pnlTraCuuNhanVien(tk);
//        try {
//            pnlThemNhanVien = new pnlThemNhanVien(tk);
//        } catch (IOException ex) {
//            Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            pnlCapNhatNhanVien = new pnlCapNhatNhanVien(tk);
//        } catch (IOException ex) {
//            Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        pnlThongKe = new pnlThongKe(tk);
//        pnlTaiKhoan = new pnlTaiKhoan(tk, nv);
//       
//        cardLayout = new CardLayout();
//        mainPanel = new JPanel(cardLayout);
//        mainPanel.add(pnlTrangChu,"Trang chủ");
//        mainPanel.add(pnlTraCuuSanPham,"Tra cứu sản phẩm");
//        mainPanel.add(pnlThemSanPham,"Thêm sản phẩm");
//        mainPanel.add(pnlCapNhatSanPham,"Cập nhật sản phẩm");
//        mainPanel.add(pnlLapHoaDon,"Lập hóa đơn");
//        mainPanel.add(pnlDanhSachHoaDon,"Danh sách hóa đơn");
//        mainPanel.add(pnlTraCuuKhachHang,"Tra cứu khách hàng");
//        mainPanel.add(pnlThemKhachHang,"Thêm khách hàng");
//        mainPanel.add(pnlThongKe,"Thống kê");
//        mainPanel.add(pnlTaiKhoan,"Tài khoản");
//        mainPanel.add(pnlTraCuuNhanVien,"Tra cứu nhân viên");
//        mainPanel.add(pnlThemNhanVien,"Thêm nhân viên");
//        mainPanel.add(pnlCapNhatNhanVien,"Cập nhật nhân viên");
//       // mainPanel.add(pnlTrangChu,"Trang chủ");

       
    }
    private void thoiGianLamViec(){
        thoiGianHoatDong_DAO = new ThoiGianHoatDongDAO_IMP();
        
        //Lấy thời gian hiện tại 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime loCalTime = LocalTime.now();
        String catChuoi = loCalTime+"";
        catChuoi = catChuoi.substring(0, 8); 
        thoiGianDangNhap = LocalTime.parse(catChuoi, formatter);
        
        // lấy ngày hiện tại 
        LocalDate localDate = LocalDate.now();
        //Khai báo entity
        tghd = new ThoiGianHoatDong(null, nv, localDate, null, null);
        //nếu tồn tại thời gian lấy ra  không thì set thời thời gian làm ngày đó là 00:00:00
        if(nv.getChucVu().getMaChucVu().equals("QL")){
            LocalTime startTimeLamViec = LocalTime.of(0, 0, 0);
            LocalTime endTime = LocalTime.of(23, 59, 59);
            thoiGianThietLap(localDate, loCalTime, formatter,startTimeLamViec,endTime);
        }else if(nv.getChucVu().getMaChucVu().equals("NV")){
            //thời gian hiện tại
            LocalTime now = LocalTime.now();
            //thời gian ca 1
            LocalTime startTimeCa1 = LocalTime.of(6, 0, 0);
            LocalTime endTimeCa1 = LocalTime.of(13, 59, 59);
            //thời gian ca 2
            LocalTime endTimeCa2 = LocalTime.of(14, 0, 0);
            LocalTime startTimeCa2 = LocalTime.of(21, 59, 59);
            //thời gian tăng ca
            LocalTime endTimeCa3 = LocalTime.of(22, 0, 0);
            LocalTime startTimeCa3 = LocalTime.of(5, 59, 59);
            
            if (isBetween(now, startTimeCa1, endTimeCa1)){
                thoiGianThietLap(localDate, loCalTime, formatter,startTimeCa1,endTimeCa1);
            }else if(isBetween(now, startTimeCa2, endTimeCa2)){
                thoiGianThietLap(localDate, loCalTime, formatter,startTimeCa2,endTimeCa2);
            }else{
                thoiGianThietLap(localDate, loCalTime, formatter,startTimeCa3,endTimeCa3);
                System.out.println("3");
            }
                     
            
        }
        
    }
    public void thoiGianThietLap(LocalDate localDate,LocalTime loCalTime,DateTimeFormatter formatter,LocalTime startTimeCa1,LocalTime endTimeCa1  ){
            if(thoiGianHoatDong_DAO.kiemTraDangNhapTrongNgay(tghd,startTimeCa1,endTimeCa1)){
                lblThoiGianDaLam.setText(tghd.getThoiGianDaLam()+"");
            }else{
                //mã thời gian 221003 0600 232108 (22103 ngày tháng) (0600 thời gian đăng nhập 06:00 (hh:mm)) (232108 mã nhân viên)
                LocalDate localDateNam = LocalDate.now();
                String formattedTime = loCalTime.format(formatter);
               
               
                Date currentDate = new Date();
                SimpleDateFormat formatterDay = new SimpleDateFormat("ddMMyy");
                String formattedDate = formatterDay.format(currentDate);
        
                String maLamViec = formattedDate+ formattedTime.substring(0,2) +formattedTime.substring(3,5) + nv.getMaNV();
                
                tghd = new ThoiGianHoatDong(maLamViec, nv, localDateNam, thoiGianDangNhap);
                
                thoiGianHoatDong_DAO.insertThoiGianLam(tghd);
                
                lblThoiGianDaLam.setText("00:00:00");
        }
    }
    public static boolean isBetween(LocalTime time, LocalTime startTime, LocalTime endTime) {
        return !time.isBefore(startTime) && !time.isAfter(endTime);
    }
    private void thoiGianHeader(){
        lblChucVu.setText(nv.getChucVu().getMaChucVu()+" :");
        lblTenNhanVien.setText(nv.getHoTenNhanVien());
        System.out.println("Họ tên nhân viên : "+nv.getHoTenNhanVien());
        System.out.println("Chức vụ "+nv.getChucVu().getMaChucVu());
        
        pnlBody.add(new Pannel.pnlTrangChu(tk,lblThoiGianDaLam));
        pnlBody.repaint();
        pnlBody.revalidate();
        
       
        // Đặt ngôn ngữ và khu vực sang tiếng Việt
	        Locale vietnameseLocale = new Locale("vi", "VN");
	        // Sử dụng ngôn ngữ và khu vực đã đặt để định dạng lại thứ
                Date currentDate = new Date();
	        SimpleDateFormat formatterDayVN = new SimpleDateFormat("EEEE", vietnameseLocale);
	        String dayOfWeekVN = formatterDayVN.format(currentDate);
                
         lblThu.setText(dayOfWeekVN);
         
         //Ngày tháng năm hiện tại
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-yy");
	String formattedDate = formatter.format(currentDate);
        
        
        // Tạo một đối tượng Timer với khoảng thời gian 1000ms (1 giây)
        Timer timer = new Timer(1000, (ActionEvent e) -> {
            // Lấy thời gian hiện tại
            LocalDateTime now = LocalDateTime.now();
            
            // Định dạng thời gian
            DateTimeFormatter formatterSecond = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = now.format(formatterSecond);
            
            // Hiển thị thời gian trên label
            lblNgayThangNam.setText(formattedDate +" "+ formattedTime);
        });

        // Bắt đầu đồng hồ
        timer.start();
        
        pnlHeader.repaint();
        pnlHeader.revalidate();   
        

        
        
    }
    private  void gioDaLam(){
        //String timeString = "01:00:00"; // Thời gian 01:00:00
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        
        int thoiGianLam = 0;
        try {
            Date parsedTime = format.parse(lblThoiGianDaLam.getText()); // Chuyển đổi chuỗi thời gian thành đối tượng Date
            int hours = parsedTime.getHours(); // Lấy giờ
            int minutes = parsedTime.getMinutes(); // Lấy phút
            int seconds = parsedTime.getSeconds(); // Lấy giây

            thoiGianLam = hours * 60 * 60 * 1000 + minutes * 60 * 1000 + seconds * 1000;
        } catch (ParseException ex) {
        }
        
        startTime = System.currentTimeMillis() - thoiGianLam; // 3600000 milliseconds = 1 giờ
        Timer timer = new Timer(1000, (ActionEvent e) -> {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - startTime;
            SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
            format1.setTimeZone(TimeZone.getTimeZone("UTC"));
            String timeText = format1.format(new Date(elapsedTime));
            lblThoiGianDaLam.setText( timeText);
        });

        timer.start();
    }
    private void chonMenu() {
        menu1.setEvent((int index, int subIndex) -> {
            if (index == 0) {
                showForm(new pnlTrangChu(tk,lblThoiGianDaLam));
            } else if (index == 1 && subIndex == 1) {
                showForm(new pnlTraCuuSanPham(tk));
            } else if (index == 1 && subIndex == 2) {
                showForm(new pnlThemSanPham(tk));
            } else if (index == 2 && subIndex == 1) {
                showForm(new pnlLapHoaDon11(tk,nv,originalLookAndFeel));
            } else if (index == 2 && subIndex == 2) {
                showForm(new pnlDanhSachHoaDon(tk,nv));
            } else if (index == 3 && subIndex == 1) {
                showForm(new pnlTraCuuKhachHang(tk));
            } else if (index == 3 && subIndex == 2) {
                try {
                    showForm(new pnlThemKhachHang(tk));
                } catch (SQLException ex) {
                    Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (index == 4 && subIndex == 1) {
                if ("NV".equals(lblChucVu)) {
                    JOptionPane.showMessageDialog(null, "Chỉ có Quản Lý mới có quyền sử dụng.");
                } else {
                    try {
                        showForm(new pnlTraCuuNhanVien(tk));
                    } catch (SQLException ex) {
                        Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (index == 4 && subIndex == 2) {
                if ("NV".equals(lblChucVu)) {
                    JOptionPane.showMessageDialog(null, "Chỉ có Quản Lý mới có quyền sử dụng.");
                } else {
                    try {
                        showForm(new pnlThemNhanVien(tk));
                    } catch (SQLException ex) {
                        Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }else if (index == 5 && subIndex == 1) { 
                
//                showForm(new pnlThongKe(tk));
                
            } else if (index == 5 && subIndex == 2) {
//                showForm(new pnlThongKe(tk));
            } else if (index == 6) {
                if ("NV".equals(lblChucVu)) {
                    JOptionPane.showMessageDialog(null, "Chỉ có Quản Lý mới có quyền sử dụng.");
                } else {
                    showForm(new pnlTaiKhoan(tk,nv));
                }
            } else if (index == 7) {
                if ("NV".equals(lblChucVu)) {
                    JOptionPane.showMessageDialog(null, "Chỉ có Quản Lý mới có quyền sử dụng.");
                } else {
                    showForm(new pnlQuyDinh());
                }
            } else if (index == 8) {
                if ("NV".equals(lblChucVu)) {
                    JOptionPane.showMessageDialog(null, "Chỉ có Quản Lý mới có quyền sử dụng.");
                } else {
                    showForm(new pnlKhuyenMai());
                }
            } else if (index == 9) {
                showForm(new pnlTroGiup());
            }
        });
    }

    public void showForm(Component com) {
        try { UIManager.setLookAndFeel(originalLookAndFeel);        
                } catch (UnsupportedLookAndFeelException ex) {            
                         
        }
        pnlBody.removeAll();
        pnlBody.add(com);
        pnlBody.repaint();
        pnlBody.revalidate();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblChucVu = new javax.swing.JLabel();
        lblTenNhanVien = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblThu = new javax.swing.JLabel();
        lblNgayThangNam = new javax.swing.JLabel();
        pnlMenu = new javax.swing.JPanel();
        scrollPaneWin111 = new Menu.ScrollPaneWin11();
        menu1 = new Menu.Menu();
        jPanel1 = new javax.swing.JPanel();
        btnDangXuat = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        pnlBody = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlHeader.setBackground(new java.awt.Color(41, 141, 98));
        pnlHeader.setPreferredSize(new java.awt.Dimension(802, 50));

        lblChucVu.setForeground(new java.awt.Color(255, 255, 255));
        lblChucVu.setText("Chức vụ");

        lblTenNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lblTenNhanVien.setText("Tên nhân viên");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("HIỆU SÁCH BLACKTEE");

        lblThu.setForeground(new java.awt.Color(255, 255, 255));
        lblThu.setText("Thứ");

        lblNgayThangNam.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayThangNam.setText("jLabel3");

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNgayThangNam, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenNhanVien)
                    .addComponent(lblChucVu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThu)
                    .addComponent(lblNgayThangNam))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnlHeader, java.awt.BorderLayout.PAGE_START);

        pnlMenu.setBackground(new java.awt.Color(21, 110, 71));
        pnlMenu.setPreferredSize(new java.awt.Dimension(250, 425));

        scrollPaneWin111.setBorder(null);
        scrollPaneWin111.setViewportView(menu1);

        jPanel1.setBackground(new java.awt.Color(43, 141, 98));
        jPanel1.setPreferredSize(new java.awt.Dimension(50, 50));

        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        jButton2.setText("Đổi mật khẩu");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDangXuat)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addGap(52, 52, 52))
            .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMenuLayout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
                .addGap(100, 100, 100))
            .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                    .addGap(0, 784, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(pnlMenu, java.awt.BorderLayout.LINE_START);

        pnlBody.setBackground(new java.awt.Color(246, 246, 246));
        pnlBody.setLayout(new java.awt.BorderLayout());
        getContentPane().add(pnlBody, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1163, 943));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    public void addTableStyle(JScrollPane scroll) {
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null); 
        scroll.setBorder(null);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel panel = new JPanel();
        panel.setBackground(new Color(60, 60, 60));
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
    }
    public void showThemKhachHang(){
        
     
     
       
   
             
 
       
    }
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        taiKhoan_DAO = new TaiKhoanDAO_IMP();
        thoiGianHoatDong_DAO = new ThoiGianHoatDongDAO_IMP();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        tghd.setThoiGianDaLam(LocalTime.parse( lblThoiGianDaLam.getText(), formatter));
       
        LocalTime localTime = LocalTime.now();
        String loCalTime =  localTime.format(formatter);
        
        tghd.setThoiGianDangXuat(LocalTime.parse(loCalTime, formatter));
        thoiGianHoatDong_DAO.capNhatThoiGianLam(tghd);
       
        try {
            taiKhoan_DAO.updataTinhTrangDangNhap(tk.getTenTK().getMaNV(), "Đã đăng xuất");
        } catch (Exception ex) {
            
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            UIManager.setLookAndFeel(originalLookAndFeel);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(pnlTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        DoiMatKhau doiMatKhau = new DoiMatKhau (tk);
        doiMatKhau.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        try {
            UIManager.setLookAndFeel(originalLookAndFeel);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(pnlTraCuuNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        int hoiDeDangXuat =   JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất không?","Thông báo",JOptionPane.YES_NO_OPTION);
        if(hoiDeDangXuat == JOptionPane.YES_OPTION){
            thoiGianHoatDong_DAO = new ThoiGianHoatDongDAO_IMP();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            tghd.setThoiGianDaLam(LocalTime.parse( lblThoiGianDaLam.getText(), formatter));
            LocalTime localTime = LocalTime.now();
            String loCalTime =  localTime.format(formatter);
            tghd.setThoiGianDangXuat(LocalTime.parse(loCalTime, formatter));
            thoiGianHoatDong_DAO.capNhatThoiGianLam(tghd);
            taiKhoan_DAO = new TaiKhoanDAO_IMP();
            
            	 taiKhoan_DAO.updataTinhTrangDangNhap(tk.getTenTK().getMaNV(), "Đã đăng xuất");
           
            DangNhap1 dangNhap = new DangNhap1 ();
            this.setVisible(false);
            dangNhap.setVisible(true);
        }
    }//GEN-LAST:event_btnDangXuatActionPerformed
    
    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblNgayThangNam;
    private javax.swing.JLabel lblTenNhanVien;
    private javax.swing.JLabel lblThu;
    private Menu.Menu menu1;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlMenu;
    private Menu.ScrollPaneWin11 scrollPaneWin111;
    // End of variables declaration//GEN-END:variables
}
