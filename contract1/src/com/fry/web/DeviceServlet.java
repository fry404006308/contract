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



            //System.out.println("addDevice  d_cid: "+device.getD_cid());


            DeviceService deviceService=new DeviceService();
            boolean addDevice = deviceService.addDevice(device);
            if (addDevice){
                //添加成功
                response.setStatus(201);
                request.getRequestDispatcher("/device?method=queryDevice&refresh=true").forward(request,response);
//                request.getRequestDispatcher("/device?method=queryDevice&c_id="+contract.getC_id()+"&c_type="+contract.getC_type()).forward(request,response);
            }else {
                // 添加失败
                response.setStatus(600);
                request.getRequestDispatcher("/device?method=queryDevice&refresh=true").forward(request,response);
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

            DeviceService deviceService=new DeviceService();
            boolean updateDevice = deviceService.updateDevice(device);
            System.out.println("device.getD_cid(): "+device.getD_cid());
            String message = "";
            if (updateDevice){
                // 修改成功后重定向到列表界面
                //response.sendRedirect(request.getContextPath()+"/device?method=queryDevice");
                request.getRequestDispatcher("/device?method=queryDevice&refresh=true").forward(request,response);
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

            DeviceService deviceService=new DeviceService();
            boolean deleteDevice = deviceService.deleteDevice(device);
            if (deleteDevice){
                // 修改成功后重定向到列表界面
                //response.sendRedirect(request.getContextPath()+"/device?method=queryDevice");
                request.getRequestDispatcher("/device?method=queryDevice&refresh=true").forward(request,response);
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

            //判断是不是有contract之后的内部刷新
            String refresh = null;
            refresh=request.getParameter("refresh");

            if("true".equals(refresh+"")){
                //System.out.println("refresh11: "+refresh);

            }else{
                //System.out.println("refresh: "+refresh);
                //不是内部刷新就要从contract界面获取数据
                // 获取参数 通过BeanUtils封装实体类
                Map<String, String[]> parameterMap = request.getParameterMap();
                Contract contract=new Contract();
                BeanUtils.populate(contract,parameterMap);
                //获取合同序号
                String contract_id=request.getParameter("contract_id");
                //System.out.println("contract_id: "+contract_id);
                //1、设置合同参数
                request.getSession().setAttribute("contract",contract);
                request.getSession().setAttribute("contract_id",contract_id);


            }

            //从数据库中查询合同对应的设备
            DeviceService deviceService=new DeviceService();
            Contract contract=(Contract)request.getSession().getAttribute("contract");
            System.out.println("contract.getC_id(): "+contract.getC_id());
            List<Device> devices=deviceService.queryDevice(contract.getC_id());
            if (devices!=null) {
                //2、设置设备参数
                request.getSession().setAttribute("devices",devices);

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
