package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Categories;
import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.Materials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatarialRepository extends JpaRepository<Materials,Integer> {

    Optional<Materials> findByName(String name);
    List<Materials> findAllByOrderByCreateDateDesc();
    Optional<Materials> findById(Long id);

    List<Materials> findByStatusOrderByCreateDateDesc(Integer status);
}
