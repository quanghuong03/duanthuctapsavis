package com.example.poloman.controller;

import com.example.poloman.common.Response;
import com.example.poloman.model.entity.SanPham;
import com.example.poloman.model.reponse.SanPhamOverviewResponse;
import com.example.poloman.model.reponse.SanPhamResponse;
import com.example.poloman.model.request.SanPhamRequest;
import com.example.poloman.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sanpham")
public class SanPhamController {

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/getAll")
    public Response<List<SanPham>> getAll() {
        return Response.ofSucceeded(sanPhamService.getAll());
    }

    @GetMapping("/findAll")
    public Response<List<SanPhamOverviewResponse>> findAll() {
        return Response.ofSucceeded(sanPhamService.getAllSanPham());
    }

    @PostMapping("/add")
    public Response<?> add(@RequestBody SanPhamRequest sanPhamRequest) {
        sanPhamService.create(sanPhamRequest);
        return Response.ofSucceeded();
    }

    @GetMapping("/{masanpham}")
    public Response<SanPhamResponse> getOne(@PathVariable Integer masanpham) {
        return Response.ofSucceeded(sanPhamService.getOne(masanpham));
    }


    @PutMapping("/update/{masanpham}")
    public Response<?> update(@PathVariable Integer masanpham, @RequestBody SanPhamRequest sanPhamRequest) {
        sanPhamService.update(masanpham, sanPhamRequest);

        return Response.ofSucceeded();
    }

    @DeleteMapping("delete/{masanpham}")
    public void delete(@PathVariable Integer masanpham) {
        sanPhamService.delete(masanpham);
    }
}
