package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomerForAdminResponse {
    private Integer id;

    private String name;

    private String email;

    private String phone;

    private String avatar;

    public CustomerForAdminResponse(Integer id, String name, String email, String phone, String avatar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
    }
}
