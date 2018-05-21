package com.zhzteam.zhz233.service.zlb.impl;

import com.zhzteam.zhz233.model.zlb.GoodsResult;

import java.util.List;

public interface GoodsServiceImpl {
    /**
     * 返回 List<GoodsResult> Limit N
     * @param pagesize
     * @return
     */
    public List<GoodsResult> selectTByKey(Integer goodstype, Integer goodsrecomm, Integer goodsstatus, Integer pagesize);
    /**
     * 返回租号商品 总数
     * @param goodsstatus
     * @return
     */
    public Integer selectRentTotal(Integer goodstype, Integer goodsstatus);
}
