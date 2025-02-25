package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Config.VnpayConfig;
import com.example.sd_95_polo_store_be.Model.Entity.Transactions;
import com.example.sd_95_polo_store_be.Model.Response.TransactionResponse;
import com.example.sd_95_polo_store_be.Repository.OrderRepository;
import com.example.sd_95_polo_store_be.Repository.TransactionsRepository;
import com.example.sd_95_polo_store_be.Service.OrderService;
import com.example.sd_95_polo_store_be.Service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Value("${vnpay.redirect.url}")
    private String redirectUrl;
    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private static final String US_ASCII = StandardCharsets.US_ASCII.toString();

    private Integer orderId = 0;

    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<Transactions> getAll() {
        return transactionsRepository.findAll();
    }

    @Override
    public TransactionResponse transaction(Integer total, String orderInfo, HttpServletRequest request) {
       orderId = Integer.valueOf(orderInfo);
       var order = orderService.get(orderId);
       orderService.changeTransaction(orderId);
       logger.info("Request payment : {} {}", order.getTotalPrice(), orderInfo);
        try {
            String urlReturn = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            String vnp_Version = VnpayConfig.vnp_Version;
            String vnp_Command = VnpayConfig.vnp_Command;
            String vnp_TxnRef = VnpayConfig.getRandomNumber(8);
            String vnp_IpAddr = VnpayConfig.vnp_IpAddr;
            String vnp_TmnCode = VnpayConfig.vnp_TmnCode;
            String orderType = VnpayConfig.orderType;

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(total * 100));
            vnp_Params.put("vnp_CurrCode", VnpayConfig.vnp_CurrCode);

            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", orderInfo);
            vnp_Params.put("vnp_OrderType", orderType);

            String locate = "vn";
            vnp_Params.put("vnp_Locale", locate);

            vnp_Params.put("vnp_ReturnUrl", VnpayConfig.vnp_Returnurl);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
            System.out.println(urlReturn);

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator<String> itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = itr.next();
                String fieldValue = vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    try {
                        hashData.append(URLEncoder.encode(fieldValue, US_ASCII));
                        //Build query
                        query.append(URLEncoder.encode(fieldName, US_ASCII));
                        query.append('=');
                        query.append(URLEncoder.encode(fieldValue, US_ASCII));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();
            String vnp_SecureHash = VnpayConfig.hmacSHA512(VnpayConfig.vnp_HashSecret, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = VnpayConfig.vnp_PayUrl + "?" + queryUrl;
            logger.info("paymentUrl: {}", paymentUrl);
            return new TransactionResponse(true, paymentUrl);
        } catch (Exception ex) {
            logger.error("Exception payment: {}", ex.getMessage());
            return new TransactionResponse(false, "");
        }
    }

    @Override
    public void orderReturn(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> fields = new HashMap<>();
        for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements(); ) {
            String fieldName = null;
            String fieldValue = null;
            try {
                fieldName = URLEncoder.encode(params.nextElement(), US_ASCII);
                fieldValue = URLEncoder.encode(request.getParameter(fieldName), US_ASCII);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        fields.remove("vnp_SecureHashType");
        fields.remove("vnp_SecureHash");
        String signValue = VnpayConfig.hashAllFields(fields);
        int status = 0;
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                status = 1;
            }
        } else {
            status = -1;
        }
        if (status == 1) {
            System.out.println("cc");
        }
        try {
            response.sendRedirect(redirectUrl+"?id="+orderId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
