package DAO_IMP;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import DAO.HoaDon_DAO;
import entity.HoaDon;
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class HoaDonDAO_IMP implements HoaDon_DAO{

	private EntityManager em;
	private static final String PERISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	public HoaDonDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERISTENCE_UNIT_NAME).createEntityManager();
	}
	@Override
	public String generateHoaDon(NhanVien nv) {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		SimpleDateFormat formatterDay = new SimpleDateFormat("ddMMyy");
		String formattedDate = "";  
	        for (int i = 1; i < 9999; i++) {
	            // Định dạng số thành chuỗi và thêm các số 0 bổ sung để đảm bảo đủ 5 chữ số
	            formattedDate =  formatterDay.format(currentDate) + nv.getMaNV().substring(2, 6)+  String.format("%04d", i);
	            
	            if(!kiemTraMaHoaDon(formattedDate)){
	                break;
	            }
	            }  
	        return formattedDate;
	}

	@Override
	public boolean kiemTraMaHoaDon(String code) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from HoaDon c where c.maHoaDon = :code", HoaDon.class)
                .setParameter("code", code)
                .getResultList().size() > 0;
	}

	@Override
	public boolean InsertHoaDon(HoaDon hd) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
            tx.begin();
            em.persist(hd);
            tx.commit();
            return true;
        } catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
        }
		return false;
	}

	
	@Override
	public ArrayList<HoaDon> layDanhSachHoaDon() {
		// TODO Auto-generated method stub
		return (ArrayList<HoaDon> )em.createQuery("select c from HoaDon c", HoaDon.class)
                .getResultList();
	}

	@Override
	public ArrayList<HoaDon> layDanhSachHoaDonTheoNgay(LocalDate ngayChon) {
		// TODO Auto-generated method stub
		return (ArrayList<HoaDon>) em.createQuery("select c from HoaDon c where c.ngayLap = :ngay", HoaDon.class)
                .setParameter("ngay", ngayChon)
                .getResultList();
	}

	@Override
	public ArrayList<HoaDon> layDanhSachHoaDonTheoThang(LocalDate ngayChon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HoaDon> layDanhSachHoaDonTheoNam(LocalDate ngayChon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HoaDon> layDanhSachHoaDonTheoNhanVien(NhanVien nv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HoaDon> layDanhSachHoaDonTheoKhachHang(String maKH) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateHoaDon(HoaDon hd) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(hd);
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
	public boolean deleteHoaDon(HoaDon hd) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
            tx.begin();
            em.remove(em.contains(hd) ? hd : em.merge(hd));
            tx.commit();
            return true;
        } catch (Exception e) {
			
			tx.rollback();
			e.printStackTrace();
        }
		return false;
	}
	@Override
	public ArrayList<HoaDon> layDanhSachHoaDonTheoNgayVaNhanVien(NhanVien nv, LocalDate ngayChon) {
		// TODO Auto-generated method stub
		return (ArrayList<HoaDon>) em.createQuery("select c from HoaDon c where c.ngayLap = :ngay and c.nhanVien = :nv", HoaDon.class)
                .setParameter("ngay", ngayChon)
                .setParameter("nv", nv)
                .getResultList();
	}
	@Override
	public HoaDon layHoaDon(String ma, LocalDate ngay) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from HoaDon c where c.maHoaDon = :ma and c.ngayLap = :ngay", HoaDon.class)
                .setParameter("ma", ma)
                .setParameter("ngay", ngay)
                .getSingleResult();
	}
	

}
