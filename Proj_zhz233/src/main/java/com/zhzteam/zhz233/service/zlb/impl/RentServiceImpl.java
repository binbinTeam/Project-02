package com.zhzteam.zhz233.service.zlb.impl;

import com.zhzteam.zhz233.model.zlb.GoodsResult;
import com.zhzteam.zhz233.model.zlb.RentResult;

import java.util.List;

public interface RentServiceImpl {
    /**
     * 返回 商品信息 List<RentResult>
     * @param goodstype
     * @param goodsstatus
     * @param pagesize
     * @return
     */
    public List<RentResult> selectTByKey(Integer goodstype, Integer goodsstatus, Integer pagesize);
    /**
     * 返回 List<RentResult> Limit N
     * @param gamesName
     * @param gamesServer
     * @param gamesArea
     * @param searchKey
     * @param priceRange
     * @param authenticate
     * @param goodsType
     * @param goodsSort
     * @return
     */
    public List<RentResult> selectTBySKey(
            String gamesName,String gamesServer,String gamesArea,String searchKey,
            Integer priceRange,Integer authenticate,Integer goodsType,Integer goodsSort,
            Integer goodsStatus,Integer currentPage,Integer pageSize
    );
    /**
     * 返回 Interger N
     * @param gamesName
     * @param gamesServer
     * @param gamesArea
     * @param searchKey
     * @param priceRange
     * @param authenticate
     * @param goodsType
     * @param goodsSort
     * @param goodsStatus
     * @return
     */
    public Integer selectTBySAutoCount(
            String gamesName,String gamesServer,String gamesArea,String searchKey,
            Integer priceRange,Integer authenticate,Integer goodsType,
            Integer goodsSort,Integer goodsStatus
    );
}
