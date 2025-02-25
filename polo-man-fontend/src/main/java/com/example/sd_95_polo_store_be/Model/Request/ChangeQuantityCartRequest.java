package com.example.sd_95_polo_store_be.Model.Request;

import lombok.Data;

@Data
public class ChangeQuantityCartRequest {
    private Integer idProductDetail;
    private Integer quantity;


}
