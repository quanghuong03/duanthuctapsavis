package com.example.sd_95_polo_store_be.Dto;

import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Model.Entity.Products;
import com.example.sd_95_polo_store_be.Model.Entity.Sizes;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Date;

@Data
public class ProductDetailDto {
    Integer id;
    Integer quantity;
    Double cost;
    Double price;
    Integer status;
    String description;
    String nameProduct;
    String nameSize;
    String nameColor;
    OffsetDateTime create_date;
    OffsetDateTime update_date;


    public ProductDetailDto(ProductDetail productDetail) {
        id = productDetail.getId();
        quantity = productDetail.getQuantity();
//        cost = productDetail.getCost();
//        price = productDetail.getPrice();
        status = productDetail.getStatus();
        create_date = productDetail.getCreateDate();
        update_date = productDetail.getUpdateDate();
        Products products = productDetail.getProducts();
        if (products != null) {
            nameProduct = products.getName();
        }
        Sizes sizes = productDetail.getSizes();
        if (sizes != null) {
            nameSize = sizes.getName();
        }
        Colors colors = productDetail.getColors();
        if (colors != null) {
            nameColor = colors.getName();
        }

    }
}
