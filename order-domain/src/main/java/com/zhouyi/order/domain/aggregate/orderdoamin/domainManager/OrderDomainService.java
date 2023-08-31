//package com.zhouyi.order.domain.aggregate.orderdoamin.domainService;
//
//import com.zhouyi.ddd.domain.aggregate.orderdoamin.Goods;
//import com.zhouyi.ddd.domain.aggregate.orderdoamin.Order;
//import com.zhouyi.ddd.domain.aggregate.orderdoamin.repository.GoodsRepository;
//import com.zhouyi.ddd.domain.aggregate.orderdoamin.repository.OrderRepository;
//import com.zhouyi.ddd.domain.aggregate.userdomain.User;
//import com.zhouyi.ddd.infrastructure.exception.ServiceException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author jinyueWang
// * @date 2023/8/1
// */
//@Service
//public class OrderDomainService {
//
//    private final OrderRepository orderRepository;
//    private final GoodsRepository goodsRepository;
//
//    @Autowired
//    public OrderDomainService(OrderRepository orderRepository, GoodsRepository goodsRepository) {
//        this.orderRepository = orderRepository;
//        this.goodsRepository = goodsRepository;
//    }
//
//    /**
//     * 下单
//     */
//    public Integer placeOrder(User user, Goods goods, int quantity) {
//
//        // 1. 创建订单聚合根对象
//        Order order = new Order(quantity, user, List.of(goods));
//
//        // 2. 库存校验
//        if (goods.getStock() < quantity) {
//            throw new ServiceException("库存不足。。。。");
//        }
//
//        // 3. 更新状态
//        goods.reduceInventory(quantity);
//        order.applyPaid();
//        order.orderTime();
//
//        // 4. 持久化
//        goodsRepository.save(goods);
//        orderRepository.save(order);
//
//        return goods.getStock();
//    }
//
//}
