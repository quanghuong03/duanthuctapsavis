package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CustomerResponse {
    private Integer id;

    private String name;

    private String email;

    private String phone;

    private String avatar;

    private String status;

    private List<AddressResponse> address;

    public CustomerResponse(Integer id, String name, String email, String phone, String avatar, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.status = status;

    }
}
