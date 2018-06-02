package com.zhzteam.zhz233.service.zlb;

import com.zhzteam.zhz233.model.zlb.LeaseOrderInfo;
import com.zhzteam.zhz233.model.zlb.LeaseOrderInfoResult;
import com.zhzteam.zhz233.model.zlb.LeaseOrderResult;
import com.zhzteam.zhz233.model.zlb.LeaseOrderShow;

import java.util.List;

public interface OrderService {
    /**
     * 返回 List<LeaseOrderResult> Limit N
     * @param pagesize
     * @return
     */
    public List<LeaseOrderResult> selectTByKey(Integer pagesize);
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
    public Boolean insertTByKey(LeaseOrderInfo leaseOrderInfo);
    /**
     * 查询订单
     * @param accountNo
     * @return
     */
    public List<LeaseOrderShow> selectListTByANO(String accountNo);
    /**
     * 查询 卖家 订单
     * @param accountNo
     * @return
     */
    public List<LeaseOrderShow> selectListTByMANO(String accountNo);
    /**
     * 删除订单
     * @param orderNo
     * @return
     */
    public void deleteTByANO(String orderNo, String accountNo);
    /**
     * 删除订单
     * @param orderNo
     * @return
     */
    public void deleteTByMANO(String orderNo, String accountNo);
    /**
     * 查询订单
     * @param orderNo
     * @return
     */
    public LeaseOrderInfoResult selectTByANO(String orderNo, String accountNo);
    /**
     * 查询订单
     * @param orderNo
     * @return
     */
    public LeaseOrderInfoResult selectTByMANO(String orderNo, String accountNo);
    /**
     * 查询订单
     * @param orderNo
     * @return
     */
    public LeaseOrderShow selectTByBNO(String orderNo, String accountNo);
    /**
     * 更新订单状态
     * @param orderNo
     * @return
     */
    public void updateTByANO(Integer state,String orderNo, String accountNo);
    /**
     * 更新订单状态
     * @param orderNo
     * @return
     */
    public void updateTByMANO(Integer state,String orderNo, String accountNo);
    /**
     * 查询订单
     * @param orderNo
     * @return
     */
    public LeaseOrderShow selectTByCNO(String orderNo, String accountNo);

}
