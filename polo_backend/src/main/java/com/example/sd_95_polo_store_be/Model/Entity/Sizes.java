package com.example.sd_95_polo_store_be.Model.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Sizes extends BaseEntity<Sizes> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;


    private Integer status;


    private String description;

    private Integer shirtlength;

    private Integer shirtwidth;

    private Integer sleevelenght;

    private Integer shoulderlength;

    @Override
    protected Sizes self() {
        return this;
    }
}
