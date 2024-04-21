package DAO_IMP;

import java.util.ArrayList;



import DAO.SanPham_DAO;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class SanPhamDAO_IMP implements SanPham_DAO{
	private EntityManager em;
	private static final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	public SanPhamDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
				.createEntityManager();
	}
	
	@Override
	public SanPham layThongTinSanPham(String maSanPham) {
		// TODO Auto-generated method stub
		
		return em.find(SanPham.class, maSanPham);
	}

	@Override
	public ArrayList<SanPham> layDanhSachSanPham() {
		// TODO Auto-generated method stub
		return (ArrayList<SanPham>) em.createQuery("select c from SanPham c", SanPham.class).getResultList();
	}

	@Override
	public boolean updateTinhTrang(String maSanPham, String tinhTrang) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			SanPham sp = em.find(SanPham.class, maSanPham);
			sp.setTinhTrang(tinhTrang);
			
			em.merge(sp);
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
	public boolean updateSoLuong(String maSanPham, int soLuong) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			SanPham sp = em.find(SanPham.class, maSanPham);
			sp.setSoLuongTon(sp.getSoLuongTon() - soLuong);
			em.merge(sp);
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public SanPham layDanhSachTheoMaSach(String maSach) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		SanPham sanPham = new SanPham() {
			
		};
		try {
            tx.begin();
			sanPham =  em.createQuery("select c from Sach c where c.maSanPham = :maSach", SanPham.class)
					.setParameter("maSach", maSach)
					.getSingleResult();
			tx.commit();
            return sanPham;
        } catch (Exception e) {
        	
        }
		return null;
	}

	@Override
	public SanPham layDanhSachTheoTenSach(String tenSach) {
		// TODO Auto-generated method stub
		return  em.createQuery("select c from Sach c where c.tenSanPham = :tenSach", SanPham.class)
                .setParameter("tenSach", tenSach)
                .getSingleResult();
	}

	@Override
	public SanPham layThongTinSanPhamTheoTen(String tenSanPham) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from SanPham c where c.tenSanPham = :tenSanPham", SanPham.class)
                .setParameter("tenSanPham", tenSanPham)
                .getSingleResult();
	}

	
	

}
