package com.zhzteam.zhz233.service.zlb;

import com.zhzteam.zhz233.mapper.zlb.GamesMapper;
import com.zhzteam.zhz233.model.zlb.GamesResult;
import com.zhzteam.zhz233.model.zlb.GoodsResult;
import com.zhzteam.zhz233.service.zlb.impl.GamesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GamesService implements GamesServiceImpl {

    @Autowired
    GamesMapper gamesMapper;

    @Override
    public List<GamesResult> selectTByKey(Integer gamestype, Integer pagesize) {
        return gamesMapper.selectTByKey(gamestype, pagesize);
    }

    @Override
    public List<GamesResult> selectTByHotKey(Integer pagesize) {
        return gamesMapper.selectTByHotKey(pagesize);
    }
}
