package com.zhzteam.zhz233.mapper.zlb;

import com.zhzteam.zhz233.model.zlb.GoodsResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    /**
     * 返回 List<GoodsResult> Limit N
     * @param goodstype
     * @param goodsrecomm
     * @param goodsstatus
     * @param pagesize
     * @return
     */
    public List<GoodsResult> selectTByKey(@Param("goodstype") Integer goodstype, @Param("goodsrecomm") Integer goodsrecomm, @Param("goodsstatus") Integer goodsstatus, @Param("pagesize") Integer pagesize);

    /**
     * 返回商品 总数
     * @param goodsstatus
     * @return
     */
    public Integer selectRentTotal(@Param("goodstype")Integer goodstype, @Param("goodsstatus") Integer goodsstatus);


}
