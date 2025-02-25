package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Entity.Categories;
import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.Materials;
import com.example.sd_95_polo_store_be.Model.Entity.Sizes;

import java.util.List;

public interface MatarialService {
    List<Materials>getMaterialsByStatus();
    List<Materials> gets();

    Materials createOrUpdate(Materials materials);

    boolean isMaterial(Materials materials);

    void deleteMaterialByIds(List<Integer> ids);

    Materials getOne(Long id);

    public void changeStatus(Long id);
}
