package DAO_IMP;

import java.util.List;

import DAO.ChucVu_DAO;
import entity.ChucVu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ChucVuDAO_IMP implements ChucVu_DAO{

	private EntityManager em;
	
	public ChucVuDAO_IMP() {
		// TODO Auto-generated constructor stub
		
		em = Persistence.createEntityManagerFactory("QuanLyHieuSach MSSQL")
				.createEntityManager();
	}
	@Override
	public boolean themChucVu(ChucVu chucVu) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			em.persist(chucVu);
			tx.commit();
			return true;
		} catch (Exception e) { 
			  tx.rollback();
           e.printStackTrace();
           
           
        } 
		return false;
	}

	@Override
	public boolean suaChucVu(ChucVu chucVu) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
            tx.begin();
            em.merge(chucVu);
            tx.commit();
            return true;
        } catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
        }
		return false;
	}

	@Override
	public boolean xoaChucVu(String maChucVu) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			
			tx.begin();
			ChucVu chucVu = em.find(ChucVu.class, maChucVu);
			em.remove(em.contains(chucVu) ? chucVu : em.merge(chucVu));
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
	public List<ChucVu> getDSChucVu() {
		// TODO Auto-generated method stub
		return em.createQuery("select c  from ChucVu c", ChucVu.class)
				.getResultList();
	}

}
