package com.zhzteam.zhz233.mapper.zlb;

import com.zhzteam.zhz233.model.GoodsRentModel;
import com.zhzteam.zhz233.model.zlb.GoodsRentResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsRentMapper {
    /**
     * 返回 List<GoodsRentResult> Limit N
     * @param goodstype
     * @param goodsrecomm
     * @param goodsstatus
     * @param pagesize
     * @return
     */
    public List<GoodsRentResult> selectTByKey(
            @Param("goodstype") Integer goodstype,
            @Param("goodsrecomm") Integer goodsrecomm,
            @Param("goodsstatus") Integer goodsstatus,
            @Param("pagesize") Integer pagesize);
    /**
     * 添加 商品
     * @param goodsRentModel
     * @return
     */
    public Long insertTByKey(GoodsRentModel goodsRentModel);

    /**
     * 查询商品信息
     * @param goodsNo
     * @return
     */
    public GoodsRentResult selectTByANO(@Param("goodsNo") String goodsNo);
}
