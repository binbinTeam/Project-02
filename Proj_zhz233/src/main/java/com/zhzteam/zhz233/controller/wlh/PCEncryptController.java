package com.zhzteam.zhz233.controller.wlh;

import com.zhzteam.zhz233.common.utils.RSACoder;
import com.zhzteam.zhz233.model.GoodsRentModel;
import com.zhzteam.zhz233.model.LeaseOrderModel;
import com.zhzteam.zhz233.model.wlh.ShowPcOrderView;
import com.zhzteam.zhz233.service.wlh.PCEncryptService;
import com.zhzteam.zhz233.service.wlh.PCGoodsRentService;
import com.zhzteam.zhz233.service.wlh.PCLeaseOrderService;
import com.zhzteam.zhz233.service.zlb.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @描述
 * @参数 $params
 * @返回值 $return
 * @创建人 wenliheng
 * @创建时间 2018/5/24
 */
@RestController
@RequestMapping(value = "/wlh")
public class PCEncryptController {

    @Autowired
    PCLeaseOrderService leaseOrderService;
    @Autowired
    PCGoodsRentService goodsRentService;
    @Autowired
    RedisServiceImpl redisService;
    @Autowired
    PCEncryptService encryptService;

    @ResponseBody
    @RequestMapping(value = "/Encrypt")
    public Object Encrypt(HttpServletRequest request, HttpServletResponse response,
                          String orderNo, String access, String publicKey,String userName,String session){
        if(StringUtils.isEmpty(orderNo) || StringUtils.isEmpty(userName) || StringUtils.isEmpty(session)){
            return null;
        }
        if (redisService.exist(session)){//用户验证
            String redisUser = redisService.select(session);
            if (!redisUser.equals(userName)){
                return null;
            }
        }
        LeaseOrderModel leaseOrderPojo = leaseOrderService.findOne(orderNo);
        if(leaseOrderPojo == null){
            return null;
        }
        GoodsRentModel goodsRentPojo = goodsRentService.selectByGoodsNo(leaseOrderPojo.getGoods_no());
        if (!access.equals(goodsRentPojo.getGoods_access())){
            return null;
        }
        String passWord = goodsRentPojo.getGoods_password();
        RSACoder rsaCoder = new RSACoder();
        String[] strs = publicKey.split(" ");
        publicKey = strs[0];
        for (int i=1; i<strs.length; i++){
            publicKey += "+"+strs[i];
        }
        byte[] b = new byte[0];
        try {
            b = rsaCoder.encryptByPublicKey(passWord,publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    @ResponseBody
    @RequestMapping(value = "/getOrderInfo")
    public Object getOrderInfo(HttpServletResponse response,HttpServletRequest request,
                               String orderNo,String userName,String session){
        if (redisService.exist(session)){//用户验证
            String redisUser = redisService.select(session);
            if (!redisUser.equals(userName)){
                return null;
            }
        }
        if (StringUtils.isEmpty(orderNo)){
            return null;
        }
        ShowPcOrderView orderView = encryptService.findOrderInfo(orderNo);
        return  orderView;
    }
}
