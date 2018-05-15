package com.zhzteam.zhz233.mapper;

import com.zhzteam.zhz233.exception.CRUDException;

public interface BasicMapper<T>{
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
    void deleteByAutoNo(String AutoNo) throws CRUDException;

    /**
     * 更新数据
     * @param t
     * @param AutoNo
     * @throws CRUDException
     */
    void updateByAutoNo(T t,String AutoNo) throws CRUDException;

    /**
     * 查询数据
     * @param AutoNo
     * @return
     * @throws CRUDException
     */
     T selectByAutoNo(String AutoNo) throws CRUDException;
}
