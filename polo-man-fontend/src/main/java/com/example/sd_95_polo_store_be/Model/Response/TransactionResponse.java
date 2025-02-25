package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;

@Data
public class TransactionResponse {
    private Boolean status;
    private String url;

    public TransactionResponse(Boolean status, String url) {
        this.status = status;
        this.url = url;
    }
}
