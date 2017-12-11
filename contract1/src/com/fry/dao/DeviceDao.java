package com.fry.dao;

import com.fry.bean.Device;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class DeviceDao {
    ComboPooledDataSource dataSource=null;
    QueryRunner queryRunner=null;



    //增加设备
    public boolean  addDevice(Device device) throws SQLException {
        dataSource=new ComboPooledDataSource();
        queryRunner=new QueryRunner(dataSource);
        String sql="insert into device values(null,?,?,?,?,?,?,?,?,?,?)";
        int row = queryRunner.update(sql,device.getD_cid(),device.getD_did(),device.getD_type(),device.getD_campus(),device.getD_apartment(),device.getD_people(),device.getD_date(),device.getD_used(),device.getD_undate(),device.getD_remark());
        dataSource.close();
        if (row>0){
            return true;
        }else {
            return false;
        }
    }

    //删除设备
    public boolean  deleteDevice(Device device) throws SQLException {
        dataSource=new ComboPooledDataSource();
        queryRunner=new QueryRunner(dataSource);
        String sql="delete from device where d_id=?";
        int row = queryRunner.update(sql,device.getD_id());
        dataSource.close();
        return row>0?true:false;
    }

    //修改设备
    public boolean updateDevice(Device device) throws SQLException {
        dataSource=new ComboPooledDataSource();
        queryRunner=new QueryRunner(dataSource);
        String sql="update device set d_cid=?,d_did=?,d_type=?,d_campus=?,d_apartment=?,d_people=?,d_date=?,d_used=?,d_undate=?,d_remark=? where d_id=?";
        int row = queryRunner.update(sql,device.getD_cid(),device.getD_did(),device.getD_type(),device.getD_campus(),device.getD_apartment(),device.getD_people(),device.getD_date(),device.getD_used(),device.getD_undate(),device.getD_remark(),device.getD_id());
        dataSource.close();
        if (row>0){
            return true;
        }else {
            return false;
        }
    }

    //查找设备
    public List<Device> queryDevice(int cid) throws SQLException {
        dataSource=new ComboPooledDataSource();
        queryRunner=new QueryRunner(dataSource);
        String sql="select * from device where d_cid=?";
        List<Device> deviceList = queryRunner.query(sql, new BeanListHandler<Device>(Device.class),cid);
        dataSource.close();
        return deviceList;
    }
}
