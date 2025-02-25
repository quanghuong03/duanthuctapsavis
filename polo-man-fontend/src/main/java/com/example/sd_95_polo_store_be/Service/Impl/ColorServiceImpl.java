package com.example.sd_95_polo_store_be.Service.Impl;


import com.example.sd_95_polo_store_be.Model.Entity.Categories;
import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.Sizes;
import com.example.sd_95_polo_store_be.Repository.ColorRepository;
import com.example.sd_95_polo_store_be.Service.ColorServices;
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
public class ColorServiceImpl implements ColorServices {
    @Autowired
    ColorRepository colorRepository;

    @Override
    public List<Colors> getColorByStatus() {
        return colorRepository.findAllByOrderByCreateDateDesc();
    }

    @Override
    public List<Colors> gets() {
        return colorRepository.findByStatusOrderByCreateDateDesc(1L);
    }

    @Override
    public ArrayList<Colors> getAllColor() {
        return (ArrayList<Colors>) colorRepository.findAll();
    }

    @Override
    public Colors saveColor(Colors color) {
        var now = OffsetDateTime.now();
        if (color.getId() != null) {
            Optional<Colors> existingCategori = colorRepository.findById(color.getId());
            if (existingCategori.isPresent()) {
                Colors updateCategori = existingCategori.get();
                updateCategori.setName(color.getName());
                updateCategori.setStatus(1);
                updateCategori.setDescription(color.getDescription());
                updateCategori.setUpdatedAt(now);
                return colorRepository.save(updateCategori);
            } else {
                throw new IllegalArgumentException("Không tìm thấy kích thước với id: " + color.getId());
            }
        } else {
            Colors newCategori = new Colors();
            if (isColorDataDuplicate(color)) {
                throw new IllegalArgumentException("loại này đã có rồi");
            }
            newCategori.setName(color.getName());
            newCategori.setStatus(1);
            newCategori.setDescription(color.getDescription());
            newCategori.setCreatedAt(now);
            newCategori.setUpdatedAt(now);
            return colorRepository.save(newCategori);
        }

    }

    @Override
    public Colors getOne(Long id) {
        Optional<Colors> optionalMauSac = colorRepository.findById(id);
        return optionalMauSac.get();
    }


    @Override
    public void deleteColorById(Long id) {

        Colors existingColor = findById(id);
        if (existingColor == null) {
            throw new IllegalArgumentException("Không tìm thấy màu sắc với ID: " + id);
        }
        colorRepository.deleteById(id);
    }

    @Override
    public Page<Colors> findAllColor(Pageable pageable) {
        return colorRepository.findAll(pageable);
    }

    @Override
    public boolean isColorDataDuplicate(Colors color) {
        return colorRepository.existsByName(color.getName());
    }

    @Override
    public Colors findById(Long id) {
        Optional<Colors> color = colorRepository.findById(id);
        return color.orElse(null);
    }

    @Override
    public void deleteColorsByIds(List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            for (Long id : ids) {
                Optional<Colors> colorOptional = colorRepository.findById(id);
                if (colorOptional.isPresent()) {
                    colorRepository.deleteById(id);
                } else {
                    throw new IllegalArgumentException("Màu sắc với ID " + id + " không tồn tại");
                }
            }
        } else {
            throw new IllegalArgumentException("Danh sách ID không hợp lệ");
        }
    }

    @Override
    public void changeStatus(Long id) {
        var brand = colorRepository.findById(id).orElseThrow();
        if(brand.getStatus() == 1){
            brand.setStatus(0);
            colorRepository.save(brand);
        }else {
            brand.setStatus(1);
            colorRepository.save(brand);
        }
    }

}
