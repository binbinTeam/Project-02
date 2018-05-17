package com.zhzteam.zhz233.service.zlb.impl;

import com.zhzteam.zhz233.model.zlb.GoodsRentResult;

import java.util.List;

public interface GoodsRentServiceImpl {
    /**
     * 返回 List<GoodsRentResult> Limit N
     * @param goodstype
     * @param goodsrecomm
     * @param goodsstatus
     * @param pagesize
     * @return
     */
    public List<GoodsRentResult> selectTByKey(Integer goodstype, Integer goodsrecomm, Integer goodsstatus, Integer pagesize);
}
