package com.mzw.util;

public interface BaseConstant {
    public static final String SUCCESS = "success";
    public static final String USER_EXISTS = "userExists";
    public static final String MOBILE_EXISTS = "mobileExists";
    public static final String EMAIL_EXISTS = "emailExists";
    public static final String ROLENAME_EXISTS = "roleNameExists";
    public static final String DELETE_FAIL = "deleteFail";
    public static final String ROWS = "rows";
    public static final String TOTAL = "total";

    /**
     * TUserEntityStatus
     * 0 正常 1 冻结
     */
    public static final String T_USER_STATUS_NORMAL = "0";
    public static final String T_USER_STATUS_FREEZE = "1";
    /**
     * TUserEntityRoleId
     */
    public static final Integer T_USER_ROLE_ID = -1;
    public static final Integer T_USER_SELL_ROLE_ID = 1001;
    public static final Integer T_USER_BUY_ROLE_ID = 1002;
    /**
     * TUserEntityBindFlag
     * 0 绑定 1 解绑
     */
    public static final Integer T_USER_BIND_FLAG = 0;
    public static final Integer T_USER_UNBIND_FLAG = 1;
}
