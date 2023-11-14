package com.example.poloman.service.impl;

import com.example.poloman.model.entity.*;
import com.example.poloman.model.request.CreateGioHangRequest;
import com.example.poloman.model.request.UpdateSoLuongGioHang;
import com.example.poloman.model.request.UpdateTrangThaiGioHang;
import com.example.poloman.repository.ChiTietSanPhamRepository;
import com.example.poloman.repository.GioHangChiTietRepository;
import com.example.poloman.repository.GioHangRepository;
import com.example.poloman.repository.SanPhamRepository;
import com.example.poloman.service.GioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GioHangChiTietServiceImpl implements GioHangChiTietService {

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;
    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;
    @Autowired
    GioHangRepository gioHangRepository;

    @Override
    public List<GioHangChiTiet> getAll() {
        return gioHangChiTietRepository.findAll();
    }

    @Override
    public GioHangChiTiet save(GioHangChiTiet gioHangChiTiet) {
        return gioHangChiTietRepository.save(gioHangChiTiet);
    }

    @Override
    public void delete(Integer magiohang) {
        gioHangChiTietRepository.deleteById(magiohang);
    }

    @Override
    @Transactional
    public void add(CreateGioHangRequest request, Integer makhachhang) {
        Optional<GioHang> optionalGioHang = gioHangRepository.findGioHangByKhachhangMakhachhang(makhachhang);
        GioHang gioHang;
        if (optionalGioHang.isPresent()) {
            // Nếu giỏ hàng đã tồn tại, sử dụng giỏ hàng hiện có
            gioHang = optionalGioHang.get();
        } else {
            // Nếu giỏ hàng chưa tồn tại, tạo một giỏ hàng mới
            gioHang = new GioHang();
            gioHang.setTrangthai(0); // Thiết lập trạng thái giỏ hàng, ví dụ: 0 = Chưa hoàn thành

            // Thiết lập quan hệ với khách hàng
            KhachHang khachHang = new KhachHang();
            khachHang.setMakhachhang(makhachhang);
            gioHang.setKhachhang(khachHang);

            // Lưu đối tượng GioHang vào cơ sở dữ liệu
            gioHang = gioHangRepository.save(gioHang);
        }

        // Lấy thông tin sản phẩm từ CSDL
        Optional<ChiTietSanPham> optionalSanPham = chiTietSanPhamRepository.findById(request.getMactsp());
        if (optionalSanPham.isPresent()) {
            ChiTietSanPham chiTietSanPham = optionalSanPham.get();

            // Kiểm tra số lượng tồn của sản phẩm
            if (chiTietSanPham.getSoluongton() >= request.getSoluong()) {
                // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
                Optional<GioHangChiTiet> optionalGioHangChiTiet = gioHangChiTietRepository.findGioHangChiTietByGiohangAndSanpham(gioHang, chiTietSanPham);
                if (optionalGioHangChiTiet.isPresent()) {
                    // Nếu sản phẩm đã tồn tại trong giỏ hàng, tăng số lượng
                    GioHangChiTiet gioHangChiTiet = optionalGioHangChiTiet.get();
                    int currentSoLuong = gioHangChiTiet.getSoluong();
                    int newSoLuong = currentSoLuong + request.getSoluong();
                    gioHangChiTiet.setSoluong(newSoLuong);
                    gioHangChiTietRepository.save(gioHangChiTiet);
                } else {
                    // Nếu sản phẩm chưa tồn tại trong giỏ hàng, tạo một đối tượng GioHangChiTiet mới
                    GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
                    gioHangChiTiet.setSoluong(request.getSoluong());
                    gioHangChiTiet.setTrangthai(0); // Thiết lập trạng thái giỏ hàng chi tiết, ví dụ: 0 = Chưa hoàn thành

                    // Thiết lập quan hệ với giỏ hàng và chi tiết sản phẩm
                    gioHangChiTiet.setGiohang(gioHang);
                    gioHangChiTiet.setSanpham(chiTietSanPham);

                    // Lưu đối tượng GioHangChiTiet vào cơ sở dữ liệu
                    gioHangChiTietRepository.save(gioHangChiTiet);
                }

                // Cập nhật số lượng tồn của sản phẩm
                int newSoLuongTon = chiTietSanPham.getSoluongton() - request.getSoluong();
                chiTietSanPham.setSoluongton(newSoLuongTon);
                chiTietSanPhamRepository.save(chiTietSanPham);

                System.out.println(gioHang);
            } else {
                // Số lượng tồn không đủ
                throw new IllegalArgumentException("Số lượng sản phẩm không khả dụng");
            }
        } else {
            // Không tìm thấy sản phẩm
            throw new IllegalArgumentException("Không tìm thấy sản phẩm");
        }
    }

    @Override
    public void updateTinhTrang(Integer magiohang, UpdateTrangThaiGioHang trangThaiGioHang) {
        var giohangct = gioHangChiTietRepository.findById(magiohang)
                .orElseThrow();
        if (trangThaiGioHang.trangthai() == 0) {
            giohangct.setTrangthai(1);
            gioHangChiTietRepository.save(giohangct);
        } else {
            giohangct.setTrangthai(0);
            gioHangChiTietRepository.save(giohangct);
        }
    }

    @Override
    public void updatesoluong(Integer magiohang, UpdateSoLuongGioHang soLuongGioHang) {
        var giohangct = gioHangChiTietRepository.findById(magiohang)
                .orElseThrow();
        if (soLuongGioHang.soluong() == 0) {
            gioHangChiTietRepository.delete(giohangct);
        } else {
            giohangct.setSoluong(soLuongGioHang.soluong());
            gioHangChiTietRepository.save(giohangct);
        }
    }
}








