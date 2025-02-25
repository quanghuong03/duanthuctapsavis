package com.example.sd_95_polo_store_be.Model.Request;

import lombok.Data;

import java.util.List;

@Data
public class AddDiscountToProductRequest {
    private List<Integer> idProduct;
    private Integer idDiscount;
}
