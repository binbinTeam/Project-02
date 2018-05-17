package com.zhzteam.zhz233.mapper.zlb;

import com.zhzteam.zhz233.model.zlb.UserResult;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * 判断 用户信息 是否有效 返回 UserResult
     * @param username
     * @param password
     * @return
     */
    public UserResult selectTByKey(@Param("username") String username, @Param("password") String password);

    /**
     * 添加 用户信息 是否有效 返回 UserResult
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

    /**
     * 获取 UserResult
     * @param no
     * @return
     */
    public UserResult selectTByNo(@Param("no") String no);
}
