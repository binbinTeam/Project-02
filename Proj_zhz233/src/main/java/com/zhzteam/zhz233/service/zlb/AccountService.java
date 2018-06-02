package com.zhzteam.zhz233.service.zlb;

import com.zhzteam.zhz233.model.zlb.AccountResult;

public interface AccountService {
    /**
     * 添加账户表
     * @param accountNo
     * @return
     */
    public Boolean insertTByKey(String accountNo);
    /**
     * 获取 账户信息
     * @param accountNo
     * @return
     */
    public AccountResult selectTByANO(String accountNo);
    /**
     * 更新 身份验证
     * @param certification
     * @param accountNo
     */
    public void updateCertificationByANO(Integer certification, String accountNo);
    /**
     * 更新 密码
     * @param password
     * @param accountNo
     * @return
     */
    public void updatePwdByPwdANo(String password, String accountNo);
    /**
     * 更新 available
     * @param money
     * @param accountNo
     * @return
     */
    public void updateRechargeAvailableByANo(Double money, String accountNo);
    /**
     * 更新 available
     * @param money
     * @param accountNo
     * @return
     */
    public void updateWithdrawAvailableByANo(Double money, String accountNo);

    /**
     *
     * @param available
     * @param frozen
     * @param accountNo
     */
    public void updateLByMoneyANo(Double available,Double frozen,String accountNo);

    /**
     *
     * @param available
     * @param frozen
     * @param accountNo
     */
    public void updateRByMoneyANo(Double available,Double frozen,String accountNo);

    /**
     * 消费
     * @param available
     * @param accountNo
     */
    public void updateAvailableByANo(Double available,String accountNo);
}
