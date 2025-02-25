package com.example.sd_95_polo_store_be.Model.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Address extends BaseEntity<Address> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String city;

    private String district;

    private String ward;

    private String fullAddress;

    private Integer status;



    @Override
    protected Address self() {
        return this;
    }
}
