package DAO_IMP;

import java.util.ArrayList;

import DAO.ChiTietHoaDon_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ChiTietHoaDonDAO_IMP implements ChiTietHoaDon_DAO{
	private static final String PERISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	private EntityManager em;
	
	public ChiTietHoaDonDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERISTENCE_UNIT_NAME).createEntityManager();
	}
	@Override
	public boolean InsertCTHoaDon(HoaDon hd, int soLuong, double giaBan, SanPham sanPham) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			ChiTietHoaDon cthd = new ChiTietHoaDon();
			cthd.setHoaDon(hd);
			cthd.setSanPham(sanPham);
			cthd.setSoLuong(soLuong);
			cthd.setDonGia(giaBan);
			em.persist(cthd);
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
	public boolean UpdateCTHoaDon(HoaDon hd, int soLuong, double giaBan, SanPham sanPham) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteCTHoaDon(HoaDon hd, SanPham sanPham) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteCTHoaDon(HoaDon hd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<ChiTietHoaDon> layDanhSachCTHoaDon() {
		// TODO Auto-generated method stub
		return (ArrayList<ChiTietHoaDon>) em.createQuery("select c from ChiTietHoaDon c",ChiTietHoaDon.class)
				.getResultList();
	}
	@Override
	public ArrayList<ChiTietHoaDon> layDanhSachCTHoaDonTheoMa(String maHoaDon) {
		// TODO Auto-generated method stub
		return (ArrayList<ChiTietHoaDon>) em.createQuery("select c from ChiTietHoaDon c where c.hoaDon.maHoaDon = :maHoaDon", ChiTietHoaDon.class)
				.setParameter("maHoaDon", maHoaDon)
				.getResultList()
				;
	}
	

}
