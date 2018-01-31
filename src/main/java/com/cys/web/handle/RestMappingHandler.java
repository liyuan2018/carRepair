package com.cys.web.handle;

import com.cys.common.annotation.Rest;
import com.cys.util.Inflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * 重写RequestMappingHandlerMapping
 * 实现@Rest注解的处理 将@Rest(User.class)转成RequestMapping为/users
 * Created by liyuan on 2018/1/31.
 */
public class RestMappingHandler extends RequestMappingHandlerMapping {
    private static final Logger log = LoggerFactory.getLogger(RestMappingHandler.class);

    @Override
    public RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = null;
        RequestMapping methodAnnotation = AnnotationUtils.findAnnotation(method, RequestMapping.class);
        if (methodAnnotation != null) {
            RequestCondition<?> methodCondition = getCustomMethodCondition(method);
            info = createRequestMappingInfo(methodAnnotation, methodCondition);
            RequestMapping requestMappingAnnotation = AnnotationUtils.findAnnotation(handlerType, RequestMapping.class);
            Rest restAnnotation = AnnotationUtils.findAnnotation(handlerType, Rest.class);

            //Rest优先级高于RequestMapping，优先处理Rest
            if (restAnnotation != null) {
                RequestCondition<?> typeCondition = getCustomTypeCondition(handlerType);
                if (restAnnotation.value().equals(Rest.class)) {
                    try {
                        info = createRequestMappingInfo(requestMappingAnnotation, typeCondition).combine(info);
                    } catch (Exception ex) {
                        log.error("controller annotation exception", requestMappingAnnotation.getClass().getSimpleName() + " missing @Rest(***.class)");
                        throw new RuntimeException(ex);
                    }
                } else
                    //特殊处理RequestMappingInfo
                    info = createRestRequestMappingInfo(restAnnotation, typeCondition).combine(info);
            } else if (requestMappingAnnotation != null) {
                RequestCondition<?> typeCondition = getCustomTypeCondition(handlerType);
                info = createRequestMappingInfo(requestMappingAnnotation, typeCondition).combine(info);
            }
        }
        return info;
    }

    private RequestMappingInfo createRestRequestMappingInfo(Rest annotation, RequestCondition<?> customCondition) {
        String[] patterns;
        Class<?> handlerType = annotation.value();
        String pluralName;
        pluralName = pathForName(handlerType.getSimpleName());
        String[] plurals = {pluralName};
        patterns = resolveEmbeddedValuesInPatterns(plurals);

        return new RequestMappingInfo(
                new PatternsRequestCondition(patterns, getUrlPathHelper(), getPathMatcher(),
                        this.useSuffixPatternMatch(), this.useTrailingSlashMatch(), this.getFileExtensions()),
                new RequestMethodsRequestCondition(annotation.method()),
                new ParamsRequestCondition(annotation.params()),
                new HeadersRequestCondition(annotation.headers()),
                new ConsumesRequestCondition(annotation.consumes(), annotation.headers()),
                new ProducesRequestCondition(annotation.produces(), annotation.headers(), getContentNegotiationManager()),
                customCondition);
    }

    /**
     * 转驼峰方法
     *
     * @param name
     * @return
     */
    private String pathForName(String name) {
        Inflector inflector = this.getInflector();
        String underscored = inflector.camelCase(name, false);
        return inflector.pluralize(underscored);
    }

    private Inflector getInflector() {
        return inflector;
    }

    private void setInflector(Inflector inflector) {
        this.inflector = inflector;
    }

    private Inflector inflector = new Inflector();

    private Class<?> getGenericType(Class<?> clz) {
        Class<?> result;
        try {
            result = (Class<?>) ((ParameterizedType) clz.getGenericSuperclass()).getActualTypeArguments()[0];
        } catch (ClassCastException ex) {
            result = null;
        }
        return result;
    }
}
