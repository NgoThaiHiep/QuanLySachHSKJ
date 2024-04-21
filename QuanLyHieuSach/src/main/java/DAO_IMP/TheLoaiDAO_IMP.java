package DAO_IMP;

import java.util.ArrayList;

import DAO.TheLoai_DAO;
import entity.TheLoai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class TheLoaiDAO_IMP implements TheLoai_DAO{

	private EntityManager em;
	private static final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	public TheLoaiDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}
	@Override
	public String generateTheLoai() {
		// TODO Auto-generated method stub
	     String number = "";
	        int n = 1;
	        do{
	        number = "TL"+ n;
	        n++;
	        }while(kiemTraTheLoai(number));
	        return number;
	}

	@Override
	public boolean kiemTraTheLoai(String code) {
		// TODO Auto-generated method stub
		return em.find(TheLoai.class, code) != null;
	}

	@Override
	public boolean InsertTheLoai(TheLoai theLoai) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(theLoai);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateTheLoai(TheLoai theLoai) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(theLoai);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteTheLoai(TheLoai theLoai) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(theLoai);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<TheLoai> layDanhSachTheLoai() {
		// TODO Auto-generated method stub
		return (ArrayList<TheLoai>)
				em.createQuery("select tl from TheLoai tl", TheLoai.class)
				.getResultList();
	}
	@Override
	public TheLoai layThongTinTheLoaiTen(String tenTheLoai) {
		// TODO Auto-generated method stub
		return em.createQuery("select tl from TheLoai tl where tl.tenTheLoai = :tenTheLoai", TheLoai.class)
                .setParameter("tenTheLoai", tenTheLoai)
                .getSingleResult();
	}
	@Override
	public TheLoai layThongTinTheLoaiMa(String maTheLoai) {
		// TODO Auto-generated method stub
		return em.find(TheLoai.class, maTheLoai);
	}

}
