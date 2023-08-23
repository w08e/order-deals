package com.zhouyi.ddd.application.query.core;

import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQuery;

public interface JpaQuery<F extends QueryParameter> {

    void select(JPAQuery<?> query, F filter);

    ComparableExpressionBase<?> columnMapping(String column);

}
