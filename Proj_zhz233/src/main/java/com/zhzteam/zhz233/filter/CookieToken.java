/*
package com.zhzteam.zhz233.filter;

import com.zhzteam.zhz233.common.config.CookieConfig;
import com.zhzteam.zhz233.common.config.JWTConfig;
import com.zhzteam.zhz233.common.config.RedisConfig;
import com.zhzteam.zhz233.common.utils.CookieUtils;
import com.zhzteam.zhz233.common.utils.GsonUtils;
import com.zhzteam.zhz233.common.utils.JWTUtils;
import com.zhzteam.zhz233.model.JWTModel;
import com.zhzteam.zhz233.model.JWTResult;
import com.zhzteam.zhz233.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

*/
/*@Order(Integer.MAX_VALUE)
@WebFilter(urlPatterns = "/*",filterName = "AuthPathFilter")*//*

public class CookieToken implements Filter {
    @Autowired
    RedisService redisService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest hsRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse hsResponse = (HttpServletResponse) servletResponse;
        */
/**
         * 请求URL
         *//*

        String servletPath = hsRequest.getServletPath();
        */
/**
         * 请求方式
         *//*

        String requestType = hsRequest.getHeader("X-Requested-With");
        */
/**
         * 获取cookie uid
         *//*

        Cookie uidCookie = CookieUtils.getCookieByName(hsRequest,"uid");
        */
/**
         * 获取cookie token
         *//*

        Cookie tokenCookie = CookieUtils.getCookieByName(hsRequest,"token");
        */
/**
         * 过滤url
         *//*

        System.err.println(servletPath);

        if(servletPath.endsWith(".ico")
                || servletPath.endsWith(".png")
                || servletPath.endsWith(".gif")
                || servletPath.endsWith(".css")
                || servletPath.endsWith(".map")
                || servletPath.endsWith(".otf")
                || servletPath.endsWith(".eot")
                || servletPath.endsWith(".svg")
                || servletPath.endsWith(".ttf")
                || servletPath.endsWith(".woff")
                || servletPath.endsWith(".woff2")){//不过滤 文件夹
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if(servletPath.equals("/")){//访问 index
            hsResponse.sendRedirect(hsRequest.getContextPath() + "/index");
            return;
        }

        if(servletPath.equals("/error")
                || servletPath.equals("/index")
                || servletPath.equals("/login")
                || servletPath.equals("/register")
                ){//不过滤 url
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if(servletPath.equals("/getIndexAutoInfo")
                ){//不过滤 url
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if(servletPath.equals("/userBean/login")
                || servletPath.equals("/userBean/register")
                || servletPath.equals("/userBean/logout")
                ){//不过滤 登录 请求 url
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if(tokenCookie != null && uidCookie != null){//判断session 缓存
            JWTResult jwtResult = JWTUtils.validateJWT(tokenCookie.getValue());
            if(jwtResult.getStatus() == JWTConfig.JWT_SUCCESS){//验证成功
                if(redisService.exist(uidCookie.getValue())){//跳转成功
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }else{//跳转失败
                    hsResponse.setCharacterEncoding("UTF-8");
                    hsResponse.sendError(HttpStatus.NOT_FOUND.value(),"用户未登录授权访问！");
                    return;
                }
            }
            if(jwtResult.getStatus() == JWTConfig.JWT_EXPIRE){//超时 刷新
                if(redisService.exist(uidCookie.getValue())){//跳转成功
                    redisService.drop(uidCookie.getValue());
                    redisService.insert(uidCookie.getValue(),uidCookie.getValue(),RedisConfig.REDIS_TIME_30MINUTE);
                    String claims = GsonUtils.objectToJsonStr(jwtResult.getClaims());
                    JWTModel jwtModel = GsonUtils.jsonStrToObject(claims, JWTModel.class);
                    String _token = JWTUtils.createJWT(
                            jwtModel.getIss(),
                            jwtModel.getAud(),
                            jwtModel.getJti(),
                            jwtModel.getSub(),
                            JWTConfig.JWT_TIME_30MINUTE);
                    CookieUtils.setCookie(hsResponse,"token", _token, CookieConfig.COOKIE_30MINUTE);
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }else{

                }
            }
            if(jwtResult.getStatus() == JWTConfig.JWT_FAIL){//验证失败
                hsResponse.setCharacterEncoding("UTF-8");
                hsResponse.sendError(HttpStatus.NOT_FOUND.value(),"用户未登录授权访问！");
                return;
            }else{
                hsResponse.setCharacterEncoding("UTF-8");
                hsResponse.sendError(HttpStatus.NOT_FOUND.value(),"用户未登录授权访问！");
                return;
            }
        }else{
            hsResponse.setCharacterEncoding("UTF-8");
            hsResponse.sendError(HttpStatus.NOT_FOUND.value(),"用户未登录授权访问！");
            return;
        }
    }

    @Override
    public void destroy() {
        System.err.println("destroy...");
    }
}
*/
