package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Entity.Products;
import com.example.sd_95_polo_store_be.Model.Request.ProductRequest;
import com.example.sd_95_polo_store_be.Model.Request.ProductRequset;
import com.example.sd_95_polo_store_be.Model.Response.GetOneProductResponse;
import com.example.sd_95_polo_store_be.Model.Response.ProductForAdminResponse;
import com.example.sd_95_polo_store_be.Repository.ProductRepository;
import com.example.sd_95_polo_store_be.common.Mapper.EntityMapper;

import java.util.List;

public interface ProductService {
    List<ProductForAdminResponse> getAllProductForAdmin();

    List<ProductForAdminResponse> getAllProductForUser();

    List<ProductForAdminResponse> getAllProduct();

    void addProduct(ProductRequset productRequset);

    public GetOneProductResponse getOne(Integer id);

    public GetOneProductResponse getProductDetail(Integer id);

    void create(ProductRequest productRequest);

    void update(Integer productId, ProductRequest productRequest);

    public void changeStatus(Integer id);

    void changeSatatusDiscount(Integer id);

}
