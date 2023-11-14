package com.example.poloman.repository;

import com.example.poloman.model.entity.DanhSachYeuThich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DanhSachYeuThichRepository extends JpaRepository<DanhSachYeuThich, UUID> {
}
