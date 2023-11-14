package com.example.poloman.controller;

import com.example.poloman.common.Response;
import com.example.poloman.model.entity.GioHang;
import com.example.poloman.model.reponse.GioHangResponse;
import com.example.poloman.service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/giohang")
public class GioHangController {

    @Autowired
    private GioHangService gioHangService;

    @GetMapping("/getAll")
    public List<GioHang> getAll() {
        return gioHangService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody GioHang gioHang) {
        gioHangService.save(gioHang);
        return ResponseEntity.ok(gioHang);
    }

    @PutMapping("/update/{magiohang}")
    public ResponseEntity<?> update(@PathVariable Integer magiohang, @RequestBody GioHang gioHang) {
        gioHangService.save(gioHang);
        return ResponseEntity.ok(gioHang);
    }

    @GetMapping("/{makhachhang}")
    public Response<GioHangResponse> getOne(@PathVariable Integer makhachhang){
        return Response.ofSucceeded(gioHangService.getOne(makhachhang));
    }

    @DeleteMapping("delete/{magiohang}")
    public void delete(@PathVariable Integer magiohang) {
        gioHangService.delete(magiohang);
    }


    @GetMapping("order/{makhachhang}")
    public Response<GioHangResponse> getOneByTrangThai(@PathVariable Integer makhachhang){
        return Response.ofSucceeded(gioHangService.getOneByTrangThai(makhachhang));
    }
}
