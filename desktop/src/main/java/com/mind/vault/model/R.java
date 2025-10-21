package com.mind.vault.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author caixr
 * @date 2025/10/21 15:17
 */
@Data
public class R<T> implements Serializable {
    private String code;
    private String message;
    private T data;
}
