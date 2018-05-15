package com.zhzteam.zhz233.service.impl;

import com.zhzteam.zhz233.model.LeaseResult;

import java.util.List;

public interface LeaseServiceImpl {
    /**
     * 返回 List<LeaseResult> Limit N
     * @param pagesize
     * @return
     */
    public List<LeaseResult> selectTByKey(Integer pagesize);
}
