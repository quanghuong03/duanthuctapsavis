package com.example.poloman.controller;

import com.example.poloman.common.Response;
import com.example.poloman.model.entity.MauSac;
import com.example.poloman.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mausac")
public class MauSacController {

    @Autowired
    private MauSacService mauSacService;

    @GetMapping("/getAll")
    public Response<List<MauSac>> getAll() {
        return Response.ofSucceeded(mauSacService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody MauSac mauSac) {
        mauSacService.save(mauSac);
        return ResponseEntity.ok(mauSac);
    }


    @PutMapping("/update/{mamausac}")
    public ResponseEntity<?> update(@PathVariable Integer mamausac, @RequestBody MauSac mauSac) {
        mauSacService.save(mauSac);
        return ResponseEntity.ok(mauSac);
    }

    @DeleteMapping("delete/{mamausac}")
    public void delete(@PathVariable Integer mamausac) {
        mauSacService.delete(mamausac);
    }
}
