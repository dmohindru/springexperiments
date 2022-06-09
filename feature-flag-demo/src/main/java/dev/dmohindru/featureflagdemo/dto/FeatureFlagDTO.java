package dev.dmohindru.featureflagdemo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class FeatureFlagDTO {
    Boolean dummyFeatureFlag1;
    Boolean dummyFeatureFlag2;

}
