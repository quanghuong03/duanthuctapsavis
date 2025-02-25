package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Model.Request.ProductDetailRepuest;
import com.example.sd_95_polo_store_be.Model.Response.ProductDetailResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface ProductDetailService {
    ArrayList<ProductDetail> getAllProductDetail();

    ProductDetail saveProductDetail(ProductDetailRepuest request);

    void createOrUpdate(ProductDetailRepuest productDetailRequest,Integer productId);

//    void deleteProductDetailById(List<Long> ids);

    List<ProductDetailResponse> getForProduct(Integer productId);

    List<ProductDetailResponse> getList();

}
