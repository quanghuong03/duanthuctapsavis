package com.example.sd_95_polo_store_be.Controller.Admin;

import com.example.sd_95_polo_store_be.Service.ImageService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PutMapping("/{id}")
    public Response<Void> deleteOne(@PathVariable Integer id) {
        imageService.deleteOne(id);
        return Response.ofSucceeded();
    }
}
