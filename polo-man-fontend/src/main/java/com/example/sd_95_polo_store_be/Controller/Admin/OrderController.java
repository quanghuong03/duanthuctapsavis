package com.example.sd_95_polo_store_be.Controller.Admin;

import com.example.sd_95_polo_store_be.Model.Entity.OrderDetail;
import com.example.sd_95_polo_store_be.Model.Entity.Orders;
import com.example.sd_95_polo_store_be.Model.Request.ChangeStatusOrder;
import com.example.sd_95_polo_store_be.Model.Request.OrderRequest;
import com.example.sd_95_polo_store_be.Model.Response.OrderVnpayResponse;
import com.example.sd_95_polo_store_be.Service.ExportOrderPdfService;
import com.example.sd_95_polo_store_be.Service.OrderService;
import com.example.sd_95_polo_store_be.common.Response;
import com.itextpdf.text.Document;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ExportOrderPdfService exportOrderPdfService;

    @GetMapping
    public Response<List<Orders>> getAll() {
        return Response.ofSucceeded(orderService.getAll());
    }

    @PutMapping("/updateStatus/{id}")
    public Response<Void> updateStatus(@PathVariable Integer id,@RequestBody ChangeStatusOrder changeStatusOrder) {
        orderService.updateStatusOrder(id, changeStatusOrder);
        return Response.ofSucceeded();
    }

    @PostMapping("/{id}")
    public Response<OrderVnpayResponse> createOrder(@RequestBody OrderRequest orderRequest, @PathVariable Integer id){

        return Response.ofSucceeded(  orderService.OrderOffline(orderRequest, id));
    }

    @GetMapping("export/{id}")
    public void getExportPdf(@PathVariable Integer id, HttpServletResponse response) {
        System.out.println("ccccc");
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "inline; attachment; filename=file.pdf";
        response.setHeader(headerKey, headerValue);
        Document document = exportOrderPdfService.OrderPdfExport(id, response);
        System.out.println("ccccc");
    }

    @GetMapping("/byTimeRange")
    public Response<List<Orders>> getOrdersByTimeRange(
            @RequestParam String timeRange) {
        try {
            List<Orders> orders = orderService.getOrdersByTimeRange(timeRange);
            return Response.ofSucceeded(orders);
        } catch (Exception e) {
            return Response.ofError("Lỗi");
        }
    }


}
