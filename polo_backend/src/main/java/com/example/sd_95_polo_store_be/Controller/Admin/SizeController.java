package com.example.sd_95_polo_store_be.Controller.Admin;

import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.Sizes;
import com.example.sd_95_polo_store_be.Service.SizeService;
import com.example.sd_95_polo_store_be.common.Response;
import org.hibernate.engine.jdbc.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/size")
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @GetMapping("")
    public Response<List<Sizes>> getsByStatus() {
        return Response.ofSucceeded(sizeService.getSizesByStatus());
    }
    @GetMapping("/getAll")
    public Response<List<Sizes>> gets() {
        return Response.ofSucceeded(sizeService.gets());
    }

    @PostMapping("/add")
    public Response<Sizes> createOrUpdateSize(@RequestBody Sizes sizes) {
        try {
           Sizes size = sizeService.createOrUpdate(sizes);
            return Response.ofSucceeded(size);
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Response<Sizes> updateSize(@PathVariable Integer id, @RequestBody Sizes sizes) {
        try {
            sizes.setId(id);
            Sizes size = sizeService.createOrUpdate(sizes);
            return Response.ofSucceeded(size);
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }
    }

    @DeleteMapping()
    public Response<List<Integer>> deleteSize(@RequestBody Map<String, List<Integer>> request) {
        List<Integer> ids = request.get("id");
        try {
            sizeService.deleteSizeByIds(ids);
            return Response.ofSucceeded();
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }

    }
    @PutMapping("changeStatus/{id}")
    public Response<Void> changeBran(@PathVariable Long id){
        sizeService.changeStatus(id);
        return Response.ofSucceeded();
    }
    @GetMapping("/{id}")
    public Response<Sizes> get(@PathVariable Long id) {
        return Response.ofSucceeded(sizeService.getOne(id));
    }
}
