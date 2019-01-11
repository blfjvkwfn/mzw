package com.mzw.util;

/**
 * Created by Administrator on 2018/11/8 0008.
 */
public class PageUtil {
    private static ThreadLocal<Integer> PAGE_SIZE = new ThreadLocal<>();
    private static ThreadLocal<Integer> CURRENT_PAGE = new ThreadLocal<>();

    public static void setPageSize(Integer pageSize){
        PAGE_SIZE.set(pageSize);
    }
    public static Integer getPageSize(){
        return PAGE_SIZE.get();
    }
    public static void removePageSize(){
        PAGE_SIZE.remove();
    }
    public static void setCurrentPage(Integer currentPage){
        CURRENT_PAGE.set(currentPage);
    }
    public static Integer getCurrentPage(){
        return CURRENT_PAGE.get();
    }
    public static void removeCurrentPage(){
        CURRENT_PAGE.remove();
    }
}
