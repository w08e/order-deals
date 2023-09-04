package com.zhouyi.order.domain.aggregate.orderdoamin.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author jinyueWang
 * @date 2023/8/1
 */
@Entity
@Table(name = "seckill_activity")
public class SeckillActivity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "goods_id")
    private Integer goodsId;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "stock")
    private Integer stock;

    // 省略getter/setter

}
