package com.example.poloman.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "size")

public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer masize;

    private String sosize;

    private String chieucao;

    private String cannang;
}
