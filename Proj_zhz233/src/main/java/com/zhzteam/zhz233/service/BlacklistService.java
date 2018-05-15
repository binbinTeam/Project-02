package com.zhzteam.zhz233.service;

import com.zhzteam.zhz233.mapper.BlacklistMapper;
import com.zhzteam.zhz233.model.BlacklistResult;
import com.zhzteam.zhz233.service.impl.BlacklistServiceImpl;
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
