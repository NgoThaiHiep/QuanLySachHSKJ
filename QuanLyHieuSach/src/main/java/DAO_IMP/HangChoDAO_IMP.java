package DAO_IMP;

import java.util.ArrayList;

import DAO.HangCho_DAO;
import entity.HangCho;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class HangChoDAO_IMP implements HangCho_DAO{

	
	private EntityManager em;
	private static final String PERISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	public HangChoDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERISTENCE_UNIT_NAME).createEntityManager();
		
	}
	@Override
	public ArrayList<HangCho> layDanhSachHangCho() {
		// TODO Auto-generated method stub
		return (ArrayList<HangCho>)em.createQuery("select c from HangCho c", HangCho.class)
				.getResultList();
	}

	@Override
	public ArrayList<HangCho> layDanhSachHangChoTheoMaKhachHang(String maKhachHang) {
		// TODO Auto-generated method stub
		return (ArrayList<HangCho>)em.createQuery("select c from HangCho c where c.maKhachHang = :maKhachHang", HangCho.class)
                .setParameter("maKhachHang", maKhachHang)
                .getResultList();
	}

	@Override
	public boolean InsertHangCho(HangCho hangCho) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(hangCho);
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
	public void DeleteDanhSachHangCho(String maKhachHang) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.createQuery("delete from HangCho c where c.maKhachHang = :maKhachHang")
			.setParameter("maKhachHang", maKhachHang)
			.executeUpdate();
			tx.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}
		
	}

	@Override
	public void DeleteHangChoQuaNgay() {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.createQuery("delete from HangCho c where c.ngayMua < current_date()")
			.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}
		
		
	}

}
