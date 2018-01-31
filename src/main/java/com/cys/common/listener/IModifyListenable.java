package com.cys.common.listener;

import java.util.Date;

/**
 * Created by liyuan on 2018/1/31.
 */
public interface IModifyListenable {
    void setModifierId(String var1);

    void setModifiedTime(Date var1);

    String getModifierId();

    Date getModifiedTime();
}
