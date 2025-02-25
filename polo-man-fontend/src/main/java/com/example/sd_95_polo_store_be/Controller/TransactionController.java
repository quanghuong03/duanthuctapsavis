package com.example.sd_95_polo_store_be.Controller;

import com.example.sd_95_polo_store_be.Model.Entity.Transactions;
import com.example.sd_95_polo_store_be.Model.Response.TransactionResponse;
import com.example.sd_95_polo_store_be.Service.TransactionService;
import com.example.sd_95_polo_store_be.common.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
@CrossOrigin(value = "*")
@Validated
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public Response<List<Transactions>> getAll(){
        return Response.ofSucceeded(transactionService.getAll());
    }

    @PostMapping("/vnpay")
    public Response<TransactionResponse> submidOrder(@RequestParam("amount") Integer totalAmount,
                                                     @RequestParam("orderInfo") String orderInfo,
                                                     HttpServletRequest request) {
        TransactionResponse response = transactionService.transaction(totalAmount, orderInfo, request);
        System.out.println("ccccc");
        return Response.ofSucceeded(response);
    }

    @GetMapping("/vnpay-payment")
    public void GetMapping(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("ccccc");
        transactionService.orderReturn(request,response);
    }

}
