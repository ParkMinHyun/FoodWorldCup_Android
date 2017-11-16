package com.example.parkminhyun.myapplication;

import java.util.HashMap;

public class FoodInfomation {
//
//    public static final String baekban = "백반";
//    public static final String bibimbab = "비빔밥";
//    public static final String bread = "빵";
//    public static final String cake = "케이크";
//    public static final String chicken = "치킨";
//    public static final String dduckppoki = "떡볶이";
//    public static final String donggas = "돈가스";
//    public static final String douhgnut = "도넛";
//    public static final String gimbab = "김밥";
//    public static final String hamburger = "햄버거";
//    public static final String ramen = "라멘";
//    public static final String sandwich = "샌드위치";
//    public static final String spagetti = "스파게티";
//    public static final String steak = "스테이크";
//    public static final String sushi = "스시";
//    public static final String zazang = "짜장면";

    private static FoodInfomation instance;
    public HashMap<String, String> map = new HashMap<String, String>();

    public static FoodInfomation getInstance(){
        if(instance==null)
        {
            instance = new FoodInfomation();
            return instance;
        }
        else
            return instance;
    }

    public FoodInfomation(){
        map.put("baekban","백반");
        map.put("bibimbab","비빔밥");
        map.put("bread","빵");
        map.put("cake","케이크");
        map.put("chicken","치킨");
        map.put("dduckppoki","떡볶이");
        map.put("donggas","돈가스");
        map.put("douhgnut","도넛");
        map.put("gimbab","김밥");
        map.put("hamburger","햄버거");
        map.put("ramen","라멘");
        map.put("sandwich","샌드위치");
        map.put("spagetti","스파게티");
        map.put("steak","스테이크");
        map.put("sushi","스시");
        map.put("zazang","짜장면");
    }

    public HashMap<String, String> getMap() {
        return map;
    }
}
