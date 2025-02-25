package com.example.sd_95_polo_store_be.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Ratings extends BaseEntity<Ratings> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String review;

    private Integer star;

    private Integer status;

    @ManyToOne
    @JoinColumn(name = "customerId ")
    private Customers customers;

    @ManyToOne
    @JoinColumn(name = "productDetailId ")
    private ProductDetail productDetail;



    @Override
    protected Ratings self() {
        return this;
    }
}
