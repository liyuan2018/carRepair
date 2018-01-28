package com.cys.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by liyuan on 2018/1/28.
 */
@MappedSuperclass
@XmlAccessorType(XmlAccessType.NONE)
public abstract class BaseRelationModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = -8027014495712928360L;

    @XmlAttribute
    @NotEmpty(message = "principalType不允许为空")
    @Column(name = "principal_type")
    protected String principalType;

    @XmlAttribute
    @NotEmpty(message = "principalId不允许为空")
    @Column(name = "principal_id")
    protected String principalId;

    @XmlAttribute
    @NotEmpty(message = "objectId不允许为空")
    @Column(name = "object_id")
    protected String objectId;

    public String getPrincipalType() {
        return principalType;
    }

    public void setPrincipalType(String principalType) {
        this.principalType = principalType;
    }

    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    /**
     * 验证关联关系
     *
     * @param principalType 关系表类型
     * @return 主表：true；副表；false；其他，null
     */
    public abstract Boolean validate(String principalType);

    /**
     * 获取关联关系主表
     * @return
     */
    public abstract Class gainMasterClass();

}

