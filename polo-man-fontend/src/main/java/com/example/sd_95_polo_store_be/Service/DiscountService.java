package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Entity.Discount;
import com.example.sd_95_polo_store_be.Model.Request.AddDiscountToProductRequest;
import com.example.sd_95_polo_store_be.Model.Response.DiscountResponse;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

public interface DiscountService {
    List<DiscountResponse> gets();

    void expireDiscounts();

    List<Discount> getAll();

    void addDiscount(AddDiscountToProductRequest addDiscountToProductRequest);

    Discount addDiscount(Discount discount);

    void changeStatus(Integer id);
}
