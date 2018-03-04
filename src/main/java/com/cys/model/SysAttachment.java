package com.cys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by liyuan on 2018/2/28.
 */
@Entity
@Table(
        name = "sys_attachment"
)
public class SysAttachment extends BaseModel {
    private static final long serialVersionUID = -7607485310306029216L;
    @Column(name ="name",columnDefinition = "VARCHAR")
    private String name;
    @Column(name ="type",columnDefinition = "VARCHAR")
    private String type;
    @Column(name ="path",columnDefinition = "VARCHAR")
    private String path;
    @Column(name ="size",columnDefinition = "Long")
    private long size;
    @Column(name ="module",columnDefinition = "VARCHAR")
    private String module;
    @Column(name ="sub_module",columnDefinition = "VARCHAR")
    private String subModule;
    @Column(name ="creator_id",columnDefinition = "VARCHAR")
    private String creatorId;
    @Column(name ="created_time",columnDefinition = "TIMESTAMP")
    private Date createdTime;
    @Transient
    private String fileSizeWithUnit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSubModule() {
        return subModule;
    }

    public void setSubModule(String subModule) {
        this.subModule = subModule;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getFileSizeWithUnit() {
        return fileSizeWithUnit;
    }

    public void setFileSizeWithUnit(String fileSizeWithUnit) {
        this.fileSizeWithUnit = fileSizeWithUnit;
    }
}
