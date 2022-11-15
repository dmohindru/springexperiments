package dev.dmohindru.springbootpostgres.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class CityDto {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "population")
    private Double population;

    @JsonProperty(value = "elevation")
    private Double elevation;

    @JsonProperty(value = "state")
    private String state;

    @JsonProperty(value = "position")
    private Position position;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder
    public static class Position {

        @JsonProperty(value = "lat")
        private Double lat;
        @JsonProperty(value = "lng")
        private Double lng;

    }
}
