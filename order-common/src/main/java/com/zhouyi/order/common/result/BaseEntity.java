package com.zhouyi.order.common.result;

import com.querydsl.core.BooleanBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.OffsetDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    protected OffsetDateTime createTime;

    /**
     * 更新时间
     */
    @Column(name = "modify_time")
    protected OffsetDateTime modifyTime;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "is_deleted")
    private Boolean isDeleted;


    public BaseEntity() {
        createTime = OffsetDateTime.now();
        modifyTime = createTime;
        isDeleted = false;
    }

    public boolean isDelete() {
        return isDeleted != null && isDeleted;
    }

    public void del() {
        this.isDeleted = true;
    }

    public Integer getVersion() {
        return version;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public OffsetDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(OffsetDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
