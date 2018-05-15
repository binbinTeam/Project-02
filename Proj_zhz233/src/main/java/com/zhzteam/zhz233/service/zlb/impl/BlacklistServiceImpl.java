package com.zhzteam.zhz233.service.zlb.impl;

import com.zhzteam.zhz233.model.zlb.BlacklistResult;

import java.util.List;

public interface BlacklistServiceImpl {
    /**
     * 返回 List<BlacklistResult> Limit N
     * @param pagesize
     * @return
     */
    public List<BlacklistResult> selectTByKey(Integer pagesize);
}
