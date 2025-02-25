package com.example.sd_95_polo_store_be.Model.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
@Data
@Accessors(chain = true)
public class DiscountResponse {
    private Integer id;
    private String name;
    private Float discount;
    private String description;
    private Float priceCore;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime endDate;
    private Integer status;

    public DiscountResponse(Integer id, String name, Float discount, String description, LocalDateTime startDate, LocalDateTime endDate, Integer status) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
}
