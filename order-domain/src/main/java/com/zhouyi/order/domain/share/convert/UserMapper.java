package com.zhouyi.order.domain.share.convert;

import com.zhouyi.order.domain.aggregate.userdomain.User;
import com.zhouyi.order.domain.aggregate.userdomain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author jinyueWang
 * @date 2023/8/1
 */
@Mapper(uses = UserMapper.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User userEntityToUser(UserEntity user);
    UserEntity userToUserEntity(User user);
}
