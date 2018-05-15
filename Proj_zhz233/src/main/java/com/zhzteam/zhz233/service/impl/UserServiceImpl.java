package com.zhzteam.zhz233.service.impl;

import com.zhzteam.zhz233.model.LogonResult;

public interface UserServiceImpl {

    /**
     * 判断 用户名 密码 是否有效
     * @param username
     * @param password
     * @return
     */
    public LogonResult selectTByKey(String username, String password);

    /**
     * 添加 用户信息 是否有效 返回 LogonResult
     * @param username
     * @param cellphone
     * @param password
     * @param autoNo
     * @return
     */
    public Boolean insertTByKey(String username,String cellphone,String password,String autoNo);

    public Boolean selectTByUserName(String username);

    public Boolean selectTByCellPhone(String cellphone);

    public String selectTByAuto();

}
