package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CartResponse {
    private Integer id;
    private List<CartDetailResponse> cartDetailResponses;

    public CartResponse(Integer id, List<CartDetailResponse> cartDetailResponses) {
        this.id = id;
        this.cartDetailResponses = cartDetailResponses;
    }
}
