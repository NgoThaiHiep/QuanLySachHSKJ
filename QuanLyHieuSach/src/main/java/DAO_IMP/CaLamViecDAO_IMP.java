package DAO_IMP;

import java.util.ArrayList;

import DAO.CaLamViec_DAO;
import entity.CaLamViec;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class CaLamViecDAO_IMP implements CaLamViec_DAO{
	private EntityManager em;
	private static final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	public CaLamViecDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
				.createEntityManager();
	}
	
	@Override
	public ArrayList<CaLamViec> layDanhSachCaLamViec() {
		// TODO Auto-generated method stub
		return ( ArrayList<CaLamViec> )em.createQuery("select clv from CaLamViec clv", CaLamViec.class)
				.getResultList();
	}
	
	
}
