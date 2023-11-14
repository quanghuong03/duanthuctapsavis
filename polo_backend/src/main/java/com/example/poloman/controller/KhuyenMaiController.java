package com.example.poloman.controller;

import com.example.poloman.model.entity.KhuyenMai;
import com.example.poloman.service.KhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/khuyenmai")
public class KhuyenMaiController {

    @Autowired
    private KhuyenMaiService khuyenMaiService;

    @GetMapping("/getAll")
    public List<KhuyenMai> getAll() {
        return khuyenMaiService.getAll();
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody KhuyenMai khuyenMai) {
        khuyenMaiService.save(khuyenMai);
        return ResponseEntity.ok(khuyenMai);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody KhuyenMai khuyenMai) {
        khuyenMaiService.save(khuyenMai);
        return ResponseEntity.ok(khuyenMai);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable UUID id) {
        khuyenMaiService.delete(id);
    }
}
