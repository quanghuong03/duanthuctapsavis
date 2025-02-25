package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Products;
import com.example.sd_95_polo_store_be.Model.Response.GetOneProductResponse;
import com.example.sd_95_polo_store_be.Model.Response.ProductDiscountResponse;
import com.example.sd_95_polo_store_be.Model.Response.ProductForAdminResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
    @Query(value = """
            select new com.example.sd_95_polo_store_be.Model.
            Response.ProductForAdminResponse
            (p.id, p.name, p.status, p.description, p.categories.id, p.brands.id,p.materials.id,p.brands.name, p.categories.name,p.materials.name,p.discount.id,p.discount.discount)
             from Products p
               order by p.createDate desc
            """)
    List<ProductForAdminResponse> getAllProductByCreateDateDesc();

    @Query(value = """
            select new com.example.sd_95_polo_store_be.Model.
            Response.ProductDiscountResponse
            (p.id, p.name, p.discount.discount)
             from Products p
               order by p.createDate desc
            """)
    List<ProductDiscountResponse> getProductDiscounts();

    @Query(value = """
            select new com.example.sd_95_polo_store_be.Model.
            Response.GetOneProductResponse
            (p.id, p.name, p.status, p.description, p.categories.id, p.brands.id,p.materials.id,p.brands.name, p.categories.name,p.materials.name,p.discount.id,p.discount.discount,p.discount.name)
             from Products p
             where p.id = :id
             
            """)
    Optional<GetOneProductResponse> getId(Integer id);
    @Query(value = """
            select Top 1 i.name from Products 
            join ProductDetail pd on Products.id = pd.productId
            join Images i on pd.id = i.productDetailId
            where productId = :id 

              """, nativeQuery = true)
    String getImage(Integer id);

    @Query(value = """
            select Top 1 pd.price from Products 
            join ProductDetail pd on Products.id = pd.productId
            where productId = :id 

              """, nativeQuery = true)
    Float getPrice(Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Products p SET p.discount.id = 1, p.status = 1 " +
            "WHERE p.discount.id IN (SELECT d.id FROM Discount d WHERE d.status = 0 OR d.status = 2) " +
            "AND p.status <> 0", nativeQuery = false)
    void updateProductsForExpiredDiscounts();

    @Query(value = """
            select new com.example.sd_95_polo_store_be.Model.
            Response.ProductForAdminResponse
            (p.id, p.name, p.status, p.description, p.categories.id, p.brands.id,p.materials.id,p.brands.name, p.categories.name,p.materials.name,p.discount.id,p.discount.discount)
             from Products p
             where p.status <> 0
              order by p.createDate desc
            """)
    List<ProductForAdminResponse> getAllProductToDiscount();

}
