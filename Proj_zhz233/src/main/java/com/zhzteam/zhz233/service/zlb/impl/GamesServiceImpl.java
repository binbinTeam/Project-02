package com.zhzteam.zhz233.service.zlb.impl;

import com.zhzteam.zhz233.model.zlb.GamesResult;
import com.zhzteam.zhz233.model.zlb.GoodsResult;

import java.util.List;

public interface GamesServiceImpl {
    /**
     * 返回 List<GamesResult> Limit N
     * @param gamestype
     * @param pagesize
     * @return
     */
    public List<GamesResult> selectTByKey(Integer gamestype, Integer pagesize);
    /**
     * 返回 List<GamesResult> Limit N
     * @param pagesize
     * @return
     */
    public List<GamesResult> selectTByHotKey(Integer pagesize);

}
