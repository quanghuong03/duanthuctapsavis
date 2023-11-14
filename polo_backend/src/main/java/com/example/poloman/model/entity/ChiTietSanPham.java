package com.example.poloman.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "chitietsanpham")
public class ChiTietSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mactsp;

    private Integer soluongton;
    private Integer mavach;

    @ManyToOne
    @JoinColumn(name = "sanpham")
    private SanPham sanpham;

    @ManyToOne
    @JoinColumn(name = "mausac")
    private MauSac mausac;

    @ManyToOne
    @JoinColumn(name = "size")
    private Size size;


}
