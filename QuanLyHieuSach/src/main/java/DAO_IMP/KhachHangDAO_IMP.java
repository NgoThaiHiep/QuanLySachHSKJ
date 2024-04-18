package DAO_IMP;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Random;

import DAO.KhachHang_DAO;
import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class KhachHangDAO_IMP implements KhachHang_DAO{
	private EntityManager em;
	private final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	
	public KhachHangDAO_IMP() {
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
		
	}
	
	@Override
	public KhachHang layThongTinKhachHang(String sdt) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			KhachHang kh = em.createQuery("select c from KhachHang c where c.soDienThoai = :sdt", KhachHang.class)
					.setParameter("sdt", sdt).getSingleResult();
			tx.commit();
			return kh;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}
		return null;
	}

	@Override
	public KhachHang layThongTinKhachHang_TheoMaKH(String maKH) {
		// TODO Auto-generated method stub
		return em.find(KhachHang.class, maKH);
	}

	@Override
	public String generateVerifyCodeKH() {
		// TODO Auto-generated method stub
		DecimalFormat df = new DecimalFormat("0000000000");
	    Random ran = new Random();

	    // Lấy hai số cuối cùng của năm
	    Calendar calendar = Calendar.getInstance();
	    int year = calendar.get(Calendar.YEAR) % 100; // Lấy 2 số cuối của năm
	    // Lấy 2 số của tháng
	    int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, cần cộng thêm 1
	    String code;
	    do {
	        code = df.format(month * 100000000 + year * 1000000 + ran.nextInt(100000));
	    } while (checkDuplicateCode(code));
	    return code;
	}

	@Override
	public String generateVerifyCode_KhachHangLe() {
		// TODO Auto-generated method stub
		DecimalFormat df = new DecimalFormat("0000000000");
	    Random ran = new Random();

	    // Lấy hai số cuối cùng của năm
	    Calendar calendar = Calendar.getInstance();
	    int year = calendar.get(Calendar.YEAR) % 100; // Lấy 2 số cuối của năm
	    // Lấy 2 số của tháng
	    int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, cần cộng thêm 1
	    String code;
	    do {
	        code = "KHL"+df.format(month * 100000000 + year * 1000000 + ran.nextInt(100000));
	    } while (checkDuplicateCode(code));
	    return code;
	}

	@Override
	public boolean checkDuplicateCode(String code) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from KhachHang c where c.maKhachHang= :code", KhachHang.class)
                .setParameter("code", code)
                .getResultList().size() > 0;
	}

	@Override
	public boolean InsertKhachHang(KhachHang kh) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(kh);
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
	public boolean updateDiemTichLuy(KhachHang kh) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(kh);
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
