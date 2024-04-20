package DAO_IMP;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import DAO.GiamGiaSanPham_DAO;
import entity.GiamGiaSanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class GiamGiaSanPhamDAO_IMP implements GiamGiaSanPham_DAO{
	private EntityManager em;
	private static final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";

	public GiamGiaSanPhamDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();

	}

	@Override
	public ArrayList<GiamGiaSanPham> layDanhSachGiamGiaSanPham() {
		// TODO Auto-generated method stub
		return ( ArrayList<GiamGiaSanPham> )em.createQuery("select g from GiamGiaSanPham g",GiamGiaSanPham.class)
				.getResultList();
	}

	@Override
	public ArrayList<GiamGiaSanPham> layDanhSachGiamGiaSanPham_GiaTien(int loai) {
		// TODO Auto-generated method stub
		return( ArrayList<GiamGiaSanPham> ) em.createQuery("select g from GiamGiaSanPham g where g.loai = :loai", GiamGiaSanPham.class)
				.setParameter("loai", loai)
				.getResultList();
	}

	@Override
	public ArrayList<GiamGiaSanPham> layDanhSachGiamGiaSanPham_TyLe(int loai) {
		// TODO Auto-generated method stub
		return ( ArrayList<GiamGiaSanPham> )
				em.createQuery("select g from GiamGiaSanPham g where g.loai = :loai", GiamGiaSanPham.class)
				.setParameter("loai", loai).getResultList();
		
	}

	@Override
	public String generateGiamGiaSanPham_TyLe() {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		SimpleDateFormat formatterDay = new SimpleDateFormat("ddMMyy");
		String formattedDate = "";
	        for (int i = 1; i < 9999; i++) {
	            // Định dạng số thành chuỗi và thêm các số 0 bổ sung để đảm bảo đủ 5 chữ số
	            formattedDate = "GGSPTL"+ formatterDay.format(currentDate)+ String.format("%04d", i);
	            if(!kiemTraGiamGiaSanPham(formattedDate)){
	                break;
	            }
	            }  
	        return formattedDate;
	}

	@Override
	public String generateGiamGiaSanPham_GiaTien() {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		SimpleDateFormat formatterDay = new SimpleDateFormat("ddMMyy");
		String formattedDate = "";
	        for (int i = 1; i < 9999; i++) {
	            // Định dạng số thành chuỗi và thêm các số 0 bổ sung để đảm bảo đủ 5 chữ số
	            formattedDate = "GGSPGT"+ formatterDay.format(currentDate)+ String.format("%04d", i);
	            if(!kiemTraGiamGiaSanPham(formattedDate)){
	                break;
	            }
	            }  
	        return formattedDate;
	}

	@Override
	public boolean kiemTraGiamGiaSanPham(String code) {
		// TODO Auto-generated method stub
//		"select GiamGiaSanPhamID  from GiamGiaSanPham where GiamGiaSanPhamID = ?
		return em.createQuery("select g from GiamGiaSanPham g where g.maGiamGiaSanPham = ?")
				.setParameter(1, code)
				.getResultList().size() > 0;
	}

	@Override
	public boolean themGiamGiaSanPham_TyLe(GiamGiaSanPham giamGiaSanPham) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			GiamGiaSanPham giamGia = new GiamGiaSanPham();
			giamGia.setMaGiamGiaSanPham(giamGiaSanPham.getMaGiamGiaSanPham());
			giamGia.setTenGiamGia(giamGiaSanPham.getTenGiamGia());
			giamGia.setSanPham(giamGiaSanPham.getSanPham());
			giamGia.setNgayBatDau(giamGiaSanPham.getNgayBatDau());
			giamGia.setNgayKetThuc(giamGiaSanPham.getNgayKetThuc());
			giamGia.setTinhTrang(giamGiaSanPham.getTinhTrang());
			giamGia.setChiTiet(giamGiaSanPham.getChiTiet());
			giamGia.setLoai(giamGiaSanPham.getLoai());
			giamGia.setTyLeGiam(giamGiaSanPham.getTyLeGiam());
			em.persist(giamGia);
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
	public boolean themGiamGiaSanPham_GiaTien(GiamGiaSanPham giamGiaSanPham) {
		// TODO Auto-generated method stub
//		state.setString(1, giamGiaSanPham.getMaGiamGiaSanPham());
//      state.setString(2, giamGiaSanPham.getTenGiamGia());
//      state.setString(3, giamGiaSanPham.getSanPham().getMaSanPham());
//      state.setString(4,formatter.format(giamGiaSanPham.getNgayBatDau()));
//      state.setString(5,formatter.format(giamGiaSanPham.getNgayKetThuc()));   
//      state.setString(6, giamGiaSanPham.getTinhTrang());
//      state.setString(7, giamGiaSanPham.getChiTiet());
//      state.setInt(8, giamGiaSanPham.getLoai());
//      state.setFloat(9, giamGiaSanPham.getTyLeGiam());
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			GiamGiaSanPham giamGia = new GiamGiaSanPham();
			giamGia.setMaGiamGiaSanPham(giamGiaSanPham.getMaGiamGiaSanPham());
			giamGia.setTenGiamGia(giamGiaSanPham.getTenGiamGia());
			giamGia.setSanPham(giamGiaSanPham.getSanPham());
			giamGia.setSoTienGiam(giamGiaSanPham.getSoTienGiam());
			giamGia.setNgayBatDau(giamGiaSanPham.getNgayBatDau());
			giamGia.setNgayKetThuc(giamGiaSanPham.getNgayKetThuc());
			giamGia.setTinhTrang(giamGiaSanPham.getTinhTrang());
			giamGia.setChiTiet(giamGiaSanPham.getChiTiet());
			giamGia.setLoai(giamGiaSanPham.getLoai());
			em.persist(giamGia);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateGiamGiaSanPham_GiaTien(GiamGiaSanPham giamGiaSanPham) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			GiamGiaSanPham giamGia = new GiamGiaSanPham();
			giamGia.setMaGiamGiaSanPham(giamGiaSanPham.getMaGiamGiaSanPham());
			giamGia.setTenGiamGia(giamGiaSanPham.getTenGiamGia());
			giamGia.setSanPham(giamGiaSanPham.getSanPham());
			giamGia.setSoTienGiam(giamGiaSanPham.getSoTienGiam());
			giamGia.setNgayBatDau(giamGiaSanPham.getNgayBatDau());
			giamGia.setNgayKetThuc(giamGiaSanPham.getNgayKetThuc());
			giamGia.setTinhTrang(giamGiaSanPham.getTinhTrang());
			giamGia.setChiTiet(giamGiaSanPham.getChiTiet());
			em.merge(giamGiaSanPham);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateGiamGiaSanPham_TyLe(GiamGiaSanPham giamGiaSanPham) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			GiamGiaSanPham giamGia = new GiamGiaSanPham();
			giamGia.setMaGiamGiaSanPham(giamGiaSanPham.getMaGiamGiaSanPham());
			giamGia.setTenGiamGia(giamGiaSanPham.getTenGiamGia());
			giamGia.setSanPham(giamGiaSanPham.getSanPham());
			giamGia.setNgayBatDau(giamGiaSanPham.getNgayBatDau());
			giamGia.setNgayKetThuc(giamGiaSanPham.getNgayKetThuc());
			giamGia.setTinhTrang(giamGiaSanPham.getTinhTrang());
			giamGia.setChiTiet(giamGiaSanPham.getChiTiet());
			giamGia.setTyLeGiam(giamGiaSanPham.getTyLeGiam());
			em.merge(giamGiaSanPham);
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
	public boolean update_tinhTrang(String ma, String tinhTrang) {
		// TODO Auto-generated method stub
//		 "UPDATE [dbo].[GiamGiaSanPham]\n" +
//         "SET  [TinhTrang] = ?\n" +
//         "WHERE [GiamGiaSanPhamID] = ?";
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			GiamGiaSanPham giamGiaSanPham = em.find(GiamGiaSanPham.class, ma);
			giamGiaSanPham.setTinhTrang(tinhTrang);
			em.merge(giamGiaSanPham);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}
}
