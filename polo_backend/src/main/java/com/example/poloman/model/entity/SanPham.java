package com.example.poloman.model.entity;

import com.dslplatform.json.CompiledJson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "sanpham")
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer masanpham;

    private String tensanpham;
    private Double giaban;
    private Double gianhap;
    private String hinhanh;
    private Integer luotmua;
    private String mota;

    @ManyToOne
    @JoinColumn(name = "thuonghieu")
    private ThuongHieu thuonghieu;

    @ManyToOne
    @JoinColumn(name = "chatlieu")
    private ChatLieu chatlieu;

    @ManyToOne
    @JoinColumn(name = "kieuao")
    private DongSP kieuao;
}
