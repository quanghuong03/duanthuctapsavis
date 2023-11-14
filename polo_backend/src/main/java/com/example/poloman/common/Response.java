package com.example.poloman.common;

import com.example.poloman.common.Util.Constant;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.domain.Page;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private T data;
    private Metadata meta = new Metadata();

    public Response(T data, Metadata meta) {
        this.data = data;
        this.meta = meta;
    }

    public Response() {
    }

    public static <T> Response<T> ofSucceeded() {
        return ofSucceeded((T) null);
    }

    public static <T> Response<T> ofSucceeded(T data) {
        Response<T> response = new Response<>();
        response.data = data;
        response.meta.setCode(Metadata.OK_CODE);
        return response;
    }

    public static <T> Response<List<T>> ofSucceeded(Page<T> data) {
        Response<List<T>> response = new Response<>();
        response.data = data.getContent();
        response.meta.setCode(Metadata.OK_CODE);
        response.meta.setPage(data.getNumber());
        response.meta.setSize(data.getSize());
        response.meta.setTotal(data.getTotalElements());
        return response;
    }

    public T getData() {
        return data;
    }

    public Metadata getMeta() {
        return meta;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Metadata {
        public static final String OK_CODE = Constant.PREFIX_RESPONSE_CODE + 200;
        private String code;
        private Integer page;
        private Integer size;
        private Long total;
        private String message;

        public Metadata() {
        }

        public Metadata(String code, Integer page, Integer size, Long total, String message) {
            this.code = code;
            this.page = page;
            this.size = size;
            this.total = total;
            this.message = message;

        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public Long getTotal() {
            return total;
        }

        public void setTotal(Long total) {
            this.total = total;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
}
