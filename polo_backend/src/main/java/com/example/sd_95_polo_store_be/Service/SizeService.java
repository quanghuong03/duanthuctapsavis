package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Entity.Brands;
import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.Sizes;
import com.example.sd_95_polo_store_be.Model.Request.BrandRequest;
import org.hibernate.engine.jdbc.Size;

import java.util.List;

public interface SizeService {
    Sizes getOne(Long id);
    List<Sizes> getSizesByStatus();
    List<Sizes> gets();

    Sizes createOrUpdate(Sizes sizes);

    boolean isSize(Sizes sizes);

    void deleteSizeByIds(List<Integer> ids);

    public void changeStatus(Long id);
}
