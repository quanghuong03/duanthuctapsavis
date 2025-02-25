package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class GetOneProductResponse {
    private Integer id;
    private String name;
    private Integer status;
    private String description;
    private Long categoryId;
    private Integer brandId;
    private Integer materialId;
    private String nameBrand;
    private String nameCategory;
    private String nameMaterial;
    private Integer discountId;
    private Float discount;
    private String nameDiscount;
    private Integer promotionPercent;
    List<ProductDetailResponse> productDetails;


    public GetOneProductResponse(Integer id, String name, Integer status, String description, Long categoryId, Integer brandId, Integer materialId, String nameBrand, String nameCategory, String nameMaterial,Integer discountId,Float discount,String nameDiscount) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.description = description;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.materialId = materialId;
        this.nameBrand = nameBrand;
        this.nameCategory = nameCategory;
        this.nameMaterial = nameMaterial;
        this.discountId = discountId;
        this.discount = discount;
        this.nameDiscount= nameDiscount;
    }
}
