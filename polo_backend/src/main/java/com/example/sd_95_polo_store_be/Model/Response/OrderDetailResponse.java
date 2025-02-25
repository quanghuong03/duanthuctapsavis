package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderDetailResponse {
    private Integer id;
    private Integer productDetailId;
    private String nameProduct;
    private String nameSize;
    private String nameColor;
    private Float price;
    private Integer quantity;
    private String image;

    public OrderDetailResponse(Integer id, Integer productDetailId, String nameProduct, String nameSize, String nameColor, Float price, Integer quantity) {
        this.id = id;
        this.productDetailId = productDetailId;
        this.nameProduct = nameProduct;
        this.nameSize = nameSize;
        this.nameColor = nameColor;
        this.price = price;
        this.quantity = quantity;

    }
}
