package com.alanma.doraemon.utils.random;

public class RandomTest {

    public static int rand(int n) {
        int ans = 0;
        while (Math.log10(ans) + 1 < n)
            ans = (int) (Math.random() * Math.pow(10, n));
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(rand(10));
    }

}