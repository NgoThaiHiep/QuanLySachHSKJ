package DAO_IMP;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import DAO.Sach_DAO;
import entity.Sach;
import entity.SanPham;
import entity.TacGia;
import entity.TheLoai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class SachDAO_IMP implements Sach_DAO{
	private static final String PERSISTENCE_UNIT_NAME = "QuanLyHieuSach MSSQL";
	private static EntityManager em;
	
	public SachDAO_IMP() {
		// TODO Auto-generated constructor stub
	em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
			.createEntityManager();
	}
	
	
	@Override
	public Sach layThongTinSach(String tenSach) {
		// TODO Auto-generated method stub
		return em.find(Sach.class, tenSach);
	}

	@Override
	public boolean insertSach(Sach s) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(s);
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
	public boolean updateSach(Sach s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSach(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String generateMaSach() {
		// TODO Auto-generated method stub
		String number = "";
		 for (int i = 1; i < 99999; i++) {
	            // Định dạng số thành chuỗi và thêm các số 0 bổ sung để đảm bảo đủ 5 chữ số
	            number = "Sach"+String.format("%05d", i);
	            
	            if(!kiemTraMaSach(number)){
	                break;
	            }
	            }  
	        return number;
		
	
	}

	@Override
	public boolean kiemTraMaSach(String code) {
		// TODO Auto-generated method stub
		
		return em.createQuery("select c from Sach c where c.maSanPham = :code", Sach.class)
                .setParameter("code", code)
                .getResultList().size() > 0;
	}


	@Override
	public boolean updateSachNgonNguMoTa(String maSach, String ngonNgu, String moTa) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Sach s = em.find(Sach.class, maSach);
			SanPham sp = em.find(SanPham.class, maSach);
			
			s.setNgonNgu(ngonNgu);
			sp.setMoTa(moTa);
			em.merge(s);
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
	public List<Sach> layDanhSachSanPhamSach() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Sach> layDanhSachTheoMaSach(String maSach) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean insertGroupTheLoai(String maSach, List<Object> selectedItems) {
	    EntityTransaction tx = em.getTransaction();
	    try {
	        tx.begin();
	        Sach s = em.find(Sach.class, maSach);

	        if (s == null) {
	            // Handle case where book is not found
	            return false;
	        }

	        // Fetch the categories based on their IDs
	        List<TheLoai> theLoaiList = new ArrayList<>();
	        for (Object selectedItem : selectedItems) {
	           { // Replace YourItemType with the actual type of items in cboTheLoai
	               System.out.println("Item Gruop: "+selectedItem.toString());
					TheLoai tl = em
							.createQuery("select tl from TheLoai tl where tl.tenTheLoai = :tenTheLoai", TheLoai.class)
							.setParameter("tenTheLoai", selectedItem.toString())
							.getSingleResult();
	                // Assuming getId() returns the ID of the item
	                System.out.println("Thể loại Group ; "+tl);
	                if (tl != null) {
	                    theLoaiList.add(tl);
	                }
	            }
	        }
	        System.out.println("Danh sách thể loại : "+theLoaiList);
	        // Associate the book with the categories
	        s.setTheLoai(new HashSet<>(theLoaiList));

	        em.persist(s);
	        tx.commit();
	        return true;
	    } catch (Exception e) {
	        if (tx.isActive()) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean insertGroupTacGia(String maSach, List<Object> selectedItems) {
			// TODO Auto-generated method stub
			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
				Sach s = em.find(Sach.class, maSach);
				if (s == null) {
					// Handle case where book is not found
					return false;
				}
				// Fetch the authors based on their IDs
				List<TacGia> tacGiaList = new ArrayList<>();
				for (Object selectedItem : selectedItems) {
					{ // Replace YourItemType with the actual type of items in cboTheLoai
						System.out.println("Item Gruop: " + selectedItem.toString());
						TacGia tg = em
								.createQuery("select tg from TacGia tg where tg.tenTacGia = :tenTacGia", TacGia.class)
								.setParameter("tenTacGia", selectedItem.toString()).getSingleResult();
						// Assuming getId() returns the ID of the item
						System.out.println("Tác giả Group ; " + tg);
						if (tg != null) {
							tacGiaList.add(tg);
						}
					}
				}
				System.out.println("Danh sách tác giả : " + tacGiaList);
				// Associate the book with the authors
				s.setTacGia(new HashSet<>(tacGiaList));
				em.persist(s);
				tx.commit();
				return true;
				
			} catch (Exception e) {
				// TODO: handle exception
				if (tx.isActive()) {
					tx.rollback();
				}
				e.printStackTrace();
				return false;
			}
	}

	


}
