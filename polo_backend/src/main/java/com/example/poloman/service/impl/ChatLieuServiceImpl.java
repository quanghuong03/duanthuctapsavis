package com.example.poloman.service.impl;

import com.example.poloman.model.entity.ChatLieu;
import com.example.poloman.repository.ChatLieuRepository;
import com.example.poloman.service.ChatLieuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatLieuServiceImpl implements ChatLieuService {
    private static final Logger logger = LoggerFactory.getLogger(ChatLieuServiceImpl.class);
    @Autowired
    private ChatLieuRepository chatLieuRepository;

    @Override
    public List<ChatLieu> getAll() {
        return chatLieuRepository.findAll();
    }

    @Override
    public ChatLieu saveOrUpdate(ChatLieu chatLieu) {
        if (chatLieu.getMachatlieu() != null) {
            Optional<ChatLieu> existingChatLieu = chatLieuRepository.findById(chatLieu.getMachatlieu());
            if (existingChatLieu.isPresent()) {
                ChatLieu updateChatLieu = existingChatLieu.get();
                updateChatLieu.setTenchatlieu(chatLieu.getTenchatlieu());
                return chatLieuRepository.save(updateChatLieu);
            }
        }

        return chatLieuRepository.save(chatLieu);
    }

    @Override
    public ChatLieu getOne(Integer machatlieu) {
        Optional<ChatLieu> optionalChatLieu = chatLieuRepository.findById(machatlieu);
        return optionalChatLieu.get();
    }

    @Override
    public void delete(Integer machatlieu) {
        chatLieuRepository.deleteById(machatlieu);
    }

}