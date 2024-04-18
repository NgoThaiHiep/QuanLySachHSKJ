package DAO_IMP;

import java.util.ArrayList;

import DAO.HangCho_DAO;
import entity.HangCho;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class HangChoDAO_IMP implements HangCho_DAO{

	
	private EntityManager em;
	private static final String PERISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	public HangChoDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERISTENCE_UNIT_NAME).createEntityManager();
		
	}
	@Override
	public ArrayList<HangCho> layDanhSachHangCho() {
		// TODO Auto-generated method stub
		return (ArrayList<HangCho>)em.createQuery("select c from HangCho c", HangCho.class)
				.getResultList();
	}

	@Override
	public ArrayList<HangCho> layDanhSachHangChoTheoMaKhachHang(String maKhachHang) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean InsertHangCho(HangCho hangCho) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void DeleteDanhSachHangCho(String maKhachHang) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DeleteHangChoQuaNgay() {
		// TODO Auto-generated method stub
		
	}

}
