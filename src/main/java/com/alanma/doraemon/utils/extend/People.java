package com.alanma.doraemon.utils.extend;

import java.io.Serializable;

public class People implements Serializable {

    private static final long serialVersionUID = -1243297810454772212L;
    private String age;

    public People born() {
        this.age = "0";
        return this;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "People [age=" + age + "]";
    }

}
