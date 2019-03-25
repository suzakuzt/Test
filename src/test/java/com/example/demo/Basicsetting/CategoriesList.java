package com.example.demo.Basicsetting;
import com.example.demo.mapper.AppUserMapper;
import com.example.demo.service.AppUserService;

import com.example.demo.util.Assertion;
import com.example.demo.util.Random;
import com.example.demo.util.Regexparam;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.Map;

@SpringBootTest
@Listeners({com.example.demo.util.AssertionListener.class})
    public class CategoriesList extends AbstractTestNGSpringContextTests {
    String token;
    String MobileNumber2 = Random.randommobile();
    String categorname = String.valueOf(Random.getRandomChar());
    String Id;
    String destroyTime;
    @Autowired
    public AppUserService appUserService;
    @Autowired
    private AppUserMapper appUserMapper;

    @Test(description = "测试基本类型的新增修改操作")
    public void Test() throws Exception {
        login();
        newcategories(token);
        selectcategories(token);
        categoriesrevision(token);
        //deletecategories(token);
    }

    @Test(description = "测试基本类型的删除操作")
    public void Test2() throws Exception {
        login();
        deletecategories(token);
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


    //新增类型
    public void newcategories(String token) throws Exception {
        String url = "/api/basic/categories";
        // String actual = appUserService.getdata(url, token);
        // System.out.println("数据列表展现最后的结果值为" + actual);
        String json = "{\"name\":\""+categorname+"\",\"creationTime\":\"\",\"editable\":true}";

        System.out.println(json);
        String actual = appUserService.newcategories(url, json);
        String id;
        Map<String, Object> map = appUserMapper.getCategory(Id);
        String Name = String.valueOf(map.get("Name"));
        System.out.println(Name);
        Assertion.verifyEquals(Name, categorname, "新增类型失败");
    }

    //查询类型
    public void selectcategories(String token) throws Exception {
        String url = "/api/basic/categories";
        String actual = appUserService.selectcategories(url, token);
        String value = "";
        String x = Regexparam.Regextest1(value, actual);
//        JSONArray json = JSONArray.fromObject(x);
//        System.out.println(x);
//        System.out.println("json字符串数字是"+json.size());

        //获取所有的JSON字符串获取对应的数字信息该部分内容不需要删除
//        if (json.size() > 0) {
//            for (int i = 0; i < json.size(); i++) {
//                JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
//                System.out.println(job.get("name"));  // 得到 每个对象中的属性值
//            }
//            int xx=Integer.valueOf(json.size())+1;
//        }
    }

    //修改类型
    public void categoriesrevision(String token) throws Exception {
        Map<String, Object> map = appUserMapper.getCategoryid(categorname);
        String id = String.valueOf(map.get("Id"));
        String url = "/api/basic/categories/" + id + "";
        String changename = String.valueOf(Random.getRandomChar());
        System.out.println("changename" + changename);
        String json = "{\"name\":\"" + changename + "\"}";
        System.out.println(json);
        String actual = appUserService.categoriesrevision(url, json);
        Map<String, Object> map1 = appUserMapper.getCategory(Id);
        String Name = String.valueOf(map1.get("Name"));
        System.out.println(Name);
        Assertion.verifyEquals(changename, Name, "添加数值修改失败");
    }


    //删除类型
    public void deletecategories(String token) throws Exception {
        Map<String, Object> map = appUserMapper.getCategoryid(categorname);
        String id = String.valueOf(map.get("Id"));
        int y = Integer.valueOf(id);
        String url = "/api/basic/categories/" + id + "";
        String actual = appUserService.deletecategories(url, token);
        System.out.println(actual);
        Map<String, Object> map1 = appUserMapper.categoriesdestroyTime(destroyTime);
        String destroyTime = String.valueOf(map1.get("destroyTime"));
        if (destroyTime != "") {
            System.out.println("TEST PASS");
        } else {
            Assertion.verifyEquals(0, 1, "删除失败");
        }
//            Map<String, Object> map1 = appUserMapper.getCategoryid(categorname);
//            String id1 = String.valueOf(map1.get("Id"));
//            int x =Integer.valueOf(id1);
//            if(x!=y)
//             System.out.println("TEST PASS");
//         else
//             Assertion.verifyEquals(0,1,"删除测试失败");
//     }
    }
}














