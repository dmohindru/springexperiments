package dev.dmohindru.featureflagdemo.controller;

import dev.dmohindru.featureflagdemo.dto.FeatureFlagDTO;
import dev.dmohindru.featureflagdemo.service.FeatureFlagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class ApiController {
    private final FeatureFlagService featureFlagService;

    @GetMapping("feature")
    ResponseEntity<FeatureFlagDTO> getFeatureFlags() {
        return new ResponseEntity<>(featureFlagService.getFeatureFlagStatus(), HttpStatus.OK);
    }

}
