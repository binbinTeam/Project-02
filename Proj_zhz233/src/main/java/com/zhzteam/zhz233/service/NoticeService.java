package com.zhzteam.zhz233.service;

import com.zhzteam.zhz233.mapper.NoticeMapper;
import com.zhzteam.zhz233.model.NoticeResult;
import com.zhzteam.zhz233.service.impl.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService implements NoticeServiceImpl {
    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public List<NoticeResult> selectTByKey(Integer noticetype, Integer noticerecomm, Integer pagesize) {
        return noticeMapper.selectTByKey(noticetype, noticerecomm, pagesize);
    }
}
