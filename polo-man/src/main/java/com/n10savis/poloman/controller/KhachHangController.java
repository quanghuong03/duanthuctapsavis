package com.n10savis.poloman.controller;

import com.n10savis.poloman.model.entity.KhachHang;
import com.n10savis.poloman.model.request.KhachHangRequest;
import com.n10savis.poloman.model.response.KhachHangResponse;
import com.n10savis.poloman.services.KhachHangSerivce;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/khachhangs")
@CrossOrigin("http://localhost:3000")
public class KhachHangController {
    @Autowired
    private final KhachHangSerivce khachHangSerivce;

    @GetMapping
    public ResponseEntity<List<KhachHangResponse>> getAll(){
        return new ResponseEntity<>(khachHangSerivce.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<KhachHang> add(@RequestBody KhachHangRequest khachHangRequest){
        return new ResponseEntity<>(khachHangSerivce.save(khachHangRequest), HttpStatus.FOUND);
    }
}
