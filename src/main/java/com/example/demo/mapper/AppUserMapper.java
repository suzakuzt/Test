package com.example.demo.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/*
* 数据库操作
* */
public interface AppUserMapper {
    //查询对应的新添加的品牌产品名称
    Map<String,Object> getBrand(@Param("id") String OutTradeNo);
    //查询对应的新添加的品牌id
    Map<String,Object> getBrandid(@Param("Name") String OutTradeNo);
    //查询对应的新添加的类型名称
    Map<String,Object> getCategory(@Param("id") String OutTradeNo);
    //查询对应的新添加的类型id
    Map<String,Object> getCategoryid(@Param("Name") String OutTradeNo);
    //查询对应的新添加的渠道名称
    Map<String,Object> getchannel(@Param("id") String OutTradeNo);
    //查询对应的新添加的渠道ID
    Map<String,Object> getchannelid(@Param("Name") String OutTradeNo);
    //查询对应的新添加的快递公司名称
    Map<String,Object> getparcel(@Param("id") String OutTradeNo);
    //查询对应的新添加的快递公司ID
    Map<String,Object> getparcelid(@Param("Name") String OutTradeNo);
    //查询对应的新添加的快递仓库名称
    Map<String,Object> getstore(@Param("id") String OutTradeNo);
    //查询对应的新添加的快递仓库ID
    Map<String,Object> getstoreid(@Param("Name") String OutTradeNo);
    //查询对应的新添加的产品名称
    Map<String,Object> getproduct(@Param("id") String OutTradeNo);
    //查询对应的新添加的产品ID
    Map<String,Object> getproductid(@Param("Name") String OutTradeNo);
    //查询对应的新添加的产品SKU名称
    Map<String,Object> getproductsku(@Param("id") String OutTradeNo);
    //查询对应的新添加的产品SKUID
    Map<String,Object> getproductskuid(@Param("Name") String OutTradeNo);
    //查询对应的新添加的快递规则名称
    Map<String,Object>  getexpressrule(@Param("id") String OutTradeNo);
    //查询对应的新添加的快递规则ID
    Map<String,Object>  getexpressruleid(@Param("Name") String OutTradeNo);
    //查询对应的新添加的营销名称
    Map<String,Object>  getcampaigns(@Param("id") String OutTradeNo);
    //查询对应的新添加的营销名称ID
    Map<String,Object>  getcampaignsid(@Param("Name") String OutTradeNo);
    //查询删除品牌的时间信息
    Map<String,Object> getdestroyTime(@Param("getdestroyTime") String OutTradeNo);
    //查询删除类型的时间信息
    Map<String,Object> categoriesdestroyTime(@Param("getdestroyTime") String OutTradeNo);
    //查询删除渠道的时间信息
    Map<String,Object> channeldestroyTime(@Param("getdestroyTime") String OutTradeNo);
    //查询删除快递公司的时间信息
    Map<String,Object> parceldestroyTime(@Param("getdestroyTime") String OutTradeNo);
    //查询删除仓库的时间信息
    Map<String,Object> storedestroyTime(@Param("getdestroyTime") String OutTradeNo);
    //查询删除快递规则的时间信息
    Map<String,Object> ruledestroyTime(@Param("getdestroyTime") String OutTradeNo);
    //查询删除产品的时间信息
    Map<String,Object> productdestroyTime(@Param("getdestroyTime") String OutTradeNo);
    //查询删除快产品SKU的时间信息
    Map<String,Object> pskudestroyTime(@Param("getdestroyTime") String OutTradeNo);
    //查询删除快产品营销的时间信息
    Map<String,Object> campaignsdestroyTime(@Param("getdestroyTime") String OutTradeNo);
    //查询对应的的订单ID
    Map<String,Object> orderrevisionid(@Param("Name") String OutTradeNo);
    //查询对应的订单code
    Map<String,Object> orderrevisioncode(@Param("id") String OutTradeNo);


}
