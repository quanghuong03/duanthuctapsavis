package com.example.sd_95_polo_store_be.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "Categories")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Categories extends BaseEntity<Categories>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    private Integer status;


    private String description;

    @Override
    protected Categories self() {
        return this;
    }
}
