package dev.dmohindru.StringProcessorServer.constants;

public enum ResponseCode {
    SUCCESS(0), ERROR(1);
    private final Integer code;

    ResponseCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}
