package com.mxw.doraemon.utils;

public class RandomUtil {

    public static int rand(int n) {
        int ans = 0;
        while (Math.log10(ans) + 1 < n) {
            ans = (int) (Math.random() * Math.pow(10, n));
        }
        return ans;
    }

}