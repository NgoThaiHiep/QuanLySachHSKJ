package DAO_IMP;


import DAO.Sach_DAO;
import entity.Sach;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class SachDAO_IMP implements Sach_DAO{
	private static final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	private static EntityManager em;
	
	public SachDAO_IMP() {
		// TODO Auto-generated constructor stub
	em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
			.createEntityManager();
	}
	
	
	@Override
	public Sach layThongTinSach(String tenSach) {
		// TODO Auto-generated method stub
		return em.find(Sach.class, tenSach);
	}

	@Override
	public boolean insertSach(Sach s) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(s);
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}
		return false;
	}

	@Override
	public boolean updateSach(Sach s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSach(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String generateMaSach() {
		// TODO Auto-generated method stub
		String number = "";
		 for (int i = 1; i < 99999; i++) {
	            // Định dạng số thành chuỗi và thêm các số 0 bổ sung để đảm bảo đủ 5 chữ số
	            number = "Sach"+String.format("%05d", i);
	            
	            if(!kiemTraMaSach(number)){
	                break;
	            }
	            }  
	        return number;
		
	
	}

	@Override
	public boolean kiemTraMaSach(String code) {
		// TODO Auto-generated method stub
		
		return em.createQuery("select c from Sach c where c.maSach = :code", Sach.class)
                .setParameter("code", code)
                .getResultList().size() > 0;
	}


	@Override
	public boolean updateSachNgonNguMoTa(String maSach, String ngonNgu, String moTa) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Sach s = em.find(Sach.class, maSach);
			SanPham sp = em.find(SanPham.class, maSach);
			
			s.setNgonNgu(ngonNgu);
			sp.setMoTa(moTa);
			em.merge(s);
			tx.commit();
			return true;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}
		return false;
	}

	


}
