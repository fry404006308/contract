package com.fry.dao;

import com.fry.bean.Contract;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/7/8.
 */
public class ContractDao {
    /**
     * @method:addCategory
     * @date: 2017/7/8
     * @params:[category]
     * @return: boolean
     */

    ComboPooledDataSource dataSource=null;
    QueryRunner queryRunner=null;


    public boolean  addContract(Contract contract) throws SQLException {

        dataSource=new ComboPooledDataSource();
        queryRunner=new QueryRunner(dataSource);
        String sql="insert into contract values(null,?,?,?,?,?,?,?,?,?,?,?,?)";
        int row = queryRunner.update(sql,contract.getC_name(),contract.getC_dtype(),contract.getC_type(),contract.getC_supplier(),contract.getC_price(),contract.getC_tprice(),contract.getC_count(),contract.getC_date(),contract.getC_campus(),contract.getC_person(),"未上传",contract.getC_remark() );
        System.out.println("addTest: "+contract.getC_name()+contract.getC_dtype());
        dataSource.close();
         if (row>0){
             return true;
         }else {
             return false;
         }

    }

    public List<Contract> queryContractList() throws SQLException {
        dataSource=new ComboPooledDataSource();
        queryRunner=new QueryRunner(dataSource);
        String sql="select * from contract";
        List<Contract> categoryList = queryRunner.query(sql, new BeanListHandler<Contract>(Contract.class));
        dataSource.close();
          return categoryList;
    }

    public List<Contract> queryPageContractList(int startPosition,int currentCount) throws SQLException {
        dataSource=new ComboPooledDataSource();
        queryRunner=new QueryRunner(dataSource);
        String sql="select * from contract limit ?,?";
        //这里我是把数据库里面的东西映射到bean里面，两者的名字应该是一模一样，也就是数据库里面的名字的bean里面的应该是一模一样
        List<Contract> contractList = queryRunner.query(sql, new BeanListHandler<Contract>(Contract.class),startPosition,currentCount);
        //System.out.println(contractList.get(0).getName()+contractList.get(0).getCampus()+"contractdao");
        //System.out.println(contractList.size()+"contractdao");
        //System.out.println(startPosition+" "+currentCount);
        dataSource.close();
        return contractList;
    }


    /**
     * @method:queryCount 查询数据总数
     * @date: 2017/7/8
     * @params:[]
     * @return: int
     */
    public int queryCount() throws SQLException {
        dataSource=new ComboPooledDataSource();
        queryRunner=new QueryRunner(dataSource);
        String sql="select count(*) from contract";
        Long query = queryRunner.query(sql, new ScalarHandler<>());
        dataSource.close();
        return query.intValue();
    }

    /**
     * @method:updateCategory
     * @date: 2017/7/10
     * @params:[category]
     * @return: boolean
     */
    public boolean updateContract(Contract contract) throws SQLException {

        dataSource=new ComboPooledDataSource();
        queryRunner=new QueryRunner(dataSource);
        String sql="update contract set c_name=?,c_dtype=?,c_type=?,c_supplier=?,c_price=?,c_tprice=?,c_count=?,c_date=?,c_campus=?,c_person=?,c_upload=?,c_remark=? where c_id=?";
        int row = queryRunner.update(sql,contract.getC_name(),contract.getC_dtype(),contract.getC_type(),
                contract.getC_supplier(),contract.getC_price(),contract.getC_tprice(),contract.getC_count(),
                contract.getC_date(),contract.getC_campus(),contract.getC_person(),contract.getC_upload(),contract.getC_remark(),contract.getC_id());
        //System.out.println("updateContract_dao_test: "+row+" "+contract.getC_id()+" "+contract.getC_name());
        dataSource.close();
        if (row>0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * @method:deleteContract 操作数据库删除信息
     * @date: 2017/11/10
     * @params:[Contract]
     * @return: boolean
     */
    public boolean deleteContract(Contract contract) throws SQLException {
        dataSource=new ComboPooledDataSource();
        queryRunner=new QueryRunner(dataSource);
        String sql="delete from contract where c_id=?";
        int row = queryRunner.update(sql,contract.getC_id());
        //System.out.println("deleteContract: "+row+contract.getC_id());
        dataSource.close();
        return row>0?true:false;
    }

    //更新成功上传合同的contract的upload字段
    public boolean updateUpload(Contract contract) throws SQLException{
        dataSource=new ComboPooledDataSource();
        queryRunner=new QueryRunner(dataSource);
        String sql="update contract set c_upload='已上传' where c_id=?";
        int row = queryRunner.update(sql,contract.getC_id());
        dataSource.close();
        return row>0?true:false;
    }
}
