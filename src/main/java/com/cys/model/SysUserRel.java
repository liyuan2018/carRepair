package com.cys.model;

import com.cys.enums.SysUserRelEnum;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liyuan on 2018/3/1.
 */
@Entity
@Table(name="sys_user_rel")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("sysUserRel")
public class SysUserRel extends BaseRelationModel {
    @Override
    public Boolean validate(String principalType) {
        List<SysUserRelEnum> results =  Arrays.stream(SysUserRelEnum.values()).filter(sysUserRelEnums -> sysUserRelEnums.toString().equals(principalType)).collect(Collectors.toList());
        return results.size()>0;
    }

    @Override
    public Class gainMasterClass() {
        return SysUser.class;
    }
}
