package com.fry.web;


import com.fry.bean.Contract;
import com.fry.bean.Page;
import com.fry.service.ContractService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/8.
 */
@WebServlet(name = "ContractServlet",urlPatterns = "/contract")
public class ContractServlet extends BaseServlet {

    /**
     * @method:addCategory 增加合同
     * @date: 2017/7/8
     * @params:[request, response]
     * @return: void
     */
    public void addContract(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // 获取参数 通过BeanUtils封装实体类
            Map<String, String[]> parameterMap = request.getParameterMap();
            Contract contract=new Contract();
            BeanUtils.populate(contract,parameterMap);
            ContractService contractService=new ContractService();
            //System.out.println("servlet_addTest: "+contract.getC_name()+contract.getC_dtype());
            boolean b = contractService.addContract(contract);
            //获取记录总条数
            int totalCount=contractService.findTotalCount();
            //获得当前所在的页
            Integer currentCount = (Integer)request.getSession().getAttribute("currentCount");
            int currentPage = (int) Math.ceil(1.0*totalCount/currentCount);


            if (b){
                //添加成功
               response.setStatus(201);
               response.sendRedirect(request.getContextPath()+"/contract?method=getContractList&currentPage="+currentPage+"&currentCount="+currentCount);
            }else {
                // 添加失败
                response.setStatus(600);
                response.sendRedirect(request.getContextPath()+"/contract?method=getContractList&currentPage="+currentPage+"&currentCount="+currentCount);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * @method:getCategoryList 查询列表
     * @date: 2017/7/10
     * @params:[request, response]
     * @return: void
     */
    public void getContractList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1 调用service中的查询方法
        try {
            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            int currentCount = Integer.parseInt(request.getParameter("currentCount"));
           // 给分页数据设置默认值
            if (currentCount==0){
                currentCount=10;
            }
            if (currentPage==0){
                currentPage=1;
            }
            //得到当前页记录的起始序号的值-1
            int recordNumber=(currentPage-1)*currentCount;
            request.setAttribute("recordNumber",recordNumber);


            //获取最后一页
            ContractService contractService=new ContractService();
            int lastPage = contractService.getLastPage(currentCount);
            request.getSession().setAttribute("lastPage",lastPage);

            //保证修改和删除合同之后页面在当前位置
            request.getSession().setAttribute("currentPage",currentPage);
            request.getSession().setAttribute("currentCount",currentCount);

            ContractService service=new ContractService();
            Page page = service.findPageContract(currentPage, currentCount);

            if (page!=null) {
                request.setAttribute("page",page);
                //System.out.println(page.getList().get(1).toString());
                request.getRequestDispatcher("/contract-list.jsp").forward(request,response);
            }else {
                request.getRequestDispatcher("/contract-list.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @method:updateContract 修改信息
     * @date: 2017/11/10
     * @params:[request, response]
     * @return: void
     */
    public void updateContract(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1 调用service中的查询方法
            Map<String, String[]> parameterMap = request.getParameterMap();
            Contract contract=new Contract();
            BeanUtils.populate(contract,parameterMap);
            ContractService service=new ContractService();
            boolean updateContract = service.updateContract(contract);

            //获得当前所在的页
            Integer currentPage =  (Integer)request.getSession().getAttribute("currentPage");
            Integer currentCount = (Integer)request.getSession().getAttribute("currentCount");

            if (updateContract){
                // 修改成功后重定向到列表界面
                response.sendRedirect(request.getContextPath()+"/contract?method=getContractList&currentPage="+currentPage+"&currentCount="+currentCount);

            }else {
                // 失败了直接提示
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("修改失败");
            }


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * @method:deleteCategory 删除分类
     * @date: 2017/7/10
     * @params:[request, response]
     * @return: void
     */
    public void deleteContract(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1 调用service中的查询方法
            Map<String, String[]> parameterMap = request.getParameterMap();
            Contract contract=new Contract();
            BeanUtils.populate(contract,parameterMap);
            ContractService service=new ContractService();
            boolean deleteContract = service.deleteContract(contract);
            //System.out.println("ContractServlet: "+deleteContract);

            //获得当前所在的页
            Integer currentPage =  (Integer)request.getSession().getAttribute("currentPage");
            Integer currentCount = (Integer)request.getSession().getAttribute("currentCount");

            if (deleteContract){
                // 删除成功后重定向到列表界面
                response.sendRedirect(request.getContextPath()+"/contract?method=getContractList&currentPage="+currentPage+"&currentCount="+currentCount);

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

    


}
