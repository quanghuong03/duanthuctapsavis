package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Orders;
import com.example.sd_95_polo_store_be.Model.Response.OrderPdfResponse;
import com.example.sd_95_polo_store_be.Model.Response.OrderResponse;
import com.example.sd_95_polo_store_be.Service.ExportOrderPdfService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
    List<Orders> findByCustomersId(Integer id);
    @Query(value = """
            select new com.example.sd_95_polo_store_be.Model.
            Response.OrderResponse
            (o.id, o.username,o.phone,o.address,o.shipCost,o.totalPrice,o.transactions.name,o.note,o.status,o.confirmDate,o.successDate,o.shipDate,o.createDate)
             from Orders o
             where o.id = :id
              order by o.createDate desc
            """)
    Optional<OrderResponse> getId(Integer id);

    @Query(value = """
                    select new com.example.sd_95_polo_store_be.Model.Response.OrderPdfResponse(o.id, o.phone, o.username, o.address, o.shipCost, o.totalPrice)
                    from Orders o
                    where o.id = :orderId
                    """)
    Optional<OrderPdfResponse> getOrderByOrderId(Integer orderId);

    List<Orders> findByOrderByCreateDateDesc();

    List<Orders> findByCreateDateAfter(OffsetDateTime createDate);

}
