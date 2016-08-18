package com.alanma.doraemon.utils.log;

import org.apache.log4j.Logger;

public class LogUtil {

    public static void info(Class clazz, String info) {
        System.out.println("===isInfoEnabled:" + Logger.getLogger(clazz).isInfoEnabled());
        System.out.println("[mxw log]:" + info);
    }

    public static void main(String[] args) {
        info(LogUtil.class, "===========test log print=========");
        Logger logger = Logger.getLogger(LogUtil.class);
        // logger.setLevel( Level.DEBUG );
        logger.info("===========test log print file=========");
        System.out.println(logger.getLevel());
    }
}
