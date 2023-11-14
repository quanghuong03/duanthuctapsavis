package com.example.poloman.controller;

import com.example.poloman.common.Response;
import com.example.poloman.model.entity.GioHangChiTiet;
import com.example.poloman.model.request.CreateGioHangRequest;
import com.example.poloman.model.request.UpdateSoLuongGioHang;
import com.example.poloman.model.request.UpdateTrangThaiGioHang;
import com.example.poloman.service.GioHangChiTietService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("giohangchitiet")
@RequiredArgsConstructor
@CrossOrigin(value = "*")
public class GioHangChiTietController {

    @Autowired
    private GioHangChiTietService gioHangChiTietService;

    @GetMapping("/getAll")
    public List<GioHangChiTiet> getAll() {
        return gioHangChiTietService.getAll();
    }

    @PostMapping("/add/{makhachhang}")
    public Response<Void> add(@RequestBody CreateGioHangRequest gioHangChiTiet,@PathVariable Integer makhachhang) {
        gioHangChiTietService.add(gioHangChiTiet,makhachhang);
        return Response.ofSucceeded();
    }

    @PutMapping("/update/{magiohang}")
    public ResponseEntity<?> update(@PathVariable Integer magiohang, @RequestBody GioHangChiTiet gioHangChiTiet) {
        gioHangChiTietService.save(gioHangChiTiet);
        return ResponseEntity.ok(gioHangChiTiet);
    }

    @PutMapping("/status/{magiohangchitiet}")
    public Response<Void> updateStatusCart(@PathVariable Integer magiohangchitiet,
                                           @RequestBody UpdateTrangThaiGioHang trangThaiGioHang) {
        gioHangChiTietService.updateTinhTrang(magiohangchitiet, trangThaiGioHang);
        return Response.ofSucceeded();
    }

    @PutMapping("/soluong/{magiohangchitiet}")
    public Response<Void> updatesoluongCart(@PathVariable Integer magiohangchitiet,
                                           @RequestBody UpdateSoLuongGioHang updateSoLuongGioHang) {
        gioHangChiTietService.updatesoluong(magiohangchitiet, updateSoLuongGioHang);
        return Response.ofSucceeded();
    }

    @DeleteMapping("delete/{magiohang}")
    public void delete(@PathVariable Integer magiohang) {
        gioHangChiTietService.delete(magiohang);
    }


}
