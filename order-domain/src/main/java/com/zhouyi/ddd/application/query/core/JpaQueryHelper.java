package com.zhouyi.ddd.application.query.core;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQuery;


import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class JpaQueryHelper {

    private final JPAQuery<?> query;

    private final Function<String, ComparableExpressionBase<?>> columnMapping;

    public JpaQueryHelper(JPAQuery<?> query, Function<String, ComparableExpressionBase<?>> columnMapping) {
        this.query = query.clone();
        this.columnMapping = columnMapping;
    }

    public <T> QueryResult<T> query(QueryParameter parameter) {

        JPAQuery<?> q = query.clone();

        config(parameter, q);

        Long total = null;
        List<?> list;
        if (parameter != null && parameter.isWithTotal()) {
            QueryResults<?> r = q.fetchResults();
            total = r.getTotal();
            list = r.getResults();
        } else {
            list = q.fetch();
        }
        return (QueryResult<T>) QueryResult.of(list, total);
    }

    public void config(QueryParameter parameter, JPAQuery<?> query) {
        // 处理分页
        if (parameter.getPageNumber() != null && parameter.getPageSize() != null) {
            query.offset((parameter.getPageNumber() - 1) * parameter.getPageSize());
            query.limit(parameter.getPageSize());
        }
        // 处理排序
        List<QueryParameter.Sort> sorts = parameter.getSorts();
        if (parameter.getSorts() != null && !sorts.isEmpty()) {
            List<OrderSpecifier<?>> o = sorts.stream().map(sort -> {
                ComparableExpressionBase<?> column = columnMapping.apply(sort.getField());
                if (column == null) {
                    throw new IllegalStateException("不支持对" + sort.getField() + "进行排序");
                }
                if (sort.getDirection() == QueryParameter.Direction.ASCENDING) {
                    return column.asc();
                } else if (sort.getDirection() == QueryParameter.Direction.DESCENDING) {
                    return column.desc();
                } else {
                    throw new IllegalStateException("不支持的排序方式: " + sort.getDirection());
                }
            }).collect(Collectors.toList());
            query.orderBy(o.toArray(new OrderSpecifier[]{}));
        }
    }
}
