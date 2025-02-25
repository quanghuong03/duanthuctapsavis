package com.example.sd_95_polo_store_be.Model.Request;

import lombok.Data;
import lombok.experimental.Accessors;

//import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class BrandRequest {
//    @NotBlank
    private Integer id;
//    @NotBlank(message = "Tên thương hiệu không được trống")
    private String name;
//    @NotBlank
    private String description;
}
