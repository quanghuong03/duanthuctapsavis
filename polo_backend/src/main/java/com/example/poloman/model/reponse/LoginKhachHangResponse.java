package com.example.poloman.model.reponse;

import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Date;
@Data
@Accessors(chain = true)
public class LoginKhachHangResponse {
    private Integer makhachhang;
    private String tenkhachhang;
    private Date ngaysinh;
    private String sodienthoai;
    private String diachi;
    private String email;
    private Boolean gioitinh;

}
