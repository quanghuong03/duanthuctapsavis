package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Cart;
import com.example.sd_95_polo_store_be.Model.Entity.CartDetail;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    Optional<CartDetail> findCartDetailByCartAndAndProductDetail(Cart cart, ProductDetail productDetail);
}
