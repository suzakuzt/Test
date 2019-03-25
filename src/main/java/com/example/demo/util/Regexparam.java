package com.example.demo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


    public class Regexparam {
        public static String regex(String str) {
            String[] strs = str.split("\"");
            int leng = strs.length;
            // for (int i = 0; i < leng; i++) {
            System.out.println(strs[3].toString());
            // }
            String token = strs[3].toString();

            return token;
        }


        public static String regexlogin(String str) {
            String[] strs = str.split("\"");
            int leng = strs.length;
            // for (int i = 0; i < leng; i++) {
            System.out.println(strs[15].toString());
            // }
            String token2 = strs[15].toString();

            return token2;
        }
        // public static void main(String[] args){

        public static String Regextest(String value, String str) {
            //System.out.println((new Regexparam()).getSubUtil(str, regex));


            if(value.equals("")) {
                String regex = "(?<=accessToken\":\")(.*?)(?=\",\"expireInSeconds)";
                //   List<String> lists = (new Regexparam()).getSubUtil(str, regex);
                //    for (String result : lists) {
                //    //  System.out.println(result);
                //    }
                String result = (new com.example.demo.util.Regexparam()).getSubUtilSimple(str, regex);
                return result;
                //   System.out.println(result);
            }else{
                String regex ="(?<=accessToken\":\")(.*?)(?=\",\"expireInSeconds)";
                List<String> lists = (new com.example.demo.util.Regexparam()).getSubUtil(str, regex);
//    for (String result : lists) {
//    //  System.out.println(result);
//    }
                String result = (new com.example.demo.util.Regexparam()).getSubUtilSimple(str, regex);
                return result;
            }

        }
//


        public static String Regextest1(String value, String str) {
            //System.out.println((new Regexparam()).getSubUtil(str, regex));


            if(value.equals("")) {
                String regex = "(?<=result\":)(.*?)(?=,\"targetUrl\":)";
                //   List<String> lists = (new Regexparam()).getSubUtil(str, regex);
                //    for (String result : lists) {
                //    //  System.out.println(result);
                //    }
                String result = (new com.example.demo.util.Regexparam()).getSubUtilSimple(str, regex);
                return result;
                //   System.out.println(result);
            }else{
                String regex ="(?<=result\":)(.*?)(?=,\"targetUrl\":)";
                List<String> lists = (new com.example.demo.util.Regexparam()).getSubUtil(str, regex);
//    for (String result : lists) {
//    //  System.out.println(result);
//    }
                String result = (new com.example.demo.util.Regexparam()).getSubUtilSimple(str, regex);
                return result;
            }

        }
        /**
         * 正则表达式匹配两个指定字符串中间的内容
         *
         * @param soap
         * @return
         */
        public List<String> getSubUtil(String soap, String regex) {
            List<String> list = new ArrayList<String>();
            Pattern pattern = Pattern.compile(regex);// 匹配的模式
            Matcher m = pattern.matcher(soap);
            while (m.find()) {
                int i = 1;
                list.add(m.group(i));
                i++;
            }
            return list;
        }

        /**
         * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
         *
         * @param soap
         * @param regex
         * @return
         */
        public static String getSubUtilSimple(String soap, String regex) {
            Pattern pattern = Pattern.compile(regex);// 匹配的模式
            Matcher m = pattern.matcher(soap);
            while (m.find()) {
                return m.group(1);
            }
            return "";
        }

        public static String  regextestt(String value,String str){


            String xx= Regextest(value,str);
            System.out.println("值为"+xx);
            String s="\"";
            if(xx.contains(s)){
                String valuee="";
                 String xxx= Regextest(valuee,xx);
              //  System.out.println(xxx);
                return xxx;
            }else{
                return  xx;
            }

        }
//      public static  void main(String[] args){
//          String  value ="policyNo";
//          String str ="{\"result\":[{\"policyNo\":\"6180813111418553\",\"proposalNo\":\"5180813189055635\",\"totalPrice\":100.00,\"insuranceNum\":1,\"saleDate\":\"2018-08-09\",\"insuranceBeginDate\":\"2018-08-09 00:00:00\",\"lastInsuranceBeginDate\":\"2018-08-09 00:00:00\",\"insuranceEndDate\":\"2019-08-19 23:59:59\",\"insurancePeriod\":1,\"insurancePeriodType\":\"Y\",\"insuredNum\":1,\"campaignCode\":\"cpp10\",\"campaignVersion\":1,\"productVersion\":2,\"productCode\":\"02CPP04\",\"paymentType\":null,\"paymentPeriod\":null,\"paymentMethod\":null,\"cancelReason\":null,\"cancelDate\":null,\"status\":true,\"isRenewal\":true,\"bpCode\":\"CPP\",\"orderNo\":\"LBX45935864\",\"outPolicyNo\":\"LBX45935864\",\"campaignName\":\"cpp10\",\"bpCampaignName\":\"双人会籍\",\"memberNo\":\"718081311141832\",\"renewalType\":2,\"nppPolicyNo\":null,\"outTradeNo\":null}],\"targetUrl\":null,\"success\":true,\"error\":null,\"unAuthorizedRequest\":false,\"__abp\":true},\"statu\":200\n";
//
//            String xxx =regextestt(value,str);
//            System.out.println(xxx);
//      }

    }



