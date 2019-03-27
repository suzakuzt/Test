package com.example.demo.Basicsetting;

import com.example.demo.config.Url;
import com.example.demo.mapper.AppUserMapper;
import com.example.demo.service.AppUserService;
import com.example.demo.util.Assertion;
import com.example.demo.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.example.demo.util.Upload;
import java.util.Map;

@SpringBootTest
@Listeners({com.example.demo.util.AssertionListener.class})
public class OrderfileManage extends AbstractTestNGSpringContextTests {
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
        //selectorder(token);

    }

    //登录获取TOKEN值,并验证登录功能是否正确
    public void login() throws Exception {
        String url = "/api/users/login";
        String json = "{\"loginAccount\":\"user2\",\"loginPwd\":\"cpp2017\"}";
        System.out.println(json);
        String actual = appUserService.login(url, json);
        token = "Bearer " + actual + "";
    }


    //上传订单文件
    public void selectorderdetail(String token) throws Exception {
        String url = "http://121.40.197.16:3000/api/file/channel/upload";
        String file="F:\\360MoveData\\Users\\29615\\Desktop\\file\\resource\\顺丰导入模板.xls";
        String type="";
        String actual= Upload.httpUpLoad(url,file,type);
        System.out.println(actual);


        //获取数据库最新添加的品牌
//            Map<String, Object> map = appUserMapper.getchannel(Id);
//            String Name = String.valueOf(map.get("Name"));
//            System.out.println(Name);
//            Assertion.verifyEquals(Name,channelname,"验证数据库是否成功添加数值");
    }




    //订单导入处理
    public void orderfilehandel(String token) throws Exception {
        String url = "/api/basic/channels";
        String json = "{\"taskIds\":\"{taskIds}\"}";
        System.out.println(json);
        String actual = appUserService.orderfilehandel(url, json);
//        //获取数据库最新添加的品牌
//        Map<String, Object> map = appUserMapper.getchannel(Id);
//        String Name = String.valueOf(map.get("Name"));
//        System.out.println(Name);
//        Assertion.verifyEquals(Name,channelname,"验证数据库是否成功添加数值");
    }



    //订单导入历史记录列表
    public void orderfilehistory(String token) throws Exception {
//        Map<String, Object> map = appUserMapper.getchannel(Id);
//        String Name = String.valueOf(map.get("Name"));
//        System.out.println(Name);
        String url = "/api/task/histories?brandcode=?&channelcode=?&status=?&startdate=?&enddate=?&page=?&pagecount=?";
        String actual = appUserService.orderfilehistory(url,token);
    }



    //上传文件预览

    public void orderfilepreview(String token) throws Exception {
//        Map<String, Object> map = appUserMapper.getchannel(Id);
//        String Name = String.valueOf(map.get("Name"));
//        System.out.println(Name);
        String url = "/api/task/review/tempOrder/{taskrecordid}?page=1&pageCount=10";
        String actual = appUserService.orderfilepreview(url,token);
    }



    //回执文件下载
    public void orderfiledownload(String token) throws Exception {
//        Map<String, Object> map = appUserMapper.getchannel(Id);
//        String Name = String.valueOf(map.get("Name"));
//        System.out.println(Name);
        String url = "/api/task/review/tempOrder/{taskrecordid}?page=1&pageCount=10";
        String actual = appUserService.orderfilepreview(url,token);
    }



    //导入快递文件
    public void uploadParcelFile() throws Exception {
        String url = Url.URL + "/api/file/express/upload";
        String file = "C:\\Users\\Pactera\\IdeaProjects\\Test\\src\\main\\java\\com\\example\\demo\\util"
                + "顺丰导入模板.xls";
        String type = " ";
        Upload.httpUpLoad(url, file, type);
        //后续做对比
    }


    //导出快递文件
    public void downloadParcelFile() throws Exception {
        //需要一块时间来写Download.httpDownLoad的逻辑
    }



}
