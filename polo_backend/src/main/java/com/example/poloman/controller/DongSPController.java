package com.example.poloman.controller;

import com.example.poloman.common.Response;
import com.example.poloman.model.entity.DongSP;
import com.example.poloman.model.entity.MauSac;
import com.example.poloman.service.DongSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dongsp")
public class DongSPController {

    @Autowired
    private DongSPService dongSPService;

    @GetMapping("/getAll")
    public Response<List<DongSP>> getAll() {
        return Response.ofSucceeded(dongSPService.getAll());
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody DongSP dongSP) {
        dongSPService.save(dongSP);
        return ResponseEntity.ok(dongSP);
    }

    @PutMapping("/update/{madongsp}")
    public ResponseEntity<?> update(@PathVariable Integer madongsp, @RequestBody DongSP dongSP) {
        dongSPService.save(dongSP);
        return ResponseEntity.ok(dongSP);
    }

    @DeleteMapping("delete/{madongsp}")
    public void delete(@PathVariable Integer madongsp) {
        dongSPService.delete(madongsp);
    }

    @GetMapping("/{madongsp}")
    public Response<DongSP> get(@PathVariable Integer madongsp) {
        return Response.ofSucceeded(dongSPService.getOne(madongsp));
    }

}
