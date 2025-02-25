package com.example.sd_95_polo_store_be.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cart extends BaseEntity<Cart> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer status;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customers customers;
    
    @Override
    protected Cart self() {
        return this;
    }
}
