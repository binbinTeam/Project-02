package com.zhzteam.zhz233.service.zlb;

import com.zhzteam.zhz233.mapper.zlb.UserMapper;
import com.zhzteam.zhz233.model.zlb.UserResult;
import com.zhzteam.zhz233.service.zlb.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceImpl {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResult selectTByKey(String username, String password) {
        return userMapper.selectTByKey(username, password);
    }

    @Override
    public Boolean insertTByKey(String username, String cellphone, String password, String autoNo) {
        if(userMapper.insertTByKey(username, cellphone, password, autoNo) > 0)return true;
        return false;
    }

    @Override
    public Boolean selectTByUserName(String username) {
        if(userMapper.selectTByUserName(username) > 0) return true;
        return false;
    }

    @Override
    public Boolean selectTByCellPhone(String cellphone) {
        if(userMapper.selectTByCellPhone(cellphone) > 0) return true;
        return false;
    }

    @Override
    public String selectTByAuto() {
        return userMapper.selectTByAuto();
    }

    @Override
    public UserResult selectTByNo(String no) {
        return userMapper.selectTByNo(no);
    }
}
