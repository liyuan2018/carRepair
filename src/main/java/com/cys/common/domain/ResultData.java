package com.cys.common.domain;

import com.cys.util.ClassUtils;
import com.cys.util.Inflector;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.*;

/**
 * 结果数据类
 * Created by liyuan on 2018/1/31.
 */
public class ResultData {


    private static final long serialVersionUID = 1L;
    /**
     * 结果数据
     */
    private final Map<String, Object> data = new HashMap<String, Object>();

    /**
     * 实体集合的Key集合
     */
    private final List<String> entitiesKeys = new ArrayList<String>();
    /**
     * 实体的Key集合
     */
    private final List<String> entityKeys = new ArrayList<String>();

    /**
     * 分页元数据
     */
    private final Meta meta = new Meta();
    /**
     * 分页元数据设置状态
     */
    private boolean beenSeted = false;
    /**
     * 短名
     */
    private static final Inflector INFLECTOR = Inflector.getInstance();

    /**
     * meta key
     */
    public static final String META = "meta";
    /**
     * total key
     */
    public static final String TOTAL = "total";

    public ResultData(){

    }

    public ResultData(Class clazz, Object object) {
        // getGenericType();
        //分页处理
        if (object instanceof Page) {
            Page page = (Page) object;
            this.putEntities(clazz, page.getContent());
            this.setTotal(((Long) page.getTotalElements()).intValue(), page.getSize());
        }
        //不分页集合处理
        else if (object instanceof List) {
            this.putEntities(clazz, (Collection) object);
        }
        //单实体对象处理
        else {
            this.putEntity(clazz, object);
        }
    }

    public ResultData setMetaMap(Map  map) {
        beenSeted = true;
        this.meta.attributes = map;
        return this;
    }

    public ResultData(Class clazz, Object object,Map metaMap) {
        // getGenericType();
        //分页处理
        if (object instanceof Page) {
            Page page = (Page) object;
            this.putEntities(clazz, page.getContent());
            this.setTotal(((Long) page.getTotalElements()).intValue(), page.getSize());
            this.setMetaMap(metaMap);
        }
        //不分页集合处理
        else if (object instanceof List) {
            this.putEntities(clazz, (Collection) object);
        }
        //单实体对象处理
        else {
            this.putEntity(clazz, object);
        }
    }

    public ResultData(String key, Object object) {
        this.put(key,object);
    }

    /**
     * 设置分页总记录数  没有总页数
     *
     * @param total 总记录数
     * @return
     */
    public ResultData setTotal(int total) {
        beenSeted = true;
        this.meta.total = total;
        return this;
    }

    /**
     * 设置分页总记录数,总页数
     *
     * @param total 总记录数
     * @param limit 单页数
     * @return
     */
    public ResultData setTotal(int total, int limit) {
        beenSeted = true;
        this.meta.total = total;
        this.meta.totalPage = (total + limit - 1) / limit;
        return this;
    }

    /**
     * 设置分页总记录数
     *
     * @param totalPage 总记录数
     * @return
     */
    @Deprecated
    public ResultData setTotalPage(int totalPage) {
        beenSeted = true;
        this.meta.totalPage = totalPage;
        return this;
    }

    /**
     * 添加实体数据集,已经存在则进行追加
     *
     * @param entityClass 实体Class
     * @param entities    实体数据集
     * @param <T>         实体类
     * @return
     */
    public <T> ResultData putEntities(Class entityClass, Collection<T> entities) {
        String key = getPluralKey(entityClass);
        if (entitiesKeys.contains(key)) {
            ((Collection<T>) data.get(key)).addAll(entities);
        } else {
            entitiesKeys.add(key);
            data.put(key, entities);
        }
        return this;
    }

    /**
     * 添加实体,已经存在则替换
     *
     * @param e   实体
     * @param <E> 实体类，不允许传入Integer、String、int等
     * @return
     */
    public <E> ResultData putEntity(Class clz, E e) {
        String key = INFLECTOR.lowerCamelCase(
                INFLECTOR.singularize(ClassUtils.getEntityNameWithoutDto(clz))
        );
        if (!entityKeys.contains(key)) {
            entityKeys.add(key);
        }
        data.put(key, e);
        return this;
    }

    /**
     * 添加实体,已经存在则替换
     *
     * @param e   实体
     * @param <E> 实体类，不允许传入Integer、String、int等
     * @return
     */
    @Deprecated
    public <E> ResultData putEntity(E e) {
        String key = INFLECTOR.lowerCamelCase(
                INFLECTOR.singularize(ClassUtils.getEntityNameWithoutDto(e.getClass()))
        );
        if (!entityKeys.contains(key)) {
            entityKeys.add(key);
        }
        data.put(key, e);
        return this;
    }

    /**
     * 添加除实体集合、实体之外的其他类型，比如: Map、Integer、String、int等
     *
     * @param key 添加项的key
     * @param e   添加项，一般为：Map、Integer、String、int等
     * @param <E>
     * @return
     * @throws IllegalArgumentException 添加的key和Entities、Entity的Key冲突异常
     */
    public <E> ResultData put(String key, E e) {
        if (entitiesKeys.contains(key) || entityKeys.contains(key)) {
            throw new IllegalArgumentException("添加的key: [" + key + "] 和Entities、Entity的Key冲突。");
        }

        data.put(key, e);
        return this;
    }

    /**
     * 获得包含分页元数据的实体数据集
     */
    public Map<String, Object> getEntities() {
        Map<String, Object> results = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            results.put(entry.getKey(), entry.getValue());
        }

        if (beenSeted) { // 设置过元数据
            results.put(META, meta);
        }
        return Collections.unmodifiableMap(results);
    }

    /**
     * 获得总记录数
     */
    public int getTotal() {
        return meta.total;
    }

    /**
     * 获得总记录数
     */
    public int getTotalPage() {
        return meta.totalPage;
    }

    /**
     * 元数据类
     */
    private class Meta implements Serializable {
        private static final long serialVersionUID = 1L;
        private int total;
        private int totalPage;

        private Map attributes;

        // for test
        public int getTotal() {
            return total;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public Map getAttributes() {
            return attributes;
        }
    }

    /**
     * 获得实体复数形式的Key
     */
    private String getPluralKey(Class entityClass) {
        return INFLECTOR.lowerCamelCase(
                INFLECTOR.pluralize(ClassUtils.getEntityNameWithoutDto(entityClass))
        );
    }
}
