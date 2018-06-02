package com.zhzteam.zhz233.mapper.zlb;

import com.zhzteam.zhz233.model.GoodsMoreModel;
import com.zhzteam.zhz233.model.zlb.GoodsRentResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMoreMapper {
    //返回 List<GoodsRentResult> Limit N
    /*public List<GoodsRentResult> selectTByKey(
            @Param("goodstype") Integer goodstype,
            @Param("goodsrecomm") Integer goodsrecomm,
            @Param("goodsstatus") Integer goodsstatus,
            @Param("pagesize") Integer pagesize);*/

    /**
     * 添加 信息
     * @param goodsMoreModel
     * @return
     */
    public Long insertTByKey(GoodsMoreModel goodsMoreModel);
}
