package com.fry.web;

import com.fry.bean.Contract;
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
    public void addDevice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 获取参数 通过BeanUtils封装实体类
            Map<String, String[]> parameterMap = request.getParameterMap();
            Device device=new Device();
            BeanUtils.populate(device,parameterMap);

            Contract contract = new Contract();
            BeanUtils.populate(contract,parameterMap);
            request.setAttribute("contract",contract);

            DeviceService deviceService=new DeviceService();
            boolean addDevice = deviceService.addDevice(device);
            if (addDevice){
                //添加成功
                response.setStatus(201);
                request.getRequestDispatcher("/device?method=queryDevice").forward(request,response);
//                request.getRequestDispatcher("/device?method=queryDevice&c_id="+contract.getC_id()+"&c_type="+contract.getC_type()).forward(request,response);
            }else {
                // 添加失败
                response.setStatus(600);
                request.getRequestDispatcher("/device?method=queryDevice").forward(request,response);
//                request.getRequestDispatcher("/device?method=queryDevice&c_id="+contract.getC_id()+"&c_type="+contract.getC_type()).forward(request,response);
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
    public void updateDevice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 获取参数 通过BeanUtils封装实体类
            Map<String, String[]> parameterMap = request.getParameterMap();
            Device device=new Device();
            BeanUtils.populate(device,parameterMap);

            Contract contract = new Contract();
            BeanUtils.populate(contract,parameterMap);
            request.setAttribute("contract",contract);

            DeviceService deviceService=new DeviceService();
            boolean updateDevice = deviceService.updateDevice(device);
            String message = "";
            if (updateDevice){
                // 修改成功后重定向到列表界面
                //response.sendRedirect(request.getContextPath()+"/device?method=queryDevice");
                request.getRequestDispatcher("/device?method=queryDevice").forward(request,response);
            }else {
                // 失败了直接提示
                //response.setContentType("text/html;charset=utf-8");
                //response.getWriter().write("修改失败");
                message = "修改失败!!";
                request.setAttribute("message",message);
                request.getRequestDispatcher("/error.jsp").forward(request, response);
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
    public void deleteDevice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 获取参数 通过BeanUtils封装实体类
            Map<String, String[]> parameterMap = request.getParameterMap();
            Device device=new Device();
            BeanUtils.populate(device,parameterMap);

            Contract contract = new Contract();
            BeanUtils.populate(contract,parameterMap);
            request.setAttribute("contract",contract);

            DeviceService deviceService=new DeviceService();
            boolean deleteDevice = deviceService.deleteDevice(device);
            if (deleteDevice){
                // 修改成功后重定向到列表界面
                //response.sendRedirect(request.getContextPath()+"/device?method=queryDevice");
                request.getRequestDispatcher("/device?method=queryDevice").forward(request,response);
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
    public void queryDevice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            Contract contract=(Contract)request.getAttribute("contract");
            if(contract==null){
                // 获取参数 通过BeanUtils封装实体类
                Map<String, String[]> parameterMap = request.getParameterMap();
                contract=new Contract();
                BeanUtils.populate(contract,parameterMap);

                request.setAttribute("contract",contract);

            }

            DeviceService deviceService=new DeviceService();
            List<Device> devices=deviceService.queryDevice(contract.getC_id());
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
