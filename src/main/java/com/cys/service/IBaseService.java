package com.cys.service;

import com.cys.model.BaseRelationModel;
import com.cys.support.jpa.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by liyuan on 2018/1/28.
 */
public abstract interface IBaseService<T, ID extends Serializable> {

    /**
     * 新增指定模型
     *
     * @param model
     * @return
     * @throws Exception
     */
    public T create(@Valid T model);

    /**
     * 批量新增
     *
     * @param models
     * @return
     * @throws Exception
     */
    public List<T> batchCreate(@Valid @Size(min = 1) List<T> models);

    /**
     * 更新指定模型
     *
     * @param model
     * @return
     * @throws Exception
     */
    public T update(@Valid T model);

    /**
     * 删除指定模型
     *
     * @param model
     * @return
     * @throws Exception
     */
    public T delete(T model);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    public void deleteById(ID id);

    /**
     * 根据ids批量删除
     *
     * @param ids
     * @throws Exception
     */
    public void deleteByIds(@Size(min = 1) ID[] ids);

    /**
     * 根据id查询模型
     *
     * @param id
     * @return
     * @throws Exception
     */
    public T findById(ID id);

    /**
     * 查询指定模型
     *
     * @param model
     * @return
     * @throws Exception
     */
    public T findOne(T model);

    /**
     * 根据ids查询列表
     *
     * @param ids id列表
     * @return 模型列表
     */
    public List<T> findByIds(List<ID> ids);

    /**
     * 查询是否存在
     *
     * @param model
     * @return
     * @throws Exception
     */
    public boolean exists(T model);

    /**
     * 查询是否存在
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean exists(ID id);

    /**
     * 查询是否唯一
     *
     * @param field
     * @param value
     * @return
     * @throws Exception
     */
    public boolean uniqueness(String field, Object value);

    public boolean uniqueness(String field, Object value, String id);

    /**
     * 不分页查询
     *
     * @param searchFilters
     * @return
     */

    public List<T> find(List<SearchFilter> searchFilters);

    /**
     * 分页查询
     *
     * @param searchFilters
     * @param page
     * @return
     */
    public Page<T> find(List<SearchFilter> searchFilters, Pageable page);

    /**
     * 根据entity不分页查询
     *
     * @param entity
     * @return
     */
    public List<T> find(T entity);

    /**
     * 根据entity分页查询
     *
     * @param entity
     * @return
     */
    public Page<T> find(T entity, Pageable page);

    /**
     * 获取关联关系主表记录分页列表
     *
     * @param relationClass 关联关系模型类型
     * @param relativeId    辅表记录ID
     * @param principalType 辅表类型
     * @param searchFilters 查询参数
     * @param pageable      分页参数
     * @return 关联关系主表分页列表
     */
    public Page<T> findMaster(Class<? extends BaseRelationModel> relationClass, String relativeId, String principalType,
                              List<SearchFilter> searchFilters, Pageable pageable);

    /**
     * 获取关联关系主表记录列表
     *
     * @param relationClass 关联关系模型类型
     * @param relativeId    辅表记录ID
     * @param principalType 辅表类型
     * @param searchFilters 查询参数
     * @return 关联关系主表记录列表
     */
    public List<T> findMaster(Class<? extends BaseRelationModel> relationClass, String relativeId, String principalType,
                              List<SearchFilter> searchFilters);

    /**
     * 获取关联关系辅表记录分页列表
     *
     * @param relationClass 关联关系模型类型
     * @param relativeId    主表记录ID
     * @param searchFilters 查询参数
     * @param pageable      分页参数
     * @return 关联关系辅表记录分页列表
     */
    public Page<T> findPrincipal(final Class<? extends BaseRelationModel> relationClass, final String relativeId,
                                 final List<SearchFilter> searchFilters, Pageable pageable);

    /**
     * 获取关联关系辅表记录列表
     *
     * @param relationClass 关联关系模型类型
     * @param relativeId    主表记录ID
     * @param searchFilters 查询参数
     * @return 关联关系辅表记录列表
     */
    public List<T> findPrincipal(final Class<? extends BaseRelationModel> relationClass, final String relativeId,
                                 final List<SearchFilter> searchFilters);

    /**
     * 获取未关联的关系主表记录列表
     *
     * @param relationClass 关联关系模型类型
     * @param relativeId    辅表记录ID
     * @param principalType 辅表类型
     * @param searchFilters 查询参数
     * @return 未关联的关系主表记录列表
     */
    public List<T> excludeMaster(Class<? extends BaseRelationModel> relationClass, String relativeId, String principalType,
                                 List<SearchFilter> searchFilters);

    /**
     * 获取未关联的关系主表记录分页列表
     *
     * @param relationClass 关联关系模型类型
     * @param relativeId    辅表记录ID
     * @param principalType 辅表类型
     * @param searchFilters 查询参数
     * @param pageable      查询参数
     * @return 未关联的关系主表记录分页列表
     */
    public Page<T> excludeMaster(Class<? extends BaseRelationModel> relationClass, String relativeId, String principalType,
                                 List<SearchFilter> searchFilters, Pageable pageable);

    /**
     * 获取未关联的关系辅表记录列表
     *
     * @param relationClass 关联关系模型类型
     * @param relativeId    主表记录ID
     * @param searchFilters 查询参数
     * @return 未关联的关系辅表记录列表
     */
    public List<T> excludePrincipal(Class<? extends BaseRelationModel> relationClass, String relativeId,
                                    List<SearchFilter> searchFilters);

    /**
     * 获取未关联的关系辅表记录分页列表
     *
     * @param relationClass 关联关系模型类型
     * @param relativeId    主表记录ID
     * @param searchFilters 查询参数
     * @param pageable      分页参数
     * @return 未关联的关系辅表记录分页列表
     */
    public Page<T> excludePrincipal(Class<? extends BaseRelationModel> relationClass, String relativeId,
                                    List<SearchFilter> searchFilters, Pageable pageable);

    /**
     * 批量增加或者更新数据（在代码中设置id的大批量数据,有一定的性能问题）
     *
     * @param entitys 需要添加或者更新的数据
     * @return
     */
    public List<T> saveOrUpdateInBatch(@Valid @Size(min = 1) List<T> entitys);

    /**
     * 批量增加数据
     *
     * @param entitys 需要添加的数据
     * @return
     */
    public List<T> saveInBatch(@Valid @Size(min = 1) List<T> entitys);
}
