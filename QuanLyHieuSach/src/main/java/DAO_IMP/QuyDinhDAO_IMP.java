package DAO_IMP;

import java.util.List;

import DAO.QuyDinh_DAO;
import entity.QuyDinh;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class QuyDinhDAO_IMP implements QuyDinh_DAO {
	
	private static final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	private EntityManager em;
	
	public QuyDinhDAO_IMP() {
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}

	
	@Override
	public List<QuyDinh> layDuLieuQuyDinh() {
		return em.createNamedQuery("QuyDinh.findAll", QuyDinh.class)
				.getResultList();
	}

	@Override
	public void capNhatQuyDinh(QuyDinh quyDinh) {
		em.getTransaction().begin();
		em.merge(quyDinh);
		em.getTransaction().commit();
	}

	@Override
	public boolean InsertQuyDinh(int soLuongToiThieu, int soLuongToiDa, float VAT) {
		QuyDinh qd = new QuyDinh(soLuongToiThieu, soLuongToiDa, VAT);
		em.getTransaction().begin();
		em.persist(qd);
		em.getTransaction().commit();
		return true;
	}
	
}