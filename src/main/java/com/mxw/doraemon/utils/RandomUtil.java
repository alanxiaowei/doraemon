package com.mxw.doraemon.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class RandomUtil {


    static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("MMdd_HHmmss_SSS", Locale.US);

    static final Random random = new Random();

    public static int rand(int n) {
        int ans = 0;
        while (Math.log10(ans) + 1 < n) {
            ans = (int) (Math.random() * Math.pow(10, n));
        }
        return ans;
    }

    public static String traceId(String sysCode) {
        return String.format("%s_%03d_%04x", LocalDateTime.now().format(DT_FORMATTER), sysCode,
                random.nextInt(0x10000));
    }


    public static void main(String[] args) {
        System.out.println(traceId("10001"));
    }


}