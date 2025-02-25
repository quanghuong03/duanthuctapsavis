package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Discount;
import com.example.sd_95_polo_store_be.Model.Entity.Products;
import com.example.sd_95_polo_store_be.Model.Request.AddDiscountToProductRequest;
import com.example.sd_95_polo_store_be.Model.Response.DiscountResponse;
import com.example.sd_95_polo_store_be.Repository.DiscountRepository;
import com.example.sd_95_polo_store_be.Repository.ProductRepository;
import com.example.sd_95_polo_store_be.Service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<DiscountResponse> gets() {
        expireDiscounts();
        productRepository.updateProductsForExpiredDiscounts();
        return discountRepository.gets();
    }

    @Override
    public void expireDiscounts() {
        discountRepository.expireActiveDiscounts();
    }

    @Override
    public List<Discount> getAll() {
        return discountRepository.findAll();
    }

    @Override
    public void addDiscount(AddDiscountToProductRequest addDiscountToProductRequest) {
        List<Products> product = productRepository.findAllById(addDiscountToProductRequest.getIdProduct());
        var discount = discountRepository.findById(addDiscountToProductRequest.getIdDiscount()).orElseThrow();
        for (Products products : product) {
            products.setDiscount(discount);
            if(discount.getStatus() == 1 && discount.getId() != 1){
                products.setStatus(3);
                productRepository.save(products);
            }else{
                products.setStatus(1);
                productRepository.save(products);
            }

    ;
        }
    }

    @Override
    public Discount addDiscount(Discount discount) {
        Discount newDiscount = new Discount();
        newDiscount.setDiscount(discount.getDiscount());
        newDiscount.setStatus(1);
        newDiscount.setEndDate(discount.getEndDate());
        newDiscount.setStartDate(discount.getStartDate());
        newDiscount.setName(discount.getName());
        newDiscount.setDescription(discount.getDescription());
        discountRepository.save(newDiscount);
        return newDiscount;
    }

    @Override
    public void changeStatus(Integer id) {
        var discount = discountRepository.findById(id).orElseThrow();
        if (discount.getStatus() == 0 ){
            discount.setStatus(1);
            discountRepository.save(discount);
        } else if(discount.getStatus() == 1){
            discount.setStatus(0);
            discountRepository.save(discount);
        } else  if (discount.getStatus() == 2) {

            discount.setStatus(1);
            discountRepository.save(discount);
        }
    }
}
