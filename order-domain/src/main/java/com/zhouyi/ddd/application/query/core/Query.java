package com.zhouyi.ddd.application.query.core;


public interface Query {

    <R> QueryResult<R> execute(QueryParameter parameter);
}
