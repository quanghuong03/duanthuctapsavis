insert into CHATLIEU(TenChatLieu)
VALUES (N'Cotton'),
(N'vải P.E'),
(N'vải thun cá sấu'),
(N'Vải linen'),
(N'Vải Lycra'),
(N'Vải Rayon'),
(N'Vải Modal'),
(N'Vải CVC'),
(N'Vải bamboo')

insert into ThuongHieu(TenThuongHieu)
VALUES 
(N'Lacoste'),
(N'Gucci'),
(N'Uniqlo'),
(N'Brooks Brothers'),
(N'Ralph Lauren'),
(N'Hugo Boss'),
(N'Adidas')

insert into MAUSAC(TenMau)
VALUES (N'Xanh'),
(N'Đỏ')

insert into DONGSP(TenDongSP)
VALUES
(N'regular-fit'),
(N'slim-fit')
insert into SIZE(SoSize,CANNANG,CHIEUCAO)
VALUES('S',N'50-60',N'160-170'),
('M',N'55-65',N'163-170'),
('L',N'65-75',N'165-172'),
('XL',N'75-90',N'170-180'),
('XXL',N'85-100',N'170-190')

insert into SanPham(TenSanPham,GiaBan,GiaNhap,HinhAnh,luotmua,mota,ThuongHieu,ChatLieu,KieuAo)
VALUES (N'Áo polo nam POMTK432',10000,5000,N'product-4',10,N'Coat with quilted lining and an adjustable hood. Featuring long sleeves with adjustable cuff tabs, adjustable asymmetric hem with elastic side tabs and a front zip fastening with placket.',1,2,2),
(N'Áo polo nam POMTK426',10000,5000,N'product-5',2,N'Coat with quilted lining and an adjustable hood. Featuring long sleeves with adjustable cuff tabs, adjustable asymmetric hem with elastic side tabs and a front zip fastening with placket.',2,3,2),
(N'Áo polo nam POHTK431',10000,5000,N'product-6',1,N'Coat with quilted lining and an adjustable hood. Featuring long sleeves with adjustable cuff tabs, adjustable asymmetric hem with elastic side tabs and a front zip fastening with placket.',1,2,2),
(N'Áo polo nam POHTK422',10000,5000,N'product-7',0,N'Coat with quilted lining and an adjustable hood. Featuring long sleeves with adjustable cuff tabs, adjustable asymmetric hem with elastic side tabs and a front zip fastening with placket.',2,3,2)
insert into ChiTietSanPham(SanPham,Size,MauSac,SoLuongTon,MaVach)
values(2,1,1,20,10)

insert into KhachHang(tenkhachhang,ngaysinh,sodienthoai,diachi,email,gioitinh,matkhau)
values(N'Phạm Quang Hướng','09-03-2003','0856572786',N'Nam Định',N'huongnui01@gmail.com',1,'123')

insert into GioHang(NgayTao, TrangThai, KhachHang)
values ('01-01-2023',1,1)

insert into GioHangChiTiet(GioHang,SanPham,SoLuong,TrangThai)
values(1,1,10,1),
(1,8,10,1)

SELECT TenSanPham FROM ChiTietSanPham WHERE MaSanPham = (SELECT SanPham FROM GioHangChiTiet WHERE MaGioHang = 1);