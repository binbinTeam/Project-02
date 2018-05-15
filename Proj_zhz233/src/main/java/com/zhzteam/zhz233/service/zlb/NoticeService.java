package com.zhzteam.zhz233.service.zlb;

import com.zhzteam.zhz233.mapper.zlb.NoticeMapper;
import com.zhzteam.zhz233.model.zlb.NoticeResult;
import com.zhzteam.zhz233.service.zlb.impl.NoticeServiceImpl;
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
