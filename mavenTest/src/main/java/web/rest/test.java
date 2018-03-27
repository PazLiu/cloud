package web.rest;

import org.apache.commons.lang.StringUtils;

public class test {
    public static void main(String[] args){
        String[] str = {"abc", "bcd", "def"};

        System.out.println( StringUtils.join(str));
    }
}
