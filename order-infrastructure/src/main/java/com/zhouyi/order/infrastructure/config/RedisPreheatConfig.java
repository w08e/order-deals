package com.zhouyi.order.infrastructure.config;

import com.zhouyi.ddd.domain.aggregate.orderdoamin.Goods;
import com.zhouyi.ddd.domain.aggregate.orderdoamin.repository.GoodsRepository;
import com.zhouyi.ddd.domain.share.enums.CacheKeyEnums;
import com.zhouyi.ddd.infrastructure.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author jinyueWang
 * @date 2023/8/16
 */
@Slf4j
@Component
public class RedisPreheatConfig {


    private final RedisUtils redisUtils;

    private final GoodsRepository goodsRepository;

    @Autowired
    public RedisPreheatConfig(RedisUtils redisUtils, GoodsRepository goodsRepository) {
        this.redisUtils = redisUtils;
        this.goodsRepository = goodsRepository;
    }

    /**
     * 商品缓存预热
     */
    @PostConstruct
    public void preheatRedisCache() {
        List<Goods> goods = goodsRepository.loadAllGoods().orElseThrow();
        goods.forEach(item -> {
            //todo 枚举啥的得优化
            String key = CacheKeyEnums.GOODS_INFO.getKey() + "_" + item.getId().toString();
            log.info("写入用户订单数据Set：[{}]", key);
            redisUtils.set(key, item.getStock().toString(), 3600L);
        });

    }
}
