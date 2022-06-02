package dev.dmohindru.retrydemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class YetAnotherValueService {
    int counter;

    @Retryable(value = RuntimeException.class,
            maxAttemptsExpression = "${retry.maxAttempts}", backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
    public String getValue() {
        log.info("counter: {}", counter);
        counter++;
        throw new RuntimeException("Some random exception from YetAnotherValue Service");
    }

    @Recover
    public String getFallbackValue(RuntimeException ex) {
        return "Yet-Another-Fallback-Value";
    }
}
