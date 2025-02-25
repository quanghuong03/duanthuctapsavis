package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ImageProductResponse {
    private Integer id;

    private String name;

    private String url_image;

    private Integer status;

    public ImageProductResponse(Integer id, String name, String url_image, Integer status) {
        this.id = id;
        this.name = name;
        this.url_image = url_image;
        this.status = status;
    }
}
