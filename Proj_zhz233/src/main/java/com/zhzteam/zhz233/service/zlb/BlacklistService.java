package com.zhzteam.zhz233.service.zlb;

import com.zhzteam.zhz233.mapper.zlb.BlacklistMapper;
import com.zhzteam.zhz233.model.zlb.BlacklistResult;
import com.zhzteam.zhz233.service.zlb.impl.BlacklistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlacklistService implements BlacklistServiceImpl {
    @Autowired
    BlacklistMapper blacklistMapper;

    @Override
    public List<BlacklistResult> selectTByKey(Integer pagesize) {
        return blacklistMapper.selectTByKey(pagesize);
    }
}
