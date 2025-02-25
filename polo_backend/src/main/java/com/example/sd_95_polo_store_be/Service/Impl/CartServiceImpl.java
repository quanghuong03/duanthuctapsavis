package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Response.CartDetailResponse;
import com.example.sd_95_polo_store_be.Model.Response.CartResponse;
import com.example.sd_95_polo_store_be.Repository.CartRepository;
import com.example.sd_95_polo_store_be.Repository.DiscountRepository;
import com.example.sd_95_polo_store_be.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public CartResponse getOne(Integer id) {
        var cartDetailResponse = cartRepository.getCartByCustomers(id);
        for (CartDetailResponse cart : cartDetailResponse) {
            var image = cartRepository.getImage(cart.getCartDetailId());
            cart.setImage(image);
            if (cart.getDiscountId() != null && cart.getDiscountId() != 1) {
                var discount = discountRepository.getId(cart.getDiscountId()).orElseThrow();
                cart.setPricePromotion(cart.getPriceCore() - (cart.getPriceCore() * discount.getDiscount()));
            }else {
                cart.setPricePromotion(cart.getPriceCore());
            }

        }
        CartResponse cartResponse = new CartResponse(id, cartDetailResponse);

        return cartResponse;
    }

    @Override
    public CartResponse getOneByStatus(Integer id) {
        var cartDetailResponse = cartRepository.getCartByCustomersAAndStatus(id);
        for (CartDetailResponse cart : cartDetailResponse) {
            var image = cartRepository.getImage(cart.getCartDetailId());
            cart.setImage(image);
            if (cart.getDiscountId() != null && cart.getDiscountId() != 1) {
                var discount = discountRepository.getId(cart.getDiscountId()).orElseThrow();
                cart.setPricePromotion(cart.getPriceCore() - (cart.getPriceCore() * discount.getDiscount()));
            }else {
                cart.setPricePromotion(cart.getPriceCore());
            }

        }
        CartResponse cartResponse = new CartResponse(id, cartDetailResponse);

        return cartResponse;

    }
}
