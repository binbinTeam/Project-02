package com.zhzteam.zhz233.filter;

import com.zhzteam.zhz233.common.utils.CookieUtils;
import com.zhzteam.zhz233.service.zlb.RedisService;
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

@Order(Integer.MAX_VALUE - 1)
@WebFilter(urlPatterns = "/*",filterName = "AuthPathFilter")
public class AuthPathFilter implements Filter {
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
        /**
         * 请求URL
         */
        String servletPath = hsRequest.getServletPath();
        /**
         * 请求方式
         */
        String requestType = hsRequest.getHeader("X-Requested-With");
        /**
         * 获取uid
         */
        String uid = hsRequest.getParameter("uid");
        /**
         * 过滤url
         */
        System.err.println(servletPath);

        if (servletPath.endsWith(".ico") || servletPath.endsWith(".png") || servletPath.endsWith(".gif") || servletPath.endsWith(".css") || servletPath.endsWith(".map") || servletPath.endsWith(".otf") || servletPath.endsWith(".eot") || servletPath.endsWith(".svg") || servletPath.endsWith(".ttf") || servletPath.endsWith(".woff") || servletPath.endsWith(".woff2")) {//不过滤 文件夹
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (servletPath.equals("/")) {//访问 index
            hsResponse.sendRedirect(hsRequest.getContextPath() + "/zlb/index");
            return;
        }

        if (servletPath.equals("/zlb/FB/error") || servletPath.equals("/zlb/index") || servletPath.equals("/zlb/login") || servletPath.equals("/zlb/register")) {//不过滤 url
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (servletPath.equals("/zlb/getIndexAutoInfo")
                || servletPath.equals("/zlb/getTasteAutoInfo")
                || servletPath.equals("/zlb/getGoodsInfo")
                || servletPath.equals("/zlb/getGoodsRentInfo")
                || servletPath.equals("/zlb/getGoodsTasteRentInfo")
                ) {//不过滤 url
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (servletPath.equals("/zlb/userBean/login")
                || servletPath.equals("/zlb/userBean/register")
                || servletPath.equals("/zlb/userBean/getUserInfo")
                || servletPath.equals("/zlb/userBean/checkCellPhone")
                || servletPath.equals("/zlb/userBean/checkUserName")
                || servletPath.equals("/zlb/userBean/checkCode")
                || servletPath.equals("/zlb/userBean/getCode")
                ) {//不过滤 登录 请求 url
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (!uid.isEmpty()) {
            if (redisService.exist(uid)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                hsResponse.setCharacterEncoding("UTF-8");
                hsResponse.sendError(HttpStatus.NOT_FOUND.value(), "访问错误！");
                //hsResponse.sendRedirect(hsRequest.getContextPath() + "/zlb/login");
                return;
            }
        } else {
            hsResponse.setCharacterEncoding("UTF-8");
            hsResponse.sendError(HttpStatus.NOT_FOUND.value(), "访问错误！");
            //hsResponse.sendRedirect(hsRequest.getContextPath() + "/zlb/login");
            return;
        }
    }

    @Override
    public void destroy() {
        System.err.println("destroy...");
    }
}
