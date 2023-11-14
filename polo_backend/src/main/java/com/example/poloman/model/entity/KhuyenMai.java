package com.example.poloman.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "khuyenmai")
public class KhuyenMai {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String makhuyenmai;
    private Date ngaytao;
    private Date ngaysua;
    private String ten;
    private Integer mucgiamgia;
    private Date ngaybatdau;
    private Date ngayketthuc;
    private Integer trangthai;

}
