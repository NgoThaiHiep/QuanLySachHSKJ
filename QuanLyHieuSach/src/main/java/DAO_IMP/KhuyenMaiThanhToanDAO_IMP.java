package DAO_IMP;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import DAO.KhuyenMaiThanhToan_DAO;
import entity.KhuyenMaiThanhToan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class KhuyenMaiThanhToanDAO_IMP implements KhuyenMaiThanhToan_DAO
{
	private EntityManager em;
	private static final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	public KhuyenMaiThanhToanDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	
	}
	@Override
	public ArrayList<KhuyenMaiThanhToan> layDanhSachKhuyenMai_TyLe() {
		// TODO Auto-generated method stub
//		"select * from KhuyenMaiThanhToan where loai = 1"
	 EntityTransaction	tx = em.getTransaction();
		try {
			tx.begin();
			ArrayList<KhuyenMaiThanhToan> list = 
					(ArrayList<KhuyenMaiThanhToan>) 
					em.createQuery("select k from KhuyenMaiThanhToan k where k.loai = 1",KhuyenMaiThanhToan.class)
						.getResultList();;
			tx.commit();
			return list;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String generateMaKhuyenMai_GiaTri() {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		SimpleDateFormat formatterDay = new SimpleDateFormat("ddMMyy");
		String formattedDate = "";
	        for (int i = 1; i < 99; i++) {
	            // Định dạng số thành chuỗi và thêm các số 0 bổ sung để đảm bảo đủ 5 chữ số
	            formattedDate = "KMGT"+ formatterDay.format(currentDate)+ String.format("%02d", i);
	            if(!kiemTraMaKhuyenMai_GiaTri(formattedDate)){
	                break;
	            }
	            }  
	        return formattedDate;
	}

	@Override
	public String generateMaKhuyenMai_TyLe() {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		SimpleDateFormat formatterDay = new SimpleDateFormat("ddMMyy");
		String formattedDate = "";
	        for (int i = 1; i < 99; i++) {
	            // Định dạng số thành chuỗi và thêm các số 0 bổ sung để đảm bảo đủ 5 chữ số
	            formattedDate = "KMTL"+ formatterDay.format(currentDate)+ String.format("%02d", i);
	            if(!kiemTraMaKhuyenMai_GiaTri(formattedDate)){
	                break;
	            }
	            }  
	        return formattedDate;
	}

	@Override
	public boolean kiemTraMaKhuyenMai_GiaTri(String code) {
		// TODO Auto-generated method stub
//		"select MaKhuyenMai from KhuyenMaiThanhToan where MaKhuyenMai = ?"
		return em.createQuery("select k from KhuyenMaiThanhToan k where k.maKhuyenMai = ?")
				.setParameter(1, code)
				.getResultList().size() > 0;
	}

	@Override
	public ArrayList<KhuyenMaiThanhToan> layDanhSachKhuyenMai_GiaTien() {
		// TODO Auto-generated method stub
//		select * from KhuyenMaiThanhToan where loai = 2
		
		return (ArrayList<KhuyenMaiThanhToan>) em.createQuery("select k from KhuyenMaiThanhToan k where k.loai = 2", KhuyenMaiThanhToan.class)
				.getResultList();
	}

	@Override
	public boolean themGiamGiaSanPham_TyLe(KhuyenMaiThanhToan khuyenMaiThanhToan) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(khuyenMaiThanhToan);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean capNhatTyLe_KhuyenMai(KhuyenMaiThanhToan khuyenMaiThanhToan) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(khuyenMaiThanhToan);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean capNhatTyLe_GiaTien(KhuyenMaiThanhToan khuyenMaiThanhToan) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(khuyenMaiThanhToan);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean themGiamGiaSanPham_GiaTien(KhuyenMaiThanhToan khuyenMaiThanhToan) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(khuyenMaiThanhToan);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update_tinhTrang(String ma, String tinhTrang) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Query query = em.createQuery("update KhuyenMaiThanhToan k set k.tinhTrang = ? where k.maKhuyenMai = ?");
			query.setParameter(1, tinhTrang);
			query.setParameter(2, ma);
			query.executeUpdate();
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateSoLuongKhuyenMai(String ma, int soLuong) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Query query = em.createQuery("update KhuyenMaiThanhToan k set k.soLuong = ? where k.maKhuyenMai = ?");
			query.setParameter(1, soLuong);
			query.setParameter(2, ma);
			query.executeUpdate();
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

}
