package com.alanma.doraemon.utils.tswitch;

public class TSwitch {

    public static void main(String[] args) {
        int key = 2;
        String result = "初始值";
        switch (key) {
        case 1:
            result = "case1";
            break;
        case 2:
            result = "case2";
            break;

        default:
            result = "case default";
            break;
        }
        System.out.print(result);
    }
}
