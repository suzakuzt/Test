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
    public class StoreManage extends AbstractTestNGSpringContextTests {
        String token;
        String token1;
        String MobileNumber2 = Random.randommobile();
        String storename=String.valueOf(Random.getRandomChar());
        String Id;
        String destroyTime;
        @Autowired
        public  AppUserService appUserService;
        @Autowired
        private AppUserMapper appUserMapper;

        @Test(description = "仓库新增编辑想查询操作")
        public void Test() throws Exception {
            login();
            newstore(token);
            selectstore(token);
            storerevision(token);
           // deletestore(token);
        }

        @Test(description = "仓库删除操作")
        public void Test2() throws Exception {
            login();
            deletestore(token);
    }


        //登录获取TOKEN值,并验证登录功能是否正确
        public void login() throws Exception {
            String url = "/api/users/login";
            String json = "{\"loginAccount\":\"user2\",\"loginPwd\":\"cpp2017\"}";
            System.out.println(json);
            String actual = appUserService.login(url, json);
            token = "Bearer " + actual + "";
        }

        //新增仓库
        public void newstore(String token) throws Exception {
            String url = "/api/basic/warehouses";
            String json = "{\"name\":\""+storename+"\",\"creationTime\":\"\",\"editable\":true}";
            System.out.println(json);
            String actual = appUserService.newstore(url, json);
            //获取数据库最新快递公司信息
            Map<String, Object> map = appUserMapper.getstore(storename);
            String Name = String.valueOf(map.get("Name"));
            System.out.println(Name);
            Assertion.verifyEquals(Name,storename,"验证数据库是否成功添加数值");
        }

        //查询仓库
        public void selectstore(String token) throws Exception {
            String url = "/api/basic/warehouses";
            String actual = appUserService.selectstore(url,token);
        }

        //修改仓库
        public void storerevision(String token) throws Exception {
            Map<String, Object> map = appUserMapper.getstoreid(Id);
            String id = String.valueOf(map.get("Id"));
            System.out.println(id);
            String url = "/api/basic/warehouses/"+id+"";
            String changename=String.valueOf(Random.getRandomChar());
            System.out.println("changename"+changename);
            String json = "{\"name\":\""+changename+"\",\"editable\":true}";
            System.out.println(json);
            String actual = appUserService.storerevision(url, json);
            Map<String, Object> map1 = appUserMapper.getstore(Id);
            String Name = String.valueOf(map1.get("Name"));
            System.out.println("Name的值为"+Name);
            Assertion.verifyEquals(changename,Name,"添加数值修改类型失败");
        }


        //删除仓库
        public void deletestore(String token) throws Exception {
            Map<String, Object> map = appUserMapper.getstoreid(Id);
            String id = String.valueOf(map.get("Id"));
            String url = "/api/basic/warehouses/"+id+"";
            String actual = appUserService.deletestore(url,token);
            Map<String, Object> map1 = appUserMapper.storedestroyTime(destroyTime);
            String destroyTime = String.valueOf(map1.get("destroyTime"));
            if (destroyTime != "") {
                System.out.println("TEST PASS");
            } else {
                Assertion.verifyEquals(0, 1, "删除失败");
            }
        }
}

