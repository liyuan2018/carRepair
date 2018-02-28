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
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private String path;
    @Column
    private long size;
    @Column
    private String module;
    @Column
    private String subModule;
    @Column
    private String creatorId;
    @Column
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
