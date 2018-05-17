package com.zhzteam.zhz233.controller.zlb;

import com.zhzteam.zhz233.common.config.*;
import com.zhzteam.zhz233.common.utils.*;
import com.zhzteam.zhz233.model.zlb.*;
import com.zhzteam.zhz233.service.zlb.RedisService;
import com.zhzteam.zhz233.service.zlb.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping(value = "/zlb")
public class UserController {
    @Autowired
    RedisService redisService;

    @Autowired
    UserService userService;

    private ResultView resultView;
    private UserResult userResult;
    private CodeResult codeResult;
    private SMSResult smsResult;

    private Map<String,Object> reMap;

    @RequestMapping(value = "/userBean/getUserLeaseOrderInfo")
    public ResultView getUserLeaseOrderInfo(HttpServletRequest hsRequest){
        //初始化 容器
        resultView = new ResultView();
        reMap = new HashMap<String, Object>();

        if(1==1){
            resultView.setStatus(StatusConfig.SUCCESS);
            resultView.setMessage("获取用户订单信息成功！");
        }else{
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("获取用户订单信息失败！");
        }
        return resultView;
    }

    @RequestMapping(value = "/userBean/getUserInfo")
    public ResultView register(HttpServletRequest hsRequest){
        //初始化 容器
        resultView = new ResultView();
        userResult = new UserResult();
        reMap = new HashMap<String, Object>();
        //获取uid
        String uid = hsRequest.getParameter("uid");
        if(uid != null){//判断uid
            if(redisService.exist(uid)){
                //读取 数据
                userResult = userService.selectTByNo(redisService.select(uid));
                String userName = userResult.getAccount();
                //放置User 信息
                reMap.put("userName",userName);
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

    @RequestMapping(value = "/userBean/checkCellPhone")
    public ResultView checkCellPhone(@Param("cellphone") String cellphone) {
        //初始化对象
        resultView = new ResultView();

        if(cellphone != null){//验证数据 合法性
            if(REVUtils.isLogonPhone(cellphone) && !userService.selectTByCellPhone(cellphone)){
                resultView.setStatus(StatusConfig.SUCCESS);
                resultView.setMessage("手机号未注册！");
            }else {
                resultView.setStatus(StatusConfig.FAIL);
                resultView.setMessage("手机号已注册！");
            }
        }else {
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("手机号验证失败！");
        }
        return resultView;
    }

    @RequestMapping(value = "/userBean/checkUserName")
    public ResultView checkUserName(@Param("username") String username) {
        //初始化对象
        resultView = new ResultView();

        if(username != null){//验证数据 合法性
            if(REVUtils.isLogonInfo(username) && !userService.selectTByUserName(username)){
                resultView.setStatus(StatusConfig.SUCCESS);
                resultView.setMessage("用户名未注册！");
            }else {
                resultView.setStatus(StatusConfig.FAIL);
                resultView.setMessage("用户名已注册！");
            }
        }else {
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("用户名验证失败！");
        }
        return resultView;
    }

    @RequestMapping(value = "/userBean/checkCode")
    public ResultView checkCode(@Param("cellphone") String cellphone,@Param("code") String code) {
        //初始化对象
        resultView = new ResultView();
        if(cellphone != null && code != null) {//验证数据 合法性
            if(REVUtils.isLogonPhone(cellphone) && redisService.exist(cellphone)){
                if(REVUtils.isLogonInfo(code) && redisService.select(cellphone).equals(code)){
                    resultView.setStatus(StatusConfig.SUCCESS);
                    resultView.setMessage("验证码验证成功！");
                }else {
                    resultView.setStatus(StatusConfig.FAIL);
                    resultView.setMessage("验证码错误！");
                }
            }else {
                resultView.setStatus(StatusConfig.FAIL);
                resultView.setMessage("验证码验证失效！");
            }
        }else {
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("验证码验证失败！");
        }
        return resultView;
    }

    @RequestMapping(value = "/userBean/getCode")
    public ResultView getCode(@Param("cellphone") String cellphone){
        //初始化对象
        resultView = new ResultView();
        codeResult = new CodeResult();
        smsResult = new SMSResult();

        if(cellphone != null){//验证数据 合法性
            if(REVUtils.isLogonPhone(cellphone) && !userService.selectTByCellPhone(cellphone)){
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
                resultView.setMessage("手机号错误或不可用！");
            }
        }else{
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("验证码获取失败！");
        }
        return resultView;
    }

    @RequestMapping(value = "/userBean/register")
    public ResultView register(HttpServletRequest hsRequest, HttpServletResponse hsResponse,@RequestBody RegisterInfo registerInfo) {
        HttpSession httpSession = hsRequest.getSession();
        //初始化对象
        resultView = new ResultView();
        userResult = new UserResult();
        reMap = new HashMap<String, Object>();

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
                                                userResult = userService.selectTByKey(registerInfo.getUsername(), registerInfo.getPassword());
                                                if(userResult != null) {//注册 登录用户
                                                    String uid= httpSession.getId();
                                                    if(redisService.exist(uid)){
                                                        redisService.drop(uid);
                                                    }
                                                    redisService.insert(uid, userResult.getAccount_no(),RedisConfig.REDIS_TIME_30MINUTE);
                                                    reMap.put("uid",uid);
                                                    resultView.setReMap(reMap);
                                                    resultView.setStatus(StatusConfig.SUCCESS);
                                                    resultView.setMessage("注册登录成功！");
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

    @RequestMapping(value = "/userBean/login")
    public ResultView logon(HttpServletRequest hsRequest, HttpServletResponse hsResponse,@RequestBody UserInfo userInfo){
        HttpSession httpSession = hsRequest.getSession();
        //初始化对象
        resultView = new ResultView();
        userResult = new UserResult();
        if(userInfo != null){
            if(userInfo.getUsername() != null && REVUtils.isLogonInfo(userInfo.getUsername())){
                if(userInfo.getPassword() != null && REVUtils.isLogonInfo(userInfo.getPassword())){
                    userResult = userService.selectTByKey(userInfo.getUsername(), userInfo.getPassword());
                    if(userResult != null){//判断数据库数据 验证
                        String uid = httpSession.getId();
                        if(redisService.exist(uid)){
                            redisService.drop(uid);
                        }
                        redisService.insert(uid, userResult.getAccount_no(),RedisConfig.REDIS_TIME_30MINUTE);
                        reMap.put("uid",uid);
                        resultView.setReMap(reMap);
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

    @RequestMapping("/login")
    public PathResult login(){
        PathResult pathResult = new PathResult();
        pathResult.setPath("/login");
        pathResult.setStatus(StatusConfig.SUCCESS);
        return pathResult;
    }

    @RequestMapping("/register")
    public PathResult register(){
        PathResult pathResult = new PathResult();
        pathResult.setPath("/register");
        pathResult.setStatus(StatusConfig.SUCCESS);
        return pathResult;
    }
}
