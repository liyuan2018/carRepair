package com.cys.repository;

/**
 *  自定义Repository基类实现
 * (放所有Repository都需要用到的方法)
 * @param <T>   实体
 * @param <I>  实体id
 * Created by liyuan on 2018/1/31.
 */

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;

public class BaseRepositoryFactoryBean<R extends JpaRepository<T, I>, T,
        I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {

    private SessionFactory sessionFactory;

    @Autowired
    public BaseRepositoryFactoryBean(EntityManagerFactory entityManagerFactory) {
        this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
        return new CustomRepositoryFactory(em, sessionFactory);
    }

    private static class CustomRepositoryFactory<T, I extends Serializable>
            extends JpaRepositoryFactory {

        private final EntityManager em;
        private final SessionFactory sessionFactory;

        public CustomRepositoryFactory(EntityManager em, SessionFactory sessionFactory) {
            super(em);
            this.em = em;
            this.sessionFactory = sessionFactory;
        }

        protected Object getTargetRepository(RepositoryMetadata metadata) {
//            if(BaseRelationJpaRepository.class.isAssignableFrom(metadata.getRepositoryInterface())){
//                return new BaseJpaRelationRepositoryImpl<T, I>((Class<T>) metadata.getDomainType(), em);
//            }
//            else {
//                return new BaseJpaRepositoryImpl<T, I>((Class<T>) metadata.getDomainType(), em);
//            }
            return new BaseJpaRepositoryImpl<T, I>((Class<T>) metadata.getDomainType(), em, sessionFactory);
        }

        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
//            if(BaseRelationJpaRepository.class.isAssignableFrom(metadata.getRepositoryInterface())){
//                return BaseJpaRelationRepositoryImpl.class;
//            }
//            else {
//                return BaseJpaRepositoryImpl.class;
//            }
            return BaseJpaRepositoryImpl.class;
        }
    }
}
