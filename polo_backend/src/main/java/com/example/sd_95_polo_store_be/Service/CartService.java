package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Response.CartResponse;

public interface CartService {
    CartResponse getOne(Integer id);
    CartResponse getOneByStatus(Integer id);
}
