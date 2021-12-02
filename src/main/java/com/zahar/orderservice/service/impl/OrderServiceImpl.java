package com.zahar.orderservice.service.impl;

import com.zahar.orderservice.commons.ErrorMessages;
import com.zahar.orderservice.entity.Order;
import com.zahar.orderservice.exception.NotFoundException;
import com.zahar.orderservice.repository.OrderRepository;
import com.zahar.orderservice.service.api.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order getOrder(Long id) {
        Order orderFromDb = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order with id: " + id + ErrorMessages.NOT_FOUND));
        return orderFromDb;
    }

    @Override
    public Order create(Order orderFromUser) {
        Order savedOrder = orderRepository.save(orderFromUser);
        return savedOrder;
    }

    @Override
    public void delete(Long id) {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            orderRepository.deleteById(order.get().getId());
        } else throw new NotFoundException("Order with id: " + id + ErrorMessages.NOT_FOUND);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
