package com.cys.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.cys.util.Base64Utils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import com.cys.util.AssertUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * 配置数据源
 * Created by liyuan on 2018/1/24.
 */
@Configuration
@MapperScan({"com.cys.**.mapper","com.cys.**.mybatis"})
@EnableTransactionManagement
public class DataSourceConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    @Value("${spring.datasource.max-active}")
    private int maxActive;
    //    @Value("${spring.datasource.max-idle}")
//    private int maxIdle;
    @Value("${spring.datasource.min-idle}")
    private int minIdle;
    @Value("${spring.datasource.initial-size}")
    private int initialSize;
    @Value("${spring.datasource.validation-query}")
    private String validationQuery;
    @Value("${spring.datasource.test-on-borrow}")
    private boolean testOnBorrow;
    @Value("${spring.datasource.test-on-return}")
    private boolean testOnReturn;
    @Value("${spring.datasource.test-while-idle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.time-between-eviction-runs-millis}")
    private long timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.min-evictable-idle-time-millis}")
    private long minEvictableIdleTimeMillis;
    @Value("${spring.datasource.max-wait-millis}")
    private long maxWaitMillis;

    @Autowired(required = true)
    private DataSourceProperties properties;

    @Autowired
    private MybatisProperties mybatisProperties;

    @Autowired
    private JpaProperties jpaProperties;


    @Bean
    public DataSource defaultDataSource(SystemConfig systemConfig) {
        DruidDataSource dataSource = new DruidDataSource();
        AssertUtils.notNull(properties.getDriverClassName(),"dataSource.driverClassName");
        AssertUtils.notNull(properties.getUrl(),"dataSource.url");
        AssertUtils.notNull(properties.getUsername(),"dataSource.userName");
        AssertUtils.notNull(properties.getPassword(), "dataSource.password");
        AssertUtils.notNull(this.maxActive, "dataSource.maxActive");
        AssertUtils.notNull(this.minIdle, "dataSource.minIdle");

        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        String password = properties.getPassword();
        try{
            password =  Base64Utils.getFromBase64(properties.getPassword());
            String dataSourceFilters =systemConfig.getProperty("spring.datasource.filters");
            if(StringUtils.isNotEmpty(dataSourceFilters)&&!dataSourceFilters.contains("$")) {
                dataSource.setFilters(dataSourceFilters);
                String connectionProperties =systemConfig.getProperty("spring.datasource.connectionProperties");
                if(StringUtils.isNotEmpty(connectionProperties)&&!connectionProperties.contains("$")) {
                    Properties properties = new Properties();
                    String[] pros = connectionProperties.split(";");
                    for (String item : pros) {
                        String[] property = item.split("=");
                        if ("druid.stat.slowSqlMillis".equals(property[0])) {
                            properties.put("druid.stat.slowSqlMillis", property[1]);
                            properties.put("druid.stat.logSlowSql", "true");
                            properties.put("druid.stat.mergeSql", "true");
                        }
                    }
                    dataSource.setConnectProperties(properties);
                }
            }
        }
        catch (Exception ex){
            LOGGER.error("dataSource config password base64 decode failure!");
        }
        finally {
            dataSource.setPassword(password);
        }
        dataSource.setMaxActive(this.maxActive);
        dataSource.setMaxWait(this.maxWaitMillis);
        dataSource.setInitialSize(this.initialSize);
        dataSource.setMinIdle(this.minIdle);
        dataSource.setValidationQuery(this.validationQuery);
        dataSource.setTestOnBorrow(this.testOnBorrow);
        dataSource.setTestOnReturn(this.testOnReturn);
        dataSource.setTestWhileIdle(this.testWhileIdle);
        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);

        //指定连接建立超过30分钟就需要被强制关闭
        dataSource.setRemoveAbandonedTimeoutMillis(1800);
        return dataSource;
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ResourceLoader resourceLoader) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        Resource[] resources = getResources(resourceLoader, mybatisProperties.getMapperLocation());
        String specialMapperLocation = mybatisProperties.getSpecialMapperLocation();
        if (!StringUtils.isBlank(specialMapperLocation)) {
            resources = ArrayUtils.addAll(resources, getResources(resourceLoader, specialMapperLocation));
        }
        sqlSessionFactoryBean.setMapperLocations(resources);
        // sqlSessionFactoryBean.setTypeAliasesPackage(mybatisProperties.getAliasesPackage());
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));

        SqlSessionFactory sqlSessionFactory;
        try {
            sqlSessionFactory = sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            // 打印日志, 否则spring容器会抑制bean创建的异常,只打印warn的不完整的信息,不利于开发调试
            LOGGER.error("SqlSessionFactory创建失败." + e.getMessage(), e);
            throw e;
        }
        return sqlSessionFactory;
    }

    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        Properties properties = new Properties();
        // properties.setProperty("hibernate.ejb.event.pre-insert","com.evada.inno.core.listener.HistoryEventListener");
        properties.setProperty("hibernate.show_sql", jpaProperties.getShowSql());
        properties.setProperty("hibernate.format_sql", jpaProperties.getFormatSql());
        properties.setProperty("hibernate.ejb.naming_strategy", jpaProperties.getHibernateNamingStrategy());
//        if(StringUtils.isNotEmpty(jpaProperties.getHibernateEjbInterceptor())){
//            properties.setProperty("hibernate.ejb.interceptor.session_scoped", jpaProperties.getHibernateEjbInterceptor());
//        }
        properties.setProperty("hibernate.jdbc.batch_size","50");
        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        entityManagerFactory.setPackagesToScan(new String[]{jpaProperties.getEntityPackage()});
        entityManagerFactory.setJpaProperties(properties);
        return entityManagerFactory;
    }

    /**
     * Jpa事务管理，一定要用JpaTransactionManager
     * 用PlatformTransactionManager会出错
     * @param entityManagerFactory
     * @return
     */
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager =  new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabasePlatform(jpaProperties.getDialect());
        return hibernateJpaVendorAdapter;
    }

    private Resource[] getResources(ResourceLoader resourceLoader, String packagePath) throws IOException {
        ResourcePatternResolver resourceResolver = ResourcePatternUtils
                .getResourcePatternResolver(resourceLoader);
        return resourceResolver.getResources(packagePath);
    }


    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}

