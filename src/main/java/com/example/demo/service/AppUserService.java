package com.example.demo.service;

import com.example.demo.model.Activetest;
import jdk.nashorn.internal.parser.Token;

import java.util.List;

public interface AppUserService {
    /*
     *1. 数据验证
     */
//    List<Activetest>   list ();

    /*
     * 2.获取TOKENapp接口数据
    */
//    String Tokenapp(String urltest) throws Exception;

    /*
     * 3.登录操作
     */
   String login(String urltest,String json)  throws Exception;


 /*
    * 4.新增品牌
    * */
    String newbrand(String urltest,String json)  throws Exception;



    /*
     * 5.查询品牌
     * */
     String  selectbrand(String urltest,String token)  throws Exception;


   /*
   * 6.修改品牌
   * */
     String brandrevision(String urltest,String json)  throws Exception;


     /**
     * 7.删除品牌
     */
    String deletebrand(String urltest,String token)  throws Exception;


    /*
     * 4.新增仓库
     * */
    String newstore(String urltest,String json)  throws Exception;



    /*
     * 5.查询仓库
     * */
    String  selectstore(String urltest,String token)  throws Exception;


    /*
     * 6.修改仓库
     * */
    String storerevision(String urltest,String json)  throws Exception;


    /**
     * 7.删除仓库
     */
    String deletestore(String urltest,String token)  throws Exception;


    /*
     * 8.新增类型
     * */
    String newcategories(String urltest,String json)  throws Exception;



    /*
     * 9.查询类型
     * */
    String  selectcategories(String urltest,String token)  throws Exception;


    /*
     * 10.修改类型
     * */
    String categoriesrevision(String urltest,String json)  throws Exception;


    /**
     * 11.删除类型
     */
    String deletecategories(String urltest,String token)  throws Exception;


    /*
     * 12.新增快递公司
     * */
    String newparcel(String urltest,String json)  throws Exception;



    /*
     * 13.查询快递公司
     * */
    String  selectparcel(String urltest,String token)  throws Exception;


    /*
     * 14.修改快递公司
     * */
    String parcelrevision(String urltest,String json)  throws Exception;

    /**
     * 15.删除快递公司
     */
    String deleteparcel(String urltest,String token)  throws Exception;

    /*
     *16.查询渠道类型
     **/
    String selectareas(String urltest, String token) throws Exception;

    /*
     *17.新增渠道接口测试
     **/
    String newchannel(String urltest, String json) throws Exception;

    /*
     *18.查询渠道类型
     **/
    String selectchannel(String urltest, String token) throws Exception;

    /*
     *19.修改渠道接口测试
     **/
    String channelrevision(String urltest, String json) throws Exception;


    /*
     *20.删除渠道接口测试
     **/
    String deletechannel(String urltest, String token) throws Exception;


    /*
     *21.获取获取产品信息列表
     **/
    String  selectopilist(String urltest, String token) throws Exception;


    /*
     *22.获取产品信息详细信息列表
     **/
    String  selectopidetail(String urltest, String token) throws Exception;


    /*
     *23.新增产品接口测试
     **/
    String newproduct(String urltest, String json) throws Exception;


   /*
    *24.获取单个产品信息详细信息接口测试
    **/
    String  productdetail(String urltest, String token) throws Exception;


   /*
    *25.修改产品接口测试
    **/
    String  productrevision(String urltest, String json) throws Exception;


  /*
   *26.删除产品接口测试
   **/
    String  deleteproduct(String urltest, String token) throws Exception;


   /*
    *27.获取产品SKU列表
    **/
    String  selectpsku(String urltest, String token) throws Exception;


   /*
    *28.新增产品SKU
    **/
    String newpsku(String urltest, String json) throws Exception;


   /*
    *29.修改产品SKU
    **/
    String pskurevision(String urltest, String json) throws Exception;


   /*
    *30.删除产品SKU
    **/
    String deletepsku(String urltest, String json) throws Exception;


   /*
    *31.获取产品营销
    **/
    String selectcampaigns(String urltest, String token) throws Exception;


   /*
    *32.新增产品营销活动
    **/
   String newcampaigns(String urltest, String json) throws Exception;


   /*
    *33.修改营销活动
    **/
   String campaignsrevision(String urltest, String json) throws Exception;


   /*
    *34.删除产品营销
    **/
   String deletecampaigns(String urltest, String token) throws Exception;


   /*
    *35.新增快递规则
    **/
   String newexpressrule(String urltest, String json) throws Exception;


   /*
    *36.查询快递规则
    **/
  String  selectexpressrule(String urltest, String token) throws Exception;



   /*
    *37.修改快递规则
    **/
  String expressrulerevision(String urltest, String json) throws Exception;


   /*
    *38.删除快递规则
    **/
  String deleteexpressrule(String urltest, String token) throws Exception;


   /*
    *39.获取面单模板
    **/
  String templates(String urltest, String token) throws Exception;


 /*
  *40.获取订单列表
  **/
 String selectorder(String urltest, String token) throws Exception;

 /*
  *41.订单修改
  **/
 String orderrevision(String urltest, String json) throws Exception;

}
