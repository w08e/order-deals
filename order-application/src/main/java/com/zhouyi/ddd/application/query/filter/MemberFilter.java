package com.zhouyi.ddd.application.query.filter;

import lombok.Data;

/**
 * @author jinyueWang
 * @date 2023/4/15
 */

@Data
public class MemberFilter {

    /**
     * 主键id
     */
    private Long id;


    /**
     *
     */
    private Long memberNo;

    /**
     * 组成员id
     */
    private String name;
}
