package com.example.sd_95_polo_store_be.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OrderDetail extends BaseEntity<OrderDetail> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;

    private Float price;

    private Integer status;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "productDetailId ")
    private ProductDetail productDetail;


    @Override
    protected OrderDetail self() {
        return this;
    }
}
