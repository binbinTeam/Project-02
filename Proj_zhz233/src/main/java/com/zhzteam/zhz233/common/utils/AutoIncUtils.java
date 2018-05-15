package com.zhzteam.zhz233.common.utils;

public class AutoIncUtils{
    /**
     * 生成自增No
     * @param oldNo
     * @return
     */
    public static String getAccountNo(String oldNo){
        Long autoNo = 1L;
        String autoDT = DateTimeUtils.getPatternY();
        if(oldNo != null) {
            if(autoDT.equals(oldNo.substring(0, 4))) {
                autoNo = Long.parseLong(oldNo.substring(5, 12)) + 1;
            }
        }
        return autoDT + String.format("%08d", autoNo);
    }
}
