package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Brands;
import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Request.BrandRequest;

import com.example.sd_95_polo_store_be.Repository.BrandRepository;
import com.example.sd_95_polo_store_be.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class    BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brands getOne(Long id) {
        Optional<Brands> optionalMauSac = brandRepository.findById(id);
        return optionalMauSac.get();
    }

    @Override
    public List<Brands> gets() {
        return brandRepository.findByStatusOrderByCreateDateDesc(1);
    }

    @Override
    public List<Brands> getBardByStatus() {
        return brandRepository.findAllByOrderByCreateDateDesc();
    }

    @Override
    public Brands createOrUpdate(BrandRequest request) {
//        if (ObjectUtils.isEmpty(request.getNameBrand().trim())) {
//            throw new IllegalArgumentException("Tên không để trống");
//        } else if (ObjectUtils.isEmpty(request.getDescription().trim())) {
//            throw new IllegalArgumentException("Mô tả không để trống");
//        }
        var now = OffsetDateTime.now();

        if (request.getId() != null) {
            Optional<Brands> existingBrand = brandRepository.findById(request.getId());
            if (existingBrand.isPresent()) {
                Brands updateBrand = existingBrand.get();
//                updateBrand.setName(request.getNameBrand());
                updateBrand.setName(request.getName());
                updateBrand.setStatus(1);
                updateBrand.setDescription(request.getDescription());
                updateBrand.setUpdatedAt(now);
                return brandRepository.save(updateBrand);
            } else {
                throw new IllegalArgumentException("Không tìm thấy thương hiệu với id: " + request.getId());
            }
        } else {
            Brands newBrand = new Brands();
//            if (isBrand(newBrand)) {
//                throw new IllegalArgumentException("Thương Hiệu này đã có rồi");
//            }
            newBrand.setName(request.getName());
            newBrand.setStatus(1);
            newBrand.setDescription(request.getDescription());
            newBrand.setCreatedAt(now);
            newBrand.setUpdatedAt(now);
            return brandRepository.save(newBrand);
        }
    }

    @Override
    public boolean isBrand(Brands brands) {
        Optional<Brands> existingBrand = brandRepository.findByName(brands.getName());
        return existingBrand.isPresent();
    }

    @Override
    public void deleteBrandByIds(List<Integer> ids) {
        if (ids != null && !ids.isEmpty()) {
            for (Integer id : ids) {
                Optional<Brands> brands = brandRepository.findById(id);
                if (brands.isPresent()) {
                    brandRepository.deleteById(id);
                } else {
                    throw new IllegalArgumentException("Không tìm thấy thương hiệu");
                }
            }
        } else {
            throw new IllegalArgumentException("Danh sách ID không hợp lệ");
        }
    }

    @Override
    public void changeStatus(Integer id) {
        var brand = brandRepository.findById(id).orElseThrow();
        if(brand.getStatus() == 1){
            brand.setStatus(0);
            brandRepository.save(brand);
        }else {
            brand.setStatus(1);
            brandRepository.save(brand);
        }
    }


}

