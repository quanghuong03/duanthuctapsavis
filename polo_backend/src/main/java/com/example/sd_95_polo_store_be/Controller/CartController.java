package com.example.sd_95_polo_store_be.Controller;

import com.example.sd_95_polo_store_be.Model.Request.CartRequest;
import com.example.sd_95_polo_store_be.Model.Request.ChangeQuantityCartRequest;
import com.example.sd_95_polo_store_be.Model.Request.ChangeStatusCartRequest;
import com.example.sd_95_polo_store_be.Model.Response.CartResponse;
import com.example.sd_95_polo_store_be.Service.CartDetailServie;
import com.example.sd_95_polo_store_be.Service.CartService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartDetailServie cartDetailServie;

    @Autowired
    private CartService cartService;

    @PostMapping("/{id}")
    public Response<Void> add(@RequestBody CartRequest cartRequest, @PathVariable Integer id) {
        cartDetailServie.addCart(cartRequest, id);
        return Response.ofSucceeded();
    }

    @GetMapping("/{id}")
    public Response<CartResponse> getOne(@PathVariable Integer id) {
        return Response.ofSucceeded(cartService.getOne(id));
    }

    @PutMapping("updateQuantity/{id}")
    public Response<Void> updateQuantity(@PathVariable Long id, @RequestBody ChangeQuantityCartRequest quantityCartRequest) {
        cartDetailServie.changeQuantityCart(id, quantityCartRequest);
        return Response.ofSucceeded();
    }


    @PutMapping("updateStatus/{id}")
    public Response<Void> update(@PathVariable Long id,@RequestBody ChangeStatusCartRequest statusCartResponse) {
        cartDetailServie.changeStatusCart(id, statusCartResponse);
        return Response.ofSucceeded();
    }

    @PutMapping("delete/{id}")
    public Response<Void> delete(@PathVariable Long id) {
        cartDetailServie.delete(id);
        return Response.ofSucceeded();
    }


    @GetMapping("order/{id}")
    public Response<CartResponse> getOneByTrangThai(@PathVariable Integer id) {
        return Response.ofSucceeded(cartService.getOneByStatus(id));
    }


}
