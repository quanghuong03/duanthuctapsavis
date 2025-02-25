package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.OrderDetail;
import com.example.sd_95_polo_store_be.Model.Entity.Orders;
import com.example.sd_95_polo_store_be.Model.Request.ChangeStatusOrder;
import com.example.sd_95_polo_store_be.Model.Request.OrderDetailRequest;
import com.example.sd_95_polo_store_be.Model.Request.OrderRequest;
import com.example.sd_95_polo_store_be.Model.Response.*;
import com.example.sd_95_polo_store_be.Repository.*;
import com.example.sd_95_polo_store_be.Service.CartService;
import com.example.sd_95_polo_store_be.Service.OrderDetailService;
import com.example.sd_95_polo_store_be.Service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Orders> getByCustomer(Integer id) {
        return orderRepository.findByCustomersId(id);
    }

    @Override
    public List<Orders> getAll() {
        return orderRepository.findByOrderByCreateDateDesc();
    }

    @Override
    @Transactional
    public void updateStatusOrder(Integer id, ChangeStatusOrder changeStatusOrder) {
        var now = OffsetDateTime.now();
        var order = orderRepository.findById(id).orElseThrow();
        var transaction = transactionsRepository.findById(4).orElseThrow();
        switch (changeStatusOrder.getStatus()) {
            case 1 -> {
                order.setStatus(1);
                orderRepository.save(order);
            }
            case 2 -> {
                order.setStatus(2);
                order.setShipCost(changeStatusOrder.getShipCost());
                order.setConfirmDate(now);
                orderRepository.save(order);
            }
            case 3 -> {
                order.setStatus(3);
                orderRepository.save(order);
            }
            case 4 -> {
                order.setStatus(4);
                order.setShipDate(now);
                orderRepository.save(order);
            }
            case 5 -> {
                order.setStatus(5);
                order.setTransactions(transaction);
                order.setSuccessDate(now);
                orderRepository.save(order);
            }
            case 6 -> {
                order.setStatus(6);
                order.setSuccessDate(now);
                orderRepository.save(order);
            }
            case 7 -> {
                if (order.getStatus() == 1 || order.getStatus() == 3
                        || order.getStatus() == 2 || order.getStatus() == 6 || order.getStatus() == 8)
                    order.setStatus(7);
                order.setNote(changeStatusOrder.getNote());
                orderRepository.save(order);
            }
            case 8 -> {
                order.setStatus(8);
                order.setSuccessDate(now);
                orderRepository.save(order);
            }
        }
    }

    @Override
    public OrderVnpayResponse orderOnline(OrderRequest orderRequest, Integer id) {
        var now = OffsetDateTime.now();
        var customer = customerRepository.findById(id).orElseThrow();
        var transaction = transactionsRepository.findById(1).orElseThrow();
        Orders orders = new Orders();
        orders.setAddress(orderRequest.getAddress());
        orders.setTotalPrice(orderRequest.getTotalPrice());
        orders.setStatus(1);
        orders.setWeight(orderRequest.getWeight());
        orders.setCustomers(customer);
        orders.setUsername(orderRequest.getUsername());
        orders.setPhone(orderRequest.getPhone());
        orders.setCreatedAt(now);
        orders.setShopping("Đặt hàng");
        orders.setTransactions(transaction);
        orders.setUpdatedAt(now);
        orderRepository.save(orders);

        List<OrderDetailRequest> orderDetailRequests = orderRequest.getOrderDetailRequest();
        orderDetailRequests.forEach(request -> orderDetailService.create(request, orders.getId()));

        CartResponse cartResponse = cartService.getOneByStatus(id);
        List<CartDetailResponse> list = cartResponse.getCartDetailResponses();
        list.forEach(cartDetail -> cartDetailRepository.deleteById(cartDetail.getCartDetailId()));

        return generteOrder(orders);

    }

    private OrderVnpayResponse generteOrder(Orders orders) {
        return new OrderVnpayResponse().setId(orders.getId()).setTotalPrice(orders.getTotalPrice());
    }

    @Override
    public Orders get(Integer id) {
        return getById(id);
    }

    @Override
    public void changeTransaction(Integer id) {
        var order = getById(id);
        var now = OffsetDateTime.now();
        var transaction = transactionsRepository.findById(2).orElseThrow();
        order.setTransactions(transaction);
        order.setUpdatedAt(now);
        orderRepository.save(order);
    }

    @Override
    public OrderResponse getOneOrder(Integer id) {
        var orderResponse = orderRepository.getId(id).orElseThrow();
        var orderDetail = orderDetailRepository.getId(id);
        for (OrderDetailResponse orderDetailResponse : orderDetail) {
            var image = orderDetailRepository.getImage(orderDetailResponse.getId());
            orderDetailResponse.setImage(image);
        }
        orderResponse.setOrderDetailResponse(orderDetail);
        return orderResponse;
    }

    @Override
    public OrderVnpayResponse OrderOffline(OrderRequest orderRequest, Integer id) {
        var admin = adminRepository.findById(id).orElseThrow();
        var now = OffsetDateTime.now();
        var transaction = transactionsRepository.findById(orderRequest.getTransactionId()).orElseThrow();
            if (orderRequest.getUsername() == null || orderRequest.getUsername().isEmpty()) {
            orderRequest.setUsername("Khách lẻ");
        }
        if (orderRequest.getAddress() == null) {
            orderRequest.setAddress("Mua tại cửa hàng");
        }
        Orders orders = new Orders();
        orders.setAddress(orderRequest.getAddress());
        orders.setTotalPrice(orderRequest.getTotalPrice());
        orders.setWeight(orderRequest.getWeight());
        orders.setAdmins(admin);
        orders.setUsername(orderRequest.getUsername());
        orders.setPhone(orderRequest.getPhone());
        orders.setCreatedAt(now);
        orders.setShipCost(orderRequest.getShipCost());
        orders.setShopping(orderRequest.getShopping());
        if (orderRequest.getShopping().equals("Tại quầy")) {
            orders.setStatus(5);
            orders.setTransactions(transaction);
        } else {
            orders.setStatus(2);
            orders.setTransactions(transaction);
        }
        orders.setTransactions(transaction);
        orders.setUpdatedAt(now);
        orderRepository.save(orders);

        List<OrderDetailRequest> orderDetailRequests = orderRequest.getOrderDetailRequest();
        orderDetailRequests.forEach(request -> orderDetailService.create(request, orders.getId()));

        return generteOrder(orders);

    }

    @Override
    public List<Orders> getOrdersByTimeRange(String timeRange) {
        switch (timeRange) {
            case "15days":
                return orderRepository.findByCreateDateAfter(OffsetDateTime.now().minusDays(15));
            case "30days":
                return orderRepository.findByCreateDateAfter(OffsetDateTime.now().minusDays(30));
            default:

                return orderRepository.findAll();
        }
    }

    private Orders getById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IndexOutOfBoundsException("Order not found"));
    }

}
