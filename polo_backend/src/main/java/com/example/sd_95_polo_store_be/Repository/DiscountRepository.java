package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Discount;
import com.example.sd_95_polo_store_be.Model.Response.DiscountResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount,Integer> {
    @Query(value = """
                select new com.example.sd_95_polo_store_be.Model.Response.DiscountResponse(d.id,d.name,d.discount,d.description,d.startDate,d.endDate,d.status)
                from Discount d
                where d.status = 1
            """)
    List<DiscountResponse> gets();

    @Query(value = """
                select new com.example.sd_95_polo_store_be.Model.Response.DiscountResponse(d.id,d.name,d.discount,d.description,d.startDate,d.endDate,d.status)
                from Discount d
                where d.id = :id and d.status = 1
            """)
    Optional<DiscountResponse> getId(Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Discount SET status = 2 " +
            "WHERE endDate <= GETDATE() AND status = 1", nativeQuery = true)
    void expireActiveDiscounts();


}

