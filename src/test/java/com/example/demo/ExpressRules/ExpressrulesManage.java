package com.example.demo.ExpressRules;

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
public class ExpressrulesManage  extends AbstractTestNGSpringContextTests {
    String token;
    String token1;
    String expressrulename=String.valueOf(Random.getRandomChar());
    String Id;
    String destroyTime;
    @Autowired
    public AppUserService appUserService;
    @Autowired
    private AppUserMapper appUserMapper;

    @Test(description = "快递规则新增编辑查询操作")
    public void Test() throws Exception {
        login();
        templates(token);
        newexpressrule(token);
        expressrulerevision(token);

    }

    @Test(description = "快递规则删除操作")
    public void Test2() throws Exception {
        login();
        deleteexpressrule(token);
    }
    //登录获取TOKEN值,并验证登录功能是否正确
    public void login() throws Exception {
        String url = "/api/users/login";
        String json = "{\"loginAccount\":\"user2\",\"loginPwd\":\"cpp2017\"}";
        System.out.println(json);
        String actual = appUserService.login(url, json);
        token = "Bearer " + actual + "";
    }



    //新增快递规则
    public void newexpressrule(String token) throws Exception {
        Map<String, Object> map = appUserMapper.getparcelid(Id);
        String id = String.valueOf(map.get("Id"));
        System.out.println(id);
        String url = "/api/express/rules";
        String json = "{\"name\":\""+expressrulename+"\",\"expressCompanyId\":\""+id+"\",\"noDeliveryArea\":\"2\",\"exceptionalArea\":\"4\",\"exceptionalECId\":\"3\"}";
        System.out.println(json);
        String actual = appUserService.newexpressrule(url, json);
        Map<String, Object> map1 = appUserMapper.getexpressrule(Id);
        String Name = String.valueOf(map1.get("Name"));
        System.out.println("Name的值为"+Name);
        Assertion.verifyEquals(expressrulename,Name,"添加数值修改类型失败");
    }

    //查询快递规则
    public void selectexpressrule(String token) throws Exception {
        String url = "/api/express/rules";
        String actual = appUserService.selectexpressrule(url,token);
    }

    //修改快递规则
    public void expressrulerevision(String token) throws Exception {

        Map<String, Object> map1 = appUserMapper.getexpressruleid(expressrulename);
        String id = String.valueOf(map1.get("Id"));
        Map<String, Object> map = appUserMapper.getparcelid(Id);
        String parcelid = String.valueOf(map.get("Id"));
        String url = "/api/express/rules/"+id+"";
        String changename=String.valueOf(Random.getRandomChar());
        System.out.println("changename"+changename);
        String json = "{\"id\":"+id+",\"name\":\""+changename+"\",\"expressCompanyId\":\""+parcelid+"\",\"noDeliveryArea\":\"1,2\",\"exceptionalArea\":\"4\",\"exceptionalECId\":\"5\"}";
        System.out.println(json);
        String actual = appUserService.expressrulerevision(url, json);
        Map<String, Object> map2 = appUserMapper.getexpressrule(Id);
        String Name = String.valueOf(map2.get("Name"));
        System.out.println("Name的值为"+Name);
        Assertion.verifyEquals(changename,Name,"添加数值修改类型失败");

    }


    //删除快递规则
    public void deleteexpressrule(String token) throws Exception {
        Map<String, Object> map = appUserMapper.getexpressruleid(expressrulename);
        String id = String.valueOf(map.get("Id"));
        String url = "/api/express/rules/"+id+"";
        String actual = appUserService.deleteexpressrule(url,token);
        Map<String, Object> map1 = appUserMapper.ruledestroyTime(destroyTime);
        String destroyTime = String.valueOf(map1.get("destroyTime"));
        if (destroyTime != "") {
            System.out.println("TEST PASS");
        } else {
            Assertion.verifyEquals(0, 1, "删除失败");
        }
    }


    //获取面单模板
    public void templates(String token) throws Exception {
        String url = "/api/express/templates";
        String actual = appUserService.templates(url,token);

    }

}
