package com.example.user.fileter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: 王能顺
 * @Date: 2018-8-23 11:59:14
 *
 * 过滤器
 *
 */
//@WebFilter(filterName = "loginSessionFilter",urlPatterns = {"/*"})
public class LoginFileter implements Filter {

    private final static Logger logger = LoggerFactory.getLogger(LoginFileter.class);

    //标示符：表示当前用户未登录(可根据自己项目需要改为json样式)
    String NO_LOGIN = "您还未登录";

    //不需要登录就可以访问的路径(比如:注册登录等)
    String[] includeUrls = new String[]{"/user/login.do","/user/registered.do"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 获取当前request中的LoginSession,判断用户是否登录
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();
        logger.info("filter url:"+uri);
        //是否需要过滤
        boolean needFilter = isNeedFilter(uri);
        if (true) { //不需要过滤直接传给下一个过滤器
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // session中包含user对象,则是登录状态
            if(session!=null&&session.getAttribute("loginUser") != null){
                //System.out.println("=====" + "user:"+session.getAttribute("user"));
                filterChain.doFilter(request, response);
            }else{
                String requestType = request.getHeader("X-Requested-With");
                //判断是否是ajax请求
                if(requestType!=null && "XMLHttpRequest".equals(requestType)){
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write(this.NO_LOGIN);
                }else{
                    //重定向到登录页(需要在static文件夹下建立此html文件)
                    response.sendRedirect(request.getContextPath()+"/html/login.html");
                }
                return;
            }
        }

    }

    @Override
    public void destroy() {

    }

    /**
     * @Description: 是否需要过滤
     * @param uri
     */
    public boolean isNeedFilter(String uri) {

        // 之过滤.do结尾的请求
        if(uri !=null && !"".equals(uri) && uri.length() > 3){
            String str = uri.substring(uri.length()-3,uri.length());
            if(!".do".equals(str)){
                return true;
            }else {
                for (String includeUrl : includeUrls) {
                    if(includeUrl.equals(uri)) {
                        return true;
                    }
                }
            }
        }
        return false;
        //return true;
    }
}
