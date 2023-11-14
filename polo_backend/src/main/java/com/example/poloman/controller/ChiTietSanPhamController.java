package com.example.poloman.controller;

import com.example.poloman.common.Response;
import com.example.poloman.model.entity.ChiTietSanPham;
import com.example.poloman.model.reponse.SanPhamResponse;
import com.example.poloman.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ctsp")
public class ChiTietSanPhamController {

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @GetMapping("/getAll")
    public List<ChiTietSanPham> getAll() {
        return chiTietSanPhamService.getAll();
    }

    @GetMapping("/{masanpham}")
    public Response<List<ChiTietSanPham>> getOne(@PathVariable Integer masanpham){
        return Response.ofSucceeded(chiTietSanPhamService.findOne(masanpham));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ChiTietSanPham chiTietSanPham) {
        chiTietSanPhamService.save(chiTietSanPham);
        return ResponseEntity.ok(chiTietSanPham);
    }

    @PutMapping("/update/{mactsp}")
    public ResponseEntity<?> update(@PathVariable Integer mactsp, @RequestBody ChiTietSanPham chiTietSanPham) {
        chiTietSanPhamService.save(chiTietSanPham);
        return ResponseEntity.ok(chiTietSanPham);
    }

    @DeleteMapping("delete/{mactsp}")
    public void delete(@PathVariable Integer mactsp) {
        chiTietSanPhamService.delete(mactsp);
    }

}
