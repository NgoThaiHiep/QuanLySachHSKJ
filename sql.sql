USE [master]
GO
/****** Object:  Database [quanlysach]    Script Date: 4/21/2024 11:38:42 PM ******/
CREATE DATABASE [quanlysach]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'quanlysach', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\quanlysach.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'quanlysach_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\quanlysach_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [quanlysach] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [quanlysach].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [quanlysach] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [quanlysach] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [quanlysach] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [quanlysach] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [quanlysach] SET ARITHABORT OFF 
GO
ALTER DATABASE [quanlysach] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [quanlysach] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [quanlysach] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [quanlysach] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [quanlysach] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [quanlysach] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [quanlysach] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [quanlysach] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [quanlysach] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [quanlysach] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [quanlysach] SET  DISABLE_BROKER 
GO
ALTER DATABASE [quanlysach] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [quanlysach] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [quanlysach] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [quanlysach] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [quanlysach] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [quanlysach] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [quanlysach] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [quanlysach] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [quanlysach] SET  MULTI_USER 
GO
ALTER DATABASE [quanlysach] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [quanlysach] SET DB_CHAINING OFF 
GO
ALTER DATABASE [quanlysach] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [quanlysach] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [quanlysach]
GO
/****** Object:  Table [dbo].[CaLamViec]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CaLamViec](
	[MaCa] [varchar](255) NOT NULL,
	[TenCa] [nvarchar](255) NULL,
	[thoiGianBatDau] [time](7) NULL,
	[thoiGianKetThuc] [time](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaCa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ChiTietHoaDon](
	[donGia] [float] NOT NULL,
	[soLuong] [int] NOT NULL,
	[MaHoaDon] [varchar](255) NOT NULL,
	[MaSP] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHoaDon] ASC,
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ChucVu]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ChucVu](
	[MaChucVu] [varchar](255) NOT NULL,
	[TenChucVu] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaChucVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[GiamGiaSanPham]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[GiamGiaSanPham](
	[MaGiamGiaSanPham] [varchar](255) NOT NULL,
	[chiTiet] [nvarchar](255) NULL,
	[loai] [int] NOT NULL,
	[ngayBatDau] [date] NULL,
	[ngayKetThuc] [date] NULL,
	[soTienGiam] [real] NOT NULL,
	[tenGiamGia] [nvarchar](255) NULL,
	[tinhTrang] [nvarchar](255) NULL,
	[tyLeGiam] [real] NOT NULL,
	[MaLoaiSanPham] [varchar](255) NULL,
	[MaSP] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaGiamGiaSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[HangCho]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[HangCho](
	[ngayMua] [date] NULL,
	[soLuong] [int] NOT NULL,
	[MaSP] [varchar](255) NOT NULL,
	[MaKH] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaKH] ASC,
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[HoaDon](
	[MaHoaDon] [varchar](255) NOT NULL,
	[ngayLap] [date] NULL,
	[soTienKhachDua] [float] NOT NULL,
	[tienBanDau] [float] NOT NULL,
	[tienThua] [float] NOT NULL,
	[tongTien] [float] NOT NULL,
	[vat] [float] NOT NULL,
	[MaKH] [varchar](255) NULL,
	[MaNV] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[KhachHang](
	[MaKhachHang] [varchar](255) NOT NULL,
	[diaChi] [nvarchar](255) NULL,
	[diemTL] [int] NOT NULL,
	[soDienThoai] [varchar](255) NULL,
	[tenKhachHang] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[KhuyenMaiThanhToan]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[KhuyenMaiThanhToan](
	[MaKhuyenMai] [varchar](255) NOT NULL,
	[GiamToiDa] [real] NOT NULL,
	[PhanTramGiam] [real] NOT NULL,
	[ChiTiet] [nvarchar](255) NULL,
	[giaTriToiThieuDonHang] [real] NOT NULL,
	[loai] [int] NOT NULL,
	[ngayBatDau] [date] NULL,
	[ngayKetThuc] [date] NULL,
	[soLuong] [int] NOT NULL,
	[soTienGiam] [real] NOT NULL,
	[TenKhuyenMai] [nvarchar](255) NULL,
	[TinhTrang] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaKhuyenMai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[LoaiSanPham]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[LoaiSanPham](
	[MaLoaiSanPham] [varchar](255) NOT NULL,
	[TenLoaiSanPham] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaLoaiSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[LoaiVanPhongPham]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[LoaiVanPhongPham](
	[MaLoaiVanPhongPham] [varchar](255) NOT NULL,
	[TenLoaiVanPhongPham] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaLoaiVanPhongPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[MaXacNhan]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[MaXacNhan](
	[email] [varchar](255) NULL,
	[maXacNhan] [varchar](255) NULL,
	[thoiGianTao] [datetime2](6) NULL,
	[TaiKhoan] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TaiKhoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NhaCungCap]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NhaCungCap](
	[MaNCC] [varchar](255) NOT NULL,
	[DiaChiNCC] [nvarchar](255) NULL,
	[email] [varchar](255) NULL,
	[SanPhamCungCap] [nvarchar](255) NULL,
	[soDienThoai] [varchar](255) NULL,
	[TenNCC] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNCC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNV] [varchar](255) NOT NULL,
	[CCCD] [varchar](255) NULL,
	[diaChi] [nvarchar](255) NULL,
	[email] [varchar](255) NULL,
	[gioiTinh] [nvarchar](255) NULL,
	[hinhAnh] [varchar](255) NULL,
	[hoTenNhanVien] [nvarchar](255) NULL,
	[soDienThoai] [varchar](255) NULL,
	[trangThai] [nvarchar](255) NULL,
	[MaChucVu] [varchar](255) NULL,
	[ngaySinh] [date] NULL,
	[MaCa] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NhaXuatBan]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NhaXuatBan](
	[MaNhaXuatBan] [varchar](255) NOT NULL,
	[DiaChi] [nvarchar](255) NULL,
	[email] [varchar](255) NULL,
	[soDienThoai] [varchar](255) NULL,
	[TenNhaXuatBan] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNhaXuatBan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[QuyDinh]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QuyDinh](
	[SoLuongToiThieu] [int] NOT NULL,
	[VAT] [real] NOT NULL,
	[soLuongToiDa] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[SoLuongToiThieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Sach]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Sach](
	[namXuatBan] [int] NOT NULL,
	[NgonNgu] [nvarchar](255) NULL,
	[soTrang] [int] NOT NULL,
	[MaSanPham] [varchar](255) NOT NULL,
	[NhaXuatBan] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Sach_TacGia]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Sach_TacGia](
	[SachID] [varchar](255) NOT NULL,
	[TacGiaID] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[SachID] ASC,
	[TacGiaID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Sach_TheLoai]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Sach_TheLoai](
	[SachID] [varchar](255) NOT NULL,
	[TheLoaiID] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[SachID] ASC,
	[TheLoaiID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[SanPham](
	[MaSanPham] [varchar](255) NOT NULL,
	[donGia] [float] NOT NULL,
	[hinhAnh] [varchar](255) NULL,
	[moTa] [nvarchar](255) NULL,
	[soLuongTon] [int] NOT NULL,
	[tenSanPham] [varchar](255) NULL,
	[tinhTrang] [nvarchar](255) NULL,
	[LoaiSanPham] [varchar](255) NULL,
	[MaNCC] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TacGia]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TacGia](
	[MaTacGia] [varchar](255) NOT NULL,
	[TenTacGia] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaTacGia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[matKhau] [varchar](255) NULL,
	[trangThai] [nvarchar](255) NULL,
	[TaiKhoan] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TaiKhoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TheLoai]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TheLoai](
	[MaTheLoai] [varchar](255) NOT NULL,
	[TenTheLoai] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaTheLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ThoiGianHoatDong]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ThoiGianHoatDong](
	[MaThoiGian] [varchar](255) NOT NULL,
	[ngayDangNhap] [date] NULL,
	[thoiGianDaLam] [time](7) NULL,
	[thoiGianDangNhap] [time](7) NULL,
	[thoiGianDangXuat] [time](7) NULL,
	[MaNV] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaThoiGian] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Thue]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Thue](
	[ID] [varchar](255) NOT NULL,
	[thue] [real] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ThuongHieu]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ThuongHieu](
	[MaThuongHieu] [varchar](255) NOT NULL,
	[TenThuongHieu] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaThuongHieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[VanPhongPham]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[VanPhongPham](
	[chatLieu] [nvarchar](255) NULL,
	[namSanXuat] [int] NOT NULL,
	[MaSanPham] [varchar](255) NOT NULL,
	[MaLoaiVanPhongPham] [varchar](255) NULL,
	[MaThuongHieu] [varchar](255) NULL,
	[MaXuatXu] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[XuatXu]    Script Date: 4/21/2024 11:38:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[XuatXu](
	[MaXuatXu] [varchar](255) NOT NULL,
	[TenQuocGia] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaXuatXu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[ChiTietHoaDon] ([donGia], [soLuong], [MaHoaDon], [MaSP]) VALUES (10000, 30, N'HD00001', N'Sach00001')
INSERT [dbo].[ChiTietHoaDon] ([donGia], [soLuong], [MaHoaDon], [MaSP]) VALUES (10000, 30, N'HD00001', N'VP00001')
INSERT [dbo].[ChucVu] ([MaChucVu], [TenChucVu]) VALUES (N'QL', N'Quản lý')
INSERT [dbo].[HoaDon] ([MaHoaDon], [ngayLap], [soTienKhachDua], [tienBanDau], [tienThua], [tongTien], [vat], [MaKH], [MaNV]) VALUES (N'21042408180001', CAST(N'2024-04-21' AS Date), 501000, 500002, 998, 500002, 0, N'KHL0424034910', N'21081841')
INSERT [dbo].[HoaDon] ([MaHoaDon], [ngayLap], [soTienKhachDua], [tienBanDau], [tienThua], [tongTien], [vat], [MaKH], [MaNV]) VALUES (N'HD00001', CAST(N'2024-04-18' AS Date), 100000, 90000, 90000, 10000, 10, N'KH00001', N'21081841')
INSERT [dbo].[KhachHang] ([MaKhachHang], [diaChi], [diemTL], [soDienThoai], [tenKhachHang]) VALUES (N'KH00001', N'a', 120, N'1234567890', N'Ngô Thái Hiệp')
INSERT [dbo].[KhachHang] ([MaKhachHang], [diaChi], [diemTL], [soDienThoai], [tenKhachHang]) VALUES (N'KHL0424024543', NULL, 5, NULL, N'No name')
INSERT [dbo].[KhachHang] ([MaKhachHang], [diaChi], [diemTL], [soDienThoai], [tenKhachHang]) VALUES (N'KHL0424034910', NULL, 10, NULL, N'No name')
INSERT [dbo].[LoaiSanPham] ([MaLoaiSanPham], [TenLoaiSanPham]) VALUES (N'LSP000001', N'Sách')
INSERT [dbo].[LoaiSanPham] ([MaLoaiSanPham], [TenLoaiSanPham]) VALUES (N'LSP000002', N'Văn phòng phẩm')
INSERT [dbo].[NhaCungCap] ([MaNCC], [DiaChiNCC], [email], [SanPhamCungCap], [soDienThoai], [TenNCC]) VALUES (N'HNBST', NULL, NULL, N'Sách', NULL, N'Hà Nội Bookstore')
INSERT [dbo].[NhaCungCap] ([MaNCC], [DiaChiNCC], [email], [SanPhamCungCap], [soDienThoai], [TenNCC]) VALUES (N'NCC1', N'Tỉnh Quảng Ninh-Thị xã Quảng Yên-Phường Quảng Yên', N'0788455884', N'Văn phòng phẩm', N'0788455884', N'Thăng Long')
INSERT [dbo].[NhaCungCap] ([MaNCC], [DiaChiNCC], [email], [SanPhamCungCap], [soDienThoai], [TenNCC]) VALUES (N'NSPN', NULL, NULL, N'Sách', NULL, N'Nhà sách Phương Nam')
INSERT [dbo].[NhaCungCap] ([MaNCC], [DiaChiNCC], [email], [SanPhamCungCap], [soDienThoai], [TenNCC]) VALUES (N'OM', NULL, NULL, N'Văn phòng phẩm', NULL, N'OfficeMate')
INSERT [dbo].[NhaCungCap] ([MaNCC], [DiaChiNCC], [email], [SanPhamCungCap], [soDienThoai], [TenNCC]) VALUES (N'TGSTN', NULL, NULL, N'Văn phòng phẩm', NULL, N'Thế Giới Sổ Tài Nguyên ')
INSERT [dbo].[NhanVien] ([MaNV], [CCCD], [diaChi], [email], [gioiTinh], [hinhAnh], [hoTenNhanVien], [soDienThoai], [trangThai], [MaChucVu], [ngaySinh], [MaCa]) VALUES (N'21081841', NULL, NULL, NULL, NULL, NULL, N'Ngô Thái Hiệp', NULL, NULL, N'QL', CAST(N'2003-12-28' AS Date), NULL)
INSERT [dbo].[NhaXuatBan] ([MaNhaXuatBan], [DiaChi], [email], [soDienThoai], [TenNhaXuatBan]) VALUES (N'NCC1', N'Thành phố Hà Nội-Quận Ba Đình-Phường Phúc Xá', N'0784584541                                                                                                                                                                                              ', N'0784584541          ', N'TiKi')
INSERT [dbo].[NhaXuatBan] ([MaNhaXuatBan], [DiaChi], [email], [soDienThoai], [TenNhaXuatBan]) VALUES (N'NXBKD', NULL, NULL, NULL, N'Nhà Xuất Bản Kim Đồng')
INSERT [dbo].[NhaXuatBan] ([MaNhaXuatBan], [DiaChi], [email], [soDienThoai], [TenNhaXuatBan]) VALUES (N'NXBT', NULL, NULL, NULL, N'Nhà Xuất Bản Trẻ')
INSERT [dbo].[QuyDinh] ([SoLuongToiThieu], [VAT], [soLuongToiDa]) VALUES (100, 10, 150)
INSERT [dbo].[Sach] ([namXuatBan], [NgonNgu], [soTrang], [MaSanPham], [NhaXuatBan]) VALUES (2003, N'Tiếng Anh', 110, N'Sach00001', NULL)
INSERT [dbo].[Sach] ([namXuatBan], [NgonNgu], [soTrang], [MaSanPham], [NhaXuatBan]) VALUES (2000, N'', 100, N'Sach00002', N'NCC1')
INSERT [dbo].[Sach] ([namXuatBan], [NgonNgu], [soTrang], [MaSanPham], [NhaXuatBan]) VALUES (2000, N'', 80, N'Sach00003', N'NCC1')
INSERT [dbo].[Sach] ([namXuatBan], [NgonNgu], [soTrang], [MaSanPham], [NhaXuatBan]) VALUES (2000, N'', 11, N'Sach00004', N'NCC1')
INSERT [dbo].[Sach] ([namXuatBan], [NgonNgu], [soTrang], [MaSanPham], [NhaXuatBan]) VALUES (1, N'', 1, N'Sach00005', N'NCC1')
INSERT [dbo].[Sach] ([namXuatBan], [NgonNgu], [soTrang], [MaSanPham], [NhaXuatBan]) VALUES (1, N'', 1, N'Sach00006', N'NCC1')
INSERT [dbo].[Sach] ([namXuatBan], [NgonNgu], [soTrang], [MaSanPham], [NhaXuatBan]) VALUES (1, N'', 1, N'Sach00007', N'NCC1')
INSERT [dbo].[Sach] ([namXuatBan], [NgonNgu], [soTrang], [MaSanPham], [NhaXuatBan]) VALUES (1, N'', 1, N'Sach00008', N'NCC1')
INSERT [dbo].[Sach] ([namXuatBan], [NgonNgu], [soTrang], [MaSanPham], [NhaXuatBan]) VALUES (1, N'', 1, N'Sach00009', N'NCC1')
INSERT [dbo].[Sach] ([namXuatBan], [NgonNgu], [soTrang], [MaSanPham], [NhaXuatBan]) VALUES (1, N'', 1, N'Sach00010', N'NCC1')
INSERT [dbo].[Sach] ([namXuatBan], [NgonNgu], [soTrang], [MaSanPham], [NhaXuatBan]) VALUES (1, N'', 1, N'Sach00011', N'NCC1')
INSERT [dbo].[Sach_TacGia] ([SachID], [TacGiaID]) VALUES (N'Sach00001', N'TG1')
INSERT [dbo].[Sach_TacGia] ([SachID], [TacGiaID]) VALUES (N'Sach00004', N'TG2')
INSERT [dbo].[Sach_TacGia] ([SachID], [TacGiaID]) VALUES (N'Sach00005', N'TG1')
INSERT [dbo].[Sach_TacGia] ([SachID], [TacGiaID]) VALUES (N'Sach00006', N'TG2')
INSERT [dbo].[Sach_TacGia] ([SachID], [TacGiaID]) VALUES (N'Sach00007', N'TG2')
INSERT [dbo].[Sach_TacGia] ([SachID], [TacGiaID]) VALUES (N'Sach00008', N'TG1')
INSERT [dbo].[Sach_TacGia] ([SachID], [TacGiaID]) VALUES (N'Sach00009', N'TG1')
INSERT [dbo].[Sach_TacGia] ([SachID], [TacGiaID]) VALUES (N'Sach00010', N'TG3')
INSERT [dbo].[Sach_TacGia] ([SachID], [TacGiaID]) VALUES (N'Sach00011', N'TG1')
INSERT [dbo].[Sach_TacGia] ([SachID], [TacGiaID]) VALUES (N'Sach00011', N'TG2')
INSERT [dbo].[Sach_TacGia] ([SachID], [TacGiaID]) VALUES (N'Sach00011', N'TG3')
INSERT [dbo].[Sach_TheLoai] ([SachID], [TheLoaiID]) VALUES (N'Sach00001', N'TL1')
INSERT [dbo].[Sach_TheLoai] ([SachID], [TheLoaiID]) VALUES (N'Sach00004', N'NT')
INSERT [dbo].[Sach_TheLoai] ([SachID], [TheLoaiID]) VALUES (N'Sach00005', N'TL1')
INSERT [dbo].[Sach_TheLoai] ([SachID], [TheLoaiID]) VALUES (N'Sach00010', N'TL1')
INSERT [dbo].[Sach_TheLoai] ([SachID], [TheLoaiID]) VALUES (N'Sach00010', N'TT')
INSERT [dbo].[Sach_TheLoai] ([SachID], [TheLoaiID]) VALUES (N'Sach00010', N'VanHoc')
INSERT [dbo].[Sach_TheLoai] ([SachID], [TheLoaiID]) VALUES (N'Sach00011', N'NT')
INSERT [dbo].[Sach_TheLoai] ([SachID], [TheLoaiID]) VALUES (N'Sach00011', N'TL1')
INSERT [dbo].[Sach_TheLoai] ([SachID], [TheLoaiID]) VALUES (N'Sach00011', N'TL2')
INSERT [dbo].[SanPham] ([MaSanPham], [donGia], [hinhAnh], [moTa], [soLuongTon], [tenSanPham], [tinhTrang], [LoaiSanPham], [MaNCC]) VALUES (N'Sach00001', 100000, N'sach1.jpg', N'Sách Giáo Khoa', 5, N'Sach 1', N'Đang bán', NULL, NULL)
INSERT [dbo].[SanPham] ([MaSanPham], [donGia], [hinhAnh], [moTa], [soLuongTon], [tenSanPham], [tinhTrang], [LoaiSanPham], [MaNCC]) VALUES (N'Sach00002', 100000, N'E:\PhanTan\DoAn\QuanLyHieuSach\src\main\java\IMG\khongCoAnh.png', N'', 100, N'T?m l?ng cao c?', N'Còn hàng', N'LSP000001', N'HNBST')
INSERT [dbo].[SanPham] ([MaSanPham], [donGia], [hinhAnh], [moTa], [soLuongTon], [tenSanPham], [tinhTrang], [LoaiSanPham], [MaNCC]) VALUES (N'Sach00003', 150000, N'E:\PhanTan\DoAn\QuanLyHieuSach\src\main\java\IMG\khongCoAnh.png', N'', 40, N'M?t bư?c', N'Còn hàng', N'LSP000001', N'HNBST')
INSERT [dbo].[SanPham] ([MaSanPham], [donGia], [hinhAnh], [moTa], [soLuongTon], [tenSanPham], [tinhTrang], [LoaiSanPham], [MaNCC]) VALUES (N'Sach00004', 1, N'E:\PhanTan\DoAn\QuanLyHieuSach\src\main\java\IMG\khongCoAnh.png', N'', 1, N'Đau', N'Hết hàng', N'LSP000001', N'HNBST')
INSERT [dbo].[SanPham] ([MaSanPham], [donGia], [hinhAnh], [moTa], [soLuongTon], [tenSanPham], [tinhTrang], [LoaiSanPham], [MaNCC]) VALUES (N'Sach00005', 1, N'E:\PhanTan\DoAn\QuanLyHieuSach\src\main\java\IMG\khongCoAnh.png', N'', 0, N'hi', N'Hết hàng', N'LSP000001', N'HNBST')
INSERT [dbo].[SanPham] ([MaSanPham], [donGia], [hinhAnh], [moTa], [soLuongTon], [tenSanPham], [tinhTrang], [LoaiSanPham], [MaNCC]) VALUES (N'Sach00006', 1, N'E:\PhanTan\DoAn\QuanLyHieuSach\src\main\java\IMG\khongCoAnh.png', N'', 1, N'1', N'Hết hàng', N'LSP000001', N'HNBST')
INSERT [dbo].[SanPham] ([MaSanPham], [donGia], [hinhAnh], [moTa], [soLuongTon], [tenSanPham], [tinhTrang], [LoaiSanPham], [MaNCC]) VALUES (N'Sach00007', 1, N'E:\PhanTan\DoAn\QuanLyHieuSach\src\main\java\IMG\khongCoAnh.png', N'', 1, N'1', N'Hết hàng', N'LSP000001', N'HNBST')
INSERT [dbo].[SanPham] ([MaSanPham], [donGia], [hinhAnh], [moTa], [soLuongTon], [tenSanPham], [tinhTrang], [LoaiSanPham], [MaNCC]) VALUES (N'Sach00008', 1, N'E:\PhanTan\DoAn\QuanLyHieuSach\src\main\java\IMG\khongCoAnh.png', N'', 1, N'1', N'Hết hàng', N'LSP000001', N'HNBST')
INSERT [dbo].[SanPham] ([MaSanPham], [donGia], [hinhAnh], [moTa], [soLuongTon], [tenSanPham], [tinhTrang], [LoaiSanPham], [MaNCC]) VALUES (N'Sach00009', 1, N'E:\PhanTan\DoAn\QuanLyHieuSach\src\main\java\IMG\khongCoAnh.png', N'', 1, N'1', N'Hết hàng', N'LSP000001', N'HNBST')
INSERT [dbo].[SanPham] ([MaSanPham], [donGia], [hinhAnh], [moTa], [soLuongTon], [tenSanPham], [tinhTrang], [LoaiSanPham], [MaNCC]) VALUES (N'Sach00010', 1, N'E:\PhanTan\DoAn\QuanLyHieuSach\src\main\java\IMG\khongCoAnh.png', N'', 1, N'1', N'Hết hàng', N'LSP000001', N'HNBST')
INSERT [dbo].[SanPham] ([MaSanPham], [donGia], [hinhAnh], [moTa], [soLuongTon], [tenSanPham], [tinhTrang], [LoaiSanPham], [MaNCC]) VALUES (N'Sach00011', 1, N'E:\PhanTan\DoAn\QuanLyHieuSach\src\main\java\IMG\khongCoAnh.png', N'', 0, N'1', N'Hết hàng', N'LSP000001', N'HNBST')
INSERT [dbo].[SanPham] ([MaSanPham], [donGia], [hinhAnh], [moTa], [soLuongTon], [tenSanPham], [tinhTrang], [LoaiSanPham], [MaNCC]) VALUES (N'VP00001', 10000, N'vp1.jpg', N'Văn phòng phẩm', -310, N'Văn ph?ng ph?m 1', N'Ngừng bán', NULL, NULL)
INSERT [dbo].[TacGia] ([MaTacGia], [TenTacGia]) VALUES (N'TG1', N'Tố Hữu')
INSERT [dbo].[TacGia] ([MaTacGia], [TenTacGia]) VALUES (N'TG2', N'Nguyen Nhat Anh')
INSERT [dbo].[TacGia] ([MaTacGia], [TenTacGia]) VALUES (N'TG3', N'Hữu Cảnh')
INSERT [dbo].[TaiKhoan] ([matKhau], [trangThai], [TaiKhoan]) VALUES (N'123456', N'Đang đăng nhập', N'21081841')
INSERT [dbo].[TheLoai] ([MaTheLoai], [TenTheLoai]) VALUES (N'NT', N'Ngôn tình')
INSERT [dbo].[TheLoai] ([MaTheLoai], [TenTheLoai]) VALUES (N'TL1', N'Bi kịch')
INSERT [dbo].[TheLoai] ([MaTheLoai], [TenTheLoai]) VALUES (N'TL2', N'Khoa học')
INSERT [dbo].[TheLoai] ([MaTheLoai], [TenTheLoai]) VALUES (N'TS', N'Tự sự')
INSERT [dbo].[TheLoai] ([MaTheLoai], [TenTheLoai]) VALUES (N'TT', N'Tiểu thuyết')
INSERT [dbo].[TheLoai] ([MaTheLoai], [TenTheLoai]) VALUES (N'TTT', N'Trinh Thám')
INSERT [dbo].[TheLoai] ([MaTheLoai], [TenTheLoai]) VALUES (N'VanHoc', N'Văn học')
INSERT [dbo].[ThoiGianHoatDong] ([MaThoiGian], [ngayDangNhap], [thoiGianDaLam], [thoiGianDangNhap], [thoiGianDangXuat], [MaNV]) VALUES (N'200424182921081841', CAST(N'2024-04-20' AS Date), CAST(N'18:29:21' AS Time), CAST(N'18:29:21' AS Time), NULL, N'21081841')
INSERT [dbo].[ThoiGianHoatDong] ([MaThoiGian], [ngayDangNhap], [thoiGianDaLam], [thoiGianDangNhap], [thoiGianDangXuat], [MaNV]) VALUES (N'210424023721081841', CAST(N'2024-04-21' AS Date), CAST(N'02:05:29' AS Time), CAST(N'02:37:08' AS Time), CAST(N'23:30:26' AS Time), N'21081841')
INSERT [dbo].[VanPhongPham] ([chatLieu], [namSanXuat], [MaSanPham], [MaLoaiVanPhongPham], [MaThuongHieu], [MaXuatXu]) VALUES (N'Nhựa', 2003, N'VP00001', NULL, NULL, NULL)
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK2yrnfo6tjygcmrv2j1xnsp8p0] FOREIGN KEY([MaSP])
REFERENCES [dbo].[SanPham] ([MaSanPham])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK2yrnfo6tjygcmrv2j1xnsp8p0]
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FKjmvd69lwk0hppqlahifj29e6c] FOREIGN KEY([MaHoaDon])
REFERENCES [dbo].[HoaDon] ([MaHoaDon])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FKjmvd69lwk0hppqlahifj29e6c]
GO
ALTER TABLE [dbo].[GiamGiaSanPham]  WITH CHECK ADD  CONSTRAINT [FK5wrc0km24fnl8u1w5twdnxpd6] FOREIGN KEY([MaLoaiSanPham])
REFERENCES [dbo].[LoaiSanPham] ([MaLoaiSanPham])
GO
ALTER TABLE [dbo].[GiamGiaSanPham] CHECK CONSTRAINT [FK5wrc0km24fnl8u1w5twdnxpd6]
GO
ALTER TABLE [dbo].[GiamGiaSanPham]  WITH CHECK ADD  CONSTRAINT [FKodyar9l0o2ior1ujnxmvdw7sm] FOREIGN KEY([MaSP])
REFERENCES [dbo].[SanPham] ([MaSanPham])
GO
ALTER TABLE [dbo].[GiamGiaSanPham] CHECK CONSTRAINT [FKodyar9l0o2ior1ujnxmvdw7sm]
GO
ALTER TABLE [dbo].[HangCho]  WITH CHECK ADD  CONSTRAINT [FKrvt8ukwnn62e0h953l3p0t3xw] FOREIGN KEY([MaKH])
REFERENCES [dbo].[KhachHang] ([MaKhachHang])
GO
ALTER TABLE [dbo].[HangCho] CHECK CONSTRAINT [FKrvt8ukwnn62e0h953l3p0t3xw]
GO
ALTER TABLE [dbo].[HangCho]  WITH CHECK ADD  CONSTRAINT [FKv5ty1gstrf9tyvot592qealg] FOREIGN KEY([MaSP])
REFERENCES [dbo].[SanPham] ([MaSanPham])
GO
ALTER TABLE [dbo].[HangCho] CHECK CONSTRAINT [FKv5ty1gstrf9tyvot592qealg]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FKai2dk2e65dqkc6o8s96pbdh6i] FOREIGN KEY([MaKH])
REFERENCES [dbo].[KhachHang] ([MaKhachHang])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FKai2dk2e65dqkc6o8s96pbdh6i]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FKqj54uj8lsoy9gwl4wyr4x2d94] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FKqj54uj8lsoy9gwl4wyr4x2d94]
GO
ALTER TABLE [dbo].[MaXacNhan]  WITH CHECK ADD  CONSTRAINT [FK3ka0jmrj5e7d8korje04mde3b] FOREIGN KEY([TaiKhoan])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[MaXacNhan] CHECK CONSTRAINT [FK3ka0jmrj5e7d8korje04mde3b]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FKaigiv4pjl3o7ns22jdqj93fat] FOREIGN KEY([MaCa])
REFERENCES [dbo].[CaLamViec] ([MaCa])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FKaigiv4pjl3o7ns22jdqj93fat]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FKigtf0c4ejp93s2w9lqekrbmbj] FOREIGN KEY([MaChucVu])
REFERENCES [dbo].[ChucVu] ([MaChucVu])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FKigtf0c4ejp93s2w9lqekrbmbj]
GO
ALTER TABLE [dbo].[Sach]  WITH CHECK ADD  CONSTRAINT [FK6lsvl4s1s8ejbbrb8ktjg90ae] FOREIGN KEY([NhaXuatBan])
REFERENCES [dbo].[NhaXuatBan] ([MaNhaXuatBan])
GO
ALTER TABLE [dbo].[Sach] CHECK CONSTRAINT [FK6lsvl4s1s8ejbbrb8ktjg90ae]
GO
ALTER TABLE [dbo].[Sach]  WITH CHECK ADD  CONSTRAINT [FKdvtvipugy8cuo5genqs3nagil] FOREIGN KEY([MaSanPham])
REFERENCES [dbo].[SanPham] ([MaSanPham])
GO
ALTER TABLE [dbo].[Sach] CHECK CONSTRAINT [FKdvtvipugy8cuo5genqs3nagil]
GO
ALTER TABLE [dbo].[Sach_TacGia]  WITH CHECK ADD  CONSTRAINT [FKb26pp8vernbgvxvucflllm4e5] FOREIGN KEY([SachID])
REFERENCES [dbo].[Sach] ([MaSanPham])
GO
ALTER TABLE [dbo].[Sach_TacGia] CHECK CONSTRAINT [FKb26pp8vernbgvxvucflllm4e5]
GO
ALTER TABLE [dbo].[Sach_TacGia]  WITH CHECK ADD  CONSTRAINT [FKbp41q7xjrxj6qlg2xir5tj3tb] FOREIGN KEY([TacGiaID])
REFERENCES [dbo].[TacGia] ([MaTacGia])
GO
ALTER TABLE [dbo].[Sach_TacGia] CHECK CONSTRAINT [FKbp41q7xjrxj6qlg2xir5tj3tb]
GO
ALTER TABLE [dbo].[Sach_TheLoai]  WITH CHECK ADD  CONSTRAINT [FKmksd1c5h7x24e1n5k4p53uq84] FOREIGN KEY([TheLoaiID])
REFERENCES [dbo].[TheLoai] ([MaTheLoai])
GO
ALTER TABLE [dbo].[Sach_TheLoai] CHECK CONSTRAINT [FKmksd1c5h7x24e1n5k4p53uq84]
GO
ALTER TABLE [dbo].[Sach_TheLoai]  WITH CHECK ADD  CONSTRAINT [FKrr2h6jni8p8sivm3lgwhy9mp7] FOREIGN KEY([SachID])
REFERENCES [dbo].[Sach] ([MaSanPham])
GO
ALTER TABLE [dbo].[Sach_TheLoai] CHECK CONSTRAINT [FKrr2h6jni8p8sivm3lgwhy9mp7]
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FKem8s7yany1yh8lbiy7dqh4vc4] FOREIGN KEY([LoaiSanPham])
REFERENCES [dbo].[LoaiSanPham] ([MaLoaiSanPham])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FKem8s7yany1yh8lbiy7dqh4vc4]
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FKpiglb2vsfhtltux87pcqote7u] FOREIGN KEY([MaNCC])
REFERENCES [dbo].[NhaCungCap] ([MaNCC])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FKpiglb2vsfhtltux87pcqote7u]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FKnyfpbjjiib3ygnefasy77gybb] FOREIGN KEY([TaiKhoan])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FKnyfpbjjiib3ygnefasy77gybb]
GO
ALTER TABLE [dbo].[ThoiGianHoatDong]  WITH CHECK ADD  CONSTRAINT [FKg5asqloy87x6itey2lueg8ac] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[ThoiGianHoatDong] CHECK CONSTRAINT [FKg5asqloy87x6itey2lueg8ac]
GO
ALTER TABLE [dbo].[VanPhongPham]  WITH CHECK ADD  CONSTRAINT [FKdjvsfb8ejcb6xkvps1es7gkuq] FOREIGN KEY([MaLoaiVanPhongPham])
REFERENCES [dbo].[LoaiVanPhongPham] ([MaLoaiVanPhongPham])
GO
ALTER TABLE [dbo].[VanPhongPham] CHECK CONSTRAINT [FKdjvsfb8ejcb6xkvps1es7gkuq]
GO
ALTER TABLE [dbo].[VanPhongPham]  WITH CHECK ADD  CONSTRAINT [FKfbhbekq830r372kduld94jirn] FOREIGN KEY([MaSanPham])
REFERENCES [dbo].[SanPham] ([MaSanPham])
GO
ALTER TABLE [dbo].[VanPhongPham] CHECK CONSTRAINT [FKfbhbekq830r372kduld94jirn]
GO
ALTER TABLE [dbo].[VanPhongPham]  WITH CHECK ADD  CONSTRAINT [FKlqc0102euo9aautm4achvpwbj] FOREIGN KEY([MaThuongHieu])
REFERENCES [dbo].[ThuongHieu] ([MaThuongHieu])
GO
ALTER TABLE [dbo].[VanPhongPham] CHECK CONSTRAINT [FKlqc0102euo9aautm4achvpwbj]
GO
ALTER TABLE [dbo].[VanPhongPham]  WITH CHECK ADD  CONSTRAINT [FKw8ry1cmwkk9im4p9mocq5dtg] FOREIGN KEY([MaXuatXu])
REFERENCES [dbo].[XuatXu] ([MaXuatXu])
GO
ALTER TABLE [dbo].[VanPhongPham] CHECK CONSTRAINT [FKw8ry1cmwkk9im4p9mocq5dtg]
GO
USE [master]
GO
ALTER DATABASE [quanlysach] SET  READ_WRITE 
GO
