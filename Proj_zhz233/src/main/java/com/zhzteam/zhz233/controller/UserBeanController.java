package com.zhzteam.zhz233.controller;

import com.zhzteam.zhz233.common.config.*;
import com.zhzteam.zhz233.common.utils.*;
import com.zhzteam.zhz233.model.*;
import com.zhzteam.zhz233.service.RedisService;
import com.zhzteam.zhz233.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserBeanController {
    @Autowired
    RedisService redisService;

    @Autowired
    UserService userService;


    private ResultView resultView;
    private LogonResult logonResult;
    private CodeResult codeResult;
    private SMSResult smsResult;

    private Map<String,Object> reMap;

    @RequestMapping(value = "/userBean/getUserLeaseOrderInfo",method = RequestMethod.GET)
    public ResultView getUserLeaseOrderInfo(HttpServletRequest hsRequest){
        //初始化 容器
        resultView = new ResultView();
        reMap = new HashMap<String, Object>();
        //获取 Cookie
        Cookie cookie = CookieUtils.getCookieByName(hsRequest,"token");
        if(cookie != null){
            if(1==1){
                resultView.setStatus(StatusConfig.SUCCESS);
                resultView.setMessage("获取用户订单信息成功！");
            }else{
                resultView.setStatus(StatusConfig.FAIL);
                resultView.setMessage("获取用户订单信息失败！");
            }
        }else{
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("获取信息失败！");
        }
        return resultView;
    }

    @RequestMapping(value = "/userBean/getUserInfo",method = RequestMethod.GET)
    public ResultView register(HttpServletRequest hsRequest){
        //初始化 容器
        resultView = new ResultView();
        reMap = new HashMap<String, Object>();
        //获取 Cookie
        Cookie cookie = CookieUtils.getCookieByName(hsRequest,"uid");
        if(cookie != null){
            if(redisService.exist(cookie.getValue())){
                //读取 数据

                //放置User 信息
                reMap.put("UserInfo","");
                resultView.setReMap(reMap);
                resultView.setStatus(StatusConfig.SUCCESS);
                resultView.setMessage("获取用户信息成功！");
            }else{
                resultView.setStatus(StatusConfig.FAIL);
                resultView.setMessage("获取用户信息失败！");
            }
        }else{
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("用户未登录，请登录后重试！");
        }
        return resultView;
    }

    @RequestMapping(value = "/userBean/getCode",method = RequestMethod.GET)
    public ResultView getCode(@Param("cellphone") String cellphone){
        //初始化对象
        resultView = new ResultView();
        codeResult = new CodeResult();
        smsResult = new SMSResult();

        if(cellphone != null){//验证数据 合法性
            if(REVUtils.isLogonPhone(cellphone)){
                codeResult = SMSUtils.cerateMsg(6);//获取信息组合
                smsResult = SMSUtils.execute(cellphone, codeResult.getMsg());
                if(smsResult != null && smsResult.getRespCode() != null){
                    if(smsResult.getRespCode().equals(SMSConfig.RESPCODE_SUCCESS)){
                        if(redisService.exist(cellphone)){
                            redisService.drop(cellphone);
                        }
                        redisService.insert(cellphone,codeResult.getCode(),RedisConfig.REDIS_TIME_30MINUTE);
                        resultView.setStatus(StatusConfig.SUCCESS);
                        resultView.setMessage("验证码发送成功！");
                    }else{
                        resultView.setStatus(StatusConfig.FAIL);
                        resultView.setMessage("发送频率过快，请稍后再试！");
                    }
                }else{
                    resultView.setStatus(StatusConfig.FAIL);
                    resultView.setMessage("网络异常！");
                }
            }else{
                resultView.setStatus(StatusConfig.FAIL);
                resultView.setMessage("手机号错误！");
            }
        }else{
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("验证码获取失败！");
        }
        return resultView;
    }

    @RequestMapping(value = "/userBean/register",method = RequestMethod.GET)
    public ResultView register(HttpServletRequest hsRequest, HttpServletResponse hsResponse, RegisterInfo registerInfo) {
        HttpSession httpSession = hsRequest.getSession();
        //初始化对象
        resultView = new ResultView();
        logonResult = new LogonResult();
        if(registerInfo != null){//验证 数据 合法性
            if(registerInfo.getUsername() != null && REVUtils.isLogonInfo(registerInfo.getUsername())){
                if(registerInfo.getCellphone() != null && REVUtils.isLogonPhone(registerInfo.getCellphone())){
                    if(registerInfo.getPassword() != null && REVUtils.isLogonInfo(registerInfo.getPassword())){
                        if(registerInfo.getCode() != null && REVUtils.isLogonInfo(registerInfo.getCode())){
                            if(!userService.selectTByUserName(registerInfo.getUsername())){
                                if(!userService.selectTByCellPhone(registerInfo.getCellphone())){
                                    if(redisService.exist(registerInfo.getCellphone())){
                                        if(redisService.select(registerInfo.getCellphone()).equals(registerInfo.getCode())){
                                           //去除缓存
                                            redisService.drop(registerInfo.getCellphone());
                                            //获取Auto 自增编号
                                            String autoNo = AutoIncUtils.getAccountNo(userService.selectTByAuto());
                                            //注册 一条信息
                                            Boolean registerFlag = userService.insertTByKey(
                                                    registerInfo.getUsername(),
                                                    registerInfo.getCellphone(),
                                                    registerInfo.getPassword(),
                                                    autoNo);
                                            if(registerFlag){//判断注册成功
                                                logonResult = userService.selectTByKey(registerInfo.getUsername(), registerInfo.getPassword());
                                                if(logonResult != null) {//判断数据库数据 验证
                                                    String uid= httpSession.getId();
                                                    CookieUtils.setCookie(hsResponse, "uid", uid, CookieConfig.COOKIE_30MINUTE);
                                                    resultView.setStatus(StatusConfig.SUCCESS);
                                                    resultView.setMessage("登录成功！");
                                                }else{
                                                    resultView.setStatus(StatusConfig.SUCCESS);
                                                    resultView.setMessage("注册成功！");
                                                }
                                            }else{
                                                resultView.setStatus(StatusConfig.FAIL);
                                                resultView.setMessage("注册失败！");
                                            }
                                        }else{
                                            resultView.setStatus(StatusConfig.FAIL);
                                            resultView.setMessage("验证码错误！");
                                        }
                                    }else{
                                        resultView.setStatus(StatusConfig.FAIL);
                                        resultView.setMessage("验证码无效！");
                                    }
                                }else{
                                    resultView.setStatus(StatusConfig.FAIL);
                                    resultView.setMessage("手机号已存在！");
                                }
                            }else{
                                resultView.setStatus(StatusConfig.FAIL);
                                resultView.setMessage("用户名已存在！");
                            }
                        }else{
                            resultView.setStatus(StatusConfig.FAIL);
                            resultView.setMessage("验证码信息错误！");
                        }
                    }else{
                        resultView.setStatus(StatusConfig.FAIL);
                        resultView.setMessage("密码信息错误！");
                    }
                }else{
                    resultView.setStatus(StatusConfig.FAIL);
                    resultView.setMessage("手机号信息错误！");
                }
            }else{
                resultView.setStatus(StatusConfig.FAIL);
                resultView.setMessage("用户名信息错误！");
            }
        }else{
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("注册信息错误！");
        }
        return resultView;
    }

    @RequestMapping(value = "/userBean/login",method = RequestMethod.GET)
    public ResultView logon(HttpServletRequest hsRequest, HttpServletResponse hsResponse, LoginInfo loginInfo){
        HttpSession httpSession = hsRequest.getSession();
        //初始化对象
        resultView = new ResultView();
        logonResult = new LogonResult();
        if(loginInfo != null){
            if(loginInfo.getUsername() != null && REVUtils.isLogonInfo(loginInfo.getUsername())){
                if(loginInfo.getPassword() != null && REVUtils.isLogonInfo(loginInfo.getPassword())){
                    logonResult = userService.selectTByKey(loginInfo.getUsername(), loginInfo.getPassword());
                    if(logonResult != null){//判断数据库数据 验证
                        String uid = httpSession.getId();
                        if(redisService.exist(uid)){
                            redisService.drop(uid);
                        }
                        redisService.insert(uid,logonResult.getAccount_no(),RedisConfig.REDIS_TIME_30MINUTE);
                        CookieUtils.setCookie(hsResponse,"uid",uid,CookieConfig.COOKIE_30MINUTE);
                        resultView.setStatus(StatusConfig.SUCCESS);
                        resultView.setMessage("登录成功！");
                    }else{
                        resultView.setStatus(StatusConfig.FAIL);
                        resultView.setMessage("登录失败！");
                    }
                }else{
                    resultView.setStatus(StatusConfig.FAIL);
                    resultView.setMessage("密码信息错误！");
                }
            }else{
                resultView.setStatus(StatusConfig.FAIL);
                resultView.setMessage("用户名信息错误！");
            }
        }else{
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("登录信息错误！");
        }
        return resultView;
    }
}
