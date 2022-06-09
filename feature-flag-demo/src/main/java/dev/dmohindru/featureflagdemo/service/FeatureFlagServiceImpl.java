package dev.dmohindru.featureflagdemo.service;

import dev.dmohindru.featureflagdemo.dto.FeatureFlagDTO;
import lombok.RequiredArgsConstructor;
import no.finn.unleash.Unleash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeatureFlagServiceImpl implements FeatureFlagService{

    private final Unleash unleash;

    @Value("${feature-flag.dummyflag1}")
    private String dummyFeatureFlag1;

    @Value("${feature-flag.dummyflag2}")
    private String dummyFeatureFlag2;

    @Override
    public FeatureFlagDTO getFeatureFlagStatus() {
        return FeatureFlagDTO
                .builder()
                .dummyFeatureFlag1(unleash.isEnabled(dummyFeatureFlag1))
                .dummyFeatureFlag2(unleash.isEnabled(dummyFeatureFlag2))
                .build();
    }
}
