package com.zhzteam.zhz233.mapper.zlb;

import com.zhzteam.zhz233.model.zlb.LogonResult;
import org.apache.ibatis.annotations.Param;

public interface UserBeanMapper {
    /**
     * 判断 用户信息 是否有效 返回 LogonResult
     * @param username
     * @param password
     * @return
     */
    public LogonResult selectTByKey(@Param("username") String username, @Param("password") String password);

    /**
     * 添加 用户信息 是否有效 返回 LogonResult
     * @param username
     * @param cellphone
     * @param password
     * @param autoNo
     * @return
     */
    public Long insertTByKey(@Param("username") String username,
                             @Param("cellphone") String cellphone,
                             @Param("password") String password,
                             @Param("autoNo") String autoNo);

    /**
     * 查询 username
     * @param username
     * @return
     */
    public Long selectTByUserName(@Param("username") String username);

    /**
     * 查询 cellphone
     * @param cellphone
     * @return
     */
    public Long selectTByCellPhone(@Param("cellphone") String cellphone);

    /**
     * 获取自增 NO
     * @return
     */
    public String selectTByAuto();
}
