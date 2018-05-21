package com.zhzteam.zhz233.service.zlb;

import com.zhzteam.zhz233.mapper.zlb.LeaseOrderMapper;
import com.zhzteam.zhz233.model.zlb.LeaseOrderResult;
import com.zhzteam.zhz233.service.zlb.impl.LeaseOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseOrderService implements LeaseOrderServiceImpl {
    @Autowired
    LeaseOrderMapper leaseOrderMapper;

    @Override
    public List<LeaseOrderResult> selectTByKey(Integer pagesize) {
        return leaseOrderMapper.selectTByKey(pagesize);
    }
}
