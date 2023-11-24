package dev.dmohindru.StringProcessorServer.model;

import dev.dmohindru.StringProcessorServer.constants.ResponseCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    byte[] messageByte;
    ResponseCode responseCode;
}
