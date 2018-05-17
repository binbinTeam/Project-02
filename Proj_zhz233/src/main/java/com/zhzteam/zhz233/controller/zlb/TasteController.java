package com.zhzteam.zhz233.controller.zlb;

import com.zhzteam.zhz233.common.config.StatusConfig;
import com.zhzteam.zhz233.model.zlb.GamesResult;
import com.zhzteam.zhz233.model.zlb.GoodsResult;
import com.zhzteam.zhz233.model.zlb.ResultView;
import com.zhzteam.zhz233.service.zlb.GamesService;
import com.zhzteam.zhz233.service.zlb.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/zlb")
public class TasteController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    GamesService gamesService;

    private ResultView resultView;
    private List<GoodsResult> goodsResultList;
    private List<GamesResult> gamesResultList;

    private Map<String,Object> reMap;

    @RequestMapping(value = "/getTasteAutoInfo")
    public ResultView getTasteAutoInfo(){
        //初始化 容器
        resultView = new ResultView();
        gamesResultList = new ArrayList<GamesResult>();
        goodsResultList = new ArrayList<GoodsResult>();
        reMap = new HashMap<String, Object>();

        //放置 特价体验 游戏信息 端游
        gamesResultList = gamesService.selectTByHotKey(8);
        reMap.put("HOTgamesResultList",gamesResultList);
        gamesResultList = gamesService.selectTByKey(1,8);
        reMap.put("DYgamesResultList",gamesResultList);
        gamesResultList = gamesService.selectTByKey(2,8);
        reMap.put("YYgamesResultList",gamesResultList);
        gamesResultList = gamesService.selectTByKey(3,8);
        reMap.put("SYgamesResultList",gamesResultList);
        //放置 特价体验 商品信息
        goodsResultList = goodsService.selectTByKey(4,3,1,10);
        reMap.put("goodsResultList",goodsResultList);
        if(!reMap.isEmpty()){
            resultView.setReMap(reMap);
            resultView.setStatus(StatusConfig.SUCCESS);
            resultView.setMessage("获取体验商品信息成功！");
        }else{
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("获取体验商品信息失败！");
        }
        return resultView;
    }

    @RequestMapping(value = "/getGoodsTasteRentInfo")
    public ResultView getGoodsTasteRentInfo(){
        //初始化 容器
        resultView = new ResultView();
        goodsResultList = new ArrayList<GoodsResult>();
        reMap = new HashMap<String, Object>();
        //放置 特价体验 商品信息
        goodsResultList = goodsService.selectTByKey(4,0,0,10);
        reMap.put("goodsResultList",goodsResultList);
        if(!reMap.isEmpty()){
            resultView.setReMap(reMap);
            resultView.setStatus(StatusConfig.SUCCESS);
            resultView.setMessage("获取特价体验出租商品信息成功！");
        }else{
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("获取特价体验出租商品信息失败！");
        }
        return resultView;
    }
}