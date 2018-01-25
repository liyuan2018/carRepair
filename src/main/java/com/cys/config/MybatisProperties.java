package com.cys.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 数据源mybatis
 * Created by liyuan on 2018/1/24.
 */
@ConfigurationProperties(prefix = "spring.mybatis")
public class MybatisProperties {

    private String dialect;
    private String mapperLocation;
    private String specialMapperLocation;
    private String repositoriesPackage;
    private String aliasesPackage;

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getRepositoriesPackage() {
        return repositoriesPackage;
    }

    public void setRepositoriesPackage(String repositoriesPackage) {
        this.repositoriesPackage = repositoriesPackage;
    }

    public String getAliasesPackage() {
        return aliasesPackage;
    }

    public void setAliasesPackage(String aliasesPackage) {
        this.aliasesPackage = aliasesPackage;
    }

    public String getMapperLocation() {
        return mapperLocation;
    }

    public void setMapperLocation(String mapperLocation) {
        this.mapperLocation = mapperLocation;
    }

    public String getSpecialMapperLocation() {
        return specialMapperLocation;
    }

    public void setSpecialMapperLocation(String specialMapperLocation) {
        this.specialMapperLocation = specialMapperLocation;
    }
}
