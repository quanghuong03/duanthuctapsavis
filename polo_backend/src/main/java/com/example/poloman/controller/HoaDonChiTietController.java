package com.example.poloman.controller;

import com.example.poloman.model.entity.HoaDonChiTiet;
import com.example.poloman.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hoadonchitiet")
public class HoaDonChiTietController {

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @GetMapping("/getAll")
    public List<HoaDonChiTiet> getAll() {
        return hoaDonChiTietService.getAll();
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTietService.save(hoaDonChiTiet);
        return ResponseEntity.ok(hoaDonChiTiet);
    }

    @PutMapping("/update/{mahoadon}")
    public ResponseEntity<?> update(@PathVariable Integer mahoadon, @RequestBody HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTietService.save(hoaDonChiTiet);
        return ResponseEntity.ok(hoaDonChiTiet);
    }

    @DeleteMapping("delete/{mahoadon}")
    public void delete(@PathVariable Integer mahoadon) {
        hoaDonChiTietService.delete(mahoadon);
    }

}
