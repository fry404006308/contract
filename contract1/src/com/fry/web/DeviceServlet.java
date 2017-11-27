package com.fry.web;

import com.fry.bean.Device;
import com.fry.service.DeviceService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DeviceServlet",urlPatterns = "/device")
public class DeviceServlet extends BaseServlet {
    //用来处理各种设备相关的请求和返回请求相关的数据
    //增加操作
    protected void addDevice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 获取参数 通过BeanUtils封装实体类
            Map<String, String[]> parameterMap = request.getParameterMap();
            Device device=new Device();
            BeanUtils.populate(device,parameterMap);
            DeviceService deviceService=new DeviceService();
            boolean addDevice = deviceService.addDevice(device);
            if (addDevice){
                //添加成功
                response.setStatus(201);
                request.getRequestDispatcher("/device-add.jsp").forward(request,response);
            }else {
                // 添加失败
                response.setStatus(600);
                request.getRequestDispatcher("/device-add.jsp").forward(request,response);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //更新操作
    protected void updateDevice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 获取参数 通过BeanUtils封装实体类
            Map<String, String[]> parameterMap = request.getParameterMap();
            Device device=new Device();
            BeanUtils.populate(device,parameterMap);
            DeviceService deviceService=new DeviceService();
            boolean updateDevice = deviceService.updateDevice(device);
            String message = "";
            if (updateDevice){
                // 修改成功后重定向到列表界面
                response.sendRedirect(request.getContextPath()+"/device?method=queryDevice");
            }else {
                // 失败了直接提示
                //response.setContentType("text/html;charset=utf-8");
                //response.getWriter().write("修改失败");
                message = "修改失败!!";
                request.setAttribute("message",message);
                request.getRequestDispatcher("/ok.jsp").forward(request, response);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //删除操作
    protected void deleteDevice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 获取参数 通过BeanUtils封装实体类
            Map<String, String[]> parameterMap = request.getParameterMap();
            Device device=new Device();
            BeanUtils.populate(device,parameterMap);
            DeviceService deviceService=new DeviceService();
            boolean deleteDevice = deviceService.deleteDevice(device);
            if (deleteDevice){
                // 修改成功后重定向到列表界面
                response.sendRedirect(request.getContextPath()+"/device?method=queryDevice");
            }else {
                // 失败了直接提示
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("删除失败");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //查询操作
    protected void queryDevice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 获取参数 通过BeanUtils封装实体类
            Map<String, String[]> parameterMap = request.getParameterMap();
            Device device=new Device();
            BeanUtils.populate(device,parameterMap);
            DeviceService deviceService=new DeviceService();
            List<Device> devices=deviceService.queryDevice(device);
            if (devices!=null) {
                request.setAttribute("devices",devices);
                request.getRequestDispatcher("/device-list.jsp").forward(request,response);
            }else {
                request.getRequestDispatcher("/device-list.jsp").forward(request,response);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
