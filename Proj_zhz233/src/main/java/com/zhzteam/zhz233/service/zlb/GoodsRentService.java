package com.zhzteam.zhz233.service.zlb;

import com.zhzteam.zhz233.mapper.zlb.GoodsRentMapper;
import com.zhzteam.zhz233.model.zlb.GoodsRentResult;
import com.zhzteam.zhz233.service.zlb.impl.GoodsRentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsRentService implements GoodsRentServiceImpl {
    @Autowired
    GoodsRentMapper goodsRentMapper;

    @Override
    public List<GoodsRentResult> selectTByKey(Integer goodstype, Integer goodsrecomm, Integer goodsstatus, Integer pagesize) {
        return goodsRentMapper.selectTByKey(goodstype, goodsrecomm, goodsstatus, pagesize);
    }
}
