package dev.dmohindru.springeventsdemo.events;

import dev.dmohindru.springeventsdemo.domain.Customer;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

public class CustomerAddEvent extends ApplicationEvent {
    private final String message;

    public CustomerAddEvent(Customer source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }


}
