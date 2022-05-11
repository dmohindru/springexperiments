package dev.dmohindru.springeventsdemo.interceptors;

import dev.dmohindru.springeventsdemo.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Slf4j
@Component
public class CustomInterceptor extends EmptyInterceptor {
    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof Customer) {
            log.info("Saving new customer");
            log.info(((Customer) entity).toString());
        }
        return super.onSave(entity, id, state, propertyNames, types);
    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof Customer) {
            log.info("Deleting a customer");
            log.info(((Customer) entity).toString());
        }
        super.onDelete(entity, id, state, propertyNames, types);
    }


}
