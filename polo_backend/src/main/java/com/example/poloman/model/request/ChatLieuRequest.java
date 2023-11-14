package com.example.poloman.model.request;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ChatLieuRequest {

    private Integer machatlieu;

    private String tenchatlieu;

    public ChatLieuRequest(Integer machatlieu, String tenchatlieu) {
        this.machatlieu = machatlieu;
        this.tenchatlieu = tenchatlieu;
    }
}
