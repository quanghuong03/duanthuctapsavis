package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.Sizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SizeRepository extends JpaRepository<Sizes,Integer> {
    Optional<Sizes> findByName(String name);
    List<Sizes> findAllByOrderByCreateDateDesc();
    Optional<Sizes> findById(Long id);
    List<Sizes> findByStatusOrderByCreateDateDesc(Integer status);
}
