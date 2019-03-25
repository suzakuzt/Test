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

@Listeners({com.example.demo.util.AssertionListener.class})
@SpringBootTest
public class Parcel extends AbstractTestNGSpringContextTests{
    String token;
    String token1;
    String MobileNumber2 = Random.randommobile();
    String parcelname=String.valueOf(Random.getRandomChar());
    String Id;
    String destroyTime;
    @Autowired
    public  AppUserService appUserService;
    @Autowired
    private AppUserMapper appUserMapper;

    @Test(description = "测试基本的快递公司新增操作")
    public void Test() throws Exception {
        login();
        newparcel(token);
        selectparcel(token);
        parcelrevision(token);
    }

    @Test(description = "测试基本的快递公司删除操作")
    public void Test2() throws Exception {
        login();
        deleteparcel(token);
    }

    //登录获取TOKEN值,并验证登录功能是否正确
    public void login() throws Exception {
        String url = "/api/users/login";
        String json = "{\"loginAccount\":\"user2\",\"loginPwd\":\"cpp2017\"}";
        System.out.println(json);
        String actual = appUserService.login(url, json);
        token = "Bearer " + actual + "";
    }


    //新增快递公司
    public void newparcel(String token) throws Exception {
        String url = "/api/basic/expresses";
        String json = "{\"name\":\""+parcelname+"\",\"creationTime\":\"\",\"editable\":true,\"splitOrderWeight\":\"10\"}";
        System.out.println(json);
        String actual = appUserService.newparcel(url, json);
        //获取数据库最新快递公司信息
        Map<String, Object> map = appUserMapper.getparcel(parcelname);
        String Name = String.valueOf(map.get("Name"));
        System.out.println(Name);
        Assertion.verifyEquals(Name,parcelname,"验证数据库是否成功添加数值");
    }

    //查询快递公司
    public void selectparcel(String token) throws Exception {
        String url = "/api/basic/expresses";
        String actual = appUserService.selectparcel(url,token);
    }

    //修改快递公司
    public void parcelrevision(String token) throws Exception {
        Map<String, Object> map = appUserMapper.getparcelid(Id);
        String id = String.valueOf(map.get("Id"));
        System.out.println(id);
        String url = "/api/basic/expresses/"+id+"";
        String changename=String.valueOf(Random.getRandomChar());
        System.out.println("changename"+changename);
        String json = "{\"name\":\""+changename+"\",\"splitOrderWeight\":\"20\",\"editable\":true}";
        System.out.println(json);
        String actual = appUserService.parcelrevision(url, json);
        Map<String, Object> map1 = appUserMapper.getparcel(Id);
        String Name = String.valueOf(map1.get("Name"));
        System.out.println("Name的值为"+Name);
        Assertion.verifyEquals(changename,Name,"修改类型失败");
    }


    //删除快递公司
    public void deleteparcel(String token) throws Exception {
        Map<String, Object> map = appUserMapper.getparcelid(Id);
        String id = String.valueOf(map.get("Id"));
        String url = "/api/basic/expresses/"+id+"";
        String actual = appUserService.deleteparcel(url,token);
        System.out.println("返回值为"+actual);
        Map<String, Object> map1 = appUserMapper.parceldestroyTime(destroyTime);
        String destroyTime = String.valueOf(map1.get("destroyTime"));
        if (destroyTime != "") {
            System.out.println("TEST PASS");
        } else {
            Assertion.verifyEquals(0, 1, "删除失败");
        }
    }

}
