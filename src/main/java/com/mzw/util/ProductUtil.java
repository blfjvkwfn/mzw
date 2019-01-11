package com.mzw.util;


public interface ProductUtil  {
    public static final String PRODUCT_TEMPLATE_NAME = "ProductTemplate";
    public static final String CATEGORY_NAME = "Category";
    public static final String ALREADY = "already";
    public static final String NEVER = "never";

    /**
     * 是否在首页
     */
    enum IsDefault{
        YES(0, "是"),NO(1, "否");
        private Integer status;
        private String msg;
        public Integer getStatus() {
            return status;
        }
        public String getMsg(){
            return msg;
        }
        private IsDefault(Integer status, String msg) {
            this.status = status;
            this.msg = msg;
        }
    }

    /**
     * 商品属性
     */
    enum ProductProperty{
        TEXT(1, "文本框"),SELECT_TXT(2, "下拉列表框"),CHECKBOX(3, "多选框"),RADIOBUTTON(4, "单选框");
        private Integer status;
        private String msg;
        public Integer getStatus() {
            return status;
        }
        public String getMsg(){
            return msg;
        }
        private ProductProperty(Integer status, String msg) {
            this.status = status;
            this.msg = msg;
        }
    }
    /**
     * 商品审核状态枚举
     */
    enum CheckStatus{
        NOT_CHECK(0, "未审核"),CHECK_ADOPT(1, "审核通过"),CHECK_REFUSE(2, "拒绝");
        private Integer status;
        private String msg;
        public Integer getStatus() {
            return status;
        }
        public String getMsg(){
            return msg;
        }
        private CheckStatus(Integer status, String msg) {
            this.status = status;
            this.msg = msg;
        }
    }
    /**
     * 商品类型
     */
    enum ProductType{
        LISTING(1, "挂牌商品"),PRESALE(2, "预售商品"),COMPETE_BUY(3, "竞买商品");
        private Integer status;
        private String msg;
        public Integer getStatus() {
            return status;
        }
        public String getMsg(){
            return msg;
        }
        private ProductType(Integer status, String msg) {
            this.status = status;
            this.msg = msg;
        }
    }
    /**
     * 商品上架状态
     */
    enum DrafStatus {
        PUBLISH(0, "草稿"),ONSHELF(1, "上架"),LOWSHELF(2, "下架"),REMOVE(3, "删除");
        private Integer status;
        private String msg;
        public Integer getStatus() {
            return status;
        }
        public String getMsg(){
            return msg;
        }
        private DrafStatus(Integer status, String msg) {
            this.status = status;
            this.msg = msg;
        }
    }
    /**
     *  保证金类型
     */
    enum MarginType{
        PERCENTAGE(1, "百分比"),FIXED_VALUE(2, "固定值");
        private Integer status;
        private String msg;
        public Integer getStatus() {
            return status;
        }
        public String getMsg(){
            return msg;
        }
        private MarginType(Integer status, String msg) {
            this.status = status;
            this.msg = msg;
        }
    }
    /**
     *  手续费类型
     */
    enum TradefeeType{
        PERCENTAGE(1, "百分比"),FIXED_VALUE(2, "固定值");
        private Integer status;
        private String msg;
        public Integer getStatus() {
            return status;
        }
        public String getMsg(){
            return msg;
        }
        private TradefeeType(Integer status, String msg) {
            this.status = status;
            this.msg = msg;
        }
    }

    /**
     *  手续费类型  add by mengzhaowei 20181213
     */
    enum ButMarginType{
        PERCENTAGE(1, "百分比"),FIXED_VALUE(2, "固定值");
        private Integer status;
        private String msg;
        public Integer getStatus() {
            return status;
        }
        public String getMsg(){
            return msg;
        }
        private ButMarginType(Integer status, String msg) {
            this.status = status;
            this.msg = msg;
        }
    }

    /**
     * 商品分类状态
     */
    enum CategoryStatus{
        NORMAL(0, "正常"),DELETE(1, "删除");
        private Integer status;
        private String msg;
        public Integer getStatus() {
            return status;
        }
        public String getMsg(){
            return msg;
        }
        private CategoryStatus(Integer status, String msg) {
            this.status = status;
            this.msg = msg;
        }
    }
    /**
     * 商品模板状态
     */
    enum ProductTemplateStatus{
        NORMAL(0, "正常"),INVALID(1, "失效");
        private Integer status;
        private String msg;
        public Integer getStatus() {
            return status;
        }
        public String getMsg(){
            return msg;
        }
        private ProductTemplateStatus(Integer status, String msg) {
            this.status = status;
            this.msg = msg;
        }
    }
    /**
     * 商品店铺状态
     */
    enum ShopStatus{
        YES(0, "正常"),NO(1, "失效");
        private Integer status;
        private String msg;
        public Integer getStatus() {
            return status;
        }
        public String getMsg(){
            return msg;
        }
        private ShopStatus(Integer status, String msg) {
            this.status = status;
            this.msg = msg;
        }
    }
}
