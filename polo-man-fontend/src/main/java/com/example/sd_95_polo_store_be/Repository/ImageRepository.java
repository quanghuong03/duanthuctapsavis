package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Images;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Model.Response.ImageProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Images, Integer> {
    @Query(value = """
                select new com.example.sd_95_polo_store_be.Model.Response.ImageProductResponse(img.id,img.name,img.url_image,img.status)
                from Images img
                where img.productDetail.id = :id 
            """)
    List<ImageProductResponse> findByProductDetail(Integer id);
    List<Images> findByProductDetail(ProductDetail productDetail);

    @Query(value = """
            delete from Images where id = :id
        """,nativeQuery = true)
    @Transactional
    @Modifying
    void deleteImage(Integer id);

    Images findByName(String name);

    Images findByNameAndProductDetail(String name, ProductDetail productDetail);

    @Query("SELECT name FROM Images WHERE id = :imageId")
    String findNameById(@Param("imageId") Integer imageId);

}
