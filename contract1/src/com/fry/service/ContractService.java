package com.fry.service;


import com.fry.bean.Contract;
import com.fry.bean.Page;
import com.fry.dao.ContractDao;

import java.sql.SQLException;
import java.util.List;

/**
 * 获取最后一页的操作应该写在这
 */
public class ContractService {
    /**
     * @method:addCategory 添加数据
     * @date: 2017/7/8
     * @params:[category]
     * @return: boolean
     */
    ContractDao dao=null;
    {
        dao = new ContractDao();
    }

    public int getLastPage(int currentCount) throws SQLException{
        // 1 查询出数据的总数
        int totalCount = dao.queryCount();
        int lastPage = (int) Math.ceil(1.0*totalCount/currentCount);
        return lastPage;
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
     * 查询出记录的总条数
     * @return
     * @throws SQLException
     */
    public int findTotalCount() throws SQLException {
        // 1 查询出数据的总数
        int totalCount = dao.queryCount();
        return totalCount;
    }

    /**
     * @method:findPageCategory 分页查询数据
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

    public boolean updateContract(Contract contract) throws SQLException {
        ContractDao dao=new ContractDao();
        boolean updateContract = dao.updateContract(contract);
        return updateContract;
    }

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

    //更新成功上传合同的contract的upload字段
    public boolean updateUpload(Contract contract) throws SQLException {
        ContractDao dao=new ContractDao();
        boolean updateUpload = dao.updateUpload(contract);
        return updateUpload;
    }
}
