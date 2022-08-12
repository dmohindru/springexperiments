package dev.dmohindru.springeventsdemo.listeners;

import dev.dmohindru.springeventsdemo.events.CustomerAddEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerNoOpsListener {

    @EventListener
    @Async
    public void listen(CustomerAddEvent customerAddEvent) throws InterruptedException {
        log.info("Event received by no ops listener");
        log.info("Message: I will not do any thing");
        Thread.sleep(5000);
    }
}
