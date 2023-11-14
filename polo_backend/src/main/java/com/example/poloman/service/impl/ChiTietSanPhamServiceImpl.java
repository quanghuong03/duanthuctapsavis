package com.example.poloman.service.impl;

import com.example.poloman.model.entity.ChiTietSanPham;
import com.example.poloman.model.reponse.SanPhamChiTietResponse;
import com.example.poloman.model.request.SanPhamChiTietRequest;
import com.example.poloman.repository.ChiTietSanPhamRepository;
import com.example.poloman.repository.MauSacRepository;
import com.example.poloman.repository.SanPhamRepository;
import com.example.poloman.repository.SizeRepository;
import com.example.poloman.service.ChiTietSanPhamService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;
    @Autowired
    private MauSacRepository mauSacRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Override
    public List<ChiTietSanPham> getAll() {
        return chiTietSanPhamRepository.findAll();
    }

    @Override
    public ChiTietSanPham save(ChiTietSanPham chiTietSanPham) {
        return chiTietSanPhamRepository.save(chiTietSanPham);
    }

    @Override
    public void delete(Integer mactsp) {
        chiTietSanPhamRepository.deleteById(mactsp);
    }

    @Override
    public List<ChiTietSanPham> findOne(Integer masanpham) {
        return chiTietSanPhamRepository.findBySanpham_Masanpham(masanpham);
    }

    @Override
    public ChiTietSanPham createOrUpdate(SanPhamChiTietRequest sanPhamChiTietRequest, Integer masanphan) {
        var mausac = mauSacRepository.findById(sanPhamChiTietRequest.getMamausac()).orElseThrow();
        var SanPham = sanPhamRepository.findById(masanphan).orElseThrow();
        var size = sizeRepository.findById(sanPhamChiTietRequest.getMasize()).orElseThrow();
        Random random = new Random();
        int bound = Integer.MAX_VALUE; // Giới hạn là giá trị tối đa của Integer
        int generatedNumber = random.nextInt(bound) + 1; // Tạo số nguyên dương từ 1 đến bound // Tạo số nguyên ngẫu nhiên
        if (sanPhamChiTietRequest.getMactsp() == null) {
            ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
            chiTietSanPham.setSoluongton(sanPhamChiTietRequest.getSoluongton());
            chiTietSanPham.setMausac(mausac);
            chiTietSanPham.setSize(size);
            chiTietSanPham.setSanpham(SanPham);
            chiTietSanPham.setMavach(generatedNumber);
            return chiTietSanPhamRepository.save(chiTietSanPham);
        } else {
            var ctsp = chiTietSanPhamRepository.findById(sanPhamChiTietRequest.getMactsp()).orElseThrow();
            ctsp.setMausac(mausac);
            ctsp.setSoluongton(sanPhamChiTietRequest.getSoluongton());
            ctsp.setSize(size);
            ctsp.setSanpham(SanPham);
            ctsp.setMausac(mausac);
            ctsp.setMavach(generatedNumber);
            return chiTietSanPhamRepository.save(ctsp);
        }
    }

    @Override
    public List<SanPhamChiTietResponse> getForSanPham(Integer masanpham) {
        return chiTietSanPhamRepository.getforProduct(masanpham);
    }
}
