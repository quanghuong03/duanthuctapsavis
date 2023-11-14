package com.example.poloman.repository;

import com.example.poloman.model.entity.DongSP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DongSPRepository extends JpaRepository<DongSP, Integer> {
}
