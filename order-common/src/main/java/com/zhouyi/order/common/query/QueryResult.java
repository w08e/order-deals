package com.zhouyi.order.common.query;

import java.util.List;

public class QueryResult<T> {

    public static <T> QueryResult<T> of(List<T> data) {
        return new QueryResult<>(data);
    }

    public static <T> QueryResult<T> of(List<T> data, Long total) {
        return new QueryResult<>(data, total);
    }

    private List<T> list;

    private Long total;

    public QueryResult(List<T> list, Long total) {
        this.total = total;
        this.list = list;
    }

    public QueryResult(List<T> list) {
        this.list = list;
    }

    public QueryResult() {
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
