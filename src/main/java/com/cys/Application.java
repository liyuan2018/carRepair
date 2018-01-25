package com.cys;

import com.cys.config.JpaProperties;
import com.cys.config.MybatisProperties;
import com.mangofactory.swagger.plugin.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class})
@EnableConfigurationProperties({JpaProperties.class, MybatisProperties.class})
@ImportResource({"classpath:/spring/app-config.xml"})
@PropertySource(value="classpath:application.properties", ignoreResourceNotFound = true)
@EnableSwagger
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
