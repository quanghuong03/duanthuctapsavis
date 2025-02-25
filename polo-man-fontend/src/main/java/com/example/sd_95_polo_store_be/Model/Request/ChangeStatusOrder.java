package com.example.sd_95_polo_store_be.Model.Request;

import lombok.Data;

@Data
public class ChangeStatusOrder {
    private Integer status;
    private String note;
    private Float shipCost;

}
