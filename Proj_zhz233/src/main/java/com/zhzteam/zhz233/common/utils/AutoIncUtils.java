package com.zhzteam.zhz233.common.utils;

public class AutoIncUtils{


    /**
     * 20180404 03 000000000001
     * @param oldNo
     * @return
     */
    public static String getLeaseOrderNo(String oldNo){
        Long autoNo = 1L;
        String autoDT = DateTimeUtils.getPatternYMD();
        if(oldNo != null && !oldNo.isEmpty() && !oldNo.equals("")) {
            if(autoDT.equals(oldNo.substring(0, 8))) {
                autoNo = Long.parseLong(oldNo.substring(11, 22)) + 1;
            }
        }
        return autoDT + "03" +String.format("%012d", autoNo);
    }
    /**
     * 生成自增No
     * @param oldNo
     * @return
     */
    public static String getAccountNo(String oldNo){
        Long autoNo = 1L;
        String autoDT = DateTimeUtils.getPatternY();
        if(oldNo != null && !oldNo.isEmpty() && !oldNo.equals("")) {
            if(autoDT.equals(oldNo.substring(0, 4))) {
                autoNo = Long.parseLong(oldNo.substring(5, 12)) + 1;
            }
        }
        return autoDT + String.format("%08d", autoNo);
    }
}
