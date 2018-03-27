package com.shty.collect.util;

/**
 * Created by john on 2016/11/18.
 */
public enum IntervalEnum {
    NEXTPAGE("nextpage", 1), CLIENTREQUEST("request", 2), NOMORE("nomore", 3), HALFDAY("halfday", 4),CAPPERON("capperson", 5),
    THIRTYSECS("30secs",6),STORY("story",7),PERHOUR("perhour",8),TENHOURS("10hours",9), IMAGINE("imagine",10);
    // 成员变量
    private String name;
    private int index;

    private IntervalEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public static String getName(int index) {
        for (IntervalEnum in : IntervalEnum.values()) {
            if (in.getIndex() == index) {
                return in.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
