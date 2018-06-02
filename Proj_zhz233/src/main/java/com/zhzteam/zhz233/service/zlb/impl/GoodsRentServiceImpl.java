package com.zhzteam.zhz233.service.zlb.impl;

import com.zhzteam.zhz233.mapper.zlb.GoodsRentMapper;
import com.zhzteam.zhz233.model.GoodsRentModel;
import com.zhzteam.zhz233.model.zlb.GoodsRentResult;
import com.zhzteam.zhz233.service.zlb.GoodsRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsRentServiceImpl implements GoodsRentService {
    @Autowired
    GoodsRentMapper goodsRentMapper;

    @Override
    public List<GoodsRentResult> selectTByKey(Integer goodstype, Integer goodsrecomm, Integer goodsstatus, Integer pagesize) {
        return goodsRentMapper.selectTByKey(goodstype, goodsrecomm, goodsstatus, pagesize);
    }

    @Override
    public Boolean insertTByKey(GoodsRentModel goodsRentModel) {
        if(goodsRentMapper.insertTByKey(goodsRentModel) > 0) return true;
        return false;
    }

    @Override
    public GoodsRentResult selectTByANO(String goodsNo) {
        return goodsRentMapper.selectTByANO(goodsNo);
    }
}
