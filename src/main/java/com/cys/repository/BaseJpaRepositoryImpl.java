package com.cys.repository;

import com.cys.common.enums.StatusEnum;
import com.cys.common.listener.IDeleteListenable;
import com.cys.common.listener.IModifyListenable;
import com.cys.support.jpa.DynamicSpecifications;
import com.cys.support.jpa.SearchFilter;
import com.cys.util.ParamUtils;
import com.cys.util.SessionUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by liyuan on 2018/1/31.
 */
public class BaseJpaRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements BaseJpaRepository<T, ID> {

    private static final Logger logger = LoggerFactory.getLogger(BaseJpaRepository.class);
    private static Executor pool = Executors.newWorkStealingPool(Math.max(Runtime.getRuntime().availableProcessors(), 32));

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager; // jpa 实体管理器
    private SessionFactory sessionFactory;
    /** 批量处理配置key */
    private static final String HIBERNATE_JDBC_BATCH_SIZE_KEY = "hibernate.jdbc.batch_size";
    /** 批量处理记录数 */
    private int batchSize = 100;
    private JdbcTemplate jdbcTemplate;

    public BaseJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.sessionFactory = entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);
        initBatchSize();
        initJdbcTemplate();
    }

    public BaseJpaRepositoryImpl(Class<T> domainClass, EntityManager em, SessionFactory sessionFactory) {
        super(domainClass, em);
        this.entityManager = em;
        this.entityManager.getProperties();
        this.sessionFactory = sessionFactory;
        initBatchSize();
        initJdbcTemplate();
    }

    private void initJdbcTemplate() {
        EntityManagerFactoryInfo info = (EntityManagerFactoryInfo) entityManager.getEntityManagerFactory();
        jdbcTemplate = new JdbcTemplate(info.getDataSource());
    }

    private void initBatchSize() {
        Object batchSizeObject = entityManager.getEntityManagerFactory().getProperties().get(HIBERNATE_JDBC_BATCH_SIZE_KEY);
        if (batchSizeObject != null) {
            batchSize = Integer.valueOf(batchSizeObject.toString());
        }
    }

    /**
     * 保存或更新数据（在代码中设置id的大批量数据,有一定的性能问题）。
     * @param entitys
     * @param <E>
     */
    @Override
    public <E extends T> void saveOrUpdateInBatch(Iterable<E> entitys) {
        Session session = entityManager.unwrap(Session.class);
        FlushMode previous = session.getFlushMode();
        session.flush();
        session.setFlushMode(FlushMode.MANUAL);
        int i = 1;
        for (E entity : entitys) {
            session.saveOrUpdate(entity);
            if (i++ % batchSize == 0) {
                session.flush();
                session.clear();
            }
        }
        session.setFlushMode(previous);
        session.flush();
        session.clear();
    }

    @Override
    public <E extends T> void saveInBatch(Iterable<E> entitys) {
        int i = 1;
        for (E entity : entitys) {
            entityManager.persist(entity);
            if (i++ % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    @Transactional
    public List<T> saveInBatchWithMultithreaded(List<T> entitys) {
        Assert.isTrue(!CollectionUtils.isEmpty(entitys), "批量处理传入的集合为空集合。");
        if (entitys.size() <= batchSize) {
            saveOrUpdateInBatch(entitys);
            return entitys;
        }

        return Lists.partition(entitys, batchSize)
                .stream()
                .map(innerEntitys -> CompletableFuture.supplyAsync(() -> {
                    Session innerSession = sessionFactory.openSession();
                    innerSession.setFlushMode(FlushMode.MANUAL);
                    try {
                        for (T entity : innerEntitys) {
                            innerSession.saveOrUpdate(entity);
                        }
                    } finally {
                        innerSession.flush();
                        innerSession.close();
                    }
                    return innerEntitys;
                }, pool))
                .collect(Collectors.toList())
                .parallelStream()
                .flatMap(innerEntitiesFuture -> innerEntitiesFuture.join().stream())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int deleteByIds(ID[] ids) {
        //逻辑删除
        if (IDeleteListenable.class.isAssignableFrom(getDomainClass())) {
            List<ID> list = Arrays.asList(ids);
            List<T> entities = this.findAll(list);
            for (T entity : entities) {
                ((IDeleteListenable) entity).setStatus(StatusEnum.DELETED.toString());
            }
            int result =  this.save(entities).size();
            return result;
        } else {
            //物理删除
//            final String hql = "delete from " + getDomainClass().getSimpleName() + " entity where entity.id in(:ids)";
//            List<ID> parameterList = Arrays.asList(ids);
//
//            if (logger.isInfoEnabled()) {
//                logger.info("deleteByIds parameter: {}", StringUtils.join(parameterList, ","));
//            }
//
//           int result =  entityManager.createQuery(hql)
//                    .setParameter("ids", parameterList)
//                    .executeUpdate();
//            entityManager.flush();
//            return result;
            for(ID id : ids){
                delete(id);
            }
            return ids.length;
        }
    }

    @Override
    @Transactional
    public void delete(ID id) {
        T entity = this.findOne(id);
        //逻辑删除
        if (entity instanceof IModifyListenable) {
            ((IModifyListenable) entity).setModifierId(SessionUtils.getCurrentUserId());
            ((IModifyListenable) entity).setModifiedTime(new Date(System.currentTimeMillis()));
        }
        if (entity instanceof IDeleteListenable) {
            ((IDeleteListenable) entity).setStatus(StatusEnum.DELETED.toString());
            this.save(entity);
        } else {
            //物理删除
            super.delete(id);
        }
    }

    @Override
    @Transactional
    public void delete(T entity) {
        //逻辑删除
        if (entity instanceof IModifyListenable) {
            ((IModifyListenable) entity).setModifierId(SessionUtils.getCurrentUserId());
            ((IModifyListenable) entity).setModifiedTime(new Date(System.currentTimeMillis()));
        }
        if (entity instanceof IDeleteListenable) {
            ((IDeleteListenable) entity).setStatus(StatusEnum.DELETED.toString());
            this.save(entity);
        } else {
            //物理删除
            super.delete(entity);
        }
    }

    @Override
    public List<T> find(List<SearchFilter> searchFilters) {
        Specification<T> spec = DynamicSpecifications.bySearchFilter(searchFilters);
        return this.findAll(spec);
    }

    @Override
    public Page<T> find(List<SearchFilter> searchFilters, Pageable page) {
        Specification<T> spec = DynamicSpecifications.bySearchFilter(searchFilters);
        return this.findAll(spec, page);
    }

    @Override
    public List<T> find(T entity) {
        Specification<T> spec = getSpecification(entity);
        return this.findAll(spec);
    }

    @Override
    public Page<T> find(T entity, Pageable page) {
        Specification<T> spec = getSpecification(entity);
        return this.findAll(spec, page);
    }

    @Override
    public T findOne(T entity) {
        Specification<T> spec = getSpecification(entity);
        return this.findOne(spec);
    }

    private Specification<T> getSpecification(T entity) {
        List<SearchFilter> searchFilters = SearchFilter.convertBean(entity);
        return DynamicSpecifications.bySearchFilter(searchFilters);
    }

    /**
     * 根据特定条件删除实体
     *
     * @param object
     */
    @Transactional
    public int deleteInBatch(T object) {
        String hql = QueryUtils.getQueryString("DELETE FROM %s x", this.getDomainClass().getSimpleName());
        StringBuilder sb = new StringBuilder(hql);
        sb.append(" WHERE ");
        Map map = BeanMap.create(object);
        Set<String> keys = map.keySet();
        boolean firstAttrFlag = true;
        for (String key : keys) {
            String value = (String) map.get(key);
            if (StringUtils.isNoneBlank(value)) {
                if(!firstAttrFlag) {
                    sb.append(" AND ");
                }
                sb.append(String.format("%s.%s = '%s'", new Object[]{"x", key, value}));
                firstAttrFlag = false;
            }
        }
        if (logger.isInfoEnabled()) {
            logger.info("deleteInBatch parameter: {}", StringUtils.join(object, ","));
        }
        return this.entityManager.createQuery(sb.toString()).executeUpdate();
    }

    /**
     * 根据特定条件批量删除实体
     */
    @Transactional
    public int deleteInBatch(List<T> objects) {
        if(ParamUtils.isEmpty(objects)) {
            return 0;
        }
        if(objects.size()==1) {
            return deleteInBatch(objects.get(0));
        }
        String hql = QueryUtils.getQueryString("DELETE FROM %s x", this.getDomainClass().getSimpleName());
        StringBuilder sb = new StringBuilder(hql);
        sb.append(" WHERE ");
        for(Iterator<T> objectIter = objects.iterator();objectIter.hasNext();) {
            T object = objectIter.next();
            sb.append("(");
            Map map = BeanMap.create(object);
            boolean firstAttrFlag = true;
            Set<String> keys = map.keySet();
            for (Iterator<String> iter = keys.iterator();iter.hasNext();) {
                String key = iter.next();
                String value = (String) map.get(key);
                if (StringUtils.isNoneBlank(value)) {
                    if(!firstAttrFlag) {
                        sb.append(" AND ");
                    }
                    sb.append(String.format("%s.%s = '%s'", new Object[]{"x", key, value}));
                    firstAttrFlag = false;
                }
            }
            sb.append(")");
            if(objectIter.hasNext()) {
                sb.append(" OR ");
            }
        }
        if (logger.isInfoEnabled()) {
            logger.info("deleteInBatch parameter: {}", StringUtils.join(objects, ","));
        }
        return this.entityManager.createQuery(sb.toString()).executeUpdate();
    }


    @Override
    public void delete(Iterable<? extends T> entities) {
        if(ParamUtils.isNull(entities))
            return;
        List<String> idList = new ArrayList<>();
        for(Iterator<? extends T> iterator = entities.iterator();iterator.hasNext();) {
            T object = iterator.next();
            Map beanMap = BeanMap.create(object);
            idList.add((String) beanMap.get("id"));
        }
        this.deleteByIds((ID[]) idList.toArray(new String[idList.size()]));
//		super.delete(entities);
    }
}
