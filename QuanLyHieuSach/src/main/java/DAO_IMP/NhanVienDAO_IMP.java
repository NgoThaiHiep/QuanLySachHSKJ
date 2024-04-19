package DAO_IMP;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import DAO.NhanVien_DAO;
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class NhanVienDAO_IMP implements NhanVien_DAO {

	private EntityManager em;
	
	public NhanVienDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory("QuanLyHieuSach MSSQL")
				.createEntityManager();
	}
	@Override
	public boolean capNhatNhanVien(NhanVien nv) {
		// TODO Auto-generated method stub
		
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(nv);
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}
		
		return false;
	}

	

	@Override
	public ArrayList<NhanVien> timKiemDanhSachTaiKhoanTheoTenDangNhap(String TenDangNhap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean InsertNhanVien(NhanVien nv) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(nv);
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
			
		}
		return false;
	}
	@Override
	public NhanVien layThongTinNhanVien(NhanVien nv) {
		// TODO Auto-generated method stub
		return em.find(NhanVien.class, nv.getMaNV());
	}
	@Override
	public ArrayList<NhanVien> layDanhSachNhanVien() {
		// TODO Auto-generated method stub
		return (ArrayList<NhanVien>) em.createQuery("select c from NhanVien c", NhanVien.class)
				.getResultList();
	}
	@Override
	public boolean capNhatTrangThaiNhanVien(String maNV, String trangThai) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public NhanVien timKiemNhanVienTheoMaNV(String maNV) {
		// TODO Auto-generated method stub
		return em.find(NhanVien.class, maNV);
	}
	@Override
	public boolean checkDuplicateEmail(String Email, String trangThai) {
		// TODO Auto-generated method stub
		
//		select c from MaXacNhan c where c.email = :Email and c.trangThai = N'Đã Tồn tại'
		
		return em
				.createQuery("select c from NhanVien c where c.email = :Email and c.trangThai = :trangThai",
						NhanVien.class)
				.setParameter("Email", Email).setParameter("trangThai", trangThai).getResultList().size() > 0;
	}
	@Override
	public ArrayList<NhanVien> dsNhanVienTheoTrangThai(String trangThai) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public NhanVien timKiemNhanVienTheoEmail(String email) {
		// TODO Auto-generated method stub
		return em
				.createQuery("select c from NhanVien c where c.email = :Email",
						NhanVien.class)
				.setParameter("Email", email)
				.getSingleResult();
	}
	@Override
	public String generateVerifyCode() {
		  DecimalFormat df = new DecimalFormat("000000");
          Random ran = new Random();

          // Lấy hai số cuối cùng của năm
          Calendar calendar = Calendar.getInstance();
          int year = calendar.get(Calendar.YEAR) % 100;

          String code;
          do {
              code = df.format(year * 10000 + ran.nextInt(10000));
          } while (checkDuplicateCode(code));
      return code;
	}
	@Override
	public boolean checkDuplicateCode(String code) {
		// TODO Auto-generated method stub
		return em.find(NhanVien.class, code) != null;
	}

}
