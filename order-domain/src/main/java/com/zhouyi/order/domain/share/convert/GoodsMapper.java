package com.zhouyi.order.domain.share.convert;
import com.zhouyi.order.domain.aggregate.orderdoamin.Goods;
import com.zhouyi.order.domain.aggregate.orderdoamin.entity.GoodsEntity;
import org.mapstruct.Mapper;

/**
 * @author jinyueWang
 * @date 2023/8/1
 */
@Mapper
public interface GoodsMapper {


    GoodsEntity goodsToGoodsEntity(Goods goods);

    Goods goodsEntityToGoods(GoodsEntity entity);
}
