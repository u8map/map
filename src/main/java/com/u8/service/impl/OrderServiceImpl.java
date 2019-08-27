package com.u8.service.impl;

import com.u8.entity.Order;
import com.u8.repository.OrderRepository;
import com.u8.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findByIsSend(Integer isSend) {
        return orderRepository.findByIsSend(isSend);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

}
