package com.mzw.pattern;

import java.io.*;

/**
 * @author Jonathan Meng
 * @date 10/05/2019
 */
public class test {
    public static void main(String[] args) {
        test();
    }
    public static String test() {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader("test"); br = new BufferedReader(fr);
            return br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{ }
        return null;
    }
}
