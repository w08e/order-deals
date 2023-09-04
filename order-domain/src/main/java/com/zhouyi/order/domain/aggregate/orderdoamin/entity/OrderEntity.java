package com.zhouyi.order.domain.aggregate.orderdoamin.entity;



import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * @author jinyueWang
 * @date 2023/8/1
 */
@Entity
@Table(name = "stock_order")
public class OrderEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "goods_id")
    private Long goodsId;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private String paid;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "create_time")
    private LocalDateTime createTime;

//    public Long getId() {
//        return id;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public Long getGoodsId() {
//        return goodsId;
//    }
//
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public OrderStatusEnums getPaid() {
//        return paid;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public LocalDateTime getCreateTime() {
//        return createTime;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public void setGoodsId(Long goodsId) {
//        this.goodsId = goodsId;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//
//    public void setPaid(OrderStatusEnums paid) {
//        this.paid = paid;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public void setCreateTime(LocalDateTime createTime) {
//        this.createTime = createTime;
//    }
//
//    public OrderEntity(Long id, Long userId, Long goodsId, BigDecimal price, OrderStatusEnums paid, Integer quantity, LocalDateTime createTime) {
//        this.id = id;
//        this.userId = userId;
//        this.goodsId = goodsId;
//        this.price = price;
//        this.paid = paid;
//        this.quantity = quantity;
//        this.createTime = createTime;
//    }
//
//    public OrderEntity() {
//    }
}