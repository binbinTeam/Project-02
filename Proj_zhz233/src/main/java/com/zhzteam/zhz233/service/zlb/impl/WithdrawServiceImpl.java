package com.zhzteam.zhz233.service.zlb.impl;

import com.zhzteam.zhz233.mapper.zlb.WithdrawMapper;
import com.zhzteam.zhz233.service.zlb.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WithdrawServiceImpl implements WithdrawService {

    @Autowired
    WithdrawMapper withdrawMapper;

    @Override
    public Boolean insertTByKey(String orderNo, String accountNo, Integer orderMethod, Double amount,Double charge,String remark, Integer status) {
        if(withdrawMapper.insertTByKey(orderNo, accountNo, orderMethod, amount, charge, remark, status)>0)return true;
        return null;
    }

    @Override
    public String selectTByAuto() {
        return withdrawMapper.selectTByAuto();
    }
}
