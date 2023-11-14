package com.example.poloman.repository;

import com.example.poloman.model.entity.SanPham;
import com.example.poloman.model.reponse.SanPhamOverviewResponse;
import com.example.poloman.model.reponse.SanPhamResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
    @Query(value = """
                    select new com.example.poloman.model.reponse.SanPhamResponse(p.masanpham, p.thuonghieu.mathuonghieu, p.chatlieu.machatlieu, p.kieuao.madongsp, p.tensanpham, p.giaban,p.gianhap,p.hinhanh,p.luotmua,p.mota,p.chatlieu.tenchatlieu,p.thuonghieu.tenthuonghieu, p.kieuao.tendongsp)
                     from SanPham p
                       where p.masanpham = :masanpham
                    """)
    Optional<SanPhamResponse> findByMaSanPham(Integer masanpham);

    @Query(value = """
                    select new com.example.poloman.model.reponse.SanPhamOverviewResponse(p.masanpham, p.thuonghieu.mathuonghieu, p.chatlieu.machatlieu, p.kieuao.madongsp, p.tensanpham, p.giaban,p.gianhap,p.hinhanh,p.luotmua,p.mota,p.chatlieu.tenchatlieu,p.thuonghieu.tenthuonghieu, p.kieuao.tendongsp)
                     from SanPham p

                    """)
    List<SanPhamOverviewResponse> findAllSanPham();
}
