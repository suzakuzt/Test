package com.example.demo.service.impl;

import com.example.demo.config.Url;
import com.example.demo.mapper.AppUserMapper;
import com.example.demo.model.Activetest;
import com.example.demo.service.AppUserService;
import com.example.demo.util.HttpClient;
import com.example.demo.util.Regexparam;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.demo.util.HttpClient.convertStringParamter;

//具体业务逻辑
@Service
public class AppUserServiceImpl implements AppUserService {
    String token;

    @Autowired
    private AppUserMapper appUserMapper;

    //获取TOKRN操作
//    @Override
//    public String Token(String urltest) throws Exception {
//        String url = Url.DEFFS_URL + urltest;
//        Map<String,Object> params=new HashMap<String, Object>();
//        params.put("appid","AppId1808109284");
//        params.put("secret","AE764E5669C5B3F77817442CF3D1BC5D1ACDBF32");
//        params.put("grant_type","client_credentials");
//        String   s = HttpClient.sendHttpGetNotoken(url,params);
//        System.out.println(s);
//        token = Regexparam.regex(s);
//        return(s);
//    }
//    @Override
//    public String Tokenapp(String urltest) throws Exception {
//        Map<String, Object> params = new HashMap<String, Object>();
//        String url = Url.phoneurl + urltest;
//        String s = HttpClient.sendHttpGetNotoken(url, params);
//        System.out.println(s);
//        String value = "";
//        String tokenapp = Regexparam.Regextest(value,s);
//        return tokenapp;
//    }


//    @Override
//    public List<Activetest> list() {
//        Activetest activetest = new Activetest();
//        String keys[] = {"id"};
//        String values[] = {"5b90e5b3dae9882f345ea31d"};
//        List<Activetest> objects = (List<Activetest>) MongodbUtils.findAll(activetest);
//        for (int i = 0; i < objects.size(); i++) {
//            Activetest activetest1 = objects.get(i);
//            System.out.print(activetest1.toString());
//        }
//        return null;
//    }

    //  登录接口
    @Override
    public String login(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPostJson(url, json);
        System.out.println(s);
        String value = "";
        String tokenlogin = Regexparam.Regextest(value, s);
        //String token;
        System.out.println("tokenlogin的值为" + tokenlogin);
        return (tokenlogin);
    }


    //    /*
//     * 4.获取数据素材库列表值
//     */
//    public String getdata(String urltest,String token) throws Exception {
//        String url = Url.phoneurl + urltest;
//        System.out.println(url);
//        String s = HttpClient.sendHttpGet(url, token);
//        return (s);
//    }
//
//    /*
//     * 5.获取访客接口
//     */
//    public String getvistor(String urltest,String token) throws Exception {
//        String url = Url.phoneurl + urltest;
//        System.out.println(url);
//        String s = HttpClient.sendHttpGet(url, token);
//        return (s);
//    }
    /*
     *新增品牌接口测试
     **/
    @Override
    public String newbrand(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPostJson(url, json);
        System.out.println(s);
        return (s);
    }

    /*
     *查询品牌
     **/
    @Override
    public String selectbrand(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpGet(url, token);
        System.out.println(s);
        return (s);
    }

    /*
     *修改品牌接口测试
     **/
    @Override
    public String brandrevision(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPutJson(url, json);
        System.out.println(s);
        return (s);
    }


    /*
     *删除品牌接口测试
     **/
    @Override
    public String deletebrand(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpDelete(url, token);
        System.out.println(s);
        return (s);
    }

    /*
     *新增仓库接口测试
     **/
    @Override
    public String newstore(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPostJson(url, json);
        System.out.println(s);
        return (s);
    }

    /*
     *查询仓库
     **/
    @Override
    public String selectstore(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpGet(url, token);
        System.out.println(s);
        return (s);
    }

    /*
     *修改仓库接口测试
     **/
    @Override
    public String storerevision(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPutJson(url, json);
        System.out.println(s);
        return (s);
    }


    /*
     *删除仓库接口测试
     **/
    @Override
    public String deletestore(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpDelete(url, token);
        System.out.println(s);
        return (s);
    }


    /*
     *新增类型接口测试
     **/
    @Override
    public String newcategories(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPostJson(url, json);
        System.out.println(s);
        return (s);
    }

    /*
     *查询类型接口测试
     **/
    @Override
    public String selectcategories(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpGet(url, token);
        System.out.println(s);
        return (s);
    }

    /*
     *修改类型接口测试
     **/
    @Override
    public String categoriesrevision(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPutJson(url, json);
        System.out.println(s);
        return (s);
    }


    /*
     *删除类型接口测试
     **/
    @Override
    public String deletecategories(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpDelete(url, token);
        System.out.println(s);
        return (s);
    }


    /*
     *新增快递公司接口测试
     **/
    @Override
    public String newparcel(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPostJson(url, json);
        System.out.println(s);
        return (s);
    }

    /*
     *查询快递公司类型
     **/
    @Override
    public String selectparcel(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpGet(url, token);
        System.out.println(s);
        return (s);
    }

    /*
     *修改快递公司接口测试
     **/
    @Override
    public String parcelrevision(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPutJson(url, json);
        System.out.println(s);
        return (s);
    }


    /*
     *删除快递公司接口测试
     **/
    @Override
    public String deleteparcel(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpDelete(url, token);
        System.out.println(s);
        return (s);
    }


    /*
     *查询范围接口
     **/
    @Override
    public String selectareas(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpGet(url, token);
        System.out.println(s);
        return (s);
    }


    /*
     *新增渠道接口测试
     **/
    @Override
    public String newchannel(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPostJson(url, json);
        System.out.println(s);
        return (s);

    }

    /*
     *查询渠道接口
     **/
    @Override
    public String selectchannel(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpGet(url, token);
        System.out.println(s);
        return (s);
    }


    /*
     *修改渠道接口测试
     **/
    @Override
    public String channelrevision(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPutJson(url, json);
        System.out.println(s);
        return (s);
    }


    /*
     *删除渠道接口测试
     **/
    @Override
    public String deletechannel(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpDelete(url, token);
        System.out.println(s);
        return (s);
    }


    /*
     *获取获取产品信息列表
     **/
    @Override
    public String selectopilist(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpGet(url, token);
        System.out.println(s);
        return (s);
    }

    /*
     *获取产品信息详细信息列表
     **/
    @Override
    public String selectopidetail(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpGet(url, token);
        System.out.println(s);
        return (s);
    }


    /*
     *新增产品接口测试
     **/
    @Override
    public String newproduct(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPostJson(url, json);
        System.out.println(s);
        return (s);
    }


    /*
     *获取单个产品信息详细信息接口测试
     **/
    @Override
    public String productdetail(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpGet(url, token);
        System.out.println(s);
        return (s);
    }


    /*
     *修改产品接口测试
     **/
    @Override
    public String productrevision(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPutJson(url, json);
        System.out.println(s);
        return (s);
    }


    /*
     *删除产品接口测试
     **/
    @Override
    public String deleteproduct(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpDelete(url, token);
        System.out.println(s);
        return (s);
    }


    /*
     *获取产品SKU列表
     **/
    @Override
    public String selectpsku(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpGet(url, token);
        System.out.println(s);
        return (s);
    }


    /*
     *新增产品SKU
     **/
    @Override
    public String newpsku(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPostJson(url, json);
        System.out.println(s);
        return (s);
    }


    /*
     *修改产品SKU
     **/
    @Override
    public String pskurevision(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPutJson(url, json);
        System.out.println(s);
        return (s);
    }


    /*
     *删除产品SKU
     **/
    @Override
    public String deletepsku(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpDelete(url, token);
        System.out.println(s);
        return (s);
    }


    /*
     *获取产品营销
     **/
    @Override
    public String selectcampaigns(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpGet(url, token);
        System.out.println(s);
        return (s);
    }


    /*
     *新增产品营销活动
     **/
    @Override
    public String newcampaigns(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPostJson(url, json);
        System.out.println(s);
        return (s);
    }


    /*
     *修改营销活动
     **/
    @Override
    public String campaignsrevision(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPutJson(url, json);
        System.out.println(s);
        return (s);
    }


    /*
     *删除产品营销
     **/
    @Override
    public String deletecampaigns(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpDelete(url, token);
        System.out.println(s);
        return (s);
    }


    /*
     *新增快递规则
     **/
    @Override
    public String newexpressrule(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPostJson(url, json);
        System.out.println(s);
        return (s);
    }


    /*
     *查询快递规则
     **/
    @Override
    public String selectexpressrule(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpGet(url, token);
        System.out.println(s);
        return (s);
    }


    /*
     *修改快递规则
     **/
    @Override
    public String expressrulerevision(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPutJson(url, json);
        System.out.println(s);
        return (s);
    }


    /*
     *删除快递规则
     **/
    @Override
    public String deleteexpressrule(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpDelete(url, token);
        System.out.println(s);
        return (s);
    }


    /*
     *获取面单模板
     **/
    @Override
    public String templates(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpGet(url, token);
        System.out.println(s);
        return (s);
    }


    /*
     *获取订单列表
     **/
    public String selectorder(String urltest, String token) throws Exception {
        String url = Url.URL + urltest;
        System.out.println(url);
        String s = HttpClient.sendHttpGet(url, token);
        System.out.println(s);
        return (s);
    }

    /*
     *修改订单
     **/
    @Override
    public String orderrevision(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPutJson(url, json);
        System.out.println(s);
        return (s);

    }


    /*
     * 修改订单文件映射关系
     **/
    public String modifyOrderMappingRelationship (String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPutJson(url, json);
        System.out.println(s);
        return (s);
    }


    /*
     *订单处理
     **/
    @Override
    public String orderfilehandel(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPostJson(url, json);
        System.out.println(s);
        return (s);
    }

    /*
     *订单导入历史记录
     **/
    @Override
    public String orderfilehistory(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPostJson(url, json);
        System.out.println(s);
        return (s);
    }


    /*
     *订单导入预览
     **/
    @Override
    public String orderfilepreview(String urltest, String json) throws Exception {
        String url = Url.URL + urltest;
        String s = HttpClient.sendHttpPostJson(url, json);
        System.out.println(s);
        return (s);
    }







}