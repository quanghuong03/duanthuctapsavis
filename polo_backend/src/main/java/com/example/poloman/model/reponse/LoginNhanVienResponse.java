package com.example.poloman.model.reponse;

import com.example.poloman.model.entity.ChucVu;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Date;

@Data
@Accessors(chain = true)
public class LoginNhanVienResponse {

    private Integer manhanvien;
    private String tennhanvien;
    private Date ngaysinh;
    private String sodienthoai;
    private String diachi;
    private String email;
    private Boolean gioitinh;
    private int idChucVu;
}
