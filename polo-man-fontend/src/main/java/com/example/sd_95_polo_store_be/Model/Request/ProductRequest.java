package com.example.sd_95_polo_store_be.Model.Request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ProductRequest {
    private String name;
    private String description;
    private Long categoryId;
    private Integer brandId;
    private Integer materialId;
    private Integer discountId;
    private List<ProductDetailRepuest> productDetailRepuests;

}
