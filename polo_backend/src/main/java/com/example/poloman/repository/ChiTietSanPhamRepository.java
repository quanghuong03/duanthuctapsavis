package com.example.poloman.repository;

import com.example.poloman.model.entity.ChiTietSanPham;
import com.example.poloman.model.entity.SanPham;
import com.example.poloman.model.reponse.SanPhamChiTietResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, Integer> {
    List<ChiTietSanPham> findBySanpham(SanPham sanPham);
    List<ChiTietSanPham> findBySanpham_Masanpham(Integer masanpham);

    @Query(value = """
                    select new com.example.poloman.model.reponse.SanPhamChiTietResponse(pd.mactsp,pd.soluongton,pd.mavach,pd.mausac.mamausac,pd.size.masize,pd.size.sosize,pd.mausac.tenmau)
                    from ChiTietSanPham pd
                    where pd.sanpham.masanpham = :masanpham
                """)
    List<SanPhamChiTietResponse> getforProduct(Integer masanpham);
}
