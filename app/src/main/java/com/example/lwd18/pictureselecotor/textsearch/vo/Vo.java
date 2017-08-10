package com.example.lwd18.pictureselecotor.textsearch.vo;

import java.io.Serializable;

/**
 * vo类
 */
public class Vo implements Serializable {
    private boolean isChecked;
    private String str1;
    private String str2;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }
}
