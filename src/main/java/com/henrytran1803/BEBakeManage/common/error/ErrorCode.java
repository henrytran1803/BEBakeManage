package com.henrytran1803.BEBakeManage.common.error;

public enum ErrorCode {
    USER_NOT_FOUND("1001", "User not found"),
    INVALID_CREDENTIALS("1002", "Invalid credentials"),
    PRODUCT_NOT_FOUND("2001", "Product not found"),
    ORDER_NOT_FOUND("3001", "Order not found"),
    ;

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
