package com.zhzteam.zhz233.service.zlb.impl;

import com.zhzteam.zhz233.exception.CRUDException;

public interface BasicServiceImpl<T> {
    /**
     * 添加数据
     * @param t
     * @return
     * @throws CRUDException
     */
    Long insert(T t) throws CRUDException;

    /**
     * 删除数据
     * @param AutoNo
     */
    void deleteByAutoNo(String AutoNo);

    /**
     * 更新数据
     * @param t
     * @param AutoNo
     * @throws CRUDException
     */
    void updateByAutoNo(T t,String AutoNo);

    /**
     * 查询数据
     * @param AutoNo
     * @return
     * @throws CRUDException
     */
    T selectByAutoNo(String AutoNo);
}
