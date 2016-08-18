package com.alanma.doraemon.utils.arry;

import java.util.ArrayList;
import java.util.List;


public class ArrayPage {
    /**
     * 获取分页属性<br>
     * 1-最后一页<br>
     * 2-上一页<br>
     * 3-下一页<br>
     * 4-当前页<br>
     * 
     * @param pagelist
     * @return
     */
    public static int[] getPageParams(List pagelist) {
        int[] pageParam = new int[2];
        pageParam[1] = (Integer) pagelist.get(2);
        int pageOper = (Integer) pagelist.get(0);
        switch (pageOper) {
        case 1:
            pageParam[0] = -1;
            break;
        case 2:
            pageParam[0] = (Integer) pagelist.get(1) - 1;
            break;
        case 3:
            pageParam[0] = (Integer) pagelist.get(1) + 1;
            break;
        case 4:
            pageParam[0] = (Integer) pagelist.get(1);
            break;

        default:
            break;
        }

        return pageParam;
    }
    
    public static void main(String[] args) {
        List pagelist=new ArrayList();
        pagelist.add(4);
        pagelist.add(1);
        pagelist.add(1);
        int[] pages = new int[2];
//        pages=getPageParams(pagelist);
        if(pages == null){
            System.out.println("sdsd");
        }
        for (int i = 0; i < pages.length; i++) {
            System.out.println(pages[i]);
        }
    }

}
