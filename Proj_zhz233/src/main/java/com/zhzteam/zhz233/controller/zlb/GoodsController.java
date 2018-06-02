package com.zhzteam.zhz233.controller.zlb;

import com.zhzteam.zhz233.common.config.StatusConfig;
import com.zhzteam.zhz233.common.utils.AutoIncUtils;
import com.zhzteam.zhz233.model.GoodsModel;
import com.zhzteam.zhz233.model.GoodsMoreModel;
import com.zhzteam.zhz233.model.GoodsRentModel;
import com.zhzteam.zhz233.model.zlb.*;
import com.zhzteam.zhz233.service.zlb.GoodsMoreService;
import com.zhzteam.zhz233.service.zlb.GoodsRentService;
import com.zhzteam.zhz233.service.zlb.GoodsService;
import com.zhzteam.zhz233.service.zlb.impl.GoodsServiceImpl;
import com.zhzteam.zhz233.service.zlb.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping(value = "/zlb")
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsRentService goodsRentService;
    @Autowired
    GoodsMoreService goodsMoreService;
    @Autowired
    RedisServiceImpl redisService;

    private ResultView resultView;
    private List<GoodsResult> goodsResultList;
    private GoodsModel goodsModel;
    private GoodsRentModel goodsRentModel;
    private GoodsMoreModel goodsMoreModel;
    private List<GoodsLeaseShow> goodsLeaseShowList;

    private Map<String,Object> reMap;

    @RequestMapping(value = "/delLeaseGoodsManager")
    public ResultView delLeaseGoodsManager(@RequestParam("goodsNo") String goodsNo,@RequestParam("uid") String uid){
        resultView = new ResultView();
        String accountNo = redisService.select(uid);
        if(accountNo != null){
             Integer goodsState = goodsService.selectGState(goodsNo);
             if(goodsState != null && (goodsState == 0 || goodsState == 2)){
                 goodsService.deleteTByGNO(goodsNo);
                 resultView.setStatus(StatusConfig.SUCCESS);
                 resultView.setMessage("删除商品成功！");
             }else {
                 resultView.setStatus(StatusConfig.FAIL);
                 resultView.setMessage("删除商品失败！");
             }
        }else {
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("删除商品错误！");
        }
        return resultView;
    }

    @RequestMapping(value = "/getLeaseGoodsShowInfo")
    public ResultView getLeaseGoodsShowInfo(@RequestParam("uid") String uid){
        resultView = new ResultView();
        goodsLeaseShowList = new ArrayList<GoodsLeaseShow>();
        reMap = new HashMap<String, Object>();
        String accountNo = redisService.select(uid);
        goodsLeaseShowList = goodsService.selectListTByANOType(1,accountNo);
        reMap.put("goodsLeaseShowList",goodsLeaseShowList);
        if(!reMap.isEmpty()){
            resultView.setReMap(reMap);
            resultView.setStatus(StatusConfig.SUCCESS);
            resultView.setMessage("获取个人发布信息成功！");
        }else{
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("获取个人发布信息失败！");
        }
        return resultView;
    }

    @RequestMapping(value = "/addLeaseGoodsInfo")
    public ResultView addLeaseGoodsInfo(@RequestParam("uid") String uid,
            @RequestParam("goods_theme") String goods_theme,@RequestParam("goods_content") String goods_content,
            @RequestParam("goods_game") String goods_game,@RequestParam("goods_hour") Double goods_hour,
            @RequestParam("goods_night") Double goods_night,@RequestParam("goods_day") Double goods_day,
            @RequestParam("goods_week") Double goods_week,@RequestParam("goods_access") String goods_access,
            @RequestParam("goods_password") String goods_password,@RequestParam("goods_logon_style") Integer goods_logon_style,
            @RequestParam("goods_compensate") Double goods_compensate,@RequestParam("goods_beforehand") Integer goods_beforehand,
            @RequestParam("goods_delay") Integer goods_delay,@RequestParam("goods_short_time") Integer goods_short_time,
            @RequestParam("goods_credit_level") Integer goods_credit_level,@RequestParam("goods_role") String goods_role,
            @RequestParam("goods_area") String goods_area,@RequestParam("goods_server") String goods_server,
            @RequestParam("goods_grade") Integer goods_grade,@RequestParam("goods_rank") Integer goods_rank,
            @RequestParam("goods_case") Integer goods_case,@RequestParam("goods_acces") Integer goods_acces){
        resultView = new ResultView();
        goodsModel = new GoodsModel();
        goodsRentModel = new GoodsRentModel();
        goodsMoreModel = new GoodsMoreModel();
        //设置基础信息
        String oldGoodsNo = goodsService.selectTByAuto();
        String newGoodsNo = AutoIncUtils.getGoodsNo(oldGoodsNo);
        String accountNo = redisService.select(uid);
        goodsModel.setGoods_no(newGoodsNo);
        goodsModel.setAccount_no(accountNo);
        goodsModel.setGoods_type(1);
        goodsModel.setGoods_game(goods_game);
        goodsModel.setGoods_theme(goods_theme);
        goodsModel.setGoods_content(goods_content);
        goodsModel.setGoods_amount(goods_hour);
        goodsModel.setGoods_recomm(0);
        goodsModel.setGoods_status(0);
        //设置出租信息
        goodsRentModel.setGoods_no(newGoodsNo);
        goodsRentModel.setGoods_game(goods_game);
        goodsRentModel.setGoods_hour(goods_hour);
        goodsRentModel.setGoods_night(goods_night);
        goodsRentModel.setGoods_day(goods_day);
        goodsRentModel.setGoods_week(goods_week);
        goodsRentModel.setGoods_month(4 * 9 * goods_week);
        goodsRentModel.setGoods_year(12 * 8 * goods_week * 4);
        goodsRentModel.setGoods_access(goods_access);
        goodsRentModel.setGoods_password(goods_password);
        goodsRentModel.setGoods_logon_style(goods_logon_style);
        goodsRentModel.setGoods_compensate(goods_compensate);
        goodsRentModel.setGoods_beforehand(goods_beforehand);
        goodsRentModel.setGoods_delay(goods_delay);
        goodsRentModel.setGoods_short_time(goods_short_time);
        goodsRentModel.setGoods_credit_level(goods_credit_level);
        //设置更多 信息
        goodsMoreModel.setGoods_no(newGoodsNo);
        goodsMoreModel.setGoods_game(goods_game);
        goodsMoreModel.setGoods_role(goods_role);
        goodsMoreModel.setGoods_area(goods_area);
        goodsMoreModel.setGoods_server(goods_server);
        goodsMoreModel.setGoods_grade(goods_grade);
        goodsMoreModel.setGoods_rank(goods_rank);
        goodsMoreModel.setGoods_access(goods_acces);
        goodsMoreModel.setGoods_case(goods_case);
        //添加 信息
        if(goodsService.insertTByKey(goodsModel)
                && goodsRentService.insertTByKey(goodsRentModel)
                && goodsMoreService.insertTByKey(goodsMoreModel)){
            resultView.setStatus(StatusConfig.SUCCESS);
            resultView.setMessage("添加商品信息成功！");
        }else{
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("添加商品信息失败！");
        }
        return resultView;
    }

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
