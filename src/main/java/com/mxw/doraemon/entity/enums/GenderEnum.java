package com.mxw.doraemon.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * 运营商联系电话枚举
 */
public enum GenderEnum implements IEnum {
    MALE("MALE", "男"),
    FEMALE("FEMALE", "女"),;



    private String value;
    private String desc;

    GenderEnum(final String value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return this.value;
    }

    @JsonValue
    public String getDesc(){
        return this.desc;
    }
}
