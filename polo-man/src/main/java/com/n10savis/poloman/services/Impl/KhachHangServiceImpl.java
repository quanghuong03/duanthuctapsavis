package com.n10savis.poloman.services.Impl;


import com.n10savis.poloman.model.entity.KhachHang;
import com.n10savis.poloman.model.request.KhachHangRequest;
import com.n10savis.poloman.model.response.KhachHangResponse;
import com.n10savis.poloman.repository.KhachHangRepository;
import com.n10savis.poloman.services.KhachHangSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangServiceImpl implements KhachHangSerivce {
    @Autowired
    KhachHangRepository khachHangRepository;

    @Override
    public List<KhachHangResponse> getAll() {
        return khachHangRepository.getAll();
    }

    @Override
    public KhachHang save(KhachHangRequest khachHangRequest) {
        KhachHang khachHang = khachHangRequest.map(new KhachHang());

       return khachHangRepository.save(khachHang);
    }
}
