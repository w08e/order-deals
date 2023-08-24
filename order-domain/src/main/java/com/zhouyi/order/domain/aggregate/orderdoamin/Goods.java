package com.zhouyi.order.domain.aggregate.orderdoamin;



import com.zhouyi.order.domain.aggregate.orderdoamin.entity.GoodsEntity;
import org.hibernate.service.spi.ServiceException;

import java.math.BigDecimal;

/**
 * @author jinyueWang
 * @date 2023/8/1
 */

public class Goods {
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 图片
     */
    private String image;
    /**
     * 原始价格
     */
    private BigDecimal originalPrice;
    /**
     * 现价
     */
    private BigDecimal seckillPrice;
    /**
     * 库存
     */
    private Integer stock;

    public void reduceInventory(int quantity) {

        if (quantity > this.stock) {
            throw new ServiceException("库存不足。。。");
        }

        this.stock -= quantity;

    }

    public static Goods fromEntity(GoodsEntity entity) {
        Goods goods = new Goods();
        goods.setId(entity.getId());
        goods.setName(entity.getName());
        goods.setImage(entity.getImage());
        goods.setOriginalPrice(entity.getOriginalPrice());
        goods.setSeckillPrice(entity.getSeckillPrice());
        goods.setStock(entity.getStock());
        return goods;
    }

    public Long getId() {
        return id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public BigDecimal getSeckillPrice() {
        return seckillPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setSeckillPrice(BigDecimal seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}

