package com.example.demo.Basicsetting;
import com.example.demo.mapper.AppUserMapper;
import com.example.demo.service.AppUserService;

import com.example.demo.util.Assertion;
import com.example.demo.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.Map;

@SpringBootTest
@Listeners({com.example.demo.util.AssertionListener.class})
    public class BrandManage extends AbstractTestNGSpringContextTests {
        String token;
        String token1;
        String MobileNumber2 = Random.randommobile();
        String username=String.valueOf(Random.getRandomChar());
        String Id;
        String destroyTime;

        @Autowired
        public  AppUserService appUserService;

        @Autowired
        private AppUserMapper appUserMapper;

        @Test(description = "测试品牌基本接口增加修改查询功能")
        public void Test() throws Exception {
              login();
              newbrand(token);
              selectbrand(token);
              brandrevision(token);
        }

        @Test(description = "测试品牌基本接口删除功能")
        public void Test2() throws Exception {
            login();
            deletebrand(token);
        }

        //登录获取TOKEN值,并验证登录功能是否正确
        public void login() throws Exception {
            //输入地址信息，后续会进行拼接
            String url = "/api/users/login";
            //输入JSON请求
            String json =  "{\"loginAccount\":\"user2\",\"loginPwd\":\"cpp2017\"}";
            //System.out.println(json);
            //String一个实际结果，LOGIN方法中会进行字符串截取，截取对应的TOKEN值
            String actual = appUserService.login(url,json);
            //将TOKEN值取出
            token = "Bearer " + actual + "";
        }

        //新增品牌
        public void newbrand(String token) throws Exception {
            String url = "/api/basic/brands";
            String json = "{\"name\":\""+username+"\",\"creationTime\":\"\",\"editable\":true}";
            //System.out.println(json);
            String actual = appUserService.newbrand(url, json);
           //获取数据库最新添加的品牌，获取数据库中的值之后取出值做对比，预期结果和实际结果进行对比
            Map<String, Object> map = appUserMapper.getBrand(Id);
            String Name = String.valueOf(map.get("Name"));
            System.out.println(Name);
            Assertion.verifyEquals(Name,username,"新增品牌失败");
        }

         //查询品牌
         public void selectbrand(String token) throws Exception {
             Map<String, Object> map = appUserMapper.getBrandid(username);
             String id = String.valueOf(map.get("Id"));
             //System.out.println(id);
             String url = "/api/basic/brands?id="+id+"";
             String actual = appUserService.selectbrand(url,token);
         }


        //修改品牌
        public void brandrevision(String token) throws Exception {
            Map<String, Object> map = appUserMapper.getBrandid(username);
            String id = String.valueOf(map.get("Id"));
            //数据库中获取到对应的新增的值
            String url = "/api/basic/brands/"+id+"";
            //定义一个随机变化的名称该名称用来作为修改后的结果名和原先的预期结果做对比
            String changname=String.valueOf(Random.getRandomChar());
            String json = "{\"name\":\""+changname+"\",\"destroyTime\":null,\"creatorUserId\":null,\"lastModifierUserId\":null,\"editable\":true}";
            //发送请求获取请求结果值
            String actual = appUserService.brandrevision(url, json);
            Map<String, Object> map1 = appUserMapper.getBrand(Id);
            String Name = String.valueOf(map1.get("Name"));
            System.out.println("Name的值为"+Name);
            Assertion.verifyEquals(changname,Name,"修改品牌失败");
        }


        //删除品牌
        public void deletebrand(String token) throws Exception {
            Map<String, Object> map = appUserMapper.getBrandid(username);
            String id = String.valueOf(map.get("Id"));
            //int y=Integer.valueOf(id);
            String url = "/api/basic/brands/"+id+"";
            String actual = appUserService.deletebrand(url,token);
            System.out.println(actual);
            Map<String, Object> map1 = appUserMapper.getdestroyTime(destroyTime);
            String destroyTime = String.valueOf(map1.get("destroyTime"));
            if(destroyTime!=""){
                System.out.println("TEST PASS");
            } else{
                Assertion.verifyEquals(0,1,"删除失败");
            }
//            int x =Integer.valueOf(id1);
//            if(x!=y)
//                System.out.println("TEST PASS");
//            else
//                Assertion.verifyEquals(0,1,"删除失败");
       }
    }
