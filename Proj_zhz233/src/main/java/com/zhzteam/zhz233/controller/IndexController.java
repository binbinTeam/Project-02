package com.zhzteam.zhz233.controller;

import com.zhzteam.zhz233.common.config.StatusConfig;
import com.zhzteam.zhz233.model.BlacklistResult;
import com.zhzteam.zhz233.model.NoticeResult;
import com.zhzteam.zhz233.model.PathResult;
import com.zhzteam.zhz233.model.ResultView;
import com.zhzteam.zhz233.service.BlacklistService;
import com.zhzteam.zhz233.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {
    @Autowired
    NoticeService noticeService;
    @Autowired
    BlacklistService blacklistService;

    private ResultView resultView;

    private List<NoticeResult> noticeResultList;
    private List<BlacklistResult> blacklistResultList;

    private Map<String,Object> reMap;

    @RequestMapping(value = "/getIndexAutoInfo",method = RequestMethod.GET)
    public ResultView getIndexInfo(){
        //初始化 容器
        resultView = new ResultView();
        noticeResultList = new ArrayList<NoticeResult>();
        reMap = new HashMap<String, Object>();
        //公告活动
        noticeResultList = noticeService.selectTByKey(1,1,5);
        reMap.put("noticeList",noticeResultList);
        //入门须知
        noticeResultList = noticeService.selectTByKey(2,1,5);
        reMap.put("newbieList",noticeResultList);
        //系统更新
        noticeResultList = noticeService.selectTByKey(3,1,5);
        reMap.put("updateList",noticeResultList);
        //获取黑名单
        blacklistResultList = blacklistService.selectTByKey(5);
        reMap.put("blackList",blacklistResultList);
        if(!reMap.isEmpty()){
                resultView.setReMap(reMap);
                resultView.setStatus(StatusConfig.SUCCESS);
                resultView.setMessage("获取信息成功！");
        }else{
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("获取信息失败！");
        }
        return resultView;
    }

    @RequestMapping("/index")
    public PathResult index(){
        PathResult  pathResult = new PathResult();
        pathResult.setPath("/index");
        pathResult.setStatus(StatusConfig.SUCCESS);
        return pathResult;
    }
}
