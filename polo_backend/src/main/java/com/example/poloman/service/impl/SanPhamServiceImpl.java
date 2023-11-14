package com.example.poloman.service.impl;

import com.example.poloman.model.entity.ChiTietSanPham;
import com.example.poloman.model.entity.SanPham;
import com.example.poloman.model.entity.ThuongHieu;
import com.example.poloman.model.reponse.SanPhamChiTietResponse;
import com.example.poloman.model.reponse.SanPhamOverviewResponse;
import com.example.poloman.model.reponse.SanPhamResponse;
import com.example.poloman.model.request.SanPhamChiTietRequest;
import com.example.poloman.model.request.SanPhamRequest;
import com.example.poloman.repository.*;
import com.example.poloman.service.ChiTietSanPhamService;
import com.example.poloman.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;
    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;
    @Autowired
    private ThuongHieuRepository thuongHieuRepository;
    @Autowired
    private ChatLieuRepository chatLieuRepository;
    @Autowired
    private DongSPRepository dongSPRepository;

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.findAll();
    }

    @Override
    public List<SanPhamOverviewResponse> getAllSanPham() {
        return sanPhamRepository.findAllSanPham();

    }

    @Override
    public SanPham save(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }

    @Override
    public void delete(Integer masanpham) {
        sanPhamRepository.deleteById(masanpham);
    }

    @Override
    public SanPhamResponse getOne(Integer masanpham) {
        var sanpham = sanPhamRepository.findByMaSanPham(masanpham).orElseThrow();
        sanpham.setList(chiTietSanPhamService.getForSanPham(masanpham));
        return sanpham;
    }

    @Override
    public void create(SanPhamRequest sanPhamRequest) {
        var dongsp = dongSPRepository.findById(sanPhamRequest.getMadongsp()).orElseThrow();
        var thuonghieu = thuongHieuRepository.findById(sanPhamRequest.getMathuonghieu()).orElseThrow();
        var chatlieu = chatLieuRepository.findById(sanPhamRequest.getMachatlieu()).orElseThrow();
        SanPham sanPham = new SanPham();
        sanPham.setThuonghieu(thuonghieu);
        sanPham.setChatlieu(chatlieu);
        sanPham.setKieuao(dongsp);
        sanPham.setMota(sanPhamRequest.getMota());
        sanPham.setHinhanh(sanPhamRequest.getHinhanh());
        sanPham.setGiaban(sanPhamRequest.getGiaban());
        sanPham.setGianhap(sanPhamRequest.getGianhap());
        sanPham.setTensanpham(sanPhamRequest.getTensanpham());
        sanPham.setHinhanh(sanPhamRequest.getHinhanh());
        sanPhamRepository.save(sanPham);

        List<SanPhamChiTietRequest> sanphamchitiet = sanPhamRequest.getSanPhamChiTietRequests();
        sanphamchitiet.forEach(request -> chiTietSanPhamService.createOrUpdate(request, sanPham.getMasanpham()));

    }

    @Override
    public void update(Integer masanpham, SanPhamRequest sanPhamRequest) {
        var sanPham = sanPhamRepository.findById(masanpham).orElseThrow();
        var dongsp = dongSPRepository.findById(sanPhamRequest.getMadongsp()).orElseThrow();
        var thuonghieu = thuongHieuRepository.findById(sanPhamRequest.getMathuonghieu()).orElseThrow();
        var chatlieu = chatLieuRepository.findById(sanPhamRequest.getMachatlieu()).orElseThrow();
        sanPham.setThuonghieu(thuonghieu);
        sanPham.setChatlieu(chatlieu);
        sanPham.setKieuao(dongsp);
        sanPham.setMota(sanPhamRequest.getMota());
        sanPham.setHinhanh(sanPhamRequest.getHinhanh());
        sanPham.setGiaban(sanPhamRequest.getGiaban());
        sanPham.setGianhap(sanPhamRequest.getGianhap());
        sanPham.setTensanpham(sanPhamRequest.getTensanpham());
        sanPham.setHinhanh(sanPhamRequest.getHinhanh());
        sanPhamRepository.save(sanPham);
        List<SanPhamChiTietRequest> sanphamchitiet = sanPhamRequest.getSanPhamChiTietRequests();
        sanphamchitiet.forEach(request -> chiTietSanPhamService.createOrUpdate(request, sanPham.getMasanpham()));
    }


}
