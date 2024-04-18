package DAO;


import java.time.LocalTime;

import entity.ThoiGianHoatDong;

public interface ThoiGianHoatDong_DAO {
	public boolean kiemTraDangNhapTrongNgay(ThoiGianHoatDong tghd,LocalTime thoiGianBatDauCa, LocalTime thoiGianKetThucCa);
	public ThoiGianHoatDong layThoiGianHoatDong(ThoiGianHoatDong tghd);
	public boolean capNhatThoiGianLam(ThoiGianHoatDong tghd);
	public boolean insertThoiGianLam(ThoiGianHoatDong tghd);
}
