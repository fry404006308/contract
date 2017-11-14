package com.fry.service;


import com.fry.bean.User;
import com.fry.dao.UserDao;

import java.sql.SQLException;

/**
 * Created by Administrator on 2017/7/6.
 */
public class UserService {
    UserDao userDao = null;
    {
        userDao = new UserDao();
    }


    /**
     * @method:register 用户注册
     * @date: 2017/7/7
     * @params:[name, password, email]
     * @return: boolean
     */
    //1. 判断注册用户是否存在
    public boolean register(User user) {


        boolean register=false;

        boolean checkUser = userDao.checkUser(user.getU_name());
        //2. 如果不存在就将用户信息添加到数据库
        if (checkUser) {
             register = userDao.register(user);
        }
        return register;
    }


    /**
     * @method:login 用户登录
     * @date: 2017/7/7
     * @params:[name, password]
     * @return: void
     */
    public User login(String name, String password) throws SQLException {

        User user = userDao.login(name, password);
        return  user;
    }


}
