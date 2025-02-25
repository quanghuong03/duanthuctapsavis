package com.example.sd_95_polo_store_be.Model.Request;

import lombok.Data;

@Data
public class CartRequest {
    private Integer id;
    private Integer productDetailId;
    private Integer quantity;
}
