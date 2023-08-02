package com.zhouyi.ddd.interfaces.controller;


import com.zhouyi.ddd.application.service.OrderService;
import com.zhouyi.ddd.infrastructure.model.result.Result;
import com.zhouyi.ddd.interfaces.command.OrderCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinyueWang
 * @date 2023/4/15
 */
@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Result<String> order(@RequestBody OrderCommand command) {
        return Result.success(orderService.order(command));
    }

}
