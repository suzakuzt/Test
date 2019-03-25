package com.example.demo.ProductManage;

import com.example.demo.Basicsetting.CategoriesList;
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
public class ProductSetting extends AbstractTestNGSpringContextTests{
    String Id;
    String token;
    String productname = String.valueOf(Random.getRandomChar());
    String productskuname = String.valueOf(Random.getRandomChar());
    String skucode = Random.randomorder();
    String campaignname =String.valueOf(Random.getRandomChar());
    String destroyTime;
    @Autowired
    public AppUserService appUserService;
    @Autowired
    private AppUserMapper appUserMapper;

    @Test(description = "快递规则删除操作")
    public void Test() throws Exception {
        login();
        selectopilist(token);
        selectopidetail(token);
        newproduct(token);
        productdetail(token);
        productrevision(token);
    }


    @Test
    public void Test2() throws Exception {
        login();
        newpsku(token);
        selectpsku(token);
         pskurevision(token);
        //deletepsku(token);
    }

    @Test
    public void Test3() throws Exception {
        login();
        selectcampaigns(token);
        newcampaigns(token);
        campaignsrevision(token);
    }


    @Test
    public void Test4() throws Exception {
        login();
        newproduct(token);
        newcampaigns(token);
        newpsku(token);
        deletecampaigns(token);
        deletepsku(token);
        deleteproduct(token);
    }





    /*
     *
     * 产品基本信息
     * 列表增删改查
     * 产品详细信息展示
     *
     * */
    //登录获取TOKEN值,并验证登录功能是否正确
    public void login() throws Exception {
        String url = "/api/users/login";
        String json = "{\"loginAccount\":\"user2\",\"loginPwd\":\"cpp2017\"}";
        System.out.println(json);
        String actual = appUserService.login(url, json);
        token = "Bearer " + actual + "";
    }


    //获取产品信息列表
    public void selectopilist(String token) throws Exception {
        String url = "/api/products";
        String actual = appUserService.selectopilist(url,token);
    }


    //获取产品信息详细信息列表
    public void selectopidetail(String token) throws Exception {
        String url = "/api/products/detail";
        String actual = appUserService.selectopidetail(url,token);
    }


    //新增产品
    public void newproduct(String token) throws Exception {
        Map<String, Object> map = appUserMapper.getBrandid(Id);
        String brandid = String.valueOf(map.get("Id"));
        Map<String, Object> map1 = appUserMapper.getCategoryid(Id);
        String categoryid = String.valueOf(map1.get("Id"));
        String url = "/api/products";
        String json = "{\"name\":\""+productname+"\",\"categoryId\":\""+categoryid+"\",\"brandId\":\""+brandid+"\",\"storeType\":\"0\",\"distributionMode\":\"0\"}";
        System.out.println(json);
        String actual = appUserService.newproduct(url, json);
        //获取数据库最新产品信息
        Map<String, Object> map2 = appUserMapper.getproduct(productname);
        String Name = String.valueOf(map2.get("Name"));
        System.out.println(Name);
        Assertion.verifyEquals(Name,productname,"验证数据库是否成功添加数值");
    }


    //获取产品信息详细信息
    public void productdetail(String token) throws Exception {
        Map<String, Object> map2 = appUserMapper.getproductid(productname);
        String id = String.valueOf(map2.get("Id"));
        String url = "/api/products/"+id+"";
        String actual = appUserService.productdetail(url,token);
    }


    //修改产品
    public void productrevision(String token) throws Exception {
       //数据库获取对应的字段数据信息
        Map<String, Object> map2 = appUserMapper.getproductid(productname);
        String id = String.valueOf(map2.get("Id"));
        String url = "/api/products/"+id+"";
        Map<String, Object> map = appUserMapper.getBrandid(Id);
        String brandid = String.valueOf(map.get("Id"));
        Map<String, Object> map1 = appUserMapper.getCategoryid(Id);

        String categoryid = String.valueOf(map1.get("Id"));
        String changename=String.valueOf(Random.getRandomChar());

        System.out.println("changename"+changename);
        String json = "{\"name\":\""+changename+"\",\"categoryId\":\""+categoryid+"\",\"brandId\":\""+brandid+"\",\"storeType\":\"0\",\"distributionMode\":\"0\"}";
        System.out.println(json);
        String actual = appUserService.productrevision(url, json);
        Map<String, Object> map3 = appUserMapper.getproduct(Id);
        String Name = String.valueOf(map3.get("Name"));
        System.out.println("Name的值为"+Name);
        Assertion.verifyEquals(changename,Name,"添加数值修改类型失败");

    }


    //删除产品
    public void deleteproduct(String token) throws Exception {
        Map<String, Object> map3 = appUserMapper.getproduct(Id);
        String Name = String.valueOf(map3.get("Name"));
        System.out.println("Name的值为"+Name);
        Map<String, Object> map2 = appUserMapper.getproductid(productname);
        String id = String.valueOf(map2.get("Id"));
        String url = "/api/products/"+id+"";
        String actual = appUserService.deleteproduct(url,token);
        Map<String, Object> map1 = appUserMapper.productdestroyTime(destroyTime);
        String destroyTime = String.valueOf(map1.get("destroyTime"));
        if (destroyTime != "") {
            System.out.println("TEST PASS");
        } else {
            Assertion.verifyEquals(0, 1, "产品删除失败");
        }

    }



         /*
         *
         * 产品SKU
         * 基本的增删改查信息
         *
         * */
         //新增SKU
         public void newpsku(String token) throws Exception {
             Map<String, Object> map = appUserMapper.getproductid(productname);
             String id = String.valueOf(map.get("Id"));
             String url = "/api/products/skus";

             Map<String, Object> mapwarehouse = appUserMapper.getstoreid(Id);
             String warehouseid = String.valueOf(mapwarehouse.get("Id"));
             String json = "{\"name\":\""+productskuname+"\",\"productId\":\""+id+"\",\"skuCode\":\""+skucode+"\",\"warehouseId\":\""+warehouseid+"\",\"weight\":\"66\",\"expressRuleId\":\"1\"}";
             System.out.println(json);
             String actual = appUserService.newpsku(url, json);
             Map<String, Object> mapproductsku = appUserMapper.getproductsku(productskuname);
             String Name = String.valueOf(mapproductsku.get("Name"));
             System.out.println(Name);
             Assertion.verifyEquals(Name,productskuname,"验证数据库是否成功添加数值");
         }


        //获取产品SKU列表
        public void selectpsku(String token) throws Exception {
            String url = "/api/products/skus";
            String actual = appUserService.selectpsku(url,token);
        }


        //修改产品SKU
        public void pskurevision(String token) throws Exception {
            //获取数据库中的SKU最新值
             Map<String, Object> map = appUserMapper.getproductskuid(productname);
            String id = String.valueOf(map.get("Id"));
            System.out.println("ID的值为"+id);
            Map<String, Object> map2 = appUserMapper.getproductsku(Id);
            String name = String.valueOf(map2.get("Name"));
            System.out.println("NAME的值为"+name);

            Map<String, Object> mapwarehouse = appUserMapper.getstoreid(Id);
            String warehouseid = String.valueOf(mapwarehouse.get("Id"));

            String url = "/api/products/skus/"+id+"";
            String changename=String.valueOf(Random.getRandomChar());
            System.out.println("changename"+changename);
            String json = "{\"name\":\""+changename+"\",\"warehouseId\":\""+warehouseid+"\",\"weight\":\"3\",\"expressRuleId\":\"1\"}";
            System.out.println(json);
            String actual = appUserService.pskurevision(url, json);
            Map<String, Object> mapproductsku = appUserMapper.getproductsku(productskuname);
            String Name = String.valueOf(mapproductsku.get("Name"));
            System.out.println(Name);
            Assertion.verifyEquals(changename,Name,"验证数据库是否成功修改数值");
        }


        //删除产品SKU
        public void deletepsku(String token) throws Exception {
            Map<String, Object> map = appUserMapper.getproductskuid(productname);
            String id = String.valueOf(map.get("Id"));
            String url = "/api/products/skus/"+id+"";
            String actual = appUserService.deletepsku(url,token);
            Map<String, Object> map1 = appUserMapper.pskudestroyTime(destroyTime);
            String destroyTime = String.valueOf(map1.get("destroyTime"));
            if (destroyTime != "") {
                System.out.println("TEST PASS");
            } else {
                Assertion.verifyEquals(0, 1, "产品SKU删除失败");
            }
        }


        /*
         *
         * 获取产品营销活动
         * campaign
         *
         * */
        //获取营销列表
        public void selectcampaigns(String token) throws Exception {
            String url = "/api/products/campaigns";
            String actual = appUserService.selectcampaigns(url,token);

        }


        //新增营销活动
        public void newcampaigns(String token) throws Exception {
            Map<String, Object> map2 = appUserMapper.getproductid(productname);
            String id = String.valueOf(map2.get("Id"));
            String outskucode = Random.randomorder();
            String url = "/api/products/campaigns";
            String json = "{\"name\":\""+campaignname+"\",\"channelSKUCode\":\""+outskucode+"\",\"productId\":\""+id+"\"}";
            System.out.println(json);
            String actual = appUserService.newcampaigns(url, json);
            Map<String, Object> mapcampaign = appUserMapper.getcampaigns(campaignname);
            String Name = String.valueOf(mapcampaign.get("Name"));
            System.out.println(Name);
            Assertion.verifyEquals(Name,campaignname,"验证数据库是否成功添加数值");
        }


        //修改营销活动
        public void campaignsrevision(String token) throws Exception {
            Map<String, Object> mapcampaign = appUserMapper.getcampaignsid(Id);
            String id = String.valueOf(mapcampaign.get("Id"));
            System.out.println(id);
            String url = "/api/products/campaigns/"+id+"";
            String changename=String.valueOf(Random.getRandomChar());
            String json = "{\"name\":\""+changename+"\"}";
            System.out.println(json);
            String actual = appUserService.campaignsrevision(url, json);
            Map<String, Object> mapcampaigns = appUserMapper.getcampaigns(campaignname);
            String Name = String.valueOf(mapcampaigns.get("Name"));
            System.out.println(Name);
            Assertion.verifyEquals(Name,changename,"验证数据库是否成功添加数值");

        }


        //删除营销活动
        public void deletecampaigns(String token) throws Exception {
            Map<String, Object> mapcampaign = appUserMapper.getcampaignsid(Id);
            String id = String.valueOf(mapcampaign.get("Id"));
            System.out.println(id);
            String url = "/api/products/campaigns/"+id+"";
            String actual = appUserService.deletecampaigns(url,token);
            Map<String, Object> map1 = appUserMapper.campaignsdestroyTime(destroyTime);
            String destroyTime = String.valueOf(map1.get("destroyTime"));
            if (destroyTime != "") {
                System.out.println("TEST PASS");
            } else {
                Assertion.verifyEquals(0, 1, "营销产品删除失败");
            }
        }




















}