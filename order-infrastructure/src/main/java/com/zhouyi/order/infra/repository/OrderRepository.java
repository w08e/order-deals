package com.zhouyi.order.infra.repository;

import com.zhouyi.order.domain.aggregate.orderdoamin.Goods;
import com.zhouyi.order.domain.aggregate.orderdoamin.Order;
import com.zhouyi.order.domain.aggregate.orderdoamin.entity.OrderEntity;
import com.zhouyi.order.domain.aggregate.userdomain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author jinyueWang
 * @date 2023/8/1
 */
@Repository
@Transactional
public class OrderRepository {

    private final EntityManager em;

    private final UserRepository userRepository;

    private final GoodsRepository goodsRepository;

    public OrderRepository(EntityManager em, UserRepository userRepository, GoodsRepository goodsRepository) {
        this.em = em;
        this.userRepository = userRepository;
        this.goodsRepository = goodsRepository;
    }

    public Optional<Order> load(Long id){
        return Optional.ofNullable(em.find(OrderEntity.class, id)).map(this::convert);
    }

    public void save(Order order) {
        OrderEntity entity = null;
        if (order.getId() == null) {
            entity = order.toEntity();
            em.persist(entity);
            order.setId(entity.getId());
        } else {
            entity = em.find(OrderEntity.class, order.getId());
            entity.setPrice(order.getPrice());
            em.merge(entity);
        }
    }


    public Order convert(OrderEntity entity) {
        User user = userRepository.load(entity.getUserId()).orElse(null);
        Goods goods = goodsRepository.load(entity.getGoodsId()).orElse(null);
        Order order = new Order();
        order.setId(entity.getId());
        order.setUser(user);
        order.setGoodsList(List.of(goods));
        order.setPaid(entity.getPaid());
        order.setQuantity(entity.getQuantity());
        order.setCreateTime(entity.getCreateTime());
        return order;
    }


}
