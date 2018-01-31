package com.cys.support.querry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.cys.common.enums.LogicType;
import com.cys.common.enums.MatchType;
import com.cys.exception.SystemException;
import com.cys.support.jpa.SearchFilter;
import com.cys.util.ParamUtils;
import com.cys.util.StringUtils;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询filter解析器 将查询对象filter转成jpa条件List<SearchFilter> 或mybatis查询对象DTO或Map
 * Created by liyuan on 2018/1/31.
 */
public class FilterParser {

    private Sort sort;
    private List<SearchFilter> searchFilters = new ArrayList<>();

    public FilterParser(String filter) {
        this(filter,null);
    }

    public FilterParser(String filter,String order) {
        // TODO 效验filter格式是否合法
        if (StringUtils.isEmpty(filter) || "undefined".equals(filter))
            return;

        JSONObject filterObject = JSON
                .parseObject(filter, Feature.OrderedField);
        // 排序处理
        if (filterObject.containsKey("order")) {
            Object orderContent = filterObject.get("order");
            List<Sort.Order> orders = new ArrayList<Sort.Order>();
            if (orderContent instanceof String) {
                orders.add(createOrder((String) orderContent));
            } else if (orderContent instanceof String[]) {
                for (String item : (String[]) orderContent) {
                    orders.add(createOrder(item));
                }
            } else if (orderContent instanceof List) {
                for (String item : (List<String>) orderContent) {
                    orders.add(createOrder(item));
                }
            }
            Sort sort = new Sort(orders);
            this.setSort(sort);
        }
        if (!StringUtils.isEmpty(order) && !"undefined".equals(order)){
            JSONObject orderObject = JSON
                    .parseObject("{order:" + order + "}", Feature.OrderedField);
            List<Sort.Order> orders = new ArrayList<Sort.Order>();
            Object orderContent =orderObject.get("order");
            if (orderContent instanceof String) {
                orders.add(createOrder((String) orderContent));
            } else if (orderContent instanceof String[]) {
                for (String item : (String[]) orderContent) {
                    orders.add(createOrder(item));
                }
            } else if (orderContent instanceof List) {
                for (String item : (List<String>) orderContent) {
                    orders.add(createOrder(item));
                }
            }
            Sort sort = new Sort(orders);
            this.setSort(sort);
        }
        // where条件处理
        if (filterObject.containsKey("where")) {
            JSONObject whereJsonObject = filterObject.getJSONObject("where");
            this.searchFilters.addAll(parse(whereJsonObject, null));
            //对兼容默认无and的语法转换
            //handleTransfer();
        }
    }

    /**
     * 对默认  "{\"where\":{\"status\":\"2\",\"or\":[{\"name\":{\"like\":\"2\"}},{\"sex\":{\"like\":\"1\"}}]}}";
     * 转换为  "{\"where\":{\"and\":[{\"status\":{\"eq\":\"2\"}},{\"or\":[{\"name\":{\"like\":\"2\"}},{\"sex\":{\"like\":\"1\"}}]}]}}"
     */
//	private void handleTransfer(){
//		List<SearchFilter> tmpList = new ArrayList<>();
//		for(int i=0;i<this.searchFilters.size();i++){
//			SearchFilter item = this.searchFilters.get(i);
//			if(item.getLogicType().equals(LogicType.OR)){
//				System.out.printf("sss");
//			}
//		}
//		tmpList.addAll(this.searchFilters);
//		this.searchFilters.clear();
//
//	}

    /**
     * 将条件转换成后端searchFilter对象
     *
     * @param jsonObject
     * @param logicType
     * @return
     */
    private List<SearchFilter> parse(JSONObject jsonObject, LogicType logicType) {
        SearchFilter searchFilter = null;
        List<SearchFilter> searchFilters = new ArrayList<SearchFilter>();
        List<String> keys = new ArrayList<String>(jsonObject.keySet());
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            if (key.equalsIgnoreCase("and")) {
                if (ParamUtils.isNotNull(searchFilter)) {
                    //含有or的需要默认LogicType and
                    if(ParamUtils.isNull(logicType))
                        logicType  = LogicType.AND;
                    searchFilter.addRoundFilter(
                            parse(handleJSONArray(jsonObject.getJSONArray(key),
                                    LogicType.AND), LogicType.AND), logicType);
                } else {
                    searchFilters.addAll(parse(
                            handleJSONArray(jsonObject.getJSONArray(key),
                                    LogicType.AND), LogicType.AND));
                }
            } else if (key.equalsIgnoreCase("or")) {
                if (ParamUtils.isNotNull(searchFilter)) {
                    //含有or的需要默认LogicType and
                    if(ParamUtils.isNull(logicType))
                        logicType  = LogicType.AND;

                    searchFilter.addRoundFilter(
                            parse(handleJSONArray(jsonObject.getJSONArray(key),
                                    LogicType.OR), LogicType.OR), logicType);
                } else {
                    searchFilters.addAll(parse(
                            handleJSONArray(jsonObject.getJSONArray(key),
                                    LogicType.OR), LogicType.OR));
                }
            } else {
                Object obj = jsonObject.get(key);
                if(obj instanceof JSONObject ) {
                    JSONObject value = jsonObject.getJSONObject(key);
                    if (i == 0) {
                        searchFilter = createSearchFilter(key, value, LogicType.AND);
                    } else {
                        if (ParamUtils.isNull(logicType)) {
                            searchFilter = createSearchFilter(key, value);
                        } else {
                            searchFilter = createSearchFilter(key, value, logicType);
                        }
                    }
                }
                else{
                    searchFilter = createSearchFilter(key, obj);
                }
                searchFilters.add(searchFilter);
            }
        }
        return searchFilters;
    }

    /**
     * 处理and和or数组
     *
     * @param jsonArray
     * @param roundLogicType
     * @return
     */
    private JSONObject handleJSONArray(JSONArray jsonArray,
                                       LogicType roundLogicType) {
        if (ParamUtils.isNotNull(jsonArray)) {
            if (jsonArray.size() < 2) {
                throw new SystemException(roundLogicType + "查询条件数必须大于2!");
            }
        }
        JSONObject jsonObject = new JSONObject(true);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject temp = jsonArray.getJSONObject(i);
            for (String key : temp.keySet()) {
                jsonObject.put(key, temp.get(key));
            }
        }
        return jsonObject;
    }

    private SearchFilter createSearchFilter(String field,
                                            JSONObject fieldValue, LogicType logicType) {
        SearchFilter searchFilter = this.createSearchFilter(field, fieldValue);
        searchFilter.setLogicType(logicType);
        return searchFilter;
    }

    private SearchFilter createSearchFilter(String field, JSONObject fieldValue) {
        // TODO 日期转换问题
        for (String matchType : fieldValue.keySet()) {
            if(!isValidMatchType(matchType)) {
                return new SearchFilter(field, MatchType.EQ, fieldValue);
            }
            Object value = fieldValue.get(matchType);
            if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) value;
                Object[] objects = new Object[jsonArray.size()];
                for (int i = 0; i < jsonArray.size(); i++) {
                    objects[i] = jsonArray.get(i);
                }
                return new SearchFilter(field,
                        this.toMatchType(matchType), objects);
            } else {
                return new SearchFilter(field,
                        this.toMatchType(matchType), value);
            }
        }
        return null;
    }

    private boolean isValidMatchType(String matchType) {
        switch (matchType) {
            case "eq":
            case "lt":
            case "lte":
            case "gt":
            case "gte":
            case "neq":
            case "like":
            case "llike":
            case "rlike":
            case "nlike":
            case "inq":
            case "nin":
            case "between":
            case "null":
            case "nnull":
                return true;
            default:
                return false;
        }
    }

    private SearchFilter createSearchFilter(String field, Object fieldValue) {
        return new SearchFilter(field,MatchType.EQ, fieldValue);
    }

    private MatchType toMatchType(String matchType) {
        switch (matchType) {
            case "eq":
                return MatchType.EQ;
            case "lt":
                return MatchType.LT;
            case "lte":
                return MatchType.LTE;
            case "gt":
                return MatchType.GT;
            case "gte":
                return MatchType.GTE;
            case "neq":
                return MatchType.NEQ;
            case "like":
                return MatchType.LIKE;
            case "llike":
                return MatchType.LLIKE;
            case "rlike":
                return MatchType.RLIKE;
            case "nlike":
                return MatchType.NLIKE;
            case "inq":
                return MatchType.INQ;
            case "nin":
                return MatchType.NIN;
            case "between":
                return MatchType.BETWEEN;
            case "null":
                return MatchType.NULL;
            case "nnull":
                return MatchType.NNULL;
            default:
                return null;
        }
    }

    /**
     * 根据排序内容，生成排序对象
     *
     * @param orderContent
     */
    private Sort.Order createOrder(String orderContent) {
        String[] arrays = orderContent.split(" ");
        Sort.Order order;
        if ("ASC".equalsIgnoreCase(arrays[1].trim())) {
            order = new Sort.Order(Sort.Direction.ASC, arrays[0]);
        } else {
            order = new Sort.Order(Sort.Direction.DESC, arrays[0]);
        }
        return order;
    }

    public List<SearchFilter> toSearchFilters() {
        return searchFilters;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

}

