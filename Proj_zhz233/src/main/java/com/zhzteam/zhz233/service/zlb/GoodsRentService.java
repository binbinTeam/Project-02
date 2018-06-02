package com.zhzteam.zhz233.service.zlb;

import com.zhzteam.zhz233.model.GoodsRentModel;
import com.zhzteam.zhz233.model.zlb.GoodsRentResult;

import java.util.List;

public interface GoodsRentService {
    /**
     * 返回 List<GoodsRentResult> Limit N
     * @param goodstype
     * @param goodsrecomm
     * @param goodsstatus
     * @param pagesize
     * @return
     */
    public List<GoodsRentResult> selectTByKey(Integer goodstype, Integer goodsrecomm, Integer goodsstatus, Integer pagesize);
    /**
     * 添加 商品
     * @param goodsRentModel
     * @return
     */
    public Boolean insertTByKey(GoodsRentModel goodsRentModel);
    /**
     * 查询商品信息
     * @param goodsNo
     * @return
     */
    public GoodsRentResult selectTByANO(String goodsNo);
}
