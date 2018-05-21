package com.zhzteam.zhz233.mapper.zlb;

import com.zhzteam.zhz233.model.zlb.LeaseOrderResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LeaseOrderMapper {
    /**
     * 返回 List<LeaseOrderResult> Limit N
     * @param pagesize
     * @return
     */
    public List<LeaseOrderResult> selectTByKey(@Param("pagesize") Integer pagesize);

}
