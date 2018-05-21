package com.zhzteam.zhz233.service.zlb.impl;

import com.zhzteam.zhz233.model.zlb.LeaseOrderResult;

import java.util.List;

public interface LeaseOrderServiceImpl {
    /**
     * 返回 List<LeaseOrderResult> Limit N
     * @param pagesize
     * @return
     */
    public List<LeaseOrderResult> selectTByKey(Integer pagesize);
}
