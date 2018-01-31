package com.cys.common.domain;

import com.cys.support.jpa.SearchFilter;
import com.cys.util.BeanUtils;
import com.cys.util.ReflectUtils;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyuan on 2018/1/31.
 */
public class Query {

    private Pageable pageable;

    private List<SearchFilter> filters;

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public List<SearchFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<SearchFilter> filters) {
        this.filters = filters;
    }

    /**
     * 转成mybatis的查询对象
     *
     * @return
     */
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        for (SearchFilter searchFilter : this.filters) {
            map.put(searchFilter.getFieldName(),
                    searchFilter.getValue() != null ? searchFilter.getValue()
                            : searchFilter.getValues());
        }
        return map;
    }

    /**
     * 转成mybatis的查询对象DTO
     *
     * @param clazz
     * @return
     * @throws Exception
     */
    public Object getBean(Class clazz) {
        try {
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> tmp = this.getMap();
            for (String key : tmp.keySet()) {
                if (key.contains(".")) {
                    String[] names = key.split("\\.");
                    Object object = null ,tmpObject1 = null;
                    // 保存对象
                    for(int i=0;i<names.length;i++) {
                        if(i==0) {
                            object = map.get(names[i]);
                            if(null == object){
                                object = clazz.getDeclaredField(names[i]).getType().newInstance();
                            }
                            tmpObject1 = object;
                        } else {
                            if(i == names.length -1) {
                                ReflectUtils.setFieldValue(tmpObject1, names[i], tmp.get(key));
                            } else {
                                Object tmpObject2 = tmpObject1.getClass().getDeclaredField(names[i]).getType().newInstance();
                                ReflectUtils.setFieldValue(tmpObject1, names[i], tmpObject2);
                                tmpObject1 = tmpObject2;
                            }
                        }
                    }
                    map.put(names[0], object);
                } else {
                    map.put(key, tmp.get(key));
                }
            }
            return BeanUtils.convertMap(clazz, map);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
