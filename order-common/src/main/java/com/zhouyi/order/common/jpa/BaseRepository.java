package com.zhouyi.order.common.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author jinyuewang
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long>, QuerydslPredicateExecutor<T> {
}
