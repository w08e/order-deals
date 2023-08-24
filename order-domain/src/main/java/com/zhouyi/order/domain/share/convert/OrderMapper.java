package com.zhouyi.order.domain.share.convert;

import org.mapstruct.Mapper;

/**
 * @author jinyueWang
 * @date 2023/8/1
 */
@Mapper(uses = OrderMapper.class)
public interface OrderMapper {
//    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
////    @Mapping(source = "id", target = "id")
////    @Mapping(source = "user.id", target = "userId")
////    @Mapping(source = "goods.id", target = "goodsId")
////    @Mapping(target = "price", source = "price")
////    @Mapping(target = "status", source = "status")
////    @Mapping(target = "createTime", source = "createTime")
//    OrderEntity orderToOrderEntity(Order entity);
//
//
////    @Mapping(target = "user.id", source = "userId")
////    @Mapping(target = "goods.id", source = "goodsId")
////    @Mapping(target = "price", source = "price")
////    @Mapping(target = "num", source = "num")
//    Order commandToDomain(OrderCommand command);
}
