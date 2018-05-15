package com.zhzteam.zhz233.service.zlb;

import com.zhzteam.zhz233.mapper.zlb.LeaseMapper;
import com.zhzteam.zhz233.model.zlb.LeaseResult;
import com.zhzteam.zhz233.service.zlb.impl.LeaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseService implements LeaseServiceImpl {
    @Autowired
    LeaseMapper leaseMapper;

    @Override
    public List<LeaseResult> selectTByKey(Integer pagesize) {
        return leaseMapper.selectTByKey(pagesize);
    }
}
