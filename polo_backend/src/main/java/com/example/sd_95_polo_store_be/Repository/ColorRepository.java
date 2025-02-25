package com.example.sd_95_polo_store_be.Repository;


import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.Sizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Colors, Long> {
    boolean existsByName(String name);
//    void deleteAllById(List<Long> ids);
Optional<Colors> findById(Long id);
    List<Colors> findByOrderByCreateDateDesc();
    List<Colors> findByStatusOrderByCreateDateDesc(Long status);
    List<Colors> findAllByOrderByCreateDateDesc();
}
