package com.zhouyi.order.domain.aggregate.orderdoamin.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author jinyueWang
 * @date 2023/8/1
 */
@Data
@Entity
@Table(name = "goods")
public class GoodsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "original_price")
    private BigDecimal originalPrice;

    @Column(name = "seckill_price")
    private BigDecimal seckillPrice;

    @Column(name = "stock")
    private Integer stock;

    @Version
    private int version;

}