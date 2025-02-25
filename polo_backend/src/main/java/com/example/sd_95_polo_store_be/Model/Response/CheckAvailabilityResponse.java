package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;

@Data
public class CheckAvailabilityResponse {
    private boolean available;

    public CheckAvailabilityResponse(boolean available) {
        this.available = available;
    }
}
