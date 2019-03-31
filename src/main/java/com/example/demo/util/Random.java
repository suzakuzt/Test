package com.example.demo.util;

public class Random {
   public static  String randommobile(){
    String phoneNo="156";
    String xx= String.valueOf((int)((Math.random()*9+1)*10000000));
    String randommobileNo=phoneNo+xx;
  return randommobileNo;
}

    public static  String randomorder(){
        String phoneNo="LBX";
        String xx= String.valueOf((int)((Math.random()*9+1)*10000000));
        String randomorder=phoneNo+xx;
        return randomorder;
    }

    public static  String randomIDNumber(){
        String IDNumber="32128219900331";
        String xx= String.valueOf((int)((Math.random()*9+1)*1000));
        String randomidNumber=IDNumber+xx;
        return randomidNumber;
    }



    

    //Unicode 基本汉字编码范围0x4e00~0x9fa5 共 20902个
    private final static int HANZI_LENGTH = 20902 ; //汉字生成码

    public static java.util.Random random = new java.util.Random();

    /**
     * 随机生成一个汉字
     * @return
     */
    public static char getRandomHanZi() {
        java.util.Random ran = new java.util.Random();
        return (char) (0x4e00 + ran.nextInt(HANZI_LENGTH));
    }
    public static char getRandomChar() {
        return (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)));
    }
    /**
     * 随机生成一个字母或者数字
     * @return
     */
    protected static char getRandomStr(String randomBase) {
        java.util.Random ran = new java.util.Random();
        return (char) randomBase.charAt(ran.nextInt(62));
    }
    public static  String randomtype(){

        String xx= String.valueOf((int)(Math.random()*5));
        return xx;
    }


    }
