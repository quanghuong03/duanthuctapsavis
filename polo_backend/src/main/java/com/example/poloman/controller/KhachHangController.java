package com.example.poloman.controller;

import com.example.poloman.model.entity.KhachHang;
import com.example.poloman.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/khachhang")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    @GetMapping("/getAll")
    public List<KhachHang> getAll() {
        return khachHangService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody KhachHang khachHang) {
        khachHangService.save(khachHang);
        return ResponseEntity.ok(khachHang);
    }

    @PutMapping("/update/{makhachhang}")
    public ResponseEntity<?> update(@PathVariable Integer makhachhang, @RequestBody KhachHang khachHang) {
        khachHangService.save(khachHang);
        return ResponseEntity.ok(khachHang);
    }

    @DeleteMapping("delete/{makhachhang}")
    public void delete(@PathVariable Integer makhachhang) {
        khachHangService.delete(makhachhang);
    }
}
