package com.alanma.doraemon.utils.extend;

import java.io.Serializable;

public class GoodPeople extends People implements Serializable {

    private static final long serialVersionUID = -6701849271326687976L;
    private String saveNum;

    public GoodPeople(String saveNum) {
        super();
        this.saveNum = saveNum;
    }


    @Override
    public GoodPeople born() {
        super.born();
        return this;
    }

    
    public String getSaveNum() {
        return saveNum;
    }

    
    public void setSaveNum(String saveNum) {
        this.saveNum = saveNum;
    }


    @Override
    public String toString() {
        return "GoodPeople [saveNum=" + saveNum + "]";
    }
    
}
