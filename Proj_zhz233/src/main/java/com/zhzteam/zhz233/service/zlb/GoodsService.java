package com.zhzteam.zhz233.service.zlb;

import com.zhzteam.zhz233.mapper.zlb.GoodsMapper;
import com.zhzteam.zhz233.model.zlb.GoodsResult;
import com.zhzteam.zhz233.service.zlb.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService implements GoodsServiceImpl {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<GoodsResult> selectTByKey(Integer goodstype, Integer goodsrecomm, Integer goodsstatus, Integer pagesize) {
        return goodsMapper.selectTByKey(goodstype, goodsrecomm, goodsstatus, pagesize);
    }

    @Override
    public Integer selectRentTotal(Integer goodstype,Integer goodsstatus) {
        return goodsMapper.selectRentTotal(goodstype,goodsstatus);
    }
}
