package dev.dmohindru.asyncdemo.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComputationResponse {

    @JsonProperty(value="computationName")
    String computationName;

    @JsonProperty(value = "resultOne")
    Integer resultOne;

    @JsonProperty(value = "resultTwo")
    Integer resultTwo;

    @JsonProperty(value = "resultThree")
    Integer resultThree;

    @JsonProperty(value = "resultFour")
    Integer resultFour;

    @JsonProperty(value = "totalComputationTime")
    Long totalComputationTime;

}
