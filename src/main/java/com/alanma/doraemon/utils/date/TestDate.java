package com.alanma.doraemon.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {

    public static void main(String[] args) {
        datePastTime();
        dateCompare();
    }

    private static void datePastTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        System.out.println("当前时间：" + sdf.format(now));

        Date afterDate = new Date(now.getTime() + 300000);
        System.out.println("到期时间：" + sdf.format(afterDate));
    }

    private static void dateCompare() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr1 = "2014-05-14 11:11:11";
            String dateStr2 = "2016-05-14 12:12:12";
            Date date1 = sdf.parse(dateStr1);
            Date date2 = sdf.parse(dateStr2);
            if (date1.getTime() - date2.getTime() > 0) {
                System.out.println("dateStr2 比 dateStr1 早");
            }
            else {
                System.out.println("dateStr1 比 dateStr2 早");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
