package com.fry.web;


import com.fry.bean.User;
import com.fry.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/7.
 */
@WebServlet(name = "UserServlet",urlPatterns = "/user")
public class UserServlet extends BaseServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request,response);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String method = request.getParameter("method");
//        if ("login".equals(method)){
//            login(request,response);
//        }else if("register".equals(method)){
//            register(request,response);
//        }
//    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        UserService userService=new UserService();
        User user=null;
        try {
            //调用service中登录方法
            user = userService.login(name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user!=null){
            //登录成功后我们再获取是否保存密码的信息，如果失败了保存密码就没有意义了
            String remember = request.getParameter("remember");
            if (remember!=null&&remember.equals("yes")){
                // 将用户名和密码加入到cookie中
                Cookie nameCookie = new Cookie("name", name);
                Cookie passwordCookie = new Cookie("password", password);
                //设置cookie的有效期 防止销毁
                nameCookie.setMaxAge(60*60*24);
                passwordCookie.setMaxAge(60*60*24);
                //将cookie发送给客户端保存
                response.addCookie(nameCookie);
                response.addCookie(passwordCookie);

            }
              request.getSession().setAttribute("user",user);
            //登录成功跳转种类列表界面

            //response.sendRedirect(request.getContextPath()+"/category?method=getCategoryList&currentPage=1&currentCount=10");
            response.sendRedirect(request.getContextPath()+"/contract?method=getContractList&currentPage=1&currentCount=10");
        }else {
            //登录失败提示
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("用户登录失败");

        }

    }
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> parameterMap = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        UserService userService=new UserService();

        boolean register = userService.register(user);
        if (register) {
            response.sendRedirect(request.getContextPath()+"login.jsp");
        }else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("注册失败");
        }
    }

}
