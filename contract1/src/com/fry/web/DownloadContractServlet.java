package com.fry.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@WebServlet(name = "DownloadContractServlet",urlPatterns = "/downloadContract")
public class DownloadContractServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
        String fileSaveRootPath=this.getServletContext().getRealPath("/WEB-INF/upload");
        //通过文件名找出文件的所在目录
        //String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
        int contract_id = Integer.parseInt(request.getParameter("c_id"));
        String path =fileSaveRootPath+"\\"+contract_id+".pdf";
        //得到要下载的文件
        File file = new File(path);
        //如果文件不存在
        if(!file.exists()){
            request.setAttribute("message", "您要下载的资源还没有上传！！");
            request.getRequestDispatcher("/ok.jsp").forward(request, response);
            return;
        }
        //得到要下载的文件名
        String fileName = request.getParameter("filename");

        String desFile=fileName+".pdf";
        System.out.println("desFile: "+desFile);

        //设置响应头，控制浏览器下载该文件
        //response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(desFile, "UTF-8"));
//        response.setHeader("content-disposition", "attachment;filename=" + desFile);
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(desFile, "UTF-8"));

        //读取要下载的文件，保存到文件输入流
        //要下载文件的源
        FileInputStream in = new FileInputStream(path);
        //创建输出流
        //我们的下载文件
        OutputStream out =response.getOutputStream() ;
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while((len=in.read(buffer))>0){
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();
    }
}
