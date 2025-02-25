package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;

@Data
public class StatusVnpayResponse {
    private Boolean status;
    private String massage;

    public StatusVnpayResponse(Boolean status, String massage) {
        this.status = status;
        this.massage = massage;
    }
}
