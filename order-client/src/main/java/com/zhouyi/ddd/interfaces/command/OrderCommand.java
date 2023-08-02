package com.zhouyi.ddd.interfaces.command;

/**
 * @author jinyueWang
 * @date 2023/8/1
 */

public class OrderCommand {
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 用户id
     */
    private Long userId;

    public Long getGoodsId() {
        return goodsId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getUserId() {
        return userId;
    }
}
