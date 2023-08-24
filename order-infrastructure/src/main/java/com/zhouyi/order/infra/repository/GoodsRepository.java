package com.zhouyi.order.infra.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zhouyi.order.domain.aggregate.orderdoamin.Goods;
import com.zhouyi.order.domain.aggregate.orderdoamin.entity.GoodsEntity;
import com.zhouyi.order.domain.aggregate.orderdoamin.entity.QGoodsEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author jinyueWang
 * @date 2023/8/1
 */
@Repository
@Transactional
public class GoodsRepository {

    private final EntityManager em;

    public GoodsRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Goods goods) {
        GoodsEntity entity = null;
        if (goods.getId() == null) {
            entity = this.convert(goods);
            em.persist(entity);
            goods.setId(entity.getId());
        } else {
            entity = em.find(GoodsEntity.class, goods.getId());
            entity.setStock(goods.getStock());
            em.merge(entity);
        }
    }

    public Optional<Goods> load(Long id) {
        return Optional.ofNullable(em.find(GoodsEntity.class, id)).map(this::convert);
    }

    /**
     * 获取全部商品
     */
    public Optional<List<Goods>> loadAllGoods() {
        return Optional.ofNullable(query(cur -> cur.stock.gt(0)));

    }

    private List<Goods> query(Function<QGoodsEntity, Predicate> condition) {
        QGoodsEntity t = QGoodsEntity.goodsEntity;
        return new JPAQueryFactory(em)
                .select(t)
                .from(t)
                .where(condition.apply(t))
                .fetch()
                .stream()
                .map(Goods::fromEntity)
                .collect(Collectors.toList());
    }

    private GoodsEntity convert(Goods goods) {
        GoodsEntity entity = new GoodsEntity();
        entity.setId(goods.getId());
        entity.setImage(goods.getImage());
        entity.setName(goods.getName());
        entity.setOriginalPrice(goods.getOriginalPrice());
        entity.setSeckillPrice(goods.getSeckillPrice());
        entity.setStock(goods.getStock());
        return entity;
    }

    private Goods convert(GoodsEntity entity) {
        Goods goods = new Goods();
        goods.setId(entity.getId());
        goods.setImage(entity.getImage());
        goods.setName(entity.getName());
        goods.setOriginalPrice(entity.getOriginalPrice());
        goods.setSeckillPrice(entity.getSeckillPrice());
        goods.setStock(entity.getStock());
        return goods;
    }
}
