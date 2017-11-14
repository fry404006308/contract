package com.fry.service;


import com.fry.bean.Contract;
import com.fry.bean.Page;
import com.fry.dao.ContractDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/7/8.
 */
public class ContractService {
    /**
     * @method:addCategory 添加生鲜数据
     * @date: 2017/7/8
     * @params:[category]
     * @return: boolean
     */
    ContractDao dao=null;
    {
        dao = new ContractDao();
    }
    public boolean addContract(Contract contract) throws SQLException {
        boolean addContract = dao.addContract(contract);
        return addContract;
    }

    /**
     * @method:findCategory 查询所有数据
     * @date: 2017/7/8
     * @params:[]
     * @return: java.util.List<net.zixue.bean.Category>
     */
    public List<Contract> findCategory() throws SQLException {
        List<Contract> contract = dao.queryContractList();
        return contract;
    }
    /**
     * @method:findPageCategory 分页查询数据
     * @date: 2017/7/9
     * @params:[currentPage, currentCount]
     * @return: java.util.List<net.zixue.bean.Category>
     */
    public Page findPageContract(int currentPage, int currentCount) throws SQLException {

        Page page=new Page();
        // 1 查询出数据的总数
        int totalCount = dao.queryCount();

        /*  总数   每页显示数目  总页数
             9        10    0.9     1
             10       10     1      1
             11       10    1.1     2
            java ceil
         */
        // 2 根据总数和当前页显示数 计算出总页数
        int totalPage= (int) Math.ceil(1.0*totalCount/currentCount);
        //3 将分页相关信息封装到page类中
        page.setCurrentCount(currentCount);
        page.setCurrentPage(currentPage);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);

        // 计算查询的起始位置
//        页数  每页显示条数  起始位置
//
//        1        3           0
//        2        3           3
//        3        3           6  （currentPage-1）*currentCount
        //计算出起始位置
         int startPosition=(currentPage-1)*currentCount;
         // 分页查询数据
         List<Contract> contracts = dao.queryPageContractList(startPosition,currentCount);
        // 将集合数据封装到page类中
        page.setList(contracts);

         return page;
    }



















////    public PageBean findPageCategory(int currentPage, int currentCount) throws SQLException {
////        PageBean pageBean = new PageBean();
////        CategoryDao dao = new CategoryDao();
////        // 查询总提哦啊书
////        int totalCount = dao.queryCount();
////        // 封装分页数据
////        pageBean.setTotalCount(totalCount);
////        pageBean.setCurrentCount(currentCount);
////        pageBean.setCurrentPage(currentPage);
////        /**
////         * 总数 每页显示    页数
////         *10    10    1       1
////         * 11   10     1.1      2
////         * 9    10    0.9       1
////         * java ceil
////         */
////
////        // 计算总页数
////        double totalPage = Math.ceil(0.1 * totalCount / currentCount);
////        pageBean.setTotalPage((int) totalPage);
////
////        // 计算起始位置
//////        页数  每页显示条数  起始位置
//////        1          3          0
//////        2          3          3
//////        3          3          6
////        //  (当前页数-1 )乘以每页显示的条数=起始位置
////        int startPosition = (currentPage - 1) * currentCount;
////        // 获取categoryList
////        List<Category> categories = dao.queryCategoryLimit(startPosition, currentCount);
////        // 将生鲜列表list封装到pageBean中
////
////        pageBean.setList(categories);
////
////        return pageBean;
////    }
//
//
//    public boolean updateCategory(Category category) throws SQLException {
//        CategoryDao dao=new CategoryDao();
//        boolean updateCategory = dao.updateCategory(category);
//        return updateCategory;
//    }
//
    /**
     * @method:deleteCategory servcie删除信息数据
     * @date: 2017/7/10
     * @params:[category]
     * @return: boolean
     */
    public boolean deleteContract(Contract contract) throws SQLException {
        ContractDao dao=new ContractDao();
        boolean deleteContract = dao.deleteContract(contract);
        return deleteContract;
    }
}
