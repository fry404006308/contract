package com.fry.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter",urlPatterns = "/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1、获取request和response对象
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

//        String reqUrl = request.getRequestURL().toString();
//        String uncheckUrl=request.getContextPath()+"/login.jsp";
//        String reqURI = request.getRequestURI();
        String servletPath = request.getServletPath();


        //2、如果是登陆界面，直接跳过
        if(servletPath.equals("/login.jsp")||servletPath.equals("/user")){
            chain.doFilter(req, resp);
            return;
        }
//        System.out.println("reqUrl: "+reqUrl);
//        System.out.println("uncheckUrl: "+uncheckUrl);
//        System.out.println("servletPath: "+servletPath);
//        System.out.println("reqURI: "+reqURI);

        //3、取userSessionKey,登录过才有
        String userSessionKey=null;
        userSessionKey=(String)request.getSession().getAttribute("USERSESSIONKEY");
//        System.out.println("userSessionKey: "+userSessionKey);

        //4、验证是否登录过
        if (userSessionKey==null){
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return ;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
