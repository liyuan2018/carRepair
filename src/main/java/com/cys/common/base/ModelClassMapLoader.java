package com.cys.common.base;

/**
 * Created by liyuan on 2018/1/28.
 */

import com.cys.config.JpaProperties;
import com.cys.model.BaseModel;
import com.cys.util.ApplicationContextUtils;
import com.cys.util.Inflector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ModelClassMapLoader {

    @Autowired
    private JpaProperties jpaProperties;
    private Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
    private Set<BeanDefinitionHolder> models;
    private Inflector inflector = Inflector.getInstance();
    @Autowired
    private ApplicationContextUtils applicationContextUtils;

    @PostConstruct
    public void init() throws ClassNotFoundException {
        ApplicationContext context = applicationContextUtils.getApplicationContext();
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) ((ConfigurableApplicationContext) context).getBeanFactory();
        ClassLoader classLoader = context.getClassLoader();
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanFactory, false) {
            @Override
            public int scan(String... basePackages) {
                int beanCountAtScanStart = this.getRegistry().getBeanDefinitionCount();
                models = doScan(basePackages);
                return this.getRegistry().getBeanDefinitionCount() - beanCountAtScanStart;
            }
        };
        scanner.addIncludeFilter(new AssignableTypeFilter(BaseModel.class));
        String[] packages = {jpaProperties.getEntityPackage()};
        scanner.scan(packages);

        for (BeanDefinitionHolder holder : models) {
            BeanDefinition mbd = holder.getBeanDefinition();
            String className = mbd.getBeanClassName();
            if (className == null) {
                continue;
            }
            Class<?> resolvedClass = ClassUtils.forName(className, classLoader);
            classMap.put(resolvedClass.getSimpleName(), resolvedClass);
        }
    }

    private String pathForClass(String modelName) {
        String underscored = inflector.camelCase(modelName, true);
        return inflector.singularize(underscored);
    }

    private Class<?> getClass(String modelName) {
        modelName = pathForClass(modelName);
        return classMap.get(modelName);
    }

    public Map<String, Class<?>> getClassMap(){
        return classMap;
    }

    public boolean isExists(String modelName) {
        return getClass(modelName) != null;
    }

    public Class<?> getModelClass(String modelName) {
        return getClass(modelName);
    }
}

