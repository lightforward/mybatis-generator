package com.example.user.controller;

import com.example.user.UserApplication;
import com.example.user.mapper.UserMapper;
import com.example.user.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 单元测试
 *
 * 2018年12月3日18:06:02
 *
 * @Author: 王能顺
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class HelloControllerTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserController userController;

    private MockMvc mockMvc;

    /**
     * 用户登录mapper接口测试方法
     */
    @Test
    public void selectMapper() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<Object,Object> param = new HashMap<Object,Object>();
        param.put("userName", "admin");
        param.put("passWord", "password");
        param.put("registeredTime", simpleDateFormat.format(new Date()));
        User user = userMapper.loginUser(param);
        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(user);
    }


    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    /**
     * 用户登录Contorller接口测试方法
     */
    @Test
    public void Ctest() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/user/login").param("userName", "admin").contentType(MediaType.APPLICATION_FORM_URLENCODED));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====客户端获得反馈数据:" + result);
        // 也可以从response里面取状态码，header,cookies...
//        System.out.println(mvcResult.getResponse().getStatus());
    }


}
