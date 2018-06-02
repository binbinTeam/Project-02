package com.zhzteam.zhz233.mapper.zlb;

import com.zhzteam.zhz233.model.GoodsModel;
import com.zhzteam.zhz233.model.zlb.*;
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

    /**
     * selectByGoodsNo 返回 Count(id)
     * @param goodsNo
     * @param goodsType
     * @param goodsStatus
     * @return
     */
    public Integer selectByGoodsNo(@Param("goodsNo") String goodsNo,@Param("goodsType")Integer goodsType, @Param("goodsStatus") Integer goodsStatus);

    /**
     * 获取 商品 信息 selectTByGoodsNo
     * @param goodsNo
     * @return
     */
    public GoodsLeaseResult selectTByGoodsNo(@Param("goodsNo") String goodsNo);

    /**
     * 获取自增 NO
     * @return
     */
    public String selectTByAuto();

    /**
     * 添加 商品
     * @param goodsModel
     * @return
     */
    public Long insertTByKey(GoodsModel goodsModel);

    /**
     * 查询 个人 发布商品信息goods_theme
     * @param goodsType
     * @param accountNo
     * @return
     */
    public List<GoodsLeaseShow> selectListTByANOType(@Param("goodsType") Integer goodsType, @Param("accountNo") String accountNo);

    /**
     * 更新商品状态
     * @param goodsStatus
     * @param goodsNo
     */
    public void updateTByGNo(@Param("goodsStatus") Integer goodsStatus, @Param("goodsNo") String goodsNo);

    /**
     * 获取状态
     * @param goodsNo
     * @return
     */
    public Integer selectGState(@Param("goodsNo") String goodsNo);

    /**
     * 删除记录
     * @param goodsNo
     */
    public void deleteTByGNO(@Param("goodsNo") String goodsNo);
}
