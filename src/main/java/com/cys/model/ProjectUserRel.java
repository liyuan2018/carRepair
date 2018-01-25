package com.cys.model;

/**
 * Created by liyuan on 2018/1/24.
 */

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ly on 2016/11/1.
 */
@Entity
@Table(name="pm_project_user_relation")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("ProjectUserRel")
public  class ProjectUserRel extends BaseModel {

    /**
     * 关联类型
     */
    @Column(name = "category", columnDefinition = "VARCHAR")
    private String category;
    /**
     * 项目ID
     */
    @Column(name = "pm_project_id", columnDefinition = "VARCHAR")
    private String pmProjectId;
    /**
     * 关联人员
     */
    @Column(name = "user_id", columnDefinition = "VARCHAR")
    private String userId;
    /**
     * 创建人
     */
    @Column(name = "creator_id", columnDefinition = "VARCHAR")
    private String creatorId;

    /**
     * 创建时间
     */
    @Column(name = "created_time", columnDefinition = "TIMESTAMP")
    private Date createdTime;

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public void setCreatedTime(Date creatorTime) {
        this.createdTime = creatorTime;
    }

    public String getCreatorId() {
        return this.creatorId;
    }

    public Date getCreatedTime() {
        return this.createdTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPmProjectId() {
        return pmProjectId;
    }

    public void setPmProjectId(String pmProjectId) {
        this.pmProjectId = pmProjectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}


