package com.zhouyi.order.domain.aggregate.orderdoamin.entity;

import javax.persistence.*;

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

    public SeckillActivity(Integer id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public SeckillActivity() {
    }
}
