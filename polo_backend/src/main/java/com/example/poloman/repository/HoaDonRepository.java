package com.example.poloman.repository;

import com.example.poloman.model.entity.HoaDon;
import com.example.poloman.model.reponse.HoaDonChiTietResponse;
import com.example.poloman.model.reponse.HoaDonResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    @Query(value = """
                select new com.example.poloman.model.reponse.HoaDonResponse(hd.mahoadon,hd.tonggia,hd.diachi,hd.thanhpho,hd.quanhuyen,hd.sodienthoai,hd.phuongxa,hd.tennguoinhan,hd.ghichu,hd.trangthai)
                from HoaDon hd
                where hd.khachhang.makhachhang = :makhachhang
            """)
    List<HoaDonResponse> getHoaDonByKhachHang(Integer makhachhang);

    @Query(value = """
                select new com.example.poloman.model.reponse.HoaDonResponse(hd.mahoadon,hd.tonggia,hd.diachi,hd.thanhpho,hd.quanhuyen,hd.sodienthoai,hd.phuongxa,hd.tennguoinhan,hd.ghichu,hd.trangthai)
                from HoaDon hd
            """)
    List<HoaDonResponse> getAll();
    @Query(value = """
                select new com.example.poloman.model.reponse.HoaDonResponse(hd.mahoadon,hd.tonggia,hd.diachi,hd.thanhpho,hd.quanhuyen,hd.sodienthoai,hd.phuongxa,hd.tennguoinhan,hd.ghichu,hd.trangthai)
                from HoaDon hd
                where hd.mahoadon = :mahoadon
            """)
    HoaDonResponse getByIdHoaDon(Integer mahoadon);

    @Query(value = """
                select new com.example.poloman.model.reponse.HoaDonChiTietResponse(hdct.mahoadon, hdct.chiTietSanPham.mactsp, hdct.chiTietSanPham.sanpham.masanpham, hdct.chiTietSanPham.sanpham.tensanpham, hdct.chiTietSanPham.size.sosize,hdct.chiTietSanPham.mausac.tenmau, hdct.soluong, hdct.dongia, hdct.chiTietSanPham.sanpham.hinhanh)
                from HoaDon hd
                join HoaDonChiTiet hdct on hdct.hoadon.mahoadon = hd.mahoadon
                where hd.mahoadon = :mahoadon
            """)
    List<HoaDonChiTietResponse> getHoaDonChiTietByHoaDon(Integer mahoadon);


}
