package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Entity.OrderDetail;
import com.example.sd_95_polo_store_be.Model.Request.OrderDetailRequest;

public interface OrderDetailService {
    OrderDetail create(OrderDetailRequest orderDetailRequest, Integer id);
}
