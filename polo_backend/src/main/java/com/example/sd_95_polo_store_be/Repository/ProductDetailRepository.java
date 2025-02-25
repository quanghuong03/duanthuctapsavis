package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Model.Response.DiscountProductDetailReponse;
import com.example.sd_95_polo_store_be.Model.Response.ProductDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    Optional<ProductDetail> findById(Integer id);

    @Query(value = """
                select new com.example.sd_95_polo_store_be.Model.Response.ProductDetailResponse(pd.id, pd.sizes.id, pd.colors.id,pd.products.name, pd.sizes.name,pd.colors.name,pd.quantity,pd.cost,pd.price,pd.weight,pd.status,pd.products.discount.id,pd.products.discount.discount,pd.products.status)
                from ProductDetail pd
                where pd.products.id = :id
                and pd.status <> 0
            """)
    List<ProductDetailResponse> getByProductId(Integer id);

    @Query(value = """
                select new com.example.sd_95_polo_store_be.Model.Response.ProductDetailResponse(pd.id, pd.sizes.id, pd.colors.id ,pd.products.name, pd.sizes.name,pd.colors.name,pd.quantity,pd.cost,pd.price,pd.weight,pd.status,pd.products.discount.id,pd.products.discount.discount,pd.products.status)
                from ProductDetail pd
                where 
               pd.status <> 0 and pd.products.status <> 0
            """)
    List<ProductDetailResponse> getAllProductDetail();



}
