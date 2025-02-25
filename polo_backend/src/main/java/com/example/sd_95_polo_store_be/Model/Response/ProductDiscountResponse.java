package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProductDiscountResponse {
    private Integer productId;
    private String productName;
    private Float priceCore;
    private Float discount;

    public ProductDiscountResponse(Integer productId, String productName, Float discount) {
        this.productId = productId;
        this.productName = productName;

        this.discount = discount;
    }
}
