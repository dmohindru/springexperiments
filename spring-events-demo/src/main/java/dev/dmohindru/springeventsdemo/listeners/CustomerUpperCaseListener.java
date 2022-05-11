package dev.dmohindru.springeventsdemo.listeners;

import dev.dmohindru.springeventsdemo.events.CustomerAddEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomerUpperCaseListener {

    @EventListener
    @Async
    public void listen(CustomerAddEvent customerAddEvent) throws InterruptedException {
        log.info("Event received by UPPER CASE listener");
        log.info("Message: " + customerAddEvent.getMessage().toUpperCase());
        Thread.sleep(5000);
    }
}
