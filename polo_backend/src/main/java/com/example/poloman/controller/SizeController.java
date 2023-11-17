package com.example.poloman.controller;

import com.example.poloman.common.Response;
import com.example.poloman.model.entity.MauSac;
import com.example.poloman.model.entity.Size;
import com.example.poloman.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/size")
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @GetMapping("/getAll")
    public Response<List<Size>> getAll() {
        return Response.ofSucceeded(sizeService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Size size) {
        sizeService.save(size);
        return ResponseEntity.ok(size);
    }

    @PutMapping("/{masize}")
    public Response<?> update(@PathVariable Integer masize) {
        return Response.ofSucceeded(sizeService.getOne(masize));
    }

    @DeleteMapping("delete/{masize}")
    public void delete(@PathVariable Integer masize) {
        sizeService.delete(masize);
    }

    @GetMapping("/{masize}")
    public Response<Size> get(@PathVariable Integer masize) {
        return Response.ofSucceeded(sizeService.getOne(masize));
    }
}
