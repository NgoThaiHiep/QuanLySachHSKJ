package DAO_IMP;

import java.util.ArrayList;

import DAO.ThuongHieu_DAO;
import entity.ThuongHieu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ThuongHieuDAO_IMP implements ThuongHieu_DAO{

	private EntityManager em;
	private static final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	public ThuongHieuDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}
	@Override
	public ArrayList<ThuongHieu> layDanhSachThuongHieu() {
		// TODO Auto-generated method stub
		return (ArrayList<ThuongHieu>) 
				em.createQuery("select th from ThuongHieu th", ThuongHieu.class)
				.getResultList();
	}

	@Override
	public String generateThuongHieu() {
		// TODO Auto-generated method stub
		String number = "";
        int n = 1;
        do{
            
        number = "TH"+ n;
            
        n++;
        }while(kiemTraMaThuongHieu(number));
        return number;
	}

	@Override
	public boolean kiemTraMaThuongHieu(String code) {
		// TODO Auto-generated method stub
		return em.find(ThuongHieu.class, code) != null;
	}

	@Override
	public boolean InsertThuongHieu(ThuongHieu thuongHieu) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(thuongHieu);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}
	
	

}
