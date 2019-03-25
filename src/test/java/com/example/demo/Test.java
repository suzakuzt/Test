package com.example.demo;


import com.example.demo.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;

@SpringBootTest
@Listeners({com.example.demo.util.ListenerVerify.class})
public class Test  extends AbstractTestNGSpringContextTests {

//    @Autowired
//    private AppUserService appUserService;
//
//    @org.testng.annotations.Test
////    public void test1(){
////        appUserService.list();
////    }
}
