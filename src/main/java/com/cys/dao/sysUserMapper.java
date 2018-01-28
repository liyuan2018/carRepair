package com.cys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface sysUserMapper {
    int countByExample(sysUserExample example);

    int deleteByExample(sysUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExampleWithBLOBsWithRowbounds(sysUserExample example, RowBounds rowBounds);

    List<SysUser> selectByExampleWithBLOBs(sysUserExample example);

    List<SysUser> selectByExampleWithRowbounds(sysUserExample example, RowBounds rowBounds);

    List<SysUser> selectByExample(sysUserExample example);

    SysUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") sysUserExample example);

    int updateByExampleWithBLOBs(@Param("record") SysUser record, @Param("example") sysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") sysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKeyWithBLOBs(SysUser record);

    int updateByPrimaryKey(SysUser record);
}