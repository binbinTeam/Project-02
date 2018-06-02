package com.zhzteam.zhz233.service.zlb.impl;

import com.zhzteam.zhz233.mapper.zlb.GoodsMapper;
import com.zhzteam.zhz233.model.GoodsModel;
import com.zhzteam.zhz233.model.zlb.GoodsLeaseResult;
import com.zhzteam.zhz233.model.zlb.GoodsLeaseShow;
import com.zhzteam.zhz233.model.zlb.GoodsResult;
import com.zhzteam.zhz233.service.zlb.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

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

    @Override
    public Boolean selectByGoodsNo(String goodsNo,Integer goodsType,Integer goodsStatus) {
        if(goodsMapper.selectByGoodsNo(goodsNo,goodsType,goodsStatus) > 0) return true;
        else return false;
    }

    @Override
    public GoodsLeaseResult selectTByGoodsNo(String goodsNo) {
        return goodsMapper.selectTByGoodsNo(goodsNo);
    }

    @Override
    public String selectTByAuto() {
        return goodsMapper.selectTByAuto();
    }

    @Override
    public Boolean insertTByKey(GoodsModel goodsModel) {
        if(goodsMapper.insertTByKey(goodsModel) > 0) return true;
        return false;
    }

    @Override
    public List<GoodsLeaseShow> selectListTByANOType(Integer goodsType, String accountNo) {
        return goodsMapper.selectListTByANOType(goodsType, accountNo);
    }

    @Override
    public void updateTByGNo(Integer goodsStatus, String goodsNo) {
        goodsMapper.updateTByGNo(goodsStatus, goodsNo);
    }

    @Override
    public Integer selectGState(String goodsNo) {
        return goodsMapper.selectGState(goodsNo);
    }

    @Override
    public void deleteTByGNO(String goodsNo) {
        goodsMapper.deleteTByGNO(goodsNo);
    }

}
