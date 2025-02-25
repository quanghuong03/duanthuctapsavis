package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Brands;
import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.Sizes;
import com.example.sd_95_polo_store_be.Repository.SizeRepository;
import com.example.sd_95_polo_store_be.Service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public Sizes getOne(Long id) {
        Optional<Sizes> optionalMauSac = sizeRepository.findById(id);
        return optionalMauSac.get();
    }

    @Override
    public List<Sizes> getSizesByStatus() {
        return sizeRepository.findAllByOrderByCreateDateDesc();
    }

    @Override
    public List<Sizes> gets() {
        return sizeRepository.findByStatusOrderByCreateDateDesc(1);
    }

    @Override
    public Sizes createOrUpdate(Sizes sizes) {
        if (ObjectUtils.isEmpty(sizes.getName().trim())) {
            throw new IllegalArgumentException("Tên không để trống");
        } else if (ObjectUtils.isEmpty(sizes.getDescription().trim())) {
            throw new IllegalArgumentException("Mô tả không để trống");
        }

        var now = OffsetDateTime.now();

        if (sizes.getId() != null) {
            Optional<Sizes> existingSize = sizeRepository.findById(sizes.getId());
            if (existingSize.isPresent()) {
                Sizes updateSize = existingSize.get();
                updateSize.setName(sizes.getName());
                updateSize.setStatus(1);
                updateSize.setDescription(sizes.getDescription());
                updateSize.setShirtlength(sizes.getShirtlength());
                updateSize.setShirtwidth(sizes.getShirtwidth());
                updateSize.setSleevelenght(sizes.getSleevelenght());
                updateSize.setShoulderlength(sizes.getShoulderlength());
                updateSize.setUpdatedAt(now);
                return sizeRepository.save(updateSize);
            } else {
                throw new IllegalArgumentException("Không tìm thấy kích thước với id: " + sizes.getId());
            }
        } else {
            Sizes newSize = new Sizes();
            if (isSize(newSize)) {
                throw new IllegalArgumentException("Kích thước này đã có rồi");
            }
            newSize.setName(sizes.getName());
            newSize.setStatus(1);
            newSize.setDescription(sizes.getDescription());
            newSize.setShirtlength(sizes.getShirtlength());
            newSize.setShirtwidth(sizes.getShirtwidth());
            newSize.setSleevelenght(sizes.getSleevelenght());
            newSize.setShoulderlength(sizes.getShoulderlength());
            newSize.setCreatedAt(now);
            newSize.setUpdatedAt(now);
            return sizeRepository.save(newSize);
        }
    }

    @Override
    public boolean isSize(Sizes sizes) {
        Optional<Sizes> optionalSizes = sizeRepository.findByName(sizes.getName());
        return optionalSizes.isPresent();
    }

    @Override
    public void deleteSizeByIds(List<Integer> ids) {
        if (ids != null && !ids.isEmpty()) {
            for (Integer id : ids) {
                Optional<Sizes> optionalSizes = sizeRepository.findById(id);
                if (optionalSizes.isPresent()) {
                    sizeRepository.deleteById(id);
                } else {
                    throw new IllegalArgumentException("Không tìm thấy size");
                }
            }
        } else {
            throw new IllegalArgumentException("Danh sách ID không hợp lệ");
        }

    }

    @Override
    public void changeStatus(Long id) {
        var brand = sizeRepository.findById(id).orElseThrow();
        if(brand.getStatus() == 1){
            brand.setStatus(0);
            sizeRepository.save(brand);
        }else {
            brand.setStatus(1);
            sizeRepository.save(brand);
        }
    }

}
