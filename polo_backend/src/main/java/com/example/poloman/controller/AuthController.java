package com.example.poloman.controller;

import com.example.poloman.common.Response;
import com.example.poloman.model.reponse.LoginKhachHangResponse;
import com.example.poloman.model.reponse.LoginNhanVienResponse;
import com.example.poloman.model.request.LoginRequest;
import com.example.poloman.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public Response<LoginNhanVienResponse> login(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest);
        return Response.ofSucceeded(authService.loginAdmin(loginRequest));
    }

    @PostMapping("/loginKhachHang")
    public Response<LoginKhachHangResponse> loginKhachHang(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest);
        return Response.ofSucceeded(authService.loginKhachHang(loginRequest));
    }
}
