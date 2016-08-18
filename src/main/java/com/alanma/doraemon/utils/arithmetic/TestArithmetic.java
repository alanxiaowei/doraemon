package com.alanma.doraemon.utils.arithmetic;

public class TestArithmetic {

    public static void main(String[] args) {
        int count=6;
        int maxrow=3;
        int pageno;
        
        if(count%maxrow==0){
            pageno=count/maxrow;
        }else{
            pageno=count/maxrow+1;
        }
        
        System.out.print(pageno);
    }
}
