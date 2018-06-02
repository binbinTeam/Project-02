package com.zhzteam.zhz233.mapper.zlb;

import com.zhzteam.zhz233.model.zlb.AccountResult;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    /**
     * 添加账户表
     * @param accountNo
     * @return
     */
    public Long insertTByKey(@Param("accountNo") String accountNo);
    /**
     * 获取 账户信息
     * @param accountNo
     * @return
     */
    public AccountResult selectTByANO(@Param("accountNo") String accountNo);

    /**
     * 更新 身份验证
     * @param certification
     * @param accountNo
     */
    public void updateCertificationByANO(@Param("certification") Integer certification,@Param("accountNo") String accountNo);

    /**
     * 更新 密码
     * @param password
     * @param accountNo
     * @return
     */
    public void updatePwdByPwdANo(@Param("password") String password,@Param("accountNo") String accountNo);
    /**
     * 更新 available
     * @param money
     * @param accountNo
     * @return
     */
    public void updateRechargeAvailableByANo(@Param("money") Double money,@Param("accountNo") String accountNo);
    /**
     * 更新 available
     * @param money
     * @param accountNo
     * @return
     */
    public void updateWithdrawAvailableByANo(@Param("money") Double money,@Param("accountNo") String accountNo);

    /**
     *  + -
     * @param available
     * @param frozen
     * @param accountNo
     */
    public void updateLByMoneyANo(@Param("available") Double available,@Param("frozen") Double frozen,@Param("accountNo") String accountNo);
    /**
     *  - +
     * @param available
     * @param frozen
     * @param accountNo
     */
    public void updateRByMoneyANo(@Param("available") Double available,@Param("frozen") Double frozen,@Param("accountNo") String accountNo);

    /**
     * 消费
     * @param available
     * @param accountNo
     */
    public void updateAvailableByANo(@Param("available") Double available,@Param("accountNo") String accountNo);
}
