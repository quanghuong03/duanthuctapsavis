package com.example.sd_95_polo_store_be.Controller.Admin;

import com.example.sd_95_polo_store_be.Model.Entity.Discount;
import com.example.sd_95_polo_store_be.Model.Request.AddDiscountToProductRequest;
import com.example.sd_95_polo_store_be.Model.Response.DiscountResponse;
import com.example.sd_95_polo_store_be.Service.DiscountService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/discount")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @GetMapping
    public Response<List<DiscountResponse>> gets() {
        return Response.ofSucceeded(discountService.gets());
    }

    @GetMapping("/getAll")
    public Response<List<Discount>> getAll() {
        return Response.ofSucceeded(discountService.getAll());
    }

    @PostMapping()
    public Response<Void> addDiscoutToProduct(@RequestBody AddDiscountToProductRequest addDiscountToProductRequest) {
        discountService.addDiscount(addDiscountToProductRequest);
        return Response.ofSucceeded();
    }

    @PutMapping("changeStatus/{id}")
    public Response<Void> changeStatus(@PathVariable Integer id) {
        discountService.changeStatus(id);
        return Response.ofSucceeded();
    }

        @PostMapping("/addDiscount")
        public Response<Discount> addDiscout(@RequestBody Discount discount) {
            return Response.ofSucceeded(discountService.addDiscount(discount));
        }
}
