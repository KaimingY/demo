package com.example.demo.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一API响应结果封装
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 成功结果
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 成功结果（带数据）
     */
    public static <T> Result<T> success(T data) {
        return success(data, "操作成功");
    }

    /**
     * 成功结果（带数据和消息）
     */
    public static <T> Result<T> success(T data, String message) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    /**
     * 失败结果
     */
    public static <T> Result<T> error() {
        return error("操作失败");
    }

    /**
     * 失败结果（带消息）
     */
    public static <T> Result<T> error(String message) {
        return error(message, 500);
    }

    /**
     * 失败结果（带消息和状态码）
     */
    public static <T> Result<T> error(String message, Integer code) {
        Result<T> result = new Result<>();
        result.setMessage(message);
        result.setCode(code);
        return result;
    }
}