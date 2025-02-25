package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DiscountProductDetailReponse {
    private Integer productDetailId;
    private String nameProdcut;
    private Float cost;
    private Float discount;

    public DiscountProductDetailReponse(Integer productDetailId, String nameProdcut, Float cost, Float discount) {
        this.productDetailId = productDetailId;
        this.nameProdcut = nameProdcut;
        this.cost = cost;
        this.discount = discount;
    }
}
