package com.zhzteam.zhz233.service.zlb;

import com.zhzteam.zhz233.model.GoodsModel;
import com.zhzteam.zhz233.model.zlb.GoodsLeaseResult;
import com.zhzteam.zhz233.model.zlb.GoodsLeaseShow;
import com.zhzteam.zhz233.model.zlb.GoodsRentMoreResult;
import com.zhzteam.zhz233.model.zlb.GoodsResult;

import java.util.List;

public interface GoodsService {
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

    /**
     * selectByGoodsNo 返回 Count(id)
     * @param goodsNo
     * @param goodsType
     * @param goodsStatus
     * @return
     */
    public Boolean selectByGoodsNo(String goodsNo,Integer goodsType, Integer goodsStatus);
    /**
     * 获取 商品 信息 selectTByGoodsNo
     * @param goodsNo
     * @return
     */
    public GoodsLeaseResult selectTByGoodsNo(String goodsNo);
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
    public Boolean insertTByKey(GoodsModel goodsModel);
    /**
     * 查询 个人 发布商品信息
     * @param goodsType
     * @param accountNo
     * @return
     */
    public List<GoodsLeaseShow> selectListTByANOType(Integer goodsType, String accountNo);
    /**
     * 更新商品状态
     * @param goodsStatus
     * @param goodsNo
     */
    public void updateTByGNo( Integer goodsStatus, String goodsNo);
    /**
     * 获取状态
     * @param goodsNo
     * @return
     */
    public Integer selectGState(String goodsNo);

    /**
     * 删除记录
     * @param goodsNo
     */
    public void deleteTByGNO(String goodsNo);
}
