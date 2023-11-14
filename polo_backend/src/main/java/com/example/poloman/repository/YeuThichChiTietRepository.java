package com.example.poloman.repository;

import com.example.poloman.model.entity.YeuThichChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface YeuThichChiTietRepository extends JpaRepository<YeuThichChiTiet, UUID> {
}
