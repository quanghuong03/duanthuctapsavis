package com.example.poloman.service;

import com.example.poloman.model.entity.ChatLieu;

import java.util.List;

public interface ChatLieuService {
    List<ChatLieu> getAll();

    ChatLieu saveOrUpdate(ChatLieu chatLieu);

    void delete(Integer machatlieu);

ChatLieu getOne(Integer machatlieu);

}
