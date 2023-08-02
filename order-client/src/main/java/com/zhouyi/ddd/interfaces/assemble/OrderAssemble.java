package com.zhouyi.ddd.interfaces.assemble;

import com.zhouyi.ddd.domain.aggregate.orderdoamin.Order;
import com.zhouyi.ddd.domain.share.convert.OrderMapper;
import com.zhouyi.ddd.interfaces.command.OrderCommand;
import org.mapstruct.factory.Mappers;

/**
 * @author jinyueWang
 * @date 2023/8/1
 */
public class OrderAssemble {

    /**
     * 能力类转领域对象
     */
    public Order commandToDomain(OrderCommand command) {
        OrderMapper mapper = Mappers.getMapper(OrderMapper.class);
        return mapper.commandToDomain(command);
    }
}
