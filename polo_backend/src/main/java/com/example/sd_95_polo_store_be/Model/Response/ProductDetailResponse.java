package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ProductDetailResponse {
    private Integer productDetailId;
    private Integer sizeId;
    private Long colorId;
    private String nameProduct;
    private String nameSize;
    private String nameColor;
    private Integer quantity;
    private Float cost;
    private Float price;
    private Float weight;
    private Integer status;
    private Float pricecost;
    private Integer discoutId;
    private Float discount;
    private Integer statusProduct;

    List<ImageProductResponse> images;

    public ProductDetailResponse(Integer productDetailId, Integer sizeId, Long colorId,String nameProduct, String nameSize, String nameColor, Integer quantity, Float cost, Float price,Float weight , Integer status,Integer discountId,Float discount,Integer statusProduct) {
        this.productDetailId = productDetailId;
        this.sizeId = sizeId;
        this.colorId = colorId;
        this.nameProduct = nameProduct;
        this.nameSize = nameSize;
        this.nameColor = nameColor;
        this.quantity = quantity;
        this.cost = cost;
        this.price = price;
        this.weight = weight;
        this.status = status;
        this.discoutId = discountId;
        this.discount = discount;
        this.statusProduct = statusProduct;
    }
}
