package com.example.sd_95_polo_store_be.Model.Request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class OrderRequest {
    private String phone;
    private String username;
    private String address;
    private String shopping;
    private Float totalPrice;
    private Float shipCost;
    private Float weight;
    private Integer transactionId;

    List<OrderDetailRequest> orderDetailRequest;
}
