package com.example.sd_95_polo_store_be.Model.Request;

import com.example.sd_95_polo_store_be.Model.Entity.Brands;
import com.example.sd_95_polo_store_be.Model.Entity.Products;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProductRequset {
    private String name;
    private Integer status;
    private String description;
    private Integer categoryId;
    private Integer brandId;
    private Integer materialId;
    private Integer discountId;

}
