package com.example.poloman.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "yeuthichchitiet")
public class YeuThichChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID madanhsach;

    private String ghichu;
    private Integer trangthai;

    @ManyToOne
    @JoinColumn(name = "danhsach")
    private DanhSachYeuThich danhsach;

    @ManyToOne
    @JoinColumn(name = "masanpham")
    private SanPham masanpham;
}
