package com.cys.config;

import com.cys.repository.BaseRepositoryFactoryBean;
import com.cys.util.ApplicationContextUtils;
import com.cys.web.converter.RESTDataHttpMessageConverter;
import com.cys.web.handle.RestMappingHandler;
import com.cys.web.resolver.QueryHandlerMethodArgumentResolver;
import com.cys.web.serializer.NullKeySerializer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;

/**
 * MVC bean注入配置
 * Created by liyuan on 2018/1/31.
 */
@Configuration
@ComponentScan({"com.cys"})
@EnableJpaRepositories(basePackages = {"com.cys.**.repository"},
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EnableConfigurationProperties({JpaProperties.class, MybatisProperties.class})
public class WebMvcConfig extends WebMvcConfigurationSupport {

    private static final String RESOURCES_LOCATION = "/";
    private static final String RESOURCES_HANDLER = RESOURCES_LOCATION + "**";

    private static final String API_RESOURCES_LOCATION = "/api/";

    private static final String API_RESOURCES_HANDLER = API_RESOURCES_LOCATION + "**";

    /**
     * 静态资源处理
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RESOURCES_HANDLER).addResourceLocations(RESOURCES_LOCATION);
        registry.addResourceHandler(API_RESOURCES_HANDLER).addResourceLocations(API_RESOURCES_LOCATION);
    }

    /**
     * 重写requestMappingHandlerMapping支持模型转换
     * eg: Rest(user.class)-->requestMapping: /users/
     *
     * @return
     */
    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RestMappingHandler handlerMapping = new RestMappingHandler();
        handlerMapping.setOrder(0);
        handlerMapping.setInterceptors(getInterceptors());
        handlerMapping.setContentNegotiationManager(mvcContentNegotiationManager());

        PathMatchConfigurer configurer = getPathMatchConfigurer();
        if (configurer.isUseSuffixPatternMatch() != null) {
            handlerMapping.setUseSuffixPatternMatch(configurer.isUseSuffixPatternMatch());
        }
        if (configurer.isUseRegisteredSuffixPatternMatch() != null) {
            handlerMapping.setUseRegisteredSuffixPatternMatch(configurer.isUseRegisteredSuffixPatternMatch());
        }
        if (configurer.isUseTrailingSlashMatch() != null) {
            handlerMapping.setUseTrailingSlashMatch(configurer.isUseTrailingSlashMatch());
        }
        if (configurer.getPathMatcher() != null) {
            handlerMapping.setPathMatcher(configurer.getPathMatcher());
        }
        if (configurer.getUrlPathHelper() != null) {
            handlerMapping.setUrlPathHelper(configurer.getUrlPathHelper());
        }
        handlerMapping.setDetectHandlerMethodsInAncestorContexts(true);
        return handlerMapping;
    }

//    /**
//     * 处理Request的PageRequest对象转换为controller的Pageable
//     * @param argumentResolvers
//     */
//    @Override
//    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//
//        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
//    }

    /**
     * 处理Request的PageRequest对象转换为controller的Pageable
     */
    @Override
    protected void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new QueryHandlerMethodArgumentResolver());
    }

    /**
     * 响应消息体Json对象转换处理
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(restDataHttpMessageConverter());
        super.addDefaultHttpMessageConverters(converters);
    }

    /**
     * 私有方法：restDataHttpMessageConverter，供configureMessageConverters使用
     *
     * @return
     */
    private MappingJackson2HttpMessageConverter restDataHttpMessageConverter() {
        RESTDataHttpMessageConverter jsonConverter = new RESTDataHttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.getSerializerProvider().setNullKeySerializer(new NullKeySerializer());
//        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true) ;
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }
//	/**
//     * 上传文件大小配置
//     * @return
//     */
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setMaxFileSize("512KB");
//        factory.setMaxRequestSize("512KB");
//        return factory.createMultipartConfig();
//    }

    /**
     * 上下文注入
     */
    @Bean
    public ApplicationContextUtils applicationContextUtils() {
        ApplicationContextUtils resolver = new ApplicationContextUtils();
        return resolver;
    }

//    /**
//     * 配置拦截器
//     * @author lance
//     * @param registry
//     */
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AccessInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/api-docs/**")
//                .excludePathPatterns("/sysUsers/login")
//                .excludePathPatterns("/sysUsers/*/sysAcls")
//                .excludePathPatterns("/*/*/diagram");
//    }


}
