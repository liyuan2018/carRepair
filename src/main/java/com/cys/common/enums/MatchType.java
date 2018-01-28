package com.cys.common.enums;

/**
 * jpa动态查询
 * Created by liyuan on 2018/1/28.
 */
public enum MatchType {
    /**
     * 等于
     * */
    EQ,
    /**
     *
     * 不等于
     *
     */
    NEQ,
    /**
     * like '%value%'
     * */
    LIKE,
    /**
     * like '%value'
     * */
    LLIKE,
    /**
     * like 'value%'
     * */
    RLIKE,
    /**
     * not like '%value%'
     * */
    NLIKE,
    /**
     * 小于
     * */
    LT,
    /**
     * 小于等于
     * */
    LTE,
    /**
     * 大于
     * */
    GT,
    /**
     * 大于等于
     * */
    GTE,
    /**
     *
     * 在两者之间
     *
     */
    BETWEEN,
    /**
     *
     * not null
     *
     */
    NULL,
    /**
     *
     * not null
     *
     */
    NNULL,
    /**
     *
     * in ()
     *
     */
    INQ,
    /**
     *
     * not in ()
     *
     */
    NIN

}

