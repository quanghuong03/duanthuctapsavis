package com.example.sd_95_polo_store_be.Controller.Admin;


import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.Sizes;
import com.example.sd_95_polo_store_be.Model.Response.GetOneProductResponse;
import com.example.sd_95_polo_store_be.Service.ColorServices;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/admin/color")
@RestController
public class ClolorController {
    @Autowired
    private ColorServices colorServices;

    @GetMapping("")
    public Response<List<Colors>> getsByStatus() {
        return Response.ofSucceeded(colorServices.getColorByStatus());
    }
    @GetMapping("/getAll")
    public Response<List<Colors>> getAll() {
        return Response.ofSucceeded(colorServices.gets());
    }

    @PostMapping("/add")
    public Response<Colors> create(@RequestBody Colors color) {
        try {
            colorServices.saveColor(color);
            return Response.ofSucceeded();
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public Response<Colors> update(@PathVariable Long id, @RequestBody Colors color) {
        try {
            color.setId(id);
          colorServices.saveColor(color);
            return Response.ofSucceeded();
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        try {
            colorServices.deleteColorById(id);
            return ResponseEntity.ok("Màu sắc đã được xóa thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/get-page")
    public List<?> getPage(@RequestParam(defaultValue = "0") int p) {
        Pageable pageable = PageRequest.of(p, 5);
        Page<Colors> page = colorServices.findAllColor(pageable);
        return page.toList();
    }

    @DeleteMapping("/delete")
    public Response<List<Long>> deleteMultiple(@RequestBody Map<String, List<Long>> request) {
        List<Long> ids = request.get("id");
        try {
            colorServices.deleteColorsByIds(ids);
            return Response.ofSucceeded();
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }

    }
    @PutMapping("changeStatus/{id}")
    public Response<Void> changeBran(@PathVariable Long id){
        colorServices.changeStatus(id);
        return Response.ofSucceeded();
    }
    @GetMapping("/{id}")
    public Response<Colors> get(@PathVariable Long id) {
        return Response.ofSucceeded(colorServices.getOne(id));
    }
}
