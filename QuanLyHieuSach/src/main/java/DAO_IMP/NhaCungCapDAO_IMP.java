package DAO_IMP;

import java.util.ArrayList;

import DAO.NhaCungCap_DAO;
import entity.NhaCungCap;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class NhaCungCapDAO_IMP implements NhaCungCap_DAO{

	private EntityManager em;
	private final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	public NhaCungCapDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}
	@Override
	public String generateNhaCungCap() {
		// TODO Auto-generated method stub
		   String number = "";
	        int n = 1;
	        do{
	            
	        number = "NCC"+ n;
	            
	        n++;
	        }while(kiemTraMaNhaCungCap(number));
	        return number;
	}

	@Override
	public boolean kiemTraMaNhaCungCap(String code) {
		// TODO Auto-generated method stub
		return em.find(NhaCungCap.class, code) != null;
	}

	@Override
	public boolean InsertNhaCungCap(NhaCungCap nhaCungCap) {
		// TODO Auto-generated method stub
	EntityTransaction tx = em.getTransaction();
	    try {
            tx.begin();
            em.persist(nhaCungCap);
            tx.commit();
            return true;
        } catch (Exception e) {
        	            e.printStackTrace();
        	                        tx.rollback();
        	                        
        }
		return false;
	}

	@Override
	public ArrayList<NhaCungCap> layDanhSachNhaCungCap() {
		// TODO Auto-generated method stub
		return (ArrayList<NhaCungCap>) 
				em.createQuery("select n from NhaCungCap n", NhaCungCap.class)
				.getResultList();
	}

}
