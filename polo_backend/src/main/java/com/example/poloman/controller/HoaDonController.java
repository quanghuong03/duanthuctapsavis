package com.example.poloman.controller;

import com.example.poloman.common.Response;
import com.example.poloman.model.entity.HoaDon;
import com.example.poloman.model.entity.KhachHang;
import com.example.poloman.model.reponse.HoaDonResponse;
import com.example.poloman.model.request.CancelHoaDonRequest;
import com.example.poloman.model.request.DoiTrangThaiRequest;
import com.example.poloman.model.request.HoaDonRequset;
import com.example.poloman.model.request.UpdateHoaDonRequest;
import com.example.poloman.service.HoaDonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/hoadon")
@RequiredArgsConstructor
@CrossOrigin(value = "*")
public class HoaDonController {

    @Autowired
    private HoaDonService hoaDonService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody HoaDon hoaDon) {
        hoaDonService.save(hoaDon);
        return ResponseEntity.ok(hoaDon);
    }



    @DeleteMapping("delete/{mahoadon}")
    public void delete(@PathVariable Integer mahoadon) {
        hoaDonService.delete(mahoadon);
    }


    @PostMapping("/add/{makhachhang}")
    public Response<HoaDon> taohoadon(@RequestBody HoaDonRequset hoaDonRequset,@PathVariable Integer makhachhang) {
        System.out.println(makhachhang);
        hoaDonService.hoaDonOnline(hoaDonRequset, makhachhang);
        return Response.ofSucceeded();
    }

    @GetMapping("/{makhachhang}")
    public Response<List<HoaDonResponse>> gets(@PathVariable Integer makhachhang) {
        return Response.ofSucceeded(hoaDonService.getHoaDon(makhachhang));
    }

    @GetMapping()
    public Response<List<HoaDonResponse>> getAll() {
        return Response.ofSucceeded(hoaDonService.getHoaDon());
    }

    @GetMapping("/admin/{mahoadon}")
    public Response<HoaDonResponse> getOne(@PathVariable Integer mahoadon) {
        return Response.ofSucceeded(hoaDonService.get(mahoadon));
    }

    @PutMapping("/{mahoadon}")
    public Response<Void> requestCancelOrder(@RequestBody CancelHoaDonRequest cancelHoaDonRequest,
                                             @PathVariable Integer mahoadon) {
        hoaDonService.requestCancel(cancelHoaDonRequest, mahoadon);
        return Response.ofSucceeded();
    }

    @PutMapping("/status/{mahoadon}")
    public Response<Void> completeOrder(@RequestBody DoiTrangThaiRequest doiTrangThaiRequest,
                                        @PathVariable Integer mahoadon) throws IOException, InterruptedException {
        hoaDonService.doitrangthai(mahoadon, doiTrangThaiRequest);
        return Response.ofSucceeded();
    }


    @PutMapping("update/{mahoadon}")
    public Response<?> changeProfileUserOrder(@RequestBody UpdateHoaDonRequest updateHoaDonRequest, @PathVariable Integer mahoadon) {
        hoaDonService.updateHoaDon(updateHoaDonRequest, mahoadon);
        return Response.ofSucceeded();
    }
}
