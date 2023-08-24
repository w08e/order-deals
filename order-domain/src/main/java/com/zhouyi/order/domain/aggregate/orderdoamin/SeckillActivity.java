package com.zhouyi.order.domain.aggregate.orderdoamin;

import java.time.LocalDateTime;

/**
 * @author jinyueWang
 * @date 2023/8/1
 */
public class SeckillActivity {

    private Integer id;
    /**
     * 货品id
     */
    private Integer goodsId;
    /**
     * 活动开始时间
     */
    private LocalDateTime startTime;
    /**
     * 活动结束时间
     */
    private LocalDateTime endTime;
    /**
     * 库存
     */
    private Integer stock;

    public SeckillActivity(Integer id, Integer goodsId, LocalDateTime startTime, LocalDateTime endTime, Integer stock) {
        this.id = id;
        this.goodsId = goodsId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Integer getStock() {
        return stock;
    }
}

