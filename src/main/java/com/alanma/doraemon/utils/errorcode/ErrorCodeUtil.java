package com.alanma.doraemon.utils.errorcode;

import java.util.HashMap;
import java.util.Map;


public class ErrorCodeUtil {
    
    public static void main(String[] args) {
        String IMD002 = "IMD001|[%s]入参条件数据容器非[%s]类型";
        Map map=getTCResult(IMD002, "user","String");
        
    }
    
    public static Map getTCResult(String errorCode,
            String... errMsgKey) {
        // TODO
        String[] errInfo=errorCode.split("\\|");
       System.out.println(errInfo[0]);
       System.out.println(errInfo[1]);
       for(int index=0;index<errMsgKey.length;index++){
           errInfo[1]=errInfo[1].replaceFirst("%s", errMsgKey[index]);
       }
      System.out.println(errInfo[0]);
      System.out.println(errInfo[1]);
        Map retMap=new HashMap();
        retMap.put("errorCode", errInfo[0]);
        retMap.put("errorMsg", errInfo[1]);
        return retMap;
    }
}
