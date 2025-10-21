package com.mind.vault.enums;

/**
 * HTTP基础业务状态码
 */
public enum HttpCode {
    /**
     * 成功
     */
    SUCCESS("success"),

    /**
     * 失败
     */
    FAIL("fail"),

    /**
     * 未找到
     */
    NOT_FOUND("not_found"),

    /**
     * 未授权
     */
    UNAUTHORIZED("unauthorized"),

    /**
     * 禁止访问
     */
    FORBIDDEN("forbidden"),

    /**
     * 验证错误
     */
    VALIDATE_ERROR("validate_error");

    private final String code;

    HttpCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}