	USE [master]

	CREATE DATABASE [FPOY_DU_AN_TN]
	GO

	USE [FPOY_DU_AN_TN]
	GO

	CREATE TABLE KhachHang(
	MaKhachHang BIGINT IDENTITY(1,1) PRIMARY KEY,
	TenKhachHang NVARCHAR(100),
	NgaySinh DATE,
	SoDienThoai VARCHAR(15) UNIQUE,
	DiaChi NVARCHAR(MAX),
	Email NVARCHAR(50) UNIQUE,
	GioiTinh BIT,
	MatKhau varchar(max)
	)

	CREATE TABLE ChucVu(
	MaChucVu INT IDENTITY(1,1) PRIMARY KEY,
	TenChucVu NVARCHAR(100)
	)

	CREATE TABLE NhanVien(
	MaNhanVien BIGINT IDENTITY(1,1) PRIMARY KEY,
	TenNhanVien NVARCHAR(100),
	NgaySinh DATE,
	SoDienThoai VARCHAR(15) UNIQUE,
	DiaChi NVARCHAR(MAX),
	Email NVARCHAR(50) UNIQUE,
	GioiTinh BIT,
	MatKhau varchar(max),
	ChucVu int REFERENCES ChucVu(MaChucVu)
	)

	CREATE TABLE Size(
	MaSize INT IDENTITY(1,1) PRIMARY KEY,
	SoSize nvarchar(100),
	ChieuCao NVARCHAR(100),
	CanNang NVARCHAR(100)
	)

	CREATE TABLE MauSac(
	MaMauSac INT IDENTITY(1,1) PRIMARY KEY,
	TenMau nvarchar(100),
	)

	CREATE TABLE ThuongHieu(
	MaThuongHieu INT IDENTITY(1,1) PRIMARY KEY,
	TenThuongHieu NVARCHAR(100)
	)

	CREATE TABLE ChatLieu(
	MaChatLieu INT IDENTITY(1,1) PRIMARY KEY,
	TenChatLieu NVARCHAR(100)
	)

	CREATE TABLE DongSP(
	MaDongSP INT IDENTITY(1,1) PRIMARY KEY,
	TenDongSP NVARCHAR(100)
	)
	CREATE TABLE SanPham(
	MaSanPham BIGINT IDENTITY(1,1) PRIMARY KEY,
	TenSanPham NVARCHAR(100),
	GiaBan Money,
	GiaNhap Money,
	HinhAnh VARCHAR(255),
	luotmua int,
	mota nvarchar(max),
	ThuongHieu INT REFERENCES ThuongHieu(MaThuongHieu),
	ChatLieu INT REFERENCES ChatLieu(MaChatLieu),
	KieuAo int REFERENCES DongSP(MaDongSP),
	)

	CREATE TABLE ChiTietSanPham(
	MaCTSP BIGINT IDENTITY(1,1) PRIMARY KEY,
	SanPham BIGINT REFERENCES SanPham(MaSanPham),
	Size INT REFERENCES Size(MaSize),
	MauSac INT REFERENCES MauSac(MaMauSac),
	SoLuongTon int,
	MaVach varchar(50) UNIQUE
	)


	CREATE TABLE KHUYENMAI(
	Id uniqueidentifier DEFAULT NEWID(),
	MAKHUYENMAI NVARCHAR(20) UNIQUE,
	NGAYTAO DATE,
	NGAYSUA DATE,
	TEN NVARCHAR(255),
	MUCGIAMGIA INT,
	NGAYBATDAU DATETIME,
	NGAYKETTHUC DATETIME,
	TRANGTHAI INT,
	PRIMARY KEY(Id)
	)

	CREATE TABLE HoaDon(
	MaHoaDon BIGINT IDENTITY(1,1) PRIMARY KEY,
	NgayTao Date,
	TrangThai int,
	GhiChu nvarchar(255),
	NgayThanhToan Date,
	KhachHang BIGINT REFERENCES KhachHang(MaKhachHang),
	NhanVien BIGINT REFERENCES NhanVien(MaNhanVien)
	)

	CREATE TABLE HoaDonChiTiet(
	MaHoaDon BIGINT IDENTITY(1,1) PRIMARY KEY,
	HoaDon BIGINT REFERENCES HoaDon(MaHoaDon),
	SanPham  BIGINT REFERENCES ChiTietSanPham(MaCTSP),
	SoLuong int,
	DonGia Money,
	TrangThai int,
	)

	CREATE TABLE GioHang(
	MaGioHang BIGINT IDENTITY(1,1) PRIMARY KEY,
	NgayTao Date,
	TrangThai int,
	KhachHang BIGINT REFERENCES KhachHang(MaKhachHang),
	)

	CREATE TABLE GioHangChiTiet(
	MaGioHang BIGINT IDENTITY(1,1) PRIMARY KEY,
	GioHang  BIGINT REFERENCES GioHang(MaGioHang),
	SanPham  BIGINT REFERENCES ChiTietSanPham(MaCTSP),
	SoLuong int,
	TrangThai int,
	)

	CREATE TABLE DanhSachYeuThich(	
		MaDanhSach	UNIQUEIDENTIFIER 
			DEFAULT NEWID() PRIMARY KEY,
		NgayTao		DATE,
		NgayCapNhap	DATE,

		NguoiSoHuu	BIGINT 
			REFERENCES KhachHang(MaKhachHang),
	
		GhiChu		NVARCHAR(MAX),
		TrangThai	INT,
	)

	CREATE TABLE YeuThichChiTiet(
		MaDanhSach	UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
		DanhSach	UNIQUEIDENTIFIER
			REFERENCES DanhSachYeuThich(MaDanhSach),
		MaSanPham	BIGINT
			REFERENCES ChiTietSanPham(MaCTSP),

		GhiChu		NVARCHAR(MAX),
		TrangThai	INT,

	)

ALTER TABLE HoaDon
Add thanhpho nvarchar(100),
quanhuyen nvarchar(100),
phuongxa nvarchar(100),
tennguoinhan nvarchar(100),
diachi nvarchar(100),
sodienthoai nvarchar(100),
tonggia float  