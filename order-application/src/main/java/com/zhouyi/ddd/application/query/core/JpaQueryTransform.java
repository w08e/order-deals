package com.zhouyi.ddd.application.query.core;


public interface JpaQueryTransform<F extends QueryParameter> extends JpaQuery<F> {

    <R> R transform(Object v);
}
