package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Brands;
import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.Materials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brands,Integer> {
    Optional<Brands> findByName(String name);
    List<Brands> findAllByOrderByCreateDateDesc();
    Optional<Brands> findById(Long id);
    List<Brands> findByStatusOrderByCreateDateDesc(Integer status);
}
