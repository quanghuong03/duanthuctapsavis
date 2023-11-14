package com.example.poloman.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "dongsp")
public class DongSP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer madongsp;

    private String tendongsp;
}
