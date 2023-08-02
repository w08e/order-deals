package com.zhouyi.ddd.interfaces.dto;

import lombok.Data;

/**
 * @author jinyueWang
 * @date 2023/4/15
 */

@Data
public class MemberVo {

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
