package dev.dmohindru.springprocessorclient.constants;

public enum StringOperation {
    LOWER_CASE(0),
    UPPER_CASE(1),
    INVERT_CASE(2),
    DISCONNECT(3);
    private final Integer operation;

    StringOperation(Integer operation) {
        this.operation = operation;
    }

    public static StringOperation getOperationFromValue(Integer value) {

        for (StringOperation op: StringOperation.values()) {
            if (op.operation.equals(value)) {
                return op;
            }
        }
        throw new IllegalArgumentException("String operation now support for the value: " + value);
    }

    public Integer getValue() {
        return this.operation;
    }

}
