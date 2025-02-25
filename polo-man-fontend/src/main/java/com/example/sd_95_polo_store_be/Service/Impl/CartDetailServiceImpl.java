package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Cart;
import com.example.sd_95_polo_store_be.Model.Entity.CartDetail;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Model.Request.CartRequest;
import com.example.sd_95_polo_store_be.Model.Request.ChangeQuantityCartRequest;
import com.example.sd_95_polo_store_be.Model.Request.ChangeStatusCartRequest;
import com.example.sd_95_polo_store_be.Repository.CartDetailRepository;
import com.example.sd_95_polo_store_be.Repository.CartRepository;
import com.example.sd_95_polo_store_be.Repository.CustomerRepository;
import com.example.sd_95_polo_store_be.Repository.ProductDetailRepository;
import com.example.sd_95_polo_store_be.Service.CartDetailServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartDetailServiceImpl implements CartDetailServie {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public void addCart(CartRequest cartRequest, Integer id) {
        Optional<Cart> optionalCart = cartRepository.findCartByCustomersId(id);
        var customer = customerRepository.findById(id).orElseThrow();
        Cart cart;
        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
        } else {
            cart = new Cart();
            cart.setStatus(0);
            cart.setCustomers(customer);
            cart = cartRepository.save(cart);
        }

        Optional<ProductDetail> optionalProductDetail = productDetailRepository.findById(cartRequest.getProductDetailId());
        if (optionalProductDetail.isPresent()) {
            ProductDetail productDetail = optionalProductDetail.get();

            // Kiểm tra số lượng tồn của sản phẩm
            if (productDetail.getQuantity() < cartRequest.getQuantity()) {
                throw new IllegalArgumentException("Số lượng trong giỏ hàng vượt quá số lượng tồn");
            }

            Optional<CartDetail> optionalCartDetail = cartDetailRepository.findCartDetailByCartAndAndProductDetail(cart, productDetail);
            if (optionalCartDetail.isPresent()) {
                CartDetail cartDetail = optionalCartDetail.get();
                Integer currentQuantity = cartDetail.getQuantity();
                Integer newQuantity = currentQuantity + cartRequest.getQuantity();

                // Kiểm tra số lượng tồn sau khi thêm vào giỏ hàng
                if (productDetail.getQuantity() < newQuantity) {
                    throw new IndexOutOfBoundsException("Số lượng trong giỏ hàng vượt quá số lượng tồn");
                }

                cartDetail.setQuantity(newQuantity);
                cartDetailRepository.save(cartDetail);
            } else {
                CartDetail newCartDetail = new CartDetail();
                newCartDetail.setQuantity(cartRequest.getQuantity());
                newCartDetail.setStatus(0);
                newCartDetail.setCart(cart);
                newCartDetail.setProductDetail(productDetail);

                // Kiểm tra số lượng tồn khi thêm mới vào giỏ hàng
                if (productDetail.getQuantity() < cartRequest.getQuantity()) {
                    throw new IllegalArgumentException("Số lượng trong giỏ hàng vượt quá số lượng tồn");
                }

                cartDetailRepository.save(newCartDetail);
            }
        }
    }

    @Override
    public void delete(Long cartDetailId) {
        var cartDetail = cartDetailRepository.findById(cartDetailId)
                .orElseThrow(() -> new IndexOutOfBoundsException("Cart not found"));
        cartDetailRepository.delete(cartDetail);
    }

    @Override
    public void changeQuantityCart(Long id, ChangeQuantityCartRequest quantityCartRequest) {
        var cartDetail = cartDetailRepository.findById(id).orElseThrow();
        var product = productDetailRepository.findById(quantityCartRequest.getIdProductDetail()).orElseThrow();

        // Kiểm tra nếu số lượng trong giỏ hàng lớn hơn số lượng của sản phẩm
        if (quantityCartRequest.getQuantity() > product.getQuantity()) {
            throw new IllegalArgumentException("Số lượng trong giỏ hàng lớn hơn số lượng của sản phẩm");
        }

        if (quantityCartRequest.getQuantity() < 0) {
            throw new IllegalArgumentException("Số lượng không hợp lệ");
        }

        if (quantityCartRequest.getQuantity() == 0) {
            cartDetailRepository.delete(cartDetail);
        } else {
            cartDetail.setQuantity(quantityCartRequest.getQuantity());
            cartDetailRepository.save(cartDetail);
        }
    }

    @Override
    public void changeStatusCart(Long id, ChangeStatusCartRequest cartResponse) {
        var cartDetail = cartDetailRepository.findById(id).orElseThrow();
        if (cartResponse.getStatus() == 0) {
            cartDetail.setStatus(1);
            cartDetailRepository.save(cartDetail);
        } else {
            cartDetail.setStatus(0);
            cartDetailRepository.save(cartDetail);
        }
    }
}
