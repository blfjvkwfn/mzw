package com.mzw.util;

public enum ErrorCode {
    SAVE_SYS_RIGHT_ERROR_CODE("E00001"),
    DELETE_SYS_RIGHT_ERROR_CODE("E00002"),
    SYS_RIGHT_EMPTY_ERROR_CODE("E00003"),
    MOBILE_IS_EMPTY("E00020"),
    MOBILE_ERROR("E00021"),
    PASSWORD_IS_EMPTY("E00022"),
    PASSWORD_RULE_ERROR("E00023"),
    MOBILE_EXISTED("E00024"),
    USER_ROLE_NOT_EXIST("E00025"),
    SHOP_NAME_EMPTY("E00026");

    private String code;
    public String getCode() {
        return code;
    }
    private ErrorCode(String code){
        this.code = code;
    }
}
