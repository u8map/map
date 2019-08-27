package com.u8.controller;


import com.u8.entity.Order;
import com.u8.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TestController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/index")
    public String index() {
//        Optional<Order> emptyOpt = orderService.findById(1L);
//        return emptyOpt.get().getIsSend().toString();

        List<Order> list = orderService.findByIsSend(1);
        return list.toString();
    }
}
