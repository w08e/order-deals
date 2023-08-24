package com.zhouyi.order.infra.repository;

import com.zhouyi.order.domain.aggregate.userdomain.User;
import com.zhouyi.order.domain.aggregate.userdomain.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * @author jinyueWang
 * @date 2023/8/1
 */
@Repository
public class UserRepository {

    private final EntityManager em;

    public UserRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<User> load(Long id) {
        return Optional.of(em.find(UserEntity.class, id)).map(this::convert);
    }

    public void save(User user) {
        UserEntity entity = null;
        if (user.getId() == null) {
            entity = this.convert(user);
            em.persist(entity);
            user.setId(entity.getId());
        } else {
            UserEntity userEntity = em.find(UserEntity.class, user.getId());
            userEntity.setName(user.getName());
            em.merge(userEntity);
        }
    }

    private User convert(UserEntity entity) {
        User user = new User();
        user.setName(entity.getName());
        user.setId(entity.getId());
        return user;
    }

    public UserEntity convert(User user) {
        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setId(user.getId());
        return entity;

    }

}
