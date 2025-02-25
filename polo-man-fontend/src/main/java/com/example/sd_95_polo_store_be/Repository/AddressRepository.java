package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Address;
import com.example.sd_95_polo_store_be.Model.Entity.CustomerAddress;
import com.example.sd_95_polo_store_be.Model.Response.AddressResponse;
import com.example.sd_95_polo_store_be.Model.Response.ProductDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<CustomerAddress,Integer> {
    @Query(value = """
                select new com.example.sd_95_polo_store_be.Model.Response.AddressResponse(cad.address.id, cad.address.city, cad.address.district,cad.address.ward,cad.address.fullAddress,cad.address.status)
                from CustomerAddress cad
                where cad.customers.id = :id
                
            """)
    List<AddressResponse> getByProductId(Integer id);
}
