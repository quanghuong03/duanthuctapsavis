package com.example.poloman.controller;

import com.example.poloman.model.entity.NhanVien;
import com.example.poloman.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nhanvien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("/getAll")
    public List<NhanVien> getAll() {
        return nhanVienService.getAll();
    }



    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody NhanVien nhanVien) {
        nhanVienService.save(nhanVien);
        return ResponseEntity.ok(nhanVien);
    }

    @PutMapping("/update/{manhanvien}")
    public ResponseEntity<?> update(@PathVariable Integer manhanvien, @RequestBody NhanVien nhanVien) {
        nhanVienService.save(nhanVien);
        return ResponseEntity.ok(nhanVien);
    }


    @DeleteMapping("delete/{manhanvien}")
    public void delete(@PathVariable Integer manhanvien) {
        nhanVienService.delete(manhanvien);
    }
}
