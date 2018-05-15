package com.zhzteam.zhz233.service;

import com.zhzteam.zhz233.mapper.UserBeanMapper;
import com.zhzteam.zhz233.model.LogonResult;
import com.zhzteam.zhz233.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceImpl {
    @Autowired
    private UserBeanMapper userBeanMapper;

    @Override
    public LogonResult selectTByKey(String username, String password) {
        return userBeanMapper.selectTByKey(username, password);
    }

    @Override
    public Boolean insertTByKey(String username, String cellphone, String password, String autoNo) {
        if(userBeanMapper.insertTByKey(username, cellphone, password, autoNo) > 0)return true;
        return false;
    }

    @Override
    public Boolean selectTByUserName(String username) {
        if(userBeanMapper.selectTByUserName(username) > 0) return true;
        return false;
    }

    @Override
    public Boolean selectTByCellPhone(String cellphone) {
        if(userBeanMapper.selectTByCellPhone(cellphone) > 0) return true;
        return false;
    }

    @Override
    public String selectTByAuto() {
        return userBeanMapper.selectTByAuto();
    }
}
