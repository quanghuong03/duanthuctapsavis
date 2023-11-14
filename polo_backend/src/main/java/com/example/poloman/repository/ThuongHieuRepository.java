package com.example.poloman.repository;

import com.example.poloman.model.entity.ThuongHieu;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface ThuongHieuRepository extends JpaRepository<ThuongHieu, Integer> {
}
