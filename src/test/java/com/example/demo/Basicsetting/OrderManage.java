package com.example.demo.Basicsetting;


import com.example.demo.mapper.AppUserMapper;
import com.example.demo.service.AppUserService;
import com.example.demo.util.Assertion;
import com.example.demo.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@SpringBootTest
@Listeners({com.example.demo.util.AssertionListener.class})
public class OrderManage extends AbstractTestNGSpringContextTests {
    String token;
    String token1;
    String MobileNumber2 = Random.randommobile();
    String channelname=String.valueOf(Random.getRandomChar());
    String Id;
    String destroyTime;
    @Autowired
    public AppUserService appUserService;
    @Autowired
    private AppUserMapper appUserMapper;

    @Test
    public void Test() throws Exception {
        login();
        selectorder(token);

    }

    //登录获取TOKEN值,并验证登录功能是否正确
    public void login() throws Exception {
        String url = "/api/users/login";
        String json = "{\"loginAccount\":\"user2\",\"loginPwd\":\"cpp2017\"}";
        System.out.println(json);
        String actual = appUserService.login(url, json);
        token = "Bearer " + actual + "";
    }

    //获取订单列表
    public void selectorder(String token) throws Exception {
        String url = "/api/orders?name=?&phoneno=?&expressno=?&startdate=?&enddate=?&page=?&pagecount=?";
        String actual = appUserService.selectorder(url,token);
    }

     //获取订单详细
    public void selectorderdetail(String token) throws Exception {
        String url = "/api/order/{orderNo}";
        String actual = appUserService.selectorder(url,token);
    }

    //修改渠道
    public void orderrevision(String token) throws Exception {
        Map<String, Object> map = appUserMapper.getchannelid(Id);
        String id = String.valueOf(map.get("Id"));
        System.out.println(id);
        String url = "/api/orders/{orderNo}";
        String changename=String.valueOf(Random.getRandomChar());
        System.out.println("changename"+changename);
        //  String json = "{\"id\":\""+id+"\",\"name\":\""+changename+"\",\"needDaliyReminder\":\"true\",\"type\":\"0\"}";
        String json ="{\"type\":\"1\",\"name\":\""+changename+"\",\"isSingle\":true,\"needDaliyReminder\":true,\"destroyTime\":null,\"parentId\":null,\"creatorUserId\":null,\"lastModifierUserId\":null,\"editable\":true}";
        System.out.println(json);
        String actual = appUserService.orderrevision(url, json);
        Map<String, Object> map1 = appUserMapper.orderrevision(Id);
        String Name = String.valueOf(map1.get("Name"));
        System.out.println("Name的值为"+Name);
        Assertion.verifyEquals(changename,Name,"添加数值修改类型失败");
    }




}
