package dev.dmohindru.springprocessorclient.constants;

public enum ResponseCode {
    SUCCESS(0), ERROR(1);
    private final Integer code;

    ResponseCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

    public static ResponseCode getResponseFromValue(Integer code) {
        for (ResponseCode respCode: ResponseCode.values()) {
            if (respCode.code.equals(code)) {
                return respCode;
            }
        }

        throw new IllegalArgumentException("Unknown response code: " + code);

    }
}
