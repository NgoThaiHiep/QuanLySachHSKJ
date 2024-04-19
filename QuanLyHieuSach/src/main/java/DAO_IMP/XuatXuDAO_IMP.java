package DAO_IMP;

import java.util.ArrayList;

import DAO.XuatXu_DAO;
import entity.XuatXu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class XuatXuDAO_IMP implements  XuatXu_DAO{

	private EntityManager em;
	private static final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	
	public XuatXuDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}
	@Override
	public ArrayList<XuatXu> layDanhSachXuatXu() {
		// TODO Auto-generated method stub
		return (ArrayList<XuatXu>) 
				em.createQuery("select xx from XuatXu xx", XuatXu.class)
				.getResultList();
	}

	@Override
	public String generateXuatXu() {
		// TODO Auto-generated method stub
		 String number = "";
	        int n = 1;
	        do{
	            
	        number = "XX"+ n;
	            
	        n++;
	        }while(kiemTraMaXuatXu(number));
	        return number;
	}

	@Override
	public boolean kiemTraMaXuatXu(String code) {
		// TODO Auto-generated method stub
		return em.find(XuatXu.class, code) != null;
	}

	@Override
	public boolean InsertXuatXu(XuatXu xuatXu) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(xuatXu);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

}
