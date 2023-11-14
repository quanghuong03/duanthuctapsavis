package com.example.poloman.repository;

import com.example.poloman.model.entity.GioHang;
import com.example.poloman.model.reponse.GioHangChiTietResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Integer> {
    @Query(value = """
                select new com.example.poloman.model.reponse.GioHangChiTietResponse(cd.magiohang, pd.mactsp, pd.sanpham.tensanpham, pd.size.sosize,  pd.sanpham.hinhanh, pd.sanpham.giaban ,pd.mausac.tenmau, cd.soluong, cd.trangthai)
                from GioHang c
                         join KhachHang kh on kh.makhachhang = c.khachhang.makhachhang
                         join GioHangChiTiet cd on c.magiohang = cd.giohang.magiohang
                         join ChiTietSanPham pd on pd.mactsp = cd.sanpham.mactsp
                     
                where kh.makhachhang = :makhachhang 
            """)
    List<GioHangChiTietResponse> getGioHangByKhachhang(Integer makhachhang);

    Optional<GioHang> findGioHangByKhachhangMakhachhang(Integer makhachhang);

    @Query(value = """
                select new com.example.poloman.model.reponse.GioHangChiTietResponse(cd.magiohang, pd.mactsp, pd.sanpham.tensanpham, pd.size.sosize,  pd.sanpham.hinhanh, pd.sanpham.giaban ,pd.mausac.tenmau, cd.soluong, cd.trangthai)
                from GioHang c
                         join KhachHang kh on kh.makhachhang = c.khachhang.makhachhang
                         join GioHangChiTiet cd on c.magiohang = cd.giohang.magiohang
                         join ChiTietSanPham pd on pd.mactsp = cd.sanpham.mactsp
                     
                where kh.makhachhang = :makhachhang and cd.trangthai <> 0
            """)
    List<GioHangChiTietResponse> getGioHangByKhachhangAndTrangthai(Integer makhachhang);


}
