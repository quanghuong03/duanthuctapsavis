package com.example.sd_95_polo_store_be.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Float price;

    private Integer status;

    @ManyToOne
    @JoinColumn(name = "cartId ")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "productDetailId ")
    private ProductDetail productDetail;

}
