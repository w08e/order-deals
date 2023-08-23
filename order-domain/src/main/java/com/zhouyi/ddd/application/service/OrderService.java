package com.zhouyi.ddd.application.service;


import com.zhouyi.ddd.domain.aggregate.orderdoamin.Goods;
import com.zhouyi.ddd.domain.aggregate.orderdoamin.domainService.OrderDomainService;
import com.zhouyi.ddd.domain.aggregate.orderdoamin.repository.GoodsRepository;
import com.zhouyi.ddd.domain.aggregate.userdomain.User;
import com.zhouyi.ddd.domain.aggregate.userdomain.repository.UserRepository;
import com.zhouyi.ddd.interfaces.command.OrderCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    private final UserRepository userRepository;
    private final GoodsRepository goodsRepository;
    private final OrderDomainService orderDomainService;

    public OrderService(UserRepository userRepository, GoodsRepository goodsRepository, OrderDomainService orderDomainService) {
        this.userRepository = userRepository;
        this.goodsRepository = goodsRepository;
        this.orderDomainService = orderDomainService;
    }

    /**
     * 不做任何处理的下单秒杀
     */
    public String order(OrderCommand command) {

        User user = userRepository.load(command.getUserId()).orElse(null);
        Goods goods = goodsRepository.load(command.getGoodsId()).orElse(null);

        try {
            orderDomainService.placeOrder(user, goods, command.getQuantity());
            return "success";
        } catch (Exception e) {
            log.error("下单失败");
            return "filed";
        }

    }
}
