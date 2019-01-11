package com.mzw;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author mengzhaowei@boce.cn
 * @date 2018/12/1 22:54
 */
public class Maopao {
    public static void main(String[] args) {
        int[] arr = {1, 8, 5, 3, 2, 7, 2};
        int temp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        for (int i=0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        short a = 1;
//        a = a+1;
        a += 1;
//        alert(),confirm(),prompt()

        System.out.println(isNumber("fsdf"));
        System.out.println(isNumber("345435"));
        System.out.println(isNumber("11"));
    }
    private static boolean isNumber(String s){
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(s);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
