package test;


import java.util.Date;
import java.util.Scanner;

import org.checkerframework.checker.units.qual.s;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import DAO.ChiTietHoaDon_DAO;
import DAO.HangCho_DAO;
import DAO.HoaDon_DAO;
import DAO.KhachHang_DAO;
import DAO.MaXacNhan_DAO;
import DAO.NhanVien_DAO;
import DAO.Sach_DAO;
import DAO.SanPham_DAO;
import DAO.TacGia_DAO;
import DAO.TheLoai_DAO;
import DAO.ThoiGianHoatDong_DAO;

import DAO_IMP.ChiTietHoaDonDAO_IMP;
import DAO_IMP.ChucVuDAO_IMP;
import DAO_IMP.HangChoDAO_IMP;
import DAO_IMP.HoaDonDAO_IMP;
import DAO_IMP.KhachHangDAO_IMP;
import DAO_IMP.MaXacNhanDAO_IMP;
import DAO_IMP.NhanVienDAO_IMP;
import DAO_IMP.SachDAO_IMP;
import DAO_IMP.SanPhamDAO_IMP;
import DAO_IMP.TacGiaDAO_IMP;
import DAO_IMP.TaiKhoanDAO_IMP;
import DAO_IMP.TheLoaiDAO_IMP;
import DAO_IMP.ThoiGianHoatDongDAO_IMP;

import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiSanPham;
import entity.MaXacNhan;
import entity.NhanVien;
import entity.Sach;
import entity.SanPham;
import entity.TacGia;
import entity.TaiKhoan;
import entity.TheLoai;
import entity.ThoiGianHoatDong;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;



public class main {
	public static void main(String[] args) {
//		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyHieuSach MSSQL");
//		 EntityManager em = emf.createEntityManager();
		
//		TacGia_DAO  tacGia_DAO = new TacGiaDAO_IMP();
//		TacGia tg = new TacGia();
//		tg = tacGia_DAO.layThongTinTacGia("Tố Hữu");
//		System.out.println(tg);
//		Sach_DAO sach_DAO = new SachDAO_IMP();
//		Sach s = new Sach();
//		s = sach_DAO.layThongTinSach("Sach00001");
//		sach_DAO.insertGroupTacGia("Sach00001", tg.getMaTacGia());
		
//		TheLoai_DAO theLoai_DAO = new TheLoaiDAO_IMP();
//		TheLoai tl = new TheLoai();
//		tl = theLoai_DAO.layThongTinTheLoai("Bi kịch");
//		sach_DAO = new SachDAO_IMP();
//		s = new Sach();
//		s = sach_DAO.layThongTinSach("Sach00001");
//		sach_DAO.insertGroupTheLoai(s.getMaSanPham(), tl.getMaTheLoai());

		SanPham_DAO sanPham_DAO = new SanPhamDAO_IMP();
		sanPham_DAO.layDanhSachSanPham().forEach(System.out::println);
//		  
//		 chucVu_DAO_imp.getDSChucVu().forEach(System.out::println);
//		 

//		 
		 //NhanVien
		 
//		 NhanVien nv = new NhanVien();
//		 nv.setMaNV("232108");
//		 nv.setHoTenNhanVien("Nguyen Van A");
//		 
//		 
//		 

//		 nhanVienDAO_IMP.InsertNhanVien(nv);
//		 
//		 nhanVienDAO_IMP.layDanhSachNhanVien()
//		 .forEach(System.out::println);
//		 
//		 em.close();
//		 emf.close();
		 
		NhanVienDAO_IMP nhanVienDAO_IMP = new NhanVienDAO_IMP();
		ChucVuDAO_IMP chucVu_DAO_imp = new ChucVuDAO_IMP();
		
		Scanner  sc  = new  Scanner(System.in);
		int n = 1; 
		do {
			 System.out.println("Nhap lua chon: ");
			 n = sc.nextInt();
			 
			 switch (n) {
			case 1: 
				 
				int c  = 1;
			
				
				
				do {
					luaChon1();
					System.out.print("Nhap lua chon case 1: ");
					c = sc.nextInt();
					switch (c) {
					case 1 :
						String cellValue = "CV01";
					SanPham	sanPham = new SanPham() {
                     	   @Override
								public String getMaSanPham() {
									return (String) cellValue;
								}
                        };
                        System.out.println(sanPham.getMaSanPham());
						break;
					case 2:
						 chucVu_DAO_imp.getDSChucVu().forEach(
								 chucVu1 ->
								 {
										if (chucVu1.getTenChucVu().equals("Giam doc")) {
											chucVu1.setTenChucVu("Quản lý");
											chucVu_DAO_imp.suaChucVu(chucVu1);	
										}
								 }
								
								 );
						break;
					case 3:
//						 chucVu_DAO_imp.xoaChucVu("CV01");
//						 chucVu_DAO_imp.getDSChucVu().forEach(System.out::println);
						break;
					
					
					case 0:
						System.out.println("Thoat");
						break;
					
					}
					
				} while (c!=0);
				
//				
				break;
			case 2:
//				
				int c2 = 1;
				do {
					luaChon2();
					System.out.println("Nhap lua chon case 2: ");
					c2 = sc.nextInt();
					switch (c2) {
					case 1:
//						NhanVien(String maNV, String hoTenNhanVien, String CCCD, LocalDate ngaySinh, boolean gioiTinh, String email, String soDienThoai, ChucVu chucVu, TaiKhoan taiKhoan, CaLamViec caLam, String trangThai, String hinhAnh,String diaChi)
						NhanVien nv = new NhanVien("21081841", "Ngô Thái Hiệp", null, null, null, null, null, null, null, null, null,null);
						nhanVienDAO_IMP.InsertNhanVien(nv);
						break;
					case 2:
						 nhanVienDAO_IMP.layDanhSachNhanVien()
						 .forEach(System.out::println);//		 nhanVienDAO_IMP.layDanhSachNhanVien()
						
						break;
					case 3:
							System.out.println(nhanVienDAO_IMP.timKiemNhanVienTheoMaNV("21081841"));
						break;
					case 4:
						break;
					case 0:
						System.out.println("Thoat");
						break;
					}
					
				}while(c2!=0);
				break;
			case 3:
				int c3 = 1;
				do {
                    System.out.println("1. Them tai khoan");
                    System.out.println("2. Tim kiem tai khoa");
                    System.out.println("3. Xoa tai khoan");
                    System.out.println("4. In danh sach tai khoan");
                    System.out.println("5. Cap nhat mat khau");
                    System.out.println("6. Cap nhat mat khau mat");
                    System.out.println("7. Cap nhat trang thai tai khoan");
                    System.out.println("8. Đăng nhập");
                    System.out.println("0. Thoat");
                    c3 = sc.nextInt();
                    switch (c3) {
                    case 1:

                    	TaiKhoan tk = new TaiKhoan(new NhanVien("21081841"),"123456","Đang hoạt động");
                    	TaiKhoanDAO_IMP taiKhoanDAO_IMP = new TaiKhoanDAO_IMP();
                    	taiKhoanDAO_IMP.inserTaiKhoan(tk);
                        
                        break;
                    case 2:
                    	TaiKhoanDAO_IMP taiKhoanDAO_IMP2 = new TaiKhoanDAO_IMP();
                    	
                    	System.out.print("Nhap ten tai khoan: ");
                    	
                    	sc = new Scanner(System.in);
                    	String tenTK  =  sc.nextLine();
                    	
                    	System.out.println(taiKhoanDAO_IMP2.layThongTinTaiKhoan(tenTK));
                        break;
                    case 5:
                    	taiKhoanDAO_IMP2 = new TaiKhoanDAO_IMP();
                    	System.out.println("Nhap ten tai khoan: ");
                    	sc = new Scanner(System.in);
                    	tenTK  =  sc.nextLine();
                    	System.out.println("tên tài khoản: "+tenTK.trim());
                    	String mk = "123456";
                    	String mkm = "111111";
                    	
                    	TaiKhoan tk3 =  taiKhoanDAO_IMP2.layThongTinTaiKhoan(tenTK);
                    	System.out.println(tk3);
                    	if(tk3.getMatKhau().equals(mk)) {
                    		System.out.println("done");
                    		NhanVien nv =  nhanVienDAO_IMP.layThongTinNhanVien(new NhanVien(tenTK));
                    		taiKhoanDAO_IMP2.upDateMatKhau(nv, mk, mkm);
                    	}
                        break;
                    case 4:
                        taiKhoanDAO_IMP2 = new TaiKhoanDAO_IMP();
                    	taiKhoanDAO_IMP2.layDanhSachTaiKhoan().forEach(System.out::println);
                        break;
                    case 3:
                    	taiKhoanDAO_IMP2 = new TaiKhoanDAO_IMP();
                    	System.out.println("Nhap ten tai khoan: ");
                    	sc = new Scanner(System.in);
                    	tenTK  =  sc.nextLine();
                    	System.out.println("tên tài khoản: "+tenTK.trim());
                    	taiKhoanDAO_IMP2.xoataikhoan(tenTK);
                    	
                        break;
                    case 6:
                    	taiKhoanDAO_IMP2 = new TaiKhoanDAO_IMP();
                    	System.out.println("Nhap ten tai khoan: ");
                    	sc = new Scanner(System.in);
                    	tenTK  =  sc.nextLine();
                    	System.out.println("tên tài khoản: "+tenTK.trim());
                    	mkm = "123456";
                    	NhanVien nv =  nhanVienDAO_IMP.layThongTinNhanVien(new NhanVien(tenTK));
                    	
                        break;
                    case 7:
                    	taiKhoanDAO_IMP2 = new TaiKhoanDAO_IMP();
                    	System.out.println("Nhap ten tai khoan: ");
                    	sc = new Scanner(System.in);
                    	tenTK  =  sc.nextLine();
                    	System.out.println("tên tài khoản: "+tenTK.trim());
                    	
                    	System.out.println("Nhap trang thai: ");
                    	sc = new Scanner(System.in);
                    	String trangThai  = sc.nextLine();
                    	nhanVienDAO_IMP = new NhanVienDAO_IMP();
                    	nv =  nhanVienDAO_IMP.layThongTinNhanVien(new NhanVien(tenTK));
                    	
//                    	taiKhoanDAO_IMP2.updataTinhTrangDangNhap(nv, trangThai);
                    	
                    	break;
                    case 8:
                    	taiKhoanDAO_IMP2 = new TaiKhoanDAO_IMP();
                    	System.out.println("Nhap ten tai khoan: ");
                    	sc = new Scanner(System.in);
                    	tenTK  =  sc.nextLine();
                    	tk =  taiKhoanDAO_IMP2.layThongTinTaiKhoan(tenTK);
                    	System.out.println(tk);
                    	if(tk.getTenTK().getMaNV().equals(tenTK)) {
                    		System.out.println("Nhap mật khẩu: ");
                        	sc = new Scanner(System.in);
                        	mk =  sc.nextLine();
                        	if(tk.getMatKhau().equals(mk)) {
                        		if(tk.getTrangThai().equals("Đã đăng xuất")){
                        			System.out.println("Đăng nhập thành công");
								} else {
									System.out.println("Tài khoản đã đăng nhập");
								}
							} else {
								System.out.println("Sai mật khẩu");
							}
						} else {
							System.out.println("Sai tài khoản hoặc mật khẩu");
						}
                    	
                    	break;
                   
                    }
                   
				}while(c3!=0);
				break;
			case 4:
				MaXacNhan maXacNhan = new MaXacNhan();
				MaXacNhanDAO_IMP maXacNhanDAO_IMP = new MaXacNhanDAO_IMP();
				nhanVienDAO_IMP = new NhanVienDAO_IMP();
				

				 
				if(nhanVienDAO_IMP.checkDuplicateEmail("ngohiep1750@gmail.com","Đang làm")) {
					System.out.println("Email đã tồn tại");
					System.out.println(nhanVienDAO_IMP.timKiemNhanVienTheoEmail("ngohiep1750@gmail.com"));
					NhanVien nv = nhanVienDAO_IMP.timKiemNhanVienTheoEmail("ngohiep1750@gmail.com");
					
//					maXacNhan.setTaiKhoan(nv);
//					maXacNhan.setMaXacNhan(maXacNhanDAO_IMP.generateVerifyCode());
//					maXacNhan.setEmail(nv.getEmail());
//					maXacNhan.setThoiGianTao(LocalDateTime.now());
//					maXacNhanDAO_IMP.themMaXacNhan(maXacNhan);
					
					
					System.out.println(maXacNhanDAO_IMP.layMaXacNhan(nv.getMaNV()));
//					maXacNhanDAO_IMP.xoaMaXacNhan(nv.getMaNV());
				} else {
					System.out.println("Email chưa tồn tại");
				}
				 break;
			case 5:
				Gson GSON = new Gson();
				SachDAO_IMP sachDAO_IMP = new SachDAO_IMP();
//				Sach s = new Sach();
//				s.setMaSanPham("Sach00001");
//				s.setTenSanPham("Sach 1");
//				s.setDonGia(100000);
//				s.setSoLuongTon(100);
//				s.setMoTa("Sách tình yêu");
//				s.setTinhTrang("Đang bán");
//				s.setHinhAnh("sach1.jpg");
//				s.setNamXuatBan(2003);
//				s.setSoTrang(110);
//				s.setNgonNgu("Tiếng Việt");
//				
//				sachDAO_IMP.insertSach(s);
				
				//System.out.println(sachDAO_IMP.layThongTinSach("Sach00001"));
//				SanPhamDAO_IMP sanPhamDAO_IMP = new SanPhamDAO_IMP();
//				System.out.println(sanPhamDAO_IMP.layThongTinSanPham("VP00001"));
				
//				SanPham_DAO sanPham_DAO = new SanPhamDAO_IMP();
//				sanPham_DAO.layDanhSachSanPham().forEach(System.out::println); 
//				
//				VanPhongPhamDAO_IMP vanPhongPhamDAO_IMP = new VanPhongPhamDAO_IMP();
//				VanPhongPham vp = new VanPhongPham();
//				vp.setMaSanPham("VP00001");
//				vp.setTenSanPham("Văn phòng phẩm 1");
//				vp.setDonGia(10000);
//				vp.setSoLuongTon(100);
//				vp.setMoTa("Văn phòng phẩm");
//				vp.setTinhTrang("Đang bán");
//				vp.setHinhAnh("vp1.jpg");
//				vp.setChatLieu("Nhựa");
//				vp.setNamSanXuat(2003);
//				
//				vanPhongPhamDAO_IMP.themVanPhongPham(vp);
				String json = GSON.toJson( sachDAO_IMP.layThongTinSach("Sach00011"));
				System.out.println(json);
				
				System.out.println(sanPham_DAO.layThongTinSanPham("VP00001"));
				
				JsonObject jsonObject = GSON.fromJson(json, JsonObject.class);
				
		        String tacGia = jsonObject.get("tacGia").toString();
		        int namXuatBan = jsonObject.get("namXuatBan").getAsInt();
		        int soTrang = jsonObject.get("soTrang").getAsInt();
		        String ngonNgu = jsonObject.get("ngonNgu").getAsString();
		        String tenSanPham = jsonObject.get("tenSanPham").getAsString();
		        int soLuongTon = jsonObject.get("soLuongTon").getAsInt();
		        String moTa = jsonObject.get("moTa").getAsString();
		        LoaiSanPham  loaiSanPham = GSON.fromJson(jsonObject.get("loaiSanPham"), LoaiSanPham.class);
		        
		        // In ra các giá trị đã trích xuất
		        System.out.println("Tác giả: " + tacGia);
		        System.out.println("Năm xuất bản: " + namXuatBan);
		        System.out.println("Số trang: " + soTrang);
		        System.out.println("Ngôn ngữ: " + ngonNgu);
		        System.out.println("Tên sản phẩm: " + tenSanPham);
		        System.out.println("Số lượng tồn: " + soLuongTon);
		        System.out.println("Mô tả: " + moTa);
		        System.out.println("Loại sản phẩm: " + loaiSanPham);
		        
		        System.out.println("-----------------------------------------");
		        System.out.println("Lấy danh sách sản phẩm");
		       
		        System.out.println(json);
		        Sach sach = new Sach();
		       
		        String theLoai1 = "";
		        sach = sachDAO_IMP.layThongTinSach("Sach00011");
		        for (TheLoai theLoai : sach.getTheLoai()) {
		        	theLoai1 += theLoai.getTenTheLoai()+",";
		        }
		        System.out.println("Thể loại: "+theLoai1.trim().substring(0, theLoai1.length()-1));
		        json = GSON.toJson(sanPham_DAO.layDanhSachSanPham());
		        JsonArray jsonArray = GSON.fromJson(json, JsonArray.class);
		        for (JsonElement jsonElement : jsonArray) {
				
					System.out.println("Tên sản phẩm : "+jsonElement.getAsJsonObject());
//					String tenSanPham1 = jsonElement.getAsJsonObject().get("tenSanPham").getAsString();
					
					
//					int soLuongTon1 = jsonElement.getAsJsonObject().get("soLuongTon").getAsInt();
//					System.out.println("Số lượng tồn: "+soLuongTon1);
					
		        }
		        
		        sanPham_DAO.updateTinhTrang("VP00001", "Ngừng bán");

		        json = GSON.toJson(sanPham_DAO.layDanhSachSanPham());
		        JsonArray jsonArray1 = GSON.fromJson(json, JsonArray.class);
		        for (JsonElement jsonElement1 : jsonArray1) {
		        	System.out.println("Sản phẩm : "+jsonElement1.getAsJsonObject());
		        }
		        	
		        sanPham_DAO.updateSoLuong("VP00001", 10);
		        json = GSON.toJson(sanPham_DAO.layThongTinSanPham("VP00001"));
		        System.out.println(json);
		        
		        System.out.println("-------------------------------------------------");
		        
		        sachDAO_IMP.updateSachNgonNguMoTa("Sach00001", "Tiếng Anh", "Sách Giáo Khoa");
		        json = GSON.toJson(sanPham_DAO.layThongTinSanPham ("Sach00001"));
		        System.out.println(json);
		        
				break;
			 case 6:
				 MaXacNhan_DAO maXacNhanDAO  = new MaXacNhanDAO_IMP();
				 System.out.println(maXacNhanDAO .generateVerifyCode());
				
				 NhanVien_DAO  nhanVien_DAO = new NhanVienDAO_IMP();
				 NhanVien nv =  nhanVien_DAO.layThongTinNhanVien(new NhanVien("21081841"));
				 System.out.println(nv);
				 
				 maXacNhan = new MaXacNhan();
				 
				 maXacNhan.setTaiKhoan(nv);
				 maXacNhan.setMaXacNhan(maXacNhanDAO.generateVerifyCode());
				 maXacNhan.setEmail(nv.getEmail());
				 maXacNhan.setThoiGianTao(LocalDateTime.now());
				 maXacNhanDAO.themMaXacNhan(maXacNhan);
				 maXacNhanDAO.xoaMaXacNhan(nv.getMaNV());
				 break;
			 case 7:
//				 KhachHang kh = new KhachHang("KH00001", "Ngô Thái Hiệp", "1234567890", "a");
//				 KhachHang_DAO khachHang_DAO = new KhachHangDAO_IMP();
//				 khachHang_DAO.InsertKhachHang(kh);
//				 System.out.println(khachHang_DAO.layThongTinKhachHang("1234567890"));
//				 System.out.println(khachHang_DAO.layThongTinKhachHang_TheoMaKH("KH00001"));
//				 kh = khachHang_DAO.layThongTinKhachHang_TheoMaKH("KH00001");
//				 kh.setDiemTL(120);
//				 khachHang_DAO.updateDiemTichLuy(kh);
//				 System.out.println(khachHang_DAO.layThongTinKhachHang_TheoMaKH("KH00001"));
//				 System.out.println(khachHang_DAO.generateVerifyCodeKH());
//				 System.out.println(khachHang_DAO.generateVerifyCode_KhachHangLe());
				
				 break;
			 case 8:
				 HangCho_DAO hangCho_DAO = new HangChoDAO_IMP();
					hangCho_DAO.layDanhSachHangChoTheoMaKhachHang("KH00003").forEach(System.out::println);
				 hangCho_DAO.DeleteHangChoQuaNgay();
				 break;
			 case 9 :
				 HoaDon hoaDon = new HoaDon("HD00001", LocalDate.now(), new NhanVien("21081841"), new KhachHang("KH00001"), 100000.000, 10000.000, 90000.000, 10.000, 90000.000);
				 HoaDon_DAO hoaDon_DAO = new HoaDonDAO_IMP() ;
//				 hoaDon_DAO.InsertHoaDon(hoaDon);
				 hoaDon_DAO.layDanhSachHoaDon().forEach(System.out::println);
				 hoaDon_DAO.layHoaDon("HD00001", LocalDate.parse("2024-04-18"));
				 System.out.println( hoaDon_DAO.layHoaDon("HD00001", LocalDate.parse("2024-04-18")));
				 System.out.println(LocalDate.parse("2024-04-18"));
				 break;
			 case 10:
				 ChiTietHoaDon_DAO chiTietHoaDon_DAO = new ChiTietHoaDonDAO_IMP();
				 
				 sanPham_DAO = new SanPhamDAO_IMP();
				 SanPham sp = sanPham_DAO.layThongTinSanPham("Sach00001");
				 hoaDon_DAO = new HoaDonDAO_IMP();
				 hoaDon = hoaDon_DAO.layHoaDon("HD00001", LocalDate.parse("2024-04-18"));
				 chiTietHoaDon_DAO.InsertCTHoaDon(hoaDon, 30, 10000, sp);
				 chiTietHoaDon_DAO.layDanhSachCTHoaDon().forEach(System.out::println);
				 sanPham_DAO.updateSoLuong("Sach00001", 30);
				System.out.println();
				 break;
				 
		        case 11:
		        	LocalDate localDate = LocalDate.now();
		        	nv = nhanVienDAO_IMP.layThongTinNhanVien(new NhanVien("21081841"));
		        	ThoiGianHoatDong tghd = new ThoiGianHoatDong(null, nv, localDate, null, null);
		        	ThoiGianHoatDong_DAO thoiGianHoatDong_DAO = new ThoiGianHoatDongDAO_IMP();
		        	
//		        	System.out.println(tghd_DAO.kiemTraDangNhapTrongNgay(tghd, startTimeLamViec, endTime));
//		        	System.out.println(tghd);
//		        	
//		        	
//		        	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//		        		LocalTime loCalTime = LocalTime.now();
//		           	 String catChuoi = loCalTime+"";
//		            
//	                LocalDate localDateNam = LocalDate.now();
//	                String formattedTime = loCalTime.format(formatter);
//	               
//	               
//	                Date currentDate = new Date();
//	                SimpleDateFormat formatterDay = new SimpleDateFormat("ddMMyy");
//	                String formattedDate = formatterDay.format(currentDate);
//	        
//	                String maLamViec = formattedDate+ formattedTime.substring(0,2) +formattedTime.substring(3,5) + nv.getMaNV();
//	                catChuoi = catChuoi.substring(0, 8); 
//	                LocalTime thoiGianDangNhap = LocalTime.parse(catChuoi, formatter);
//	                tghd = new ThoiGianHoatDong(maLamViec, nv, localDateNam, thoiGianDangNhap);
//	                
//	                thoiGianHoatDong_DAO.insertThoiGianLam(tghd);
		        	
		        	
		        	

		        	 LocalTime startTimeLamViec = LocalTime.of(18,00,00);
		        	 LocalTime endTime = LocalTime.of(23, 59, 59);
//		        	 tghd.setThoiGianDangNhap(startTimeLamViec);
//		        	 tghd.setThoiGianDangXuat(endTime);
//		        	 System.out.println(tghd);
		        	 tghd.setNgayDangNhap(localDate);
//		        	 System.out.println(thoiGianHoatDong_DAO.kiemTraDangNhapTrongNgayString(tghd, startTimeLamViec+"", endTime+""));
		        	System.out.println(thoiGianHoatDong_DAO.kiemTraDangNhapTrongNgay(tghd, startTimeLamViec, endTime));
		        	System.out.println(tghd);
		        	break;
			}
			
			 
		 }while(n!=0);
		
		
	}
	public static void luaChon1() {
		System.out.println("1. Them chuc vu");
		System.out.println("2. In danh sach chuc vu");
		System.out.println("3. Sua chuc vu");
		System.out.println("4. Xoa chuc vu");
		
	}
	public static void luaChon2() {
		System.out.println("1. Them Nhan vien");
		System.out.println("2. In danh sach nhan vien");
	}
}
