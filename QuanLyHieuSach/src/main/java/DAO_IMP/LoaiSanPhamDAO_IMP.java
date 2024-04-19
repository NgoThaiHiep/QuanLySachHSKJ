package DAO_IMP;

import java.util.ArrayList;

import DAO.LoaiSanPham_DAO;
import entity.LoaiSanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class LoaiSanPhamDAO_IMP implements LoaiSanPham_DAO{
	private EntityManager em;
	private static final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	public LoaiSanPhamDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}
	@Override
	public ArrayList<LoaiSanPham> layDanhLoaiSanPham() {
		// TODO Auto-generated method stub
		return (ArrayList<LoaiSanPham>) em.createQuery("select lsp from LoaiSanPham lsp", LoaiSanPham.class)
                .getResultList();
	}

}
