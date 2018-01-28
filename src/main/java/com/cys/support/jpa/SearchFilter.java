package com.cys.support.jpa;

/**
 * springside动态查询
 * Created by liyuan on 2018/1/28.
 */

import com.cys.common.enums.LogicType;
import com.cys.common.enums.MatchType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.beans.BeanMap;

import java.util.*;


public class SearchFilter {

    private String fieldName;
    private Object value;
    private MatchType matchType;
    private Object[] values;
    private LogicType logicType = LogicType.AND;
    private LogicType roundLogicType;
    private List<SearchFilter> joinFilters = new ArrayList<SearchFilter>();

    public SearchFilter(String fieldName, MatchType matchType,
                        LogicType logicType, Object value) {
        this(fieldName, matchType, value);
        this.logicType = logicType;
    }

    public SearchFilter(String fieldName, MatchType matchType,
                        LogicType logicType, Object... values) {
        this(fieldName, matchType, values);
        this.logicType = logicType;
    }

    public SearchFilter(String fieldName, MatchType matchType, Object value) {
        this.fieldName = fieldName;
        this.value = value;
        this.matchType = matchType;
    }

    public SearchFilter(String fieldName, MatchType matchType, Object... values) {
        this.fieldName = fieldName;
        this.values = values;
        this.matchType = matchType;
    }

    /**
     * 将bean转化为SearchFilter
     *
     * @param bean
     *            实体类
     * @return List
     */
    public static List<SearchFilter> convertBean(Object bean) {
        Map map = BeanMap.create(bean);
        List<SearchFilter> searchFilters = new ArrayList<SearchFilter>();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            Object value = map.get(key);
            if(!com.cys.util.StringUtils.isEmpty(value))
                searchFilters.add(new SearchFilter(key, MatchType.EQ, value));
        }
        return searchFilters;
    }

    /**
     * searchParams中key的格式为OPERATOR_FIELDNAME
     */
    public static Map<String, SearchFilter> parse(
            Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = new HashMap();
        for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
            // 过滤掉空值
            String key = entry.getKey();
            Object value = entry.getValue();

            if (StringUtils.isBlank(String.valueOf(value))) {
                continue;
            }
            // 拆分operator与filedAttribute
            String[] names = StringUtils.split(key, "_");
            if (names.length != 2) {
                throw new IllegalArgumentException(key
                        + " is not a valid search filter name");
            }
            String filedName = names[1];
            MatchType operator = MatchType.valueOf(names[0].toUpperCase());
            // 创建searchFilter
            SearchFilter filter = new SearchFilter(filedName, operator, value);
            filters.put(key, filter);
        }
        return filters;
    }

    public SearchFilter addRoundFilter(List<SearchFilter> filters, LogicType logicType) {
        switch(logicType) {
            case AND:return this.addRoundAndFilter(filters);
            case OR:return this.addRoundOrFilter(filters);
            default:return this;
        }
    }

    public SearchFilter addRoundAndFilter(List<SearchFilter> filters) {
        this.setRoundLogicType(LogicType.AND);
        this.joinFilters.addAll(filters);
        return this;
    }

    public SearchFilter addRoundOrFilter(List<SearchFilter> filters) {
        this.setRoundLogicType(LogicType.OR);
        this.joinFilters.addAll(filters);
        return this;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

    public LogicType getLogicType() {
        return logicType;
    }

    public void setLogicType(LogicType logicType) {
        this.logicType = logicType;
    }

    public LogicType getRoundLogicType() {
        return roundLogicType;
    }

    public void setRoundLogicType(LogicType roundLogicType) {
        this.roundLogicType = roundLogicType;
    }

    public List<SearchFilter> getJoinFilters() {
        return joinFilters;
    }

    public void setJoinFilters(List<SearchFilter> joinFilters) {
        this.joinFilters = joinFilters;
    }

}

