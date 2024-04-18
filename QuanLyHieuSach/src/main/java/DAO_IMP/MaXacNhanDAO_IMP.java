package DAO_IMP;

import java.text.DecimalFormat;
import java.util.Random;

import DAO.MaXacNhan_DAO;
import entity.MaXacNhan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MaXacNhanDAO_IMP implements MaXacNhan_DAO{

	private static EntityManager em;
	private static final String PERISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	public MaXacNhanDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERISTENCE_UNIT_NAME).createEntityManager();
		
	}
	
	@Override
	public String generateVerifyCode() {
		// TODO Auto-generated method stub
		DecimalFormat df= new DecimalFormat("000000");
		Random ran = new Random();
		String code = df.format(ran.nextInt(1000000));
		while(checkDuplicateCode(code)) {
			code = df.format(ran.nextInt(1000000));
		}
		return code;
		
	}

	@Override
	public boolean checkDuplicateCode(String code) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from MaXacNhan c where c.maXacNhan = :code", MaXacNhan.class)
						.setParameter("code", code)
						.getResultList().size() > 0;
	}

	

	


	@Override
	public boolean themMaXacNhan(MaXacNhan mxn) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
            tx.begin();
            em.persist(mxn);
            tx.commit();
            return true;
        } catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
        }
		return false;
	}

	@Override
	public boolean xoaMaXacNhan(String taiKhoan) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			MaXacNhan mxn = em.find(MaXacNhan.class, taiKhoan);
			em.remove(em.contains(mxn) ? mxn : em.merge(mxn));
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return false;
	}

	@Override
	public MaXacNhan layMaXacNhan(String taiKhoan) {
		// TODO Auto-generated method stub
		return em.find(MaXacNhan.class, taiKhoan);
	}

}
