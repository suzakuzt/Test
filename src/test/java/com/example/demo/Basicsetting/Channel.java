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
public class Channel extends AbstractTestNGSpringContextTests{
    String token;
    String token1;
    String MobileNumber2 = Random.randommobile();
    String channelname=String.valueOf(Random.getRandomChar());
    String Id;
    String destroyTime;
    @Autowired
    public  AppUserService appUserService;
    @Autowired
    private AppUserMapper appUserMapper;

    @Test(description = "测试基本的渠道新增修改查询操作")
    public void Test() throws Exception {
            login();
            selectareas(token);
            newchannel(token);
            selectchannel(token);
            channelrevision(token);
    }

    @Test(description = "测试基本的渠道删除操作")
    public void Test2() throws Exception {
        login();
        deletechannel(token);
    }

    //登录获取TOKEN值,并验证登录功能是否正确
    public void login() throws Exception {
        String url = "/api/users/login";
        String json = "{\"loginAccount\":\"user2\",\"loginPwd\":\"cpp2017\"}";
        System.out.println(json);
        String actual = appUserService.login(url, json);
        // System.out.println("最后的结果值为" + actual);
        token = "Bearer " + actual + "";
    }



   //获取区域列表
   public void selectareas(String token) throws Exception {
       String url = "/api/basic/areas";
       String actual = appUserService.selectareas(url,token);
   }
/**
 *
 * 新增渠道功能
 *
 *
 * **/
    //新增渠道
    public void newchannel(String token) throws Exception {
        String url = "/api/basic/channels";
        String json = "{\"name\":\""+channelname+"\",\"creationTime\":\"\",\"editable\":true,\"type\":\"0\",\"needDaliyReminder\":true}";
        System.out.println(json);
        String actual = appUserService.newchannel(url, json);
        //获取数据库最新添加的品牌
        Map<String, Object> map = appUserMapper.getchannel(Id);
        String Name = String.valueOf(map.get("Name"));
        System.out.println(Name);
        Assertion.verifyEquals(Name,channelname,"验证数据库是否成功添加数值");
    }


    //查询渠道
    public void selectchannel(String token) throws Exception {
        Map<String, Object> map = appUserMapper.getchannel(Id);
        String Name = String.valueOf(map.get("Name"));
        System.out.println(Name);
        String url = "/api/basic/channels/name="+Name+"";
        String actual = appUserService.selectchannel(url,token);
    }

    //修改渠道
    public void channelrevision(String token) throws Exception {
        Map<String, Object> map = appUserMapper.getchannelid(Id);
        String id = String.valueOf(map.get("Id"));
        System.out.println(id);
        String url = "/api/basic/channels/"+id+"";
        String changename=String.valueOf(Random.getRandomChar());
        System.out.println("changename"+changename);
      //  String json = "{\"id\":\""+id+"\",\"name\":\""+changename+"\",\"needDaliyReminder\":\"true\",\"type\":\"0\"}";
        String json ="{\"type\":\"1\",\"name\":\""+changename+"\",\"isSingle\":true,\"needDaliyReminder\":true,\"destroyTime\":null,\"parentId\":null,\"creatorUserId\":null,\"lastModifierUserId\":null,\"editable\":true}";
        System.out.println(json);
        String actual = appUserService.channelrevision(url, json);
        Map<String, Object> map1 = appUserMapper.getchannel(Id);
        String Name = String.valueOf(map1.get("Name"));
        System.out.println("Name的值为"+Name);
        Assertion.verifyEquals(changename,Name,"添加数值修改类型失败");
    }


    //删除渠道
    public void deletechannel(String token) throws Exception {
        Map<String, Object> map = appUserMapper.getchannelid(Id);
        String id = String.valueOf(map.get("Id"));
        String url = "/api/basic/channels/"+id+"";
        String actual = appUserService.deletechannel(url,token);
        System.out.println(actual);
        Map<String, Object> map1 = appUserMapper.channeldestroyTime(destroyTime);
        String destroyTime = String.valueOf(map1.get("destroyTime"));
        if (destroyTime != "") {
            System.out.println("TEST PASS");
        } else {
            Assertion.verifyEquals(0, 1, "删除失败");
        }
    }
}
