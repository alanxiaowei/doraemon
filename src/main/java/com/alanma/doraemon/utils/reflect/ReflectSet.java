package com.alanma.doraemon.utils.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.alanma.doraemon.utils.reflect.domain.EntityTAD;

/**
 * 反射set赋值
 *
 */
public class ReflectSet {

    public static void main(String[] args) throws Exception {
        /*
         * 需要赋值的事例
         */
        EntityTAD bean = new EntityTAD();

        // 得到类对象
        @SuppressWarnings("rawtypes")
        Class cls = (Class) bean.getClass();


        /*
         * 得到类中的所有属性集合
         */
        Field[] fs = cls.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            String type = f.getType().toString();// 得到此属性的类型
            if (type.endsWith("String")) {
                f.set(bean, "12"); // 给属性设值
            }
            else if (type.endsWith("int") || type.endsWith("Integer")) {
                f.set(bean, 12); // 给属性设值
            }
        }

        /*
         * 打印赋值结果
         */
        Method[] methods = cls.getMethods();

        for ( int i = 0; i < methods. length ; i++){

            Method method = methods[i];

            if (method.getName().startsWith( "get" )){

               System. out .print( "methodName:" +method.getName()+ "/t" );

               System. out .println( "value:" +method.invoke(bean)); // 得到 get 方法的值

            }

        }
    }

    /**
     * @param val
     * @return
     */
    public static String firstToLower(String val) {
        return val.substring(0, 1).toLowerCase() + val.substring(1);
    }
}
