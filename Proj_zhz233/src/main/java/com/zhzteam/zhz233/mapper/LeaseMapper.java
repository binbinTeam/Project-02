package com.zhzteam.zhz233.mapper;

import com.zhzteam.zhz233.model.LeaseResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LeaseMapper {
    /**
     * 返回 List<LeaseResult> Limit N
     * @param pagesize
     * @return
     */
    public List<LeaseResult> selectTByKey(@Param("pagesize") Integer pagesize);

}
