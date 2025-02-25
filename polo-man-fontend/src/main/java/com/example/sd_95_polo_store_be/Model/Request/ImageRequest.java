package com.example.sd_95_polo_store_be.Model.Request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ImageRequest {
    private Integer id;
    private String name;
    private String url_image;
}
