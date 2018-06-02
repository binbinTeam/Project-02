package com.zhzteam.zhz233.controller.zlb;

import com.zhzteam.zhz233.common.config.RedisConfig;
import com.zhzteam.zhz233.common.config.SMSConfig;
import com.zhzteam.zhz233.common.config.StatusConfig;
import com.zhzteam.zhz233.common.utils.AutoIncUtils;
import com.zhzteam.zhz233.common.utils.DateTimeUtils;
import com.zhzteam.zhz233.common.utils.DecimalUtils;
import com.zhzteam.zhz233.common.utils.SMSUtils;
import com.zhzteam.zhz233.model.zlb.*;
import com.zhzteam.zhz233.service.zlb.*;
import com.zhzteam.zhz233.service.zlb.impl.GoodsServiceImpl;
import com.zhzteam.zhz233.service.zlb.impl.OrderServiceImpl;
import com.zhzteam.zhz233.service.zlb.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/zlb")
public class OrderController {
    @Autowired
    RedisServiceImpl redisService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Autowired
    AccountService accountService;
    @Autowired
    GoodsRentService goodsRentService;
    @Autowired
    InvlaService invlaService;

    private LeaseOrderInfo leaseOrderInfo;
    private GoodsLeaseResult goodsLeaseResult;
    private List<LeaseOrderShow> leaseOrderShowList;
    private LeaseOrderInfoResult leaseOrderInfoResult;
    private LeaseOrderShow leaseOrderShow;
    private AccountResult accountResult;
    private GoodsRentResult goodsRentResult;
    private SMSResult smsResult;
    private ResultView resultView;

    private Map<String,Object> reMap;

    @RequestMapping(value = "/sendLeaseOrderMSG")
    public ResultView sendLeaseOrderMSG(@RequestParam("orderNo") String orderNo,@RequestParam("uid") String uid){
        resultView = new ResultView();
        leaseOrderInfoResult = new LeaseOrderInfoResult();
        accountResult = new AccountResult();
        smsResult = new SMSResult();
        String accountNo = redisService.select(uid);
        if(accountNo != null){
            leaseOrderInfoResult = orderService.selectTByANO(orderNo,accountNo);
            accountResult = accountService.selectTByANO(accountNo);
            if(leaseOrderInfoResult != null
                    && accountResult != null
                    && accountResult.getAvailable() >= 0.1
                    && leaseOrderInfoResult.getOrder_state() == 4
                    && DateTimeUtils.Before(leaseOrderInfoResult.getOrder_end_time())
                    ){
                accountResult = accountService.selectTByANO(leaseOrderInfoResult.getBuyer_no());
                String MSG = SMSUtils.createOrderMsg(orderNo);
                smsResult = SMSUtils.execute(accountResult.getCellphone(),MSG);
                System.err.println(smsResult.getRespCode());
                //0.1 / 条
                if(smsResult.getRespCode().equals(SMSConfig.RESPCODE_SUCCESS)){
                    accountService.updateAvailableByANo(0.1,accountNo);
                    String oldNo = invlaService.selectTByAuto();
                    String newNo = AutoIncUtils.getInvlaOrderNo(oldNo);
                    invlaService.insertTByKey(newNo,accountNo,0,0.1);
                    resultView.setStatus(StatusConfig.SUCCESS);
                    resultView.setMessage("发送信息成功！");
                }else {
                    resultView.setStatus(StatusConfig.FAIL);
                    resultView.setMessage("发送信息失败！");
                }
            }else {
                resultView.setStatus(StatusConfig.FAIL);
                resultView.setMessage("操作错误！");
            }
        }else {
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("发送信息错误！");
        }
        return resultView;
    }

    @RequestMapping(value = "/getLeaseOrderManagerInfo")
    public ResultView getLeaseOrderManagerInfo(@RequestParam("uid") String uid){
        resultView = new ResultView();
        leaseOrderShowList = new ArrayList<LeaseOrderShow>();
        reMap = new HashMap<String, Object>();
        String accountNo = redisService.select(uid);
        if(accountNo != null){
            //获取 订单 列表
            leaseOrderShowList = orderService.selectListTByMANO(accountNo);
            reMap.put("leaseOrderManagerShowList",leaseOrderShowList);
            if(!reMap.isEmpty()){
                resultView.setReMap(reMap);
                resultView.setStatus(StatusConfig.SUCCESS);
                resultView.setMessage("获取订单信息成功！");
            }
        }else {
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("获取订单信息失败！");
        }
        return resultView;
    }

    @RequestMapping(value = "/payLeaseOrder")
    public ResultView payLeaseOrder(@RequestParam("orderNo") String orderNo,@RequestParam("uid") String uid){
        resultView = new ResultView();
        accountResult = new AccountResult();
        goodsRentResult = new GoodsRentResult();
        leaseOrderInfoResult = new LeaseOrderInfoResult();
        String accountNo = redisService.select(uid);
        if(accountNo != null){
            //校验操作是否合法
            leaseOrderInfoResult = orderService.selectTByANO(orderNo,accountNo);
            if(leaseOrderInfoResult != null
                    && leaseOrderInfoResult.getOrder_state() == 0
                    && leaseOrderInfoResult.getOrder_end_time() != null
                    && DateTimeUtils.Before(leaseOrderInfoResult.getOrder_end_time())){
                //获取 商品 信息
                goodsRentResult = goodsRentService.selectTByANO(leaseOrderInfoResult.getGoods_no());
                accountResult = accountService.selectTByANO(accountNo);
                if(goodsRentService != null && accountResult != null) {
                    if (accountResult.getCreditLevel() >= goodsRentResult.getGoods_credit_level()) {
                        double lf = accountResult.getAvailable() - (goodsRentResult.getGoods_compensate() + leaseOrderInfoResult.getOrder_amount());
                        if (lf >= 0) {
                            //更新账户 余额
                            accountService.updateRByMoneyANo(leaseOrderInfoResult.getOrder_amount() + goodsRentResult.getGoods_compensate(),goodsRentResult.getGoods_compensate(),accountNo);
                            //更新 账户 消费记录表
                            String oldNo = invlaService.selectTByAuto();
                            String newNo = AutoIncUtils.getInvlaOrderNo(oldNo);
                            invlaService.insertTByKey(newNo,accountNo,0,leaseOrderInfoResult.getOrder_amount());
                            oldNo = invlaService.selectTByAuto();
                            newNo = AutoIncUtils.getInvlaOrderNo(oldNo);
                            invlaService.insertTByKey(newNo,leaseOrderInfoResult.getAccount_no(),1,leaseOrderInfoResult.getOrder_amount());
                            //更新 订单状态 为 进行ING
                            orderService.updateTByANO(4,leaseOrderInfoResult.getOrder_no(),accountNo);
                            //更新商品状态
                            goodsService.updateTByGNo(3,leaseOrderInfoResult.getGoods_no());
                            resultView.setStatus(StatusConfig.SUCCESS);
                            resultView.setMessage("订单支付操作成功！");
                        } else {
                            resultView.setStatus(StatusConfig.FAIL);
                            resultView.setMessage("您的余额不足，请先充值,订单支付操作失败！");
                        }
                    } else {
                        //更新 订单状态 为 失败
                        orderService.updateTByANO(1,leaseOrderInfoResult.getOrder_no(),accountNo);
                        resultView.setStatus(StatusConfig.FAIL);
                        resultView.setMessage("您的用户等级不够,订单支付操作失败！");
                    }
                }else {
                    resultView.setStatus(StatusConfig.FAIL);
                    resultView.setMessage("用户支付信息错误！");
                }
            }else {
                //更新 订单状态 为 失效
                orderService.updateTByANO(3,leaseOrderInfoResult.getOrder_no(),accountNo);
                resultView.setStatus(StatusConfig.FAIL);
                resultView.setMessage("订单支付失败！");
            }
        }else {
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("订单支付错误！");
        }
        return resultView;
    }

    @RequestMapping(value = "/showLeaseManagerOrder")
    public ResultView showLeaseManagerOrder(@RequestParam("orderNo") String orderNo,@RequestParam("uid") String uid){
        resultView = new ResultView();
        leaseOrderShow = new LeaseOrderShow();
        reMap = new HashMap<String, Object>();
        String accountNo = redisService.select(uid);
        if(accountNo != null){
            //校验操作是否合法
            leaseOrderShow = orderService.selectTByCNO(orderNo,accountNo);
            if(leaseOrderShow != null){
                //获取 一条信息
                reMap.put("LeaseOrderManagerDetailInfo",leaseOrderShow);
                if(!reMap.isEmpty()){
                    resultView.setReMap(reMap);
                    resultView.setStatus(StatusConfig.SUCCESS);
                    resultView.setMessage("获取订单管理信息成功！");
                }else{
                    resultView.setStatus(StatusConfig.FAIL);
                    resultView.setMessage("获取订单管理信息失败！");
                }
            }else {
                resultView.setStatus(StatusConfig.FAIL);
                resultView.setMessage("获取订单管理失败！");
            }
        }else {
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("获取订单管理错误！");
        }
        return resultView;
    }

    @RequestMapping(value = "/showLeaseOrder")
    public ResultView showLeaseOrder(@RequestParam("orderNo") String orderNo,@RequestParam("uid") String uid){
        resultView = new ResultView();
        leaseOrderShow = new LeaseOrderShow();
        reMap = new HashMap<String, Object>();
        String accountNo = redisService.select(uid);
        if(accountNo != null){
            //校验操作是否合法
            leaseOrderShow = orderService.selectTByBNO(orderNo,accountNo);
            if(leaseOrderShow != null){
                //获取 一条信息
                reMap.put("LeaseOrderDetailInfo",leaseOrderShow);
                if(!reMap.isEmpty()){
                    resultView.setReMap(reMap);
                    resultView.setStatus(StatusConfig.SUCCESS);
                    resultView.setMessage("获取订单信息成功！");
                }else{
                    resultView.setStatus(StatusConfig.FAIL);
                    resultView.setMessage("获取订单信息失败！");
                }
            }else {
                resultView.setStatus(StatusConfig.FAIL);
                resultView.setMessage("获取订单失败！");
            }
        }else {
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("获取订单错误！");
        }
        return resultView;
    }

    @RequestMapping(value = "/updLeaseManagerOrder")
    public ResultView updLeaseManagerOrder(@RequestParam("orderNo") String orderNo,@RequestParam("uid") String uid){
        resultView = new ResultView();
        leaseOrderInfoResult = new LeaseOrderInfoResult();
        String accountNo = redisService.select(uid);
        if(accountNo != null){
            //校验更新操作是否合法
            leaseOrderInfoResult = orderService.selectTByMANO(orderNo,accountNo);
            if(leaseOrderInfoResult != null
                    && leaseOrderInfoResult.getOrder_state() == 4){
                goodsRentResult = goodsRentService.selectTByANO(leaseOrderInfoResult.getGoods_no());
                if(goodsRentResult != null && leaseOrderInfoResult.getOrder_end_time() != null
                        && DateTimeUtils.After(leaseOrderInfoResult.getOrder_end_time())
                        ){
                    //更新 一条信息
                    orderService.updateTByMANO(2,orderNo,accountNo);
                    //退款 押金
                    accountService.updateLByMoneyANo(goodsRentResult.getGoods_compensate(),goodsRentResult.getGoods_compensate(),leaseOrderInfoResult.getBuyer_no());
                    goodsService.updateTByGNo(1,leaseOrderInfoResult.getGoods_no());
                    resultView.setStatus(StatusConfig.SUCCESS);
                    resultView.setMessage("更新订单成功！");
                }else{
                    resultView.setStatus(StatusConfig.FAIL);
                    resultView.setMessage("更新订单失败！");
                }
            }else {
                resultView.setStatus(StatusConfig.FAIL);
                resultView.setMessage("订单未找到！");
            }
        }else {
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("更新订单错误！");
        }
        return resultView;
    }

    @RequestMapping(value = "/updLeaseOrder")
    public ResultView updLeaseOrder(@RequestParam("orderNo") String orderNo,@RequestParam("uid") String uid){
        resultView = new ResultView();
        leaseOrderInfoResult = new LeaseOrderInfoResult();
        String accountNo = redisService.select(uid);
        if(accountNo != null){
            //校验更新操作是否合法
            leaseOrderInfoResult = orderService.selectTByANO(orderNo,accountNo);
            if(leaseOrderInfoResult != null
                    && leaseOrderInfoResult.getOrder_state() == 4){
                goodsRentResult = goodsRentService.selectTByANO(leaseOrderInfoResult.getGoods_no());
                if(goodsRentResult != null && leaseOrderInfoResult.getOrder_end_time() != null
                        && DateTimeUtils.After(leaseOrderInfoResult.getOrder_end_time())
                        ){
                    //更新 一条信息
                    orderService.updateTByANO(2,orderNo,accountNo);
                    //退款 押金
                    accountService.updateLByMoneyANo(goodsRentResult.getGoods_compensate(),goodsRentResult.getGoods_compensate(),accountNo);
                    goodsService.updateTByGNo(1,leaseOrderInfoResult.getGoods_no());
                    resultView.setStatus(StatusConfig.SUCCESS);
                    resultView.setMessage("更新订单成功！");
                }else{
                    resultView.setStatus(StatusConfig.FAIL);
                    resultView.setMessage("更新订单失败！");
                }
            }else {
                resultView.setStatus(StatusConfig.FAIL);
                resultView.setMessage("订单未找到！");
            }
        }else {
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("更新订单错误！");
        }
        return resultView;
    }

    @RequestMapping(value = "/delLeaseManagerOrder")
    public ResultView delLeaseManagerOrder(@RequestParam("orderNo") String orderNo,@RequestParam("uid") String uid){
        resultView = new ResultView();
        leaseOrderInfoResult = new LeaseOrderInfoResult();
        String accountNo = redisService.select(uid);
        if(accountNo != null){
            //校验删除操作是否合法
            leaseOrderInfoResult = orderService.selectTByMANO(orderNo,accountNo);
            if(leaseOrderInfoResult != null
                    && leaseOrderInfoResult.getOrder_state()!= 4
                    && leaseOrderInfoResult.getOrder_state()!= 2){
                //删除 一条信息
                orderService.deleteTByMANO(orderNo,accountNo);
                resultView.setStatus(StatusConfig.SUCCESS);
                resultView.setMessage("删除订单成功！");
            }else {
                resultView.setStatus(StatusConfig.FAIL);
                resultView.setMessage("删除订单失败！");
            }
        }else {
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("删除订单错误！");
        }
        return resultView;
    }

    @RequestMapping(value = "/delLeaseOrder")
    public ResultView delLeaseOrder(@RequestParam("orderNo") String orderNo,@RequestParam("uid") String uid){
        resultView = new ResultView();
        leaseOrderInfoResult = new LeaseOrderInfoResult();
        String accountNo = redisService.select(uid);
        if(accountNo != null){
            //校验删除操作是否合法
            leaseOrderInfoResult = orderService.selectTByANO(orderNo,accountNo);
            if(leaseOrderInfoResult != null
                    && leaseOrderInfoResult.getOrder_state()!= 4
                    && leaseOrderInfoResult.getOrder_state()!= 2){
                //删除 一条信息
                orderService.deleteTByANO(orderNo,accountNo);
                resultView.setStatus(StatusConfig.SUCCESS);
                resultView.setMessage("删除订单成功！");
            }else {
                resultView.setStatus(StatusConfig.FAIL);
                resultView.setMessage("删除订单失败！");
            }
        }else {
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("删除订单错误！");
        }
        return resultView;
    }

    @RequestMapping(value = "/getLeaseOrderInfo")
    public ResultView getLeaseOrderInfo(@RequestParam("uid") String uid){
        resultView = new ResultView();
        leaseOrderShowList = new ArrayList<LeaseOrderShow>();
        reMap = new HashMap<String, Object>();
        String accountNo = redisService.select(uid);
        if(accountNo != null){
            //获取 订单 列表
            leaseOrderShowList = orderService.selectListTByANO(accountNo);
            reMap.put("leaseOrderShowList",leaseOrderShowList);
            if(!reMap.isEmpty()){
                resultView.setReMap(reMap);
                resultView.setStatus(StatusConfig.SUCCESS);
                resultView.setMessage("获取订单信息成功！");
            }
        }else {
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("获取订单信息失败！");
        }
        return resultView;
    }

    @RequestMapping(value = "/addRentOrder")
    public ResultView addRentOrder(@RequestParam("goods_no") String goods_no,
                                   @RequestParam("goods_time") Integer goods_time,
                                   @RequestParam("goods_night") Integer goods_night,
                                   @RequestParam("goods_day") Integer goods_day,
                                   @RequestParam("goods_week") Integer goods_week,
                                   @RequestParam("uid") String uid){
        resultView = new ResultView();
        leaseOrderInfo = new LeaseOrderInfo();
        goodsLeaseResult = new GoodsLeaseResult();
        reMap = new HashMap<String, Object>();
        if(goods_no != null
                && goods_time>= 0
                && goods_night >= 0
                && goods_day >= 0
                && goods_week >= 0
                && redisService.exist(uid)){
            if(goodsService.selectByGoodsNo(goods_no,1,1)){//检测商品有效性
                Date startTime = new Date();
                Date endTime = new Date();
                Double amount = 0.00;
                //获取 商品 信息 所属人
                goodsLeaseResult = goodsService.selectTByGoodsNo(goods_no);
                if(goods_week > 0){
                    amount = DecimalUtils.mul(goodsLeaseResult.getGoods_week(),goods_week,2);
                    startTime = DateTimeUtils.getDateTime();
                    endTime = DateTimeUtils.addDay(7 * goods_week);
                }else if(goods_day > 0){
                    amount = DecimalUtils.mul(goodsLeaseResult.getGoods_day(),goods_day,2);
                    startTime = DateTimeUtils.getDateTime();
                    endTime = DateTimeUtils.addDay(goods_day);
                }else if(goods_night > 0){
                    amount = DecimalUtils.mul(goodsLeaseResult.getGoods_night(),goods_night,2);
                    startTime = DateTimeUtils.getDateTime(22,0,0);
                    endTime = DateTimeUtils.getDateTime(22 + 10 ,0,0);
                }else {
                    amount = DecimalUtils.mul(goodsLeaseResult.getGoods_amount(),goods_time,2);
                    startTime = DateTimeUtils.getDateTime();
                    endTime = DateTimeUtils.addHour(goods_time);
                }
                //获取 商品 购买人
                String buyerNo = redisService.select(uid);
                //获取原 No
                String oldNo = orderService.selectTByAuto();
                //获取 订单 No
                String orderNo = AutoIncUtils.getLeaseOrderNo(oldNo);

                //设置 订单 信息
                leaseOrderInfo.setOrder_no(orderNo);
                leaseOrderInfo.setGoods_no(goodsLeaseResult.getGoods_no());
                leaseOrderInfo.setAccount_no(goodsLeaseResult.getAccount_no());
                leaseOrderInfo.setBuyer_no(buyerNo);
                leaseOrderInfo.setOrder_amount(amount);
                leaseOrderInfo.setOrder_start_time(startTime);
                leaseOrderInfo.setOrder_end_time(endTime);
                leaseOrderInfo.setOrder_state(0);
                leaseOrderInfo.setUpdate_time(new Date());
                leaseOrderInfo.setCreate_time(new Date());
                //插入数据库
                if(orderService.insertTByKey(leaseOrderInfo)){
                    //放入 缓存 或者 数据库
                    //redisServiceImpl.set("test",leaseOrderInfo,RedisConfig.REDIS_TIME_30MINUTE);
                    resultView.setStatus(StatusConfig.SUCCESS);
                    resultView.setMessage("添加订单成功！");
                }else {
                    resultView.setStatus(StatusConfig.FAIL);
                    resultView.setMessage("添加订单失败！");
                }
            }else {
                resultView.setStatus(StatusConfig.FAIL);
                resultView.setMessage("添加订单失败！");
            }
        }else{
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("添加订单失败！");
        }
        return resultView;
    }
}
