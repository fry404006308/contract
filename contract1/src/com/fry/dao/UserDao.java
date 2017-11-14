package com.fry.dao;

import com.fry.bean.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Created by Administrator on 2017/7/6.
 */
public class UserDao {


    ComboPooledDataSource dataSource=null;
    QueryRunner queryRunner=null;





    /**
     * @method:checkUser 检查用户是否存在
     * @date: 2017/7/7
     * @params:[name]
     * @return: boolean
     */

    public boolean checkUser(String name){

        dataSource=new ComboPooledDataSource();
        queryRunner=new QueryRunner(dataSource);
        try {
            String sql="select u_name from user where u_name=?";
            User user = queryRunner.query(sql, new BeanHandler<User>(User.class),name);
            dataSource.close();
            //如果没有查询到数据 说明这个用户名没有注册过
            if (user==null) {
                return  true;
            }else {
                return  false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }

    }
    /**
     * @method:register 用户注册
     * @date: 2017/7/7
     * @params:[name, password, email]
     * @return: boolean
     */

    public boolean register(User user) {


        dataSource=new ComboPooledDataSource();
        queryRunner=new QueryRunner(dataSource);
        try {
            String sql="insert into user values(null,?,?,?)";
            int row = queryRunner.update(sql, user.getU_name(), user.getU_password(), user.getU_email());
            dataSource.close();
            //行数大于零说明注册成功
            if (row>0) {
                return  true;
            }else {
                return  false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }

    }


    /**
     * @method:login 查询数据库
     * @date: 2017/7/7
     * @params:[name, password]
     * @return: void
     */
    public User login(String name, String password) throws SQLException {
        dataSource=new ComboPooledDataSource();
        queryRunner=new QueryRunner(dataSource);
        String sql="select * from user where u_name=? and u_password=?";
        User user = queryRunner.query(sql, new BeanHandler<User>(User.class),name,password);
        dataSource.close();
        return  user;
    }
}
