package DAO_IMP;

import DAO.VanPhongPham_DAO;
import entity.Sach;
import entity.SanPham;
import entity.VanPhongPham;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class VanPhongPhamDAO_IMP implements VanPhongPham_DAO {

	private EntityManager em;
	private static final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	public VanPhongPhamDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}
	@Override
	public Sach layThongTinSach(String tenSach) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertVanPhongPham(VanPhongPham vpp) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
            tx.begin();
            em.persist(vpp);
            tx.commit();
            return true;
        } catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
        }
		return false;
	}

	@Override
	public boolean updateVanPhongPham(VanPhongPham vpp) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			SanPham sp = em.find(SanPham.class, vpp.getMaSanPham());
			sp.setTenSanPham(vpp.getTenSanPham());
			sp.setSoLuongTon(vpp.getSoLuongTon());
			sp.setDonGia(vpp.getDonGia());
			sp.setNhaCungCap(vpp.getNhaCungCap());
			sp.setTinhTrang(vpp.getTinhTrang());
			sp.setLoaiSanPham(vpp.getLoaiSanPham());
			sp.setMoTa(vpp.getMoTa());
			sp.setHinhAnh(vpp.getHinhAnh());
			
			em.merge(vpp);
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean deleteVanPhongPham(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String generateMaVanPhongPham() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean kiemTraMaVanPhongPham(String code) {
		// TODO Auto-generated method stub
		return false;
	}


}
