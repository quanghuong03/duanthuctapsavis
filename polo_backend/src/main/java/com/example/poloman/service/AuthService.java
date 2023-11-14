package com.example.poloman.service;
import com.example.poloman.model.entity.NhanVien;
import com.example.poloman.model.reponse.LoginKhachHangResponse;
import com.example.poloman.model.reponse.LoginNhanVienResponse;
import com.example.poloman.model.request.LoginRequest;

public interface AuthService {
     LoginNhanVienResponse loginAdmin(LoginRequest loginRequest);
     NhanVien register(NhanVien nhanVien);
     LoginKhachHangResponse loginKhachHang(LoginRequest loginRequest);
}
