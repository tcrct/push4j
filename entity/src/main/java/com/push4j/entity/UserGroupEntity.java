package com.push4j.entity;

import org.beetl.sql.core.annotatoin.Table;
import org.fastboot.db.model.BaseEntity;

/**
 * 用户与组关联表
 *
 * @author Laotang
 * @since 1.0
 */
@Table(name = "userGroup")
public class UserGroupEntity extends BaseEntity {

    private String userId;
    private Integer groupId;


    public UserGroupEntity() {
    }

    public UserGroupEntity(String userId, Integer groupId, Integer unRead) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
