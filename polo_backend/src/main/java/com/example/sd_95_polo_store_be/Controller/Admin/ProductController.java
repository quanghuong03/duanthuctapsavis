package com.example.sd_95_polo_store_be.Controller.Admin;

import com.example.sd_95_polo_store_be.Model.Entity.Products;
import com.example.sd_95_polo_store_be.Model.Request.ProductRequest;
import com.example.sd_95_polo_store_be.Model.Request.ProductRequset;
import com.example.sd_95_polo_store_be.Model.Response.GetOneProductResponse;
import com.example.sd_95_polo_store_be.Model.Response.ProductForAdminResponse;
import com.example.sd_95_polo_store_be.Repository.ProductRepository;
import com.example.sd_95_polo_store_be.Service.ProductService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Response<List<ProductForAdminResponse>> getAll() {
        return Response.ofSucceeded(productService.getAllProductForAdmin());
    }

    @GetMapping("/homepage")
    public Response<List<ProductForAdminResponse>> getProductForHomePage() {
        return Response.ofSucceeded(productService.getAllProductForUser());
    }

    @GetMapping("/discount")
    public Response<List<ProductForAdminResponse>> getDiscount() {
        return Response.ofSucceeded(productService.getAllProduct());
    }

    @PostMapping
    public Response<Products> addProduct(@RequestBody ProductRequset requset) {
        try {
            productService.addProduct(requset);
            return Response.ofSucceeded();
        } catch (Exception e) {
            return Response.ofError("Failed to add product: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public Response<?> add(@RequestBody ProductRequest productRequest) {
        productService.create(productRequest);
        return Response.ofSucceeded();
    }

    @PutMapping("/update/{productId}")
    public Response<?> update(@PathVariable Integer productId, @RequestBody ProductRequest productRequest) {
        productService.update(productId,productRequest);
        return Response.ofSucceeded();
    }

    @GetMapping("/{id}")
    public Response<GetOneProductResponse> getOne(@PathVariable Integer id) {
        return Response.ofSucceeded(productService.getOne(id));
    }

    @PutMapping("changeStatus/{id}")
    public Response<Void> changeStatus(@PathVariable Integer id) {
        productService.changeStatus(id);
        return Response.ofSucceeded();
    }

    @PutMapping("changeStautsDiscount/{id}")
    public Response<Void> changeStautsDiscount(@PathVariable Integer id) {
        productService.changeSatatusDiscount(id);
        return Response.ofSucceeded();
    }

    @GetMapping("/productDetail/{id}")
    public Response<GetOneProductResponse> getProductDetaul(@PathVariable Integer id) {
        return Response.ofSucceeded(productService.getProductDetail(id));
    }
}
