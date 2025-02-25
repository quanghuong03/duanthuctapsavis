package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Entity.Transactions;
import com.example.sd_95_polo_store_be.Model.Response.TransactionResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface TransactionService {
    List<Transactions> getAll();
    TransactionResponse transaction(Integer total, String orderInfo, HttpServletRequest request);
 void orderReturn(HttpServletRequest request, HttpServletResponse response);
}
