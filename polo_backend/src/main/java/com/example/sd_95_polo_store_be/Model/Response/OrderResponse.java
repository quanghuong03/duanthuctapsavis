package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class OrderResponse {
    private Integer id;
    private String username;
    private String phone;
    private String address;
    private Float totalPrice;
    private Float shipCost;
    private String nameTransaction;
    private String note;
    private Integer status;
    OffsetDateTime confirmDate;
    OffsetDateTime successDate;
    OffsetDateTime shipDate;
    OffsetDateTime createDate;

    private List<OrderDetailResponse> orderDetailResponse;

    public OrderResponse(Integer id, String username, String phone, String address,Float shipCost, Float totalPrice, String nameTransaction, String note, Integer status, OffsetDateTime confirmDate, OffsetDateTime successDate, OffsetDateTime shipDate, OffsetDateTime createDate) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.shipCost = shipCost;
        this.totalPrice = totalPrice;
        this.nameTransaction = nameTransaction;
        this.note = note;
        this.status = status;
        this.confirmDate = confirmDate;
        this.successDate = successDate;
        this.shipDate = shipDate;
        this.createDate = createDate;

    }
}
