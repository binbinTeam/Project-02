package com.zhzteam.zhz233.service.zlb.impl;

import com.zhzteam.zhz233.mapper.zlb.OrderMapper;
import com.zhzteam.zhz233.model.zlb.LeaseOrderInfo;
import com.zhzteam.zhz233.model.zlb.LeaseOrderInfoResult;
import com.zhzteam.zhz233.model.zlb.LeaseOrderResult;
import com.zhzteam.zhz233.model.zlb.LeaseOrderShow;
import com.zhzteam.zhz233.service.zlb.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<LeaseOrderResult> selectTByKey(Integer pagesize) {
        return orderMapper.selectTByKey(pagesize);
    }

    @Override
    public String selectTByAuto() {
        return orderMapper.selectTByAuto();
    }

    @Override
    public Boolean insertTByKey(LeaseOrderInfo leaseOrderInfo) {
        if(orderMapper.insertTByKey(leaseOrderInfo) > 0) return true;
        else return false;
    }

    @Override
    public List<LeaseOrderShow> selectListTByANO(String accountNo) {
        return orderMapper.selectListTByANO(accountNo);
    }

    @Override
    public List<LeaseOrderShow> selectListTByMANO(String accountNo) {
        return orderMapper.selectListTByMANO(accountNo);
    }

    @Override
    public void deleteTByANO(String orderNo, String accountNo) {
        orderMapper.deleteTByANO(orderNo, accountNo);
    }

    @Override
    public void deleteTByMANO(String orderNo, String accountNo) {
        orderMapper.deleteTByMANO(orderNo, accountNo);
    }

    @Override
    public LeaseOrderInfoResult selectTByANO(String orderNo, String accountNo) {
        return orderMapper.selectTByANO(orderNo, accountNo);
    }

    @Override
    public LeaseOrderInfoResult selectTByMANO(String orderNo, String accountNo) {
        return orderMapper.selectTByMANO(orderNo, accountNo);
    }

    @Override
    public LeaseOrderShow selectTByBNO(String orderNo, String accountNo) {
        return orderMapper.selectTByBNO(orderNo, accountNo);
    }

    @Override
    public void updateTByANO(Integer state, String orderNo, String accountNo) {
        orderMapper.updateTByANO(state,orderNo,accountNo);
    }

    @Override
    public void updateTByMANO(Integer state, String orderNo, String accountNo) {
        orderMapper.updateTByMANO(state, orderNo, accountNo);
    }

    @Override
    public LeaseOrderShow selectTByCNO(String orderNo, String accountNo) {
        return orderMapper.selectTByCNO(orderNo, accountNo);
    }

}
