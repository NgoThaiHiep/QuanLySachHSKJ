package DAO_IMP;

import java.util.ArrayList;

import DAO.LoaiVanPhongPham_DAO;
import entity.LoaiVanPhongPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class LoaiVanPhongPhamDAO_IMP implements LoaiVanPhongPham_DAO {
	private EntityManager em;
	private static final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	public LoaiVanPhongPhamDAO_IMP() {
		// TODO Auto-generated constructor stub
	em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}
	@Override
	public String generatMaLoaiVanPhongPham() {
		// TODO Auto-generated method stub
		  String number = "";
	        int n = 1;
	        do{       
	        number = "LVPP"+ n;
	        n++;
	        }while(kiemTraMaLoaiVanPhongPham(number));
	        return number;
	}

	@Override
	public boolean kiemTraMaLoaiVanPhongPham(String code) {
		// TODO Auto-generated method stub
		return em.find(LoaiVanPhongPham.class, code) != null;
	}

	@Override
	public boolean InsertLoaiVanPhongPham(LoaiVanPhongPham loaiVanPhongPham) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(loaiVanPhongPham);
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
	public ArrayList<LoaiVanPhongPham> layDanhLoaiVanPhongPham() {
		// TODO Auto-generated method stub
		return (ArrayList<LoaiVanPhongPham>) em
				.createQuery("select lvpp from LoaiVanPhongPham lvpp", LoaiVanPhongPham.class)
				.getResultList();
	}

}
