package com.zhouyi.order.domain.aggregate.userdomain;

/**
 * @author jinyueWang
 * @date 2023/4/15
 */

public class User {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户名称
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
