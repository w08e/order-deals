package com.zhouyi.order.domain.aggregate.userdomain.repository;

import com.zhouyi.order.common.jpa.BaseRepository;
import com.zhouyi.order.domain.aggregate.userdomain.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {

}
