package com.example.poloman.controller;


import com.example.poloman.model.entity.DanhSachYeuThich;
import com.example.poloman.service.DanhSachYeuThichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/danhsachyeuthich")
public class DanhSachYeuThichController {

    @Autowired
    private DanhSachYeuThichService danhSachYeuThichService;

    @GetMapping("/getAll")
    public List<DanhSachYeuThich> getAll() {
        return danhSachYeuThichService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody DanhSachYeuThich danhSachYeuThich) {
        danhSachYeuThichService.save(danhSachYeuThich);
        return ResponseEntity.ok(danhSachYeuThich);
    }

    @PutMapping("/update/{madanhsach}")
    public ResponseEntity<?> update(@PathVariable UUID madanhsach, @RequestBody DanhSachYeuThich danhSachYeuThich) {
        danhSachYeuThichService.save(danhSachYeuThich);
        return ResponseEntity.ok(danhSachYeuThich);
    }


    @DeleteMapping("delete/{madanhsach}")
    public void delete(@PathVariable UUID madanhsach) {
        danhSachYeuThichService.delete(madanhsach);
    }

}
