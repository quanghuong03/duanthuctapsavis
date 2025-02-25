package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Customers;
import com.example.sd_95_polo_store_be.Model.Response.CustomerForAdminResponse;
import com.example.sd_95_polo_store_be.Model.Response.CustomerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {
    Customers findByEmailAndPassword(String email,String password);

    @Query(value = """
                select new com.example.sd_95_polo_store_be.Model.Response.CustomerForAdminResponse(c.id,c.name,c.email,c.phone,c.avatar)
                from Customers c
              where c.status = '1'
            """)
    List<CustomerForAdminResponse> getCustomersForAdmin();

    @Query(value = """
                select new com.example.sd_95_polo_store_be.Model.Response.CustomerResponse(c.id,c.name,c.email,c.phone,c.avatar,c.status)
                from Customers c
              where c.id = :id and c.status = '1'
            """)
   Optional<CustomerResponse> getOneCustomer(Integer id);

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
