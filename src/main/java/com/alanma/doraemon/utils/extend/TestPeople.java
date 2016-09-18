package com.alanma.doraemon.utils.extend;

import com.alibaba.fastjson.JSONObject;

public class TestPeople {

    public static void main(String[] args) {
        People people = peopleBorn(true);
        String json = JSONObject.toJSONString(people);
        System.out.println(json);
    }

    private static People peopleBorn(boolean isGoodPeople) {
        if (isGoodPeople) {
            return new GoodPeople("5").born();
        }
        else {
            return new BadPeople("10").born();
        }
    }
}
