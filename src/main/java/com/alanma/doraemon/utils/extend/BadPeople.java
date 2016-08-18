package com.alanma.doraemon.utils.extend;

import java.io.Serializable;

public class BadPeople extends People implements Serializable {

    private static final long serialVersionUID = 4381687931140928608L;
    private String killNum;
    
    public BadPeople(String killNum) {
        super();
        this.killNum = killNum;
    }

    @Override
    public BadPeople born() {
        super.born();
        return this;
    }

    
    public String getKillNum() {
        return killNum;
    }

    
    public void setKillNum(String killNum) {
        this.killNum = killNum;
    }


    @Override
    public String toString() {
        return "BadPeople [killNum=" + killNum + "]";
    }
    
    
    
}
