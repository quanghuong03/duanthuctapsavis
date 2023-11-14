package com.example.poloman.controller;

import com.example.poloman.common.Response;
import com.example.poloman.model.entity.ChatLieu;
import com.example.poloman.service.ChatLieuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/chatlieu")
@RequiredArgsConstructor
@CrossOrigin(value = "*")
public class ChatLieuController {

    @Autowired
    private ChatLieuService chatLieuService;

    @GetMapping("/getAll")
    public Response<List<ChatLieu>> getAll() {
        return Response.ofSucceeded(chatLieuService.getAll());
    }

    @PostMapping("/saveOrUpdate")
    public Response<ChatLieu> saveOrUpdate(@RequestBody ChatLieu chatLieu) {
        return Response.ofSucceeded(chatLieuService.saveOrUpdate(chatLieu));
    }
    @GetMapping("/{machatlieu}")
    public Response<ChatLieu> get(@PathVariable Integer machatlieu) {
        return Response.ofSucceeded(chatLieuService.getOne(machatlieu));
    }

    @DeleteMapping("delete/{machatlieu}")
    public void delete(@PathVariable Integer machatlieu) {
        chatLieuService.delete(machatlieu);
    }

}
