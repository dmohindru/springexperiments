package dev.dmohindru.retrydemo.controller;

import dev.dmohindru.retrydemo.service.ValueService;
import dev.dmohindru.retrydemo.service.YetAnotherValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class ApiController {

    private final ValueService valueService;
    private final YetAnotherValueService yetAnotherValueService;

    @GetMapping("/value")
    String getValue() {
        return valueService.getValue();
    }

//    @GetMapping("/value-retry")
//    String getRetryValue() {
//        return valueService.callTemplateRetry();
//    }

    @GetMapping("/yet-value")
    String getYetAnotherValue() {
        return yetAnotherValueService.getValue();
    }
}
