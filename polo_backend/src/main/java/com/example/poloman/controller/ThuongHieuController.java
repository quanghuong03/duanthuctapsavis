package com.example.poloman.controller;

import com.example.poloman.common.Response;
import com.example.poloman.model.entity.ThuongHieu;
import com.example.poloman.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/thuonghieu")
public class ThuongHieuController {

    @Autowired
    private ThuongHieuService thuongHieuService;

    @GetMapping("/getAll")
    public Response<List<ThuongHieu>> getAll() {
        return Response.ofSucceeded(thuongHieuService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ThuongHieu thuongHieu) {
        thuongHieuService.save(thuongHieu);
        return ResponseEntity.ok(thuongHieu);
    }


    @PutMapping("/update/{mathuonghieu}")
    public ResponseEntity<?> update(@PathVariable Integer mathuonghieu, @RequestBody ThuongHieu thuongHieu) {
        thuongHieuService.save(thuongHieu);
        return ResponseEntity.ok(thuongHieu);
    }

    @DeleteMapping("delete/{mathuonghieu}")
    public void delete(@PathVariable Integer mathuonghieu) {
        thuongHieuService.delete(mathuonghieu);
    }
}
