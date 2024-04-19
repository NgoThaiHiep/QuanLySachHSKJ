package DAO_IMP;

import java.util.ArrayList;

import DAO.NhaXuatBan_DAO;
import entity.NhaXuatBan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class NhaXuatBanDAO_IMP implements NhaXuatBan_DAO{
	private EntityManager em;
	private static final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	public NhaXuatBanDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}
	@Override
	public String generateNhaXuatBan() {
		// TODO Auto-generated method stub
		 String number = "";
	        int n = 1;
	        do{
	            
	        number = "NXB"+ n;
	            
	        n++;
	        }while(kiemTraNhaXuatBan(number));
	        return number;
	}

	@Override
	public boolean kiemTraNhaXuatBan(String code) {
		// TODO Auto-generated method stub
		return em.find(NhaXuatBan.class, code) != null;
	}

	@Override
	public boolean InsertNhaXuatBan(NhaXuatBan nhaXuatBan) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(nhaXuatBan);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	
	@Override
	public ArrayList<NhaXuatBan> layDanhSachNhaXuatBan() {
		// TODO Auto-generated method stub
		return  (ArrayList<NhaXuatBan>)
				em.createQuery("select nxb from NhaXuatBan nxb", NhaXuatBan.class)
				.getResultList();
	}

}
