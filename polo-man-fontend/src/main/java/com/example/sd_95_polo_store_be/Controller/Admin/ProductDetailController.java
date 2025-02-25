package com.example.sd_95_polo_store_be.Controller.Admin;

import com.example.sd_95_polo_store_be.Dto.ProductDetailDto;
import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Model.Request.ProductDetailRepuest;

import com.example.sd_95_polo_store_be.Model.Response.ProductDetailResponse;
import com.example.sd_95_polo_store_be.Service.ProductDetailService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/ProductDetail")
@RestController
public class ProductDetailController {
    @Autowired
    ProductDetailService productDetailService;



    @GetMapping()
    public Response<List<ProductDetailResponse>> getAll() {
        return Response.ofSucceeded(productDetailService.getList());
    }

    @PostMapping("/add")
    public Response<ProductDetailDto> create(@RequestBody ProductDetailRepuest request) {
        try {
            ProductDetail savedProductDetail = productDetailService.saveProductDetail(request);
            return Response.ofSucceeded(new ProductDetailDto(savedProductDetail));
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public Response<ProductDetailDto> update(@PathVariable Long id, @RequestBody ProductDetailRepuest request) {
        try {
//            request.getIdProductDetail(id);
            ProductDetail productDetail = productDetailService.saveProductDetail(request);
            return Response.ofSucceeded(new ProductDetailDto(productDetail));
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }
    }


}
