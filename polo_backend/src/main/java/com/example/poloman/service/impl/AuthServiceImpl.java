package com.example.poloman.service.impl;

import com.example.poloman.model.entity.KhachHang;
import com.example.poloman.model.entity.NhanVien;
import com.example.poloman.model.reponse.LoginKhachHangResponse;
import com.example.poloman.model.reponse.LoginNhanVienResponse;
import com.example.poloman.model.request.LoginRequest;
import com.example.poloman.repository.KhachHangRepository;
import com.example.poloman.repository.NhanVienRepository;
import com.example.poloman.service.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private NhanVienRepository nhanVienRepository;
    private KhachHangRepository khachHangRepository;

    @Autowired
    public AuthServiceImpl(NhanVienRepository nhanVienRepository, KhachHangRepository khachHangRepository) {
        this.nhanVienRepository = nhanVienRepository;
        this.khachHangRepository = khachHangRepository;
    }


    @Override
    @Transactional
    public LoginNhanVienResponse loginAdmin(LoginRequest loginRequest) {
        NhanVien nhanVien = nhanVienRepository.findByEmailAndMatkhau(loginRequest.getEmail(), loginRequest.getMatkhau());
        return generateLoginResponse(nhanVien);
    }

    private LoginNhanVienResponse generateLoginResponse(NhanVien nhanVien) {
        return new LoginNhanVienResponse()
                .setManhanvien(nhanVien.getManhanvien())
                .setEmail(nhanVien.getEmail())
                .setSodienthoai(nhanVien.getSodienthoai())
                .setDiachi(nhanVien.getDiachi())
                .setGioitinh(nhanVien.getGioitinh())
                .setTennhanvien(nhanVien.getTennhanvien())
                ;
    }

    @Override
    public NhanVien register(NhanVien nhanVien) {
        return null;
    }

    @Override
    public LoginKhachHangResponse loginKhachHang(LoginRequest loginRequest) {
        KhachHang khachHang = khachHangRepository.findByEmailAndMatkhau(loginRequest.getEmail(), loginRequest.getMatkhau());

        return generateLoginKhachHangResponse(khachHang);
    }

    private LoginKhachHangResponse generateLoginKhachHangResponse(KhachHang khachHang) {
        return new LoginKhachHangResponse()
                .setMakhachhang(khachHang.getMakhachhang())
                .setEmail(khachHang.getEmail())
                .setSodienthoai(khachHang.getSodienthoai())
                .setDiachi(khachHang.getDiachi())
                .setGioitinh(khachHang.getGioitinh())
                .setTenkhachhang(khachHang.getTenkhachhang())
                ;
    }
}
