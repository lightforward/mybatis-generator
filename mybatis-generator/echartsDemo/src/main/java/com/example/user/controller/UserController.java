package com.example.user.controller;

import com.example.user.aop.MyLog;
import com.example.user.model.User;
import com.example.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 王能顺
 * @Date: 2018-8-23 11:59:35
 *
 * 用户登录注册的控制层
 */
@RestController
@RequestMapping("user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 用户注册方法
     *
     * @param request
     * @return
     */
    @MyLog(value = "用户注册")  //这里添加了AOP的自定义注解
    @RequestMapping(value = "registered",method=RequestMethod.POST)
    public Object registered(HttpServletRequest request) {
        logger.info("logback 访问:注册接口");
        logger.error("logback 访问:注册接口");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        Map<Object, Object> result = new HashMap<Object, Object>();
        if (userName == null || userName.length() == 0 || "".equals(userName)) {
            result.put("resultCode", 500);
            result.put("msg", "用户名不能为空!");
        }else if (password == null || password.length() < 3 || "".equals(password)) {
            result.put("resultCode", 500);
            result.put("msg", "密码长度不能少于三位!");
        }else if (!password.equals(confirmPassword)) {
            result.put("resultCode", 500);
            result.put("msg", "两次密码输入不一致!");
        }else {
            Map<Object, Object> param = new HashMap<Object, Object>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            param.put("userName", userName);
            param.put("passWord", password);
            param.put("registeredTime", simpleDateFormat.format(new Date()));
            int i = userService.addUser(param);
            result.put("resultCode", 200);
            if (i > 0) {
                result.put("msg", "恭喜你注册成功");
            } else {
                result.put("msg", "未知错误,注册失败,请联系管理员");
            }
        }
        return result;
    }

    /**
     * 用户登录
     *
     * @param request
     * @return
     */
    @MyLog(value = "用户登录")  //这里添加了AOP的自定义注解
    @RequestMapping(value = "login",method=RequestMethod.POST)
    public Object login(HttpServletRequest request) {
        logger.error("logback 访问:登录接口");
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        Map<Object, Object> param = new HashMap<Object, Object>();
        param.put("userName", userName);
        param.put("passWord", passWord);
        User user = userService.loginUser(param);
        Map<Object, Object> result = new HashMap<Object, Object>();
        if(user != null) {
            result.put("resultCode", 200);
            result.put("msg", "登录成功");
            setSession(request, user);
        }else {
            result.put("resultCode", 500);
            result.put("msg", "用户名或者密码错误");
        }
        return result;
    }

    public void setSession(HttpServletRequest request, User loginUser){
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        //将数据存储到session中
        session.setAttribute("loginUser", loginUser);
        session.setMaxInactiveInterval(60 * 20); //单位秒
    }
}
