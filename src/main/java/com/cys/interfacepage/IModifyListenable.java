package com.cys.interfacepage;

import java.util.Date;

/**
 * Created by liyuan on 2018/2/9.
 */
public interface IModifyListenable {
    /** 设置修改者 */
    void setModifierId(String userId);

    /** 设置修改时间 */
    void setModifiedTime(Date now);

    /** 获取修改者*/
    public String getModifierId();

    /** 获取修改时间*/
    public Date getModifiedTime();
}
