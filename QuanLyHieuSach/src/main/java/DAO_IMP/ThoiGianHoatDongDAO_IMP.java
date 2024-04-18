package DAO_IMP;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

import DAO.ThoiGianHoatDong_DAO;
import entity.NhanVien;
import entity.ThoiGianHoatDong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional.TxType;


public class ThoiGianHoatDongDAO_IMP implements ThoiGianHoatDong_DAO {
	private EntityManager em;
	private static final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	public ThoiGianHoatDongDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}
	
	@Override
	public boolean kiemTraDangNhapTrongNgay(ThoiGianHoatDong tghd, LocalTime thoiGianBatDauCa, LocalTime thoiGianKetThucCa) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			//sử dung sql java hibernate 
			NhanVien nv = em.find(NhanVien.class, tghd.getNhanVien().getMaNV());
			em.createQuery( "select c from ThoiGianHoatDong c where c.nhanVien = :NhanVienID and c.ngayDangNhap = :NgayDangNhap and c.thoiGianDangNhap >= :ThoiGianBatDau and c.thoiGianDangXuat <= :ThoiGianKetThuc")
			.setParameter("NhanVienID", nv )
			.setParameter("NgayDangNhap", tghd.getNgayDangNhap())
			.setParameter("ThoiGianBatDau", thoiGianBatDauCa)
			.setParameter("ThoiGianKetThuc", thoiGianKetThucCa);
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
	public boolean capNhatThoiGianLam(ThoiGianHoatDong tghd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertThoiGianLam(ThoiGianHoatDong tghd) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
            tx.begin();
            em.persist(tghd);
            tx.commit();
            return true;
        } catch (Exception e) {
        	            e.printStackTrace();
        	                        tx.rollback();
        }
		return false;
	}

	@Override
	public ThoiGianHoatDong layThoiGianHoatDong(ThoiGianHoatDong tghd) {
	    return em.createQuery(
	        "SELECT c FROM ThoiGianHoatDong c WHERE c.nhanVien = :NhanVienID AND c.ngayDangNhap = :NgayDangNhap AND c.thoiGianDangNhap > :ThoiGianBatDau AND c.thoiGianDangXuat = :ThoiGianKetThuc",
	        ThoiGianHoatDong.class)
	        .setParameter("NhanVienID", em.find(NhanVien.class, tghd.getNhanVien().getMaNV()))
	        .setParameter("NgayDangNhap", tghd.getNgayDangNhap())
	        .setParameter("ThoiGianBatDau", tghd.getThoiGianDangNhap())
	        .setParameter("ThoiGianKetThuc", tghd.getThoiGianDangXuat())
	        .getSingleResult();
	}



	 	
	 
}
