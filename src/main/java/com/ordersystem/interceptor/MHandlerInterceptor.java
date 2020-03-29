package com.ordersystem.interceptor;

import com.ordersystem.util.MConstant.MConstant;
import com.ordersystem.util.MWebUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//处理拦截适配器
public class MHandlerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        MWebUtil.noCache(response);

        request.setAttribute("ContextPath", request.getContextPath());

        boolean flag = false;
        String url = request.getRequestURI();
        String adminPath = MConstant.ADMIN_PATH_PREV;
        String userPath = MConstant.USER_PATH_PREV;
        String loginPath = MConstant.LOGIN_PATH_PREV;
        String devResPath = request.getContextPath() + MConstant.DEV_RES_PATH;
        String loginResPath = request.getContextPath() + MConstant.LOGIN_RES_PATH;
        String buyResPath = request.getContextPath() + MConstant.BUY_RES_PATH;
        if (url.startsWith(adminPath) || url.startsWith(userPath) || url.startsWith(loginPath)) {
            if (!flag) {
                if (url.startsWith(adminPath)) {
                    request.setAttribute("devResPath", devResPath);
                } else if (url.startsWith(loginPath)) {
                    request.setAttribute("loginResPath", loginResPath);
                } else if (url.startsWith(userPath)) {
                    request.setAttribute("buyResPath", buyResPath);
                }
            }
//            else {
//
//            }
        } else {
            MWebUtil.noCache(response);
            flag = true;
        }
        flag = true;
        return flag;
    }

}