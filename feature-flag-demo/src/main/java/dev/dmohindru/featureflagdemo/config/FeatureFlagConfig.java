package dev.dmohindru.featureflagdemo.config;

import lombok.RequiredArgsConstructor;
import no.finn.unleash.DefaultUnleash;
import no.finn.unleash.Unleash;
import no.finn.unleash.util.UnleashConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeatureFlagConfig {
    @Value("${feature-flag.appname}")
    private String featureFlagAppName;

    @Value("${feature-flag.url}")
    private String featureFlagApiUrl;

    @Value("${feature-flag.instance}")
    private String featureFlagInstanceId;

    @Bean
    public Unleash unleash() {
        UnleashConfig config = UnleashConfig
                .builder()
                .appName(featureFlagAppName)
                .instanceId(featureFlagInstanceId)
                .unleashAPI(featureFlagApiUrl)
                .build();

        return new DefaultUnleash(config);
    }
}
