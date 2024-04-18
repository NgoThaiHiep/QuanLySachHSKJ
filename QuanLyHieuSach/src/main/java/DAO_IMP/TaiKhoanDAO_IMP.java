package DAO_IMP;

import java.util.ArrayList;

import DAO.TaiKhoan_DAO;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class TaiKhoanDAO_IMP implements TaiKhoan_DAO{

	private EntityManager em;
	public TaiKhoanDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory("QuanLyHieuSach MSSQL")
				.createEntityManager();
	}
	@Override
	public ArrayList<TaiKhoan> layDanhSachTaiKhoan() {
		// TODO Auto-generated method stub
		return (ArrayList<TaiKhoan>) em.createQuery("select c from TaiKhoan c", TaiKhoan.class).getResultList();
	}

	@Override
	public TaiKhoan layThongTinTaiKhoan(String tenTK) {
		// TODO Auto-generated method stub
		return em.find(TaiKhoan.class, tenTK);
	}

	@Override
	public boolean login(TaiKhoan tk) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean login_DaDangNhap(TaiKhoan tk) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean inserTaiKhoan(TaiKhoan tk) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		
			try {
				tx.begin();
				em.persist(tk);
				tx.commit();
				return true;
				
			} catch (Exception e) {
					// TODO: handle exception
				e.printStackTrace();
				tx.rollback();
			}
			
		
		return false;
	}

	@Override
	public boolean upDateMatKhau(NhanVien nhanVien, String matKhauCu, String matKhauMoi) {
		
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			 int updatedCount = em.createQuery(
		                "UPDATE TaiKhoan " +
		                "SET matKhau = :matKhauMoi " +
		              
		                "WHERE matKhau = :matKhauCu AND tenTK = :tk ")
		                .setParameter("matKhauMoi", matKhauMoi)
		                .setParameter("matKhauCu", matKhauCu)
		                .setParameter("tk", nhanVien)
		                .executeUpdate();
			tx.commit();
			return updatedCount > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}
		
		return false;
		
	   
	}


	@Override
	public boolean updataPasswordLost(NhanVien nhanVien, String matKhauMoi, String trangThai) {
		// TODO Auto-generated method stub
		
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			int updateCount = em.createQuery(
					 "UPDATE TaiKhoan " +
				    "SET matKhau = :matKhauMoi, " +
					"trangThai = :trangThai " +
				    "WHERE  tenTK = :tk"
					)
					 .setParameter("matKhauMoi", matKhauMoi)
					 .setParameter("trangThai", trangThai)
					 .setParameter("tk", nhanVien)
					.executeUpdate();
			tx.commit();
			return updateCount > 0;
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
			
		}
		return false;
		
	}

	@Override
	public boolean updataTinhTrangDangNhap(NhanVien nhanVien, String trangThai) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.createQuery(
					"UPDATE TaiKhoan " +
			        "SET trangThai = :trangThai " +
					"WHERE tenTK = :tenTk"
					).setParameter("trangThai", trangThai)
					.setParameter("tenTk", nhanVien)
					.executeUpdate();
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}
		return false;
				
	}
	@Override
	public boolean xoataikhoan(String id) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
            tx.begin();
            TaiKhoan tk = em.find(TaiKhoan.class, id);
            em.remove(em.contains(tk) ? tk : em.merge(tk));
            tx.commit();
            return true;
        } catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
        }
		return false;
	}

}
