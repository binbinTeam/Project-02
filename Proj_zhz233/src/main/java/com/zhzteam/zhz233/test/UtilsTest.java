package com.zhzteam.zhz233.test;

import com.zhzteam.zhz233.common.utils.AutoIncUtils;
import com.zhzteam.zhz233.common.utils.GsonUtils;
import com.zhzteam.zhz233.common.utils.JWTUtils;
import com.zhzteam.zhz233.model.JWTModel;
import com.zhzteam.zhz233.model.zlb.JWTResult;
import org.junit.Test;

public class UtilsTest {
    @Test
    public void Test1(){
        String autoNo = AutoIncUtils.getAccountNo("");
    }
    @Test
    public void Test0() {
        //String jwtStr = JWTUtils.createJWT(JWTConfig.JWT_ISS, "", "1", "", JWTConfig.JWT_TIME_30MINUTE);
        String jwtStr = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJaSFpUZWFtIiwiYXVkIjoienVtZW5nMjAxOCIsImp0aSI6IjIwMTgwMDAwMDAwMyIsInN1YiI6IlpIWjIzM19VU0VSX0FVVEgwIiwiaWF0IjoxNTI2MzEyMDMwLCJleHAiOjE1MjYzMTM4MzB9.hPGoOmzTXGdg8aYV42k-GhixEb0VbTorWhul7yNEvP8";
        JWTResult jwtResult = JWTUtils.validateJWT(jwtStr);
        System.err.println(jwtResult.getStatus());
        String claims = GsonUtils.objectToJsonStr(jwtResult.getClaims());
        JWTModel jwtModel = GsonUtils.jsonStrToObject(claims, JWTModel.class);
        System.err.println(jwtStr);
        System.err.println(claims);
        System.err.println(jwtModel.getJti());
    }
}
