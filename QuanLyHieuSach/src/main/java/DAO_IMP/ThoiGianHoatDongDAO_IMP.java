package DAO_IMP;


import java.time.LocalTime;

import DAO.ThoiGianHoatDong_DAO;
import entity.NhanVien;
import entity.ThoiGianHoatDong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;



public class ThoiGianHoatDongDAO_IMP implements ThoiGianHoatDong_DAO {
	private EntityManager em;
	private static final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	public ThoiGianHoatDongDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}
	
	@Override
	public boolean kiemTraDangNhapTrongNgay(ThoiGianHoatDong tghd, LocalTime thoiGianBatDauCa, LocalTime thoiGianKetThucCa) {
		 boolean result =  em.createQuery(
	            "SELECT c FROM ThoiGianHoatDong c WHERE c.nhanVien = :NhanVien AND c.ngayDangNhap = :NgayDangNhap "
	            + "AND CAST(:ThoiGianBatDau AS time) <= CAST(c.thoiGianDangNhap AS time) "
	            + "AND CAST(:ThoiGianKetThuc AS time) >= CAST(c.thoiGianDangNhap AS time)"
	            ,
	            ThoiGianHoatDong.class)
	            .setParameter("NhanVien", em.find(NhanVien.class, tghd.getNhanVien().getMaNV()))
	            .setParameter("NgayDangNhap", tghd.getNgayDangNhap())
	            .setParameter("ThoiGianBatDau", java.sql.Time.valueOf(thoiGianBatDauCa))
	            .setParameter("ThoiGianKetThuc", java.sql.Time.valueOf(thoiGianKetThucCa))
	            .getResultList().size() > 0;
	    if (result) {
          ThoiGianHoatDong td =   em.createQuery ("SELECT c FROM ThoiGianHoatDong c WHERE c.nhanVien = :NhanVien AND c.ngayDangNhap = :NgayDangNhap "
   	            + "AND CAST(:ThoiGianBatDau AS time) <= CAST(c.thoiGianDangNhap AS time) "
   	            + "AND CAST(:ThoiGianKetThuc AS time) >= CAST(c.thoiGianDangNhap AS time)"
   	            ,
   	            ThoiGianHoatDong.class)
   	            .setParameter("NhanVien", em.find(NhanVien.class, tghd.getNhanVien().getMaNV()))
   	            .setParameter("NgayDangNhap", tghd.getNgayDangNhap())
   	            .setParameter("ThoiGianBatDau", java.sql.Time.valueOf(thoiGianBatDauCa))
   	            .setParameter("ThoiGianKetThuc", java.sql.Time.valueOf(thoiGianKetThucCa))
   	            .getSingleResult();
           tghd.setMaThoiGian(td.getMaThoiGian());
           tghd.setThoiGianDaLam(td.getThoiGianDaLam());
        }
	    return result;
	}


	
		

	@Override
	public boolean capNhatThoiGianLam(ThoiGianHoatDong tghd) {
		// TODO Auto-generated method stub
//		UPDATE [dbo].[ThoiGianHoatDong] SET [ThoiGianDaLam] = ?,[ThoiGianDangXuat] = ? WHERE [ThoiGianHoatDongID] = ?
		EntityTransaction tx = em.getTransaction();
		try {
            tx.begin();
            em.createQuery(
                    "UPDATE ThoiGianHoatDong SET thoiGianDaLam = :ThoiGianDaLam,"
                    + " thoiGianDangXuat = :ThoiGianDangXuat "
                    + "WHERE maThoiGian = :MaThoiGian")
                    .setParameter("ThoiGianDaLam", tghd.getThoiGianDaLam())
                    .setParameter("ThoiGianDangXuat", tghd.getThoiGianDangXuat())
                    .setParameter("MaThoiGian", tghd.getMaThoiGian())
                    .executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
        	
			e.printStackTrace();
			tx.rollback();
		}
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
