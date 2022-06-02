package dev.dmohindru.retrydemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableRetry
@RequiredArgsConstructor
public class ValueService {
    int counter;
    //private final RetryTemplate retryTemplate;

    @Retryable(
    maxAttemptsExpression = "${retry.maxAttempts}", backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
    public String getValue() {
        log.info("counter: {}", counter);
        counter++;
        throw new RuntimeException("Some random exception");
    }

    @Recover
    public String getFallbackValue(RuntimeException ex) {
        log.info(ex.getMessage());
        //throw ex;
        return "Fallback-Value";
    }

    @Scheduled(cron = "0/10 * * * * *")
    public void printMessage() {
        log.info("timer tick");
    }

//    public String callTemplateRetry() {
//        retryTemplate.execute(arg0 -> {
//            templateRetryValue();
//            return "Finally from call Template Retry";
//        }, arg -> {
//            return "Finally from call Template Retry";
//        });
//
//        // This value will be returned as a RECOVERY value
//        return "Finally Done";
//
//    }

//    public String templateRetryValue() {

//        if (counter < 2)
//            throw new RuntimeException("Throw from templateRetryValue method");
//        counter++;

//        return "Retry-template-value";
//    }
}
