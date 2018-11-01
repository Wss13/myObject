package com.example.configure.dto;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/10/31
 */
public class Configuer {


    public String getMoudleName() {
        return moudleName;
    }

    public void setMoudleName(String moudleName) {
        this.moudleName = moudleName;
    }

    public String getMoudleKeyName() {
        return moudleKeyName;
    }

    public void setMoudleKeyName(String moudleKeyName) {
        this.moudleKeyName = moudleKeyName;
    }

    public String getMoudleValue() {
        return moudleValue;
    }

    public void setMoudleValue(String moudleValue) {
        this.moudleValue = moudleValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    String moudleName;
    String moudleKeyName;
    String moudleValue;
    String type;
}
