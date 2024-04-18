package DAO_IMP;

import java.util.ArrayList;

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

}
