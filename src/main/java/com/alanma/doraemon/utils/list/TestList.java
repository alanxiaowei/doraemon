package com.alanma.doraemon.utils.list;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TestList {

    public static void main(String[] args) {
        ArrayList list=new ArrayList();
        list.add("001");
        list.add("002");
        
        String[] str=new String[2];
        str[0]="001";
        str[1]="002";
        
        
        System.out.println(list.toString());
        System.out.println(list.size());
        System.out.println(list.toString());
        System.out.println("条件的值JavaList，如：[\"列\",\"=\",值]或[[\"列1\",\"=\",值1,\"and\"],[\"列2\",\"<\",值2]]");
    }
    
    
}
