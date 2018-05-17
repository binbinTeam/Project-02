package com.zhzteam.zhz233.controller.zlb;

import com.zhzteam.zhz233.common.config.StatusConfig;
import com.zhzteam.zhz233.model.zlb.GoodsRentResult;
import com.zhzteam.zhz233.model.zlb.GoodsResult;
import com.zhzteam.zhz233.model.zlb.ResultView;
import com.zhzteam.zhz233.service.zlb.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/zlb")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    private ResultView resultView;
    private List<GoodsResult> goodsResultList;

    private Map<String,Object> reMap;

    @RequestMapping(value = "/getGoodsInfo")
    public ResultView getGoodsInfo(){
        //初始化 容器
        resultView = new ResultView();
        goodsResultList = new ArrayList<GoodsResult>();
        reMap = new HashMap<String, Object>();
        //放置商品信息
        goodsResultList = goodsService.selectTByKey(0,0,0,10);
        reMap.put("goodsResultList",goodsResultList);
        if(!reMap.isEmpty()){
            resultView.setReMap(reMap);
            resultView.setStatus(StatusConfig.SUCCESS);
            resultView.setMessage("获取商品信息成功！");
        }else{
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("获取商品信息失败！");
        }
        return resultView;
    }
}
