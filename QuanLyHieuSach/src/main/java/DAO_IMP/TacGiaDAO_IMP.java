package DAO_IMP;

import java.util.ArrayList;

import DAO.TacGia_DAO;
import entity.TacGia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class TacGiaDAO_IMP implements TacGia_DAO{

	private static final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	private EntityManager em;
	public TacGiaDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}
	@Override
	public boolean kiemTraMaSach(String code) {
		// TODO Auto-generated method stub
		return em.find(TacGia.class, code) != null;
	}

	@Override
	public ArrayList<TacGia> layDanhSachTacGia() {
		// TODO Auto-generated method stub
		return (ArrayList<TacGia>) 
				em.createQuery("select tg from TacGia tg", TacGia.class)
				.getResultList();
	}

	@Override
	public boolean InsertTacGia(TacGia tacGia) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(tacGia);
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
	public String generateTacGia() {
		// TODO Auto-generated method stub
		 String number = "";
	        int n = 1;
	        do{
	            
	        number = "TG"+ n;
	            
	        n++;
	        }while(kiemTraMaSach(number));
	        return number;
	}
	@Override
	public TacGia layThongTinTacGiaTheoTen(String tenTacGia) {
		// TODO Auto-generated method stub
		return em.createQuery
				("select tg from TacGia tg where tg.tenTacGia = :tenTacGia", TacGia.class)
				.setParameter("tenTacGia", tenTacGia)
				.getSingleResult();
	}
	@Override
	public TacGia layThongTinTacGiaTheoMa(String maTacGia) {
		// TODO Auto-generated method stub
		return em.find(TacGia.class, maTacGia);
	}
	

}
