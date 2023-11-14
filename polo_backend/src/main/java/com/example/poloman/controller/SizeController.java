package com.example.poloman.controller;

import com.example.poloman.common.Response;
import com.example.poloman.model.entity.Size;
import com.example.poloman.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/size")
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

    @PutMapping("/update/{masize}")
    public ResponseEntity<?> update(@PathVariable Integer masize, @RequestBody Size size) {
        sizeService.save(size);
        return ResponseEntity.ok(size);
    }

    @DeleteMapping("delete/{masize}")
    public void delete(@PathVariable Integer masize) {
        sizeService.delete(masize);
    }
}
