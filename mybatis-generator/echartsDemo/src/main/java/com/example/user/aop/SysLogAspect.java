package com.example.user.aop;

import com.alibaba.fastjson.JSON;
import com.example.user.model.SysLog;
import com.example.user.model.User;
import com.example.user.utils.HttpContextUtils;
import com.example.user.utils.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.ArrayUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: 王能顺
 * @Date: 2019年10月23日11:31:06
 *
 * 系统日志：切面处理类
 */
@Aspect
@Component
public class SysLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

//    @Autowired
//    private SysLogService sysLogService;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation( com.example.user.aop.MyLog)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        logger.info("切面。。。。。");
        //保存日志
        SysLog sysLog = new SysLog();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            String value = myLog.value();
            //保存获取的操作
            sysLog.setOperation(value);
        }
        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        // 返回的数组中携带有Request或者Response对象，导致序列化异常,需要过滤掉
        Stream<Object> stream = ArrayUtils.isEmpty(args) ? Stream.empty() : Arrays.asList(args).stream();
        List<Object> logArgs = stream
                .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)))
                .collect(Collectors.toList());
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(logArgs);
        sysLog.setParams(params);
        sysLog.setCreateDate(new Date());
        //获取用户名
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        sysLog.setUsername(loginUser.getUserName());
        //获取用户ip地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        System.out.print(sysLog.toString());
        logger.info(sysLog.toString());
        //调用service保存SysLog实体类到数据库
//        sysLogService.save(sysLog);
    }
}
