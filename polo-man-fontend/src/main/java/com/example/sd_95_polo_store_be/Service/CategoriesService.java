package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Entity.Categories;
import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface CategoriesService {
    Categories getOne(Long id);
    List<Categories>getCategoryByStatus();
    ArrayList<Categories> getAllCategories();
    Categories saveCategories(Categories categories);
    void deleteCategoriesById(Long id);
    Page<Categories> findAllCategories(Pageable pageable);
    boolean isCategoriesDataDuplicate(Categories categories);
    Categories findById(Long id);
    void deleteCategoriesByIds(List<Long> ids);
    public void changeStatus(Long id);
    List<Categories> gets();
}
