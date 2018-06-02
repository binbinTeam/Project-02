package com.zhzteam.zhz233.service.zlb.impl;

import com.zhzteam.zhz233.mapper.zlb.GoodsMoreMapper;
import com.zhzteam.zhz233.model.GoodsMoreModel;
import com.zhzteam.zhz233.service.zlb.GoodsMoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsMoreServiceImpl implements GoodsMoreService {
    @Autowired
    GoodsMoreMapper goodsMoreMapper;
    @Override
    public Boolean insertTByKey(GoodsMoreModel goodsMoreModel) {
        if(goodsMoreMapper.insertTByKey(goodsMoreModel) > 0) return true;
        else return true;
    }
}
