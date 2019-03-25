package com.example.demo.RoleManage;
import com.example.demo.mapper.AppUserMapper;
import com.example.demo.service.AppUserService;

import com.example.demo.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import java.util.Map;
@SpringBootTest
public class Rolesetting {

    String token;
    String token1;
    String MobileNumber2 = Random.randommobile();
    @Autowired
    public AppUserService appUserService;
    @Autowired
    private AppUserMapper appUserMapper;

    @Test
    public void Test() throws Exception {
        //verify();
        //login();
        // data(token);
    }

    /*
     * 获取权限列表
     * 新增角色
     * 修改角色
     * 删除角色
     * */
    //获取产品信息列表
//    public void selectauths(String token) throws Exception {
//        String url = "/api/basic/auths";
//        // String actual = appUserService.getdata(url, token);
//        // System.out.println("数据列表展现最后的结果值为" + actual);
//        String actual = appUserService.selectauths(url,token);
//        // Map<String, Object> map = appUserMapper.getProposal(orderNo);
//        // ProposalNo = String.valueOf(map.get("ProposalNo"));
//    }
//
//
//   //新增角色
//   public void newrole(String token) throws Exception {
//       String url = "/api/roles";
//       // String actual = appUserService.getdata(url, token);
//       // System.out.println("数据列表展现最后的结果值为" + actual);splitOrderWeight
//       String json = "{\"name\":\"宝宝来了仓库\",\"auth\":\"{auth}\"}";
//       System.out.println(json);
//       String actual = appUserService.newrole(url, json);
//       //Map<String, Object> map = appUserMapper.getProposal(orderNo);
//       // ProposalNo = String.valueOf(map.get("ProposalNo"));
//   }
//
//    //获取角色列表
//    public void selectroles(String token) throws Exception {
//        String url = "/api/roles?page=1&pageCount=10";
//        // String actual = appUserService.getdata(url, token);
//        // System.out.println("数据列表展现最后的结果值为" + actual);
//        String actual = appUserService.selectroles(url,token);
//        // Map<String, Object> map = appUserMapper.getProposal(orderNo);
//        // ProposalNo = String.valueOf(map.get("ProposalNo"));
//    }
//
//
//    //获取角色详情
//    public void selectrolesdetail(String token) throws Exception {
//        String url = "/api/roles/1";
//        // String actual = appUserService.getdata(url, token);
//        // System.out.println("数据列表展现最后的结果值为" + actual);
//        String actual = appUserService.deleterole(url,token);
//        // Map<String, Object> map = appUserMapper.getProposal(orderNo);
//        // ProposalNo = String.valueOf(map.get("ProposalNo"));
//    }
//
//
//
//
//    //修改角色
//    public void rolerevision(String token) throws Exception {
//        String url = "/api/roles";
//        // String actual = appUserService.getdata(url, token);
//        // System.out.println("数据列表展现最后的结果值为" + actual);splitOrderWeight
//        String json = "{\"name\":\"宝宝来了仓库\",\"auth\":\"{auth}\"}";
//        System.out.println(json);
//        String actual = appUserService.rolerevision(url, json);
//        //Map<String, Object> map = appUserMapper.getProposal(orderNo);
//        // ProposalNo = String.valueOf(map.get("ProposalNo"));
//    }
//
//
//
//    //删除角色
//    public void deleterole(String token) throws Exception {
//        String url = "/roles/{id}";
//        // String actual = appUserService.getdata(url, token);
//        // System.out.println("数据列表展现最后的结果值为" + actual);
//        String actual = appUserService.deleterole(url,token);
//        // Map<String, Object> map = appUserMapper.getProposal(orderNo);
//        // ProposalNo = String.valueOf(map.get("ProposalNo"));
//    }






}
