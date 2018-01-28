package com.cys.repository;

import com.cys.support.jpa.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liyuan on 2018/1/28.
 */
@NoRepositoryBean
public abstract interface BaseJpaRepository<T,ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T> {

    /**
     * 根据特定条件删除实体
     * @param object
     */
    int  deleteInBatch(T object);

    /**
     * 根据特定条件集合删除实体
     * @param object
     */
    int  deleteInBatch(List<T> objects);

    /**
     * 保存或更新数据（在代码中设置id的大批量数据,有一定的性能问题）。
     * @param entitys
     * @param <E>
     */
    @Transactional(rollbackFor = Exception.class)
    <E extends T> void saveOrUpdateInBatch(Iterable<E> entitys);

    /**
     * 批量保存对象
     * @param entitys   对象集合
     * @param <E>       实体类
     */
    @Transactional(rollbackFor = Exception.class)
    <E extends T> void saveInBatch(Iterable<E> entitys);

    /**
     * 高危注意：此为多线程批量处理，一般用于性能测试场景，正规业务请勿用此方法
     * @param entitys   实体集合
     * @return          实体集合
     */
    @Transactional
    List<T> saveInBatchWithMultithreaded(List<T> entitys);

    /**
     * 批量删除实体
     * @param ids   实体id数组
     */
    int deleteByIds(ID[] ids);

    /**
     * 不分页查询
     * @param searchFilters
     * @return
     */

    List<T> find(List<SearchFilter> searchFilters);

    /**
     * 分页查询
     * @param searchFilters
     * @param page
     * @return
     */
    Page<T> find(List<SearchFilter> searchFilters, Pageable page);

    /**
     * 根据filterMaps不分页查询
     * @param entity
     * @return
     */
    List<T> find(T entity);

    /**
     * 根据filterMaps分页查询
     * @param entity
     * @return
     */
    Page<T> find(T entity, Pageable page);

    /**
     * 查询单一对象
     * @param entity
     * @return
     */
    T findOne(T entity);
}
