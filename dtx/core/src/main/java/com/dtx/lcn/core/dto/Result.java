package com.dtx.lcn.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private int code;

    private String message;

    private T data;

    public static <T> Result<T> wrapOK(T data) {
        return new Result<>(0, "success", data);
    }

}
