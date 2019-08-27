package com.u8.service;


import com.u8.entity.Order;

import java.util.List;
import java.util.Optional;


public interface OrderService {
    List<Order> findByIsSend(Integer isSend);

    Optional<Order> findById(Long id);
}
