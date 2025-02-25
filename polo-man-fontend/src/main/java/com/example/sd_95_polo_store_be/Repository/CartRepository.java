package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Cart;
import com.example.sd_95_polo_store_be.Model.Response.CartDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    Optional<Cart> findCartByCustomersId(Integer id);

    @Query(value = """
                select new com.example.sd_95_polo_store_be.Model.Response.CartDetailResponse(cd.id, pd.id, pd.products.name, pd.sizes.name,pd.colors.name,pd.weight , cd.status, cd.quantity,pd.products.discount.id,pd.price)
                from Cart c
                         join Customers kh on kh.id = c.customers.id
                         join CartDetail cd on c.id = cd.cart.id
                         join ProductDetail pd on pd.id = cd.productDetail.id
                where kh.id = :id 
            """)
    List<CartDetailResponse> getCartByCustomers(Integer id);

    @Query(value = """
            select Top 1 i.name from CartDetail 
            join ProductDetail pd on CartDetail.productDetailId = pd.id
            join Images i on pd.id = i.productDetailId
            where CartDetail.id = :id 
              """, nativeQuery = true)
    String getImage(@Param("id") Long id);

    @Query(value = """
                select new com.example.sd_95_polo_store_be.Model.Response.CartDetailResponse(cd.id, pd.id, pd.products.name, pd.sizes.name,pd.colors.name,pd.weight, cd.status, cd.quantity,pd.products.discount.id,pd.price)
                from Cart c
                         join Customers kh on kh.id = c.customers.id
                         join CartDetail cd on c.id = cd.cart.id
                         join ProductDetail pd on pd.id = cd.productDetail.id
                     
                where kh.id = :id and cd.status <> 0
            """)
    List<CartDetailResponse> getCartByCustomersAAndStatus(Integer id);
}
