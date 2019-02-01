package com.mzw.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author mengzhaowei@boce.cn
 * @date 2018/12/6 10:48
 */
public class MathUtils {
    // 默认运算精度
    private static final int DEF_DIV_SCALE = 10;

    // 结果精度
    private static final int RESULT_DIV_SCALE = 10;

    private static final String FORMAT_PATERN = "######.########";

    private MathUtils() {
    }

    private static BigDecimal result(BigDecimal b) {
        return b.setScale(RESULT_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 加法运算。
     *
     * @param v1 double
     * @param v2 double
     * @return double
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Double.toString(v2)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        return result(b1.add(b2)).doubleValue();
    }

    /**
     * 加法运算。
     *
     * @param v1 float
     * @param v2 float
     * @return double
     */
    public static double add(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Float.toString(v2)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        return result(b1.add(b2)).doubleValue();
    }

    /**
     * 加法运算。
     *
     * @param v1 float
     * @param v2 double
     * @return double
     */
    public static double add(float v1, double v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Double.toString(v2)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        return result(b1.add(b2)).doubleValue();
    }

    /**
     * 加法运算。
     *
     * @param v1 double
     * @param v2 float
     * @return double
     */
    public static double add(double v1, float v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Float.toString(v2)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        return result(b1.add(b2)).doubleValue();
    }

    /**
     * 减法运算。
     *
     * @param v1 double
     * @param v2 double
     * @return double
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Double.toString(v2)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        return result(b1.subtract(b2)).doubleValue();
    }

    /**
     * 减法运算。
     *
     * @param v1 float
     * @param v2 float
     * @return double
     */
    public static double sub(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Float.toString(v2)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        return result(b1.subtract(b2)).doubleValue();
    }

    /**
     * 减法运算。
     *
     * @param v1 float
     * @param v2 double
     * @return double
     */
    public static double sub(float v1, double v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Double.toString(v2)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        return result(b1.subtract(b2)).doubleValue();
    }

    /**
     * 减法运算。
     *
     * @param v1 double
     * @param v2 float
     * @return double
     */
    public static double sub(double v1, float v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Float.toString(v2)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        return result(b1.subtract(b2)).doubleValue();
    }

    /**
     * 乘法运算。
     *
     * @param v1 double
     * @param v2 double
     * @return double
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Double.toString(v2)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);

        return result(result(b1.multiply(b2))).doubleValue();
    }

    /**
     * 乘法运算。
     *
     * @param v1 float
     * @param v2 float
     * @return double
     */
    public static double mul(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Float.toString(v2)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);

        return result(result(b1.multiply(b2))).doubleValue();
    }

    /**
     * 乘法运算。
     *
     * @param v1 float
     * @param v2 double
     * @return double
     */
    public static double mul(float v1, double v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Double.toString(v2)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);

        return result(result(b1.multiply(b2))).doubleValue();
    }

    /**
     * 乘法运算。
     *
     * @param v1 double
     * @param v2 float
     * @return double
     */
    public static double mul(double v1, float v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Float.toString(v2)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);

        return result(result(b1.multiply(b2))).doubleValue();
    }

    /**
     * 除法运算。
     *
     * @param v1
     * @param v2
     * @return double
     */
    public static double div(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Double.toString(v2)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        return result(b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP))
                .doubleValue();
    }

    /**
     * 除法运算。
     *
     * @param v1
     * @param v2
     * @return double
     */
    public static double div(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Float.toString(v2)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        return result(b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP))
                .doubleValue();
    }

    /**
     * 除法运算。
     *
     * @param v1
     * @param v2
     * @return double
     */
    public static double div(float v1, double v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Double.toString(v2)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        return result(b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP))
                .doubleValue();
    }

    /**
     * 除法运算。
     *
     * @param v1
     * @param v2
     * @return double
     */
    public static double div(double v1, float v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Float.toString(v2)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        return result(b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP))
                .doubleValue();
    }

    /**
     * 除法运算。
     *
     * @param v1           double
     * @param v2           double
     * @param scale        int
     * @param roundingMode int
     * @return double
     */
    public static double div(double v1, double v2, int scale, int roundingMode) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Double.toString(v2)).setScale(
                DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        return result(b1.divide(b2, scale, roundingMode)).doubleValue();
    }

    /**
     * 比较大小
     *
     * @param v1
     * @param v2
     * @return 1：大于；0：等于；-1：小于
     */
    public static int compareTo(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1)).setScale(
                RESULT_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Double.toString(v2)).setScale(
                RESULT_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        return b1.compareTo(b2);
    }

    /**
     * 比较大小
     *
     * @param v1
     * @param v2
     * @return 1：大于；0：等于；-1：小于
     */
    public static int compareTo(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1)).setScale(
                RESULT_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Float.toString(v2)).setScale(
                RESULT_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        return b1.compareTo(b2);
    }

    /**
     * 比较大小
     *
     * @param v1
     * @param v2
     * @return 1：大于；0：等于；-1：小于
     */
    public static int compareTo(double v1, float v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1)).setScale(
                RESULT_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Float.toString(v2)).setScale(
                RESULT_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        return b1.compareTo(b2);
    }

    /**
     * 比较大小
     *
     * @param v1
     * @param v2
     * @return 1：大于；0：等于；-1：小于
     */
    public static int compareTo(float v1, double v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1)).setScale(
                RESULT_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Double.toString(v2)).setScale(
                RESULT_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        return b1.compareTo(b2);
    }

    /**
     * 四舍五入格式化浮点数
     *
     * @param v
     * @param scale 小数位数
     * @return double
     */
    public static double format(double v, int scale) {
        return format(v, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 四舍五入格式化浮点数
     *
     * @param v
     * @param scale 小数位数
     * @return double
     */
    public static double format(double v, int scale, int roundingMode) {
        BigDecimal b1 = new BigDecimal(Double.toString(v)).setScale(scale, roundingMode);
        return b1.doubleValue();
    }

    /**
     * 四舍五入格式化浮点数
     *
     * @param v
     * @param scale 小数位数
     * @return double
     */
    public static double format(float v, int scale) {
        BigDecimal b1 = new BigDecimal(Float.toString(v)).setScale(scale,
                BigDecimal.ROUND_HALF_UP);
        return b1.doubleValue();
    }

    public static String formatStr(double v, int scale) {
        double formatValue = format(v, scale);
        DecimalFormat formatter = new DecimalFormat(FORMAT_PATERN);
        return formatter.format(formatValue);
    }

    /**
     * 比较大小
     *
     * @param v1
     * @param v2
     * @param scale 精度
     * @return 1：大于；0：等于；-1：小于
     */
    public static int compareTo(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1)).setScale(
                scale, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(Double.toString(v2)).setScale(
                scale, BigDecimal.ROUND_HALF_UP);
        return b1.compareTo(b2);
    }

    /**
     * 格式化钱
     *
     * @param v
     * @return
     */
    public static Double moneyFormat(double v) {
        DecimalFormat df1 = new DecimalFormat("##########0.00");
        df1.setRoundingMode(RoundingMode.HALF_UP);
        Double result = Double.parseDouble(df1.format(v));
        return result;
    }


    public static void main(String[] args) {
        float v1 = 1800.01f;
        double v2 = 153846;
        float v3 = 153846f;
        v1 = (float) v2;
        System.out.println(v1 + " " + v2 + " " + v3);
        System.out.println(Float.MAX_VALUE + " " + Float.MIN_VALUE);
        System.out.println(format(113123123123.132123132123D, 2));
        System.out.println(formatStr(113123123123.132123132123D, 2));
    }

    public static Double doubleCheck(double num) {
        if (0 == num) {
            return null;
        }
        if (1 == MathUtils.compareTo(num, Double.valueOf("9.999999999999998E9"))) {
            return null;
        }
        return MathUtils.format(num, 2);
    }
}