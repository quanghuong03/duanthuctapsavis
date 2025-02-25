package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AddressResponse {
    private Integer id;

    private String city;

    private String district;

    private String ward;

    private String fullAddress;

    private Integer status;

    public AddressResponse(Integer id, String city, String district, String ward, String fullAddress, Integer status) {
        this.id = id;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.fullAddress = fullAddress;
        this.status = status;
    }
}
