package cn.zhsit.authority.interceptors;

import cn.zhsit.common.constants.ZhsConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class CharacterEncodingInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding(ZhsConstants.UTF8);
        response.setCharacterEncoding(ZhsConstants.UTF8);
        return true;
    }

}
