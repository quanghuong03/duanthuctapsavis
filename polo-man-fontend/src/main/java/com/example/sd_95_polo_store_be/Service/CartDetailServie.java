package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Request.CartRequest;
import com.example.sd_95_polo_store_be.Model.Request.ChangeQuantityCartRequest;
import com.example.sd_95_polo_store_be.Model.Request.ChangeStatusCartRequest;

public interface CartDetailServie {
    public void addCart(CartRequest cartRequest,Integer id);
     void delete(Long cartDetailId);
    public void changeQuantityCart(Long id, ChangeQuantityCartRequest quantityCartRequest);
    void changeStatusCart(Long id, ChangeStatusCartRequest cartResponse);
}
