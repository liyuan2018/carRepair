package com.cys.service.impl;

import com.cys.model.BaseModel;
import com.cys.model.BaseRelationModel;
import com.cys.support.jpa.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by liyuan on 2018/3/1.
 */
public interface IBaseRelationService<T extends BaseRelationModel, ID extends Serializable> {

    public T create(@Valid T model);

    public List<T> batchCreate(@Valid @Size(min = 1) List<T> models);

    public void deleteById(ID id);

    public void deleteByIds(@Size(min = 1) ID[] ids);

    public T findOne(T model);

    public List<T> find(T entity);

    /**
     * 获取包含主表信息的关联关系分页列表
     *
     * @param relativeId
     *            辅表ID
     * @param principalType
     *            辅表类型
     * @param searchFilters
     *            查询参数
     * @param pageable
     *            分页参数
     * @return 包含主表信息的关联关系分页列表
     * @throws Exception
     */
    public Page<Map<String, Object>> findWithMaster(String relativeId,
                                                    String principalType, List<SearchFilter> searchFilters,
                                                    Pageable pageable) throws Exception;

    /**
     * 获取包含主表信息的关联关系列表
     *
     * @param relativeId
     *            辅表ID
     * @param principalType
     *            辅表类型
     * @param searchFilters
     *            查询参数
     * @return 包含主表信息的关联关系列表
     * @throws Exception
     */
    public List<Map<String, Object>> findWithMaster(String relativeId,
                                                    String principalType, List<SearchFilter> searchFilters)
            throws Exception;

    /**
     * 获取包含辅表信息的关联关系分页列表
     *
     * @param relativeId
     *            主表ID
     * @param principalType
     *            辅表类型
     * @param searchFilters
     *            查询参数
     * @param pageable
     *            分页参数
     * @return 包含辅表信息的关联关系分页列表
     * @throws Exception
     */
    public Page<Map<String, Object>> findWithPrincipal(String relativeId,
                                                       String principalType, List<SearchFilter> searchFilters,
                                                       Pageable pageable) throws Exception;

    /**
     * 获取包含辅表信息的关联关系列表
     *
     * @param relativeId
     *            主表ID
     * @param principalType
     *            辅表类型
     * @param searchFilters
     *            查询参数
     * @return 包含辅表信息的关联关系列表
     * @throws Exception
     */
    public List<Map<String, Object>> findWithPrincipal(String relativeId,
                                                       String principalType, List<SearchFilter> searchFilters)
            throws Exception;

    /**
     * 获取未关联的主表分页列表
     *
     * @param relativeId    相对辅表ID
     * @param principalType 相对辅表类型
     * @param searchFilters 查询参数
     * @param pageable      分页信息
     * @return 未关联的主表分页列表
     * @throws Exception
     */
    public Page excludeMaster(String relativeId, String principalType,
                              List<SearchFilter> searchFilters, Pageable pageable)
            throws Exception;

    /**
     * 获取未关联的辅表分页列表
     *
     * @param relativeId    相对主表ID
     * @param principalType 辅表类型
     * @param searchFilters 查询参数
     * @param pageable      分页信息
     * @return 未关联的辅表分页列表
     * @throws Exception
     */
    public Page excludePrincipal(String relativeId, String principalType,
                                 List<SearchFilter> searchFilters, Pageable pageable)
            throws Exception;

    /**
     * 根据objectId查询关联关系
     *
     * @param objectId
     * @return
     * @throws Exception
     */
    public List<T> findByObjectId(String objectId);

    /**
     * 根据objectIds查询关联关系
     *
     * @param objectIds
     * @return
     */
    public List<T> findByObjectIdIn(List<String> objectIds);

    /**
     * 根据principalType和objectId查询关联关系
     *
     * @param principalType
     * @param objectId
     * @return
     */
    public List<T> findByPrincipalTypeAndObjectId(String principalType,
                                                  String objectId);

    /**
     * 根据principalType和objectIds查询关联关系
     *
     * @param principalType
     * @param objectIds
     * @return
     */
    public List<T> findByPrincipalTypeAndObjectIdIn(String principalType, List<String> objectIds);

    /**
     * 根据principalType和principalId查询关联关系
     *
     * @param principalType
     * @param principalId
     */
    public List<T> findByPrincipalTypeAndPrincipalId(String principalType,
                                                     String principalId);

    /**
     * 根据obiectId删除关联关系
     *
     * @param objectId
     */
    public void deleteByObjectId(String objectId);

    /**
     * 根据obiectIds删除关联关系
     *
     * @param objectIds
     */
    public void deleteByObjectIdIn(List<String> objectIds);

    /**
     * 根据principalType和objectId删除关联关系
     *
     * @param principalType
     * @param objectId
     */
    public void deleteByPrincipalTypeAndObjectId(String principalType,
                                                 String objectId);

    /**
     * 根据principalType和principalId删除关联关系
     *
     * @param principalType
     * @param principalId
     */
    public void deleteByPrincipalTypeAndPrincipalId(String principalType,
                                                    String principalId);

    /**
     * 根据principalType和principalId和objectId删除关联关系
     *
     * @param principalType
     * @param principalId
     * @param objectId
     * @throws Exception
     */
    public void deleteByPrincipalTypeAndPrincipalIdAndObjectId(
            String principalType, String principalId, String objectId);

    /**
     * 根据principalId删除关联关系
     *
     * @param principalId
     */
    public void deleteByPrincipalId(String principalId);

    /**
     * 批量保存关联对象
     *
     * @param objectId
     * @param principalType
     * @param principals
     * @return
     */
    public List<T> createRelations(String objectId, String principalType, List<? extends BaseModel> principals);

}
