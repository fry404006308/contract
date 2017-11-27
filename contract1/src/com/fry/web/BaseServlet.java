package com.fry.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/7/8.
 */
@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        try {
            // 1 获取方法名字
            String method = req.getParameter("method");
            //System.out.println("method: "+method);
            // 2 获取到当前对象的字节码文件
            Class clazz=this.getClass();
            // 3 拿到字节码对象中的方法
            Method clazzMethod = clazz.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            // 4 执行方法
            clazzMethod.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
