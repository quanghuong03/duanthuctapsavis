package com.example.poloman.controller;

import com.example.poloman.model.entity.ChucVu;
import com.example.poloman.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("chucvu")
public class ChucVuController {

    @Autowired
    private ChucVuService chucVuService;

    @GetMapping("/getAll")
    public List<ChucVu> getAll() {
        return chucVuService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ChucVu chucVu) {
        chucVuService.save(chucVu);
        return ResponseEntity.ok(chucVu);
    }


    @PutMapping("/update/{machucvu}")
    public ResponseEntity<?> update(@PathVariable Integer machucvu, @RequestBody ChucVu chucVu) {
        chucVuService.save(chucVu);
        return ResponseEntity.ok(chucVu);
    }

    @DeleteMapping("delete/{machucvu}")
    public void delete(@PathVariable Integer machucvu) {
        chucVuService.delete(machucvu);
    }


}
