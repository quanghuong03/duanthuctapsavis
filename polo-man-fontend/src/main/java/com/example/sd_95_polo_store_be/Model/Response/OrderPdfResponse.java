package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;

@Data
public class OrderPdfResponse {
    private Integer id;
    private String phone;
    private String username;
    private String address;
    private Float shipCost;
    private Float totalPrice;

    public OrderPdfResponse(Integer id, String phone, String username, String address, Float shipCost, Float totalPrice) {
        this.id = id;
        this.phone = phone;
        this.username = username;
        this.address = address;
        this.shipCost = shipCost;
        this.totalPrice = totalPrice;
    }
}
