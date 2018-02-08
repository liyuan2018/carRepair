package com.cys.interfacepage

import java.util.*

/**
 * Created by liyuan on 2018/2/9.
 */
import java.util.Date

/**
 * 创建者和创建时间可监听
 * （通过监听器自动设置值）
 * @author gaozhiqiang
 */
interface ICreateListenable {

    /** 获取创建者 */
    /** 设置创建者  */
    var creatorId: String

    /** 获取创建时间 */
    /** 设置创建时间  */
    var createdTime: Date
}
