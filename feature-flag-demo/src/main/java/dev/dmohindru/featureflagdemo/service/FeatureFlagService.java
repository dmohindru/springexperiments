package dev.dmohindru.featureflagdemo.service;

import dev.dmohindru.featureflagdemo.dto.FeatureFlagDTO;

public interface FeatureFlagService {
    FeatureFlagDTO getFeatureFlagStatus();
}
