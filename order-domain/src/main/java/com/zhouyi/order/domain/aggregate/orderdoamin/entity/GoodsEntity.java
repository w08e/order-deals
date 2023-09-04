package com.zhouyi.order.domain.aggregate.orderdoamin.entity;


import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author jinyueWang
 * @date 2023/8/1
 */
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

    public GoodsEntity(Long id, String name, String image, BigDecimal originalPrice, BigDecimal seckillPrice, Integer stock, int version) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.originalPrice = originalPrice;
        this.seckillPrice = seckillPrice;
        this.stock = stock;
        this.version = version;
    }

    public Long getId() {
        return id;
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

    public Integer getStock() {
        return stock;
    }

    public void setId(Long id) {
        this.id = id;
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

    public GoodsEntity() {
    }
}