package DAO_IMP;

import java.util.ArrayList;
import java.util.List;

import DAO.Thue_DAO;
import entity.Thue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class ThueDAO_IMP implements Thue_DAO {
	private EntityManager em;
	private static final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	
	public ThueDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
		
	}
	@Override
	public ArrayList<Thue> layDuLieuThue() {
	    return new ArrayList<>(em.createQuery("select t from Thue t", Thue.class)
	                               .getResultList());
	}
	@Override
	public boolean updateThue(Thue thue) {
		 try {
            em.getTransaction().begin();
            em.merge(thue);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
	}
		return false;
	}
	@Override
	public boolean insertThue(Thue thue) {
		try {
			em.getTransaction().begin();
			em.persist(thue);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean deleteThue(String id) {
		Thue thue = em.find(Thue.class, id);
		try {
			em.getTransaction().begin();
			em.remove(thue);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	
}
