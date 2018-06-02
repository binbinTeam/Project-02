package com.zhzteam.zhz233.service.zlb.impl;

import com.zhzteam.zhz233.mapper.zlb.AccountMapper;
import com.zhzteam.zhz233.model.zlb.AccountResult;
import com.zhzteam.zhz233.service.zlb.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;

    @Override
    public Boolean insertTByKey(String accountNo) {
        if(accountMapper.insertTByKey(accountNo) > 0)return true;
        else return false;
    }

    @Override
    public AccountResult selectTByANO(String accountNo) {
        return accountMapper.selectTByANO(accountNo);
    }

    @Override
    public void updateCertificationByANO(Integer certification, String accountNo) {
        accountMapper.updateCertificationByANO(certification,accountNo);
    }

    @Override
    public void updatePwdByPwdANo(String password, String accountNo) {
        accountMapper.updatePwdByPwdANo(password, accountNo);
    }

    @Override
    public void updateRechargeAvailableByANo(Double money, String accountNo) {
        accountMapper.updateRechargeAvailableByANo(money, accountNo);
    }

    @Override
    public void updateWithdrawAvailableByANo(Double money, String accountNo) {
        accountMapper.updateWithdrawAvailableByANo(money, accountNo);
    }

    @Override
    public void updateLByMoneyANo(Double available, Double frozen, String accountNo) {
        accountMapper.updateLByMoneyANo(available, frozen, accountNo);
    }

    @Override
    public void updateRByMoneyANo(Double available, Double frozen, String accountNo) {
        accountMapper.updateRByMoneyANo(available, frozen, accountNo);
    }

    @Override
    public void updateAvailableByANo(Double available, String accountNo) {
        accountMapper.updateAvailableByANo(available, accountNo);
    }
}
