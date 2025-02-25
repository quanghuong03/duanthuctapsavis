package com.example.sd_95_polo_store_be.Model.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Customers extends BaseEntity<Customers> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private String phone;

    private String avatar;

    private String password;

    private String status;


    @Override
    protected Customers self() {
        return this;
    }
}
