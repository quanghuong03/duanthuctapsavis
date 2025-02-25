package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;

@Data
public class OrderDetailPdfResponse {
    private String name;
    private Integer quantity;
    private Float price;

    public OrderDetailPdfResponse(String name, Integer quantity, Float price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
