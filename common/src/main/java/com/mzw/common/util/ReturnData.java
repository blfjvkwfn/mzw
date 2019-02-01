package com.mzw.common.util;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.Properties;

public class ReturnData {

    private Short status;
    private String errorCode = "";
    private Object data;
    private String errorMsg = "";
    private static Properties properties = new Properties();
    static{
        try {
            properties.load(ReturnData.class.
                    getClassLoader().getResourceAsStream("error.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String readValue(String key){
        return (String)properties.get(key);
    }
    public static class DataStatus
    {
        public static final Short SUCCESS = Short.valueOf((short)0);
        public static final Short FAIL = Short.valueOf((short)1);
        public DataStatus()
        {
        }
    }

    private ReturnData(Object data)
    {
        status = Short.valueOf((short)0);
        this.data = data;
    }

    private ReturnData(String errorCode, Object data, String errorMsg)
    {
        status = Short.valueOf((short)1);
        this.errorCode = errorCode;
        this.data = data;
        if(!EmptyUtil.isFieldEmpty(readValue(errorCode))){
            this.errorMsg = readValue(errorCode);
        }

    }

    public ReturnData(Short status, Object data)
    {
        this.status = status;
        this.data = data;
    }

    public ReturnData(Short status, String code, Object data, String msg)
    {
        this.status = status;
        this.errorCode = errorCode;
        this.data = data;
        if(!EmptyUtil.isFieldEmpty(readValue(errorCode))){
            this.errorMsg = readValue(errorCode);
        }
    }

    public static ReturnData getSuccessReturnData(String data)
    {
        return new ReturnData(JSON.parseObject(data));
    }

    public static ReturnData getSuccessReturnDataForObject(Object data)
    {
        return new ReturnData(data);
    }

    public static ReturnData getFailReturnData(String code, Object data, String msg)
    {
        return new ReturnData(code, data, msg);
    }

    public Short getStatus()
    {
        return status;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public Object getData()
    {
        return data;
    }

    public String getErrorMsg()
    {
        return readValue(errorCode) == null ? "" : readValue(errorCode);
    }

    public void setStatus(Short status)
    {
        this.status = status;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
        this.setErrorMsg(getErrorMsg());
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public ReturnData()
    {
    }
}
