package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.OrderDetail;
import com.example.sd_95_polo_store_be.Model.Response.OrderDetailPdfResponse;
import com.example.sd_95_polo_store_be.Model.Response.OrderDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
    @Query(value = """
            select new com.example.sd_95_polo_store_be.Model.
            Response.OrderDetailResponse
            (od.id ,od.productDetail.id,od.productDetail.products.name,od.productDetail.sizes.name,od.productDetail.colors.name,od.price,od.quantity)
             from OrderDetail od
             where od.orders.id = :id
            """)
    List<OrderDetailResponse> getId(Integer id);

    @Query(value = """
            select Top 1 i.name from OrderDetail 
            join ProductDetail pd on OrderDetail.productDetailId = pd.id
            join Images i on pd.id = i.productDetailId
            where OrderDetail.id = :id 
              """, nativeQuery = true)
    String getImage(@Param("id") Integer id);

    @Query(value = """
                    select new com.example.sd_95_polo_store_be.Model.Response.OrderDetailPdfResponse(p.name, od.quantity, od.price)  
                    from OrderDetail od 
                    inner join Orders o on o.id = od.orders.id
                    inner join ProductDetail pd on od.productDetail.id = pd.id 
                    inner join Products p on p.id = pd.products.id 
                    where o.id = :orderId
                    """)
    List<OrderDetailPdfResponse> getListOrderPdf(Integer orderId);
}
