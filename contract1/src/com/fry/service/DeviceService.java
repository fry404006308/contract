package com.fry.service;

import com.fry.bean.Device;
import com.fry.dao.DeviceDao;

import java.sql.SQLException;
import java.util.List;

public class DeviceService {
    /**
     * service层里面处理各种算法
     * 这里我们只包含基本的增删改查
     * 不包含其它的算法
     *
     */
    DeviceDao dao=null;
    {
        dao=new DeviceDao();
    }
    //1、增加设备
    public boolean addDevice(Device device) throws SQLException {
        boolean addDevice = dao.addDevice(device);
        return addDevice;
    }
    //2、删除设备
    public boolean deleteDevice(Device device) throws SQLException {
        boolean deleteDevice = dao.deleteDevice(device);
        return deleteDevice;
    }
    //3、修改设备
    public boolean updateDevice(Device device) throws SQLException {
        boolean updateDevice = dao.updateDevice(device);
        return updateDevice;
    }
    //4、查询设备
    public List<Device> queryDevice(int cid) throws SQLException {
        List<Device> devices = dao.queryDevice(cid);
        return devices;
    }
}
