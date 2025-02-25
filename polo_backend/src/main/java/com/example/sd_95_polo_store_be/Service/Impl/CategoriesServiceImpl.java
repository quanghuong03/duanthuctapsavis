package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Categories;
import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Repository.CategoriesRepository;
import com.example.sd_95_polo_store_be.Service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    CategoriesRepository categoriesRepository;

    @Override
    public Categories getOne(Long id) {
        Optional<Categories> optionalMauSac = categoriesRepository.findById(id);
        return optionalMauSac.get();
    }

    @Override
    public List<Categories> getCategoryByStatus() {
        return categoriesRepository.findAllByOrderByCreateDateDesc();
    }

    @Override
    public ArrayList<Categories> getAllCategories() {
        return (ArrayList<Categories>) categoriesRepository.findAll();
    }

    @Override
    public Categories saveCategories(Categories categories) {
//        if (ObjectUtils.isEmpty(categories.getName().trim())) {
//            throw new IllegalArgumentException("Tên không để trống");
//        } else if (ObjectUtils.isEmpty(categories.getStatus().toString().trim())) {
//            throw new IllegalArgumentException("Trạng thái không để trống");
//        } else if (ObjectUtils.isEmpty(categories.getDescription().trim())) {
//            throw new IllegalArgumentException("Mô tả không để trống");
//        } else if (isCategoriesDataDuplicate(categories)) {
//            throw new IllegalArgumentException("Loại này đã có rồi");
//        }
        var now = OffsetDateTime.now();
        if (categories.getId() != null) {
            Optional<Categories> existingCategori = categoriesRepository.findById(categories.getId());
            if (existingCategori.isPresent()) {
                Categories updateCategori = existingCategori.get();
                updateCategori.setName(categories.getName());
                updateCategori.setStatus(1);
                updateCategori.setDescription(categories.getDescription());
                updateCategori.setUpdatedAt(now);
                return categoriesRepository.save(updateCategori);
            } else {
                throw new IllegalArgumentException("Không tìm thấy kích thước với id: " + categories.getId());
            }
        } else {
            Categories newCategori = new Categories();
            if (isCategoriesDataDuplicate(categories)) {
                throw new IllegalArgumentException("loại này đã có rồi");
            }
            newCategori.setName(categories.getName());
            newCategori.setStatus(1);
            newCategori.setDescription(categories.getDescription());
            newCategori.setCreatedAt(now);
            newCategori.setUpdatedAt(now);
            return categoriesRepository.save(newCategori);
        }

    }


    @Override
    public void deleteCategoriesById(Long id) {
        Categories existingCategories = findById(id);
        if (existingCategories == null) {
            throw new IllegalArgumentException("Không tìm thấy loại với ID: " + id);
        }
        categoriesRepository.deleteById(id);
    }

    @Override
    public Page<Categories> findAllCategories(Pageable pageable) {
        return categoriesRepository.findAll(pageable);
    }

    @Override
    public boolean isCategoriesDataDuplicate(Categories categories) {
        return categoriesRepository.existsByName(categories.getName());
    }

    @Override
    public Categories findById(Long id) {
        Optional<Categories> categories = categoriesRepository.findById(id);
        return categories.orElse(null);
    }

    @Override
    public void deleteCategoriesByIds(List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            for (Long id : ids) {
                Optional<Categories> categoriesOptional = categoriesRepository.findById(id);
                if (categoriesOptional.isPresent()) {
                    categoriesRepository.deleteById(id);
                } else {
                    throw new IllegalArgumentException("Loại với ID " + id + " không tồn tại");
                }
            }
        } else {
            throw new IllegalArgumentException("Danh sách ID không hợp lệ");
        }

    }

    @Override
    public void changeStatus(Long id) {
        var brand = categoriesRepository.findById(id).orElseThrow();
        if(brand.getStatus() == 1){
            brand.setStatus(0);
            categoriesRepository.save(brand);
        }else {
            brand.setStatus(1);
            categoriesRepository.save(brand);
        }
    }

    @Override
    public List<Categories> gets() {
        return categoriesRepository.findByStatusOrderByCreateDateDesc(1L);
    }

}
