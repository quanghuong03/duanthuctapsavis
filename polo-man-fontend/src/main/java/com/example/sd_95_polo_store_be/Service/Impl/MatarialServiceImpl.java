package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.Materials;
import com.example.sd_95_polo_store_be.Repository.MatarialRepository;
import com.example.sd_95_polo_store_be.Service.MatarialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MatarialServiceImpl implements MatarialService {
    @Autowired
    private MatarialRepository matarialRepository;

    @Override
    public List<Materials> getMaterialsByStatus() {
        return matarialRepository.findAllByOrderByCreateDateDesc();
    }

    @Override
    public List<Materials> gets() {
        return matarialRepository.findByStatusOrderByCreateDateDesc(1);
    }

    @Override
    public Materials createOrUpdate(Materials materials) {
        if (ObjectUtils.isEmpty(materials.getName().trim())) {
            throw new IllegalArgumentException("Tên không để trống");
        } else if (ObjectUtils.isEmpty(materials.getDescription().trim())) {
            throw new IllegalArgumentException("Mô tả không để trống");
        }
        var now = OffsetDateTime.now();

        if (materials.getId() != null) {
            Optional<Materials> existingMaterial = matarialRepository.findById(materials.getId());
            if (existingMaterial.isPresent()) {
                Materials updateMaterial = existingMaterial.get();
                updateMaterial.setName(materials.getName());
                updateMaterial.setStatus(1);
                updateMaterial.setDescription(materials.getDescription());
                updateMaterial.setUpdatedAt(now);
                return matarialRepository.save(updateMaterial);
            } else {
                throw new IllegalArgumentException("Không tìm thấy vật liệu với id: " + materials.getId());
            }
        } else {
            Materials newMaterial = new Materials();
            if (isMaterial(newMaterial)) {
                throw new IllegalArgumentException("Vật liệu này đã có rồi");
            }
            newMaterial.setName(materials.getName());
            newMaterial.setStatus(1);
            newMaterial.setDescription(materials.getDescription());
            newMaterial.setCreatedAt(now);
            newMaterial.setUpdatedAt(now);
            return matarialRepository.save(newMaterial);
        }
    }

    @Override
    public boolean isMaterial(Materials materials) {
        Optional<Materials> optionalMeterials = matarialRepository.findByName(materials.getName());
        return optionalMeterials.isPresent();
    }

    @Override
    public void deleteMaterialByIds(List<Integer> ids) {
        if (ids != null && !ids.isEmpty()) {
            for (Integer id : ids) {
                Optional<Materials> optionalMeterials = matarialRepository.findById(id);
                if (optionalMeterials.isPresent()) {
                     matarialRepository.deleteById(id);
                } else {
                    throw new IllegalArgumentException("Không tìm thấy chất liệu");
                }
            }
        } else {
            throw new IllegalArgumentException("Danh sách ID không hợp lệ");
        }

    }

    @Override
    public Materials getOne(Long id) {
        Optional<Materials> optionalMauSac = matarialRepository.findById(id);
        return optionalMauSac.get();
    }

    @Override
    public void changeStatus(Long id) {
        var brand = matarialRepository.findById(id).orElseThrow();
        if(brand.getStatus() == 1){
            brand.setStatus(0);
           matarialRepository.save(brand);
        }else {
            brand.setStatus(1);
            matarialRepository.save(brand);
        }
    }

}
