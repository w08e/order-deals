package com.zhouyi.order.common.query;

import java.util.List;

public class QueryParameter {

    /**
     * 页码
     */
    private Long pageNumber;

    /**
     * 每页条数
     */
    private Long pageSize;

    /**
     * 是否查询总条目
     */
    private boolean withTotal;

    /**
     * 排序
     */
    private List<Sort> sorts;

    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isWithTotal() {
        return withTotal;
    }

    public void setWithTotal(boolean withTotal) {
        this.withTotal = withTotal;
    }

    public List<Sort> getSorts() {
        return sorts;
    }

    public void setSorts(List<Sort> sorts) {
        this.sorts = sorts;
    }


    public static class Sort {

        private String field;

        private Direction direction;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public Direction getDirection() {
            return direction;
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
        }
    }


    public enum Direction {
        ASCENDING,
        DESCENDING
    }
}
