package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdminResponse {
    private Integer id;
    private String nameAdmin;
    private String email;
    private String phone;
    private String avatar;
    private String address;
    private String password;
    private Integer status;
    private Integer roleId;
    private String nameRole;
}
