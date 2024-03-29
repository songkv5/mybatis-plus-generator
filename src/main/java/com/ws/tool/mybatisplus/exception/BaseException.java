package com.ws.tool.mybatisplus.exception;

/**
 * @author willis<songkai01>
 * @chapter
 * @section
 * @since 2019年11月09日 0:01
 */
public abstract class BaseException extends RuntimeException {
    private int code;
    private String msg;

    public BaseException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}