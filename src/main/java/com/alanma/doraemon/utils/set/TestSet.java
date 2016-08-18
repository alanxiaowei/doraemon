package com.alanma.doraemon.utils.set;

import java.util.HashSet;
import java.util.Set;

public class TestSet {

    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        System.out.println(set.add("1"));
        System.out.println(set.add("1"));
        System.out.println(set.add("2"));
        System.out.println(set.toString());
    }
}
