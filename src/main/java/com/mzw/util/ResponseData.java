package com.mzw.util;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResponseData implements Serializable {
    public static final Integer SUCCESS = 0;
    public static final Integer FAIL = 1;

    private Integer status;
    private String errorCode;
    private Object data;
    private String errorMsg;

    public ResponseData() {
    }
}
