package com.cys.service.impl;

import com.cys.common.enums.MatchType;
import com.cys.exception.BusinessException;
import com.cys.exception.SystemException;
import com.cys.model.BaseModel;
import com.cys.model.BaseRelationModel;
import com.cys.repository.BaseJpaRepository;
import com.cys.service.IBaseService;
import com.cys.support.jpa.DynamicSpecifications;
import com.cys.support.jpa.SearchFilter;
import com.cys.util.ReflectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.annotation.Validated;

import javax.persistence.criteria.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyuan on 2018/1/28.
 */
@Validated
public abstract class BaseServiceImpl<T, ID extends Serializable> implements
        IBaseService<T, ID> {

    public static final Logger logger = LoggerFactory
            .getLogger(BaseServiceImpl.class);

    @Autowired
    protected BaseJpaRepository<T, ID> repository;

    @Override
    public T create(@Valid T model) {
        return repository.save(model);
    }

    @Override
    public List<T> batchCreate(@Valid @Size(min = 1) List<T> models) {
        return repository.save(models);
    }

    @Override
    public T update(@Valid T model) {
        return repository.save(model);
    }

    @Override
    public T delete(T model) {
        repository.delete(model);
        return model;
    }

    @Override
    public void deleteById(ID id) {
        repository.delete(id);
    }

    @Override
    public void deleteByIds(@Size(min = 1) ID[] ids) {
        repository.deleteByIds(ids);
    }

    @Override
    public T findById(ID id) {
        return repository.findOne(id);
    }

    @Override
    public T findOne(T model) {
        return repository.findOne(model);
    }

    @Override
    public boolean exists(T model) {
        BaseModel baseModel = (BaseModel) model;
        return repository.exists((ID) baseModel.getId());
    }

    @Override
    public boolean exists(ID id) {
        return repository.exists(id);
    }

    @Override
    public boolean uniqueness(String field, Object value) {
        return uniqueness(field, value, null);
    }

    public boolean uniqueness(String field, Object value, String id) {
        List<SearchFilter> searchFilters = new ArrayList<SearchFilter>();
        searchFilters.add(new SearchFilter(field, MatchType.EQ, value));
        if (!StringUtils.isBlank(id)) {
            searchFilters.add(new SearchFilter("id", MatchType.NEQ, id));
        }
        Specification spec = DynamicSpecifications.bySearchFilter(searchFilters);
        return CollectionUtils.isEmpty(repository.findAll(spec));
    }

    @Override
    public List<T> find(List<SearchFilter> searchFilters) {
        return this.repository.find(searchFilters);
    }

    public List<T> findByIds(List<ID> ids) {
        return this.repository.findAll(ids);
    }

    @Override
    public Page<T> find(List<SearchFilter> searchFilters, Pageable page) {
        return this.repository.find(searchFilters, page);
    }

    @Override
    public List<T> find(T entity) {
        return this.repository.find(entity);
    }

    @Override
    public Page<T> find(T entity, Pageable page) {
        return this.repository.find(entity, page);
    }

    public Page<T> findMaster(Class<? extends BaseRelationModel> relationClass, String relativeId, String principalType,
                              List<SearchFilter> searchFilters, Pageable pageable) {
        Specification<T> spec = getMasterSpecification(relationClass, relativeId, principalType, searchFilters, false);
        return repository.findAll(spec, pageable);
    }

    public List<T> findMaster(Class<? extends BaseRelationModel> relationClass, String relativeId, String principalType,
                              List<SearchFilter> searchFilters) {
        Specification<T> spec = getMasterSpecification(relationClass, relativeId, principalType, searchFilters, false);
        return repository.findAll(spec);
    }

    public Page<T> findPrincipal(final Class<? extends BaseRelationModel> relationClass, final String relativeId,
                                 final List<SearchFilter> searchFilters, Pageable pageable) {
        Specification<T> spec = getPrincipalSpecification(relationClass, relativeId, searchFilters, false);
        return repository.findAll(spec, pageable);
    }

    public List<T> findPrincipal(final Class<? extends BaseRelationModel> relationClass, final String relativeId,
                                 final List<SearchFilter> searchFilters) {
        Specification<T> spec = getPrincipalSpecification(relationClass, relativeId, searchFilters, false);
        return repository.findAll(spec);
    }

    public List<T> excludeMaster(Class<? extends BaseRelationModel> relationClass, String relativeId, String principalType,
                                 List<SearchFilter> searchFilters) {
        Specification<T> spec = getMasterSpecification(relationClass, relativeId, principalType, searchFilters, true);
        return repository.findAll(spec);
    }

    public Page<T> excludeMaster(Class<? extends BaseRelationModel> relationClass, String relativeId, String principalType,
                                 List<SearchFilter> searchFilters, Pageable pageable) {
        Specification<T> spec = getMasterSpecification(relationClass, relativeId, principalType, searchFilters, true);
        return repository.findAll(spec, pageable);
    }

    public List<T> excludePrincipal(Class<? extends BaseRelationModel> relationClass, String relativeId,
                                    List<SearchFilter> searchFilters) {
        Specification<T> spec = getPrincipalSpecification(relationClass, relativeId, searchFilters, true);
        return repository.findAll(spec);
    }

    public Page<T> excludePrincipal(Class<? extends BaseRelationModel> relationClass, String relativeId,
                                    List<SearchFilter> searchFilters, Pageable pageable) {
        Specification<T> spec = getPrincipalSpecification(relationClass, relativeId, searchFilters, true);
        return repository.findAll(spec, pageable);
    }

    /**
     * 保存或更新数据（在代码中设置id的大批量数据,有一定的性能问题）。
     *
     * @param entitys
     */
    @Override
    public List<T> saveOrUpdateInBatch(@Valid @Size(min = 1) List<T> entitys) {
        repository.saveOrUpdateInBatch(entitys);
        return entitys;
    }

    @Override
    public List<T> saveInBatch(@Valid @Size(min = 1) List<T> entitys) {
        repository.saveInBatch(entitys);
        return entitys;
    }

    /**
     * 判断指定模型类型是否属于关联关系类型的主类型
     *
     * @param relationClass 关联关系类型
     * @param principalType 被判断模型类型
     * @return true，主；false，辅
     */
    boolean isMaster(Class<? extends BaseRelationModel> relationClass, String principalType) {
        BaseRelationModel relationModel;
        try {
            relationModel = relationClass.newInstance();
        } catch (Exception ignore) {
            ignore.printStackTrace();
            throw new SystemException("New instance error : " + relationClass.getSimpleName());
        }
        Boolean isMaster = relationModel.validate(principalType);
        if (isMaster == null) {
            throw new BusinessException("Error, relationType is not support : " + principalType);
        }
        return isMaster;
    }

    /**
     * 获得关联关系主表信息的Specification
     *
     * @param relationClass 关联关系模型类型
     * @param relativeId    关系表记录ID
     * @param principalType 关系表类型
     * @param searchFilters 查询参数
     * @param isExclude     是否查找已关联，已关联：true；反之false
     * @return Specification
     */
    private Specification<T> getMasterSpecification(final Class<? extends BaseRelationModel> relationClass, final String relativeId,
                                                    final String principalType, final List<SearchFilter> searchFilters, final boolean isExclude) {
        String masterType = getModelClassSimpleName();
        boolean isMaster = isMaster(relationClass, masterType);
        if (!isMaster) {
            throw new BusinessException(masterType + " is not the master of " + relationClass.getSimpleName());
        }
        Specification<T> spec = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Subquery subquery = query.subquery(relationClass);
                Root<?> subRoot = subquery.from(relationClass);
                subquery.select(subRoot).where(
                        cb.and(cb.equal(subRoot.get("principalType"), principalType),
                                cb.equal(subRoot.get("principalId"), relativeId),
                                cb.equal(subRoot.get("objectId"), root.get("id"))));
                query.where(cb.and(
                        isExclude ? cb.not(cb.exists(subquery)) : cb.exists(subquery),
                        DynamicSpecifications.bySearchFilter(searchFilters, getModelClass())
                                .toPredicate(root, query, cb)));
                return query.getRestriction();
            }
        };
        return spec;
    }

    /**
     * 获取关联关系辅表信息的Specification
     *
     * @param relationClass 关联关系模型类型
     * @param relativeId    主表记录ID
     * @param searchFilters 查询参数
     * @param isExclude     是否查找已关联，已关联：true；反之false
     * @return Specification
     */
    private Specification<T> getPrincipalSpecification(final Class<? extends BaseRelationModel> relationClass, final String relativeId,
                                                       final List<SearchFilter> searchFilters, final boolean isExclude) {
        final String principalType = getModelClassSimpleName();
        boolean isMaster = isMaster(relationClass, principalType);
        if (isMaster) {
            throw new BusinessException(principalType + " is not the principal of " + relationClass.getSimpleName());
        }
        Specification<T> spec = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Subquery subquery = query.subquery(relationClass);
                Root<?> subRoot = subquery.from(relationClass);
                subquery.select(subRoot).where(
                        cb.and(cb.equal(subRoot.get("principalType"), principalType),
                                cb.equal(subRoot.get("objectId"), relativeId),
                                cb.equal(subRoot.get("principalId"), root.get("id"))));
                query.where(cb.and(
                        isExclude ? cb.not(cb.exists(subquery)) : cb.exists(subquery),
                        DynamicSpecifications.bySearchFilter(searchFilters, getModelClass())
                                .toPredicate(root, query, cb)));
                return query.getRestriction();
            }
        };
        return spec;
    }

    /**
     * 获取泛型模型的名称
     *
     * @return
     */
    private String getModelClassSimpleName() {
        return getModelClass().getSimpleName();
    }

    /**
     * 获取泛型模型的类型
     *
     * @return
     */
    private Class<T> getModelClass() {
        return (Class<T>) ReflectUtils.getClassGenricType(getClass());
    }
}
