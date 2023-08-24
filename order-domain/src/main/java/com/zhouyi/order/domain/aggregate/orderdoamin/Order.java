package com.zhouyi.order.domain.aggregate.orderdoamin;

import com.zhouyi.order.domain.aggregate.orderdoamin.entity.OrderEntity;
import com.zhouyi.order.domain.aggregate.userdomain.User;
import com.zhouyi.order.domain.share.enums.OrderStatusEnums;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jinyueWang
 * @date 2023/8/1
 */
@Data
public class Order {

    private Long id;
    private BigDecimal price;
    private OrderStatusEnums paid;
    private Integer quantity;
    private LocalDateTime createTime;
    private User user;
    private List<Goods> goodsList;
    private List<SeckillActivity> seckillActivities;

    public Order(Integer quantity, User user, List<Goods> goodsList) {
        this.quantity = quantity;
        this.user = user;
        this.goodsList = goodsList;
    }

    public Order() {
    }

    public void applyPaid() {
        this.paid = OrderStatusEnums.UNPAID;
    }

    public void orderTime(){
        this.setCreateTime(LocalDateTime.now());
    }

    public OrderEntity toEntity() {
        OrderEntity entity = new OrderEntity();
        entity.setId(this.id);
        entity.setUserId(this.user.getId());
        entity.setGoodsId(this.getGoodsList()==null?null:this.getGoodsList().get(0).getId());
        entity.setPaid(this.getPaid());
        entity.setQuantity(this.getQuantity());
        entity.setCreateTime(this.getCreateTime());
        return entity;
    }
}
