package com.cys.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by liyuan on 2018/1/24.
 */
@MappedSuperclass
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 2458742039986328707L;
    @GeneratedValue(
            generator = "system-uuid",
            strategy = GenerationType.IDENTITY
    )
    @GenericGenerator(
            name = "system-uuid",
            strategy = "uuid"
    )
    @Column(
            name = "id",
            columnDefinition = "VARCHAR(32)"
    )
    @Id
    protected String id;

    public BaseModel() {
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(o != null && this.getClass() == o.getClass()) {
            BaseModel baseModel = (BaseModel)o;
            return (new EqualsBuilder()).append(this.id, baseModel.id).isEquals();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(17, 37)).append(this.id).toHashCode();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}