package dev.dmohindru.springeventsdemo.config;

import org.hibernate.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class HibernateConfig implements HibernatePropertiesCustomizer {

    @Autowired
    Interceptor customInterceptor;

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.ejb.interceptor", customInterceptor);

    }
}
