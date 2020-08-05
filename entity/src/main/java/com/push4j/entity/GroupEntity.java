package com.push4j.entity;

import org.beetl.sql.core.annotatoin.Table;
import org.fastboot.db.model.BaseEntity;

import javax.validation.constraints.NotEmpty;

/**
 * 组对象实体
 *
 * @author Laotang
 * @since 1.0
 */
@Table(name = "group")
public class GroupEntity extends BaseEntity {

    /**用戶組名稱*/
    @NotEmpty(message = "用户组名称不能为空")
    private String name;
    /**用戶組描述*/
    private String desc;
    /**条件运算逻辑*/
    private ConditionEntity ConditionEntity;


    public GroupEntity() {
    }

    public GroupEntity(String name, String desc, com.push4j.entity.ConditionEntity conditionEntity) {
        this.name = name;
        this.desc = desc;
        ConditionEntity = conditionEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public com.push4j.entity.ConditionEntity getConditionEntity() {
        return ConditionEntity;
    }

    public void setConditionEntity(com.push4j.entity.ConditionEntity conditionEntity) {
        ConditionEntity = conditionEntity;
    }
}
