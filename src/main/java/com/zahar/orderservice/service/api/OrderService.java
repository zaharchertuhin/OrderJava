package com.zahar.orderservice.service.api;

import com.zahar.orderservice.entity.Order;

import java.util.List;

public interface OrderService {
    Order getOrder(Long id);

    Order create(Order order);

    void delete(Long id);

    List<Order> getAll();
}
