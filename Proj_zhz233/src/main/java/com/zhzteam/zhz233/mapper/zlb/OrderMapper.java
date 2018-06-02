package com.zhzteam.zhz233.mapper.zlb;

import com.zhzteam.zhz233.model.zlb.LeaseOrderInfo;
import com.zhzteam.zhz233.model.zlb.LeaseOrderInfoResult;
import com.zhzteam.zhz233.model.zlb.LeaseOrderResult;
import com.zhzteam.zhz233.model.zlb.LeaseOrderShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    /**
     * 返回 List<LeaseOrderResult> Limit N
     * @param pagesize
     * @return
     */
    public List<LeaseOrderResult> selectTByKey(@Param("pagesize") Integer pagesize);

    /**
     * 获取自增 NO
     * @return
     */
    public String selectTByAuto();

    /**
     * 添加 订单 信息
     * @param leaseOrderInfo
     * @return
     */
    public Long insertTByKey(LeaseOrderInfo leaseOrderInfo);

    /**
     * 查询订单List
     * @param accountNo
     * @return
     */
    public List<LeaseOrderShow> selectListTByANO(@Param("accountNo")String accountNo);

    /**
     * 查询订单 卖家  List
     * @param accountNo
     * @return
     */
    public List<LeaseOrderShow> selectListTByMANO(@Param("accountNo")String accountNo);

    /**
     * 删除订单
     * @param orderNo
     * @return
     */
    public void deleteTByANO(@Param("orderNo")String orderNo, @Param("accountNo")String accountNo);
    /**
     * 删除订单
     * @param orderNo
     * @return
     */
    public void deleteTByMANO(@Param("orderNo")String orderNo, @Param("accountNo")String accountNo);
    /**
     * 查询订单
     * @param orderNo
     * @return
     */
    public LeaseOrderInfoResult selectTByANO(@Param("orderNo")String orderNo, @Param("accountNo")String accountNo);
    /**
     * 查询订单
     * @param orderNo
     * @return
     */
    public LeaseOrderInfoResult selectTByMANO(@Param("orderNo")String orderNo, @Param("accountNo")String accountNo);
    /**
     * 更新订单状态
     * @param state
     * @param orderNo
     * @param accountNo
     */
    public void updateTByANO(@Param("state")Integer state, @Param("orderNo")String orderNo, @Param("accountNo")String accountNo);
    /**
     * 更新订单状态
     * @param orderNo
     * @return
     */
    public void updateTByMANO(@Param("orderState") Integer orderState, @Param("orderNo") String orderNo, @Param("accountNo") String accountNo);
    /**
     * 查询订单
     * @param orderNo
     * @return
     */
    public LeaseOrderShow selectTByBNO(@Param("orderNo")String orderNo, @Param("accountNo")String accountNo);

    /**
     * 查询订单
     * @param orderNo
     * @return
     */
    public LeaseOrderShow selectTByCNO(@Param("orderNo")String orderNo, @Param("accountNo")String accountNo);

}
