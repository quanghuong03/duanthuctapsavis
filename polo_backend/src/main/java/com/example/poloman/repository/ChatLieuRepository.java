package com.example.poloman.repository;

import com.example.poloman.common.InsertUpdateRepository;
import com.example.poloman.model.entity.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatLieuRepository extends JpaRepository<ChatLieu, Integer> {
}
