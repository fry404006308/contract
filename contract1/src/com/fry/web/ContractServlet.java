package com.fry.web;


import com.fry.bean.Contract;
import com.fry.bean.Page;
import com.fry.service.ContractService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
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
            ContractService categoryService=new ContractService();
            System.out.println("servlet_addTest: "+contract.getC_name()+contract.getC_dtype());
            boolean b = categoryService.addContract(contract);
            if (b){
                //添加成功
               response.setStatus(201);
               request.getRequestDispatcher("/contract-add.jsp").forward(request,response);
            }else {
                // 添加失败
                response.setStatus(600);
                request.getRequestDispatcher("/contract-add.jsp").forward(request,response);
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

            if (updateContract){
                // 修改成功后重定向到列表界面
                response.sendRedirect(request.getContextPath()+"/contract?method=getContractList&currentPage=1&currentCount=10");

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
            System.out.println("ContractServlet: "+deleteContract);
            if (deleteContract){
                // 删除成功后重定向到列表界面
                response.sendRedirect(request.getContextPath()+"/contract?method=getContractList&currentPage=1&currentCount=10");

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

    public void uploadContract(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
        操作：
        1、执行文件上传操作
        2、修改数据库中的下载否字段
         */

        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        System.out.println(savePath);
        File file = new File(savePath);
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath+"目录不存在，需要创建");
            //创建目录
            file.mkdir();
        }
        //消息提示
        String message = "";
        System.out.println("aaa");
        try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
//            if(!ServletFileUpload.isMultipartContent(request)){
//                //程序直接从这里出去了
//                System.out.println("bbb");
//                //按照传统方式获取数据
//                return;
//            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            System.out.println(list.size());
            for(FileItem item : list){
                System.out.println("bbb");
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(name + "=" + value);
                }else{//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    System.out.println(filename);
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    //获取文件的后缀，然后使用id名加上后缀的方式来命名

                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while((len=in.read(buffer))>0){
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    message = "文件上传成功！";
                }
            }
        }catch (Exception e) {
            message= "文件上传失败！";
            e.printStackTrace();

        }
        System.out.println("upload:");
        request.setAttribute("message",message);
        request.getRequestDispatcher("/ok.jsp").forward(request, response);


    }


}
