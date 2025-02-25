package com.example.sd_95_polo_store_be.Model.Request;

import lombok.Data;

@Data
public class ChangeStatusImage {
    private Integer status;



    public Integer status() {
        return status;
    }
}
