package com.zhzteam.zhz233.mapper;

import com.zhzteam.zhz233.model.NoticeResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeMapper {
    /**
     * 返回 List<NoticeResult> Limit N
     * @param noticetype
     * @param noticerecomm
     * @return
     */
    public List<NoticeResult> selectTByKey(@Param("noticetype") Integer noticetype, @Param("noticerecomm") Integer noticerecomm,@Param("pagesize") Integer pagesize);

}
